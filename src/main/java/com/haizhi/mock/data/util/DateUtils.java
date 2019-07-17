package com.haizhi.mock.data.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期工具类
 *
 * @author xuguoqin
 * @date 2019/7/17 10:11 AM
 */
public class DateUtils {

    public static final DateTimeFormatter DEFAULT_LOCAL_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String format(LocalDate localDate) {
        return localDate.format(DEFAULT_LOCAL_DATE_FORMATTER);
    }

    public static String format(LocalDate localDate, DateTimeFormatter formatter) {
        return localDate.format(formatter);
    }

    public static String format(LocalDateTime localDateTime, DateTimeFormatter formatter) {
        return localDateTime.format(formatter);
    }

    public static void main(String[] args) {
        System.out.println(LocalDate.now().toString());
    }
}
