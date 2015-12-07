package com.cdel.advc.testterm.domain;

import java.io.Serializable;
import java.util.Date;

import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 
 * <p>
 * 考期（届 ，年级） 实体
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-1 上午11:44:21
 */
@SuppressWarnings("serial")
public class Testterm extends BaseDomain implements Serializable {

	/** 考期ID */
	private Integer termID;

	/** 辅导ID */
	private Integer majorID;

	/** 考期名称 */
	private String termName;

	/** 考期类别 : 1实验、2精品、3混合 */
	private Short termType;

	/** 考期年份 */
	private String termYear;

	/** 考期月份 */
	private String termMonth;

	/** 创建人 */
	private Integer creator;

	/** 创建时间 */
	private Date createTime;

	/** 状态（1正常，0无效） */
	private Short status;

	/** 班级简称(生成班级名称的前缀) */
	private String classShortName;

	/** 开始时间 (学习计划开始时间) */
	private Date startTime;

	/** 结束时间(学习计划结束时间) */
	private Date endTime;

	/** 最大学习时间(默认学习时间) */
	private Integer maxStudyTime;

	/** 预习开始时间 */
	private Date preStartTime;

	/** 预习结束时间 */
	private Date preEndTime;

	/** 预习最大时间 */
	private Integer preMaxStudyTime;

	// other
	/** 考期年份、月份按日期显示 */
	private Date termDate;

	private Integer siteID;

	private String majorName;// 辅导名称

	private String creatorName;// 创建人名称

	private Integer classID;// 课程ID

	private String className;// 课程名称

	public Integer getMaxStudyTime() {
		return maxStudyTime;
	}

	public void setMaxStudyTime(Integer maxStudyTime) {
		this.maxStudyTime = maxStudyTime;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getClassID() {
		return classID;
	}

	public void setClassID(Integer classID) {
		this.classID = classID;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public Short getStatus() {
		return status;
	}

	/**
	 * 得到课程状态名称
	 */
	public String getStatusStr() {
		if (this.status == null) {
			return "";
		}
		return Contants.status.get(status);
	}

	public void setStatus(Short status) {
		this.status = status;
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

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}

	public Integer getTermID() {
		return termID;
	}

	public void setTermID(Integer termID) {
		this.termID = termID;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((termID == null) ? 0 : termID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Testterm other = (Testterm) obj;
		if (termID == null) {
			if (other.termID != null)
				return false;
		} else if (!termID.equals(other.termID))
			return false;
		return true;
	}

	public Integer getSiteID() {
		return siteID;
	}

	public void setSiteID(Integer siteID) {
		this.siteID = siteID;
	}

	public String getClassShortName() {
		return classShortName;
	}

	public void setClassShortName(String classShortName) {
		this.classShortName = classShortName;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getPreEndTime() {
		return preEndTime;
	}

	public void setPreEndTime(Date preEndTime) {
		this.preEndTime = preEndTime;
	}

	public Integer getPreMaxStudyTime() {
		return preMaxStudyTime;
	}

	public void setPreMaxStudyTime(Integer preMaxStudyTime) {
		this.preMaxStudyTime = preMaxStudyTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getPreStartTime() {
		return preStartTime;
	}

	public void setPreStartTime(Date preStartTime) {
		this.preStartTime = preStartTime;
	}

	public Date getTermDate() {
		return termDate;
	}

	public void setTermDate(Date termDate) {
		this.termDate = termDate;
	}

	public String getTermDateStr() {
		return termYear + termMonth;
	}

	public String getTermTypeStr() {
		if (termType == null) {
			return "";
		} else {
			return Contants.termTypeMap.get(termType);
		}
	}

}