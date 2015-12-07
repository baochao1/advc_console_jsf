package com.cdel.advc.memberdefine.domain;

import java.io.Serializable;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 已往学习经历
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
public class Examkill extends BaseDomain implements Serializable {
	private String examName;// 考试课程
	private String year;// 考试年度
	private String score;// 成绩

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

}