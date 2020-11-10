package java.base.a7datetime;

import java.time.*;
import java.time.temporal.ChronoField;

public class LocalDateAndTime {
    public static void main(String[] args) {
        //LocalDate
        LocalDate date = LocalDate.of(2018,4,1);//2018-04-01
        System.out.println("date " + date);
        int year = date.getYear();//2018
        Month month = date.getMonth();//APRIL
        int day = date.getDayOfMonth();//16
        DayOfWeek dow = date.getDayOfWeek();//MONDAY
        int len = date.lengthOfMonth();//30
        boolean leap = date.isLeapYear();//false
        System.out.println("year " + year);
        System.out.println("month " + month);
        System.out.println("day " + day);
        System.out.println("dow " + dow);
        System.out.println("len " + len);
        System.out.println("leap " + leap);
        //今天
        LocalDate today = LocalDate.now();//2019-10-01
        System.out.println("today " + today);
        //get(TemporalField field)获取时间信息，TemporalField接口定义了如何访问temporal对象某个字段的值，ChronoField枚举实现了接口
        int yearFromField = today.get(ChronoField.YEAR);//2019
        int monthFromField = today.get(ChronoField.MONTH_OF_YEAR);//10
        int dayFromField = today.get(ChronoField.DAY_OF_MONTH);//1
        System.out.println("yearFromField " + yearFromField);
        System.out.println("monthFromField " + monthFromField);
        System.out.println("dayFromField " + dayFromField);

        //LocalTime
        LocalTime time = LocalTime.of(13,45,20);//13:45:20
        System.out.println("time " + time);
        int hour = time.getHour();//13
        int minute = time.getMinute();//45
        int second = time.getSecond();//20
        System.out.println("hour " + hour);
        System.out.println("minute " + minute);
        System.out.println("second " + second);

        //解析字符串构建实例
        LocalDate date1 = LocalDate.parse("2019-06-06");
        LocalTime time1 = LocalTime.parse("14:46:21");
        System.out.println("date1 " + date1);
        System.out.println("time1 " + time1);

        //DateFormat和DateFormatter

        //合并日期和时间
        LocalDateTime dt1 = LocalDateTime.of(2014,Month.MARCH,18,13,45,20);//2014-03-18T13:45:20
        LocalDateTime dt2 = LocalDateTime.of(date,time);//2018-04-01T13:45:20
        LocalDateTime dt3 = date.atTime(15,47,22);//2018-04-01T15:47:22
        LocalDateTime dt4 = date.atTime(time);//2018-04-01T13:45:20
        LocalDateTime dt5 = time.atDate(date);//2018-04-01T13:45:20
        System.out.println("dt1 " + dt1);
        System.out.println("dt2 " + dt2);
        System.out.println("dt3 " + dt3);
        System.out.println("dt4 " + dt4);
        System.out.println("dt5 " + dt5);
        //由复合类拆回LocalDate和LocalTime
        LocalDate date2 = dt1.toLocalDate();
        LocalTime time2 = dt1.toLocalTime();

    }
}
