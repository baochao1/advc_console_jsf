package com.cdel.qz.quesError.domain;

import java.util.Date;

import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

@SuppressWarnings("serial")
public class QuesError extends BaseDomain implements java.io.Serializable {

	private Integer userID;
	private Integer courseID;
	private String courseName;
	private String testType;// 考试类别
	private String testName;// 考试名称
	private Date lastPlayTime;
	private Integer errorCount;

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Integer getCourseID() {
		return courseID;
	}

	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Date getLastPlayTime() {
		return lastPlayTime;
	}

	public String getLastPlayTimeStr() {
		return DateUtil.getNowDateString(lastPlayTime, "yyyy-MM-dd HH:mm:ss");
	}

	public void setLastPlayTime(Date lastPlayTime) {
		this.lastPlayTime = lastPlayTime;
	}

	public Integer getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(Integer errorCount) {
		this.errorCount = errorCount;
	}

}