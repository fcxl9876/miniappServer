package xin.fcxl9876.miniappserver.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class DateUtil {
	public static final String DATE_FULL_STR = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_SMALL_YMDHM = "yyyy-MM-dd HH:mm";
	public static final String DATE_SMALL_YMDH = "yyyy-MM-dd HH:00"; 
	public static final String DATE_SMALL_YMDHH = "yyyy-MM-dd HH"; 
	public static final String DATE_SMALL_YMDHMS = "yyyy-MM-dd HH:00:00"; 
	public static final String DATE_SMALL_STR  = "yyyy-MM-dd";
	public static final String DATE_SMALL_YM  = "yyyy-MM";
	public static final String DATE_SMALL_Y  = "yyyy";
	public static final String DATE_ALL_STR  = "yyyyMMddHHmmss";
	
	public static final Integer HOUR_MINUTE = 60; //一小时的分钟数
	public static final Integer HOUR_FIVE_MINUTE = 12; //一小时的五分钟数
	public static final Integer DAY_HOUR = 24; //一天的小时数
	public static final Integer O38H_DAY = 17; // 一天的O3滑动平均数
	public static final Integer O38H_HOUR = 8; // O3滑动区间值
	public static final Integer WEEK_DAY = 7; //一周的天数据
	
	public static void main(String[] args) {
	    List<String> hourTimeList = new ArrayList<>();
//        String minTime = Collections.min(hourTimeList) + ":01:00";
//        String maxTime = DateUtil.addHour(Collections.max(hourTimeList) + ":00:00", 1, DateUtil.DATE_SMALL_YMDHMS);
//        System.out.println(minTime);
//        System.out.println(maxTime);
//        String aString = "0(";
//        System.out.println(aString.substring(0, aString.indexOf("(")));
        for (int i = 0; i < 744; i++) {
        	String maxTime = DateUtil.addHour("2020-09-09 11", -i, DateUtil.DATE_SMALL_YMDHMS);
        	hourTimeList.add(maxTime);
		}
        System.out.println(hourTimeList.size());
        // 需要对未统计的小时时间点进行数据推移,每个时间点的数据往后最大推移度为7个小时，且必须去重并不超过当前时间点
 		List<String> newHourTimeList = new ArrayList<String>();
 		newHourTimeList.addAll(hourTimeList);
 		for (String string : hourTimeList) {
             for (int i = 1; i <= 7; i++) {
                 String newTime = DateUtil.addHour(string, -i, DateUtil.DATE_SMALL_YMDHH);
                 if (!newHourTimeList.contains(newTime) &&
                         DateUtil.stringToDate(newTime, DateUtil.DATE_SMALL_YMDHH).before(new Date())) {
                     newHourTimeList.add(newTime);
                 }
             }
         }
 		System.out.println(newHourTimeList);
 		System.out.println(newHourTimeList.size());
    }
	
    /** 
     * 获取当前日期 
     * @return 
     */  
    public static String getCurrentDate(String patten){  
        SimpleDateFormat format = new SimpleDateFormat(patten);        
        return format.format(new Date());  
    }  
    
    /**
     * 当月最后一天
     * @return
     */
	public static String getLastDayM(Date d,String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
        Date theDate = calendar.getTime();
        return df.format(theDate);
	}
	
	/** 
     *  
     * 小时加减
     */  
    public static String addHour(Date d,int n,String pattern){  
        Calendar cal = Calendar.getInstance(); 
        cal.setTime(d);
        cal.add(Calendar.HOUR,n);  
        Date datNew = cal.getTime();
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(datNew);
    } 
    
    public static String addHour(String d,int n,String pattern){  
        SimpleDateFormat sd=new SimpleDateFormat(pattern);
    	Calendar cal = Calendar.getInstance(); 
        try {
			cal.setTime(sd.parse(d));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        cal.add(Calendar.HOUR,n);  
        Date datNew = cal.getTime();
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(datNew);
    }
      
    /** 
     *  
     * 日期加减
     */  
    public static String addDate(Date d,int n,String pattern){  
        Calendar cal = Calendar.getInstance(); 
        cal.setTime(d);
        cal.add(Calendar.DATE,n);  
        Date datNew = cal.getTime();
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(datNew);
    } 
    
    public static String addDate(String d,int n,String pattern){  
        SimpleDateFormat sd=new SimpleDateFormat(pattern);
    	Calendar cal = Calendar.getInstance(); 
        try {
			cal.setTime(sd.parse(d));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        cal.add(Calendar.DATE,n);  
        Date datNew = cal.getTime();
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(datNew);
    } 
    
    /** 
     *  
     * 月份加减
     */  
    public static String addMonth(Date d,int n,String pattern){  
        Calendar cal = Calendar.getInstance(); 
        cal.setTime(d);
        cal.add(Calendar.MONTH,n);
        Date datNew = cal.getTime();
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(datNew);
    }  
    public static String addMonth(String d,int n,String pattern){  
        SimpleDateFormat sd=new SimpleDateFormat(pattern);
    	Calendar cal = Calendar.getInstance(); 
        try {
			cal.setTime(sd.parse(d));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        cal.add(Calendar.MONTH,n);  
        Date datNew = cal.getTime();
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(datNew);
    } 
    /** 
     *  
     * 年份加减
     */  
    public static String addYear(Date d,int n,String pattern){  
        Calendar cal = Calendar.getInstance(); 
        cal.setTime(d);
        cal.add(Calendar.YEAR,n);
        Date datNew = cal.getTime();
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(datNew);
    }  
    public static String addYear(String d,int n,String pattern){  
        SimpleDateFormat sd=new SimpleDateFormat(pattern);
    	Calendar cal = Calendar.getInstance(); 
        try {
			cal.setTime(sd.parse(d));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        cal.add(Calendar.YEAR,n);  
        Date datNew = cal.getTime();
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(datNew);
    } 
    
    /**
	 * 
	 * 分钟加减
	 */
	public static String addMin(Date d, int n, String pattern) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.MINUTE, n);
		Date datNew = cal.getTime();
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(datNew);
	}
	/**
	 * 
	 * 分钟加减
	 */
	public static Date addYear(Date d, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.YEAR, n);
		Date datNew = cal.getTime();
		return datNew;
	}
    
    /** 
     * 日期转换成字符串 
     * @param date 
     * @return 
     */  
    public static String dateToString(Date date,String pattern){  
        SimpleDateFormat format = new SimpleDateFormat(pattern);  
        return format.format(date);  
    }
    

    /** 
     * 日期转换成字符串 年 月 日
     * @param date 
     * @return 
     */  
    public static String dateToStringYY(Date date){  
        SimpleDateFormat format = new SimpleDateFormat(DATE_SMALL_STR);
        String d=format.format(date);
        d=d.substring(0,4)+"年"+d.substring(5,7)+"月"+d.substring(8)+"日";
        return d;
    }
    
    /** 
     * 字符串转换日期 
     * @param str 
     * @return 
     */  
    public static Date stringToDate(String str,String pattern){  
        SimpleDateFormat format = new SimpleDateFormat(pattern);  
        if(!str.equals("")&&str!=null){  
            try {  
                return format.parse(str);  
            } catch (ParseException e) {  
                e.printStackTrace();  
            }  
        }  
        return null;  
    }  
    
    /**
     * 查询当月天 列表 不包含今天
     * @return ["03-01","03-02"....]
     */
   public  static List<String>  dangyueDayStringList(){
	   List<String> list  = new ArrayList<String>();
	   Calendar ca = Calendar.getInstance();
	   ca.setTime(new Date());
	   int count = ca.get(Calendar.DAY_OF_MONTH); 
	   ca.set(Calendar.DAY_OF_MONTH, ca.getActualMinimum(Calendar.DAY_OF_MONTH)); 
	  
	   SimpleDateFormat si = new SimpleDateFormat("MM-dd");
	   String time ="";
	   int temp =1;
	   while( temp<count){
		   time = si.format(ca.getTime());
		   list.add(time);
		   ca.add(Calendar.DAY_OF_MONTH, 1);
		  
		   temp++;
	   }
	   return list;
   }
   
   /**
    * 日期第一天  20
    * @param date
    * @return 2017-03-01
    */
   public static String dangyueDiyitian(Date date,String pattern){
	   SimpleDateFormat si = new SimpleDateFormat(pattern);
	   Calendar ca = Calendar.getInstance();
	   ca.setTime(date);
	   ca.set(Calendar.DAY_OF_MONTH, ca.getActualMinimum(Calendar.DAY_OF_MONTH)); 
	   return si.format(ca.getTime());
   }
   
   /**
    * *
    * @param args
    */
   public static Date addDate2(Date date,int n){
	   Calendar cal = Calendar.getInstance(); 
       cal.setTime(date);
       cal.add(Calendar.DATE,n);  
       Date datNew = cal.getTime();
	   return datNew;
   } 
   
	/**
	 * 获取年份
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}
	/**
	 * 获取年份2
	 * @param date
	 * @return
	 */
	public static int getYear(String date) {
		Date d= stringToDate(date, DATE_ALL_STR);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		return cal.get(Calendar.YEAR);
	}
	
	/**
	 * 获取天数
	 * @param date
	 * @return
	 */
	public static int getDAY_OF_YEAR(String date,String pattern) {
		Date d= stringToDate(date, pattern);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		return cal.get(Calendar.DAY_OF_YEAR);
	}
	
	/**
	 * 获取月份
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH);
	}
	
	/**
	 * 获取月份
	 * @param date
	 * @return
	 */
	public static int getDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DATE);
	}

	/**
	 * 获取一年起始时间
	 * @param date
	 * @return
	 */
	public static Date getFirstDay_Year(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int firstDay = cal.getActualMinimum(Calendar.DAY_OF_YEAR);
		cal.set(Calendar.DAY_OF_YEAR, firstDay);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}
	
	public static String getFirstDay_Year(Date date,String pattern) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int firstDay = cal.getActualMinimum(Calendar.DAY_OF_YEAR);
		cal.set(Calendar.DAY_OF_YEAR, firstDay);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date datNew = cal.getTime();
		SimpleDateFormat format = new SimpleDateFormat(pattern);
	    return format.format(datNew);
	}
	/**
	 * 获取一年结束时间
	 * @param date
	 * @return
	 */
	public static Date getFinalyDay_Year(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int finalDat = cal.getActualMaximum(Calendar.DAY_OF_YEAR);
		cal.set(Calendar.DAY_OF_YEAR, finalDat);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}
	
	public static String getFinalyDay_Year(Date date,String pattern) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int finalDat = cal.getActualMaximum(Calendar.DAY_OF_YEAR);
		cal.set(Calendar.DAY_OF_YEAR, finalDat);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		Date datNew = cal.getTime();
		SimpleDateFormat format = new SimpleDateFormat(pattern);
	    return format.format(datNew);
	}
	
	public static String getFirstDay_Month(String date,String pattern,String pattern2) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(new SimpleDateFormat(pattern).parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int firstDat = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, firstDat);
		cal.set(Calendar.HOUR_OF_DAY, 00);
		cal.set(Calendar.MINUTE, 00);
		cal.set(Calendar.SECOND, 00);
	    return new SimpleDateFormat(pattern2).format(cal.getTime());
	}
	public static String getFinalyDay_Month(String date,String pattern,String pattern2) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(new SimpleDateFormat(pattern).parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int finalDat = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, finalDat);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
	    return new SimpleDateFormat(pattern2).format(cal.getTime());
	}
	
	/**
	 * 获取时间区间
	 * 
	 * @param d1 开始时间区间
	 * @param d2 结束时间区间
	 * @return 时间区间集合
	 */
	public static List<String> getTimeList(String d1, String d2, String pattern, String type) {
		SimpleDateFormat sd = new SimpleDateFormat(pattern);
		Calendar c = Calendar.getInstance();
		List<String> list = new ArrayList<String>();
		Date st, et;
		long sl, el, length;
		try {
			st = sd.parse(d1);
			et = sd.parse(d2);
			sl = st.getTime();
			el = et.getTime();
			if (type == "hour") {// 以小时为间隔
				length = (el - sl) / (60 * 60 * 1000);
			} else if (type == "day") {// 一天为间隔
				length = (el - sl) / (24 * 60 * 60 * 1000);
			} else {// 一月为间隔
				length = (el - sl) / (30L * 24L * 60L * 60L * 1000L); //必须加L，否则溢出结果为负数
			}
			list.add(sd.format(st));
			for (int i = 0; i < length; i++) {
				c.setTime(st);
				if (type == "hour") {
					c.add(Calendar.HOUR, 1);
					st = c.getTime();
				} else if (type == "day") {
					c.add(Calendar.DAY_OF_YEAR, 1);
					st = c.getTime();
				} else {
					c.add(Calendar.MONTH, 1);
					st = c.getTime();
				}
				list.add(sd.format(st));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 计算两个时间的相差天数
	public static int differentDays(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int day1 = cal1.get(Calendar.DAY_OF_YEAR);
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);

		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		if (year1 != year2) { // 同一年
			int timeDistance = 0;
			for (int i = year1; i < year2; i++) {
				if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) { // 闰年
					timeDistance += 366;
				} else { // 不是闰年
					timeDistance += 365;
				}
			}
			return timeDistance + (day2 - day1);
		} else { // 不同年
			return day2 - day1 + 1;
		}
	}
	
	/**
	* @Title: getDaysByYearMonth
	* @Description: 获取月份的天数
	* @param year
	* @param month
	* @return    参数
	* @return int    返回类型
	* @throws
	 */
	public static int getDaysByYearMonth(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month);
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}
	
	/**
	 * 取得季度第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDateOfSeason(Date date) {
		return getFirstDateOfMonth(getSeasonDate(date)[0]);
	}
 
	/**
	 * 取得季度最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDateOfSeason(Date date) {
		return getLastDateOfMonth(getSeasonDate(date)[2]);
	}
	
	/**
	 * 取得月第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDateOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}
 
	/**
	 * 取得月最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDateOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	
	/**
	 * 取得季度月
	 * 
	 * @param date
	 * @return
	 */
	public static Date[] getSeasonDate(Date date) {
		Date[] season = new Date[3];
 
		Calendar c = Calendar.getInstance();
		c.setTime(date);
 
		int nSeason = getSeason(date);
		if (nSeason == 1) {// 第一季度
			c.set(Calendar.MONTH, Calendar.JANUARY);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.FEBRUARY);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.MARCH);
			season[2] = c.getTime();
		} else if (nSeason == 2) {// 第二季度
			c.set(Calendar.MONTH, Calendar.APRIL);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.MAY);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.JUNE);
			season[2] = c.getTime();
		} else if (nSeason == 3) {// 第三季度
			c.set(Calendar.MONTH, Calendar.JULY);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.AUGUST);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.SEPTEMBER);
			season[2] = c.getTime();
		} else if (nSeason == 4) {// 第四季度
			c.set(Calendar.MONTH, Calendar.OCTOBER);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.NOVEMBER);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.DECEMBER);
			season[2] = c.getTime();
		}
		return season;
	}
	
	/**
	 * 
	 * 1 第一季度 2 第二季度 3 第三季度 4 第四季度
	 * 
	 * @param date
	 * @return
	 */
	public static int getSeason(Date date) {
 
		int season = 0;
 
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH);
		switch (month) {
		case Calendar.JANUARY:
		case Calendar.FEBRUARY:
		case Calendar.MARCH:
			season = 1;
			break;
		case Calendar.APRIL:
		case Calendar.MAY:
		case Calendar.JUNE:
			season = 2;
			break;
		case Calendar.JULY:
		case Calendar.AUGUST:
		case Calendar.SEPTEMBER:
			season = 3;
			break;
		case Calendar.OCTOBER:
		case Calendar.NOVEMBER:
		case Calendar.DECEMBER:
			season = 4;
			break;
		default:
			break;
		}
		return season;
	}

}
