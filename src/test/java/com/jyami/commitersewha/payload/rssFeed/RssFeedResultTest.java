package com.jyami.commitersewha.payload.rssFeed;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockserver.integration.ClientAndServer;

import static com.jyami.commitersewha.util.MockScrapingServerSetting.PORT;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by jyami on 2020/10/08
 */
class RssFeedResultTest {

    private static ClientAndServer mockServer;
    private MockRssFeedServerSetting mockRssFeedServerSetting;

    @BeforeEach
    void setUp() {
        mockServer = ClientAndServer.startClientAndServer(PORT);
        mockRssFeedServerSetting = new MockRssFeedServerSetting();
        System.out.println("mock server start");
    }

    @Test
    @DisplayName("feedParser이후 데이터 바인딩을 완료한다.")
    void feedParserTest() {
        mockRssFeedServerSetting.creatBlogMockServer();
        String url = "http://localhost:9000/mock/kakaoenterprise/rss";

        RssFeedResult parsing = RssFeedResult.parsing(RssFeedParser.parsingEntry(url));

        assertThat(parsing.getBlogName()).isEqualTo("카카오엔터프라이즈 기술블로그 Tech&(테크앤)");
        assertThat(parsing.getFeedContents().size()).isEqualTo(10);
    }

    @AfterEach
    void tearDown() {
        mockServer.stop();
        System.out.println("mock server stop");
    }


}