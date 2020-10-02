package com.jyami.commitersewha.controller;

import com.jyami.commitersewha.domain.user.User;
import com.jyami.commitersewha.payload.DefaultResponse;
import com.jyami.commitersewha.payload.request.CommentRequest;
import com.jyami.commitersewha.payload.response.CommentResponse;
import com.jyami.commitersewha.security.CurrentUser;
import com.jyami.commitersewha.security.UserPrincipal;
import com.jyami.commitersewha.service.CommentService;
import com.jyami.commitersewha.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping("")
    public ResponseEntity<?> createComment(@CurrentUser UserPrincipal userPrincipal,
                                           @Valid @RequestBody CommentRequest commentRequest) {
        User user = userService.getUserFromId(userPrincipal.getId());
        CommentResponse commentResponse = commentService.createComment(user, commentRequest);
        log.info("---createComment success :{} - {}", user.getUserId(), commentResponse.toString());
        return ResponseEntity.ok()
                .body(DefaultResponse.of(HttpStatus.OK, SUCCESS_COMMENT_CREATE, commentResponse));
    }

    @PostMapping("{commentId}")
    public ResponseEntity<?> deleteComment(@CurrentUser UserPrincipal userPrincipal, @PathVariable long commentId) {
        commentService.deleteComment(userPrincipal.getId(), commentId);
        log.info("---deleteComment success : {} => {} ", userPrincipal.getId(), commentId);
        return ResponseEntity.ok()
                .body(DefaultResponse.of(HttpStatus.OK, SUCCESS_COMMENT_DELETE));
    }
}
