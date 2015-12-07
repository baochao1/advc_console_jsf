package com.cdel.advc.major.domain;

import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 辅导的当前班级数
 * 
 * @author zhangsulei
 */

public class ClassNo extends BaseDomain implements java.io.Serializable {
	private static final long serialVersionUID = 7193900830270819888L;

	private Integer majorID;
	private Short classType;// 类型1:实验,2:精品,3:混合
	private Short currClassNo;// 当前的班级数
	private String classTypeName;// 类型1:实验,2:精品,3:混合

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}

	public Short getClassType() {
		return classType;
	}

	public void setClassType(Short classType) {
		this.classType = classType;
	}

	public Short getCurrClassNo() {
		return currClassNo;
	}

	public void setCurrClassNo(Short currClassNo) {
		this.currClassNo = currClassNo;
	}

	public String getClassTypeName() {
		return classTypeName;
	}

	public void setClassTypeName(String classTypeName) {
		this.classTypeName = classTypeName;
	}

}