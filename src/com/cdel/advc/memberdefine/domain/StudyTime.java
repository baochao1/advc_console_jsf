package com.cdel.advc.memberdefine.domain;

import java.io.Serializable;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 学习时段
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
public class StudyTime extends BaseDomain implements Serializable {
	private String timeName;// 时段
	private String startTime;// 起始时间
	private String endTime;// 结束时间

	public String getTimeName() {
		return timeName;
	}

	public void setTimeName(String timeName) {
		this.timeName = timeName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}