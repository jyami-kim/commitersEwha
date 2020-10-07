package com.jyami.commitersewha.util;

import org.apache.commons.io.IOUtils;
import org.mockserver.client.MockServerClient;

import java.io.IOException;
import java.io.InputStream;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

/**
 * Created by jyami on 2020/10/07
 */
public class MockScrapingServerSetting {
    public static final int PORT = 9000;

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
                                .withBody(readHtmlFile("ewhaJob.html"))
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
                                .withBody(readHtmlFile("ewhaNotification.html"))
                );
    }

    private byte[] readHtmlFile(String filePath) {
        InputStream resourceAsStream = getClass().getClassLoader()
                .getResourceAsStream(filePath);
        try {
            assert resourceAsStream != null;
            return IOUtils.toByteArray(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("file IO 실패");
        }
    }
}
