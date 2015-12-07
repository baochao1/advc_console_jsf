package com.cdel.advc.memberclass.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cdel.advc.course.domain.Course;
import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 
 * <p>
 * 班级学员实体
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-1 上午11:33:38
 */
@SuppressWarnings("serial")
public class MemberClass extends BaseDomain implements Serializable {

	/** 学员学号 */
	private String studentNo;

	/** 学员ID */
	private Integer userID;

	/** 班级ID */
	private Integer classID;

	/** 学习课程 */
	private String studyCourse;

	/** 在班级中的序号 */
	private Integer sequence;

	/** 第一次进班时间(第一次进班时间，换班时保持不变) */
	private Date firstTime;

	/** 最后登录时间 */
	private Date lastLogin;

	/** 正确率 */
	private Float rightRate;

	/** 创建时间 */
	private Date createTime;

	/** 状态 */
	private Short status;
	/** 学员班级备注*/
	private String remarks;

	// --------------------------------vo, dto----------------------------//
	/** 显示的 班级备注*/
	private String showRemarks;
	 
	private String className;

	/** 学员名称 */
	private String userName;

	/** 手机号 */
	private String telPhone;

	/** 显示的手机号 */
	private String formerTelPhone;

	/** 最新学习报告创建时间 */
	private Date rptCreateTime;

	private String email;

	/** 学习课程的信息 */
	private List<Course> studyCourseList;

	/** 预习课程 */
	private List<Course> preStudyCourseList;

	/** 班主任 */
	private String displayAdviser;

	private Integer majorID;
	private Integer termID;
	private String termName;
	private Short termType;
	private String studyCourseName;
	private String courseCode;// 学员学习的课程的code
	/** 课程ID 数组  */
	private Integer[] courseIDs;
	private String courseIDsStr; //拼接后的字符串
	
	private Date enterTime;// 入班时间(createTime的别名)
	private Integer strategyID;
	private Short strategyType;
	private Integer newClassID;// 学员分配的新班级ID,用于学员换班等
	private Short classStatus;// 所在班级状态
	private Integer rptCreateDay;// 最新学习报告生成日期距今的天数
	private Integer callDay;// 最近回访距今的天数
	private Integer callSuccessDay;// 最近成功回访距今的天数
	private Integer reportDateSpace;// 学习报告生成间隔时间(天数)
	private Integer smsSendDay;// 最近发送短信距今的天数
	private Date updateDate;// 学习报告更新日期
	private Date reservedDate;// 回访预约时间
	private Short notifyType;// 回访提醒类型（状态值,提示电话回访目前的状态:过期，预约...）
	private Short isNewService;// 是否开启短信发送
	private Integer examDay;// 考试日期距今的天数
	private Integer createDay;// 入班日期距今的天数
	private Integer planID;
	private Integer prePlanID;
	private Integer planStatus;
	private Integer prePlanStatus;
 	private String newphonenumber;
	

	/** 显示的手机号（处理后手机号显示部分号码） */
	public String getFormerTelPhone() {
		this.formerTelPhone = "";
		if (StringUtils.isNotBlank(this.telPhone)) {
			String[] arr = StringUtils.split(this.telPhone, "|");
			if (arr != null && arr.length > 0) {
				if (arr.length == 1) {
					if (arr[0].length() > 7) {
						this.formerTelPhone = this.telPhone.substring(0, 7)
								+ "****";
					} else {
						this.formerTelPhone = this.telPhone;
					}
				} else {
					for (int i = 0; i < arr.length; i++) {
						String phone = arr[i];
						if (phone.length() > 7) {
							phone = phone.substring(0, 7) + "****";
						}
						this.formerTelPhone += phone + "|";
					}
				}
			} else {
				if (this.telPhone.length() > 7) {
					this.formerTelPhone = this.telPhone.substring(0, 7)
							+ "****";
				} else {
					this.formerTelPhone = this.telPhone;
				}
			}
		} 
		return this.formerTelPhone;
	}

	/**
	 * 
	 * 得到创建时间
	 */
	public String getCreateTimeStr() {
		return DateUtil.getNowDateString(this.createTime, "yyyy-MM-dd");
	}

	
	public String getShowRemarks() {
		if(remarks != null && !remarks.equals("")){
			if(remarks.length()<=100 && remarks.length()>12){
			   showRemarks = StringUtils.substring(remarks, 0, 10) +"......";
			}else if(remarks.length() <=12){
				showRemarks=this.remarks;
			}
		}else if(remarks == null || remarks.equals("")){
			showRemarks=this.remarks;
		}
		return showRemarks;
	}

	public void setShowRemarks(String showRemarks) {
		this.showRemarks = showRemarks;
	}

	public Date getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(Date firstTime) {
		this.firstTime = firstTime;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getStudyCourse() {
		return studyCourse;
	}

	public void setStudyCourse(String studyCourse) {
		this.studyCourse = studyCourse;
	}

	public Integer getClassID() {
		return classID;
	}

	public void setClassID(Integer classID) {
		this.classID = classID;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	public Date getRptCreateTime() {
		return rptCreateTime;
	}

	public void setRptCreateTime(Date rptCreateTime) {
		this.rptCreateTime = rptCreateTime;
	}

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public Short getTermType() {
		return termType;
	}

	public String getTermTypeStr() {
		if (termType == null) {
			return "";
		} else {
			return Contants.termTypeMap.get(termType);
		}
	}

	public void setTermType(Short termType) {
		this.termType = termType;
	}

	public Integer getTermID() {
		return termID;
	}

	public void setTermID(Integer termID) {
		this.termID = termID;
	}

	public String getStudyCourseName() {
		return studyCourseName;
	}

	public void setStudyCourseName(String studyCourseName) {
		this.studyCourseName = studyCourseName;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Float getRightRate() {
		return rightRate;
	}

	public void setRightRate(Float rightRate) {
		this.rightRate = rightRate;
	}

	public Date getEnterTime() {
		return enterTime;
	}

	public String getEnterTimeStr() {
		return DateUtil.getNowDateString(enterTime, "yyyy-MM-dd");
	}

	public void setEnterTime(Date enterTime) {
		this.enterTime = enterTime;
	}

	public void setFormerTelPhone(String formerTelPhone) {
		this.formerTelPhone = formerTelPhone;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Course> getStudyCourseList() {
		return studyCourseList;
	}

	public void setStudyCourseList(List<Course> studyCourseList) {
		this.studyCourseList = studyCourseList;
	}

	public String getStudyCourseListStr() {
		if (studyCourseList == null || studyCourseList.size() == 0) {
			return "";
		} else {
			StringBuffer sb = new StringBuffer("");
			for (Course course : studyCourseList) {
				sb.append(course.getCourseName()).append(";");
			}
			return sb.toString();
		}
	}

	public String getStudyCourseListStrShort() {
		return StringUtils.substring(getStudyCourseListStr(), 0, 30) + "...";
	}

	public String getDisplayAdviser() {
		return displayAdviser;
	}

	public void setDisplayAdviser(String displayAdviser) {
		this.displayAdviser = displayAdviser;
	}

	public Short getStrategyType() {
		return strategyType;
	}

	public void setStrategyType(Short strategyType) {
		this.strategyType = strategyType;
	}

	public Integer getStrategyID() {
		return strategyID;
	}

	public void setStrategyID(Integer strategyID) {
		this.strategyID = strategyID;
	}

	public Integer getNewClassID() {
		return newClassID;
	}

	public void setNewClassID(Integer newClassID) {
		this.newClassID = newClassID;
	}

	public Short getClassStatus() {
		return classStatus;
	}

	public void setClassStatus(Short classStatus) {
		this.classStatus = classStatus;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public Integer getRptCreateDay() {
		return rptCreateDay;
	}

	public String getRptCreateDayStr() {
		if (this.rptCreateDay == null) {
			return "-";
		}
		return rptCreateDay.toString();
	}

	public void setRptCreateDay(Integer rptCreateDay) {
		this.rptCreateDay = rptCreateDay;
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

	public Integer getReportDateSpace() {
		return reportDateSpace;
	}

	public void setReportDateSpace(Integer reportDateSpace) {
		this.reportDateSpace = reportDateSpace;
	}

	public Integer getSmsSendDay() {
		return smsSendDay;
	}

	public String getSmsSendDayStr() {
		if (this.smsSendDay == null) {
			return "-";
		}
		return smsSendDay.toString();
	}

	public void setSmsSendDay(Integer smsSendDay) {
		this.smsSendDay = smsSendDay;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public String getUpdateDateStr() {
		if (updateDate == null) {
			return "-";
		}
		return DateUtil.getNowDateString(updateDate, "yyyy-MM-dd");
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Short getIsNewService() {
		return isNewService;
	}

	public void setIsNewService(Short isNewService) {
		this.isNewService = isNewService;
	}

	public Integer getExamDay() {
		return examDay;
	}

	public void setExamDay(Integer examDay) {
		this.examDay = examDay;
	}

	public void setNotifyType() {
		if (this.reservedDate != null) {// 已预约时
			this.notifyType = 0;
		} else {
			if (isNewService == null || isNewService == 0) {
				// 短信服务未开启时
				if (this.callDay != null) {
					if (55 <= this.callDay && this.callDay < 60) {
						this.notifyType = 1;
					} else if (this.callDay >= 60) {
						this.notifyType = 2;
					}
				}
			} else {
				// 短信服务开启时
				if (this.callDay == null) {// 未打过电话
					if (5 <= this.createDay && this.createDay < 8) {
						this.notifyType = 1;
					} else if (this.createDay >= 8) {
						this.notifyType = 2;
					}
				} else {// 打过电话
					if (examDay != null) {
						if (examDay < 60 && examDay >= 2
								&& callSuccessDay != null
								&& (examDay - callSuccessDay) > 60) {
							this.notifyType = 1;
						}
						if (examDay < 2
								&& callSuccessDay != null
								&& (((examDay - callSuccessDay) > 60) || (examDay - callSuccessDay) < 2)) {
							this.notifyType = 2;
						}
						if (examDay < 60 && examDay >= 2
								&& callSuccessDay == null) {
							this.notifyType = 1;
						}
						if (examDay < 2 && callSuccessDay == null) {
							this.notifyType = 2;
						}
					}
				}
			}
		}
	}

	public void setNotifyType(Short notifyType) {
		this.notifyType = notifyType;
	}

	public Short getNotifyType() {
		return notifyType;
	}

	/**
	 * 学员电话回访提醒类型显示值
	 */
	public String getNotifyTypeStr() {
		this.setNotifyType();

		if (this.notifyType == null) {
			return "";
		} else {
			return Contants.memberCallNotifyType.get(this.notifyType);
		}
	}

	public Integer getCreateDay() {
		return createDay;
	}

	public void setCreateDay(Integer createDay) {
		this.createDay = createDay;
	}

	public List<Course> getPreStudyCourseList() {
		return preStudyCourseList;
	}

	public void setPreStudyCourseList(List<Course> preStudyCourseList) {
		this.preStudyCourseList = preStudyCourseList;
	}

	public String getPreStudyCourseListStr() {
		if (preStudyCourseList == null || preStudyCourseList.size() == 0) {
			return "";
		} else {
			StringBuffer sb = new StringBuffer("");
			for (Course course : preStudyCourseList) {
				sb.append(course.getCourseName()).append(",");
			}
			if (sb.length() > 0) {
				return sb.substring(0, sb.length());
			}
			return sb.toString();
		}
	}

	public Date getReservedDate() {
		return reservedDate;
	}

	public void setReservedDate(Date reservedDate) {
		this.reservedDate = reservedDate;
	}

	public Integer getPlanID() {
		return planID;
	}

	public void setPlanID(Integer planID) {
		this.planID = planID;
	}

	public Integer getPrePlanID() {
		return prePlanID;
	}

	public void setPrePlanID(Integer prePlanID) {
		this.prePlanID = prePlanID;
	}

	public Integer getPlanStatus() {
		return planStatus;
	}

	public void setPlanStatus(Integer planStatus) {
		this.planStatus = planStatus;
	}

	public Integer getPrePlanStatus() {
		return prePlanStatus;
	}

	public void setPrePlanStatus(Integer prePlanStatus) {
		this.prePlanStatus = prePlanStatus;
	}

	public String getPlanStatusStr() {
		if (planID == null) {
			return "未设置";
		} else if (planStatus == 1) {
			return "学习计划(正常)";
		} else {
			return "需重新生成";
		}
	}

	public String getPrePlanStatusStr() {
		if (prePlanID == null) {
			return "未设置";
		} else if (prePlanStatus == 1) {
			return "预习计划(正常)";
		} else {
			return "需重新生成";
		}
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getNewphonenumber() {
		return newphonenumber;
	}

	public void setNewphonenumber(String newphonenumber) {
		this.newphonenumber = newphonenumber;
	}

	public Integer[] getCourseIDs() {
		if(courseIDs != null && courseIDs.length>0){
			Arrays.sort(courseIDs);
		}
		return courseIDs;
	}

	public void setCourseIDs(Integer[] courseIDs) {
		this.courseIDs = courseIDs;
	}

	public String getCourseIDsStr() {
		courseIDsStr = StringUtils.join(courseIDs, ",");
		return courseIDsStr;
	}

	public void setCourseIDsStr(String courseIDsStr) {
		this.courseIDsStr = courseIDsStr;
	}

}