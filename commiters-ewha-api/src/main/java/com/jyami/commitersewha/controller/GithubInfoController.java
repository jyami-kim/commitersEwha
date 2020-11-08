package com.jyami.commitersewha.controller;

import com.jyami.commitersewha.payload.DefaultResponse;
import com.jyami.commitersewha.payload.ResponseCode;
import com.jyami.commitersewha.payload.request.SearchRequest;
import com.jyami.commitersewha.payload.response.PostResponse;
import com.jyami.commitersewha.security.CurrentUser;
import com.jyami.commitersewha.security.GoogleUserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jyami.commitersewha.payload.ResponseMessage.GET_POST_LIST;

/**
 * Created by jyami on 2020/11/08
 */
@RequestMapping("api/github")
@RestController
@RequiredArgsConstructor
@Slf4j
public class GithubInfoController {

    @GetMapping("me")
    public ResponseEntity<?> saveNewGithubInfo(@CurrentUser GoogleUserPrincipal googleUserPrincipal) {
        log.info("---saveNewGithubInfo : parameter = {}", googleUserPrincipal.getId());
        return null;
    }

//    @GetMapping("newInformation")
//    public ResponseEntity<?> saveNewGithubInfo(@CurrentUser GoogleUserPrincipal googleUserPrincipal) {
//        log.info("---saveNewGithubInfo : parameter = {}", googleUserPrincipal.getId());
//        return null;
//    }
}
