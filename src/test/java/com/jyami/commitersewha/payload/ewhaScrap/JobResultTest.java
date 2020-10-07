package com.jyami.commitersewha.payload.ewhaScrap;

import com.jyami.commitersewha.util.MockScrapingServerSetting;
import com.jyami.commitersewha.util.ScrapUtils;
import org.jsoup.nodes.Document;
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
class JobResultTest {

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
        Document crawlingResult = ScrapUtils.getCrawlingResult("http://localhost:9000/test/ewha/job");
        JobResult jobResult = JobResult.ewhaJobScraping(crawlingResult);
        assertThat(jobResult.getJobList().size()).isEqualTo(15);
    }

    @AfterEach
    void tearDown() {
        mockServer.stop();
        System.out.println("mock server stop");
    }

}