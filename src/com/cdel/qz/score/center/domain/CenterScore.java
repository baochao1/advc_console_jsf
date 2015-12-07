package com.cdel.qz.score.center.domain;

import java.io.Serializable;
import java.util.Date;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 考试中心记录
 * 
 * @author张苏磊
 */
@SuppressWarnings("serial")
public class CenterScore extends BaseDomain implements Serializable {

	private Integer paperScoreID;
	private Integer userID;
	private Integer courseID;
	private String courseName;
	private Integer centerID;
	private String centerName;
	private Integer paperViewID;
	private String paperViewName;
	// -----------------------------vo---------------------------------------------
	private Float machineMark;// 客观题得分
	private Float lastMark;// 最后得分
	private Short playCount;// 考试次数
	private Date lastPlayTime;// 最后一次交卷时间
	private Float paperTotalScore;// 试卷总分

	public Integer getPaperScoreID() {
		return paperScoreID;
	}

	public void setPaperScoreID(Integer paperScoreID) {
		this.paperScoreID = paperScoreID;
	}

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

	public Integer getCenterID() {
		return centerID;
	}

	public void setCenterID(Integer centerID) {
		this.centerID = centerID;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public Integer getPaperViewID() {
		return paperViewID;
	}

	public void setPaperViewID(Integer paperViewID) {
		this.paperViewID = paperViewID;
	}

	public String getPaperViewName() {
		return paperViewName;
	}

	public void setPaperViewName(String paperViewName) {
		this.paperViewName = paperViewName;
	}

	public Float getMachineMark() {
		return machineMark;
	}

	public void setMachineMark(Float machineMark) {
		this.machineMark = machineMark;
	}

	public Short getPlayCount() {
		return playCount;
	}

	public void setPlayCount(Short playCount) {
		this.playCount = playCount;
	}

	public Date getLastPlayTime() {
		return lastPlayTime;
	}

	public String getLastPlayTimeStr() {
		return DateUtil.getNowDateString(lastPlayTime, "yyyy-MM-dd");
	}

	public String getLastPlayTimeStr2() {
		return DateUtil.getNowDateString(lastPlayTime, "yyyy-MM-dd HH:mm:ss");
	}

	public void setLastPlayTime(Date lastPlayTime) {
		this.lastPlayTime = lastPlayTime;
	}

	public Float getPaperTotalScore() {
		return paperTotalScore;
	}

	public void setPaperTotalScore(Float paperTotalScore) {
		this.paperTotalScore = paperTotalScore;
	}

	public Float getLastMark() {
		return lastMark;
	}

	public void setLastMark(Float lastMark) {
		this.lastMark = lastMark;
	}

}