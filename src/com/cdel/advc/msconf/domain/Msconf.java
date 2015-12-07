package com.cdel.advc.msconf.domain;

import java.io.Serializable;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 面授班
 * 
 * @author 张苏磊
 */
@SuppressWarnings("serial")
public class Msconf extends BaseDomain implements Serializable {

	private String mskb;
	private Integer classID;
	private String mshy;
	private Integer creator;
	private String mstxl;
	private Integer status;
	private Integer updator;
	private java.util.Date creatTime;
	private java.util.Date updateTime;
	private java.util.Date msEndTime;
	private java.util.Date msStartTime;
	private java.util.Date tzTime;

	public java.util.Date getTzTime() {
		return tzTime;
	}

	public void setTzTime(java.util.Date tzTime) {
		this.tzTime = tzTime;
	}

	public String getMskb() {
		return mskb;
	}

	public void setMskb(String mskb) {
		this.mskb = mskb;
	}

	public Integer getClassID() {
		return classID;
	}

	public void setClassID(Integer classID) {
		this.classID = classID;
	}

	public String getMshy() {
		return mshy;
	}

	public void setMshy(String mshy) {
		this.mshy = mshy;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public String getMstxl() {
		return mstxl;
	}

	public void setMstxl(String mstxl) {
		this.mstxl = mstxl;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getUpdator() {
		return updator;
	}

	public void setUpdator(Integer updator) {
		this.updator = updator;
	}

	public java.util.Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(java.util.Date creatTime) {
		this.creatTime = creatTime;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	public java.util.Date getMsEndTime() {
		return msEndTime;
	}

	public void setMsEndTime(java.util.Date msEndTime) {
		this.msEndTime = msEndTime;
	}

	public java.util.Date getMsStartTime() {
		return msStartTime;
	}

	public void setMsStartTime(java.util.Date msStartTime) {
		this.msStartTime = msStartTime;
	}

}