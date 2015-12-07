package com.cdel.advc.smsRegularly.domain;

import java.io.Serializable;
import java.util.Date;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 短信发送表（固定日期发送的记录先生成好，轮询发送的记录到时间再生成） 固定日期发送的短信目前来看有：入学测试提醒、考前提醒
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
public class SmsRegularly extends BaseDomain implements Serializable {

	private Integer smsRegID;
	private Integer userID;
	private Integer classID;
	private Date sendTime;
	private Integer templateID;
	private String content;
	private Short isNeed;// 是否需要发送,0否，1是
	private Short sendStatus;// 是否已发送,0否，1是
	private Short isSucess;// 是否发送成功,0否，1是

	public Integer getSmsRegID() {
		return smsRegID;
	}

	public void setSmsRegID(Integer smsRegID) {
		this.smsRegID = smsRegID;
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

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Integer getTemplateID() {
		return templateID;
	}

	public void setTemplateID(Integer templateID) {
		this.templateID = templateID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Short getIsNeed() {
		return isNeed;
	}

	public void setIsNeed(Short isNeed) {
		this.isNeed = isNeed;
	}

	public Short getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(Short sendStatus) {
		this.sendStatus = sendStatus;
	}

	public Short getIsSucess() {
		return isSucess;
	}

	public void setIsSucess(Short isSucess) {
		this.isSucess = isSucess;
	}

}
