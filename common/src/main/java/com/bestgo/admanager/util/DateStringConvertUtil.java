package com.bestgo.admanager.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author mengjun
 * @Date 2018-05-01 00:35
 * @Desc 日期和字符串之间转换
 */
public class DateStringConvertUtil {

	/**
	 * 将日期对象转换为字符串
	 * @param date
	 * @param format 比如"yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	public static String dateToString(Date date,String format){
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		return sdf.format(date);
	}
	

	/**
	 * 将字符串转换为日期对象
	 * @param date
	 * @param format 比如"yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	public static Date stringToDate(String date,String format){
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		try {
			return sdf.parse(date) ;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null ;
	}
	
}
