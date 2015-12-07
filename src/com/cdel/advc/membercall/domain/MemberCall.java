package com.cdel.advc.membercall.domain;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.cdel.util.StringUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 
 * <p>
 * 回访记录 实体
 * </p>
 * <p>
 * 距离上次回访时间/距离上次成功回访时间/25天提醒、30天超时.....？？？？？
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-10 上午9:40:58
 */
@SuppressWarnings("serial")
public class MemberCall extends BaseDomain implements java.io.Serializable {

	/** ID */
	private Integer callID;

	/** 班级id */
	private Integer classID;

	/** 学员ID（被回访者） */
	private Integer userID;

	/** 回访教师ID */
	private Integer caller;

	/** 回访状态0电话打不通、1成功回访，2、预约回访 */
	private Short callStatus;

	/** 最后回访时间 */
	private Date callTime;

	/** 回访分类 1" 专业问题，2客服问题 */
	private Short callType;

	/** 回访了解并记录的学员 学习状况 */
	private String studyStatus;

	/** 回访了解并记录的学员 个性要求 */
	private String personalAsk;

	/** 回访了解并记录的学员意见反馈 */
	private String feedback;

	/** 预约日期 */
	private Date reservedDate;

	/** 预约时间 */
	private String reservedTime;

	/** 预约内容 */
	private String reserveRemark;

	// --------------------------------- dto
	// vo--------------------------------//

	/** 班级编号 */
	private String classCode;

	/** 班级名称 */
	private String className;

	/** 班主任 */
	private String displayAdviser;

	/** 学员名称（被回访者） */
	private String userName;

	/** 考期名称 */
	private String termName;

	/** 考期id */
	private Integer termID;

	/** 辅导（职称）ID */
	private Integer majorID;

	/** 老师id */
	private Integer teacherID;

	/** 老师名称 */
	private String teacherName;

	/** 组织ID */
	private Integer orgID;

	/** 回访开始时间 */
	private Date startDate;

	/** 回访结束时间 */
	private Date endDate;

	/** 回访时间排序 */
	private Integer callTimeOrder;

	/** 最近回访距今的天数 */
	private Integer callDay;

	/** 最近成功回访距今的天数 */
	private Integer callSuccessDay;

	/** 预约回访过期小时数 */
	private Integer postHours;

	// --------------------------------------------------//

	/** 班级 学员的数量 */
	private Short currCount;

	/** 班级中 过期电话回访的学员数量 */
	private Short overTimeCount;

	/** 教师描述 */
	private String classDesc;

	/** 考期 */
	private Short termType;

	/** 网站id */
	private Integer siteID;

	/** 地区id */
	private Integer areaID;

	/** 管理员责任说明 */
	private String teacherDesc;

	/** 老师类型 */
	private Integer teacherType;

	/** 回访天数是否大于30天 */
	private Boolean call30status;

	/** 是否教务人员 */
	private Boolean teachUser;

	/** 和老师在同一个组织机构下的其他老师ID */
	private List<Integer> sameOrgTeachers;

	// -------------- 暂时....-------------//
	/** 创建时间 */
	private Date createTime;

	/** 状态 */
	private Short status;
    /** 同步回访记录的主键**/
	private Integer outboundID;
	// ---------------------------//

	/** 预约时间段对应显示值 */
	public String getReservedTimeStr() {
		if (StringUtils.isBlank(reservedTime)) {
			return "";
		} else {
			String[] arr = StringUtils.split(reservedTime, ",");
			StringBuffer sb = new StringBuffer("");
			for (int i = 0; i < arr.length; i++) {
				sb.append(Contants.reservedTimeMap.get(new Short(arr[i])))
						.append(",");
			}
			if (sb.length() == 0) {
				return "";
			} else {
				return sb.substring(0, sb.length() - 1);
			}
		}
	}

	/** 班级中 过期回访学员个数占总学员数的百分比 */
	public String getClassUserPercentum() {
		if (this.currCount == null || this.currCount == 0) {
			return "-";
		}
		return StringUtil
				.getPercentumString(((float) this.overTimeCount / this.currCount));
	}

	/** 预约时间显示值 */
	public String getReservedDateStr() {
		return DateUtil.getNowDateString(this.reservedDate, "yyyy-MM-dd HH:mm");
	}

	/** 回访状态显示值 */
	public String getCallStatusStr() {
		return Contants.callStatus.get(this.callStatus);
	}

	/** 最后回访时间 显示值 */
	public String getCallTimeStr() {
		return DateUtil.getNowDateString(getCallTime(), "yyyy-MM-dd HH:mm");
	}

	/** 创建时间字符串显示值 */
	public String getCreateTimeStr() {
		return DateUtil.getNowDateString(getCreateTime(), "yyyy-MM-dd");
	}

	/**
	 * 电话回访类型
	 * 
	 * @return
	 */
	public String getCallTypeStr() {
		if (callType != null) {
			return Contants.stuCallType.get(callType);
		} else {
			return "";
		}
	}

	/** 根据天数，设置回访限制的开始，结束日期（天） */
	protected void setStartAndEndDay(Byte dayNumber){
		
		if(dayNumber != null){
			
			switch (dayNumber) {
			case 30:
				this.setStartDate(getFormatDate(-55 + 1));
				this.setEndDate(getFormatDate(-30 + 1));
				break;
				
			case 55:
				this.setStartDate(getFormatDate(-60 + 1));
				this.setEndDate(getFormatDate(-55 + 1));
				break;
				
			case 60:
				this.setStartDate(null);
				this.setEndDate(getFormatDate(-60 + 1));
				break;

			default:
				break;
			}
		}
	}
	
	private Date getFormatDate(int days){
		return DateUtil.getAfterDate(new Date(), days, (short)0);
	}
	
	public void setCall30status(Boolean call30status) {
		
		this.call30status = call30status;
		
		if(call30status){
			this.setStartAndEndDay((byte) 60);
		}
	}

	public void setCallDay(Integer callDay) {
		this.callDay = callDay;
	}

	public Short getOverTimeCount() {
		if (this.overTimeCount == null) {
			return 0;
		}
		return overTimeCount;
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

	public void setOverTimeCount(Short overTimeCount) {
		this.overTimeCount = overTimeCount;
	}

	public Integer getCallDay() {
		return callDay;
	}

	public Integer getCallSuccessDay() {
		return callSuccessDay;
	}

	public void setCallSuccessDay(Integer callSuccessDay) {
		this.callSuccessDay = callSuccessDay;
	}

	public Integer getCallID() {
		return callID;
	}

	public void setCallID(Integer callID) {
		this.callID = callID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Short getCallStatus() {
		return callStatus;
	}

	public void setCallStatus(Short callStatus) {
		this.callStatus = callStatus;
	}

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Short getCurrCount() {
		return currCount;
	}

	public void setCurrCount(Short currCount) {
		this.currCount = currCount;
	}

	public String getClassDesc() {
		return classDesc;
	}

	public void setClassDesc(String classDesc) {
		this.classDesc = classDesc;
	}

	public Integer getClassID() {
		return classID;
	}

	public void setClassID(Integer classID) {
		this.classID = classID;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getDisplayAdviser() {
		return displayAdviser;
	}

	public void setDisplayAdviser(String displayAdviser) {
		this.displayAdviser = displayAdviser;
	}

	public Integer getTermID() {
		return termID;
	}

	public void setTermID(Integer termID) {
		this.termID = termID;
	}

	public Short getTermType() {
		return termType;
	}

	public void setTermType(Short termType) {
		this.termType = termType;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Integer getSiteID() {
		return siteID;
	}

	public void setSiteID(Integer siteID) {
		this.siteID = siteID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public Integer getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(Integer teacherID) {
		this.teacherID = teacherID;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public String getTeacherNameStr() {
		if (StringUtils.isBlank(teacherName)) {
			return "无";
		} else {
			return teacherName;
		}
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Date getCallTime() {
		return callTime;
	}

	public void setCallTime(Date callTime) {
		this.callTime = callTime;
	}

	public Integer getCallTimeOrder() {
		return callTimeOrder;
	}

	public void setCallTimeOrder(Integer callTimeOrder) {
		this.callTimeOrder = callTimeOrder;
	}

	public Integer getAreaID() {
		return areaID;
	}

	public void setAreaID(Integer areaID) {
		this.areaID = areaID;
	}

	public Short getCallType() {
		return callType;
	}

	public void setCallType(Short callType) {
		this.callType = callType;
	}

	public String getTeacherDesc() {
		return teacherDesc;
	}

	public void setTeacherDesc(String teacherDesc) {
		this.teacherDesc = teacherDesc;
	}

	public Integer getTeacherType() {
		return teacherType;
	}

	public void setTeacherType(Integer teacherType) {
		this.teacherType = teacherType;
	}

	public Boolean getCall30status() {
		return call30status;
	}

	public Integer getOrgID() {
		return orgID;
	}

	public void setOrgID(Integer orgID) {
		this.orgID = orgID;
	}

	public Boolean getTeachUser() {
		return teachUser;
	}

	public void setTeachUser(Boolean teachUser) {
		this.teachUser = teachUser;
	}

	public List<Integer> getSameOrgTeachers() {
		return sameOrgTeachers;
	}

	public void setSameOrgTeachers(List<Integer> sameOrgTeachers) {
		this.sameOrgTeachers = sameOrgTeachers;
	}

	public Integer getCaller() {
		return caller;
	}

	public void setCaller(Integer caller) {
		this.caller = caller;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStudyStatus() {
		return studyStatus;
	}

	public void setStudyStatus(String studyStatus) {
		this.studyStatus = studyStatus;
	}

	public String getPersonalAsk() {
		return personalAsk;
	}

	public void setPersonalAsk(String personalAsk) {
		this.personalAsk = personalAsk;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Date getReservedDate() {
		return reservedDate;
	}

	public void setReservedDate(Date reservedDate) {
		this.reservedDate = reservedDate;
	}

	public String getReservedTime() {
		return reservedTime;
	}

	public void setReservedTime(String reservedTime) {
		this.reservedTime = reservedTime;
	}

	public String getReserveRemark() {
		return reserveRemark;
	}

	public void setReserveRemark(String reserveRemark) {
		this.reserveRemark = reserveRemark;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public Integer getPostHours() {
		return postHours;
	}

	public Integer getOutboundID() {
		return outboundID;
	}

	public void setOutboundID(Integer outboundID) {
		this.outboundID = outboundID;
	}

	public void setPostHours(Integer postHours) {
		this.postHours = postHours;
	}

}
