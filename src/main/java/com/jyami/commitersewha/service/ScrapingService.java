package com.jyami.commitersewha.service;

import com.jyami.commitersewha.config.EwhaProperties;
import com.jyami.commitersewha.payload.ewhaScrap.JobResult;
import com.jyami.commitersewha.payload.ewhaScrap.NotificationResult;
import com.jyami.commitersewha.util.ScrapUtils;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

/**
 * Created by jyami on 2020/10/07
 */
@Service
@RequiredArgsConstructor
public class ScrapingService {

    private final EwhaProperties properties;

    public JobResult getJobResult() {
        Document crawlingResult = ScrapUtils.getCrawlingResult(properties.getScrapJobUrl());
        return JobResult.ewhaJobScraping(crawlingResult, properties.getBaseUrl());
    }

    public NotificationResult getNotificationResult() {
        Document crawlingResult = ScrapUtils.getCrawlingResult(properties.getScrapNotification());
        return NotificationResult.ewhaNotificationScraping(crawlingResult, properties.getBaseUrl());
    }
}
