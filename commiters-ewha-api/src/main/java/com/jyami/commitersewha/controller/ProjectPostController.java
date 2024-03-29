package com.jyami.commitersewha.controller;

import com.jyami.commitersewha.domain.user.User;
import com.jyami.commitersewha.payload.DefaultResponse;
import com.jyami.commitersewha.payload.ResponseCode;
import com.jyami.commitersewha.payload.request.PostRequest;
import com.jyami.commitersewha.payload.request.ProjectPostRequest;
import com.jyami.commitersewha.payload.request.SearchRequest;
import com.jyami.commitersewha.payload.response.CommentResponse;
import com.jyami.commitersewha.payload.response.LikeResponse;
import com.jyami.commitersewha.payload.response.PostResponse;
import com.jyami.commitersewha.payload.response.ProjectPostResponse;
import com.jyami.commitersewha.security.CurrentUser;
import com.jyami.commitersewha.security.GoogleUserPrincipal;
import com.jyami.commitersewha.service.PostService;
import com.jyami.commitersewha.service.ProjectPostService;
import com.jyami.commitersewha.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.jyami.commitersewha.payload.ResponseMessage.*;

/**
 * Created by jyami on 2020/09/30
 */
@RequestMapping("api/project")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ProjectPostController {
    private final ProjectPostService projectPostService;
    private final UserService userService;

    @GetMapping("posts")
    public ResponseEntity<?> getAllPostWithPage(SearchRequest searchRequest) {
        log.info("---getAllPage : parameter = {}", searchRequest);
        Page<ProjectPostResponse> postOutLineResponse = projectPostService.getPostOutLineResponse(searchRequest);
        return ResponseEntity.ok()
                .body(DefaultResponse.of(ResponseCode.OK, GET_POST_LIST, postOutLineResponse));
    }

    @GetMapping("post/{postId}") // 사실상 post 자세히 보기
    public ResponseEntity<?> getCommentsOfPost(@CurrentUser GoogleUserPrincipal googleUserPrincipal, @PathVariable Long postId) {
        ProjectPostResponse postDetail = projectPostService.getPostDetail(postId);
        log.info("---get comments of post success : viewer {} => {}", googleUserPrincipal.getId(), postId);
        return ResponseEntity.ok()
                .body(DefaultResponse.of(ResponseCode.OK, GET_COMMENTS_BY_POSTID, postDetail));
    }

    @PostMapping("post")
    public ResponseEntity<?> createPost(@CurrentUser GoogleUserPrincipal googleUserPrincipal, @RequestBody ProjectPostRequest postRequest) {
        User user = userService.getUserFromId(googleUserPrincipal.getId());
        ProjectPostResponse postResponse = projectPostService.createNewPost(user, postRequest);
        log.info("---createPost success :{} - {}", user.getUserId(), postResponse);
        return ResponseEntity.ok()
                .body(DefaultResponse.of(ResponseCode.OK, SUCCESS_POST_CREATE, postResponse));
    }

    @PutMapping("post")
    public ResponseEntity<?> updatePost(@CurrentUser GoogleUserPrincipal googleUserPrincipal,
                                        @Valid @RequestBody ProjectPostRequest postRequest) {
        ProjectPostResponse projectPostResponse = projectPostService.updatePost(googleUserPrincipal.getId(), postRequest);
        log.info("---updatePost success : {} ", projectPostResponse);
        return ResponseEntity.ok()
                .body(DefaultResponse.of(ResponseCode.OK, SUCCESS_POST_UPDATE, projectPostResponse));
    }

    @DeleteMapping("post/{postId}")
    public ResponseEntity<?> deletePost(@CurrentUser GoogleUserPrincipal googleUserPrincipal, @PathVariable Long postId) {
        projectPostService.deletePost(googleUserPrincipal.getId(), postId);
        log.info("---deletePost success : {} => {} ", googleUserPrincipal.getId(), postId);
        return ResponseEntity.ok()
                .body(DefaultResponse.of(ResponseCode.OK, SUCCESS_POST_DELETE, postId));
    }

    /*
     * 좋아요 등록
     */
    @PostMapping("post/{postId}/like")
    public ResponseEntity<?> createPostLike(@CurrentUser GoogleUserPrincipal googleUserPrincipal, @PathVariable Long postId) {
        User user = userService.getUserFromId(googleUserPrincipal.getId());
        LikeResponse likeResponse = projectPostService.changeUserLikeStatus(user, postId);
        log.info("---post like status success change :{}", likeResponse);
        return ResponseEntity.ok()
                .body(DefaultResponse.of(ResponseCode.OK, SUCCESS_CHANGE_LIKE_STATUS, likeResponse));
    }

}
