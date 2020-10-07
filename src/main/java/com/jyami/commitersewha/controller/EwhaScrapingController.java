package com.jyami.commitersewha.controller;

import com.jyami.commitersewha.payload.DefaultResponse;
import com.jyami.commitersewha.payload.ewhaScrap.JobResult;
import com.jyami.commitersewha.payload.ewhaScrap.NotificationResult;
import com.jyami.commitersewha.service.ScrapingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jyami.commitersewha.payload.ResponseMessage.*;

/**
 * Created by jyami on 2020/10/03
 */
@RequestMapping("api/scrap")
@RestController
@RequiredArgsConstructor
@Slf4j
public class EwhaScrapingController {

    private final ScrapingService scrapingService;

    @GetMapping("ewha/job")
    public ResponseEntity<?> getEwhaJobInfo() {
        JobResult jobResult = scrapingService.getJobResult();
        return ResponseEntity.ok().body(DefaultResponse.of(HttpStatus.OK, SCRAPING_EWHA_JOB_SUCCESS, jobResult));
    }


    @GetMapping("ewha/notification")
    public ResponseEntity<?> getEwhaNotificationInfo() {
        NotificationResult notificationResult = scrapingService.getNotificationResult();
        return ResponseEntity.ok().body(DefaultResponse.of(HttpStatus.OK, SCRAPING_EWHA_NOTIFICATION_SUCCESS, notificationResult));
    }

}
