package com.jyami.commitersewha.controller;

import com.jyami.commitersewha.domain.User;
import com.jyami.commitersewha.payload.DefaultResponse;
import com.jyami.commitersewha.payload.request.UserUpdateRequest;
import com.jyami.commitersewha.payload.response.UserInfoResponse;
import com.jyami.commitersewha.security.CurrentUser;
import com.jyami.commitersewha.security.UserPrincipal;
import com.jyami.commitersewha.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.jyami.commitersewha.payload.ResponseMessage.*;


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
        UserInfoResponse currentUser = userService.getCurrentUser(userPrincipal.getId());
        return ResponseEntity.ok()
                .body(DefaultResponse.of(HttpStatus.OK, GET_CURRENT_USER_INFO, currentUser));
    }

    @PostMapping
    public ResponseEntity<?> nextSignUp(@CurrentUser UserPrincipal userPrincipal,
                                        @RequestBody UserUpdateRequest userUpdateRequest) {
        UserInfoResponse user = userService.nextSignUp(userPrincipal.getId(), userUpdateRequest);
        log.info("-- complete user signup : {}", userPrincipal.getId());
        return ResponseEntity.ok()
                .body(DefaultResponse.of(HttpStatus.OK, SUCCESS_USER_SIGNUP, user));
    }

    @PutMapping()
    public ResponseEntity<?> updateUserInfo(@CurrentUser UserPrincipal userPrincipal,
                                            @RequestBody UserUpdateRequest userUpdateRequest) {
        UserInfoResponse user = userService.nextSignUp(userPrincipal.getId(), userUpdateRequest);
        log.info("-- complete user info update : {}", userPrincipal.getId());
        return ResponseEntity.ok()
                .body(DefaultResponse.of(HttpStatus.OK, UPDATE_USER_INFO, user));
    }

    @GetMapping
    public ResponseEntity<?> getUserInfo(@CurrentUser UserPrincipal userPrincipal, @RequestParam("user") String userSubId) {
        log.info("-- see user info : {} => {}", userPrincipal.getId(), userSubId);
        User user = userService.getUserFromSubId(userSubId);
        UserInfoResponse userInfo = UserInfoResponse.fromEntity(user);
        return ResponseEntity.ok()
                .body(DefaultResponse.of(HttpStatus.OK, GET_USER_INFO, userInfo));
    }


}
