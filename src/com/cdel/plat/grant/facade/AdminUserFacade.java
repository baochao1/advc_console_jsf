package com.cdel.plat.grant.facade;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.cdel.plat.grant.domain.AdminUser;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.CheckUtil;

/**
 * Class description goes here.
 * 
 * @version 1.0 2008-1-17
 * @author LiXuFang - j2eeli@chinaacc.com
 */
@SuppressWarnings("serial")
@Service
public class AdminUserFacade extends BaseFacadeImpl<AdminUser, Integer>
		implements Serializable {
	/**
	 * 获取用户
	 * 
	 * @param user
	 * @return
	 */
	public AdminUser getUserInfo(AdminUser user) {
		return baseDao.get(user, "getUserInfo");
	}

	/**
	 * 根据用户名获取用户
	 * 
	 * @param userName
	 * @return
	 */
	public AdminUser getUserByUserName(String userName) {
		return baseDao.get(userName, "getUserByUserName");
	}

	/**
	 * 更新用户登录时间
	 * 
	 * @param user
	 */
	public void updateUserLoginMsgByID(AdminUser user) {
		baseDao.update(user, "updateUserLoginMsgByID");
	}

	/**
	 * 获取用户角色ID的List
	 * 
	 * @param adminID
	 * @return
	 */
	public List<Integer> getUserRoleID(Integer adminID) {
		return baseDao.findSomeList(adminID, "getUserRoleID");
	}

	public boolean checkPass(String passWd, AdminUser au) {
		String pass = au.getPassWd();
		String newpass = au.getNewPassWd();
		String connewpass = au.getConNewPassWd();
		if (StringUtils.isBlank(passWd) || StringUtils.isBlank(newpass)
				|| StringUtils.isBlank(connewpass)) {
			return this.addWarnMessage("info", "三个密码必须都输入！");
		}
		if (!CheckUtil.checkPass(pass)) {
			return this.addWarnMessage("info", "请输入合法的旧密码！");
		}
		if (!CheckUtil.checkPass(newpass)) {
			return this.addWarnMessage("info", "请输入合法的新密码！");
		}
		if (!CheckUtil.checkPass(connewpass)) {
			return this.addWarnMessage("info", "请输入合法的确认密码！");
		}
		if (!passWd.equals(pass)) {
			return this.addWarnMessage("info", "旧密码输入错误！");
		}
		if (pass.equals(newpass)) {
			return this.addWarnMessage("info", "旧密码不能和新密码一样！");
		}
		if (!newpass.equals(connewpass)) {
			return this.addWarnMessage("info", "新密码和确认密码不一致！");
		}
		return true;
	}

}
