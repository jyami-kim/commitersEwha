package com.jyami.commitersewha.controller;

import com.jyami.commitersewha.payload.DefaultResponse;
import com.jyami.commitersewha.payload.ResponseCode;
import com.jyami.commitersewha.security.CurrentUser;
import com.jyami.commitersewha.security.GoogleUserPrincipal;
import com.jyami.commitersewha.service.UserRankService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.jyami.commitersewha.payload.ResponseMessage.SAVE_GITHUB_REPOSITORY_INFO_SUCCESS;
import static com.jyami.commitersewha.payload.ResponseMessage.UPDATE_COMMIT_RANK_SUCCESS;

/**
 * Created by jyami on 2020/11/28
 */
@RequestMapping("api/rank")
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserRankController {

    private final UserRankService userRankService;

    @PostMapping
    public ResponseEntity<?> saveRank(@CurrentUser GoogleUserPrincipal googleUserPrincipal) {
        log.info("---saveRankAsQuarter : parameter = {}", googleUserPrincipal.getId());
        userRankService.saveRank(googleUserPrincipal.getId());
        return ResponseEntity.ok()
                .body(DefaultResponse.of(ResponseCode.OK, UPDATE_COMMIT_RANK_SUCCESS));
    }

    @GetMapping("{authorId}")
    public ResponseEntity<?> getRankDetailAsQuarterAndWeek(@CurrentUser GoogleUserPrincipal googleUserPrincipal,
                                                           @PathVariable String authorId) {
        log.info("---saveRankAsQuarter : parameter = {} => {}", googleUserPrincipal.getId(), authorId);
        userRankService.getUserRankRank(authorId);
        return ResponseEntity.ok()
                .body(DefaultResponse.of(ResponseCode.OK, UPDATE_COMMIT_RANK_SUCCESS));
    }

}
