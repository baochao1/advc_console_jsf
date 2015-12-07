package com.cdel.plat.grant.facade;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdel.advc.teacher.facade.TeacherFacade;
import com.cdel.plat.grant.domain.AdminUser;
import com.cdel.util.Contants;

/**
 * @author dell 模拟登录
 */
@Service
public class LoginFacade {
	@Autowired
	private AdminUserFacade adminUserFacade;
	@Autowired
	private PrivilegeFacade privilegeFacade;
	@Autowired
	private TeacherFacade teacherFacade;
	@Autowired
	private AdminRoleFacade adminRoleFacade;

	/**
	 * 模拟登录
	 * 
	 * @param courseID
	 */
	public int loginSet(String userName, HttpSession session) {
		Integer memberID = (Integer) session
				.getAttribute(Contants.ADMIN_USER_ID);
		if (memberID == null) {
			AdminUser user = new AdminUser();
			user.setAdminName(userName);
			AdminUser u = adminUserFacade.getUserByUserName(userName);
			if (u == null) {
				return -1;
			} else {
				Integer adminID = u.getAdminID();
				List<Integer> sl = adminUserFacade.getUserRoleID(adminID);
				session.setAttribute(Contants.ADMIN_USER_ID, adminID);
				session.setAttribute(Contants.ADMIN_REAL_NAME, u.getRealName());
				session.setAttribute(Contants.ADMIN_USER_NAME, u.getAdminName());
				session.setAttribute(Contants.ADMIN_USER_ROLES, sl);
				session.setAttribute(Contants.CLASS_TEACHER,
						this.teacherFacade.getTeacherAndOrg(adminID));// 班级管理员
				session.setAttribute(Contants.ADMIN_USER_AUTH, privilegeFacade
						.loadAUTH(Contants.defSystemType, adminID, sl));// 查询权限
				return 1;
			}
		}
		return 1;
	}

	/**
	 * 模拟登录，给客服分班用
	 * 
	 * @param courseID
	 */
	public int loginSet2(String userName, HttpSession session) {
		Integer memberID = (Integer) session
				.getAttribute(Contants.ADMIN_USER_ID);
		if (memberID == null) {
			AdminUser user = new AdminUser();
			user.setAdminName(userName);
			AdminUser u = adminUserFacade.getUserByUserName(userName);
			if (u == null) {
				// 没有用户，模拟登录
				Integer adminID = -1;
				List<Integer> sl = new ArrayList<Integer>();
				sl.add(adminRoleFacade
						.getRoleIDByName(Contants.DIVIDE_ROLENAME));
				session.setAttribute(Contants.ADMIN_USER_ID, adminID);
				session.setAttribute(Contants.ADMIN_REAL_NAME,
						Contants.DIVIDE_ROLENAME);
				session.setAttribute(Contants.ADMIN_USER_NAME, userName);
				session.setAttribute(Contants.ADMIN_USER_ROLES, sl);
				session.setAttribute(Contants.ADMIN_USER_AUTH, privilegeFacade
						.loadAUTH(Contants.defSystemType, adminID, sl));// 查询权限
			} else {
				Integer adminID = u.getAdminID();
				List<Integer> sl = adminUserFacade.getUserRoleID(adminID);
				session.setAttribute(Contants.ADMIN_USER_ID, adminID);
				session.setAttribute(Contants.ADMIN_REAL_NAME, u.getRealName());
				session.setAttribute(Contants.ADMIN_USER_NAME, u.getAdminName());
				session.setAttribute(Contants.ADMIN_USER_ROLES, sl);
				session.setAttribute(Contants.CLASS_TEACHER,
						this.teacherFacade.getTeacherAndOrg(adminID));// 班级管理员
				session.setAttribute(Contants.ADMIN_USER_AUTH, privilegeFacade
						.loadAUTH(Contants.defSystemType, adminID, sl));// 查询权限
			}
		}
		return 1;
	}

}
