package com.jyami.commitersewha.payload.scrap;

import com.jyami.commitersewha.config.ScrapingProperties;
import com.jyami.commitersewha.exception.ScrapingException;
import com.jyami.commitersewha.service.ScrapingService;
import com.jyami.commitersewha.util.MockScrapingServerSetting;
import com.jyami.commitersewha.util.ScrapUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockserver.integration.ClientAndServer;

import static com.jyami.commitersewha.util.MockScrapingServerSetting.PORT;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by jyami on 2020/10/07
 */
class BaekjoonResultTest {

    private static ClientAndServer mockServer;
    private MockScrapingServerSetting mockScrapingServerSetting;

    @BeforeEach
    void setUp() {
        mockServer = ClientAndServer.startClientAndServer(PORT);
        mockScrapingServerSetting = new MockScrapingServerSetting();
        System.out.println("mock server start");
    }

    @Test
    @DisplayName("백준 사이트 랭킹 크롤링")
    void scrapingRank() {
        mockScrapingServerSetting.creatRankMockServer();
        Document document = ScrapUtils.getCrawlingResult("http://localhost:9000/test/baekjoon/rank");

        Element crawlingResult = document.select("tbody tr").stream()
                .filter(x -> "이화여자대학교".equals(x.select("td:nth-child(2)").text()))
                .findFirst().get();
        BaekjoonResult baekjoonResult = BaekjoonResult.of(crawlingResult, "http://ewha.com/");

        assertThat(baekjoonResult.getUniversityName()).isEqualTo("이화여자대학교");
    }

    @AfterEach
    void tearDown() {
        mockServer.stop();
        System.out.println("mock server stop");
    }


}