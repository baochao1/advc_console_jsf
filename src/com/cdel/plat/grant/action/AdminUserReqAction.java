package com.cdel.plat.grant.action;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.apache.commons.lang.StringUtils;

import com.cdel.plat.grant.domain.AdminUser;
import com.cdel.plat.grant.facade.AdminUserFacade;
import com.cdel.util.BaseAction;

/**
 * 个人中心
 * 
 * @author 张苏磊
 * 
 */
@ManagedBean
@SuppressWarnings("serial")
public class AdminUserReqAction extends BaseAction<AdminUser> implements
		Serializable {

	@ManagedProperty(value = "#{adminUserFacade}")
	private AdminUserFacade adminUserFacade;

	private String passWd;// 旧密码
	private byte submitSuccess = 0;// 修改是否成功

	/**
	 * 更新个人信息
	 */
	public void updateSubmit() {
		AdminUserAction adminUserAction = (AdminUserAction) this
				.getViewAction("adminUserAction");
		try {
			AdminUser au = adminUserAction.getAdminUser();
			if (StringUtils.isBlank(au.getRealName())) {
				this.addWarnMessage("info", "真实姓名不能为空!");
				return;
			}
			adminUserFacade.update(au);
			submitSuccess = 1;
		} catch (Exception e) {
			e.printStackTrace();
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 更新密码
	 */
	public void updatePass() {
		AdminUserAction adminUserAction = (AdminUserAction) this
				.getViewAction("adminUserAction");
		try {
			AdminUser au = adminUserAction.getAdminUser();
			if (adminUserFacade.checkPass(passWd, au)) {
				au.setPassWd(au.getNewPassWd());
				adminUserFacade.update(au);
				submitSuccess = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public void setAdminUserFacade(AdminUserFacade adminUserFacade) {
		this.adminUserFacade = adminUserFacade;
	}

	public String getPassWd() {
		return passWd;
	}

	public void setPassWd(String passWd) {
		this.passWd = passWd;
	}

}
