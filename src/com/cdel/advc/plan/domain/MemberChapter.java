package com.cdel.advc.plan.domain;

import java.io.Serializable;
import java.util.Date;

import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 
 * <p>学员章节完成进度</p>
 * 
 * @author Du Haiying
 * Create at:2013-8-2 上午11:16:44
 */
@SuppressWarnings("serial")
public class MemberChapter extends BaseDomain implements Serializable {
	
	
	/** 章节编号 */
	private Integer chapterID;
	
	/** 计划ID */
	private Integer planID;
	
	/** 章节编号 */
	private Integer userID;
	
	/** 完成状态 */
	private Short finishStatus;
	
	/** 完成时间 */
	private Date finishTime;

	public Integer getChapterID() {
		return chapterID;
	}

	public void setChapterID(Integer chapterID) {
		this.chapterID = chapterID;
	}

	public Integer getPlanID() {
		return planID;
	}

	public void setPlanID(Integer planID) {
		this.planID = planID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
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
	
		


}