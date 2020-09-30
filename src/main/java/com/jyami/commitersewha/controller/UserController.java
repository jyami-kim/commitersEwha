package com.jyami.commitersewha.controller;

import com.jyami.commitersewha.domain.User;
import com.jyami.commitersewha.payload.DefaultResponse;
import com.jyami.commitersewha.payload.response.UserInfoResponse;
import com.jyami.commitersewha.security.CurrentUser;
import com.jyami.commitersewha.security.UserPrincipal;
import com.jyami.commitersewha.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jyami.commitersewha.payload.ResponseMessage.GET_CURRENT_USER_INFO;


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
        UserInfoResponse currentUser = userService.getCurrentUser(userPrincipal);
        return ResponseEntity.ok().body(DefaultResponse.of(HttpStatus.OK, GET_CURRENT_USER_INFO, currentUser));
    }

//    @PostMapping
//    public ResponseEntity<?> processNextSignup(@CurrentUser UserPrincipal userPrincipal){
//        log.info("-- complete user signup : {}", userPrincipal.getId());
//        userService.
//
//    }

}
