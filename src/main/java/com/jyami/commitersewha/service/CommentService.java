package com.jyami.commitersewha.service;

import com.jyami.commitersewha.domain.*;
import com.jyami.commitersewha.payload.request.CommentRequest;
import com.jyami.commitersewha.payload.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Comment comment = commentRepository.save(commentRequest.toEntity(post, user));
        return CommentResponse.fromEntity(comment);
    }




}
