package com.jyami.commitersewha.service;

import com.jyami.commitersewha.domain.projectPost.ProjectPost;
import com.jyami.commitersewha.domain.projectPost.ProjectPostRepository;
import com.jyami.commitersewha.domain.user.User;
import com.jyami.commitersewha.exception.NotAccessUserException;
import com.jyami.commitersewha.exception.ResourceNotFoundException;
import com.jyami.commitersewha.payload.ResponseMessage;
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
    public ProjectPostResponse getPostDetail(Long projectPostId) {
        ProjectPost projectPost = projectPostRepository.findByProjectPostId(projectPostId)
                .orElseThrow(() -> new ResourceNotFoundException("projectPost", "id", projectPostId));
        projectPost.addHitCount();
        return ProjectPostResponse.fromEntityToWithoutComment(projectPost);
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
    public ProjectPostResponse updatePost(Long userId, ProjectPostRequest projectPostRequest) {
        ProjectPost projectPost = findPostFromId(projectPostRequest.getProjectPostId());
        validateAuthorizedUser(userId, projectPost);

        projectPostRequest.updateEntity(projectPost);

        ProjectPost updatedPost = projectPostRepository.save(projectPost);
        return ProjectPostResponse.fromEntityToWithoutComment(updatedPost);
    }

    @Transactional
    public void deletePost(Long userId, Long projectPostId) {
        ProjectPost projectPost = findPostFromId(projectPostId);
        validateAuthorizedUser(userId, projectPost);

        projectPostRepository.delete(projectPost);
    }

    private void validateAuthorizedUser(Long accessUserId, ProjectPost projectPost) {
        long authorId = projectPost.getUser().getUserId();
        if (authorId != accessUserId)
            throw new NotAccessUserException(ResponseMessage.CAN_NOT_UPDATED_THIS_ACCESS_USER);
    }

    @Transactional
    public LikeResponse changeUserLikeStatus(User user, Long postId) {
        ProjectPost post = findPostFromId(postId);
        boolean likeStatus = changeLikeStatus(user, post);
        projectPostRepository.save(post);
        return LikeResponse.ofPostLike(user.getUserId(), postId, likeStatus);
    }

    private boolean changeLikeStatus(User user, ProjectPost projectPost) {
        Set<User> likesUser = projectPost.getLikesUser();
        if (likesUser.contains(user)) {
            projectPost.removeLikeUser(user);
            return false;
        }
        projectPost.addLikeUser(user);
        return true;
    }

}
