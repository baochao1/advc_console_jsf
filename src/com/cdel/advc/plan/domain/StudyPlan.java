package com.cdel.advc.plan.domain;

import java.io.Serializable;
import java.util.Date;

import com.cdel.util.CodeUtil;
import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 
 * <p>
 * 学习计划 实体
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-31 上午11:57:54
 */
@SuppressWarnings("serial")
public class StudyPlan extends BaseDomain implements Serializable {

	/** 计划ID */
	private Integer planID;

	/** 计划名 */
	private String planName;

	/** 班级ID */
	private Integer classID;

	/** 学员编号 */
	private Integer userID;

	/** 计划开始日期 */
	private Date startDay;

	/** 结束日期 */
	private Date endDay;

	/** 计划类型:0 学习计划、1预习计划 */
	private Short planType;

	/** 计划状态：0 未生成、1 正常、2 模板被该、3 学员反馈表被改、4 章节开课时间滞后、5 个人开始时间被改、6 个人修改特殊时间 */
	private Short planStatus;

	/** 学习课程 */
	private String studyCourse;

	/** 预习课程 */
	private String preCourses;

	// ----------- vo DTO -------------//

	/** 计划为预习计划时ID */
	private Integer prePlanID;

	/** 学员反馈学习时间 */
	private String memberHours;

	/** 学员姓名 */
	private String userName;

	private String className;

	/** 学员进入班级时间 */
	private Date createTime;

	/** 学习计划修改者 ID */
	private Integer planCreatorID;

	/** 学习计划修改者 姓名 */
	private String planCreatorName;

	private Short sunday;

	private Short wednesday;

	private Short thursday;

	private Short saturday;

	private Short maxStudyTime;

	private Short friday;

	private Short tuesday;

	private Short monday;

	private Short totalSum;

	private Short preSunday;

	private Short preWednesday;

	private Short preThursday;

	private Short preSaturday;

	private Short preMaxStudyTime;

	private Short preFriday;

	private Short preTuesday;

	private Short preMonday;

	private Short preTotalSum;

	/** 计划状态值 */
	public String getPlanStatusStr() {
		if (this.planStatus == null) {
			this.planStatus = 0;
		}
		return Contants.planStatus1.get(this.planStatus);
	}

	/** 学习计划是否创建标识 */
	public boolean isPlanCreated() {
		return this.planID != null ? true : false;
	}

	/** 预习计划是否创建标识 */
	public boolean isPrePlanCreated() {
		return this.prePlanID != null ? true : false;
	}

	public String getPlanCreatedStr() {
		return this.isPlanCreated() ? "已设置" : "未设置";
	}

	public String getPrePlanCreatedStr() {
		return this.isPrePlanCreated() ? "已设置" : "未设置";
	}

	/** 返回设置学习时间 */
	public String getSetStudyHours() {
		String rtnStr = "";
		if (sunday != null) {
			rtnStr += CodeUtil.minute2Hour(sunday);
		} else {
			rtnStr += "-";
		}
		rtnStr += "|";

		if (monday != null) {
			rtnStr += CodeUtil.minute2Hour(monday);
		} else {
			rtnStr += "-";
		}
		rtnStr += "|";

		if (tuesday != null) {
			rtnStr += CodeUtil.minute2Hour(tuesday);
		} else {
			rtnStr += "-";
		}
		rtnStr += "|";

		if (wednesday != null) {
			rtnStr += CodeUtil.minute2Hour(wednesday);
		} else {
			rtnStr += "-";
		}
		rtnStr += "|";

		if (thursday != null) {
			rtnStr += CodeUtil.minute2Hour(thursday);
		} else {
			rtnStr += "-";
		}
		rtnStr += "|";

		if (friday != null) {
			rtnStr += CodeUtil.minute2Hour(friday);
		} else {
			rtnStr += "-";
		}
		rtnStr += "|";

		if (saturday != null) {
			rtnStr += CodeUtil.minute2Hour(saturday);
		} else {
			rtnStr += "-";
		}
		rtnStr += "|";

		if (totalSum != null) {
			rtnStr += CodeUtil.minute2Hour(totalSum);
		} else {
			rtnStr += "-";
		}
		rtnStr += "|";

		if (maxStudyTime != null) {
			rtnStr += CodeUtil.minute2Hour(maxStudyTime);
		} else {
			rtnStr += "-";
		}

		return rtnStr;
	}

	public String getSetPreStudyHours() {
		String rtnStr = "";
		if (preSunday != null) {
			rtnStr += CodeUtil.minute2Hour(preSunday);
		} else {
			rtnStr += "-";
		}
		rtnStr += "|";

		if (preMonday != null) {
			rtnStr += CodeUtil.minute2Hour(preMonday);
		} else {
			rtnStr += "-";
		}
		rtnStr += "|";

		if (preTuesday != null) {
			rtnStr += CodeUtil.minute2Hour(preTuesday);
		} else {
			rtnStr += "-";
		}
		rtnStr += "|";

		if (preWednesday != null) {
			rtnStr += CodeUtil.minute2Hour(preWednesday);
		} else {
			rtnStr += "-";
		}
		rtnStr += "|";

		if (preThursday != null) {
			rtnStr += CodeUtil.minute2Hour(preThursday);
		} else {
			rtnStr += "-";
		}
		rtnStr += "|";

		if (preFriday != null) {
			rtnStr += CodeUtil.minute2Hour(preFriday);
		} else {
			rtnStr += "-";
		}
		rtnStr += "|";

		if (preSaturday != null) {
			rtnStr += CodeUtil.minute2Hour(preSaturday);
		} else {
			rtnStr += "-";
		}
		rtnStr += "|";

		if (preTotalSum != null) {
			rtnStr += CodeUtil.minute2Hour(preTotalSum);
		} else {
			rtnStr += "-";
		}
		rtnStr += "|";

		if (preMaxStudyTime != null) {
			rtnStr += CodeUtil.minute2Hour(preMaxStudyTime);
		} else {
			rtnStr += "-";
		}

		return rtnStr;
	}

	/** 加入班级时间 */
	public String getCreateTimeStr() {
		return DateUtil.getNowDateString(this.createTime, "yyyy-MM-dd");
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getPlanCreatorID() {
		return planCreatorID;
	}

	public void setPlanCreatorID(Integer planCreatorID) {
		this.planCreatorID = planCreatorID;
	}

	public String getPlanCreatorName() {
		return planCreatorName;
	}

	public void setPlanCreatorName(String planCreatorName) {
		this.planCreatorName = planCreatorName;
	}

	public Integer getPlanID() {
		return planID;
	}

	public void setPlanID(Integer planID) {
		this.planID = planID;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public Integer getClassID() {
		return classID;
	}

	public void setClassID(Integer classID) {
		this.classID = classID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Date getStartDay() {
		return startDay;
	}

	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}

	public Date getEndDay() {
		return endDay;
	}

	public void setEndDay(Date endDay) {
		this.endDay = endDay;
	}

	public Short getPlanType() {
		return planType;
	}

	public void setPlanType(Short planType) {
		this.planType = planType;
	}

	public Short getPlanStatus() {
		return planStatus;
	}

	public void setPlanStatus(Short planStatus) {
		this.planStatus = planStatus;
	}

	public String getStudyCourse() {
		return studyCourse;
	}

	public void setStudyCourse(String studyCourse) {
		this.studyCourse = studyCourse;
	}

	public String getPreCourses() {
		return preCourses;
	}

	public void setPreCourses(String preCourses) {
		this.preCourses = preCourses;
	}

	public Integer getPrePlanID() {
		return prePlanID;
	}

	public void setPrePlanID(Integer prePlanID) {
		this.prePlanID = prePlanID;
	}

	public String getMemberHours() {
		return memberHours;
	}

	public void setMemberHours(String memberHours) {
		this.memberHours = memberHours;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
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

	public Short getPreSunday() {
		return preSunday;
	}

	public void setPreSunday(Short preSunday) {
		this.preSunday = preSunday;
	}

	public Short getPreWednesday() {
		return preWednesday;
	}

	public void setPreWednesday(Short preWednesday) {
		this.preWednesday = preWednesday;
	}

	public Short getPreThursday() {
		return preThursday;
	}

	public void setPreThursday(Short preThursday) {
		this.preThursday = preThursday;
	}

	public Short getPreSaturday() {
		return preSaturday;
	}

	public void setPreSaturday(Short preSaturday) {
		this.preSaturday = preSaturday;
	}

	public Short getPreMaxStudyTime() {
		return preMaxStudyTime;
	}

	public void setPreMaxStudyTime(Short preMaxStudyTime) {
		this.preMaxStudyTime = preMaxStudyTime;
	}

	public Short getPreFriday() {
		return preFriday;
	}

	public void setPreFriday(Short preFriday) {
		this.preFriday = preFriday;
	}

	public Short getPreTuesday() {
		return preTuesday;
	}

	public void setPreTuesday(Short preTuesday) {
		this.preTuesday = preTuesday;
	}

	public Short getPreMonday() {
		return preMonday;
	}

	public void setPreMonday(Short preMonday) {
		this.preMonday = preMonday;
	}

	public Short getPreTotalSum() {
		return preTotalSum;
	}

	public void setPreTotalSum(Short preTotalSum) {
		this.preTotalSum = preTotalSum;
	}

}