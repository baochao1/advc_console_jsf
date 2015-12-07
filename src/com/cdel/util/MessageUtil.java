package com.cdel.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * messgae处理类
 */
public class MessageUtil {
	/**
	 * 添加message
	 * 
	 * @param msg
	 * @param context
	 * @param str
	 */
	public static void addMessage(FacesMessage msg, FacesContext context,
			String str) {
		msg.setSummary(str);
		context.addMessage(null, msg);
	}

	/**
	 * 给指定name添加message
	 * 
	 * @param name
	 * @param msg
	 * @param context
	 * @param str
	 */
	public static void addMessage(String name, FacesMessage msg,
			FacesContext context, String str) {
		msg.setSummary(str);
		context.addMessage(name, msg);
	}

}
