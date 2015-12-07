package com.cdel.advc.report.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * Domain Object. 统计学习时长
 * 
 * @version 1.0 2009-4-13
 * @author 张苏磊
 */
@SuppressWarnings("serial")
public class CwTimeLong extends BaseDomain implements Serializable {

	/** 编号 */
	private Integer timeStatID;

	/** 累计学习时间/学习时间 */
	private Integer totalStudyTime;

	/** 章节号 */
	private Short chapterNum;

	/** 学员编号 */
	private Integer userID;

	/** 课程ID */
	private Integer courseID;

	/** 最早开始时间 */
	private Date earlyStudyTime;

	/** 班次 */
	private String classLevel;

	/** 创建时间 */
	private Date createTime;

	/** 最后结束时间 */
	private Date lastStudyTime;

	/** 学习日期 */
	private Date studyDate;

	// other
	private String chapterName;

	private String courseName;

	private String userName;

	private String courseCode;

	private String cwID;

	private List<String> cwIDList = new ArrayList<String>();

	public String getCwID() {
		return cwID;
	}

	public void setCwID(String cwID) {
		this.cwID = cwID;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getTotalStudyTime() {
		return totalStudyTime;
	}

	public String getTotalStudyTimeStr() {
		if (totalStudyTime == null) {
			return "";
		}
		int hours = totalStudyTime / 60;
		int minute = totalStudyTime % 60;
		return hours + "小时" + minute + "分钟";
	}

	public void setTotalStudyTime(Integer totalStudyTime) {
		this.totalStudyTime = totalStudyTime;
	}

	public Short getChapterNum() {
		return chapterNum;
	}

	public void setChapterNum(Short chapterNum) {
		this.chapterNum = chapterNum;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Date getEarlyStudyTime() {
		return earlyStudyTime;
	}

	public String getEarlyStudyTimeStr() {
		return DateUtil.getNowDateString(earlyStudyTime, "yyyy-MM-dd HH:mm");
	}

	public void setEarlyStudyTime(Date earlyStudyTime) {
		this.earlyStudyTime = earlyStudyTime;
	}

	public String getClassLevel() {
		return classLevel;
	}

	public String getClassLevelStr() {
		if (classLevel == null) {
			return "";
		}
		return classLevel;
	}

	public void setClassLevel(String classLevel) {
		this.classLevel = classLevel;
	}

	public Integer getTimeStatID() {
		return timeStatID;
	}

	public void setTimeStatID(Integer timeStatID) {
		this.timeStatID = timeStatID;
	}

	public Integer getCourseID() {
		return courseID;
	}

	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastStudyTime() {
		return lastStudyTime;
	}

	public String getLastStudyTimeStr() {
		return DateUtil.getNowDateString(lastStudyTime, "yyyy-MM-dd HH:mm");
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setLastStudyTime(Date lastStudyTime) {
		this.lastStudyTime = lastStudyTime;
	}

	public Date getStudyDate() {
		return studyDate;
	}

	public String getStudyDateStr() {
		return DateUtil.getNowDateString(studyDate, "yyyy-MM-dd");
	}

	public void setStudyDate(Date studyDate) {
		this.studyDate = studyDate;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public List<String> getCwIDList() {
		return cwIDList;
	}

	public void setCwIDList(List<String> cwIDList) {
		this.cwIDList = cwIDList;
	}

}