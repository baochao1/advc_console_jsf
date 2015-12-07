package com.cdel.advc.memberclass.domain;

import java.io.Serializable;
import java.util.Date;

import com.chnedu.plat.rad.domain.BaseDomain;

@SuppressWarnings("serial")
public class PhoneNumberLog  extends BaseDomain implements Serializable {
	private Integer siteID;
	private Integer classID;
	private Integer memberID;
	private String oldphonenumber;
	private String newphonenumber;
	private Integer updaterID;
	private Date updateTime;
	public Integer getClassID() {
		return classID;
	}
	public void setClassID(Integer classID) {
		this.classID = classID;
	}
	public Integer getMemberID() {
		return memberID;
	}
	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}
	public String getOldphonenumber() {
		return oldphonenumber;
	}
	public void setOldphonenumber(String oldphonenumber) {
		this.oldphonenumber = oldphonenumber;
	}
	public String getNewphonenumber() {
		return newphonenumber;
	}
	public void setNewphonenumber(String newphonenumber) {
		this.newphonenumber = newphonenumber;
	}
	public Integer getUpdaterID() {
		return updaterID;
	}
	public void setUpdaterID(Integer updaterID) {
		this.updaterID = updaterID;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getSiteID() {
		return siteID;
	}
	public void setSiteID(Integer siteID) {
		this.siteID = siteID;
	}
	
}
