package java.base.a7datetime;

import java.time.*;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.TimeZone;

public class VariableMethod {
    public static void main(String[] args) {
        LocalDate ld1 = LocalDate.of(2014,3,18);//2014-03-18
        LocalDate ld2 = ld1.withYear(2018);//2018-03-18
        LocalDate ld3 = ld2.withDayOfMonth(25);//2018-03-25
        LocalDate ld4 = ld3.with(ChronoField.MONTH_OF_YEAR,9);//2018-09-25
        System.out.println("ld1 " + ld1);
        System.out.println("ld2 " + ld2);
        System.out.println("ld3 " + ld3);
        System.out.println("ld4 " + ld4);
        //以声明的方式操纵LocalDate对象
        LocalDate date1 = LocalDate.of(2014,3,18);//2014-03-18
        LocalDate date2 = date1.plusWeeks(1);//2014-03-25
        LocalDate date3 = date2.minusYears(3);//2011-03-25
        LocalDate date4 = date3.plus(6, ChronoUnit.MONTHS);//2011-09-25
        System.out.println("date1 " + date1);
        System.out.println("date2 " + date2);
        System.out.println("date3 " + date3);
        System.out.println("date4 " + date4);

        //打印输出及解析日期—时间对象
        String s1 = date1.format(DateTimeFormatter.BASIC_ISO_DATE);//20140318
        String s2 = date1.format(DateTimeFormatter.ISO_LOCAL_DATE);//2014-03-18
        System.out.println("s1 " + s1);
        System.out.println("s2 " + s2);

        LocalDate lds1 = LocalDate.parse("20140318",DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate lds2 = LocalDate.parse("2014-03-18",DateTimeFormatter.ISO_LOCAL_DATE);
        //指定模式创建DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = date1.format(formatter);//18/03/2014
        LocalDate fromFormattedDate = LocalDate.parse(formattedDate,formatter);
        System.out.println("formattedDate " + formattedDate);

        //本地化DateTimeFormatter
        DateTimeFormatter formatterCN = DateTimeFormatter.ofPattern("yyyy年 MMMM dd日", Locale.CHINA);
        String formattedCNDate = date1.format(formatterCN);//2014年 三月 18日
        LocalDate fromFormattedCNDate = LocalDate.parse(formattedCNDate,formatterCN);
        System.out.println("formattedCNDate " + formattedCNDate);

        //自定义DateTimeFormatter
        DateTimeFormatter CNFormatter = new DateTimeFormatterBuilder()
                .appendText(ChronoField.YEAR)
                .appendLiteral("年 ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral("日 ")
                .toFormatter(Locale.CHINA);
        String stringCNFormatter = date1.format(CNFormatter);//2014年 三月 18日
        System.out.println("stringCNFormatter " + stringCNFormatter);


        //处理不同的时区和历法

        //地区Id标识：{区域}/{城市}
        ZoneId romeZone = ZoneId.of("Europe/Rome");

        //通过方法toZoneId将旧的时区对象转换为ZoneId
        ZoneId zoneId = TimeZone.getDefault().toZoneId();

        //有了ZoneId就可以和日期—时间对象结合成ZonedDateTime实例，代表了对应时区的时间点
        ZonedDateTime zdt1 = date1.atStartOfDay(romeZone);//2014-03-18T00:00+01:00[Europe/Rome]
        LocalDateTime dateTime = date1.atTime(18,13,45);
        ZonedDateTime zdt2 = dateTime.atZone(romeZone);//2014-03-18T18:13:45+01:00[Europe/Rome]
        Instant instant = Instant.now();
        ZonedDateTime zdt3 = instant.atZone(romeZone);//2019-10-01T03:37:34.201+01:00[Europe/Rome]
        System.out.println("zdt1 " + zdt1);
        System.out.println("zdt2 " + zdt2);
        System.out.println("zdt3 " + zdt3);

        //通过ZoneId将Instant转为LocalDateTime
        LocalDateTime timeFromInstant = LocalDateTime.ofInstant(instant,romeZone);//2019-12-04T03:52:42.845
        System.out.println("timeFromInstant " + timeFromInstant);

        //表示此时间和伦敦格林尼治子午线时间的差异
        ZoneOffset newYorkOffSet = ZoneOffset.of("-05:00");
        //但上述方式并未考虑任何夏令时的影响，因此不推荐。

        //ZoneOffset是ZoneId的子类，可以通过其创建OffsetDateTime
        OffsetDateTime dateTimeInNewYork = OffsetDateTime.of(dateTime,newYorkOffSet);//2014-03-18T18:13:45-05:00
        System.out.println("dateTimeInNewYork " + dateTimeInNewYork);

        //可以直接生成Instant
        Instant fromOffsetLocalDateTime = dateTimeInNewYork.toInstant();
        //LocalDateTime则需要ZoneOffset参数才能生成Instant
        Instant fromLocalDateTime = dateTime.toInstant(newYorkOffSet);

        //其他日历系统

        //根据LocalDate（Temporal对象）创建实例
        JapaneseDate japaneseDate = JapaneseDate.from(date1);

        //可以为某个Locale显式的创建日历系统
        Chronology japaneseChronology = Chronology.ofLocale(Locale.JAPAN);
        ChronoLocalDate now = japaneseChronology.dateNow();//2019-10-01
        System.out.println("now " + now);

    }
}
