package com.sikiedu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
* @author ����
* @version ����ʱ�䣺2019��1��28�� ����7:11:02
* ��˵��
*/
public class MyUtils {
	
	public static String DateConvert(String date) throws ParseException {
		if(date.contains("/")) {
			//��Ҫת��
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date newDate = sdf.parse(date);
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			String newDateStr = sdf1.format(newDate);
			return newDateStr;
		}
		return date;
	}
}
