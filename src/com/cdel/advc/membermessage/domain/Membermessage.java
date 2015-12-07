/*
 *                                                                    
 */
package com.cdel.advc.membermessage.domain;

import java.io.Serializable;
import java.util.Date;

import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 *                                               
 * <p>学员间消息(看来对应的是---"班级短信"---模块) 实体</p>
 * 
 * @author Du Haiying
 * Create at:2013-7-4 上午11:14:58
 */
@SuppressWarnings("serial")
public class Membermessage extends BaseDomain implements Serializable {

	
	/** 消息ID */
	private Integer msgID;
	
	/** 消息主题  */
	private String msgTitle;
	
	/** 消息内容  */
	private String msgContent;
	
	/** 发送人 ID */
	private Integer sender;
	
	/** 消息类型 */
	private Short msgType;
	
	/** 发送时间*/
	private Date sentTime;
	
	/** 接收人ID */
	private Integer receiver;
	
	/** 回复内容 */
	private String replyContent;
	
	/** 回复时间 */
	private Date replyTime;
	
	/** 是否已读 */
	private Short readFlag;

	/** 班级ID */
	private Integer classID;
	
    /** 状态  */
    private Short status;
    
    //-- vo dto -/
    
    /** 发送人 名称  */
    private String senderName;
    
    /** 接收人名称 */
    private String receiverName;
    
    /** 班级名称 */
    private String className;
    
    /** 辅导ID */
    private Integer majorID;

    private String classcode;//班级代码
    private String displayAdviser;//班主任名称
    private String userName;//学员名称
    private String teacherName;//老师名称
    private Integer teacherID;//老师ID
    private Integer logCount;//日志记录
    private String replyContentOld;//原回复
    
    
    /** 得到状态显示值  */
    public String getStatusStr(){
    	if (this.status == null) {
    		return "";
    	}else {
    		return Contants.status.get(this.status);
    	}
    }
    
    /** 阅读状态显示值  */
    public String getReadStatusStr(){
    	if (this.readFlag == null) {
    		return "";
    	}else {
    		return Contants.readStatus.get(this.readFlag);
    	}
    }
    
    /** 发送时间格式化显示    */
    public  String getSentTimeStr(){
    	if (this.sentTime == null) {
			return "";
		}else {
			return DateUtil.getNowDateString(this.sentTime, "yyyy-MM-dd HH:mm");
		}
    }
    
    /** 回复时间格式化显示    */
    public  String getReplyTimeStr(){
    	if (this.replyTime == null) {
			return "";
		}else {
			return DateUtil.getNowDateString(this.replyTime, "yyyy-MM-dd HH:mm");
		}
    }

	public Integer getMsgID() {
		return msgID;
	}

	public void setMsgID(Integer msgID) {
		this.msgID = msgID;
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

	public Integer getSender() {
		return sender;
	}

	public void setSender(Integer sender) {
		this.sender = sender;
	}

	public Short getMsgType() {
		return msgType;
	}

	public void setMsgType(Short msgType) {
		this.msgType = msgType;
	}

	public Date getSentTime() {
		return sentTime;
	}

	public void setSentTime(Date sentTime) {
		this.sentTime = sentTime;
	}

	public Integer getReceiver() {
		return receiver;
	}

	public void setReceiver(Integer receiver) {
		this.receiver = receiver;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	public Short getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(Short readFlag) {
		this.readFlag = readFlag;
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

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}

	public String getClasscode() {
		return classcode;
	}

	public void setClasscode(String classcode) {
		this.classcode = classcode;
	}

	public String getDisplayAdviser() {
		return displayAdviser;
	}

	public void setDisplayAdviser(String displayAdviser) {
		this.displayAdviser = displayAdviser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Integer getLogCount() {
		return logCount;
	}

	public void setLogCount(Integer logCount) {
		this.logCount = logCount;
	}

	public Integer getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(Integer teacherID) {
		this.teacherID = teacherID;
	}

	public String getReplyContentOld() {
		return replyContentOld;
	}

	public void setReplyContentOld(String replyContentOld) {
		this.replyContentOld = replyContentOld;
	}
    
}