package com.cdel.api.callCenter.member;

import java.io.Serializable;

/**
 * 
 * <p>
 * 学员实体（为呼叫中心定制开发的 DTO VO）
 * </p>
 * 
 * @author Du Haiying Create at:2013-10-14 上午11:53:33
 */
@SuppressWarnings("serial")
public class Member implements Serializable {

	/** 所属网站id**/
	private Integer siteID;
	/** 学员代码 */
	private String userCode;

	/** 手机 */
	private String phone;

	private String className;
	private String userID;
	private String classID;
	private String userName;
	private String telPhone;
	private String firstTime;
	private String status;
	private String createTime;
	private String updateTime;
	 /** 返回的记录数**/
    private Integer count;
    
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getClassID() {
		return classID;
	}

	public void setClassID(String classID) {
		this.classID = classID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	public String getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(String firstTime) {
		this.firstTime = firstTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getSiteID() {
		return siteID;
	}

	public void setSiteID(Integer siteID) {
		this.siteID = siteID;
	}

}