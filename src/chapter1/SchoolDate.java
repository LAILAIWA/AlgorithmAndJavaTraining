package chapter1;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @program: datastructure
 * @description:
 * @author: 来建培
 * @create: 2018-12-07
 */
public class SchoolDate {


    public static int[] getSchoolYearAndSemester(){

        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(nowTime);
        int[] result = new int[2];
        String[] dateArray =  dateString.split("-");
        int year = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]);
        //同时转换学年与学期
        if(month > 2 && month < 9){// 3-8月份为第二学期
            result[1] = 2;
            //转换学年
            result[0] = year-1;
        }else if(month > 0 && month < 3){// 1-2月份,9-12月份为第一学期
            result[1] = 1;
            //转换学年
            result[0] = year-1;
        }else if( month > 8 && month < 13){
            result[1] = 1;
            //转换学年
            result[0] = year;
        }
        else {//月份违规则赋值-1
            result[1] = -1;
        }
        return result;
    }
}
