package chapter1;

import java.sql.Date;
import java.util.Calendar;

/**
 * @program: datastructure
 * @description:
 * @author: 来建培
 * @create: 2019-01-03
 */
public class a {
    public static void main(String[] args){
//        Date startDate = Date.valueOf("2018-9-3");
//        Date date = Date.valueOf("2018-12-31");
//        System.out.println("--getCurrentWeekNum--");
//        System.out.println("--学期开始日期：" + startDate.toString());
//        //获取当前日期
//        System.out.println("--日期：" + date.toString());
//
//        //获取开始日期在一年中的周数
//        Calendar cal1 = Calendar.getInstance();
//        cal1.setFirstDayOfWeek(Calendar.MONDAY);//设置周一为一周的第一天
//        cal1.setTime(startDate);
//        int num1 = cal1.get(Calendar.WEEK_OF_YEAR);
//        System.out.println("--学期开始所在周数：" + num1);
//
//        //获取当前日期在一年中的周数
//        Calendar cal2 = Calendar.getInstance();
//        cal2.setFirstDayOfWeek(Calendar.MONDAY);
//        cal2.setTime(date);
//        int num2 = cal2.get(Calendar.WEEK_OF_YEAR);
//        int month = cal2.get(Calendar.MONTH);
//        System.out.println("--当前日期所在周数：" + num2);
//        if(month == 11 && num2 == 1)
//            num2 = 53;
//
//        int result = 0;
//        int startYear = cal1.get(Calendar.YEAR);
//        int nowYear = cal2.get(Calendar.YEAR);
//        int Child = nowYear-startYear;
//        if(Child == 0){
//            result = num2 - num1 + 1;
//        }else if(Child > 0){
//            result = Child * (52 - num1) + num2 + 1;
//        }else {
//            result = -1;
//        }
//        System.out.println("--当前所在教学周数：" + result);
        //2019-7-16 2019-9-1  2019-1-22 2019-2-24
        Date startDate = Date.valueOf("2019-7-17");
        Date endDate = Date.valueOf("2019-9-1");
        boolean result = judgeIfInVacationTime(startDate,endDate,"1");
        System.out.println("结果：" + result);
        startDate = Date.valueOf("2019-1-23");
        endDate = Date.valueOf("2019-2-24");
        result = judgeIfInVacationTime(startDate,endDate,"2");
        System.out.println("结果：" + result);
    }

    static Date startDate201801 = Date.valueOf("2018-9-3");
    static Date startDate201802 = Date.valueOf("2019-2-25");
    static Date startDate201901 = Date.valueOf("2019-9-2");
    static Date startDate201902 = Date.valueOf("2020-2-24");

    //获取结束日期
    public static Date countEndDate(Date beginDate){
        Calendar calendar = Calendar.getInstance();
        System.out.println("开学日期：" + beginDate.toString());
        calendar.setTime(beginDate);
        calendar.add(Calendar.WEEK_OF_YEAR,20);
        Date endDate = new Date(calendar.getTime().getTime());
        System.out.println("结束日期：" + endDate.toString());
        return endDate;
    }

    //判断时间段是否在寒暑假
    public static boolean judgeIfInVacationTime(Date startDate,Date endDate,String code){
        //获取对应假期时间段
        if(code.equals("1")){//暑假
            Date vacBeginDate = countEndDate(startDate201802);
            Date vacEndDate = startDate201901;
            System.out.println("暑假开始日期：" + vacBeginDate.toString());
            System.out.println("暑假结束日期：" + vacEndDate.toString());
            if(startDate.after(vacBeginDate) && endDate.before(vacEndDate))
                return true;
            else
                return false;
        }else if(code.equals("2")){//寒假
            Date vacBeginDate = countEndDate(startDate201801);
            Date vacEndDate = startDate201802;
            System.out.println("寒假开始日期：" + vacBeginDate.toString());
            System.out.println("寒假结束日期：" + vacEndDate.toString());
            if(startDate.after(vacBeginDate) && endDate.before(vacEndDate))
                return true;
            else
                return false;
        }else
            throw new RuntimeException("假期代码异常");

    }
}
