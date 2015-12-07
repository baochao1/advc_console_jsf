package com.cdel.util;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import com.cdel.advc.common.JsfHelper;
import com.cdel.advc.teacher.domain.Teacher;
import com.chnedu.plat.rad.util.RequestHandler;

/**
 * @author dell 只有操作权限和查看权限定义
 */
@SuppressWarnings("serial")
public class BaseAction<T> implements Serializable {

	protected final Log logger = LogFactory.getLog(getClass());

	// 操作权限
	private boolean optAuth = false;
	// 查看权限
	private boolean viewAuth = false;

	protected String SUCESSINFO = "修改成功！";
	protected String FAILINFO = "数据错误,操作失败！";
	protected String height;// tab高度
	protected String heighti = "300";// tab高度
	protected String heighti2 = "280";// tab高度(带按钮栏)

	/** DataTable 分页数据表格组件 */
	protected DataTable pageTable;

	@SuppressWarnings("unchecked")
	public BaseAction() {
		Class<T> entityClass = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		String className = entityClass.getSimpleName().toLowerCase();
		setViewAuth(hasAuth("/" + className + Contants.VIEW));
		setOptAuth(hasAuth("/" + className + Contants.OPT));
		if (getAttribute("height") == null) {
			height = getParameter("height");
			if (StringUtils.isNotBlank(height)) {
				setAttribute("height", height);
			}
		} else {
			height = getAttribute("height");
		}
		if (StringUtils.isNotBlank(height)) {
			heighti = calHeight(13.0f / 20);
		}
	}

	protected String calHeight(float percent) {
		float f = Float.parseFloat(height);
		return (int) (f * percent) + "";
	}

	public boolean isOptAuth() {
		return optAuth;
	}

	public void setOptAuth(boolean optAuth) {
		this.optAuth = optAuth;
	}

	public boolean isViewAuth() {
		return viewAuth;
	}

	public void setViewAuth(boolean viewAuth) {
		this.viewAuth = viewAuth;
	}

	@SuppressWarnings("unchecked")
	protected boolean hasAuth(String actionURL) {
		FacesContext context = FacesContext.getCurrentInstance();
		return authenticate((List<String>) JsfHelper.getSession(context)
				.getAttribute(Contants.ADMIN_USER_AUTH), actionURL);
	}

	private boolean authenticate(List<String> authMap, String action) {
		if (authMap == null) {
			return false;
		}
		return authMap.contains(action);
	}

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

	protected HttpSession getSession() {
		return JsfHelper.getSession(FacesContext.getCurrentInstance());
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

	/**
	 * 返回viewAction
	 * 
	 * @param actionName
	 * @return
	 */
	protected Object getViewAction(String actionName) {
		Map<String, Object> viewMap = FacesContext.getCurrentInstance()
				.getViewRoot().getViewMap();
		return viewMap.get(actionName);
	}

	/** 得到当前班级管理员 */
	protected Teacher getCurrentTeacher() {
		return (Teacher) JsfHelper
				.getSession(FacesContext.getCurrentInstance()).getAttribute(
						Contants.CLASS_TEACHER);
	}

	protected HttpServletRequest getRequest() {
		return JsfHelper.getRequest(FacesContext.getCurrentInstance());
	}

	protected HttpServletResponse getResponse() {
		return JsfHelper.getResponse(FacesContext.getCurrentInstance());
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

	/**
	 * 返回编辑的行
	 * 
	 * @param event
	 * @return
	 */
	protected Object getEditRow(CellEditEvent event) {
		DataTable d = (DataTable) event.getSource();
		return d.getRowData();
	}

	/**
	 * 返回编辑的行
	 * 
	 * @param event
	 * @return
	 */
	protected Object getEditRow(RowEditEvent event) {
		DataTable d = (DataTable) event.getSource();
		return d.getRowData();
	}

	public DataTable getPageTable() {
		return pageTable;
	}

	public void setPageTable(DataTable pageTable) {
		this.pageTable = pageTable;
	}

	public String getHeighti() {
		return heighti;
	}

	public String getHeighti2() {
		return heighti2;
	}

}
