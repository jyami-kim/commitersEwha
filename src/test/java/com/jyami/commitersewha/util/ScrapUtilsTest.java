package com.jyami.commitersewha.util;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockserver.integration.ClientAndServer;

import static com.jyami.commitersewha.util.MockScrapingServerSetting.PORT;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

/**
 * Created by jyami on 2020/10/07
 */
class ScrapUtilsTest {

    private static ClientAndServer mockServer;
    private MockScrapingServerSetting mockScrapingServerSetting;

    @BeforeEach
    void setUp() {
        mockServer = ClientAndServer.startClientAndServer(PORT);
        mockScrapingServerSetting = new MockScrapingServerSetting();
        System.out.println("mock server start");
    }

    @Test
    @DisplayName("이화여대 취업페이지 크롤링")
    void scrapingEwhaJob() {
        mockScrapingServerSetting.createJobMockServer();
        Document crawlingResult = ScrapUtils.getCrawlingResult("http://localhost:9000/test/ewha/jobs");

    }

    @Test
    @DisplayName("이화여대 공지페이지 크롤링")
    void scrapingEwhaNotification() {
        mockScrapingServerSetting.createNotificationMockServer();
        Document crawlingResult = ScrapUtils.getCrawlingResult("http://localhost:9000/test/ewha/jobs");
    }

    @AfterEach
    void tearDown() {
        mockServer.stop();
        System.out.println("mock server stop");
    }



}