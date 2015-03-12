package com.cmbb.smartkids.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTools {

	/**
	 * 日期转为字符
	 * @param date 日期
	 * @param formatString 格式
	 * @return
	 */
	public static String dateToString(Date date, String formatString) {
		if (formatString == null || formatString.equals(""))
			formatString = "yyyy-MM-dd HH:mm:ss";

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(formatString);
			return sdf.format(date);
		} catch (Exception e) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(date);
		}
	}

	/**
	 * 将日期转换成当天8:00的字符串
	 * @param date    日期
	 * @param formatString  转换格式
	 * @return
	 */
	public static String dateToString8AM(Date date, String formatString) {
		if (formatString == null || formatString.equals(""))
			formatString = "yyyy-MM-dd HH:mm:ss";

		try {
			Date newDate = new Date(date.getYear(), date.getMonth(), date.getDate(), 8, 0, 0);
			SimpleDateFormat sdf = new SimpleDateFormat(formatString);
			return sdf.format(newDate);
		} catch (Exception e) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(date);
		}
	}

	/**
	 *  将日期转换成某天的某�?��刻的字符�?
	 * @param date   日期
	 * @param formatString  日期格式
	 * @param hourse      �?
	 * @param min         �?
	 * @param sec         �?
	 * @return
	 */
	public static String dateToString(Date date, String formatString, int hourse, int min, int sec) {
		if (formatString == null || formatString.equals(""))
			formatString = "yyyy-MM-dd HH:mm:ss";

		try {
			Date newDate = new Date(date.getYear(), date.getMonth(), date.getDate(), hourse, min,
					sec);
			SimpleDateFormat sdf = new SimpleDateFormat(formatString);
			return sdf.format(newDate);
		} catch (Exception e) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(date);
		}
	}

	/**
	 * 字符串转为日�?按照yyyy-MM-dd HH:mm:ss格式
	 * @param strDate 日期字符�?
	 * @param defaultValue 默认�?
	 * @return
	 */
	public static Date stringToDate(String strDate, Date defaultValue) {
		return stringToDate(strDate, "yyyy-MM-dd HH:mm:ss", defaultValue);
	}

	/**
	 * 字符串转为日�?
	 * @param strDate 日期字符�?
	 * @param formatString 格式化字符串 如果为空则按照yyyy-MM-dd HH:mm:ss格式
	 * @param defaultValue 
	 * @return
	 */
	public static Date stringToDate(String strDate, String formatString, Date defaultValue) {
		if (formatString == null || formatString.equals(""))
			formatString = "yyyy-MM-dd HH:mm:ss";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(formatString);
			return sdf.parse(strDate);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	* 把JSON中常用的Date格式转化成普通字符串的Date格式
	* @param jsonDate 类似 Date(1301760000000+0800) 格式
	* @param formatString 格式
	* @return
	*/
	public static String jsonDateToSimpleDateString(String jsonDate, String formatString) {
		String dateStr = jsonDate;
		dateStr = dateStr.substring(5, dateStr.length() - 1);
		String[] dateStrArr = dateStr.split("\\+");
		Date date = new Date(Long.parseLong(dateStrArr[0]) + Long.parseLong(dateStrArr[1]));
		dateStr = dateToString(date, "");
		return dateStr;
	}

	/**
	 * 转换日期格式
	 * @param strdate 日期字符�?
	 * @param beferformat 日期格式
	 * @param afterformat 结果日期格式
	 * @return
	 */
	public static String changeDateFormat(String strdate, String beferformat, String afterformat) {
		Date date = new Date();
		String afterStr = null;
		SimpleDateFormat sdf = new SimpleDateFormat(beferformat);
		try {
			date = (Date) sdf.parse(strdate);
			SimpleDateFormat after = new SimpleDateFormat(afterformat);
			afterStr = after.format(date);
			System.out.println(afterStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return afterStr;
	}

	public static long dateToLong(String strdate) {
		Date temp = stringToDate(strdate, Calendar.getInstance().getTime());
		return temp.getTime();
	}
}
