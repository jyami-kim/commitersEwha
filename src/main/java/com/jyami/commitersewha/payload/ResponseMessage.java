package com.jyami.commitersewha.payload;

/**
 * Created by jyami on 2020/09/27
 */
public class ResponseMessage {
    private ResponseMessage() {
    }

    public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";

    public static final String GET_CURRENT_USER_INFO = "현재 로그인한 유저 정보 조회 성공";
    public static final String SUCESS_USER_SIGNUP = "추가 정보 포함한 유저 회원 가입 성공";

    public static final String GET_ALL_BADGE_LIST = "현재 모든 뱃지 리스트 조회 성공";
    public static final String GET_ALL_DEV_STACK_LIST = "현재 모든 DEV STACK 태그 리스 조회 성공";

}
