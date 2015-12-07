package com.cdel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Date Utility Class used to convert Strings to Dates and Timestamps
 */
public class DateUtil {
	private static Log log = LogFactory.getLog(DateUtil.class);

	// 当前时间转到时间stirng
	public static String getNowToString(String format) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String sNowTime = sdf.format(date);
		return sNowTime;

	}

	// 当前时间转到时间Date
	public static Date getNowToDate(String format) {
		String date = getNowToString("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			Date sNowTime = sdf.parse(date);
			return sNowTime;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(ExceptionUtil.getEMsg(e));
			return null;
		}

	}

	// date转到时间stirng
	public static String getNowDateString(Date date, String format) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			String sNowTime = sdf.format(date);
			return sNowTime;
		} else {
			return null;
		}

	}

	// 时间stirng转到date
	public static Date stringToDate(String str, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;
		try {
			if (str != null && !str.equals("")) {
				date = sdf.parse(str);
			}
		} catch (ParseException e) {
			e.printStackTrace();
			log.error(ExceptionUtil.getEMsg(e));
		}
		return date;
	}

	/**
	 * 返回00:00:00
	 * 
	 * @param date
	 * @return
	 */
	public static Date getStartDayTime(Date date) {
		if (date != null) {
			String s = getNowDateString(date, "yyyy-MM-dd");
			return stringToDate(s + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
		} else {
			return null;
		}
	}

	/**
	 * 返回hour:min:sec
	 * 
	 * @param date
	 * @return
	 */
	public static Date getStartDayTime(Date date, Short hour, Short min,
			Short sec) {
		if (date != null) {
			String s = getNowDateString(date, "yyyy-MM-dd");
			String hours = "";
			if (hour == null || hour == 0 || hour > 23) {
				hours = "00";
			} else {
				if (hour < 10) {
					hours = "0" + hour;
				} else {
					hours = hour.toString();
				}
			}
			String mins = "";
			if (min == null || min == 0 || min > 59) {
				mins = "00";
			} else {
				if (min < 10) {
					mins = "0" + min;
				} else {
					mins = min.toString();
				}
			}
			String secs = "";
			if (sec == null || sec == 0 || sec > 59) {
				secs = "00";
			} else {
				if (sec < 10) {
					secs = "0" + sec;
				} else {
					secs = sec.toString();
				}
			}
			return stringToDate(s + " " + hours + ":" + mins + ":" + secs,
					"yyyy-MM-dd HH:mm:ss");
		} else {
			return null;
		}
	}

	/**
	 * 获取年份
	 * 
	 * @param date
	 * @return
	 */
	public static String getYear(Date date) {
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal.get(Calendar.YEAR) + "";
		} else {
			return "";
		}
	}

	/**
	 * 获取月份
	 * 
	 * @param date
	 * @return
	 */
	public static String getMonth(Date date) {
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int month = cal.get(Calendar.MONTH) + 1;
			if (month < 10) {
				return "0" + month;
			} else {
				return "" + month;
			}
		} else {
			return "";
		}
	}

	/**
	 * 获取第二天信息
	 * 
	 * @param endDate
	 * @return
	 */
	public static Date getNextDay(Date endDate) {
		if (endDate != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(endDate);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			return calendar.getTime();
		}
		return null;
	}

	/**
	 * 获取星期几
	 * 
	 * @param endDate
	 * @return
	 */
	public static int getDayOfWeek(Date d) {
		if (d != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			int c = cal.get(Calendar.DAY_OF_WEEK) - 1;
			if (c < 0) {
				return 0;
			} else {
				return c;
			}
		}
		return -1;
	}

	/**
	 * 返回days天以后的日期
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date getAfterDate(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)
				+ days);
		return calendar.getTime();
	}

	/**
	 * 返回days天以后的日期
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date getAfterDate(Date date, int days, Short hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)
				+ days);
		return getStartDayTime(calendar.getTime(), hour, (short) 0, (short) 0);
	}

	/**
	 * 返回两个日期的时间差，毫秒
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Long getDiffDate(Date date1, Date date2) {
		if (date1 != null && date2 != null) {
			return date1.getTime() - date2.getTime();
		} else {
			return null;
		}
	}

	/**
	 * 返回两个日期的日期差
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Integer getDiffDate2(Date date1, Date date2) {
		Long se = getDiffDate(date1, date2);
		if (se != null) {
			Long se2 = se / (long) (24 * 60 * 60 * 1000);
			return se2.intValue() + 1;
		} else {
			return null;
		}
	}

	/**
	 * 分钟数转换为小时数和分钟数显示，如： 100分钟 => 1小时40分钟
	 * 
	 * @param minutes
	 * @return
	 */
	public static String minute2HourMinute(int minutes) {
		if (minutes <= 0) {
			return "0小时0分钟";
		} else if (minutes < 60) {
			return "0小时" + minutes + "分钟";
		} else {
			int h = minutes / 60;
			int m = minutes % 60;

			return h + "小时" + m + "分钟";
		}
	}

	public static Date getWeekDay(Short weekNum, Byte weekDay, Short hour,
			Date startTime, Date endTime) {
		int index = 0;
		int dayOfWeek = -1;
		Date nextDate = startTime;
		do {
			nextDate = getNextDay(nextDate);
			if (nextDate.compareTo(endTime) > 0) {
				return null;
			}
			dayOfWeek = getDayOfWeek(nextDate);
			if (dayOfWeek == weekDay) {
				index++;
			}
		} while (dayOfWeek != weekDay || index != weekNum);
		return getStartDayTime(nextDate, hour, (short) 0, (short) 0);
	}
}
