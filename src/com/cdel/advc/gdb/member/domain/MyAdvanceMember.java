package com.cdel.advc.gdb.member.domain;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.cdel.util.DateUtil;

/**
 * 我的高端班学员
 * 
 * @author zhangsulei
 * 
 */
@SuppressWarnings("serial")
public class MyAdvanceMember extends AdvanceMember implements
		java.io.Serializable {
	private Integer orgID;
	private String teacherCode;
	private String teacherName;
	private Short courseStatus;// 学员报的课程状态
	private Integer callDay;// 最近回访距今的天数
	private Integer callSuccessDay;// 最近成功回访距今的天数
	private Date updateDate;// 学习报告更新日期

	/** 显示的手机号 */
	private String formerTelPhone;

	public Integer getOrgID() {
		return orgID;
	}

	public void setOrgID(Integer orgID) {
		this.orgID = orgID;
	}

	public String getTeacherCode() {
		return teacherCode;
	}

	public void setTeacherCode(String teacherCode) {
		this.teacherCode = teacherCode;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Short getCourseStatus() {
		return courseStatus;
	}

	public void setCourseStatus(Short courseStatus) {
		this.courseStatus = courseStatus;
	}

	public Integer getCallDay() {
		return callDay;
	}

	public void setCallDay(Integer callDay) {
		this.callDay = callDay;
	}

	public Integer getCallSuccessDay() {
		return callSuccessDay;
	}

	public void setCallSuccessDay(Integer callSuccessDay) {
		this.callSuccessDay = callSuccessDay;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateDateStr() {
		if (updateDate == null) {
			return "-";
		}
		return DateUtil.getNowDateString(updateDate, "yyyy-MM-dd");
	}

	/** 显示的手机号（处理后手机号显示部分号码） */
	public String getFormerTelPhone() {
		if (StringUtils.isNotBlank(getTelPhone())
				&& getTelPhone().length() == 11) {
			this.formerTelPhone = getTelPhone().substring(0, 7) + "****";
		} else {
			this.formerTelPhone = getTelPhone();
		}
		return this.formerTelPhone;
	}

	public String getCallDayStr() {
		if (this.callDay == null) {
			return "-";
		}
		return callDay.toString();
	}

	public String getCallSuccessDayStr() {
		if (this.callSuccessDay == null) {
			return "-";
		}
		return callSuccessDay.toString();
	}

	public String getCourseNames() {
		if (getCourseList() != null && getCourseList().size() > 0) {
			StringBuffer sb = new StringBuffer("");
			for (int i = 0; i < getCourseList().size(); i++) {
				sb.append(getCourseList().get(i)).append(";");
			}
			return sb.toString();
		} else {
			return "";
		}
	}

}
