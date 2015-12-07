package com.cdel.advc.report.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * Domain Object. 学习报告
 * 
 * @version 1.0 2009-4-13
 * @author 张苏磊
 */
@SuppressWarnings("serial")
public class Report extends BaseDomain implements Serializable {

	/** ID */
	private Integer reportID;

	/** 报告标题 */
	private String reportTitle;

	/** 学员ID */
	private Integer userID;

	/** 班级ID */
	private Integer classID;

	/** 章节的开始日期 */
	private Date startTime;

	/** 章节的截止日期 */
	private Date endTime;

	/** 报告课程年份 */
	private String reportYear;

	/** 学员申请评论状态 */
	private Short applyStatus;

	/** 学员申请评论时间 */
	private Date applyTime;

	/** 学员申请评论内容 */
	private String applyContent;

	/** 最新评语 教师ID */
	private Integer teacherID;

	/** 最新评语 时间 */
	private Date remarkTime;

	private Short status;

	private Integer creator;

	private Date createTime;

	// -------------------------- vo dto ------------------------ //
	private String teacherName;

	private String userName;

	private String className;

	private String creatorName;

	/** 评论内容 */
	private String remarkContent;

	/** 每日学习时长 */
	private String dayTimeLong;

	/** 错题记录 */
	private String errorRecord;

	/** 中心测试记录 */
	private String centerRecord;

	/** 知识点测试记录 */
	private String pointRecord;

	/** 学习时长记录 */
	private String timeLongRecord;

	/** 得到状态显示值 */
	public String getStatusStr() {
		if (this.status == null) {
			return "";
		} else {
			return Contants.status.get(this.status);
		}
	}

	public String getStartTimeStr() {
		return DateUtil.getNowDateString(this.startTime, "yyyy-MM-dd");
	}

	public String getEndTimeStr() {
		return DateUtil.getNowDateString(this.endTime, "yyyy-MM-dd");
	}

	public String getApplyTimeStr() {
		return DateUtil.getNowDateString(this.applyTime, "yyyy-MM-dd HH:mm");
	}

	public String getRemarkTimeStr() {
		return DateUtil.getNowDateString(this.remarkTime, "yyyy-MM-dd HH:mm");
	}

	public String getCreateTimeStr() {
		return DateUtil.getNowDateString(this.createTime, "yyyy-MM-dd HH:mm");
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Integer getReportID() {
		return reportID;
	}

	public void setReportID(Integer reportID) {
		this.reportID = reportID;
	}

	public String getReportTitle() {
		return reportTitle;
	}

	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Integer getClassID() {
		return classID;
	}

	public void setClassID(Integer classID) {
		this.classID = classID;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getReportYear() {
		return reportYear;
	}

	public void setReportYear(String reportYear) {
		this.reportYear = reportYear;
	}

	public Short getApplyStatus() {
		return applyStatus;
	}

	public String getApplyStatusStr() {
		if (applyStatus == null) {
			return Contants.applyStatus.get((short) 0);
		} else {
			return Contants.applyStatus.get(applyStatus);
		}
	}

	public void setApplyStatus(Short applyStatus) {
		this.applyStatus = applyStatus;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getApplyContent() {
		return applyContent;
	}

	public String getApplyContentStr() {
		if (StringUtils.isBlank(applyContent)) {
			return "无申请说明";
		}
		return applyContent;
	}

	public void setApplyContent(String applyContent) {
		this.applyContent = applyContent;
	}

	public Integer getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(Integer teacherID) {
		this.teacherID = teacherID;
	}

	public Date getRemarkTime() {
		return remarkTime;
	}

	public void setRemarkTime(Date remarkTime) {
		this.remarkTime = remarkTime;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
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

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getRemarkContent() {
		return remarkContent;
	}

	public void setRemarkContent(String remarkContent) {
		this.remarkContent = remarkContent;
	}

	public String getDayTimeLong() {
		return dayTimeLong;
	}

	public void setDayTimeLong(String dayTimeLong) {
		this.dayTimeLong = dayTimeLong;
	}

	public String getErrorRecord() {
		return errorRecord;
	}

	public void setErrorRecord(String errorRecord) {
		this.errorRecord = errorRecord;
	}

	public String getCenterRecord() {
		return centerRecord;
	}

	public void setCenterRecord(String centerRecord) {
		this.centerRecord = centerRecord;
	}

	public String getPointRecord() {
		return pointRecord;
	}

	public void setPointRecord(String pointRecord) {
		this.pointRecord = pointRecord;
	}

	public String getTimeLongRecord() {
		return timeLongRecord;
	}

	public void setTimeLongRecord(String timeLongRecord) {
		this.timeLongRecord = timeLongRecord;
	}

}