/*
 *                                                                   
 */
package com.cdel.advc.plan.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cdel.util.CodeUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 
 * <p>
 * 学员学习时间设置 实体
 * </p>
 * 
 * @author Du Haiying Create at:2013-8-5 上午9:31:52
 */
@SuppressWarnings("serial")
public class MemberTermHours extends BaseDomain implements Serializable {

	/** ID */
	private Integer mthID;

	/** 计划类型 */
	private Short mthType;

	/** 学员ID */
	private Integer userID;

	/** 考期ID */
	private Integer termID;

	private Short sunday;

	private Short wednesday;

	private Short thursday;

	private Short saturday;

	private Short friday;

	private Short tuesday;

	private Short monday;

	/** 总学习时长 */
	private Short totalSum;

	/** 最大学习时长 */
	private Short maxStudyTime;

	private Double sundayHour = 0.0;

	private Double wednesdayHour = 0.0;

	private Double thursdayHour = 0.0;

	private Double saturdayHour = 0.0;

	private Double fridayHour = 0.0;

	private Double tuesdayHour = 0.0;

	private Double mondayHour = 0.0;

	private Double maxStudyTimeHour = 0.0;

	private Integer planID;

	/** 分钟数转为小时数 */
	public void forHours() {
		this.sundayHour = CodeUtil.minute2HourNumber(this.sunday);
		this.wednesdayHour = CodeUtil.minute2HourNumber(this.wednesday);
		this.thursdayHour = CodeUtil.minute2HourNumber(this.thursday);
		this.saturdayHour = CodeUtil.minute2HourNumber(this.saturday);
		this.fridayHour = CodeUtil.minute2HourNumber(this.friday);
		this.tuesdayHour = CodeUtil.minute2HourNumber(this.tuesday);
		this.mondayHour = CodeUtil.minute2HourNumber(this.monday);
		this.maxStudyTimeHour = CodeUtil.minute2HourNumber(this.maxStudyTime);
	}

	/** 小时数转为分钟数 */
	public void forMinites() {
		this.sunday = (short) (60 * this.sundayHour);
		this.wednesday = (short) (60 * this.wednesdayHour);
		this.thursday = (short) (60 * this.thursdayHour);
		this.saturday = (short) (60 * this.saturdayHour);
		this.friday = (short) (60 * this.fridayHour);
		this.tuesday = (short) (60 * this.tuesdayHour);
		this.monday = (short) (60 * this.mondayHour);
		this.maxStudyTime = (short) (60 * this.maxStudyTimeHour);
		this.totalSum = (short) (this.sunday + this.wednesday + this.thursday
				+ this.saturday + this.friday + this.tuesday + this.monday);
	}

	/** 总小时数 */
	public Double getTotalHour() {
		Double temp = this.sundayHour + this.wednesdayHour + this.thursdayHour
				+ this.saturdayHour + this.fridayHour + this.tuesdayHour
				+ this.mondayHour;
		return new BigDecimal(temp).setScale(2, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
	}

	public Short getSunday() {
		return sunday;
	}

	public void setSunday(Short sunday) {
		this.sunday = sunday;
	}

	public Short getWednesday() {
		return wednesday;
	}

	public void setWednesday(Short wednesday) {
		this.wednesday = wednesday;
	}

	public Short getThursday() {
		return thursday;
	}

	public void setThursday(Short thursday) {
		this.thursday = thursday;
	}

	public Integer getTermID() {
		return termID;
	}

	public void setTermID(Integer termID) {
		this.termID = termID;
	}

	public Short getSaturday() {
		return saturday;
	}

	public void setSaturday(Short saturday) {
		this.saturday = saturday;
	}

	public Short getMaxStudyTime() {
		return maxStudyTime;
	}

	public void setMaxStudyTime(Short maxStudyTime) {
		this.maxStudyTime = maxStudyTime;
	}

	public Short getFriday() {
		return friday;
	}

	public void setFriday(Short friday) {
		this.friday = friday;
	}

	public Short getTuesday() {
		return tuesday;
	}

	public void setTuesday(Short tuesday) {
		this.tuesday = tuesday;
	}

	public Short getMonday() {
		return monday;
	}

	public void setMonday(Short monday) {
		this.monday = monday;
	}

	public Short getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(Short totalSum) {
		this.totalSum = totalSum;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Integer getMthID() {
		return mthID;
	}

	public void setMthID(Integer mthID) {
		this.mthID = mthID;
	}

	public Short getMthType() {
		return mthType;
	}

	public void setMthType(Short mthType) {
		this.mthType = mthType;
	}

	public Double getSundayHour() {
		return sundayHour;
	}

	public void setSundayHour(Double sundayHour) {
		this.sundayHour = sundayHour;
	}

	public Double getWednesdayHour() {
		return wednesdayHour;
	}

	public void setWednesdayHour(Double wednesdayHour) {
		this.wednesdayHour = wednesdayHour;
	}

	public Double getThursdayHour() {
		return thursdayHour;
	}

	public void setThursdayHour(Double thursdayHour) {
		this.thursdayHour = thursdayHour;
	}

	public Double getSaturdayHour() {
		return saturdayHour;
	}

	public void setSaturdayHour(Double saturdayHour) {
		this.saturdayHour = saturdayHour;
	}

	public Double getFridayHour() {
		return fridayHour;
	}

	public void setFridayHour(Double fridayHour) {
		this.fridayHour = fridayHour;
	}

	public Double getTuesdayHour() {
		return tuesdayHour;
	}

	public void setTuesdayHour(Double tuesdayHour) {
		this.tuesdayHour = tuesdayHour;
	}

	public Double getMondayHour() {
		return mondayHour;
	}

	public void setMondayHour(Double mondayHour) {
		this.mondayHour = mondayHour;
	}

	public Double getMaxStudyTimeHour() {
		return maxStudyTimeHour;
	}

	public void setMaxStudyTimeHour(Double maxStudyTimeHour) {
		this.maxStudyTimeHour = maxStudyTimeHour;
	}

	public Integer getPlanID() {
		return planID;
	}

	public void setPlanID(Integer planID) {
		this.planID = planID;
	}

}