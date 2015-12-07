package com.cdel.plat.grant.domain;

import java.io.Serializable;
import java.util.Date;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * Class description goes here.
 * 
 * @version 1.0 2008-1-17
 * @author LiXuFang - j2eeli@chinaacc.com
 */
public class AdminUser extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 6467720460317210760L;
	private String adminName;
	private String realName;
	private Integer adminID;
	private String passWd;
	private String newPassWd;
	private String conNewPassWd;
	private String displayName;
	private String cellPhone;
	private Date lastLogin;
	private String loginIP;
	private Integer status;
	private Integer creator;
	private String creatorName;
	private Date createTime;
	private Integer roleID;
	private Date pwdModifyTime;
	private Integer systemType;
	private String systemTypeStr;

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSystemTypeStr() {
		return systemTypeStr;
	}

	public Integer getSystemType() {
		return systemType;
	}

	public void setSystemType(Integer systemType) {
		this.systemType = systemType;
	}

	public Date getPwdModifyTime() {
		return pwdModifyTime;
	}

	public void setPwdModifyTime(Date pwdModifyTime) {
		this.pwdModifyTime = pwdModifyTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getRoleID() {
		return roleID;
	}

	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}

	public Integer getAdminID() {
		return adminID;
	}

	public void setAdminID(Integer adminID) {
		this.adminID = adminID;
	}

	public String getPassWd() {
		return passWd;
	}

	public void setPassWd(String passWd) {
		this.passWd = passWd;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getLoginIP() {
		return loginIP;
	}

	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getNewPassWd() {
		return newPassWd;
	}

	public void setNewPassWd(String newPassWd) {
		this.newPassWd = newPassWd;
	}

	public String getConNewPassWd() {
		return conNewPassWd;
	}

	public void setConNewPassWd(String conNewPassWd) {
		this.conNewPassWd = conNewPassWd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adminID == null) ? 0 : adminID.hashCode());
		result = prime * result
				+ ((cellPhone == null) ? 0 : cellPhone.hashCode());
		result = prime * result
				+ ((displayName == null) ? 0 : displayName.hashCode());
		result = prime * result + ((loginIP == null) ? 0 : loginIP.hashCode());
		result = prime * result + ((passWd == null) ? 0 : passWd.hashCode());
		result = prime * result
				+ ((realName == null) ? 0 : realName.hashCode());
		result = prime * result
				+ ((adminName == null) ? 0 : adminName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final AdminUser other = (AdminUser) obj;
		if (adminID == null) {
			if (other.adminID != null)
				return false;
		} else if (!adminID.equals(other.adminID))
			return false;
		if (cellPhone == null) {
			if (other.cellPhone != null)
				return false;
		} else if (!cellPhone.equals(other.cellPhone))
			return false;
		if (displayName == null) {
			if (other.displayName != null)
				return false;
		} else if (!displayName.equals(other.displayName))
			return false;
		if (loginIP == null) {
			if (other.loginIP != null)
				return false;
		} else if (!loginIP.equals(other.loginIP))
			return false;
		if (passWd == null) {
			if (other.passWd != null)
				return false;
		} else if (!passWd.equals(other.passWd))
			return false;
		if (realName == null) {
			if (other.realName != null)
				return false;
		} else if (!realName.equals(other.realName))
			return false;
		if (adminName == null) {
			if (other.adminName != null)
				return false;
		} else if (!adminName.equals(other.adminName))
			return false;
		return true;
	}

}
