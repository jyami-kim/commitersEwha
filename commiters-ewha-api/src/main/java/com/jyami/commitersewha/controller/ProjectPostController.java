package com.jyami.commitersewha.controller;

import com.jyami.commitersewha.domain.user.User;
import com.jyami.commitersewha.payload.DefaultResponse;
import com.jyami.commitersewha.payload.ResponseCode;
import com.jyami.commitersewha.payload.request.PostRequest;
import com.jyami.commitersewha.payload.request.SearchRequest;
import com.jyami.commitersewha.payload.response.LikeResponse;
import com.jyami.commitersewha.payload.response.PostResponse;
import com.jyami.commitersewha.security.CurrentUser;
import com.jyami.commitersewha.security.GoogleUserPrincipal;
import com.jyami.commitersewha.service.PostService;
import com.jyami.commitersewha.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.jyami.commitersewha.payload.ResponseMessage.*;

/**
 * Created by jyami on 2020/09/30
 */
@RequestMapping("api/project")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ProjectPostController {
    private final UserService userService;

    @GetMapping("posts")
    public ResponseEntity<?> getAllPostWithPage(SearchRequest searchRequest) {
        log.info("---getAllPage : parameter = {}", searchRequest);
        Page<PostResponse> postOutLineResponse = postService.getPostOutLineResponse(searchRequest);
        return ResponseEntity.ok()
                .body(DefaultResponse.of(ResponseCode.OK, GET_POST_LIST, postOutLineResponse));
    }

    @PostMapping("post")
    public ResponseEntity<?> createPost(@CurrentUser GoogleUserPrincipal googleUserPrincipal, @RequestBody PostRequest postRequest) {
        User user = userService.getUserFromId(googleUserPrincipal.getId());
        PostResponse postResponse = postService.createNewPost(user, postRequest);
        log.info("---createPost success :{} - {}", user.getUserId(), postResponse);
        return ResponseEntity.ok()
                .body(DefaultResponse.of(ResponseCode.OK, SUCCESS_POST_CREATE, postResponse));
    }

    @PutMapping("post")
    public ResponseEntity<?> updatePost(@CurrentUser GoogleUserPrincipal googleUserPrincipal,
                                        @Valid @RequestBody PostRequest postRequest) {
        PostResponse postResponse = postService.updatePost(googleUserPrincipal.getId(), postRequest);
        log.info("---updatePost success : {} ", postResponse);
        return ResponseEntity.ok()
                .body(DefaultResponse.of(ResponseCode.OK, SUCCESS_POST_UPDATE, postResponse));
    }

    @DeleteMapping("post/{postId}")
    public ResponseEntity<?> deletePost(@CurrentUser GoogleUserPrincipal googleUserPrincipal, @PathVariable Long postId) {
        postService.deletePost(googleUserPrincipal.getId(), postId);
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
        LikeResponse likeResponse = postService.changeUserLikeStatus(user, postId);
        log.info("---post like status success change :{}", likeResponse);
        return ResponseEntity.ok()
                .body(DefaultResponse.of(ResponseCode.OK, SUCCESS_CHANGE_LIKE_STATUS, likeResponse));
    }

}
