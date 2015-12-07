package com.cdel.advc.adminTeacher.domain;

import java.io.Serializable;

import com.chnedu.plat.rad.domain.BaseDomain;
 
@SuppressWarnings("serial")
public class AdminStatis  extends BaseDomain implements Serializable{
	
	private Integer teacherID;
	private Integer adviserClassNum;
	private Integer adviserMemberTotal;
	private Integer adminClassNum;
	private Integer adminMemberTotal;

	private String teacherCode;
	private String teacherName;
	private Integer status;
	private Integer isHeader;
	private Integer orgID;
	private String orgName;
	private Integer parentID;
	
	private Integer  groupID;
	private Integer  groupName;
	
	private Integer adviserClassSum;// 班主任班级总和
	private Integer adviserMemberSum;// 班主任管理的学员总和
	private Integer adminClassSum;// 管理员管理班级的总和
	private Integer adminMemberSum;// 管理员管理学员的总和
	public Integer getTeacherID() {
		return teacherID;
	}
	public void setTeacherID(Integer teacherID) {
		this.teacherID = teacherID;
	}
	public Integer getAdviserClassNum() {
		return adviserClassNum == null ? 0:adviserClassNum;
	}
	public void setAdviserClassNum(Integer adviserClassNum) {
		this.adviserClassNum = adviserClassNum;
	}
	public Integer getAdviserMemberTotal() {
		return adviserMemberTotal == null ? 0:adviserMemberTotal;
	}
	public void setAdviserMemberTotal(Integer adviserMemberTotal) {
		this.adviserMemberTotal = adviserMemberTotal;
	}
	public Integer getAdminClassNum() {
		return adminClassNum == null ? 0:adminClassNum;
	}
	public void setAdminClassNum(Integer adminClassNum) {
		this.adminClassNum = adminClassNum;
	}
	public Integer getAdminMemberTotal() {
		return adminMemberTotal == null ? 0:adminMemberTotal;
	}
	public void setAdminMemberTotal(Integer adminMemberTotal) {
		this.adminMemberTotal = adminMemberTotal;
	}
	public String getTeacherCode() {
		return teacherCode;
	}
	public void setTeacherCode(String teacherCode) {
		this.teacherCode = teacherCode;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIsHeader() {
		return isHeader;
	}
	public void setIsHeader(Integer isHeader) {
		this.isHeader = isHeader;
	}
	public Integer getOrgID() {
		return orgID;
	}
	public void setOrgID(Integer orgID) {
		
		this.orgID = orgID;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public Integer getParentID() {
		return parentID;
	}
	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}
	public Integer getGroupID() {
		return groupID;
	}
	public void setGroupID(Integer groupID) {
		this.groupID = groupID;
	}
	public Integer getGroupName() {
		return groupName;
	}
	public void setGroupName(Integer groupName) {
		this.groupName = groupName;
	}
	public Integer getAdviserClassSum() {
		return adviserClassSum+=adviserClassNum; //只有一个小组
	}
	public void setAdviserClassSum(Integer adviserClassSum) {
		this.adviserClassSum = adviserClassSum;
	}
	public Integer getAdviserMemberSum() {
		return adviserMemberSum+=adviserMemberTotal;
	}
	public void setAdviserMemberSum(Integer adviserMemberSum) {
		this.adviserMemberSum = adviserMemberSum;
	}
	public Integer getAdminClassSum() {
		return adminClassSum+=adminClassNum;
	}
	public void setAdminClassSum(Integer adminClassSum) {
		this.adminClassSum = adminClassSum;
	}
	public Integer getAdminMemberSum() {
		return adminMemberSum+=adminMemberTotal;
	}
	public void setAdminMemberSum(Integer adminMemberSum) {
		this.adminMemberSum = adminMemberSum;
	}
	
	
}
