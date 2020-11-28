package com.jyami.commitersewha.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    void name() {
        for(int i = 1; i<=12; i++){
            LocalDate localDate = LocalDate.of(2020, i, 1);
            LocalDateTime thisQuarterStartTime = TimeUtils.getThisQuarterStartTime(localDate);
            System.out.println(thisQuarterStartTime);
        }
    }
}