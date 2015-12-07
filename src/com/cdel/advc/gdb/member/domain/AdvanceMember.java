package com.cdel.advc.gdb.member.domain;

import java.util.Date;
import java.util.List;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 高端班学员
 * 
 * @author zhangsulei
 * 
 */
@SuppressWarnings("serial")
public class AdvanceMember extends BaseDomain implements java.io.Serializable {
	/** 数据库字段 */
	private Integer id;
	private Integer userID;
	private Integer majorID;
	private String studyCourse;
	private String siteCourseIds;
	private Integer teacherID;
	private Short status;
	private Date createTime;
	private String telPhone;
	// ------------------------------------------------
	private String userName;
	private List<String> courseList;// 学员报的课程List;
	private String courseIDs;// 高端班IDs
	private String courseIDs2;// 高端班IDs，把"'"变"''"，用于行列转换
	private String courseID;
	private Short successType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}

	public String getStudyCourse() {
		return studyCourse;
	}

	public void setStudyCourse(String studyCourse) {
		this.studyCourse = studyCourse;
	}

	public String getSiteCourseIds() {
		return siteCourseIds;
	}

	public void setSiteCourseIds(String siteCourseIds) {
		this.siteCourseIds = siteCourseIds;
	}

	public Integer getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(Integer teacherID) {
		this.teacherID = teacherID;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<String> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<String> courseList) {
		this.courseList = courseList;
	}

	public String getCourseCodes() {
		if (courseList != null && courseList.size() > 0) {
			StringBuffer sb = new StringBuffer("");
			for (int i = 0; i < courseList.size(); i++) {
				sb.append(courseList.get(i)).append(";");
			}
			return sb.toString();
		} else {
			return "";
		}
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public String getCourseIDs() {
		return courseIDs;
	}

	public void setCourseIDs(String courseIDs) {
		this.courseIDs = courseIDs;
	}

	public String getCourseIDs2() {
		return courseIDs2;
	}

	public void setCourseIDs2(String courseIDs2) {
		this.courseIDs2 = courseIDs2;
	}

	public Short getSuccessType() {
		return successType;
	}

	public void setSuccessType(Short successType) {
		this.successType = successType;
	}

}
