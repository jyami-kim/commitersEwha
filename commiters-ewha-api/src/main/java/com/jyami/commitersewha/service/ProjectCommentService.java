package com.jyami.commitersewha.service;

import com.jyami.commitersewha.domain.comment.Comment;
import com.jyami.commitersewha.domain.post.Post;
import com.jyami.commitersewha.domain.projectComment.ProjectComment;
import com.jyami.commitersewha.domain.projectComment.ProjectCommentRepository;
import com.jyami.commitersewha.domain.projectPost.ProjectPost;
import com.jyami.commitersewha.domain.user.User;
import com.jyami.commitersewha.exception.NotAccessUserException;
import com.jyami.commitersewha.exception.ResourceNotFoundException;
import com.jyami.commitersewha.payload.ResponseMessage;
import com.jyami.commitersewha.payload.request.CommentRequest;
import com.jyami.commitersewha.payload.response.CommentResponse;
import com.jyami.commitersewha.payload.response.LikeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by jyami on 2020/10/02
 */
@Service
@RequiredArgsConstructor
public class ProjectCommentService {

    private final ProjectPostService projectPostService;
    private final ProjectCommentRepository projectCommentRepository;

    @Transactional
    public CommentResponse createComment(User user, CommentRequest commentRequest) {
        ProjectPost projectPost = projectPostService.findPostFromId(commentRequest.getPostId());
        validateParentComment(commentRequest.getPostId());
        ProjectComment comment = projectCommentRepository.save(commentRequest.toProjectPostEntity(projectPost, user));
        return CommentResponse.fromProjectPostEntity(comment);
    }

    @Transactional
    public List<CommentResponse> getCommentsByPostId(Long postId) {
        List<ProjectComment> comments = projectCommentRepository.findPostByIdWithComments(postId);
        if (comments.size() == 0) {
            ProjectPost post = projectPostService.findPostFromId(postId);
            post.addHitCount();
            return Collections.emptyList();
        }
        comments.get(0).getProjectPost().addHitCount();

        return comments.stream()
                .map(CommentResponse::fromProjectPostEntity)
                .collect(Collectors.toList());
    }

    private void validateParentComment(long parentId) {
        if (parentId != -1) {
            projectCommentRepository.findById(parentId)
                    .orElseThrow(() -> new ResourceNotFoundException("projectComment", "parentId", parentId));
        }
    }

    @Transactional
    public void deleteComment(Long userId, Long commentId) {
        ProjectComment comment = projectCommentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("projectComment", "commentId", commentId));
        validateAuthorizedUser(userId, comment);
        projectCommentRepository.delete(comment);
    }

    private void validateAuthorizedUser(Long accessUserId, ProjectComment comment) {
        long authorId = comment.getUser().getUserId();
        if (authorId != accessUserId)
            throw new NotAccessUserException(ResponseMessage.CAN_NOT_UPDATED_THIS_ACCESS_USER);
    }

    @Transactional
    public LikeResponse changeUserLikeStatus(User user, Long commentId) {
        ProjectComment projectComment = projectCommentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("projectComment", "commentId", commentId));
        boolean likeStatus = changeLikeStatus(user, projectComment);
        projectCommentRepository.save(projectComment);
        return LikeResponse.ofCommentLike(user.getUserId(), projectComment.getProjectPost().getProjectPostId(), commentId, likeStatus);
    }

    private boolean changeLikeStatus(User user, ProjectComment comment) {
        Set<User> likesUser = comment.getLikesUser();
        if (likesUser.contains(user)) {
            comment.removeLikeUser(user);
            return false;
        }
        comment.addLikeUser(user);
        return true;
    }
}
