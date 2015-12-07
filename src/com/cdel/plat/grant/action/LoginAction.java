package com.cdel.plat.grant.action;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import com.cdel.advc.teacher.facade.TeacherFacade;
import com.cdel.plat.grant.domain.AdminUser;
import com.cdel.plat.grant.facade.AdminUserFacade;
import com.cdel.plat.grant.facade.PrivilegeFacade;
import com.cdel.plat.grant.facade.SaveWebSite;
import com.cdel.util.BaseNoLoginAction;
import com.cdel.util.CheckUtil;
import com.cdel.util.Contants;
import com.cdel.util.CookieUtil;
import com.chnedu.plat.grant.util.DESPlus;
import com.chnedu.plat.rad.domain.BaseDomain;
import com.chnedu.plat.rad.util.net.ProxyUtil;

/**
 * Class description goes here.
 * 
 * @version 1.0 2008-1-28
 * @author LiXuFang - j2eeli@chinaacc.com
 */

@ManagedBean
@SuppressWarnings("serial")
public class LoginAction extends BaseNoLoginAction<LoginAction> implements
		Serializable {
	private String userName;
	private String password;
	// 新密码
	private String newPass;
	// 确认新密码
	private String confirmNewPass;
	private String loginValidate;
	private Integer pwdChangeSpace = 15;
	@ManagedProperty(value = "#{privilegeFacade}")
	private PrivilegeFacade privilegeFacade;
	@ManagedProperty(value = "#{adminUserFacade}")
	private AdminUserFacade adminUserFacade;
	@ManagedProperty(value = "#{saveWebSite}")
	private SaveWebSite saveWebSite;
	@ManagedProperty(value = "#{teacherFacade}")
	private TeacherFacade teacherFacade;
	// 站点ID
	private Integer siteID;
	private String secret;// 自动登录标志
	private String alertDiv;

	/* 登陆 */
	public String login() throws Exception {
		HttpServletRequest request = this.getRequest();
		Date date = new Date();
		HttpSession session = request.getSession();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd*HH:mm:ss");
		DESPlus des = new DESPlus();
		int flag = 0;
		if (secret != null) {
			secret = des.decrypt(secret);
			String[] str = secret.split(",");
			if (str.length == 2) {
				if ("N".equals(str[0])) {
					Date d = sdf.parse(str[1]);
					Date now = new Date();
					long space = now.getTime() - d.getTime();
					if (space < 43200000) {
						flag = 1;
					}
				}
			}
		}
		AdminUser u = null;
		if (flag == 0) {
			String validateNumber = (String) session.getAttribute(Contants.YZ);
			if (validateNumber == null || loginValidate == null
					|| !loginValidate.equals(validateNumber)) {
				request.setAttribute("msg", "验证码错误");
				return "login";
			}
			// 不删除验证码session，可保证刷新时不退出
			// session.removeAttribute(Contants.YZ);
			AdminUser user = new AdminUser();
			user.setAdminName(userName);
			user.setPassWd(password);

			u = adminUserFacade.getUserInfo(user);
		} else if (flag == 1) {
			u = adminUserFacade.getUserByUserName(userName);
		}
		if (u == null) {
			request.setAttribute("msg", "您的用户名或密码错误");
			return "login";
		} else {
			AdminUser au = new AdminUser();
			au.setAdminID(u.getAdminID());
			au.setLastLogin(new Date());
			au.setLoginIP(ProxyUtil.getRemoteIp(request));
			adminUserFacade.updateUserLoginMsgByID(au);

			if (flag == 0 && pwdChangeSpace != null) {
				if (u.getPwdModifyTime() == null) {
					request.setAttribute("msg", "管理员为您重置过密码，为安全起见，请您重新设定的密码！");
					request.setAttribute("adminID", u.getAdminID());
					return "/page/grant/compelUpdatePwd";
				} else {
					if ((u.getPwdModifyTime().getTime() + (pwdChangeSpace * 86400000l)) < new Date()
							.getTime()) {
						request.setAttribute("msg", "距离上次密码修改已经超过"
								+ pwdChangeSpace + "天，为安全起见，请您重新设置密码！");
						request.setAttribute("adminID", u.getAdminID());
						return "/page/grant/compelUpdatePwd";
					}
				}
			}

			Integer adminID = u.getAdminID();
			List<Integer> sl = adminUserFacade.getUserRoleID(adminID);
			boolean isSuper = false;
			if (sl != null && sl.size() > 0) {
				for (Integer roleID : sl) {
					if (roleID.equals(1)) {
						isSuper = true;
						break;
					}
				}
			}
			String currentTime = sdf.format(date);
			String source = "N," + currentTime;
			try {
				String desStr = des.encrypt(source);

				session.setAttribute(Contants.ADMIN_USER_ID, adminID);
				if (isSuper)
					session.setAttribute(Contants.ADMIN_USER_IS_SUPER, "Y");
				session.setAttribute(Contants.ADMIN_USER_ROLES, sl);
				session.setAttribute(Contants.ADMIN_REAL_NAME, u.getRealName());
				session.setAttribute(Contants.ADMIN_USER_NAME, u.getAdminName());
				session.setAttribute(Contants.ADMIN_PASSWORD, desStr);
				session.setAttribute(Contants.CLASS_TEACHER,
						this.teacherFacade.getTeacherAndOrg(adminID));// 班级管理员
				session.setAttribute(Contants.ADMIN_USER_AUTH, privilegeFacade
						.loadAUTH(Contants.defSystemType, adminID, sl));// 查询权限
			} catch (Exception e) {
				logger.error("错误：source=" + source);
			}
			// 判断是否选过课程信息
			Cookie[] cookies = request.getCookies();
			Boolean flag1 = false;
			Boolean websiteFlag = false;
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					Cookie c = cookies[i];
					if (c.getName().equals(Contants.COOKIENAME_WEBSITE)) {
						websiteFlag = true;
						String defaultSite = c.getValue();
						if (defaultSite != null && defaultSite != "") {
							siteID = Integer.parseInt(defaultSite);
							int result = saveWebSite.saveWebSiteToSession(
									siteID, session);
							if (result == 1) {
								flag1 = true;
							}
						}
					}
					if (c.getName().equals(Contants.COOKIENAME_COURSE)) {
						String defaultCourse = c.getValue();
						if (defaultCourse != null && defaultCourse != "") {
							Integer courseID = Integer.parseInt(defaultCourse);
							saveWebSite.saveCourseToSession(courseID, session);
						}
					}
				}
			}
			if (websiteFlag & flag1) {
				alertDiv = "N";
			}
			return "/page/grant/busMain";
		}
	}

	/**
	 * 退出
	 * 
	 * @return
	 */
	public String logout() {
		HttpSession session = this.getSession();
		session.invalidate();
		return "/login";
	}

	/**
	 * 定时刷新
	 * 
	 * @return
	 * @throws Exception
	 */
	public void keepSession() throws Exception {
		System.out.println("刷新后台!");
	}

	/**
	 * 定时修改密码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String compelUpdatePwd() throws Exception {
		HttpServletRequest request = this.getRequest();
		if (!CheckUtil.checkPass(newPass)) {
			request.setAttribute("msg", "请输入合法的新密码");
			return "/page/grant/compelUpdatePwd";
		}
		if (!CheckUtil.checkPass(confirmNewPass)) {
			request.setAttribute("msg", "请输入合法的新密码");
			return "/page/grant/compelUpdatePwd";
		}
		if (!newPass.equals(confirmNewPass)) {
			request.setAttribute("msg", "两次新密码的输入必须一致");
			return "/page/grant/compelUpdatePwd";
		}
		AdminUser user = adminUserFacade.getUserByUserName(userName);
		if (!user.getPassWd().equals(password)) {
			request.setAttribute("msg", "旧密码不对");
			return "/page/grant/compelUpdatePwd";
		} else if (user.getPassWd().equals(newPass)) {
			request.setAttribute("msg", "为安全起见，请不要设置与上次相同的密码！");
			return "/page/grant/compelUpdatePwd";
		} else {
			AdminUser au = new AdminUser();
			au.setAdminID(user.getAdminID());
			au.setPassWd(newPass);
			au.setPwdModifyTime(new Date());
			adminUserFacade.update(au);
		}
		return "/login";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginValidate() {
		return loginValidate;
	}

	public void setLoginValidate(String loginValidate) {
		this.loginValidate = loginValidate;
	}

	public void setPrivilegeFacade(PrivilegeFacade privilegeFacade) {
		this.privilegeFacade = privilegeFacade;
	}

	public void setSaveWebSite(SaveWebSite saveWebSite) {
		this.saveWebSite = saveWebSite;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public String getConfirmNewPass() {
		return confirmNewPass;
	}

	public void setConfirmNewPass(String confirmNewPass) {
		this.confirmNewPass = confirmNewPass;
	}

	public void setAdminUserFacade(AdminUserFacade adminUserFacade) {
		this.adminUserFacade = adminUserFacade;
	}

	public Integer getSiteID() {
		if (siteID == null) {
			siteID = BaseDomain.baseSiteID;
		}
		return siteID;
	}

	public void setSiteID(Integer siteID) {
		this.siteID = siteID;
	}

	public void setTeacherFacade(TeacherFacade teacherFacade) {
		this.teacherFacade = teacherFacade;
	}

	public String getAlertDiv() {
		return alertDiv;
	}

	public void setAlertDiv(String alertDiv) {
		this.alertDiv = alertDiv;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
		if (StringUtils.isNotBlank(secret)) {
			HttpServletRequest request = this.getRequest();
			request.setAttribute("autoFlag", "1");
		}
	}

}