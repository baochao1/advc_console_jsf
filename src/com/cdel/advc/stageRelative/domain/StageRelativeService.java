package com.cdel.advc.stageRelative.domain;

import java.io.Serializable;
import java.util.Date;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 学习计划的阶段服务与发短信时间关系
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
public class StageRelativeService extends BaseDomain implements Serializable {

	private Integer smsStageRelSerID;
	private Integer smsStageRelID;
	private Date serviceTime;
	private Integer templateID;
	private Short status;
	// ------------------------------------------------------------------------------
	private Integer userID;
	private Integer classID;

	public Integer getSmsStageRelSerID() {
		return smsStageRelSerID;
	}

	public void setSmsStageRelSerID(Integer smsStageRelSerID) {
		this.smsStageRelSerID = smsStageRelSerID;
	}

	public Integer getSmsStageRelID() {
		return smsStageRelID;
	}

	public void setSmsStageRelID(Integer smsStageRelID) {
		this.smsStageRelID = smsStageRelID;
	}

	public Date getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(Date serviceTime) {
		this.serviceTime = serviceTime;
	}

	public Integer getTemplateID() {
		return templateID;
	}

	public void setTemplateID(Integer templateID) {
		this.templateID = templateID;
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

}
