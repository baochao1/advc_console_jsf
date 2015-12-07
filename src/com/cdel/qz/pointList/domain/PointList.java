package com.cdel.qz.pointList.domain;

import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 知识点策略
 * 
 * @author 张苏磊
 */

@SuppressWarnings("serial")
public class PointList extends BaseDomain implements java.io.Serializable {
	private Integer pointListID;
	private String pointListName;
	private Integer courseID;

	public Integer getPointListID() {
		return pointListID;
	}

	public void setPointListID(Integer pointListID) {
		this.pointListID = pointListID;
	}

	public String getPointListName() {
		return pointListName;
	}

	public void setPointListName(String pointListName) {
		this.pointListName = pointListName;
	}

	public Integer getCourseID() {
		return courseID;
	}

	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}

}