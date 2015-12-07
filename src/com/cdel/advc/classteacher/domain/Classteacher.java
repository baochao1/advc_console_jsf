package com.cdel.advc.classteacher.domain;

import java.io.Serializable;

import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 
 * <p>
 * 班级教师（班主任，助教等） 实体
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-1 上午11:29:05
 */
@SuppressWarnings("serial")
public class Classteacher extends BaseDomain implements Serializable {

	private Integer teacherID;// 教师ID

	private Short teacherType;// 教室类型(0普通、1管理员)

	private String teacherDesc;// 教师描述

	private Integer classID;// 班级ID

	private Integer tutorID;// ID

	// ------vo相关属性-------//

	private String teacherName;// 教师名称

	private String teacherCode;

	private Byte isZhuRen;// 是否班主任

	public Integer getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(Integer teacherID) {
		this.teacherID = teacherID;
	}

	public Short getTeacherType() {
		return teacherType;
	}

	public void setTeacherType(Short teacherType) {
		this.teacherType = teacherType;
	}

	public String getTeacherDesc() {
		return teacherDesc;
	}

	public void setTeacherDesc(String teacherDesc) {
		this.teacherDesc = teacherDesc;
	}

	public Integer getClassID() {
		return classID;
	}

	public void setClassID(Integer classID) {
		this.classID = classID;
	}

	public Integer getTutorID() {
		return tutorID;
	}

	public void setTutorID(Integer tutorID) {
		this.tutorID = tutorID;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Byte getIsZhuRen() {
		return isZhuRen;
	}

	public void setIsZhuRen(Byte isZhuRen) {
		this.isZhuRen = isZhuRen;
	}

	public String getTeacherCode() {
		return teacherCode;
	}

	public void setTeacherCode(String teacherCode) {
		this.teacherCode = teacherCode;
	}

}