package com.cdel.advc.smsTemplate.domain;

import java.io.Serializable;

/**
 * 发短信规则对象：非固定日期单次
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
public class SendRuleOne implements Serializable {

	private Integer stageID;
	private Integer startOrEnd;// 开始或结束，1：开始，2：结束
	private Integer dayNum;// 天数
	private Short hour;// 几点

	public Integer getStageID() {
		return stageID;
	}

	public void setStageID(Integer stageID) {
		this.stageID = stageID;
	}

	public Integer getStartOrEnd() {
		return startOrEnd;
	}

	public void setStartOrEnd(Integer startOrEnd) {
		this.startOrEnd = startOrEnd;
	}

	public Integer getDayNum() {
		return dayNum;
	}

	public void setDayNum(Integer dayNum) {
		this.dayNum = dayNum;
	}

	public Short getHour() {
		return hour;
	}

	public void setHour(Short hour) {
		this.hour = hour;
	}

}
