package com.cdel.advc.classes.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.cdel.advc.classteacher.domain.Classteacher;
import com.cdel.advc.course.domain.Course;
import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 
 * <p>
 * 班级实体
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-1 上午10:36:43
 */
@SuppressWarnings("serial")
public class Classes extends BaseDomain implements Serializable {

	/** 班级ID */
	private Integer classID;

	/** 班级名称 */
	private String className;

	/** 班级编号（用于产生学号） */
	private String classCode;

	/** 班级介绍 (班级口号) */
	private String classDesc;

	/** 状态：1正常，0无效 */
	private Short status;

	/** 创建时间 */
	private Date createTime;

	/** 班级的学员人数 */
	private Short currCount;

	/** 班主任 */
	private String displayAdviser;

	/** 分班策略ID */
	private Integer strategyID;

	/** 考期编号 */
	private Integer termID;

	/** 公告主题 */
	private String noticeTitle;

	/** 公告创建人 */
	private Integer noticeCreator;

	/** 公告内容 */
	private String noticeContent;

	/** 公告查阅次数 */
	private Integer noticeHit;

	/** 公告创建时间 */
	private Date noticeTime;

	/** 地区ID （面授班使用，自己的？？？） */
	private Integer areaID;

	/** 是否满员 （自己的？？？） */
	private Short hasFull;

	// -----------vo,dto相关属性 等-----------------//

	// -------------- 班级考期相关 --------------------//

	/** 考期名称 */
	private String termName;

	/** 考期类型 */
	private String termType;

	/** 考期 */
	private String termYear;

	/** 考期 */
	private String termMonth;

	// -------------- 班级课程相关--------------------//

	/** 班级对应的课程ID集合字符串 */
	private String courseSet;
	/** 班级对应的课程名称集合字符串 */
	private String courseNames;

	// -------------- 班级辅导相关--------------------//

	/** 分班策略所对应辅导ID */
	private Integer majorID;

	/** 辅导名称 */
	private String majorName;

	/** 辅导所对应的网站ID */
	private Integer siteID;

	/** （总）管理员ID */
	private Integer teacherID;

	/** （总）管理员名称 */
	private String teacherName;
	/** 全部管理员名称 */
	private String teacherNames;

	/** 班级学员名称 */
	private String userName;

	/** 班级管理员所在组织ID */
	private Integer orgID;

	/** 班级管理员编号，名称 ，班主任名称 */
	private String manager;

	private Integer currSeq;// 当前空闲的seq

	/** 分班策略 对应的每班限制人数 */
	private Short limitNum;

	/** 面授开始时间 */
	private Date msStartTime;

	/** 面授结束时间 */
	private Date msEndTime;

	private Integer roomStatus;// 聊天室状态

	/** 最新班级公告（黑板报）添加日期 */
	private Date classMsg1Time;
	/** 最新班级公告（黑板报）距今的天数 */
	private Integer classMsg1Day;
	/** 最新班级活动添加日期 */
	private Date classMsg2Time;
	/** 最新班级活动距今的天数 */
	private Integer classMsg2Day;
	// 班主任teacherCode
	private String adviserTeacherCode;
	/** 是否教务人员 */
	private Boolean teachUser;
	/** 和老师在同一个组织机构下的其他老师ID */
	private List<Integer> sameOrgTeachers;

	/**
	 * 
	 * 得到创建时间
	 */
	public String getCreateTimeStr() {
		if (this.createTime != null) {
			return DateUtil.getNowDateString(this.createTime, "yyyy-MM-dd");
		} else {
			return "";
		}
	}

	/** 得到状态显示值 */
	public String getStatusStr() {
		if (this.status == null) {
			return "";
		} else {
			return Contants.status.get(this.status);
		}
	}

	/**
	 * 班级是否满员显示值
	 */
	public String getHasFullStr() {
		if (this.hasFull == null) {
			return "";
		} else {
			return Contants.hasFull.get(this.hasFull);
		}
	}

	public Short getHasFull() {
		return hasFull;
	}

	public void setHasFull(Short hasFull) {
		this.hasFull = hasFull;
	}

	public Integer getAreaID() {
		return areaID;
	}

	public void setAreaID(Integer areaID) {
		this.areaID = areaID;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getCourseSet() {
		return courseSet;
	}

	public void setCourseSet(String courseSet) {
		this.courseSet = courseSet;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTermYear() {
		return termYear;
	}

	public void setTermYear(String termYear) {
		this.termYear = termYear;
	}

	public String getTermMonth() {
		return termMonth;
	}

	public void setTermMonth(String termMonth) {
		this.termMonth = termMonth;
	}

	public Short getLimitNum() {
		return limitNum;
	}

	public void setLimitNum(Short limitNum) {
		this.limitNum = limitNum;
	}

	public Integer getCurrSeq() {
		return currSeq;
	}

	public void setCurrSeq(Integer currSeq) {
		this.currSeq = currSeq;
	}

	public Integer getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(Integer teacherID) {
		this.teacherID = teacherID;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public Integer getStrategyID() {
		return strategyID;
	}

	public void setStrategyID(Integer strategyID) {
		this.strategyID = strategyID;
	}

	public Short getCurrCount() {
		return currCount;
	}

	public void setCurrCount(Short currCount) {
		this.currCount = currCount;
	}

	public String getClassDesc() {
		if (this.classDesc == null) {
			return "";
		}
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

	public Integer getNoticeCreator() {
		return noticeCreator;
	}

	public void setNoticeCreator(Integer noticeCreator) {
		this.noticeCreator = noticeCreator;
	}

	public String getDisplayAdviser() {
		return displayAdviser;
	}

	public void setDisplayAdviser(String displayAdviser) {
		this.displayAdviser = displayAdviser;
	}

	public void setDisplayAdviserStr(String adviserTeacherCode,
			String teacherName) {
		if (StringUtils.isBlank(adviserTeacherCode)) {
			this.displayAdviser = "";
		} else if (adviserTeacherCode.equals("-1")) {
			this.displayAdviser = "-1";
		} else {
			this.displayAdviser = adviserTeacherCode + "号"
					+ StringUtils.substring(teacherName, 0, 1) + "老师";
		}
	}

	public Integer getTermID() {
		return termID;
	}

	public void setTermID(Integer termID) {
		this.termID = termID;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public Integer getNoticeHit() {
		return noticeHit;
	}

	public void setNoticeHit(Integer noticeHit) {
		this.noticeHit = noticeHit;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getNoticeTime() {
		return noticeTime;
	}

	public void setNoticeTime(Date noticeTime) {
		this.noticeTime = noticeTime;
	}

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
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

	public Integer getOrgID() {
		return orgID;
	}

	public void setOrgID(Integer orgID) {
		this.orgID = orgID;
	}

	public Date getMsStartTime() {
		return msStartTime;
	}

	public void setMsStartTime(Date msStartTime) {
		this.msStartTime = msStartTime;
	}

	public Date getMsEndTime() {
		return msEndTime;
	}

	public void setMsEndTime(Date msEndTime) {
		this.msEndTime = msEndTime;
	}

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}

	public Integer getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(Integer roomStatus) {
		this.roomStatus = roomStatus;
	}

	public String getTeacherNames() {
		return teacherNames;
	}

	public void setTeacherNames(List<Classteacher> tlist) {
		StringBuffer sb = new StringBuffer("");
		for (Classteacher ct : tlist) {
			sb.append(ct.getTeacherName()).append(";");
		}
		this.teacherNames = sb.toString();
	}

	public Integer getClassMsg1Day() {
		return classMsg1Day;
	}

	public void setClassMsg1Day(Integer classMsg1Day) {
		this.classMsg1Day = classMsg1Day;
	}

	public Integer getClassMsg2Day() {
		return classMsg2Day;
	}

	public void setClassMsg2Day(Integer classMsg2Day) {
		this.classMsg2Day = classMsg2Day;
	}

	public Date getClassMsg1Time() {
		return classMsg1Time;
	}

	public void setClassMsg1Time(Date classMsg1Time) {
		this.classMsg1Time = classMsg1Time;
	}

	public Date getClassMsg2Time() {
		return classMsg2Time;
	}

	public void setClassMsg2Time(Date classMsg2Time) {
		this.classMsg2Time = classMsg2Time;
	}

	public String getAdviserTeacherCode() {
		return adviserTeacherCode;
	}

	public void setAdviserTeacherCode(String adviserTeacherCode) {
		this.adviserTeacherCode = adviserTeacherCode;
	}

	public void setAdviserTeacherCodeStr(String adviserTeacher) {
		if (StringUtils.isBlank(displayAdviser)) {
			this.adviserTeacherCode = "";
		} else {
			if (displayAdviser.indexOf("号") != -1) {
				adviserTeacherCode = displayAdviser.substring(0,
						displayAdviser.indexOf("号"));
			}
		}
	}

	public String getCourseNames() {
		return courseNames;
	}

	public void setCourseNames(List<Course> courses) {
		StringBuffer sb = new StringBuffer("");
		for (Course c : courses) {
			sb.append(c.getCourseName()).append(";");
		}
		this.courseNames = sb.toString();
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

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}