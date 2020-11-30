package com.jyami.commitersewha.payload;

/**
 * Created by jyami on 2020/09/27
 */
public final class ResponseMessage {
    private ResponseMessage() {
    }

    public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";

    public static final String GET_CURRENT_USER_INFO = "현재 로그인한 유저 정보 조회 성공";
    public static final String GET_USER_INFO = "유저 정보 조회 성공";
    public static final String SUCCESS_USER_SIGNUP = "추가 정보 포함한 유저 회원 가입 성공";
    public static final String UPDATE_USER_INFO = "유저 개인정보 수정 성공";

    public static final String GET_POST_LIST = "커뮤니티 포스트 리스트 조회 성공";
    public static final String SUCCESS_POST_CREATE = "커뮤니티 포스트 만들기 성공";
    public static final String GET_COMMENTS_BY_POSTID = "커뮤니티 포스트에 해당하는 댓글 수 가져오기";
    public static final String CAN_NOT_UPDATED_THIS_ACCESS_USER = "현재 접근한 유저는 변경이 불가능 합니다.";
    public static final String SUCCESS_POST_UPDATE = "커뮤니티 포스트 업데이트 성공";
    public static final String SUCCESS_POST_DELETE = "커뮤니티 포스트 삭제 성공";

    public static final String SUCCESS_COMMENT_CREATE = "커뮤니티 포스트의 댓글 만들기 성공";
    public static final String SUCCESS_COMMENT_DELETE = "커뮤니티 포스트의 댓글 삭제하기 성공";

    public static final String SUCCESS_CHANGE_LIKE_STATUS = "좋아요 상태 변경하기 성공";


    public static final String GET_ALL_BADGE_LIST = "현재 모든 뱃지 리스트 조회 성공";
    public static final String GET_ALL_DEV_STACK_LIST = "현재 모든 DEV STACK 태그 리스 조회 성공";

    public static final String SCRAPING_EWHA_NOTIFICATION_SUCCESS = "이화 홈페이지 공지사항 사이트 결과 조회 성공";
    public static final String SCRAPING_EWHA_JOB_SUCCESS = "이화 홈페이지 취업 게시물 결과 조회 성공";
    public static final String SCRAPING_BAEKJOON_SUCCESS = "백준 사이트 이화여대 랭킹 조회 성공";

    public static final String RSS_CONTENTS_SUCCESS = "기술블로그 RSS 조회 성공";
    public static final String NOT_HAVE_CURRENT_GOOGLE_LOGINED_USER = "현재 세션에 로그인 되어있는 google 유저가 없습니다.";

    public static final String GET_GITHUB_USER_DETAIL_INFO = "github 기본정보 조회 성공";
    public static final String GITHUB_REST_CALL_ERROR = "github OK status 값을 받지 못했습니다.";

    public static final String SAVE_GITHUB_REPOSITORY_INFO_SUCCESS = "github repository 정보 저장 성공";
    public static final String UPDATE_GITHUB_INFO_SUCCESS = "시간에 따른 github 정보 없데이트 성공";
    public static final String FIND_COMMIT_SUCCESS = "나열된 커밋맵 찾기 성공";
    public static final String FIND_STAT_HOUR_COUNT_SUCCESS = "시간에 따른 커밋 개수 카운트 성공";
    public static final String FIND_STAT_WEEKDAY_COUNT_SUCCESS = "요일에 따른 커밋 개수 카운트 성공";

    public static final String UPDATE_COMMIT_RANK_SUCCESS = "DB에 저장된 commit을 읽고 rank update 성공";
    public static final String FIND_RANKING_WEEK_SUCCESS = "한주간의 랭킹 리스트 찾기 성공";
    public static final String FIND_RANKING_QUARTER_SUCCESS = "한 쿼터간 랭킹 리스트 찾기 성공";

}
