package com.yan.spring.batch.learning;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex {

	public static void main(String[] args) {
		testDateRegex();

	}

	public static void testNumRegex() {
		String line = "18.59%";
		Pattern r = Pattern.compile("(\\d+\\.\\d+)");

		// 现在创建 matcher 对象
		Matcher m = r.matcher(line);
		if (m.find()) {
			System.out.println(m.group(0));
			System.out.println(m.group(1));
			
		}
	}
	
	public static void testDateRegex() {
		String line = "(20-02-27 15:00)";
		//(20-02-27 15:00)
		Pattern r = Pattern.compile("(\\d+\\-\\d+\\-\\d+ \\d+:\\d+)");

		// 现在创建 matcher 对象
		Matcher m = r.matcher(line);
		if (m.find()) {
			System.out.println(m.group(0));
			System.out.println(m.group(1));
			
			String dateTimeStr = m.group(0);
			SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm");
			Date date = null;
			try {
				date = sdf.parse(dateTimeStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			System.out.println(date);
			
		}
		
				
	}
}
