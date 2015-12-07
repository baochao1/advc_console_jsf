package com.cdel.advc.gdb.plan.domain;

import java.io.Serializable;
import java.util.Date;

import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 高端班学习计划.
 * 
 * @version 1.0 2011-11-10
 * @author 张苏磊
 */
@SuppressWarnings("serial")
public class GdbStudyPlan extends BaseDomain implements Serializable {

	private Integer studyPlanID;
	private Integer userID;
	private Date startDate;
	private Date endDate;
	private Short status;
	private String studyContent;
	private String studyMethods;
	private Date createTime;
	private Date updateTime;
	private Integer createUserId;
	private Integer siteCourseID;
	private Float studyHours;
	private Integer weekNum;
	private Short isView;
	// --------------------vo--------------------------------
	private Date startDate1;
	private Date startDate2;
	private String siteCourseName;
	private String creator;
	private Integer courseID;

	public Integer getStudyPlanID() {
		return studyPlanID;
	}

	public void setStudyPlanID(Integer studyPlanID) {
		this.studyPlanID = studyPlanID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public String getStudyContent() {
		return studyContent;
	}

	public void setStudyContent(String studyContent) {
		this.studyContent = studyContent;
	}

	public String getStudyMethods() {
		return studyMethods;
	}

	public void setStudyMethods(String studyMethods) {
		this.studyMethods = studyMethods;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Integer getSiteCourseID() {
		return siteCourseID;
	}

	public void setSiteCourseID(Integer siteCourseID) {
		this.siteCourseID = siteCourseID;
	}

	public Float getStudyHours() {
		return studyHours;
	}

	public String getStudyHoursStr() {
		if (studyHours == null) {
			return "0";
		} else {
			return studyHours.intValue() + "小时";
		}
	}

	public void setStudyHours(Float studyHours) {
		this.studyHours = studyHours;
	}

	public Integer getWeekNum() {
		return weekNum;
	}

	public String getWeekNumStr() {
		if (weekNum == null) {
			return "";
		} else {
			return "第" + weekNum + "周";
		}
	}

	public void setWeekNum(Integer weekNum) {
		this.weekNum = weekNum;
	}

	public Short getIsView() {
		return isView;
	}

	public void setIsView(Short isView) {
		this.isView = isView;
	}

	public Date getStartDate1() {
		return startDate1;
	}

	public void setStartDate1(Date startDate1) {
		this.startDate1 = startDate1;
	}

	public Date getStartDate2() {
		return startDate2;
	}

	public void setStartDate2(Date startDate2) {
		this.startDate2 = startDate2;
	}

	public String getSiteCourseName() {
		return siteCourseName;
	}

	public void setSiteCourseName(String siteCourseName) {
		this.siteCourseName = siteCourseName;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Integer getCourseID() {
		return courseID;
	}

	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}

}