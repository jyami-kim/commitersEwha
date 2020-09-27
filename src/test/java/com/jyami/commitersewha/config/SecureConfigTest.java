package com.jyami.commitersewha.config;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;

/**
 * Created by jyami on 2020/09/27
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(properties = "spring.config.location=classpath:/application-oauth2.yaml")
public class SecureConfigTest {


    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Test
    @DisplayName("클라이언트에서 로그인 없이 api를 호출하려 하면 403이 뜬다.")
    public void clientOAuth() throws Exception {
        given()
                .when()
                .redirects().follow(false) // 리다이렉트 방지
                .get("/api/user/me")
                .then()
                .statusCode(403);
    }

}
