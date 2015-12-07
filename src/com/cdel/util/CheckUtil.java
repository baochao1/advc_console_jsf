package com.cdel.util;

import java.util.regex.Pattern;

/**
 * 各种验证
 * 
 * @author 张苏磊
 * 
 */
public class CheckUtil {

	/**
	 * 验证几位整数
	 * 
	 * @param str
	 * @param wei位数
	 * @return
	 */
	public static boolean checkInt(String str, int wei) {
		Pattern pattern;
		if (wei > 0)
			pattern = Pattern.compile("[0-9]{1," + wei + "}");
		else
			pattern = Pattern.compile("[0-9]{1,}");
		return pattern.matcher(str).matches();
	}

	/**
	 * 验证几位小数
	 * 
	 * @param str
	 * @param intwei整数位数
	 * @param wei小数位数
	 * @return
	 */
	public static boolean checkFloat(String str, int intwei, int wei) {
		Pattern pattern = Pattern.compile("[0-9]{1," + intwei + "}.[0-9]{1,"
				+ wei + "}");
		return pattern.matcher(str).matches();
	}

	/**
	 * 验证整数或几位小数
	 * 
	 * @param str
	 * @param intwei整数位数
	 * @param wei小数位数
	 * @return
	 */
	public static boolean checkFloat2(String str, int intwei, int wei) {
		Pattern pattern = Pattern.compile("[0-9]{1," + intwei
				+ "}.{0,1}[0-9]{0," + wei + "}");
		return pattern.matcher(str).matches();
	}

	/**
	 * 验证url地址
	 * 
	 * @param str
	 * @return
	 */
	public static boolean checkUrl(String str) {
		Pattern pattern = Pattern
				.compile("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?");
		return pattern.matcher(str).matches();
	}

	/**
	 * 验证密码
	 * 
	 * @param str
	 * @return
	 */
	public static boolean checkPass(String str) {
		Pattern pattern = Pattern.compile("^[0-9a-zA-Z]{4,16}$");
		return pattern.matcher(str).matches();
	}

}
