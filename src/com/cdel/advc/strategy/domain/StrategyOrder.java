package com.cdel.advc.strategy.domain;

import java.io.Serializable;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 策略里的课程排序domain
 * 
 * @author:张苏磊
 */
@SuppressWarnings("serial")
public class StrategyOrder extends BaseDomain implements Serializable {
	private Integer strategyID;
	private Integer courseID;
	private Integer sequence;
	private Short status;

	private String courseName;
	private String courseIDs;

	public Integer getStrategyID() {
		return strategyID;
	}

	public void setStrategyID(Integer strategyID) {
		this.strategyID = strategyID;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Integer getCourseID() {
		return courseID;
	}

	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseIDs() {
		return courseIDs;
	}

	public void setCourseIDs(String courseIDs) {
		this.courseIDs = courseIDs;
	}

}