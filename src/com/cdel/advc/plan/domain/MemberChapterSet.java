package com.cdel.advc.plan.domain;

import java.io.Serializable;
import com.cdel.util.CodeUtil;
import com.cdel.util.Contants;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * <p>
 * 计划项学习要求设置 实体
 * </p>
 * 
 * @author Du Haiying Create at:2013-8-15 下午2:36:27
 */
@SuppressWarnings("serial")
public class MemberChapterSet extends BaseDomain implements Serializable {

	private Integer chapterSetID;

	private Integer chapterID;

	private Integer userID;

	/** 建议时间 */
	private Short suggestStyTime;

	/** 学员建议 */
	private String studyAsk;

	/** 是否预习计划章节标识 */
	private Short preChapter;

	/** 学习计划章节正在学习或已停止学习标识 */
	private Short isStudy;

	// -------------- vo dto ------------------------//
	/** 学习提示 */
	private String studyAsk1;

	/** 学习建议 */
	private String studyAsk2;

	/** 完成标准 */
	private String studyAsk3;

	/** 备注 */
	private String studyAsk4;

	/** 建议时间小时属性值 */
	private Double suggestStyTimeHour;

	// --------------- dto------------//
	/** 主计划ID */
	private Integer planID;
	private Short type;
	private String chapterName;
	private Integer creator;
	private String creatorName;
	private Integer sequence;
	private String courseName;
	private Short phaseNo;
	private Short openStatus;
	private Integer planChpID;
	private String courseIDs;

	public Double getSuggestStyTimeHour() {
		return suggestStyTimeHour;
	}

	public void setSuggestStyTimeHour(Double suggestStyTimeHour) {
		this.suggestStyTimeHour = suggestStyTimeHour;
		this.suggestStyTime = (short) (60 * this.suggestStyTimeHour);
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public String getStudyAsk1() {
		return studyAsk1;
	}

	public void setStudyAsk1(String studyAsk1) {
		this.studyAsk1 = studyAsk1;
	}

	public String getStudyAsk2() {
		return studyAsk2;
	}

	public void setStudyAsk2(String studyAsk2) {
		this.studyAsk2 = studyAsk2;
	}

	public String getStudyAsk3() {
		return studyAsk3;
	}

	public void setStudyAsk3(String studyAsk3) {
		this.studyAsk3 = studyAsk3;
	}

	public String getStudyAsk4() {
		return studyAsk4;
	}

	public void setStudyAsk4(String studyAsk4) {
		this.studyAsk4 = studyAsk4;
	}

	public Short getSuggestStyTime() {
		return suggestStyTime;
	}

	public void setSuggestStyTime(Short suggestStyTime) {
		this.suggestStyTime = suggestStyTime;
		this.suggestStyTimeHour = CodeUtil
				.minute2HourNumber(this.suggestStyTime);
	}

	public Integer getChapterSetID() {
		return chapterSetID;
	}

	public void setChapterSetID(Integer chapterSetID) {
		this.chapterSetID = chapterSetID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getStudyAsk() {
		return studyAsk;
	}

	public void setStudyAsk(String studyAsk) {
		this.studyAsk = studyAsk;
	}

	public Integer getChapterID() {
		return chapterID;
	}

	public void setChapterID(Integer chapterID) {
		this.chapterID = chapterID;
	}

	public Short getPreChapter() {
		return preChapter;
	}

	public void setPreChapter(Short preChapter) {
		this.preChapter = preChapter;
	}

	public Short getIsStudy() {
		return isStudy;
	}

	public void setIsStudy(Short isStudy) {
		this.isStudy = isStudy;
	}

	public Integer getPlanID() {
		return planID;
	}

	public void setPlanID(Integer planID) {
		this.planID = planID;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
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

	public String getIsStudyStr() {
		if (this.isStudy == null || this.isStudy == 1) {
			return "在学习";
		} else {
			return "已删除";
		}
	}

	/** 开通状态显示值 */
	public String getOpenStatusStr() {
		if (this.openStatus == null) {
			return "";
		}
		return Contants.planChapterOpenStatus.get(this.openStatus);
	}

	public Integer getPlanChpID() {
		return planChpID;
	}

	public void setPlanChpID(Integer planChpID) {
		this.planChpID = planChpID;
	}

	public String getCourseIDs() {
		return courseIDs;
	}

	public void setCourseIDs(String courseIDs) {
		this.courseIDs = courseIDs;
	}

}