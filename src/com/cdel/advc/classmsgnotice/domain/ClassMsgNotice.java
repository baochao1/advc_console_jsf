package com.cdel.advc.classmsgnotice.domain;

import java.util.Date;

import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

@SuppressWarnings("serial")
public class ClassMsgNotice extends BaseDomain implements java.io.Serializable {
	private Integer classMsgID;// 关联id
	private Integer classID;// 班级ID
	private String className;// 班级名称
	private String msgTitle;// 标题
	private String msgContent;// 内容
	private Integer creator;// 创建者
	private Short msgType;// 类型
	private Short status;// 状态
	private String teacherName;// 老师名字
	private Date createTime;// 创建时间
	private Integer majorID;// 辅导id

	public String getCreateTimeStr() {
		return DateUtil.getNowDateString(getCreateTime(), "yyyy-MM-dd HH:mm");
	}

	public Integer getClassMsgID() {
		return classMsgID;
	}

	public void setClassMsgID(Integer classMsgID) {
		this.classMsgID = classMsgID;
	}

	public Integer getClassID() {
		return classID;
	}

	public void setClassID(Integer classID) {
		this.classID = classID;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Short getMsgType() {
		return msgType;
	}

	public void setMsgType(Short msgType) {
		this.msgType = msgType;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}

	public String getStatusStr() {
		if (status == null) {
			return "";
		} else {
			return Contants.status.get(status);
		}
	}

}
