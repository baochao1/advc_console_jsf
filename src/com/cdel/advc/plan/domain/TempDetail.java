package com.cdel.advc.plan.domain;

import java.io.Serializable;
import java.util.Date;

import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * Domain Object.学习计划模板
 * 
 * @version 1.0 2009-4-13
 * @author 张苏磊
 */
@SuppressWarnings("serial")
public class TempDetail extends BaseDomain implements Serializable {

	private String studyAsk;
	private String studyAsk1;
	private String studyAsk2;
	private String studyAsk3;
	private String studyAsk4;
	private Integer studyAskFlag;
	private Short suggestStyTime;
	private Integer templateID;
	private Short sequence;
	private Integer tmpDetailID;
	private Integer chapterID;
	private String chapterName;
	private String templateName;
	private Short phaseNo;
	private Short openStatus;
	private Date openTime;
	private String noteName;
	private String chapterNo;
	private String courseName;
	private Short isLock;
	private Integer isPreChapter;

	private String chapterStudyAsk;

	public String getChapterStudyAsk() {
		return chapterStudyAsk;
	}

	public void setChapterStudyAsk(String chapterStudyAsk) {
		this.chapterStudyAsk = chapterStudyAsk;
	}

	public Integer getIsPreChapter() {
		return isPreChapter;
	}

	public void setIsPreChapter(Integer isPreChapter) {
		this.isPreChapter = isPreChapter;
	}

	public String getStudyAsk() {

		return studyAsk;
	}

	public void setStudyAsk(String studyAsk) {
		this.studyAsk = studyAsk;
	}

	public Short getSuggestStyTime() {
		return suggestStyTime;
	}

	public void setSuggestStyTime(Short suggestStyTime) {
		this.suggestStyTime = suggestStyTime;
	}

	public Integer getTemplateID() {
		return templateID;
	}

	public void setTemplateID(Integer templateID) {
		this.templateID = templateID;
	}

	public Short getSequence() {
		return sequence;
	}

	public void setSequence(Short sequence) {
		this.sequence = sequence;
	}

	public Integer getTmpDetailID() {
		return tmpDetailID;
	}

	public void setTmpDetailID(Integer tmpDetailID) {
		this.tmpDetailID = tmpDetailID;
	}

	public Integer getChapterID() {
		return chapterID;
	}

	public void setChapterID(Integer chapterID) {
		this.chapterID = chapterID;
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

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getChapterNo() {
		return chapterNo;
	}

	public void setChapterNo(String chapterNo) {
		this.chapterNo = chapterNo;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getNoteName() {
		return noteName;
	}

	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}

	public Short getIsLock() {
		return isLock;
	}

	public void setIsLock(Short isLock) {
		this.isLock = isLock;
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

	public Integer getStudyAskFlag() {
		return studyAskFlag;
	}

	public void setStudyAskFlag(Integer studyAskFlag) {
		this.studyAskFlag = studyAskFlag;
	}

}