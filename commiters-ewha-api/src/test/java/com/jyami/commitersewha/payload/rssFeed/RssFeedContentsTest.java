package com.jyami.commitersewha.payload.rssFeed;

import com.jyami.commitersewha.util.TestResourceLoader;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

/**
 * Created by jyami on 2020/11/21
 */
class RssFeedContentsTest {

    @Test
    void parsingDescription() {
        byte[] bytes = TestResourceLoader.readFile("kakao_rss_description.html");
        String html = new String(bytes, StandardCharsets.UTF_8);
        String result = RssFeedContents.parsingAndLimitText(html);
        System.out.println(result);
    }
}