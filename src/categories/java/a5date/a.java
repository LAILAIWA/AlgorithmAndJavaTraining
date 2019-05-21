package categories.java.a5date;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;

/**
 * @program: datastructure
 * @description:
 * @author: 来建培
 * @create: 2019-01-09
 */
public class a {
    public static void main(String[] args) {
        DateFormat dateFormat = DateFormat.getDateInstance();
        //Time time = new Time();

        try {
            Timestamp timestamp = Timestamp.valueOf("2019-1-9 00:00:00");
            Date date1 = Date.valueOf("2019-1-9");
            Date date2 = Date.valueOf("2019-2-10");

            //Timestamp->日期字符串
            System.out.println("1.Timestamp -> 日期字符串: ");
            System.out.println("  " + timestamp.toString() + " -> " + TimestampToDateString(timestamp));

            //日期字符串->Timestamp
            System.out.println("2.日期字符串 -> Timestamp: ");
            System.out.println("  " + "2019-1-9" + " -> " + DateStringToTimestamp("2019-1-9"));

            //Timestamp -> 日期整型数组
            int[] result = getYearMonthDayFromTimestamp(timestamp);
            System.out.println("3.Timestamp -> 日期整型数组: ");
            System.out.println("  " + timestamp.toString() + " -> [" + result[0] + "," + result[1] + "," + result[2] + "]");

            //计算日期间相隔天数
            System.out.println("4.计算日期间相隔天数: ");
            System.out.println("date1[" + date1.toString() + "],date2[" + date2.toString() + "]");
            System.out.println("  " + apartDays(date1, date2));

            //获取指定日期所在周的周一和周日的日期
            System.out.println("5.获取指定日期所在周的周一和周日的日期: ");
            System.out.println("categories.java.a5date[" + date1.toString() + "]");
            String[] gwdp = getWeekDatePeriod(date1);
            System.out.println("  " + gwdp[0] + " " + gwdp[1]);

            //获取指定日期星期几
            System.out.println("6.获取指定日期星期几: ");
            System.out.println("categories.java.a5date[" + date1.toString() + "]");
            System.out.println("  " + getWeekDayOfWeek(date1));

            //获取日期指定 月份/天数 后的日期
            System.out.println("7.获取日期指定 月份/天数 后的日期: ");
            System.out.println("categories.java.a5date[" + date1.toString() + "]");
            int num = 10, code = 1;
            System.out.println("  " + num + "月后: " + addMonthOrDayNum(date1, num, code).toString());
            num = 80;
            code = 2;
            System.out.println("  " + num + "天后: " + addMonthOrDayNum(date1, num, code).toString());

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    //Timestamp -> 日期字符串
    public static String TimestampToDateString(Timestamp timestamp) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(timestamp);
    }

    //日期字符串 -> Timestamp
    public static Timestamp DateStringToTimestamp(String date) throws Exception {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String hms = formatter.format(ts);
        date = date + " " + hms;
        return Timestamp.valueOf(date);
    }

    //Timestamp -> 日期整型数组
    public static int[] getYearMonthDayFromTimestamp(Timestamp timestamp) {
        String dateString = TimestampToDateString(timestamp);
        String[] dateArray = dateString.split("-");
        int[] date = new int[3];
        date[0] = Integer.parseInt(dateArray[0]);
        date[1] = Integer.parseInt(dateArray[1]);
        date[2] = Integer.parseInt(dateArray[2]);
        return date;
    }

    //计算日期间相隔天数
    public static int apartDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);

        if (year1 != year2) {//同一年
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {//闰年
                    timeDistance += 366;
                } else { //不是闰年
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        } else {//不同年
            System.out.println("判断day2 - day1 : " + (day2 - day1));
            return day2 - day1;
        }
    }

    //获取指定日期所在周的周一和周日的日期
    public static String[] getWeekDatePeriod(Date nowDate) {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTime(nowDate);
        int weekNum = cal.get(Calendar.WEEK_OF_YEAR);//获取当前日期在一年中的周数
        System.out.println("--当前日期所在周数：" + weekNum);

        String[] dateStrings = new String[2];
        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (1 == dayOfWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayOfWeek);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        //注意月份要加1
        dateStrings[0] = cal.get(Calendar.YEAR) + "年" + (cal.get(Calendar.MONTH) + 1) + "月" + cal.get(Calendar.DAY_OF_MONTH) + "日";
        System.out.println("所在周星期一的日期：" + dateStrings[0]);
        cal.add(Calendar.DATE, 6);
        dateStrings[1] = cal.get(Calendar.YEAR) + "年" + (cal.get(Calendar.MONTH) + 1) + "月" + cal.get(Calendar.DAY_OF_MONTH) + "日";
        System.out.println("所在周星期日的日期：" + dateStrings[1]);
        return dateStrings;
    }

    //获取指定日期星期几
    public static String getWeekDayOfWeek(Date nowDate) {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTime(nowDate);
        //当周一变为Monday，num也要减1
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
        String day = "未知";
        switch (dayOfWeek) {
            case 1:
                day = "星期一";
                break;
            case 2:
                day = "星期二";
                break;
            case 3:
                day = "星期三";
                break;
            case 4:
                day = "星期四";
                break;
            case 5:
                day = "星期五";
                break;
            case 6:
                day = "星期六";
                break;
            case 7:
                day = "星期天";
                break;
            default:
                break;
        }
        return day;
    }

    //获取日期指定 月份/天数 后的日期
    public static Date addMonthOrDayNum(Date date, int num, int code) {
        Calendar calendar = Calendar.getInstance();
        System.out.println("给定日期：" + date.toString());
        calendar.setTime(date);
        if (code == 1)
            calendar.add(Calendar.MONTH, num);//月份
        else if (code == 2)
            calendar.add(Calendar.WEEK_OF_YEAR, num);//天数
        Date result = new Date(calendar.getTime().getTime());
        System.out.println("结果日期：" + result.toString());
        return result;
    }


}
