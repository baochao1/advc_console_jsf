package com.cdel.advc.qzUploadFile.domain;

import java.util.Date;

import com.chnedu.plat.rad.domain.BaseDomain;

@SuppressWarnings("serial")
public class QzUploadFile extends BaseDomain implements java.io.Serializable{
	private Integer id;
	private String fileUrl;
	private Integer creator;
	private Date createTime;
	private String realName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
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
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
}
