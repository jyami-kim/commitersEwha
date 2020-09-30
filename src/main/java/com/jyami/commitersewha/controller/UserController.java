package com.jyami.commitersewha.controller;

import com.jyami.commitersewha.domain.User;
import com.jyami.commitersewha.payload.DefaultResponse;
import com.jyami.commitersewha.payload.request.UserSignupRequest;
import com.jyami.commitersewha.payload.response.UserInfoResponse;
import com.jyami.commitersewha.security.CurrentUser;
import com.jyami.commitersewha.security.UserPrincipal;
import com.jyami.commitersewha.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.jyami.commitersewha.payload.ResponseMessage.GET_CURRENT_USER_INFO;
import static com.jyami.commitersewha.payload.ResponseMessage.SUCESS_USER_SIGNUP;


/**
 * Created by jyami on 2020/09/22
 */
@RequestMapping("api/user")
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("me")
    public ResponseEntity<?> getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        log.info("-- current user info : {}", userPrincipal.toString());
        User currentUser = userService.getCurrentUser(userPrincipal.getId());
        return ResponseEntity.ok()
                .body(DefaultResponse.of(HttpStatus.OK, GET_CURRENT_USER_INFO, UserInfoResponse.fromEntity(currentUser)));
    }

    @PostMapping
    public ResponseEntity<?> nextSignup(@CurrentUser UserPrincipal userPrincipal,
                                        @RequestBody UserSignupRequest userSignupRequest) {
        User user = userService.nextSignUp(userPrincipal.getId(), userSignupRequest);
        log.info("-- complete user signup : {}", userPrincipal.getId());
        return ResponseEntity.ok()
                .body(DefaultResponse.of(HttpStatus.OK, SUCESS_USER_SIGNUP, UserInfoResponse.fromEntity(user)));
    }

}
