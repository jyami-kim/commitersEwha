package com.jyami.commitersewha.payload;

/**
 * Created by jyami on 2020/10/25
 */
public class ResponseCode {
    private ResponseCode() {
    }
    public static final int INTERNAL_SERVER_ERROR = -500;
    public static final int OK = -200;
    public static final int DOMAIN_ERROR = -450;
}
