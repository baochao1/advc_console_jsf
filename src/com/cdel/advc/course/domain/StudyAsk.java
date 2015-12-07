package com.cdel.advc.course.domain;

import java.io.Serializable;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * Domain Object.学习要求
 * 
 * @version 1.0 2009-4-13
 * @author Arthur Liu - arthurliu@chinaacc.com
 */
@SuppressWarnings("serial")
public class StudyAsk extends BaseDomain implements Serializable {

	private Short suggestStyTime;
	private Short suggestStyTime4Hour;
	private Short type;
	private Integer chapterID;
	private String content;
	private Integer askID;
	private String studyAsk1;
	private String studyAsk2;
	private String studyAsk3;
	private String studyAsk4;

	public void setStudyAsk1(String studyAsk1) {
		this.studyAsk1 = studyAsk1;
	}

	public void setStudyAsk2(String studyAsk2) {
		this.studyAsk2 = studyAsk2;
	}

	public void setStudyAsk3(String studyAsk3) {
		this.studyAsk3 = studyAsk3;
	}

	public void setStudyAsk4(String studyAsk4) {
		this.studyAsk4 = studyAsk4;
	}

	public String getStudyAsk1() {
		return studyAsk1;
	}

	public String getStudyAsk2() {
		return studyAsk2;
	}

	public String getStudyAsk3() {
		return studyAsk3;
	}

	public String getStudyAsk4() {
		return studyAsk4;
	}

	public Short getSuggestStyTime() {
		return suggestStyTime;
	}

	public void setSuggestStyTime(Short suggestStyTime) {
		this.suggestStyTime = suggestStyTime;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Integer getChapterID() {
		return chapterID;
	}

	public void setChapterID(Integer chapterID) {
		this.chapterID = chapterID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getAskID() {
		return askID;
	}

	public void setAskID(Integer askID) {
		this.askID = askID;
	}

	public Short getSuggestStyTime4Hour() {
		return suggestStyTime4Hour;
	}

	public Integer getSuggestStyTime4HourList() {
		if (suggestStyTime != null) {
			return suggestStyTime / 60;
		} else {
			return null;
		}
	}

	public void setSuggestStyTime4Hour(Short suggestStyTime4Hour) {
		this.suggestStyTime4Hour = suggestStyTime4Hour;
	}

	public String getTypeStr() {
		if (type == 1) {
			return "一门";
		} else if (type == 2) {
			return "二门";
		} else if (type == 3) {
			return "三门";
		} else if (type == 4) {
			return "四门以上";
		} else {
			return "无法确定";
		}
	}

}