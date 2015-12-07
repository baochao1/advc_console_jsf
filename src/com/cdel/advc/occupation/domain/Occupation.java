package com.cdel.advc.occupation.domain;

import java.io.Serializable;
import java.util.Date;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * Domain Object. 职业
 * 
 * @author 张苏磊
 */
@SuppressWarnings("serial")
public class Occupation extends BaseDomain implements Serializable {

	private Integer occupationID;
	private Integer creator;
	private Integer siteID;
	private String occupationName;
	private Date createTime;
	private String siteName;

	public Integer getOccupationID() {
		return occupationID;
	}

	public void setOccupationID(Integer occupationID) {
		this.occupationID = occupationID;
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

	public String getOccupationName() {
		return occupationName;
	}

	public void setOccupationName(String occupationName) {
		this.occupationName = occupationName;
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