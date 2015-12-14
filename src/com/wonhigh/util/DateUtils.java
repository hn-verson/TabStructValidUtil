package com.wonhigh.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtils {
	
	/**
	 * 返回格式化的当前时间字符串
	 * @param pattern
	 * @return
	 */
	public static String getFormatDate(String pattern){
		DateFormat df = null;
		Date date = new Date();
		try {
			df = new SimpleDateFormat(Constants.LOG_FILE_DATE_FORMAT);
		} catch (NullPointerException npe) {
			//given pattern is null
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} catch (IllegalArgumentException iae){
			//given pattern is invalid
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		return df.format(date);
	}

}
