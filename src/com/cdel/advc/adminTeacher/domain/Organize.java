package com.cdel.advc.adminTeacher.domain;

import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * Organize entity. 
 * @author xuxiaoguang
 */
@SuppressWarnings("serial")
public class Organize extends BaseDomain implements java.io.Serializable {

	private Integer orgID;
	private String orgName;
	private Integer  parentID;
	private Integer  groupID;
	private String  groupName;
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
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	
}
