package com.jyami.commitersewha.service;

import com.jyami.commitersewha.domain.Comment;
import com.jyami.commitersewha.domain.Post;
import com.jyami.commitersewha.domain.PostRepository;
import com.jyami.commitersewha.domain.User;
import com.jyami.commitersewha.exception.NotAccessUserException;
import com.jyami.commitersewha.exception.ResourceNotFoundException;
import com.jyami.commitersewha.payload.request.PageRequest;
import com.jyami.commitersewha.payload.request.PostRequest;
import com.jyami.commitersewha.payload.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.jyami.commitersewha.payload.ResponseMessage.CAN_NOT_UPDATED_THIS_ACCESS_USER;

/**
 * Created by jyami on 2020/09/30
 */
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Page<PostResponse> getPostOutLineResponse(PageRequest pageRequest) {
        return postRepository.findAll(pageRequest.of())
                .map(PostResponse::fromEntityToShotDto);
    }

    @Transactional
    public PostResponse createNewPost(User user, PostRequest postRequest) {
        Post post = postRepository.save(postRequest.toEntity(user));
        return PostResponse.fromEntityToShotDto(post);
    }

    @Transactional
    public PostResponse getDetailPost(Long postId) {
        Post post = postRepository.findPostByIdWithComments(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        post.addHitCount();
        return PostResponse.fromEntityToLongDto(post);
    }

    @Transactional
    public PostResponse updatePost(Long userId, PostRequest postRequest) {
        Post post = findPostFromId(postRequest.getPostId());
        validateAuthorizedUser(userId, post);

        postRequest.updateEntity(post);

        Post updatedPost = postRepository.save(post);
        return PostResponse.fromEntityToLongDto(updatedPost);
    }

    @Transactional
    public void deletePost(Long userId, Long postId) {
        Post post = findPostFromId(postId);
        validateAuthorizedUser(userId, post);

        postRepository.delete(post);
    }

    public Post findPostFromId(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
    }

    private void validateAuthorizedUser(Long accessUserId, Post post) {
        long authorId = post.getUser().getUserId();
        if (authorId != accessUserId)
            throw new NotAccessUserException(CAN_NOT_UPDATED_THIS_ACCESS_USER);
    }
}
