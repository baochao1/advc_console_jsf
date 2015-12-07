package com.cdel.advc.technologyMsg.domain;

import java.util.Date;

import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

@SuppressWarnings("serial")
public class TechnologyMsg extends BaseDomain implements java.io.Serializable {

	private Date replyTime;// 回复时间
	private String replyTimeStr;// 回复时间字符串
	private Integer classID;// 班级ID
	private Short readFlag;// 阅读状态
	private Date sentTime;// 发送时间
	private String sentTimeStr;// 发送时间
	private Short msgType;// 类型
	private Integer msgID;// 回复id
	private Integer majorID;// 辅导id
	private Short status;// 状态
	private Integer reply;// 回复状态
	private String userName;// 学员id
	private String className;// 班级名称
	private String displayAdviser;// 班主任
	private String msgTitle;// 消息主题

	public String getReplyTimeStr() {
		replyTimeStr = DateUtil.getNowDateString(getReplyTime(),
				"yyyy-MM-dd HH:mm");
		return replyTimeStr;
	}

	public String getSentTimeStr() {
		sentTimeStr = DateUtil.getNowDateString(getSentTime(),
				"yyyy-MM-dd HH:mm");
		return sentTimeStr;
	}

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	public Integer getClassID() {
		return classID;
	}

	public void setClassID(Integer classID) {
		this.classID = classID;
	}

	public Short getReadFlag() {
		return readFlag;
	}

	public String getReadFlagStr() {
		if (readFlag == null) {
			return "";
		} else {
			return Contants.readStatus.get(readFlag);
		}
	}

	public void setReadFlag(Short readFlag) {
		this.readFlag = readFlag;
	}

	public Date getSentTime() {
		return sentTime;
	}

	public void setSentTime(Date sentTime) {
		this.sentTime = sentTime;
	}

	public Short getMsgType() {
		return msgType;
	}

	public void setMsgType(Short msgType) {
		this.msgType = msgType;
	}

	public Integer getMsgID() {
		return msgID;
	}

	public void setMsgID(Integer msgID) {
		this.msgID = msgID;
	}

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}

	public Short getStatus() {
		return status;
	}

	public String getStatusStr() {
		if (status == null) {
			return "";
		} else {
			return Contants.status.get(status);
		}
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getReply() {
		return reply;
	}

	public void setReply(Integer reply) {
		this.reply = reply;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getDisplayAdviser() {
		return displayAdviser;
	}

	public void setDisplayAdviser(String displayAdviser) {
		this.displayAdviser = displayAdviser;
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

}
