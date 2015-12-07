package com.cdel.advc.sitenotice.domain;

import java.io.Serializable;
import java.util.Date;
import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * Domain Object.系统公告
 * 
 * @author 张苏磊
 */
public class Sitenotice extends BaseDomain implements Serializable {

	private static final long serialVersionUID = 9165332927190741150L;

	private Integer siteNoticeID;
	private String siteName;
	private Integer siteID;
	private String subject;
	private Short type;
	private String creatorName;
	private Date createTime;
	private Integer creator;
	private Date startTime;
	private Date endTime;
	private String content;
	private Short status;

	public Integer getSiteNoticeID() {
		return siteNoticeID;
	}

	public void setSiteNoticeID(Integer siteNoticeID) {
		this.siteNoticeID = siteNoticeID;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public Integer getSiteID() {
		return siteID;
	}

	public void setSiteID(Integer siteID) {
		this.siteID = siteID;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getTypeStr() {
		if (this.type == null || this.type == 0) {
			return "";
		}
		return Contants.sitenoticeType.get(this.type);
	}

	public String getStatusStr() {
		if (this.status == null) {
			return "";
		}
		return Contants.status.get(this.status);
	}

	public String getCreateTimeStr() {
		return DateUtil.getNowDateString(createTime, "yyyy-MM-dd HH:mm");
	}

	public String getStartTimeStr() {
		return DateUtil.getNowDateString(startTime, "yyyy-MM-dd HH:mm");
	}

	public String getEndTimeStr() {
		return DateUtil.getNowDateString(endTime, "yyyy-MM-dd HH:mm");
	}

}