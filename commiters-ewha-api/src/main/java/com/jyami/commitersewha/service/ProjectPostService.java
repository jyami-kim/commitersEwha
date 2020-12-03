package com.jyami.commitersewha.service;

import com.jyami.commitersewha.domain.post.Post;
import com.jyami.commitersewha.domain.post.PostRepository;
import com.jyami.commitersewha.domain.projectPost.ProjectPost;
import com.jyami.commitersewha.domain.projectPost.ProjectPostRepository;
import com.jyami.commitersewha.domain.user.User;
import com.jyami.commitersewha.exception.NotAccessUserException;
import com.jyami.commitersewha.exception.ResourceNotFoundException;
import com.jyami.commitersewha.payload.ResponseMessage;
import com.jyami.commitersewha.payload.request.PostRequest;
import com.jyami.commitersewha.payload.request.ProjectPostRequest;
import com.jyami.commitersewha.payload.request.SearchRequest;
import com.jyami.commitersewha.payload.response.LikeResponse;
import com.jyami.commitersewha.payload.response.ProjectPostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by jyami on 2020/09/30
 */
@Service
@RequiredArgsConstructor
public class ProjectPostService {

    private final ProjectPostRepository projectPostRepository;

    public Page<ProjectPostResponse> getPostOutLineResponse(SearchRequest searchRequest) {
        return projectPostRepository.findAllBySearchCondition(searchRequest)
                .map(ProjectPostResponse::fromEntityToWithoutComment);
    }

    @Transactional
    public ProjectPostResponse createNewPost(User user, ProjectPostRequest projectPostRequest) {
        ProjectPost projectPost = projectPostRepository.save(projectPostRequest.toEntity(user));
        return ProjectPostResponse.fromEntityToWithoutComment(projectPost);
    }

    public ProjectPost findPostFromId(Long projectPostId) {
        return projectPostRepository.findByProjectPostId(projectPostId)
                .orElseThrow(() -> new ResourceNotFoundException("projectPost", "id", projectPostId));
    }

    @Transactional
    public ProjectPostResponse updatePost(Long userId, PostRequest postRequest) {
        ProjectPost projectPost = findPostFromId(postRequest.getPostId());
        validateAuthorizedUser(userId, projectPost);

        postRequest.updateEntity(post);

        Post updatedPost = projectPostRepository.save(post);
        return ProjectPostResponse.fromEntityToWithoutComment(updatedPost);
    }

    @Transactional
    public void deletePost(Long userId, Long postId) {
        Post post = findPostFromId(postId);
        validateAuthorizedUser(userId, post);

        projectPostRepository.delete(post);
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
        projectPostRepository.save(post);
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
