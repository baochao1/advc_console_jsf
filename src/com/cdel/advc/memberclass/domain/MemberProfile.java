package com.cdel.advc.memberclass.domain;

import java.io.Serializable;
import java.util.Date;

import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 学员个性化信息
 * 
 * @version 1.0 2009-4-13
 * @author zhangsulei
 */
@SuppressWarnings("serial")
public class MemberProfile extends BaseDomain implements Serializable {

	private Integer userID;
	private String userName;
	private String nickName;
	private String iconUrl;
	private Date createTime;
	private Date lastModifyTime;
	private Short helpIndex;
	private Integer loginTimes;
	private Short currentStatus;

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String getCreateTimeStr() {
		return DateUtil.getNowDateString(createTime, "yyyy-MM-dd HH:mm");
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Short getHelpIndex() {
		return helpIndex;
	}

	public void setHelpIndex(Short helpIndex) {
		this.helpIndex = helpIndex;
	}

	public Integer getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public String getLastModifyTimeStr() {
		return DateUtil.getNowDateString(lastModifyTime, "yyyy-MM-dd HH:mm");
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public Short getCurrentStatus() {
		return currentStatus;
	}

	public String getCurrentStatusStr() {
		if (currentStatus == null) {
			return "未登录";
		} else if (currentStatus == 0) {
			return "未登录";
		} else if (currentStatus == 1) {
			return "我在线上";
		} else if (currentStatus == 2) {
			return "正在学习";
		} else if (currentStatus == 3) {
			return "我要隐身";
		}
		return "未登录";
	}

	public void setCurrentStatus(Short currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getIconUrlStr() {
		if (iconUrl == null) {
			return "/resources/images/memberprofile.jpg";
		} else {
			return iconUrl;
		}
	}

}