package com.cdel.qz.course.domain;

import java.util.Date;

import com.cdel.util.Contants;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * QzCourse entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class QzCourse extends BaseDomain implements java.io.Serializable {

	private Integer courseID;
	private Integer majorID;
	private String courseName;
	private Short courseChapter;
	private Integer sequence;
	private Integer creator;
	private Date createTime;
	private Short status;
	private Integer chapterListID;
	private Integer pointListID;

	// ------------------------------------------------------------------

	private Integer siteID;
	private String majorName;
	private String businessName;

	public Integer getSiteID() {
		return siteID;
	}

	public void setSiteID(Integer siteID) {
		this.siteID = siteID;
	}

	public Integer getChapterListID() {
		return chapterListID;
	}

	public void setChapterListID(Integer chapterListID) {
		this.chapterListID = chapterListID;
	}

	public Integer getPointListID() {
		return pointListID;
	}

	public void setPointListID(Integer pointListID) {
		this.pointListID = pointListID;
	}

	public Integer getCourseID() {
		return courseID;
	}

	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}

	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Short getCourseChapter() {
		return this.courseChapter;
	}

	public void setCourseChapter(Short courseChapter) {
		this.courseChapter = courseChapter;
	}

	public Integer getSequence() {
		return this.sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Integer getCreator() {
		return this.creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getStatusStr() {
		if (this.status == null) {
			return "";
		}
		return Contants.status.get(status);
	}

}