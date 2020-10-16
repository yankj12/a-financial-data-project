package com.yan;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestNum {

	public static void main(String[] args) {
		System.out.println(isDecimal("--"));
		System.out.println(isDecimal("23.35B"));
		System.out.println(isDecimal("23.35"));
		System.out.println(new BigDecimal("1.04%"));
		
	}

	public static boolean isDecimal(String line) {
		Pattern r = Pattern.compile("(\\d+\\.\\d+)");

		// 现在创建 matcher 对象
		Matcher m = r.matcher(line);
		
		return m.matches();
	}
}
