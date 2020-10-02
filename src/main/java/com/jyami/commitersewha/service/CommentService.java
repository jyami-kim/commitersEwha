package com.jyami.commitersewha.service;

import com.jyami.commitersewha.domain.*;
import com.jyami.commitersewha.exception.NotAccessUserException;
import com.jyami.commitersewha.exception.ResourceNotFoundException;
import com.jyami.commitersewha.payload.request.CommentRequest;
import com.jyami.commitersewha.payload.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.jyami.commitersewha.payload.ResponseMessage.CAN_NOT_UPDATED_THIS_ACCESS_USER;

/**
 * Created by jyami on 2020/10/02
 */
@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostService postService;
    private final CommentRepository commentRepository;

    @Transactional
    public CommentResponse createComment(User user, CommentRequest commentRequest) {
        Post post = postService.findPostFromId(commentRequest.getPostId());
        validateParentComment(commentRequest.getPostId());
        Comment comment = commentRepository.save(commentRequest.toEntity(post, user));
        return CommentResponse.fromEntity(comment);
    }

    private void validateParentComment(long parentId) {
        if (parentId != -1) {
            commentRepository.findById(parentId)
                    .orElseThrow(() -> new ResourceNotFoundException("comment", "parentId", parentId));
        }
    }


    @Transactional
    public void deleteComment(Long userId, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("comment", "commentId", commentId));
        validateAuthorizedUser(userId, comment);
        commentRepository.delete(comment);
    }

    private void validateAuthorizedUser(Long accessUserId, Comment comment) {
        long authorId = comment.getUser().getUserId();
        if (authorId != accessUserId)
            throw new NotAccessUserException(CAN_NOT_UPDATED_THIS_ACCESS_USER);
    }
}
