package com.jyami.commitersewha.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by jyami on 2020/11/12
 */
public class TimeUtils {

    public static LocalDateTime getStartDate(LocalDate localDate) {
        return localDate.atStartOfDay();
    }

    public static LocalDateTime getStartDate(String localDate) {
        LocalDate date = LocalDate.parse(localDate, DateTimeFormatter.ISO_DATE);
        return LocalDateTime.of(date, LocalTime.MIN);
    }

    public static LocalDateTime getEndDate(LocalDate localDate) {
        return localDate.atTime(23, 59, 59);
    }

    public static LocalDateTime getTodayEndTime() {
        return getEndDate(LocalDate.now());
    }

    public static LocalDateTime getThisYearStartTime() {
        return getStartDate(LocalDate.now().minusWeeks(52));
    }

    public static LocalDateTime getThisQuarterStartTime(LocalDate localDate) {
        int quarterIndex = (localDate.getMonthValue() - 1) / 3;
        int quarterStartMonth = quarterIndex * 3 + 1;
        LocalDate date = LocalDate.of(localDate.getYear(), quarterStartMonth, 1);
        return getStartDate(date);
    }
}
