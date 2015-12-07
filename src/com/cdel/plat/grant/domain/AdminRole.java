package com.cdel.plat.grant.domain;

import java.io.Serializable;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 角色
 * 
 * @author zhangsulei
 */
@SuppressWarnings("serial")
public class AdminRole extends BaseDomain implements Serializable {
	private Integer roleID;
	private String roleName;

	public Integer getRoleID() {
		return roleID;
	}

	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
