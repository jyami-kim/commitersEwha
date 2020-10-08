package com.jyami.commitersewha.util;

import org.mockserver.client.MockServerClient;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

/**
 * Created by jyami on 2020/10/07
 */
public class MockScrapingServerSetting {
    public static final int PORT = 9000;

    public void creatRankMockServer() {
        new MockServerClient("localhost", PORT)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/test/baekjoon/rank")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withBody(TestResourceLoader.readFile("baekjoonRank.html"))
                );

    }

    public void createJobMockServer() {
        new MockServerClient("localhost", PORT)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/test/ewha/job")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withBody(TestResourceLoader.readFile("ewhaJob.html"))
                );

    }

    public void createNotificationMockServer() {
        new MockServerClient("localhost", PORT)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/test/ewha/notification")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withBody(TestResourceLoader.readFile("ewhaNotification.html"))
                );
    }


}
