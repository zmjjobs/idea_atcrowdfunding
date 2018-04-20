package com.bestgo.admanager.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期和字符串之间转换
 * @author zhangyu
 *
 */
public class DateStringUtil {

	/**
	 * 将日期对象转换为字符串
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return sdf.format(date);
	}
	
	/**
	 * 将字符串转换为日期对象
	 * @param date
	 * @return
	 */
	public static Date stringToDate(String date){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			return sdf.parse(date) ;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null ;
	}
	
}
