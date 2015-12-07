package com.cdel.qz.score.point.domain;

import java.util.Date;

import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 知识点测试记录 entity.
 * 
 * @author 张苏磊
 */

@SuppressWarnings("serial")
public class PointScore extends BaseDomain implements java.io.Serializable {
	private Integer pointTestID;
	private Integer pointID;
	private String pointTestName;
	private Integer siteCourseID;
	private String siteCourseName;
	private Integer courseID;
	private Integer userID;
	private String userName;
	// -----------------------vo-------------------------------
	private String pointName;
	private String courseName;
	private Integer chapterID;
	private String chapterName;
	private Integer pointNum;// 知识点个数
	private Short totleNum;// 题目个数
	private Short correctCount;// 正确题数
	private Short errorNum;// 错误题数
	private String correctScale;// 正确率
	private Date lastPlayTime;// 最后一次考试的时间

	public String getPointTestName() {
		return pointTestName;
	}

	public void setPointTestName(String pointTestName) {
		this.pointTestName = pointTestName;
	}

	public String getSiteCourseName() {
		return siteCourseName;
	}

	public void setSiteCourseName(String siteCourseName) {
		this.siteCourseName = siteCourseName;
	}

	public Integer getSiteCourseID() {
		return siteCourseID;
	}

	public void setSiteCourseID(Integer siteCourseID) {
		this.siteCourseID = siteCourseID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getPointTestID() {
		return pointTestID;
	}

	public void setPointTestID(Integer pointTestID) {
		this.pointTestID = pointTestID;
	}

	public Integer getCourseID() {
		return courseID;
	}

	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Integer getPointID() {
		return pointID;
	}

	public void setPointID(Integer pointID) {
		this.pointID = pointID;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public Integer getPointNum() {
		return pointNum;
	}

	public void setPointNum(Integer pointNum) {
		this.pointNum = pointNum;
	}

	public Short getTotleNum() {
		return totleNum;
	}

	public void setTotleNum(Short totleNum) {
		this.totleNum = totleNum;
	}

	public Short getCorrectCount() {
		return correctCount;
	}

	public void setCorrectCount(Short correctCount) {
		this.correctCount = correctCount;
	}

	public String getCorrectScale() {
		return correctScale;
	}

	public void setCorrectScale(String correctScale) {
		this.correctScale = correctScale;
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

	public Short getErrorNum() {
		return errorNum;
	}

	public void setErrorNum(Short errorNum) {
		this.errorNum = errorNum;
	}

	public Date getLastPlayTime() {
		return lastPlayTime;
	}

	public String getLastPlayTimeStr() {
		return DateUtil.getNowDateString(lastPlayTime, "yyyy-MM-dd");
	}

	public void setLastPlayTime(Date lastPlayTime) {
		this.lastPlayTime = lastPlayTime;
	}

}