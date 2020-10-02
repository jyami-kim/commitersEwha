package com.jyami.commitersewha.payload.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by jyami on 2020/10/02
 */
class PostRequestTest {

    @Test
    @DisplayName("아무 값이 안들어 갔을 때에도 잘 변환된다.")
    void name() {
        PostRequest postRequest = new PostRequest();
        assertThat(postRequest.joiningHashTag()).isEqualTo("");
    }

}