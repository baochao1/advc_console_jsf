package com.cdel.advc.report.domain;

/**
 * Domain Object. 学习报告评语
 * 
 * @version 1.0 2009-4-13
 * @author 张苏磊
 */
@SuppressWarnings("serial")
public class ReportRemark extends Report {

	private Short classQueryType;// 是否在用一组
	private String manager;// 班级管理员
	private Integer majorID;

	public Short getClassQueryType() {
		return classQueryType;
	}

	public void setClassQueryType(Short classQueryType) {
		this.classQueryType = classQueryType;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}

}