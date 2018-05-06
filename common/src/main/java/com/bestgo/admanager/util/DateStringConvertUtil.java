package com.bestgo.admanager.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 字符串与日期间的转换的工具类
 *@author zhumengjun
 *@version 
 *@datetime 2017年7月11日上午11:41:36  
 */
public class DateStringConvertUtil {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 将日期转换为字符串
	 * @return
	 */
	public static String dateToString(Date date){
		return sdf.format(date);
	}
	
	/**
	 * 将字符串转换为日期
	 * @return
	 */
	public static Date stringToDate(String dateStr){
		try {
			sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
