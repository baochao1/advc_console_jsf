package com.cdel.advc.major.domain;

import java.util.Date;

import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * QzMajor entity.
 * 
 * @author zhangsulei
 */

public class Major extends BaseDomain implements java.io.Serializable {
	private static final long serialVersionUID = 7193900830270819888L;
	private Integer majorID;
	private String majorName;
	private Integer businessID;
	private Integer siteID;
	private String siteName;
	private String shortName;// 简称
	private Short status;
	private Integer sequence;
	private Integer creator;
	private Date createTime;
	private String businessName;
	private String creatorName;// 创建者名字
	private Short reportDateSpace;// 学习报告生成间隔时间
	private Short isNewService;// 是否开启短信发送
	private String planSmsTemplate;// 短信模板
	private Date examDate;// 考试日期
	private Short roomStatus;// 聊天室
	private Short enterRoom;// 是否可以进入聊天室
	private Short reportStatus;// 是否开启学习报告
	private Date reportDate;// 报告生成时间

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public Integer getBusinessID() {
		return businessID;
	}

	public void setBusinessID(Integer businessID) {
		this.businessID = businessID;
	}

	public Integer getSiteID() {
		return siteID;
	}

	public void setSiteID(Integer siteID) {
		this.siteID = siteID;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getStatusStr() {
		if (status == null) {
			return "";
		} else {
			return Contants.status.get(status);
		}
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
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

	public String getCreateTimeStr() {
		return DateUtil.getNowDateString(getCreateTime(), "yyyy-MM-dd HH:mm");
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Short getReportDateSpace() {
		return reportDateSpace;
	}

	public void setReportDateSpace(Short reportDateSpace) {
		this.reportDateSpace = reportDateSpace;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public Short getIsNewService() {
		return isNewService;
	}

	public void setIsNewService(Short isNewService) {
		this.isNewService = isNewService;
	}

	public String getPlanSmsTemplate() {
		return planSmsTemplate;
	}

	public void setPlanSmsTemplate(String planSmsTemplate) {
		this.planSmsTemplate = planSmsTemplate;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public Short getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(Short roomStatus) {
		this.roomStatus = roomStatus;
	}

	public Short getEnterRoom() {
		return enterRoom;
	}

	public void setEnterRoom(Short enterRoom) {
		this.enterRoom = enterRoom;
	}

	public Short getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(Short reportStatus) {
		this.reportStatus = reportStatus;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public String getReportDateStr() {
		return DateUtil.getNowDateString(getReportDate(), "yyyy-MM-dd");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((majorID == null) ? 0 : majorID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Major))
			return false;
		Major other = (Major) obj;
		if (majorID == null) {
			if (other.majorID != null)
				return false;
		} else if (!majorID.equals(other.majorID))
			return false;
		return true;
	}

}