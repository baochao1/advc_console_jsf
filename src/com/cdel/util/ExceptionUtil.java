package com.cdel.util;

/**
 * 打印异常信息 author：zsl
 */
public class ExceptionUtil {
	public static String getEMsg(Exception e) {
		StringBuffer sb = new StringBuffer();
		sb.append(e).append("\r\n");
		StackTraceElement[] stackTraceElement = e.getStackTrace();
		if (stackTraceElement != null && stackTraceElement.length > 0) {
			for (int i = 0; i < stackTraceElement.length; i++) {
				StackTraceElement st = stackTraceElement[i];
				sb.append(st.toString()).append("\r\n");
			}
		}
		return sb.toString();
	}
}
