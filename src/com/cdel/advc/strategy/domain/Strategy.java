package com.cdel.advc.strategy.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cdel.advc.course.domain.Course;
import com.cdel.advc.major.domain.MainMajor;
import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 
 * <p>
 * 分班策略实体
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-1 上午11:40:30
 */
@SuppressWarnings("serial")
public class Strategy extends BaseDomain implements Serializable {

	private Short priorityLevel;// 优先级

	private String setDesc;// 策略描述

	private Short limitNum;// 每班限制人数

	private Integer majorID;// 辅导ID

	private Short strategyType;// 策略类别
	private Short isAuto;// 是否参与分班

	private Integer creator;// 创建人

	private Short status;// 状态

	private Integer strategyID;// 策略ID

	private String courseRegex;// 课程组合(Regex)

	private String courseSet;// 套餐组合

	private Date createTime;// 创建时间

	private String andOrStr;// 与或串
	// vo
	private Integer siteID;
	private String shortName;
	private String majorName;
	private Short currClassNo;// 策略下的班级的当前排号
	private String creatorName;
	private Integer termID;// 考期ID
	private Integer classNum;// 班级数
	private String termName;// 考期名称
	private String termYear;// 考期年份
	private String termMonth;// 考期月份
	private Short termType;// 考期类别
	private String courseNames;// 策略里包含的课程名
	private String studyCourse;// 学员报的符合该策略的课程
	private String regSeq;// 规则执行顺序
	
	private Integer mainMajorID;
	
	private List<MainMajor> mainMajors;//跨数据库查询的解决方式
	
	/**获取对应的主库辅导名称  跨数据库查询的解决方式*/
	public String getMainMajorName() {
		
		String name = "";
		
		if(this.mainMajorID != null && this.mainMajors != null && this.mainMajors.size() > 0){
			MainMajor mainMajor = new MainMajor();
			mainMajor.setMajorID(this.mainMajorID);
			int i = this.mainMajors.indexOf(mainMajor);
			if(i != -1){
				name = this.mainMajors.get(i).getMajorName();
			}
		}
		return name;
	}

	public void setMainMajors(List<MainMajor> mainMajors) {
		this.mainMajors = mainMajors;
	}

	public Integer getClassNum() {
		if (classNum == null) {
			classNum = 0;
		}
		return classNum;
	}

	public void setClassNum(Integer classNum) {
		this.classNum = classNum;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Short getCurrClassNo() {
		return currClassNo;
	}

	public void setCurrClassNo(Short currClassNo) {
		this.currClassNo = currClassNo;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Short getPriorityLevel() {
		return priorityLevel;
	}

	public void setPriorityLevel(Short priorityLevel) {
		this.priorityLevel = priorityLevel;
	}

	public String getSetDesc() {
		return setDesc;
	}

	public void setSetDesc(String setDesc) {
		this.setDesc = setDesc;
	}

	public Short getLimitNum() {
		return limitNum;
	}

	public void setLimitNum(Short limitNum) {
		this.limitNum = limitNum;
	}

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Short getStatus() {
		return status;
	}

	public String getStatusStr() {
		if (status == null) {
			return "";
		} else {
			return Contants.status.get(status);
		}
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Integer getStrategyID() {
		return strategyID;
	}

	public void setStrategyID(Integer strategyID) {
		this.strategyID = strategyID;
	}

	public String getCourseRegex() {
		return courseRegex;
	}

	public void setCourseRegex(String courseRegex) {
		this.courseRegex = courseRegex;
	}

	public String getCourseSet() {
		return courseSet;
	}

	public void setCourseSet(String courseSet) {
		this.courseSet = courseSet;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String getCreateTimeStr() {
		return DateUtil.getNowDateString(createTime, "yyyy-MM-dd");
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getSiteID() {
		return siteID;
	}

	public void setSiteID(Integer siteID) {
		this.siteID = siteID;
	}

	public Short getStrategyType() {
		return strategyType;
	}

	public String getStrategyTypeStr() {
		if (strategyType == null) {
			return "";
		} else {
			return Contants.strategyTypeMap.get(strategyType);
		}
	}

	public void setStrategyType(Short strategyType) {
		this.strategyType = strategyType;
	}

	public Integer getTermID() {
		return termID;
	}

	public void setTermID(Integer termID) {
		this.termID = termID;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
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

	public Short getTermType() {
		return termType;
	}

	public void setTermType(Short termType) {
		this.termType = termType;
	}

	public String getCourseNames() {
		return courseNames;
	}

	public void setCourseNames(List<Course> courses) {
		if (courses != null) {
			StringBuffer sb = new StringBuffer("");
			for (Course c : courses) {
				sb.append(c.getCourseName()).append(";");
			}
			this.courseNames = sb.toString();
		} else {
			this.courseNames = "";
		}
	}

	public String getStudyCourse() {
		return studyCourse;
	}

	public void setStudyCourse(String studyCourse) {
		this.studyCourse = studyCourse;
	}

	public String getCourseNamesShort() {
		if (courseNames == null) {
			return "";
		} else {
			return StringUtils.substring(courseNames, 0, 30) + "...";
		}
	}

	public String getAndOrStr() {
		return andOrStr;
	}

	public void setAndOrStr(String andOrStr) {
		this.andOrStr = andOrStr;
	}

	public Short getIsAuto() {
		return isAuto;
	}

	public void setIsAuto(Short isAuto) {
		this.isAuto = isAuto;
	}

	public String getRegSeq() {
		return regSeq;
	}

	public void setRegSeq(String regSeq) {
		this.regSeq = regSeq;
	}
	
	public Integer getMainMajorID() {
		return mainMajorID;
	}

	public void setMainMajorID(Integer mainMajorID) {
		this.mainMajorID = mainMajorID;
	}

	@Override
	public String toString() {
		return "Strategy [priorityLevel=" + priorityLevel + ", setDesc="
				+ setDesc + ", limitNum=" + limitNum + ", majorID=" + majorID
				+ ", strategyType=" + strategyType + ", courseRegex="
				+ courseRegex + ", courseSet=" + courseSet + ", siteID="
				+ siteID + ", termID=" + termID + "]";
	}

}