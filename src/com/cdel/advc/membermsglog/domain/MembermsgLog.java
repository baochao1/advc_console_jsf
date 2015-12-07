package com.cdel.advc.membermsglog.domain;

import java.io.Serializable;
import java.util.Date;

import com.chnedu.plat.rad.domain.BaseDomain;

@SuppressWarnings("serial")
public class MembermsgLog extends BaseDomain implements Serializable {
	private Integer msgLogID;// logID
	private Integer msgID;// 消息ID
	private Short logType;// 类型
	private String logContent;// 内容
	private Integer creator;// 创建人
	private Date createTime;// 创建时间

	public Integer getMsgLogID() {
		return msgLogID;
	}

	public void setMsgLogID(Integer msgLogID) {
		this.msgLogID = msgLogID;
	}

	public Integer getMsgID() {
		return msgID;
	}

	public void setMsgID(Integer msgID) {
		this.msgID = msgID;
	}

	public Short getLogType() {
		return logType;
	}

	public void setLogType(Short logType) {
		this.logType = logType;
	}

	public String getLogContent() {
		return logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
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

}
