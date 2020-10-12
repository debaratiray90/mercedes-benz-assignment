package com.userdataservice.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static Date convertFromStringToDate(String dateInString) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {

			date = formatter.parse(dateInString);
			System.out.println(date);
			System.out.println(formatter.format(date));

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String convertFromDateToString(Date date) {
          DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
          return dateFormat.format(date);  
	}
}
