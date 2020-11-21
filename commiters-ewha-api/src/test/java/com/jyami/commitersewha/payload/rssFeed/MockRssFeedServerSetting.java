package com.jyami.commitersewha.payload.rssFeed;

import com.jyami.commitersewha.util.TestResourceLoader;
import org.mockserver.client.MockServerClient;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

/**
 * Created by jyami on 2020/10/08
 */
public class MockRssFeedServerSetting {
    public static final int PORT = 9000;


    public void creatBlogMockServer() {
        new MockServerClient("localhost", PORT)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/mock/kakaoenterprise/rss")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withBody(TestResourceLoader.readFile("kakao_rss.xml"))
                );

    }
}
