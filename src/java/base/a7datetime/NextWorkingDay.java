package java.base.a7datetime;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

/**
 * 计算明天的日期，同时过滤掉周六和周日这些节假日
 */
public class NextWorkingDay implements TemporalAdjuster {
    @Override
    public Temporal adjustInto(Temporal temporal) {
        //读取当前日期
        DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        //正常情况加1天
        int dayToAdd = 1;
        //周五加3天
        if(dow == DayOfWeek.FRIDAY) dayToAdd = 3;
        //周六加2天
        else if(dow == DayOfWeek.SATURDAY) dayToAdd = 2;
        //返回修改的日期
        return temporal.plus(dayToAdd, ChronoUnit.DAYS);
    }
}
