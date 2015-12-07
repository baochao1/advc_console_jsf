package com.cdel.advc.smsTemplate.domain;

import java.io.Serializable;

/**
 * 发短信规则对象：循环
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
public class SendRuleTwo implements Serializable {

	private Short weekInterval;// 周间隔
	private Byte weekDay;// 周几
	private Short hour;// 几点

	public Short getWeekInterval() {
		return weekInterval;
	}

	public void setWeekInterval(Short weekInterval) {
		this.weekInterval = weekInterval;
	}

	public Byte getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(Byte weekDay) {
		this.weekDay = weekDay;
	}

	public Short getHour() {
		return hour;
	}

	public void setHour(Short hour) {
		this.hour = hour;
	}

}
