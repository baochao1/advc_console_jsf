package com.cdel.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class CookieUtil {
	public static String path = "/";
	public static int validAge = 10 * 24 * 3600;

	/**
	 * 添加cookie
	 * 
	 * @param response
	 * @param cookieName
	 * @param cookieVal
	 */
	public static void addCookie(HttpServletResponse response,
			String cookieName, String cookieVal) {
		Cookie cookieCourse = new Cookie(cookieName, cookieVal);
		cookieCourse.setPath(path);
		cookieCourse.setMaxAge(validAge);
		response.addCookie(cookieCourse);
	}

	/**
	 * 删除cookie
	 * 
	 * @param response
	 * @param cookieName
	 */
	public static void delCookie(HttpServletResponse response, String cookieName) {
		Cookie cookieCourse = new Cookie(cookieName, null);
		cookieCourse.setPath(path);
		cookieCourse.setMaxAge(0);
		response.addCookie(cookieCourse);
	}

	/**
	 * 获取cookie
	 * 
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static String getCookie(HttpServletRequest request, String cookieName) {
		String cookieValue = "";
		Cookie cookies[] = null;
		Cookie sCookie = null;
		String sname = null;
		cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				sCookie = cookies[i];
				sname = sCookie.getName();
				if (!sname.equals(cookieName)) {
					continue;
				}

				if (sname.equals(cookieName)) {
					cookieValue = sCookie.getValue();
					try {
						cookieValue = URLDecoder.decode(cookieValue,
								Contants.CODE);
					} catch (UnsupportedEncodingException e) {
						return null;
					}
					if (cookieValue == null)
						break;
					if (cookieValue.trim().length() == 0)
						cookieValue = null;
					break;
				}

			}
		}
		return cookieValue;
	}

	public static Integer getCookieInt(HttpServletRequest request,
			String cookieName) {
		String val = getCookie(request, cookieName);
		try {
			if (StringUtils.isNotBlank(val)) {
				return Integer.parseInt(val);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
