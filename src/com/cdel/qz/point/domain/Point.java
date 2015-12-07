package com.cdel.qz.point.domain;

import java.util.Date;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 知识点
 * 
 * @author 张苏磊
 */

public class Point extends BaseDomain implements java.io.Serializable {
	private static final long serialVersionUID = 5937446608344962761L;
	private Integer pointID;
	private String pointName;
	private Integer chapterID;
	private String chapterName;
	private Integer creator;
	private String realName;
	private Date createTime;
	private Integer courseID;
	private Short status;
	private Integer pointListID;
	// -------------------------------vo------------------------------------
	private Integer userID;
	private Integer studyPlanID;

	public Integer getPointID() {
		return pointID;
	}

	public void setPointID(Integer pointID) {
		this.pointID = pointID;
	}

	public String getPointName() {
		return pointName;
	}

	public void setPointName(String pointName) {
		this.pointName = pointName;
	}

	public Integer getChapterID() {
		return chapterID;
	}

	public void setChapterID(Integer chapterID) {
		this.chapterID = chapterID;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
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

	public Integer getCourseID() {
		return courseID;
	}

	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getPointListID() {
		return pointListID;
	}

	public void setPointListID(Integer pointListID) {
		this.pointListID = pointListID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Integer getStudyPlanID() {
		return studyPlanID;
	}

	public void setStudyPlanID(Integer studyPlanID) {
		this.studyPlanID = studyPlanID;
	}

}