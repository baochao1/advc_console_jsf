package com.cdel.advc.plan.domain;

import java.io.Serializable;
import com.cdel.util.Contants;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 
 * <p>
 * 学习计划章节排序
 * </p>
 * 
 * @author 张苏磊
 */
@SuppressWarnings("serial")
public class PlanChapterSequenceSet extends BaseDomain implements Serializable {

	private Integer sequence;
	private Integer planID;
	private Integer chapterID;
	// -------------vo-------------------
	private String courseName;
	private String chapterName;
	private Short phaseNo;
	private Short openStatus;

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Integer getPlanID() {
		return planID;
	}

	public void setPlanID(Integer planID) {
		this.planID = planID;
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

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
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

	/** 开通状态显示值 */
	public String getOpenStatusStr() {
		if (this.openStatus == null) {
			return "";
		}
		return Contants.planChapterOpenStatus.get(this.openStatus);
	}

}