package com.jyami.commitersewha.service;

import com.jyami.commitersewha.domain.comment.Comment;
import com.jyami.commitersewha.domain.post.Post;
import com.jyami.commitersewha.domain.post.PostRepository;
import com.jyami.commitersewha.domain.user.User;
import com.jyami.commitersewha.exception.NotAccessUserException;
import com.jyami.commitersewha.exception.ResourceNotFoundException;
import com.jyami.commitersewha.payload.request.PostRequest;
import com.jyami.commitersewha.payload.request.SearchRequest;
import com.jyami.commitersewha.payload.response.LikeResponse;
import com.jyami.commitersewha.payload.response.PostResponse;
import com.jyami.commitersewha.payload.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Set;

/**
 * Created by jyami on 2020/09/30
 */
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Page<PostResponse> getPostOutLineResponse(SearchRequest searchRequest) {
        return postRepository.findAllBySearchCondition(searchRequest)
                .map(PostResponse::fromEntityToWithoutComment);
    }

    @Transactional
    public PostResponse createNewPost(User user, PostRequest postRequest) {
        Post post = postRepository.save(postRequest.toEntity(user));
        return PostResponse.fromEntityToWithoutComment(post);
    }

    @Transactional
    public PostResponse getDetailPost(Long postId) {
        List<Comment> postByIdWithComments = postRepository.findPostByIdWithComments(postId);
        if (postByIdWithComments.size() == 0) {
            Post post = findPostFromId(postId);
            post.addHitCount();
            return PostResponse.fromEntityToWithoutComment(post);
        }
        postByIdWithComments.get(0).getPost().addHitCount();
        return PostResponse.fromEntityWithCommentDto(postByIdWithComments);
    }

    public Post findPostFromId(Long postId) {
        return postRepository.findByPostId(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
    }

    @Transactional
    public PostResponse updatePost(Long userId, PostRequest postRequest) {
        Post post = findPostFromId(postRequest.getPostId());
        validateAuthorizedUser(userId, post);

        postRequest.updateEntity(post);

        Post updatedPost = postRepository.save(post);
        return PostResponse.fromEntityToWithoutComment(updatedPost);
    }

    @Transactional
    public void deletePost(Long userId, Long postId) {
        Post post = findPostFromId(postId);
        validateAuthorizedUser(userId, post);

        postRepository.delete(post);
    }

    private void validateAuthorizedUser(Long accessUserId, Post post) {
        long authorId = post.getUser().getUserId();
        if (authorId != accessUserId)
            throw new NotAccessUserException(ResponseMessage.CAN_NOT_UPDATED_THIS_ACCESS_USER);
    }

    @Transactional
    public LikeResponse changeUserLikeStatus(User user, Long postId) {
        Post post = findPostFromId(postId);
        boolean likeStatus = changeLikeStatus(user, post);
        postRepository.save(post);
        return LikeResponse.ofPostLike(user.getUserId(), postId, likeStatus);
    }

    private boolean changeLikeStatus(User user, Post post) {
        Set<User> likesUser = post.getLikesUser();
        if (likesUser.contains(user)) {
            post.removeLikeUser(user);
            return false;
        }
        post.addLikeUser(user);
        return true;
    }

}
