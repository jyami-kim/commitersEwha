package com.jyami.commitersewha.controller;

import com.jyami.commitersewha.domain.User;
import com.jyami.commitersewha.payload.DefaultResponse;
import com.jyami.commitersewha.payload.request.PageRequest;
import com.jyami.commitersewha.payload.request.PostRequest;
import com.jyami.commitersewha.payload.response.PostResponse;
import com.jyami.commitersewha.security.CurrentUser;
import com.jyami.commitersewha.security.UserPrincipal;
import com.jyami.commitersewha.service.PostService;
import com.jyami.commitersewha.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.jyami.commitersewha.payload.ResponseMessage.*;

/**
 * Created by jyami on 2020/09/30
 */
@RequestMapping("api")
@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @GetMapping("posts")
    public ResponseEntity<?> getAllPostWithPage(@RequestParam(required = false) Integer page,
                                                @RequestParam(required = false) Integer size,
                                                @RequestParam(required = false) Sort.Direction direction) {
        log.info("---getAllPage");
        PageRequest pageRequest = new PageRequest(page, size, direction);
        Page<PostResponse> postOutLineResponse = postService.getPostOutLineResponse(pageRequest);
        return ResponseEntity.ok()
                .body(DefaultResponse.of(HttpStatus.OK, GET_POST_LIST, postOutLineResponse));
    }

    @PostMapping("post")
    public ResponseEntity<?> createPost(@CurrentUser UserPrincipal userPrincipal, @RequestBody PostRequest postRequest) {
        User user = userService.getUserFromId(userPrincipal.getId());
        PostResponse postResponse = postService.createNewPost(user, postRequest);
        log.info("---createPost success :{} - {}", user.getUserId(), postResponse);
        return ResponseEntity.ok()
                .body(DefaultResponse.of(HttpStatus.OK, SUCCESS_POST_CREATE, postResponse));
    }

    @GetMapping("post/{postId}")
    // TODO : 댓글까지 함께 조회
    public ResponseEntity<?> getPostDetail(@CurrentUser UserPrincipal userPrincipal, @PathVariable Long postId) {
        PostResponse postResponse = postService.getDetailPost(postId);
        log.info("---getPost success : viewer {} => {}", userPrincipal.getId(), postResponse);
        return ResponseEntity.ok()
                .body(DefaultResponse.of(HttpStatus.OK, GET_POST_DETAIL, postResponse));
    }

    @PutMapping("post")
    public ResponseEntity<?> updatePost(@CurrentUser UserPrincipal userPrincipal,
                                        @Valid @RequestBody PostRequest postRequest) {
        PostResponse postResponse = postService.updatePost(userPrincipal.getId(), postRequest);
        log.info("---updatePost success : {} ", postResponse);
        return ResponseEntity.ok()
                .body(DefaultResponse.of(HttpStatus.OK, SUCCESS_POST_UPDATE, postResponse));
    }

    @DeleteMapping("post/{postId}")
    public ResponseEntity<?> deletePost(@CurrentUser UserPrincipal userPrincipal, @PathVariable Long postId) {
        postService.deletePost(userPrincipal.getId(), postId);
        log.info("---deletePost success : {} => {} ", userPrincipal.getId(), postId);
        return ResponseEntity.ok()
                .body(DefaultResponse.of(HttpStatus.OK, SUCCESS_POST_DELETE, postId));
    }

}
