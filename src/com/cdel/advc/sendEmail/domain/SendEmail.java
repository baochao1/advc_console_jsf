package com.cdel.advc.sendEmail.domain;

import java.io.Serializable;
import java.util.Date;

import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

@SuppressWarnings("serial")
public class SendEmail extends BaseDomain implements Serializable {
	private Integer mailID;
	private Integer classID;
	private String className;
	private String title;
	private String content;
	private Integer sender;
	private Date sendTime;
	private String teacherName;

	public Integer getMailID() {
		return mailID;
	}

	public void setMailID(Integer mailID) {
		this.mailID = mailID;
	}

	public Integer getClassID() {
		return classID;
	}

	public void setClassID(Integer classID) {
		this.classID = classID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	/**
	 * 
	 * 得到发送时间
	 */
	public String getSendTimeStr() {
		return DateUtil.getNowDateString(this.sendTime, "yyyy-MM-dd hh:mm:ss");
	}

	public Integer getSender() {
		return sender;
	}

	public void setSender(Integer sender) {
		this.sender = sender;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

}
