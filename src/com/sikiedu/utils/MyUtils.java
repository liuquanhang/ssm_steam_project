package com.sikiedu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
* @author 作者
* @version 创建时间：2019年1月28日 下午7:11:02
* 类说明
*/
public class MyUtils {
	
	public static String DateConvert(String date) throws ParseException {
		if(date.contains("/")) {
			//需要转换
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date newDate = sdf.parse(date);
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			String newDateStr = sdf1.format(newDate);
			return newDateStr;
		}
		return date;
	}
}
