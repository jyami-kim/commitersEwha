package com.jyami.commitersewha.service;

import com.jyami.commitersewha.config.ScrapingProperties;
import com.jyami.commitersewha.exception.ScrapingException;
import com.jyami.commitersewha.payload.scrap.BaekjoonResult;
import com.jyami.commitersewha.payload.scrap.JobResult;
import com.jyami.commitersewha.payload.scrap.NotificationResult;
import com.jyami.commitersewha.payload.scrap.ScrapUtils;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

/**
 * Created by jyami on 2020/10/07
 */
@Service
@RequiredArgsConstructor
public class ScrapingService {

    private final ScrapingProperties properties;

    public JobResult getJobResult() {
        Document crawlingResult = ScrapUtils.getCrawlingResult(properties.getEwha().getScrapJobUrl());
        return JobResult.ewhaJobScraping(crawlingResult, properties.getEwha().getBaseUrl());
    }

    public NotificationResult getNotificationResult() {
        Document crawlingResult = ScrapUtils.getCrawlingResult(properties.getEwha().getScrapNotification());
        return NotificationResult.ewhaNotificationScraping(crawlingResult, properties.getEwha().getBaseUrl());
    }

    public BaekjoonResult getBaekjoonResult() {
        Document document = ScrapUtils.getCrawlingResult(properties.getBaekjoon().getScrapUrl());
        Element crawlingResult = document.select("tbody tr").stream()
                .filter(x -> "이화여자대학교".equals(x.select("td:nth-child(2)").text()))
                .findFirst()
                .orElseThrow(() -> new ScrapingException("이화여자대학교가 학교랭킹 100위권 밖입니다"));
        return BaekjoonResult.of(crawlingResult, properties.getBaekjoon().getBaseUrl());
    }


}
