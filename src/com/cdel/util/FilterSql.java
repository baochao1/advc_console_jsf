package com.cdel.util;

import java.util.logging.Logger;
import java.util.regex.Pattern;

public class FilterSql {
	private static Logger log = Logger.getLogger(FilterSql.class.getName());

	static String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
			+ "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";

	static Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);

	/***************************************************************************
	 * 参数校验
	 * 
	 * @param 未通过返回
	 *            -1
	 */
	public static String isValid(String str) {

		if (sqlPattern.matcher(str).find()) {

			log.info("未能通过过滤器：str=" + str);

			return "-1";
		}
		return str;
	}

	/**
	 * @param str
	 * @return 未通过返回false
	 */
	public static boolean isValidRB(String str) {

		if (sqlPattern.matcher(str).find()) {

			log.info("未能通过过滤器：str=" + str);

			return false;
		}
		return true;
	}

}
