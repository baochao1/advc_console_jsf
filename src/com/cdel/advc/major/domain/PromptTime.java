package com.cdel.advc.major.domain;

import java.util.Date;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * @author xxg
 */
@SuppressWarnings("serial")
public class PromptTime extends BaseDomain implements java.io.Serializable {

	private Integer promptID; // 主键
	private Integer guideLanguageID;// 外键
	private Date beginTime;// 开始时间
	private Date endTime; // 结束时间

	public Integer getGuideLanguageID() {
		return guideLanguageID;
	}

	public void setGuideLanguageID(Integer guideLanguageID) {
		this.guideLanguageID = guideLanguageID;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getPromptID() {
		return promptID;
	}

	public void setPromptID(Integer promptID) {
		this.promptID = promptID;
	}

}
