package com.cdel.advc.sendMemberMsgGroup.domain;

import com.chnedu.plat.rad.domain.BaseDomain;

@SuppressWarnings("serial")
public class SendMemberMsgGroup extends BaseDomain implements
		java.io.Serializable {
	private String msgTitle;// 标题
	private Short msgType; // 消息类型 
	private Integer majorID;// 辅导id
	private String majorName;// 辅导名称
	private Integer courseID;// 班级ID
	private String content;// 内容
	private Short status;// 状态
	private Integer userID;// 用户ID
	private Integer classID;// 班级ID
	private String studyCourse;// 课程
	private String className;// 班级名称
	private String userName;// 学员名称

	
	public Short getMsgType() {
		return msgType;
	}

	public void setMsgType(Short msgType) {
		this.msgType = msgType;
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public Integer getCourseID() {
		return courseID;
	}

	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Integer getClassID() {
		return classID;
	}

	public void setClassID(Integer classID) {
		this.classID = classID;
	}

	public String getStudyCourse() {
		return studyCourse;
	}

	public void setStudyCourse(String studyCourse) {
		this.studyCourse = studyCourse;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
