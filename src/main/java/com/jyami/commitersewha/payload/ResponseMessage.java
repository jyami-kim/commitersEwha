package com.jyami.commitersewha.payload;

/**
 * Created by jyami on 2020/09/27
 */
public class ResponseMessage {
    private ResponseMessage() {
    }

    public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";

    public static final String GET_CURRENT_USER_INFO = "현재 로그인한 유저 정보 조회 성공";
    public static final String GET_USER_INFO = "유저 정보 조회 성공";
    public static final String SUCCESS_USER_SIGNUP = "추가 정보 포함한 유저 회원 가입 성공";
    public static final String UPDATE_USER_INFO = "유저 개인정보 수정 성공";

    public static final String GET_POST_LIST = "커뮤니티 포스트 리스트 조회 성공";
    public static final String SUCCESS_POST_CREATE = "커뮤니티 포스트 만들기 성공";
    public static final String GET_POST_DETAIL = "커뮤니티 포스트 자세하게 조회하기 성공";
    public static final String CAN_NOT_UPDATED_THIS_ACCESS_USER = "현재 접근한 유저는 변경이 불가능 합니다.";
    public static final String SUCCESS_POST_UPDATE = "커뮤니티 포스트 업데이트 성공";
    public static final String SUCCESS_POST_DELETE = "커뮤니티 포스트 삭제 성공";

    public static final String GET_ALL_BADGE_LIST = "현재 모든 뱃지 리스트 조회 성공";
    public static final String GET_ALL_DEV_STACK_LIST = "현재 모든 DEV STACK 태그 리스 조회 성공";

}