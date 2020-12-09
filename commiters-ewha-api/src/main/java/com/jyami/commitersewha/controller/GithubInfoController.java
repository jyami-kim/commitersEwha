package com.jyami.commitersewha.controller;

import com.jyami.commitersewha.domain.commitInfo.dto.CommitMap;
import com.jyami.commitersewha.domain.commitInfo.dto.HourStat;
import com.jyami.commitersewha.domain.commitInfo.dto.WeekDayStat;
import com.jyami.commitersewha.payload.DefaultResponse;
import com.jyami.commitersewha.payload.ResponseCode;
import com.jyami.commitersewha.payload.response.GithubCommitInfoResponse;
import com.jyami.commitersewha.payload.response.GithubDetailInfoResponse;
import com.jyami.commitersewha.security.CurrentUser;
import com.jyami.commitersewha.security.GoogleUserPrincipal;
import com.jyami.commitersewha.service.GithubInfoService;
import com.jyami.commitersewha.service.UserRankService;
import com.jyami.commitersewha.util.TimeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    private final UserRankService userRankService;

    @GetMapping("me")
    public ResponseEntity<?> getGihtubCurrentUSerInfo(@CurrentUser GoogleUserPrincipal googleUserPrincipal) {
        log.info("---getUserGithubInfo : parameter = {}", googleUserPrincipal.getId());
        GithubDetailInfoResponse detailInfo = githubInfoService.getGithubInfoFromUserId(googleUserPrincipal.getId());
        return ResponseEntity.ok()
                .body(DefaultResponse.of(ResponseCode.OK, GET_GITHUB_USER_DETAIL_INFO, detailInfo));
    }

    @GetMapping("{subId}")
    public ResponseEntity<?> getGithubUserInfo(@CurrentUser GoogleUserPrincipal googleUserPrincipal,
                                               @PathVariable String subId) {
        log.info("---getUserGithubInfo : parameter = {} => {}", googleUserPrincipal.getId(), subId);
        GithubDetailInfoResponse detailInfo = githubInfoService.getGithubInfoFromSubId(subId);
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

    @PostMapping("update/{startDate}") // YYYY-MM-dd
    public ResponseEntity<?> updateGithubRepositoryInfo(@CurrentUser GoogleUserPrincipal googleUserPrincipal, @PathVariable String startDate) {
        log.info("---updateGithubInfo : userId = {} date = {}", googleUserPrincipal.getId(), startDate);
        HashMap<String, List<GithubCommitInfoResponse>> updateDataInfo =
                githubInfoService.updateDateInfo(TimeUtils.getStartDate(startDate), googleUserPrincipal.getId());
        userRankService.saveRank(googleUserPrincipal.getId());
        return ResponseEntity.ok()
                .body(DefaultResponse.of(ResponseCode.OK, UPDATE_GITHUB_INFO_SUCCESS, updateDataInfo));
    }

    @GetMapping("commitMap/{subId}")
    public ResponseEntity<?> getGitCommitMap(@CurrentUser GoogleUserPrincipal googleUserPrincipal, @PathVariable String subId) {
        log.info("---commitMap : search {} => {}", googleUserPrincipal.getId(), subId);
        List<CommitMap> commitMapCount = githubInfoService.findCommitMapCount(subId);
        return ResponseEntity.ok()
                .body(DefaultResponse.of(ResponseCode.OK, FIND_COMMIT_SUCCESS, commitMapCount));
    }

    @GetMapping("stat/time/{subId}")
    public ResponseEntity<?> getGitCommitTimeStat(@CurrentUser GoogleUserPrincipal googleUserPrincipal, @PathVariable String subId) {
        log.info("---commitStatHourTime : search {} => {}", googleUserPrincipal.getId(), subId);
        List<HourStat> commitTimeCount = githubInfoService.findCommitStatHourCount(subId);
        return ResponseEntity.ok()
                .body(DefaultResponse.of(ResponseCode.OK, FIND_STAT_HOUR_COUNT_SUCCESS, commitTimeCount));
    }

    @GetMapping("stat/weekday/{subId}") // 0부터 monday
    public ResponseEntity<?> getGitCommitWeekdayStat(@CurrentUser GoogleUserPrincipal googleUserPrincipal, @PathVariable String subId) {
        log.info("---commitStatWeekdayTime : search {} => {}", googleUserPrincipal.getId(), subId);
        List<WeekDayStat> commitStatWeekDayCount = githubInfoService.findCommitStatWeekdayCount(subId);
        return ResponseEntity.ok()
                .body(DefaultResponse.of(ResponseCode.OK, FIND_STAT_WEEKDAY_COUNT_SUCCESS, commitStatWeekDayCount));
    }

}
