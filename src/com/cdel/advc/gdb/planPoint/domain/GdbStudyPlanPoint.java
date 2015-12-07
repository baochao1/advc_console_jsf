package com.cdel.advc.gdb.planPoint.domain;

import java.io.Serializable;
import java.util.Date;

import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 高端班知识点
 * 
 * @author 张苏磊
 */
@SuppressWarnings("serial")
public class GdbStudyPlanPoint extends BaseDomain implements Serializable {

	private Integer id;
	private Integer studyPlanID;
	private Integer pointID;
	private Integer siteCourseID;
	private Integer createUserID;
	private Integer userID;
	private Date createTime;

	// -----------------------vo-----------------------------
	private Integer courseID;
	private String siteCourseName;
	private String creator;// 创建人
	private String pointName;

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String getCreateTimeStr() {
		return DateUtil.getNowDateString(createTime, "yyyy-MM-dd HH:mm:ss");
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPointID() {
		return pointID;
	}

	public void setPointID(Integer pointID) {
		this.pointID = pointID;
	}

	public Integer getStudyPlanID() {
		return studyPlanID;
	}

	public void setStudyPlanID(Integer studyPlanID) {
		this.studyPlanID = studyPlanID;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getPointName() {
		return pointName;
	}

	public void setPointName(String pointName) {
		this.pointName = pointName;
	}

	public Integer getCreateUserID() {
		return createUserID;
	}

	public void setCreateUserID(Integer createUserID) {
		this.createUserID = createUserID;
	}

	public Integer getSiteCourseID() {
		return siteCourseID;
	}

	public void setSiteCourseID(Integer siteCourseID) {
		this.siteCourseID = siteCourseID;
	}

	public String getSiteCourseName() {
		return siteCourseName;
	}

	public void setSiteCourseName(String siteCourseName) {
		this.siteCourseName = siteCourseName;
	}

	public Integer getCourseID() {
		return courseID;
	}

	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}

}