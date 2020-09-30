package com.jyami.commitersewha.payload;

/**
 * Created by jyami on 2020/09/27
 */
public class ResponseMessage {
    private ResponseMessage() {
    }

    public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";

    public static final String GET_CURRENT_USER_INFO = "현재 로그인한 유저 정보 조회 성공";
}
