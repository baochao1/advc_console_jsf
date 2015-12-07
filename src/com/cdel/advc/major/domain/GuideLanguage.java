package com.cdel.advc.major.domain;

import java.util.Date;
import com.cdel.util.Contants;
import com.cdel.util.DateUtil;

/**
 * GuideLanguage entity.
 * 
 * @author xxg
 */
@SuppressWarnings("serial")
public class GuideLanguage extends PromptTime implements java.io.Serializable {

	private Integer guideLanguageID;// 自增长主主键
	private Integer majorID; // 辅导id
	private Integer stageID; // 阶段id
	private Integer serviceID; // 服务项id
	private Short promptKey; // 提示时间
	private String promptContent;// 提示内容
	private Short status;// 是否有效
	private Date createTime;// 创建时间
	private Integer createID;// 创建人id

	// -------------------------------------------------------------------------------------

	private String stageName;// 阶段名称
	private String serviceName;// 服务项名称

	public String getStatusStr() {
		if (status == null) {
			return "";
		} else {
			return Contants.status.get(status);
		}
	}

	public String getCreateTimeStr() {
		return DateUtil.getNowDateString(createTime, "yyyy-MM-dd");
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

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public Integer getServiceID() {
		return serviceID;
	}

	public void setServiceID(Integer serviceID) {
		this.serviceID = serviceID;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Short getPromptKey() {
		return promptKey;
	}

	public void setPromptKey(Short promptKey) {
		this.promptKey = promptKey;
	}

	public String getPromptName() {
		if (promptKey != null) {
			return Contants.promptKeyMap.get(promptKey);
		} else {
			return "";
		}
	}

	public String getPromptContent() {
		return promptContent;
	}

	public void setPromptContent(String promptContent) {
		this.promptContent = promptContent;
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

	public Integer getCreateID() {
		return createID;
	}

	public void setCreateID(Integer createID) {
		this.createID = createID;
	}

	public Integer getGuideLanguageID() {
		return guideLanguageID;
	}

	public void setGuideLanguageID(Integer guideLanguageID) {
		this.guideLanguageID = guideLanguageID;
	}

}
