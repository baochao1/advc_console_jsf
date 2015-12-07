package com.cdel.advc.specialty.domain;

import java.io.Serializable;
import java.util.Date;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * Domain 专业
 * 
 * @author zhangsulei
 */
@SuppressWarnings("serial")
public class Specialty extends BaseDomain implements Serializable {

	private Integer specialtyID;
	private Integer creator;
	private Integer siteID;
	private String specialtyName;
	private Date createTime;
	private String siteName;

	private String creatorName;

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Integer getSpecialtyID() {
		return specialtyID;
	}

	public void setSpecialtyID(Integer specialtyID) {
		this.specialtyID = specialtyID;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Integer getSiteID() {
		return siteID;
	}

	public void setSiteID(Integer siteID) {
		this.siteID = siteID;
	}

	public String getSpecialtyName() {
		return specialtyName;
	}

	public void setSpecialtyName(String specialtyName) {
		this.specialtyName = specialtyName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

}