package com.cdel.advc.plan.domain;

import java.util.Date;

import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 特殊日期设置
 * 
 * @author zhangsulei
 */

@SuppressWarnings("serial")
public class PlanSpecialGeneral extends BaseDomain implements
		java.io.Serializable {
	private Integer specialID;
	private Integer planID;
	private Date specialDate;
	private Short specialHours;
	private String logDesc;
	private Integer creator;
	private Date createTime;
	private Short status;
	// -----------------vo------------------------------
	private String userName;
	private Date beginDate;
	private String teacherName;
	private Short specialMin;

	public Integer getSpecialID() {
		return specialID;
	}

	public void setSpecialID(Integer specialID) {
		this.specialID = specialID;
	}

	public Integer getPlanID() {
		return planID;
	}

	public void setPlanID(Integer planID) {
		this.planID = planID;
	}

	public Date getSpecialDate() {
		return specialDate;
	}

	public String getSpecialDateStr() {
		return DateUtil.getNowDateString(specialDate, "yyyy-MM-dd");
	}

	public void setSpecialDate(Date specialDate) {
		this.specialDate = specialDate;
	}

	public Short getSpecialHours() {
		return specialHours;
	}

	public void setSpecialHours(Short specialHours) {
		this.specialHours = specialHours;
	}

	public String getLogDesc() {
		return logDesc;
	}

	public void setLogDesc(String logDesc) {
		this.logDesc = logDesc;
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

	public String getCreateTimeStr() {
		return DateUtil.getNowDateString(createTime, "yyyy-MM-dd");
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Short getStatus() {
		return status;
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

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Short getSpecialMin() {
		if (specialHours == null) {
			specialMin = 0;
		} else {
			specialMin = (short) (specialHours / 60);
		}
		return specialMin;
	}

	public void setSpecialMin(Short specialMin) {
		this.specialMin = specialMin;
	}

	public void setSpecialMin() {
		if (specialMin == null) {
			specialHours = 0;
		} else {
			specialHours = (short) (specialMin * 60);
		}
	}

}