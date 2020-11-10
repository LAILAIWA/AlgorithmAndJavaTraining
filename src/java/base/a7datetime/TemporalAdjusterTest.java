package java.base.a7datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static java.time.temporal.TemporalAdjusters.*;

public class TemporalAdjusterTest {
    public static void main(String[] args) {
        //TemporalAdjuster
        LocalDate date1 = LocalDate.of(2014,3,18);//2014-03-18
        LocalDate date2 = date1.with(nextOrSame(DayOfWeek.SUNDAY));//2014-03-23
        LocalDate date3 = date2.with(lastDayOfMonth());//2014-03-31
        System.out.println("date1 " + date1);
        System.out.println("date2 " + date2);
        System.out.println("date3 " + date3);

        //自定义TemporalAdjuster
        //通过类实现
        LocalDate ta1 = date1.with(new NextWorkingDay());//2014-03-19
        //通过Lambda表达式实现
        LocalDate ta2 = date1.with(temporal -> {
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if(dow == DayOfWeek.FRIDAY) dayToAdd = 3;
            else if(dow == DayOfWeek.SATURDAY) dayToAdd = 2;
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });//2014-03-19
        //推荐使用工厂方法ofDateAdjuster
        TemporalAdjuster nextWorkingDay = TemporalAdjusters.ofDateAdjuster(
                temporal -> {
                    DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
                    int dayToAdd = 1;
                    if(dow == DayOfWeek.FRIDAY) dayToAdd = 3;
                    else if(dow == DayOfWeek.SATURDAY) dayToAdd = 2;
                    return temporal.plus(dayToAdd, ChronoUnit.DAYS);
                }
        );//2014-03-19
        LocalDate ta3 = date1.with(nextWorkingDay);
        System.out.println("ta1 " + ta1);
        System.out.println("ta2 " + ta2);
        System.out.println("ta3 " + ta3);
    }
}
