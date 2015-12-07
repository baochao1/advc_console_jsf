package com.cdel.advc.stageRelative.domain;

import java.io.Serializable;
import java.util.Date;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 学习计划与阶段服务时间关系
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
public class StageRelative extends BaseDomain implements Serializable {

	private Integer smsStageRelID;
	private Integer userID;
	private Integer classID;
	private Integer planID;
	private Integer stageID;
	private Date startTime;
	private Date endTime;
	private Date serviceTime;
	private Integer templateID;
	private Short status;

	public Integer getSmsStageRelID() {
		return smsStageRelID;
	}

	public void setSmsStageRelID(Integer smsStageRelID) {
		this.smsStageRelID = smsStageRelID;
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

	public Integer getPlanID() {
		return planID;
	}

	public void setPlanID(Integer planID) {
		this.planID = planID;
	}

	public Integer getStageID() {
		return stageID;
	}

	public void setStageID(Integer stageID) {
		this.stageID = stageID;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

}
