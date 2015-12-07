package com.cdel.qz.siteCourse.domain;

import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * QzSiteCourse entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class QzSiteCourse extends BaseDomain implements java.io.Serializable {
	private Integer siteCourseID;
	private String siteCourseName;
	private Short serverType;

	public Integer getSiteCourseID() {
		return siteCourseID;
	}

	public void setSiteCourseID(Integer siteCourseID) {
		this.siteCourseID = siteCourseID;
	}

	public String getSiteCourseName() {
		return siteCourseName;
	}

	public void setSiteCourseName(String siteCourseName) {
		this.siteCourseName = siteCourseName;
	}

	public Short getServerType() {
		return serverType;
	}

	public void setServerType(Short serverType) {
		this.serverType = serverType;
	}

}