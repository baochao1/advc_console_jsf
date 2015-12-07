package com.cdel.advc.smsTemplate.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 发送短信模板实体
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
public class SmsTemplate extends BaseDomain implements Serializable {

	private Integer templateID;
	private Integer majorID;
	private Integer stageID;
	private String title;
	private String sendContent;
	private Integer creator;
	private Date createTime;
	private Byte sendType;// 发送类型：目前是1：非固定日期单次，2：循环
	private String sendRule;// 发送规则
	private Short status;
	// -------------------------------vo-----------------------------------------
	private String stageName;

	public Integer getTemplateID() {
		return templateID;
	}

	public void setTemplateID(Integer templateID) {
		this.templateID = templateID;
	}

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}

	public Integer getStageID() {
		return stageID;
	}

	public void setStageID(Integer stageID) {
		this.stageID = stageID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSendContent() {
		return sendContent;
	}

	public String getSendContentStr() {
		if (StringUtils.isNotBlank(sendContent)) {
			String newStr = StringUtils.substring(sendContent, 0, 50);
			if (StringUtils.length(sendContent) == StringUtils.length(newStr)) {
				return sendContent;
			} else {
				return newStr + "......";
			}
		} else {
			return "";
		}
	}

	public void setSendContent(String sendContent) {
		this.sendContent = sendContent;
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

	public Byte getSendType() {
		return sendType;
	}

	public void setSendType(Byte sendType) {
		this.sendType = sendType;
	}

	public String getSendRule() {
		return sendRule;
	}

	public void setSendRule(String sendRule) {
		this.sendRule = sendRule;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
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

	public String getSendTypeStr() {
		if (this.sendType == null) {
			return "";
		}
		return Contants.sendType.get(sendType);
	}

}
