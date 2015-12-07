package com.cdel.plat.grant.domain;

import java.io.Serializable;
import java.util.Date;

import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * Class description goes here.
 * 
 * @version 1.0 2008-1-17
 * @author LiXuFang - j2eeli@chinaacc.com
 */
public class Privilege extends BaseDomain implements Serializable,
		Comparable<Privilege> {
	private static final long serialVersionUID = -909287480225229712L;

	private Integer privilegeID;
	private String privilegeName;
	private String privilegeCategory;
	private int treeType;
	private Integer parentID;
	private String parentName;
	private int treeLevel;
	private String actionURL;
	private Integer neighborID;
	private String description;
	private Integer creator;
	private String username;
	private Date createTime;
	private int sequence;
	private Integer systemType;
	private Integer priType;
	private Integer roleID;
	private Integer adminID;

	public Integer getPrivilegeID() {
		return privilegeID;
	}

	public void setPrivilegeID(Integer privilegeID) {
		this.privilegeID = privilegeID;
	}

	public String getPrivilegeName() {
		return privilegeName;
	}

	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	public String getPrivilegeCategory() {
		return privilegeCategory;
	}

	public void setPrivilegeCategory(String privilegeCategory) {
		this.privilegeCategory = privilegeCategory;
	}

	public int getTreeType() {
		return treeType;
	}

	public void setTreeType(int treeType) {
		this.treeType = treeType;
	}

	public Integer getParentID() {
		return parentID;
	}

	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}

	public int getTreeLevel() {
		return treeLevel;
	}

	public void setTreeLevel(int treeLevel) {
		this.treeLevel = treeLevel;
	}

	public String getActionURL() {
		return actionURL;
	}

	public void setActionURL(String actionURL) {
		this.actionURL = actionURL;
	}

	public Integer getNeighborID() {
		return neighborID;
	}

	public void setNeighborID(Integer neighborID) {
		this.neighborID = neighborID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Integer getSystemType() {
		return systemType;
	}

	public void setSystemType(Integer systemType) {
		this.systemType = systemType;
	}

	public Integer getPriType() {
		return priType;
	}

	public void setPriType(Integer priType) {
		this.priType = priType;
	}

	public Integer getRoleID() {
		return roleID;
	}

	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}

	public Integer getAdminID() {
		return adminID;
	}

	public void setAdminID(Integer adminID) {
		this.adminID = adminID;
	}

	@Override
	public int compareTo(Privilege p) {
		if (this.sequence > p.sequence) {
			return 1;
		} else if (this.sequence < p.sequence) {
			return -1;
		} else {
			return 0;
		}
	}

}