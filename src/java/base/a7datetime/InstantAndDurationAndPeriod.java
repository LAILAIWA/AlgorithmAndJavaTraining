package java.base.a7datetime;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class InstantAndDurationAndPeriod {
    public static void main(String[] args) {

        //机器的日期和时间格式
        Instant i1 = Instant.ofEpochSecond(3);
        Instant i2 = Instant.ofEpochSecond(3,0);
        Instant i3 = Instant.ofEpochSecond(2,1_000_000_000);
        Instant i4 = Instant.ofEpochSecond(4,-1_000_000_000);
        System.out.println("i1 " + i1);
        System.out.println("i2 " + i2);
        System.out.println("i3 " + i3);
        System.out.println("i4 " + i4);
//        int day = Instant.now().get(ChronoField.DAY_OF_MONTH);//java.time.temporal.UnsupportedTemporalTypeException: Unsupported field: DayOfMonth

        //定义Duration或Period
        LocalDate date = LocalDate.of(2018,4,1);//2018-04-01
        LocalTime time1 = LocalTime.parse("14:46:21");
        LocalTime time2 = LocalTime.parse("15:46:21");
        LocalDateTime dt1 = time1.atDate(date);//2018-04-01T15:47:22
        LocalDateTime dt2 = time2.atDate(date);//2018-04-01T15:47:22
        Instant it1 = Instant.ofEpochSecond(3);
        Instant it2 = Instant.ofEpochSecond(4);
        Duration d1 = Duration.between(time1,time2);
        Duration d2 = Duration.between(dt1,dt2);
        Duration d3 = Duration.between(it1,it2);
        System.out.println("d1 " + d1.getSeconds());
        System.out.println("d2 " + d2.getSeconds());
        System.out.println("d3 " + d3.getSeconds());

        Duration threeMinutes = Duration.ofMinutes(3);
        Duration threeMinutes1 = Duration.of(3, ChronoUnit.MINUTES);

        Period tenDays = Period.between(LocalDate.of(2014,3,8),
                LocalDate.of(2014,3,18));
        System.out.println("tenDays " + tenDays.getDays());
        Period tenDays1 = Period.ofDays(10);
        Period threeWeeks = Period.ofWeeks(3);
        Period twoYearsSixMonthsOneDay = Period.of(2,6,1);

    }
}
