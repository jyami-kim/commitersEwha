package com.jyami.commitersewha.payload.rssFeed;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.junit.jupiter.api.*;
import org.mockserver.integration.ClientAndServer;

import java.io.IOException;
import java.net.URL;

import static com.jyami.commitersewha.util.MockScrapingServerSetting.PORT;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by jyami on 2020/10/08
 */
class RssFeedParserTest {

    private static ClientAndServer mockServer;
    private MockRssFeedServerSetting mockRssFeedServerSetting;

    @BeforeEach
    void setUp() {
        mockServer = ClientAndServer.startClientAndServer(PORT);
        mockRssFeedServerSetting = new MockRssFeedServerSetting();
        System.out.println("mock server start");
    }

    @Test
//    @Disabled
    @DisplayName("feedParser의 기능을 학습한다.")
    void learnFeedParserFeature() throws IOException, FeedException {
        String url = "https://d2.naver.com/d2.atom";
        SyndFeed feed = new SyndFeedInput().build(new XmlReader(new URL(url)));
        System.out.println("================" + feed.getTitle() + "================");
        for (int i = 0; i < feed.getEntries().size(); i++) {
            SyndEntry entry = feed.getEntries().get(i);
            System.out.println(entry.getTitle() + " => " + entry.getLink() + ":" + entry.getPublishedDate());
        }
    }

    @Test
    @DisplayName("feedParser의 기능을 학습한다.")
    void feedParserTest() {
        mockRssFeedServerSetting.creatBlogMockServer();
        String url = "http://localhost:9000/mock/kakaoenterprise/rss";

        SyndFeed feed = RssFeedParser.parsingEntry(url);

        assertThat(feed.getTitle()).isEqualTo("카카오엔터프라이즈 기술블로그 Tech&(테크앤)");
        assertThat(feed.getEntries().size()).isEqualTo(10);
    }

    @AfterEach
    void tearDown() {
        mockServer.stop();
        System.out.println("mock server stop");
    }

}