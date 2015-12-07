package com.cdel.advc.innermsg.domain;

import java.util.Date;
import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

@SuppressWarnings("serial")
public class InnerMsgReceive extends BaseDomain implements java.io.Serializable {

	private Integer receiveID;
	private Integer innerMsgID;
	private Date readTime;// 阅读时间
	private Short readStatus;// 阅读状态
	private Integer receiveTeacherID;// 接收老师ID
	private String senderName;// 发送人姓名
	private String receiveName;// 接收人姓名
	private String teacherCode;// 接收/发送人代码
	private String receiverName;// 接收人串

	public Integer getInnerMsgID() {
		return innerMsgID;
	}

	public void setInnerMsgID(Integer innerMsgID) {
		this.innerMsgID = innerMsgID;
	}

	public Date getReadTime() {
		return readTime;
	}

	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}

	public String getReadTimeStr() {
		return DateUtil.getNowDateString(readTime, "yyyy-MM-dd HH:mm");
	}

	public Short getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(Short readStatus) {
		this.readStatus = readStatus;
	}

	public String getReadStatusStr() {
		if (readStatus == null) {
			return "--";
		} else {
			return Contants.readStatus.get(readStatus);
		}
	}

	public Integer getReceiveTeacherID() {
		return receiveTeacherID;
	}

	public void setReceiveTeacherID(Integer receiveTeacherID) {
		this.receiveTeacherID = receiveTeacherID;
	}

	public Integer getReceiveID() {
		return receiveID;
	}

	public void setReceiveID(Integer receiveID) {
		this.receiveID = receiveID;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getTeacherCode() {
		return teacherCode;
	}

	public void setTeacherCode(String teacherCode) {
		this.teacherCode = teacherCode;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

}
