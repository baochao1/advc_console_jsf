package com.cdel.api.ext;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang.StringUtils;

import com.cdel.advc.divide.domain.Divide;
import com.cdel.plat.grant.facade.LoginFacade;
import com.cdel.util.BaseNoLoginAction;

/**
 * @author 张苏磊
 * @desc 模拟登录
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class DivideApiAction extends BaseNoLoginAction<Divide> implements
		Serializable {

	@ManagedProperty(value = "#{loginFacade}")
	private LoginFacade loginFacade;

	private String operator;
	private Integer siteID;// 网站ID
	private String userName;// 网站ID
	private Integer userID;

	public void autoLogin() {
		if (StringUtils.isBlank(operator) || StringUtils.isBlank(userName)
				|| siteID == null) {
			this.addErrorMessage("info", "参数不全！");
		}
		int result = loginFacade.loginSet2(operator, this.getSession());
		if (result == -1) {
			this.addErrorMessage("info", "对不起，该操作员用户不存在！");
		}
		this.addCallbackParam("result", result);
	}

	public void setLoginFacade(LoginFacade loginFacade) {
		this.loginFacade = loginFacade;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Integer getSiteID() {
		return siteID;
	}

	public void setSiteID(Integer siteID) {
		this.siteID = siteID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

}
