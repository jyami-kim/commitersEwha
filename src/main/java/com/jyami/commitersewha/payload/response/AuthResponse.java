package com.jyami.commitersewha.payload.response;

import lombok.Getter;

/**
 * Created by jyami on 2020/09/22
 */
@Getter
public class AuthResponse {

    private String accessToken;
    private String tokenType = "Bearer";

    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

}
