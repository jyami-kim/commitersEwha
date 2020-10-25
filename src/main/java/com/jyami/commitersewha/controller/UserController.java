package com.jyami.commitersewha.controller;

import com.jyami.commitersewha.domain.user.User;
import com.jyami.commitersewha.payload.DefaultResponse;
import com.jyami.commitersewha.payload.ResponseCode;
import com.jyami.commitersewha.payload.request.UserUpdateRequest;
import com.jyami.commitersewha.payload.response.UserInfoResponse;
import com.jyami.commitersewha.security.CurrentUser;
import com.jyami.commitersewha.security.GoogleUserPrincipal;
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
    public ResponseEntity<?> getCurrentUser(@CurrentUser GoogleUserPrincipal googleUserPrincipal) {
        log.info("-- current user info : {}", googleUserPrincipal.toString());
        UserInfoResponse currentUser = userService.getCurrentUser(googleUserPrincipal.getId());
        return ResponseEntity.ok()
                .body(DefaultResponse.of(ResponseCode.OK, GET_CURRENT_USER_INFO, currentUser));
    }

    @PostMapping
    public ResponseEntity<?> nextSignUp(@CurrentUser GoogleUserPrincipal googleUserPrincipal,
                                        @RequestBody UserUpdateRequest userUpdateRequest) {
        UserInfoResponse user = userService.nextSignUp(googleUserPrincipal.getId(), userUpdateRequest);
        log.info("-- complete user signup : {}", googleUserPrincipal.getId());
        return ResponseEntity.ok()
                .body(DefaultResponse.of(ResponseCode.OK, SUCCESS_USER_SIGNUP, user));
    }

    @PutMapping()
    public ResponseEntity<?> updateUserInfo(@CurrentUser GoogleUserPrincipal googleUserPrincipal,
                                            @RequestBody UserUpdateRequest userUpdateRequest) {
        UserInfoResponse user = userService.nextSignUp(googleUserPrincipal.getId(), userUpdateRequest);
        log.info("-- complete user info update : {}", googleUserPrincipal.getId());
        return ResponseEntity.ok()
                .body(DefaultResponse.of(ResponseCode.OK, UPDATE_USER_INFO, user));
    }

    @GetMapping
    public ResponseEntity<?> getUserInfo(@CurrentUser GoogleUserPrincipal googleUserPrincipal, @RequestParam("user") String userSubId) {
        log.info("-- see user info : {} => {}", googleUserPrincipal.getId(), userSubId);
        User user = userService.getUserFromSubId(userSubId);
        UserInfoResponse userInfo = UserInfoResponse.fromEntity(user);
        return ResponseEntity.ok()
                .body(DefaultResponse.of(ResponseCode.OK, GET_USER_INFO, userInfo));
    }


}
