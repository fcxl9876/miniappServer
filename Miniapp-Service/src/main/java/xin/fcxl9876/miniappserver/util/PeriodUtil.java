package xin.fcxl9876.miniappserver.util;

import java.util.Calendar;
import java.util.Date;

/**
 * 周报 期数工具类
 */
public class PeriodUtil {

    private static final Integer MINIMAL_DAYS_IN_FIRST_WEEK = 7;

    public static String getStartByPeriod(Integer periodNum){
        Calendar calendar = Calendar.getInstance();
        calendar.setMinimalDaysInFirstWeek(MINIMAL_DAYS_IN_FIRST_WEEK);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.DAY_OF_WEEK, 2);

        calendar.set(Calendar.WEEK_OF_YEAR, periodNum);

        return DateUtil.dateToString(calendar.getTime(), DateUtil.DATE_FULL_STR);
    }

    public static String getEndByPeriod(Integer periodNum){
        Calendar calendar = Calendar.getInstance();
        calendar.setMinimalDaysInFirstWeek(MINIMAL_DAYS_IN_FIRST_WEEK);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.DAY_OF_WEEK, 2);

        calendar.set(Calendar.WEEK_OF_YEAR, periodNum+1);
        calendar.add(Calendar.SECOND, -1);

        return DateUtil.dateToString(calendar.getTime(), DateUtil.DATE_FULL_STR);
    }

    public static Integer getNowPeriodNum(){
        Calendar calendar = Calendar.getInstance();

        calendar.setMinimalDaysInFirstWeek(MINIMAL_DAYS_IN_FIRST_WEEK);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);

        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public static Integer getPeriodNumByStartDate(Date startDate){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.setMinimalDaysInFirstWeek(MINIMAL_DAYS_IN_FIRST_WEEK);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);

        return calendar.get(Calendar.WEEK_OF_YEAR);
    }


    public static void main(String[] args) {
        System.out.println(getNowPeriodNum());
        System.out.println(getStartByPeriod(45));
        System.out.println(getEndByPeriod(45));
        System.out.println(getStartByPeriod(1));
        System.out.println(getEndByPeriod(1));
    }

}
