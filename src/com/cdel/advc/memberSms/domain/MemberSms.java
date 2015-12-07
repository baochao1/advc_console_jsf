/*
 *                                                                  
 */
package com.cdel.advc.memberSms.domain;

import java.io.Serializable;
import java.util.Date;

import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 
 * <p>
 * 学员手机短信 实体
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-17 下午5:14:26
 */
@SuppressWarnings("serial")
public class MemberSms extends BaseDomain implements Serializable {

	/** ID */
	private Integer smsID;

	/** 班级ID */
	private Integer classID;

	/** 学员ID */
	private Integer userID;

	/** 短信内容 */
	private String content;

	/** 手机号码 */
	private String mobile;

	/** 发送时间 */
	private Date sendTime;

	/** 发送人ID */
	private Integer sender;

	/** 状态值 */
	private Short status;

	// -------------VO DTO-------------//

	private Integer courseID;

	/** 班级名称 */
	private String className;

	/** 发送人姓名 */
	private String realName;

	private Integer majorID;

	@Override
	public MemberSms clone() throws CloneNotSupportedException {
		MemberSms ms = new MemberSms();
		ms.setClassID(classID);
		ms.setStatus(status);
		ms.setSmsID(smsID);
		ms.setContent(content);
		ms.setMobile(mobile);
		ms.setSendTime(sendTime);
		ms.setUserID(userID);
		ms.setSender(sender);
		ms.setClassName(className);
		return ms;
	}

	/** 状态显示值 */
	public String getStatusStr() {
		if (status == null) {
			return "";
		} else {
			return Contants.status.get(status);
		}
	}

	/**
	 * 发送时间显示值
	 */
	public String getCreateTimeStr() {
		if (this.sendTime != null) {
			return DateUtil.getNowDateString(this.sendTime, "yyyy-MM-dd HH:mm");
		} else {
			return "";
		}
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getClassID() {
		return classID;
	}

	public void setClassID(Integer classID) {
		this.classID = classID;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Integer getSmsID() {
		return smsID;
	}

	public void setSmsID(Integer smsID) {
		this.smsID = smsID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Integer getSender() {
		return sender;
	}

	public void setSender(Integer sender) {
		this.sender = sender;
	}

	public Integer getCourseID() {
		return courseID;
	}

	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}

}