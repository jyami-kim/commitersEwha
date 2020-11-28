package com.jyami.commitersewha.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

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
    @DisplayName("지정한 날짜의 분기가 맞는지 확인")
    void name() {
        for(int i = 1; i<=12; i++){
            LocalDate localDate = LocalDate.of(2020, i, 1);
            LocalDateTime thisQuarterStartTime = TimeUtils.getThisQuarterStartTime(localDate);
            System.out.println(thisQuarterStartTime);
        }
    }

    @Test
    @DisplayName("지정한 날짜의 주에서 시작하는 시간 확인")
    void name2() {
        LocalDate now = LocalDate.of(2020, Month.NOVEMBER, 29);
        int minusValue = now.getDayOfWeek().getValue();
        LocalDate startWeek = now.minusDays(minusValue);
        System.out.println(startWeek);
    }
}