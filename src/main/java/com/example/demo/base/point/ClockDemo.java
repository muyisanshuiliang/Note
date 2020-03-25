package com.example.demo.base.point;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.zone.ZoneRules;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

/**
 * @Author yl
 * @Date 2020/3/25 11:09
 * @description:
 */
public class ClockDemo {
    public static void main(String[] args) {
        Clock clock = Clock.systemUTC();
        /*获取时间戳*/
        System.out.println(clock.millis());
        clock = Clock.systemDefaultZone();
        System.out.println(clock.millis());
        /*获取时区*/
        ZoneId zone = clock.getZone();
        System.out.println(zone.toString());
        String id = zone.getId();
        System.out.println(id);
        Instant instant = clock.instant();
        Date date = Date.from(instant);
        System.out.println(date);
        testTimezones();
        System.out.println("==========test LocalTime==========");
        testLocalTime();
        System.out.println("==========test LocalDate==========");
        testLocalDate();
        System.out.println("==========test LocalDateTime==========");
        testLocalDateTime();
    }

    private static void testTimezones() {
        /*获取默认时区*/
        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println(zoneId);
        /*获取所有的可用时区*/
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        System.out.println(availableZoneIds);

        // 获取时区规则 ZoneRules[currentStandardOffset=+08:00]
        ZoneRules rules = zoneId.getRules();
        System.out.println(rules);
    }

    private static void testLocalTime() {
        ZoneId zoneId = ZoneId.systemDefault();
        ZoneId zoneId2 = ZoneId.of("Etc/GMT+8");

        // 获取指定时区的当前时间
        LocalTime afterDate = LocalTime.now(zoneId);
        System.out.println("默认时区的当前时间：" + afterDate);
        LocalTime beforeDate = LocalTime.now(zoneId2);
        System.out.println("指定时区的当前时间：" + beforeDate);

        // 判断一个本地时间是否在另一个本地时间之前
        System.out.println("afterDate.isBefore(beforeDate): " + afterDate.isBefore(beforeDate));
        System.out.println("afterDate.isAfter(beforeDate): " + afterDate.isAfter(beforeDate));

        // 获取两个本地时间小时之差
        System.out.println("ChronoUnit.HOURS.between(afterDate, beforeDate): " + ChronoUnit.HOURS.between(afterDate, beforeDate));
        System.out.println("ChronoUnit.HOURS.between(beforeDate, afterDate): " + ChronoUnit.HOURS.between(beforeDate, afterDate));

        // 获取两个本地时间分钟之差
        System.out.println("ChronoUnit.MINUTES.between(afterDate, beforeDate): " + ChronoUnit.MINUTES.between(afterDate, beforeDate));
        System.out.println("ChronoUnit.MINUTES.between(beforeDate, afterDate): " + ChronoUnit.MINUTES.between(beforeDate, afterDate));

        LocalTime localTime = LocalTime.of(23, 59, 59);
        System.out.println(localTime);       // 23:59:59

        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.GERMAN);
        LocalTime local = LocalTime.parse("13:37", formatter);
        System.out.println(local);  // 13:37
    }

    private static void testLocalDate(){
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        LocalDate yesterday = tomorrow.minusDays(2);
        System.out.println(today + "," + tomorrow + "," + yesterday);

        LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
        System.out.println(dayOfWeek);

        DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
                .withLocale(Locale.GERMAN);
        LocalDate localDate = LocalDate.parse("24.12.2014", germanFormatter);
        System.out.println(localDate);   // 2014-12-24
    }

    private static void testLocalDateTime(){
        LocalDateTime localDateTime = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        System.out.println(dayOfWeek);

        Month month = localDateTime.getMonth();
        System.out.println(month);

        long minuteOfDay = localDateTime.getLong(ChronoField.MINUTE_OF_DAY);
        System.out.println(minuteOfDay);

        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        System.out.println(date);

        // DateTimeFormatter是不可变的，所以它是线程安全的
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parsed = LocalDateTime.parse("2018-05-07 07:13:00", formatter);
        String string = formatter.format(parsed);
        System.out.println(string);
    }
}
