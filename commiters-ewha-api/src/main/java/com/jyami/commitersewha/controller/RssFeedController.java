package com.jyami.commitersewha.controller;

import com.jyami.commitersewha.payload.DefaultResponse;
import com.jyami.commitersewha.payload.ResponseCode;
import com.jyami.commitersewha.payload.response.RssFeedResponse;
import com.jyami.commitersewha.payload.rssFeed.RssFeedContents;
import com.jyami.commitersewha.service.RssFeedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.jyami.commitersewha.payload.ResponseMessage.RSS_CONTENTS_SUCCESS;

/**
 * Created by jyami on 2020/10/08
 */
@RequestMapping("api/rss")
@RestController
@RequiredArgsConstructor
@Slf4j
public class RssFeedController {

    //TODO : redis - 실제 페이지 찌르는 것 최소화하기

    private final RssFeedService rssFeedService;

    @GetMapping("")
    public ResponseEntity<?> getAllPostWithPage() {
        RssFeedResponse allRssFeedResult = rssFeedService.getAllRssFeedResult();
        return ResponseEntity.ok()
                .body(DefaultResponse.of(ResponseCode.OK, RSS_CONTENTS_SUCCESS, allRssFeedResult));
    }

}
