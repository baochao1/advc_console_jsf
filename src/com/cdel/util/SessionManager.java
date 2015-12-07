package com.cdel.util;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

/**
 * @author zhangsulei 修改皮肤
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class SessionManager extends BaseNoLoginAction<SessionManager> implements
		Serializable {

	private String theme = JsfUtil.DEFAULT_THEME;
	private String cookieName = "jsftheme";

	public void setTheme(String theme) {
		if (theme == null)
			theme = JsfUtil.DEFAULT_THEME;

		if (this.theme.equals(theme))
			return;

		this.theme = theme;

	}

	/**
	 * 保存当前主题到cookie
	 */
	public void saveTheme() {
		CookieUtil.addCookie(this.getResponse(), cookieName, theme);
		this.addInfoMessage("info", "刷新后，可以看到整体效果！");
		logger.info(this.getCurrentUserName() + "做了切换主题");
	}

	public String getTheme() {
		String s = CookieUtil.getCookie(this.getRequest(), cookieName);
		if (StringUtils.isBlank(s)) {
			return theme;
		}
		return s;
	}

	public List<Theme> getThemes() {
		return JsfUtil.THEMES;
	}

}
