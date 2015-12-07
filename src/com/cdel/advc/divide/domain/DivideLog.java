package com.cdel.advc.divide.domain;

import java.util.Date;

import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

@SuppressWarnings("serial")
public class DivideLog extends BaseDomain implements java.io.Serializable {
	private Integer logID;
	private Integer userID;
	private Date createDate;
	private Integer creator;
	private String logDesc;
	private String teacherName;
	private String operator;

	public DivideLog() {

	}

	public DivideLog(Integer userID, String logDesc, Integer creator,
			String operator) {
		this.userID = userID;
		this.creator = creator;
		this.logDesc = logDesc;
		this.createDate = new Date();
		this.operator = operator;
	}

	public Integer getLogID() {
		return logID;
	}

	public void setLogID(Integer logID) {
		this.logID = logID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public String getCreateDateStr() {
		return DateUtil.getNowDateString(createDate, "yyyy-MM-dd");
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public String getLogDesc() {
		return logDesc;
	}

	public void setLogDesc(String logDesc) {
		this.logDesc = logDesc;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}
