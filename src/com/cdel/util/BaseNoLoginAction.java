package com.cdel.util;

import java.io.Serializable;
import java.net.URLDecoder;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;
import com.cdel.advc.common.JsfHelper;
import com.cdel.advc.teacher.domain.Teacher;
import com.chnedu.plat.rad.util.RequestHandler;

/**
 * @author dell 只有操作权限和查看权限定义
 */
@SuppressWarnings("serial")
public class BaseNoLoginAction<T> implements Serializable {

	protected final Log logger = LogFactory.getLog(getClass());

	protected String SUCESSINFO = "修改成功！";
	protected String FAILINFO = "数据错误,操作失败！";

	/** 得到当前请求中的参数 */
	protected String getParameter(String name) {
		return JsfHelper.getParameter(FacesContext.getCurrentInstance(), name);
	}

	/** 得到当前请求中的参数 */
	protected String getParameterDecode(String name) {
		String va = getParameter(name);
		if (StringUtils.isBlank(va)) {
			return "";
		} else {
			try {
				return URLDecoder.decode(va, Contants.CODE);
			} catch (Exception e) {
				return "";
			}
		}
	}

	/** 得到当前请求中的参数 */
	protected Integer getIntegerParameter(String name) {
		return JsfHelper.getIntegerParameter(FacesContext.getCurrentInstance(),
				name);
	}

	/** 得到当前用户ID */
	protected Integer getCurrentUserID() {
		return RequestHandler.getCurrentAdmin(JsfHelper.getRequest(FacesContext
				.getCurrentInstance()));
	}

	/** 得到当前用户登录名 */
	protected String getCurrentUserName() {
		return getAttribute(Contants.ADMIN_USER_NAME);
	}

	/** 得到当前用户真实名字 */
	protected String getCurrentRealName() {
		return getAttribute(Contants.ADMIN_REAL_NAME);
	}

	/** 得到session信息 */
	protected String getAttribute(String name) {
		return (String) JsfHelper.getSession(FacesContext.getCurrentInstance())
				.getAttribute(name);
	}

	/** 置session信息 */
	protected void setAttribute(String name, Object value) {
		JsfHelper.getSession(FacesContext.getCurrentInstance()).setAttribute(
				name, value);
	}

	/** 得到当前网站ID */
	protected Integer getCurrentSiteID() {
		return JsfHelper.getIntegerParameter(FacesContext.getCurrentInstance(),
				"siteID");
	}

	/**
	 * 前台返回参数
	 * 
	 * @param name
	 * @param value
	 */
	protected void addCallbackParam(String name, Object value) {
		RequestContext rcontext = RequestContext.getCurrentInstance();
		rcontext.addCallbackParam(name, value);
	}

	/**
	 * 添加前台提示
	 * 
	 * @param id
	 * @param info
	 */
	protected void addMessage(String id, String info) {
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		MessageUtil.addMessage(id, message, FacesContext.getCurrentInstance(),
				info);
	}

	/**
	 * 添加前台提示
	 * 
	 * @param id
	 * @param info
	 */
	protected void addMessage(String info) {
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		MessageUtil
				.addMessage(message, FacesContext.getCurrentInstance(), info);
	}

	/**
	 * 添加前台提示--正常提示
	 * 
	 * @param id
	 * @param info
	 */
	protected void addInfoMessage(String info) {
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		MessageUtil
				.addMessage(message, FacesContext.getCurrentInstance(), info);
	}

	protected void addInfoMessage(String id, String info) {
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		MessageUtil.addMessage(id, message, FacesContext.getCurrentInstance(),
				info);
	}

	/**
	 * 添加前台提示--一般警告提示
	 * 
	 * @param id
	 * @param info
	 */
	protected void addWarnMessage(String info) {
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_WARN);
		MessageUtil
				.addMessage(message, FacesContext.getCurrentInstance(), info);
	}

	protected void addWarnMessage(String id, String info) {
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_WARN);
		MessageUtil.addMessage(id, message, FacesContext.getCurrentInstance(),
				info);
	}

	/**
	 * 添加前台提示--一般错误提示
	 * 
	 * @param id
	 * @param info
	 */
	protected void addErrorMessage(String info) {
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		MessageUtil
				.addMessage(message, FacesContext.getCurrentInstance(), info);
	}

	protected void addErrorMessage(String id, String info) {
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		MessageUtil.addMessage(id, message, FacesContext.getCurrentInstance(),
				info);
	}

	/**
	 * 更新页面组件
	 * 
	 * @param id
	 */
	protected void updateComponent(String id) {
		RequestContext rcontext = RequestContext.getCurrentInstance();
		rcontext.update(id);
	}

	/**
	 * 请求完成后页面执行可以执行的脚本
	 */
	protected void executeScript(String script) {
		RequestContext rcontext = RequestContext.getCurrentInstance();
		rcontext.execute(script);
	}

	protected HttpServletRequest getRequest() {
		return JsfHelper.getRequest(FacesContext.getCurrentInstance());
	}

	protected HttpServletResponse getResponse() {
		return JsfHelper.getResponse(FacesContext.getCurrentInstance());
	}

	protected HttpSession getSession() {
		return JsfHelper.getSession(FacesContext.getCurrentInstance());
	}

	protected String getRequestParameterByMap(String name) {
		return JsfHelper.getRequestParameterMap(
				FacesContext.getCurrentInstance()).get(name);
	}

	protected Integer getIntegerRequestParameterByMap(String name) {
		String val = JsfHelper.getRequestParameterMap(
				FacesContext.getCurrentInstance()).get(name);
		if (StringUtils.isNotBlank(val) && StringUtils.isNumeric(val)) {
			return Integer.parseInt(val);
		} else {
			return null;
		}
	}

	/** 得到当前班级管理员 */
	protected Teacher getCurrentTeacher() {
		return (Teacher) JsfHelper
				.getSession(FacesContext.getCurrentInstance()).getAttribute(
						Contants.CLASS_TEACHER);
	}

}
