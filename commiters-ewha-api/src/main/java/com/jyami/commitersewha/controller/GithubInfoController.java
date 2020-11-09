package com.jyami.commitersewha.controller;

import com.jyami.commitersewha.domain.githubInfo.GithubInfo;
import com.jyami.commitersewha.payload.DefaultResponse;
import com.jyami.commitersewha.payload.ResponseCode;
import com.jyami.commitersewha.payload.request.SearchRequest;
import com.jyami.commitersewha.payload.response.GithubDetailInfoResponse;
import com.jyami.commitersewha.payload.response.PostResponse;
import com.jyami.commitersewha.security.CurrentUser;
import com.jyami.commitersewha.security.GoogleUserPrincipal;
import com.jyami.commitersewha.service.GithubInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.jyami.commitersewha.payload.ResponseMessage.*;

/**
 * Created by jyami on 2020/11/08
 */
@RequestMapping("api/github")
@RestController
@RequiredArgsConstructor
@Slf4j
public class GithubInfoController {

    private final GithubInfoService githubInfoService;

    @GetMapping("{authorId}")
    public ResponseEntity<?> getGithubUserInfo(@CurrentUser GoogleUserPrincipal googleUserPrincipal,
                                               @PathVariable String authorId) {
        log.info("---getUserGithubInfo : parameter = {} => {}", googleUserPrincipal.getId(), authorId);
        GithubDetailInfoResponse detailInfo = githubInfoService.getGithubInfoFromUserId(authorId);
        return ResponseEntity.ok()
                .body(DefaultResponse.of(ResponseCode.OK, GET_GITHUB_USER_DETAIL_INFO, detailInfo));
    }

    @PostMapping("newCommiters")
    public ResponseEntity<?> saveNewGithubRepoInfo(@CurrentUser GoogleUserPrincipal googleUserPrincipal) {
        log.info("---saveNewGithubInfo : parameter = {}", googleUserPrincipal.getId());
        githubInfoService.saveNewCommitersInfo(googleUserPrincipal.getId());
        return ResponseEntity.ok()
                .body(DefaultResponse.of(ResponseCode.OK, SAVE_GITHUB_REPOSITORY_INFO_SUCCESS));
    }

//    @GetMapping("newInformation")
//    public ResponseEntity<?> saveNewGithubInfo(@CurrentUser GoogleUserPrincipal googleUserPrincipal) {
//        log.info("---saveNewGithubInfo : parameter = {}", googleUserPrincipal.getId());
//        return null;
//    }
}
