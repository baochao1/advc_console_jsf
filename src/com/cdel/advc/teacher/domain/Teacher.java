/*                                                                    
 * Copyright (c) 2000-2009 CDELEDU.COM. All Rights Reserved.           
 *                                                                    
 * This software is the confidential and proprietary information of   
 * CDELEDU.COM. ("Confidential Information"). You shall not          
 * disclose such Confidential Information and shall use it only in    
 * accordance with the terms of the license agreement you entered into
 * with CDELEDU.COM.                                                   
 *                                                                    
 */
package com.cdel.advc.teacher.domain;

import java.io.Serializable;
import java.util.ArrayList;

import com.cdel.util.Contants;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * Domain Object. 教师
 * 
 * @version 1.0 2013-7-8
 * @author Arthur 张苏磊
 */
@SuppressWarnings("serial")
public class Teacher extends BaseDomain implements Serializable {

	/** 教师ID */
	private Integer teacherID;

	/** 教师姓名 */
	private String teacherName;

	/** 教师编码 */
	private String teacherCode;

	/** 教师机构组织ID */
	private Integer orgID;

	/** 是否组织头目 */
	private Short isHeader;

	/**
	 * 是否有班级高级管理权限 ：0无;1有. 是否有班级高级管理权限（改班级名称、批量开放关闭班级）
	 */
	private Short hasClass;

	private Short status;

	// ------------- vo dto-----------//

	/** 管理员职责描述 */
	private String teacherDesc;

	/** 教师类型：1 主管理员，0管理员 */
	private Short teacherType;

	/** 组织名称 */
	private String orgName;

	/** 教师所在机构的parentID */
	private Integer parentID;

	private Integer isChecked;

	/** 是否是主管理员标识 显示 */
	public String getTeacherTypeStr() {
		if (this.teacherType == 1) {
			return "(主)";
		} else {
			return "";
		}
	}

	public String getMainManagerStr() {
		if (this.teacherType == null) {
			return "";
		}
		if (this.teacherType == 1) {
			return "checked";
		} else {
			return "";
		}
	}

	public boolean isManager() {

		return new ArrayList<Integer>().contains(this.teacherID);

	}

	public String getIsHeaderStr() {
		if (isHeader == null) {
			return "";
		} else {
			return Contants.teacherHeader.get(isHeader);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((teacherID == null) ? 0 : teacherID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Teacher other = (Teacher) obj;
		if (teacherID == null) {
			if (other.teacherID != null)
				return false;
		} else if (!teacherID.equals(other.teacherID))
			return false;
		return true;
	}

	public Integer getParentID() {
		return parentID;
	}

	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}

	public Integer getOrgID() {
		return orgID;
	}

	public void setOrgID(Integer orgID) {
		this.orgID = orgID;
	}

	public Short getIsHeader() {
		return isHeader;
	}

	public void setIsHeader(Short isHeader) {
		this.isHeader = isHeader;
	}

	public Short getHasClass() {
		return hasClass;
	}

	public void setHasClass(Short hasClass) {
		this.hasClass = hasClass;
	}

	public String getTeacherDesc() {
		return teacherDesc;
	}

	public void setTeacherDesc(String teacherDesc) {
		this.teacherDesc = teacherDesc;
	}

	public Short getTeacherType() {
		return teacherType;
	}

	public void setTeacherType(Short teacherType) {
		this.teacherType = teacherType;
	}

	public Integer getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Integer isChecked) {
		this.isChecked = isChecked;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Integer getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(Integer teacherID) {
		this.teacherID = teacherID;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherCode() {
		return teacherCode;
	}

	public void setTeacherCode(String teacherCode) {
		this.teacherCode = teacherCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}