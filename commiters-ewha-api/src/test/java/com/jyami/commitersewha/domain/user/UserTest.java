package com.jyami.commitersewha.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by jyami on 2020/10/01
 */
class UserTest {

    @Test
    @DisplayName("이메일을 이용해 검색에 필요한 서브아이디를 만들어낸다.")
    void emailToSubId() {
        String email = "mjung1798@ewhain.net";
        String subId = User.emailToSubId(email);
        assertThat(subId).isEqualTo("mjung1798");
    }


    @Test
    @DisplayName("기본설정되어있는 이름을 이용해 개인정보를 분리한다")
    void name() {
        String googleName = "김민정(엘텍공과대학 소프트웨어학부)";
        String[] userInfo = User.divideName(googleName);
        assertThat(userInfo[0]).isEqualTo("김민정");
        assertThat(userInfo[1]).isEqualTo("엘텍공과대학 소프트웨어학부");
    }
}