package close.gxph.bunny.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



/**
 * 日期操作工具类
 * @author 李彬
 *
 */
public class DateUtil {
	/**
	 * 默认日期格式
	 */
	private static final String LONG_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final String SHORT_FORMAT = "yyyy-MM-dd";
	private static final String CODE_FORMAT = "yyyyMMdd";
	/**
	 * 一天毫秒总数
	 */
	private static final int DAY_MILLSECONDS = 1000 * 60 * 60 * 24;
	/**
	 * 一小时毫秒总数
	 */
	private static final int DAY_MILLSECOND = 1000 * 60 * 60;
	/**
	 * 默认日期格式化对象
	 */
	private static final SimpleDateFormat LONG_DATE_FORMAT = new SimpleDateFormat(LONG_FORMAT);
	private static final SimpleDateFormat SHORT_DATE_FORMAT = new SimpleDateFormat(SHORT_FORMAT);
	private static final SimpleDateFormat CODE_DATE_FORMAT = new SimpleDateFormat(CODE_FORMAT);
	/**
	 * 星期参数
	 */
	public static String MONDAY = "";
	public static String TUESDAY = "";
	public static String WEDNESDAY = "";
	public static String THURSDAY = "";
	public static String FRIDAY = "";
	public static String SATURDAY = "";
	public static String SUNDAY = "";
	/**
	 * 获取当前YYYY-MM-DD HH:MM:SS格式的日期
	 * 返回类型为String
	 */
	public static String getCurrLongDateStr(){
		String ret = null;
		try {
			ret = LONG_DATE_FORMAT.format(Calendar.getInstance().getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 获取当前YYYY-MM-DD格式日期
	 * 返回类型为String
	 */
	public static String getCurrShortDateStr(){
		String ret = null;
		try{
			ret = SHORT_DATE_FORMAT.format(Calendar.getInstance().getTime());
		}catch(Exception e){
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 获取当前YYYYMMDD格式日期
	 * 返回类型为String
	 */
	public static String getCurrCodeDateStr(){
		String ret = null;
		try{
			ret = CODE_DATE_FORMAT.format(Calendar.getInstance().getTime());
		}catch(Exception e){
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 获取当前YYYY-MM-DD HH:MM:SS格式的日期
	 * 返回类型为Date
	 */
	public static Date getCurrLongDate(){
		Date ret = null;
		try {
			ret = LONG_DATE_FORMAT.parse(LONG_DATE_FORMAT.format(Calendar.getInstance().getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 获取当前YYYY-MM-DD格式的日期
	 * 返回类型为Date
	 */
	public static Date getCurrShortDate(){
		Date ret = null;
		try {
			ret = SHORT_DATE_FORMAT.parse(SHORT_DATE_FORMAT.format(Calendar.getInstance().getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 获取当前YYYYMMDD格式的日期
	 * 返回类型为Date
	 */
	public static Date getCurrCodeDate(){
		Date ret = null;
		try {
			ret = CODE_DATE_FORMAT.parse(CODE_DATE_FORMAT.format(Calendar.getInstance().getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 根据指定日期格式格式化字符串日期为日期类型
	 * @param dateString
	 * 字符串日期参数
	 * @param dateType
	 * 指定日期格式
	 */
	public static Date formatDate(String dateString,String dateType){
		Date ret = null;
		try {
			ret = new SimpleDateFormat(dateType).parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 根据指定的日期格式格式化日期为字符串日期类型
	 * @param date
	 * @param dateType
	 * @return
	 */
	public static String formatDateToStr(Date date,String dateType){
		String ret = null;
		try {
			ret = new SimpleDateFormat(dateType).format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 获取已知日期的指定之后天数后的日期
	 * @param date
	 * 指定日期
	 * @param days
	 * 指定天数
	 * @return
	 */
	public static Date generateDateOfDays(Date date, int days) {
		Date ret = null;
		try {
			if (date == null) {
				return ret;
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, days);
			ret = calendar.getTime();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 计算两个日期之间的相隔天数
	 * 
	 * @param early
	 * @param late
	 * @return
	 */
	public static long calculateDaysBetween(Date beforeDate, Date backdate) {
		long ret = 0;
		try {
			if (beforeDate == null && backdate == null) {
				return ret;
			}
			ret = ((beforeDate.getTime() - backdate.getTime()) / DAY_MILLSECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 判定early日期是否在late日期指定天数之前
	 * 
	 * @param early
	 * 判定日期
	 * @param late
	 * 指定日期
	 * @param day
	 * 相隔天数
	 * @return
	 */
	public static boolean isBeforeDate(Date early, Date late, int day) {
		boolean ret = false;
		try {
			if (early == null || late == null) {
				return ret;
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(late);
			calendar.add(Calendar.DAY_OF_MONTH, -day);
			ret = early.before(calendar.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 判定late日期是否在early日期指定天数之后
	 * 
	 * @param early
	 * 判定日期
	 * @param late
	 * 指定日期
	 * @param day
	 * 相隔天数
	 * @return
	 */
	public static boolean isAfterDate(Date early, Date late, int day) {
		boolean ret = false;
		try {
			if (early == null || late == null) {
				return ret;
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(early);
			calendar.add(Calendar.DAY_OF_MONTH, day);
			ret = late.after(calendar.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 获取指定日期星期信息参数
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateWeek(Date date) {
		String ret = null;
		try {
			if (date == null) {
				return ret;
			}
			ret = new SimpleDateFormat("EEEE").format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 获取指定日期月份信息参数
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateMonth(Date date){
		String ret = null;
		try {
			ret = new SimpleDateFormat("MM").format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 获取指定日期月份信息参数
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateYear(Date date){
		String ret = null;
		try {
			ret = new SimpleDateFormat("yyyy").format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 获取指定日期日信息参数
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateDay(Date date){
		String ret = null;
		try {
			ret = new SimpleDateFormat("dd").format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 获取指定日期年份和月份信息参数
	 * @param date
	 * 指定日期
	 * @param dateType
	 * [yyyy-MM][yyyyMM]
	 * @return
	 */
	public static String getDateYearAndMonth(Date date,String dateType){
		String ret = null;
		try {
			ret = new SimpleDateFormat(dateType).format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 获取指定日期上一年信息参数
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastYearDate(Date date) {
		Date ret = null;
		try {
			if (date == null) {
				return ret;
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.YEAR, -1);
			ret = calendar.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 获取指定日期下一年信息参数
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNextYearDate(Date date) {
		Date ret = null;
		try {
			if (date == null) {
				return ret;
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.YEAR, 1);
			ret = calendar.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 获取指定日期上一月信息参数
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastMonthDate(Date date) {
		Date ret = null;
		try {
			if (date == null) {
				return ret;
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, -1);
			ret = calendar.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 获取指定日期下一月信息参数
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNextMonthDate(Date date) {
		Date ret = null;
		try {
			if (date == null) {
				return ret;
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, 1);
			ret = calendar.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 获取指定日期前一天信息参数
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayDate(Date date) {
		Date ret = null;
		try {
			if (date == null) {
				return ret;
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			ret = calendar.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 获取指定日期3天后信息参数
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNextDayDate(Date date) {
		Date ret = null;
		try {
			if (date == null) {
				return ret;
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, 3);
			ret = calendar.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 获取指定日期所在星期星期一日期信息参数
	 * 
	 * @param date
	 * @return
	 */
	public static Date getWeekStartDate(Date date) {
		Date ret = null;
		try {
			if (date == null) {
				return ret;
			}

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);

			int weekDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;
			switch (weekDay) {
			case 0:
				calendar.add(Calendar.DAY_OF_MONTH, -6);
				break;
			case 1:
				break;
			default:
				calendar.add(Calendar.DAY_OF_MONTH, -(weekDay - 1));
			}

			ret = calendar.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 获取指定日期所在星期星期日日期信息参数
	 * 
	 * @param date
	 * @return
	 */
	public static Date getWeekEndDate(Date date) {
		Date ret = null;
		try {
			if (date == null) {
				return ret;
			}

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);

			int weekDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;
			switch (weekDay) {
			case 0:
				break;
			default:
				calendar.add(Calendar.DAY_OF_MONTH, (7 - weekDay));
			}

			ret = calendar.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 获取指定日期所在月份第一日日期信息参数
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthStartDate(Date date) {
		Date ret = null;
		try {
			if (date == null) {
				return ret;
			}

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.DAY_OF_MONTH, 1);

			ret = calendar.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 获取指定日期所在月份天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getDaysOfMonth(Date date) {
		int ret = 0;
		try {
			if (date == null) {
				return ret;
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			ret = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 获取指定日期所在月份最末一日日期信息参数
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthEndDate(Date date) {
		Date ret = null;
		try {
			if (date == null) {
				return ret;
			}

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.DAY_OF_MONTH, getDaysOfMonth(date));
			ret = calendar.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public static List<String> time(Date date, Date times,String day) {
		List<String> list = new ArrayList<String>();
		String time = DateUtil.getDateDay(times);
		String pre = null;
		String cur = null;
		if (date != null && !date.equals("")) {
			pre = DateUtil.getDateYearAndMonth(DateUtil.getLastMonthDate(date),
					"yyyy-MM");
			pre = pre + "-" + (Integer.parseInt(day) + 1);
			cur = DateUtil.getDateYearAndMonth(date, "yyyy-MM");
			cur = cur + "-" + Integer.parseInt(day);
		} else if (Integer.parseInt(time) <= Integer.parseInt(day)) {
			pre = DateUtil.getDateYearAndMonth(
					DateUtil.getLastMonthDate(times), "yyyy-MM");
			pre = pre + "-" + (Integer.parseInt(day) + 1);
			cur = DateUtil.getDateYearAndMonth(times, "yyyy-MM");
			cur = cur + "-" + Integer.parseInt(day);
		} else {
			pre = DateUtil.getDateYearAndMonth(DateUtil.getCurrShortDate(),
					"yyyy-MM");
			pre = pre + "-" + (Integer.parseInt(day) + 1);
			cur = DateUtil.getDateYearAndMonth(DateUtil.getNextMonthDate(times),
					"yyyy-MM");
			cur = cur + "-" + Integer.parseInt(day);
		}
		list.add(pre);
		list.add(cur);
		return list;
	}
	
	/**
	 * 获取导游出行天数
	 * @param str添加时间
	 * @return
	 */
	public static long getguidedays(String str){
			long a = 0;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String str1 = sdf.format(new Date());
			SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date d=sim.parse(str);
				Date d1=sim.parse(str1);
				return DateUtil.calculateDaysBetween(d1, d);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			return a;
	}
	
	private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static Date str2Date(String str) {
		return str2Date(str, null);
	}

	public static Date str2Date(String str, String format) {
		if (str == null || str.length() == 0) {
			return null;
		}
		if (format == null || format.length() == 0) {
			format = FORMAT;
		}
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			date = sdf.parse(str);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;

	}

	public static Calendar str2Calendar(String str) {
		return str2Calendar(str, null);

	}

	public static Calendar str2Calendar(String str, String format) {

		Date date = str2Date(str, format);
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		return c;

	}

	public static String date2Str(Calendar c) {// yyyy-MM-dd HH:mm:ss
		return date2Str(c, null);
	}

	public static String date2Str(Calendar c, String format) {
		if (c == null) {
			return null;
		}
		return date2Str(c.getTime(), format);
	}

	public static String date2Str(Date d) {// yyyy-MM-dd HH:mm:ss
		return date2Str(d, null);
	}

	public static String date2Str(Date d, String format) {// yyyy-MM-dd HH:mm:ss
		if (d == null) {
			return null;
		}
		if (format == null || format.length() == 0) {
			format = FORMAT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String s = sdf.format(d);
		return s;
	}

	public static String getCurDateStr() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-"
				+ c.get(Calendar.DAY_OF_MONTH) + "-"
				+ c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE)
				+ ":" + c.get(Calendar.SECOND);
	}

	/**
	 * 获得当前日期的字符串格式
	 * 
	 * @param format
	 * @return
	 */
	public static String getCurDateStr(String format) {
		Calendar c = Calendar.getInstance();
		return date2Str(c, format);
	}

	// 格式到秒
	public static String getMillon(long time) {

		return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(time);

	}

	// 格式到天
	public static String getDay(long time) {

		return new SimpleDateFormat("yyyy-MM-dd").format(time);

	}

	// 格式到毫秒
	public static String getSMillon(long time) {

		return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS").format(time);

	}
	
	/**
	 * 计算两个日期之间的相隔小时数
	 * 
	 * @param early
	 * @param late
	 * @return
	 */
	public static long calculateDaysBetweens(String beforeDate, String backdate) {
		long a = 0;
		long ret = 0;
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d=sim.parse(beforeDate);
			Date d1=sim.parse(backdate);
			if (d == null && d1 == null) {
				return ret;
			}
			ret = ((d1.getTime() - d.getTime()) / DAY_MILLSECOND);
			System.out.println(ret);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * 获取指定日期n天后信息参数
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNextDayDates(Date date,int day) {
		Date ret = null;
		try {
			if (date == null) {
				return ret;
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, day);
			ret = calendar.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * 计算日期与当前时间的大小 ，  true是日期大于当前时间
	 * 
	 * @param early
	 * @param late
	 * @return
	 */
	public static boolean compareToCurrentTime(String date) {
		boolean ret = false;
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		try {
			String currentTime = new Timestamp(System.currentTimeMillis())+"";
			Date d=sim.parse(currentTime);
			Date d1=sim.parse(date);
			if (d == null && d1 == null) {
				return ret;
			}
			ret = ((d1.getTime() - d.getTime()) / DAY_MILLSECOND) > 0;
			System.out.println(ret);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return ret;
	}
	/**
	 * 将2015-12-15T16:00:00.000Z格式的时间字符串转换成java.ql.Timestamp类型
	 */
	public static Timestamp convertTimecode(String paramTime){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date tempDate;
		long systamp = 0L;
		try {
			tempDate = getDate(sdf.parse(paramTime.substring(0,10)));
			systamp = tempDate.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Timestamp(systamp);
	}
	/**
	 * 将2015-12-15T16:00:00.000Z格式的时间字符串转换成java.ql.Timestamp类型
	 */
	public static Timestamp convertStrToTimestamp(String paramTime,String formatStr){
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		Date tempDate;
		long systamp = 0L;
		try {
			tempDate = getDate(sdf.parse(paramTime.substring(0,19)));
			systamp = tempDate.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Timestamp(systamp);
	}
	// 将时间戳转为字符串  
	public static String getStrTime(String cc_time) {  
		String re_StrTime = null;  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		// 例如：cc_time=1291778220  
		long lcc_time = Long.valueOf(cc_time);  
		re_StrTime = sdf.format(new Date(lcc_time * 1000L));  	  
		return re_StrTime;  
	}
	/**
	 * 获取指定日期的下一天
	 * @param date
	 * @return
	 */
	public static Date getDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date date1 = new Date(calendar.getTimeInMillis());
		return date1;
	}
	/**
	 * 获取已知字符串日期指定天数内每一天对应的日期集合
	 * @param date
	 * 指定日期
	 * @param days
	 * 指定天数
	 * @return
	 */
	public static List<String> getDaysByStime(Timestamp date, int days) {
		Date ret = new Date() ;
		List<String> timelists = new ArrayList<String>();
		try {
			ret = date;
			if (date == null) {
				return timelists;
			}
			for (int i = 0; i < days; i++) {
				ret = date;
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(ret);
				calendar.add(Calendar.DAY_OF_MONTH, i);
				ret = calendar.getTime();
				timelists.add(SHORT_DATE_FORMAT.format(ret));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timelists;
	}
	/**
	 * 获取指定日期后几天后，按形式输出字符串
	 * @param date
	 * @return
	 */
	public static String addDay(Date date, int d, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, d);
		return formatter.format(cal.getTime());
	}
	 /**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }  
    /**  
     * 获取当前时间  
     * @return timestamp类型的时间  
     */    
    public static Timestamp getNowTime(){    
    	Timestamp nowTime = new Timestamp(System.currentTimeMillis()); 
		return nowTime;       
    } 
    
    
    /**
	 * 测试方法
	 * @param args
	 */
	public static void main(String[] args) {
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = SHORT_DATE_FORMAT.parse("2010-11-11");
			d2 = SHORT_DATE_FORMAT.parse("2010-11-1");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println(getNextDayDate(new Date()) + "字符串日期格式：YYYY-MM-DD HH:MM:SS");
	
		
		
	}
}
