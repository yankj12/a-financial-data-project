package com.yan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestDate {

	public static void main(String[] args) throws Exception {
		String line = "2020年3月4日";
		
		findDateStrByRegex(line);
	}

	public static void test() {
		//1583289827074
		System.out.println(System.currentTimeMillis());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(1577059200L);
		System.out.println(sdf.format(date));
	}
	
	public static void test2(String line) {
		Pattern r = Pattern.compile("[\\u4e00-\\u9fa5]+");
		String[] str=r.split(line);
		System.out.println(Arrays.toString(str));
	}
	
	
	public static Date findDateTimeByRegex(String line) {
		Date date = null;
		
		//2020年3月4日
		Pattern r = Pattern.compile("(\\d+).*?(\\d+).*?(\\d+)");

		// 现在创建 matcher 对象
		Matcher m = r.matcher(line);
		if (m.find()) {
			System.out.println(m.group(0));
			System.out.println(m.group(1));
			System.out.println(m.group(2));
			System.out.println(m.group(3));
			
		}
		return date;
	}
	
	public static String findDateStrByRegex(String line) {
		
		//2020年3月4日
		Pattern r = Pattern.compile("\\d+");

		// 现在创建 matcher 对象
		Matcher m = r.matcher(line);
		int index = 0;
		StringBuilder dateStrBuilder = new StringBuilder();
		while (m.find()) {
			if(index > 0) {
				dateStrBuilder.append("-");
			}
			String numStr = m.group();
			
			if(index == 0 && numStr.length() == 4) {
				dateStrBuilder.append(numStr);
			}else if(index > 0 && numStr.length() == 1) {
				dateStrBuilder.append("0").append(numStr);
			}else if(index > 0 && numStr.length() == 2) {
				dateStrBuilder.append(numStr);
			}else {
				throw new RuntimeException("日期格式不正确");
			}
			index++;
		}
		System.out.println(dateStrBuilder.toString());
		return dateStrBuilder.toString();
	}
	
	public static Date findDateByRegex(String line) throws ParseException {
		Date date = null;
		
		//2020年3月4日
		Pattern r = Pattern.compile("\\d+");

		// 现在创建 matcher 对象
		Matcher m = r.matcher(line);
		int index = 0;
		StringBuilder dateStrBuilder = new StringBuilder();
		while (m.find()) {
			if(index > 0) {
				dateStrBuilder.append("/");
			}
			String numStr = m.group();
			dateStrBuilder.append(numStr);
			index++;
		}
		//System.out.println(dateStrBuilder.toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/d");
		date = sdf.parse(dateStrBuilder.toString());
		return date;
	}
}
