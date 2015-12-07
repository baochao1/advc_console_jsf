/*
 * 
 */
package com.cdel.advc.plan.domain;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 
 * <p>
 * 学习计划章节 实体
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-31 下午5:45:34
 */
@SuppressWarnings("serial")
public class PlanChapter extends BaseDomain implements Serializable {

	/** 计划章节表ID */
	private Integer planChpID;

	/** 计划ID */
	private Integer planID;

	/** 详细计划模板ID */
	private Integer tmpDetailID;

	/** 章节计划开始日期 */
	private Date startTime;

	/** 章节计划截止日期 */
	private Date endTime;

	/** 章节计划所属周次 */
	private Short planWeek;

	/** 周开始时间 */
	private Date weekStartTime;

	/** 周截止时间 */
	private Date weekEndTime;

	/** 计划所属章节ID */
	private Integer chapterID;

	/** 章节计划排序号 */
	private Integer sequence;

	private Short isOld;

	// ----- vo dto ----------//

	/** 章节号 */
	private String chapterNo;

	/** 章节名称 */
	private String chapterName;

	/** 阶段号 */
	private Short phaseNo;
	private String stageName;

	/** 建议章节学习时间（单位分钟） */
	private Integer suggestStyTime;

	/** 章节所属课程名称 */
	private String courseName;

	/** 开通状态 */
	private Short openStatus;

	/** 计划完成状态 */
	private Short finishStatus;

	/** 计划完成时间 */
	private Date finishTime;

	/** 章节是否在学习标识 */
	private Short isStudy;

	// add by liulei
	private Integer userID;

	private Date tmpEndTime;

	private Short type;

	private Integer seq;

	/** 章节是否在学习状态显示值 */
	public String getIsStudyStr() {

		if (this.isStudy == null || this.isStudy == 1) {
			return "在学习";
		} else {
			return "已删除";
		}
	}

	/** 章节计划完成时间 */
	public String getFinishTimeStr() {

		if (this.finishTime == null) {
			return "";
		} else {
			return DateUtil.getNowDateString(this.finishTime, "yyyy-MM-dd");
		}
	}

	/** 完成状态显示值 */
	public String getFinishStatusStr() {
		if (this.finishStatus == null) {
			this.finishStatus = (short) 0;
		}
		return Contants.planChapterFinishStatus.get(this.finishStatus);
	}

	/** 开通状态显示值 */
	public String getOpenStatusStr() {
		if (this.openStatus == null) {
			return "";
		}
		return Contants.planChapterOpenStatus.get(this.openStatus);
	}

	/** 是否预习章节计划显示值 */
	public String getIsPreStr() {

		if (this.sequence != null && this.sequence < 0) {
			return "预习";
		} else {
			return "";
		}
	}

	/** 章节计划开始时间显示值 */
	public String getStartTimeStr() {

		if (this.startTime == null) {
			return "";
		} else {
			return DateUtil.getNowDateString(this.startTime, "yyyy-MM-dd");
		}
	}

	/** 章节计划开始时间显示值 */
	public String getEndTimeStr() {

		if (this.endTime == null) {
			return "";
		} else {
			return DateUtil.getNowDateString(this.endTime, "yyyy-MM-dd");
		}
	}

	public Short getIsStudy() {
		return isStudy;
	}

	public void setIsStudy(Short isStudy) {
		this.isStudy = isStudy;
	}

	public Short getPhaseNo() {
		return phaseNo;
	}

	public void setPhaseNo(Short phaseNo) {
		this.phaseNo = phaseNo;
	}

	public Short getOpenStatus() {
		return openStatus;
	}

	public void setOpenStatus(Short openStatus) {
		this.openStatus = openStatus;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Integer getSuggestStyTime() {
		return suggestStyTime;
	}

	public void setSuggestStyTime(Integer suggestStyTime) {
		this.suggestStyTime = suggestStyTime;
	}

	public Date getTmpEndTime() {
		return tmpEndTime;
	}

	public void setTmpEndTime(Date tmpEndTime) {
		this.tmpEndTime = tmpEndTime;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Integer getPlanID() {
		return planID;
	}

	public void setPlanID(Integer planID) {
		this.planID = planID;
	}

	public Integer getTmpDetailID() {
		return tmpDetailID;
	}

	public void setTmpDetailID(Integer tmpDetailID) {
		this.tmpDetailID = tmpDetailID;
	}

	public Integer getPlanChpID() {
		return planChpID;
	}

	public void setPlanChpID(Integer planChpID) {
		this.planChpID = planChpID;
	}

	public Date getWeekEndTime() {
		return weekEndTime;
	}

	public void setWeekEndTime(Date weekEndTime) {
		this.weekEndTime = weekEndTime;
	}

	public Date getWeekStartTime() {
		return weekStartTime;
	}

	public void setWeekStartTime(Date weekStartTime) {
		this.weekStartTime = weekStartTime;
	}

	public Short getPlanWeek() {
		return planWeek;
	}

	public void setPlanWeek(Short planWeek) {
		this.planWeek = planWeek;
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

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public Integer getChapterID() {
		return chapterID;
	}

	public void setChapterID(Integer chapterID) {
		this.chapterID = chapterID;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Short getFinishStatus() {
		return finishStatus;
	}

	public void setFinishStatus(Short finishStatus) {
		this.finishStatus = finishStatus;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getChapterNo() {
		return chapterNo;
	}

	public void setChapterNo(String chapterNo) {
		this.chapterNo = chapterNo;
	}

	public PlanChapter clone() {
		PlanChapter pc = new PlanChapter();
		if (planID != null)
			pc.setPlanID(planID);
		if (tmpDetailID != null)
			pc.setTmpDetailID(tmpDetailID);
		if (weekEndTime != null)
			pc.setWeekEndTime(weekEndTime);
		if (weekStartTime != null)
			pc.setWeekStartTime(weekStartTime);
		if (planWeek != null)
			pc.setPlanWeek(planWeek);
		if (startTime != null)
			pc.setStartTime(startTime);
		if (endTime != null)
			pc.setEndTime(endTime);
		return pc;
	}

	public String toString() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String rtnStr = "";
		if (planID != null)
			rtnStr += this.planID;
		rtnStr += ",";
		if (planID != null)
			rtnStr += tmpDetailID;
		rtnStr += ",";
		if (startTime != null)
			rtnStr += df.format(startTime);
		rtnStr += ",";
		if (endTime != null)
			rtnStr += df.format(endTime);
		rtnStr += ",";
		if (planWeek != null)
			rtnStr += planWeek;
		rtnStr += ",";
		if (weekStartTime != null)
			rtnStr += df.format(weekStartTime);
		rtnStr += ",";
		if (weekEndTime != null)
			rtnStr += df.format(weekEndTime);
		return rtnStr;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Short getIsOld() {
		return isOld;
	}

	public void setIsOld(Short isOld) {
		this.isOld = isOld;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((planChpID == null) ? 0 : planChpID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlanChapter other = (PlanChapter) obj;
		if (planChpID == null) {
			if (other.planChpID != null)
				return false;
		} else if (!planChpID.equals(other.planChpID))
			return false;
		return true;
	}

}