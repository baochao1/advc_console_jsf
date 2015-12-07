package com.cdel.advc.major.domain;

import java.util.Date;

import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 特殊日期设置
 * 
 * @author zhangsulei
 */

public class SpecialGeneral extends BaseDomain implements java.io.Serializable {
	private static final long serialVersionUID = 7193900830270819888L;

	private Integer specialGeneralID;
	private Integer majorID;
	private String majorName;
	private Date generalDay;// 日期
	private Integer longTime;// 时间(秒)
	private Integer LongTimeMin;// 时间(分钟)
	private String createName;// 添加人
	private Date createTime;
	private Integer creator;// 添加人

	public Integer getSpecialGeneralID() {
		return specialGeneralID;
	}

	public void setSpecialGeneralID(Integer specialGeneralID) {
		this.specialGeneralID = specialGeneralID;
	}

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

	public Date getGeneralDay() {
		return generalDay;
	}

	public void setGeneralDay(Date generalDay) {
		this.generalDay = generalDay;
	}

	public String getGeneralDayStr() {
		return DateUtil.getNowDateString(getGeneralDay(), "yyyy-MM-dd");
	}

	public Integer getLongTime() {
		return longTime;
	}

	public void setLongTime(Integer longTime) {
		this.longTime = longTime;
	}

	public Integer getLongTimeMin() {
		return LongTimeMin;
	}

	public void setLongTimeMin(Integer longTimeMin) {
		LongTimeMin = longTimeMin;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
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

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

}