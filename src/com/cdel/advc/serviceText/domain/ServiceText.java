package com.cdel.advc.serviceText.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 学习建议实体
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
public class ServiceText extends BaseDomain implements Serializable {

	private Integer textID;
	private String content;
	private Integer serviceID;
	private Integer majorID;
	private Integer courseID;
	private Short status;
	private Date createTime;

	// ----------------------------------------------------------------

	private Integer stageID;
	private String stageName;
	private String serviceName;
	private String majorName;
	private String courseName;

	public Integer getStageID() {
		return stageID;
	}

	public void setStageID(Integer stageID) {
		this.stageID = stageID;
	}

	public Integer getTextID() {
		return textID;
	}

	public void setTextID(Integer textID) {
		this.textID = textID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getServiceID() {
		return serviceID;
	}

	public void setServiceID(Integer serviceID) {
		this.serviceID = serviceID;
	}

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}

	public Integer getCourseID() {
		return courseID;
	}

	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateTimeStr() {
		return DateUtil.getNowDateString(getCreateTime(), "yyyy-MM-dd");
	}

	public String getStatusStr() {
		if (this.status == null) {
			return "";
		}
		return Contants.status.get(status);
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getContentStr() {
		if (StringUtils.isNotBlank(content)) {
			String newStr = StringUtils.substring(content, 0, 50);
			if (StringUtils.length(content) == StringUtils.length(newStr)) {
				return content;
			} else {
				return newStr + "......";
			}
		} else {
			return "";
		}
	}

}
