package com.cdel.advc.teacherorg.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.cdel.advc.teacher.domain.Teacher;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 
 * <p>
 * 教师组别（组织）实体
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-2 下午3:30:46
 */
@SuppressWarnings("serial")
public class TeacherOrg extends BaseDomain implements Serializable {

	private Integer orgID;// ID
	
	private Integer parentID;// 父ID
	
	private String orgName;// 组别(组织)名称
	
	private Short faqStatus;//????
	
	private Short level;// 级别
	
	private Date createTime;// 创建时间

	// ----- vo属性 -------//
	private String parentName;//
	
	private List<Teacher> teachers;
	
	
	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public Integer getOrgID() {
		return orgID;
	}

	public void setOrgID(Integer orgID) {
		this.orgID = orgID;
	}

	public Integer getParentID() {
		return parentID;
	}

	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Short getFaqStatus() {
		return faqStatus;
	}

	public void setFaqStatus(Short faqStatus) {
		this.faqStatus = faqStatus;
	}

	public Short getLevel() {
		return level;
	}

	public void setLevel(Short level) {
		this.level = level;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getCreateTimeStr() {
		return DateUtil.getNowDateString(this.createTime, "yyyy-DD-mm");
	}
}
