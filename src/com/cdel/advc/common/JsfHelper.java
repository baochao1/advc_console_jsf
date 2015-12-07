/*
 * @Title: JsfHelper.java
 * @Package com.cdel.advc.common
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-6-28 上午10:16:23
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-6-28                          
 */
package com.cdel.advc.common;

import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * jsf帮助类
 * </p>
 * 
 * @author Du Haiying Create at:2013-6-28 上午10:16:23
 */
public class JsfHelper {

	public static ExternalContext getExternalContext(FacesContext context) {
		return context.getExternalContext();
	}

	/**
	 * 获取request对象
	 */
	public static HttpServletRequest getRequest(FacesContext context) {
		return (HttpServletRequest) getExternalContext(context).getRequest();
	}

	/**
	 * 获取response对象
	 */
	public static HttpServletResponse getResponse(FacesContext context) {
		return (HttpServletResponse) getExternalContext(context).getResponse();
	}

	/**
	 * 获取参数map
	 * 
	 * @param context
	 * @return
	 */
	public static Map<String, String> getRequestParameterMap(
			FacesContext context) {
		return getExternalContext(context).getRequestParameterMap();
	}

	/**
	 * 获取session对象
	 */
	public static HttpSession getSession(FacesContext context) {
		return getRequest(context).getSession();
	}

	/**
	 * 获取request请求参数(返回String)
	 */
	public static String getParameter(FacesContext context, String name) {
		return getRequest(context).getParameter(name);
	}

	/**
	 * 获取request请求参数(返回Integer)
	 */
	public static Integer getIntegerParameter(FacesContext context, String name) {
		String tmp = getParameter(context, name);
		if (StringUtils.isNotBlank(tmp) && StringUtils.isNumeric(tmp)) {
			return Integer.parseInt(tmp);
		} else {
			return null;
		}
	}

}
