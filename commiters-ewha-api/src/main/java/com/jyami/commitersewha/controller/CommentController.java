package com.jyami.commitersewha.controller;

import com.jyami.commitersewha.domain.user.User;
import com.jyami.commitersewha.payload.DefaultResponse;
import com.jyami.commitersewha.payload.ResponseCode;
import com.jyami.commitersewha.payload.request.CommentRequest;
import com.jyami.commitersewha.payload.response.CommentResponse;
import com.jyami.commitersewha.payload.response.LikeResponse;
import com.jyami.commitersewha.security.CurrentUser;
import com.jyami.commitersewha.security.GoogleUserPrincipal;
import com.jyami.commitersewha.service.CommentService;
import com.jyami.commitersewha.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.jyami.commitersewha.payload.ResponseMessage.*;

/**
 * Created by jyami on 2020/10/02
 */
@RequestMapping("api/comment")
@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;

    @GetMapping("post/{postId}") // 사실상 post 자세히 보기
    public ResponseEntity<?> getCommentsOfPost(@CurrentUser GoogleUserPrincipal googleUserPrincipal, @PathVariable Long postId) {
        List<CommentResponse> commentsByPostId = commentService.getCommentsByPostId(postId);
        log.info("---get comments of post success : viewer {} => {}", googleUserPrincipal.getId(), commentsByPostId);
        return ResponseEntity.ok()
                .body(DefaultResponse.of(ResponseCode.OK, GET_COMMENTS_BY_POSTID, commentsByPostId));
    }



    @PostMapping("")
    public ResponseEntity<?> createComment(@CurrentUser GoogleUserPrincipal googleUserPrincipal,
                                           @Valid @RequestBody CommentRequest commentRequest) {
        User user = userService.getUserFromId(googleUserPrincipal.getId());
        CommentResponse commentResponse = commentService.createComment(user, commentRequest);
        log.info("---createComment success :{} - {}", user.getUserId(), commentResponse.toString());
        return ResponseEntity.ok()
                .body(DefaultResponse.of(ResponseCode.OK, SUCCESS_COMMENT_CREATE, commentResponse));
    }

    @DeleteMapping("{commentId}")
    public ResponseEntity<?> deleteComment(@CurrentUser GoogleUserPrincipal googleUserPrincipal, @PathVariable long commentId) {
        commentService.deleteComment(googleUserPrincipal.getId(), commentId);
        log.info("---deleteComment success : {} => {} ", googleUserPrincipal.getId(), commentId);
        return ResponseEntity.ok()
                .body(DefaultResponse.of(ResponseCode.OK, SUCCESS_COMMENT_DELETE));
    }

    @PostMapping("{commentId}/like")
    public ResponseEntity<?> createPostLike(@CurrentUser GoogleUserPrincipal googleUserPrincipal, @PathVariable Long commentId) {
        User user = userService.getUserFromId(googleUserPrincipal.getId());
        LikeResponse likeResponse = commentService.changeUserLikeStatus(user, commentId);
        log.info("---comment like status success change :{}", likeResponse);
        return ResponseEntity.ok()
                .body(DefaultResponse.of(ResponseCode.OK, SUCCESS_CHANGE_LIKE_STATUS, likeResponse));
    }
}
