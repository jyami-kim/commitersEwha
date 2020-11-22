package com.jyami.commitersewha.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by jyami on 2020/11/22
 */
class TimeUtilsTest {

    @Test
    void getThisYearStartTime() {
        LocalDateTime thisYearStartTime = TimeUtils.getThisYearStartTime();
        System.out.println(thisYearStartTime);
    }
}