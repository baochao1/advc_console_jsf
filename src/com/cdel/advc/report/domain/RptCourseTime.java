package com.cdel.advc.report.domain;

import java.io.Serializable;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 
 * <p>
 * 学习报告 课程学习时长 统计
 * </p>
 * 
 * @author Du Haiying Create at:2013-8-26 上午11:57:46
 */
@SuppressWarnings("serial")
public class RptCourseTime extends BaseDomain implements Serializable {

	/** ID */
	private Integer courseTimeID;

	private Integer reportID;

	private String courseName;

	/** 总计时长 */
	private Integer timeLong;

	public Integer getCourseTimeID() {
		return courseTimeID;
	}

	public void setCourseTimeID(Integer courseTimeID) {
		this.courseTimeID = courseTimeID;
	}

	public Integer getTimeLong() {
		return timeLong;
	}

	public void setTimeLong(Integer timeLong) {
		this.timeLong = timeLong;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getReportID() {
		return reportID;
	}

	public void setReportID(Integer reportID) {
		this.reportID = reportID;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}