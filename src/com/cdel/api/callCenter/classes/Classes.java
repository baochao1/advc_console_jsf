package com.cdel.api.callCenter.classes;

import java.io.Serializable;
import java.util.Date;

/** 
 *                                               
 * <p>呼叫中心相关 班级 实体（VO，DTO）</p>
 * 
 * @author Du Haiying
 * Create at:2013-10-14 上午11:36:01
 */
@SuppressWarnings("serial")
public class Classes implements Serializable{
    
	/** 所属网站id**/
	private Integer siteID;
	
	/** 班级ID**/
	private Integer classID;
	
	/** 考期编号**/
	private Integer termID;
	
	/** 班级名称**/
	private String className;
	
	/** 班级编号**/
	private String classCode;
	
	/** 教师（班主任）名称  */
	private String teacherName;
	private String  displayAdviser;
	
	/** 考期名称 */
    private String examName;
    
    /** 学员个数 */
    private Short studentCount;
    private Short currCount;
    
    /** 状态  1正常，0无效 */
    private Integer status;
    
    /**创建时间 **/
    public String createTime;
    
    /**最后一次更新时间 **/
    public String updateTime;
    public Date updateDate;
    /** 返回的记录数**/
    private Integer count;
    
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getTermID() {
		return termID;
	}

	public void setTermID(Integer termID) {
		this.termID = termID;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
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

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public Short getStudentCount() {
		return studentCount;
	}

	public void setStudentCount(Short studentCount) {
		this.studentCount = studentCount;
	}

	public String getDisplayAdviser() {
		return displayAdviser;
	}

	public void setDisplayAdviser(String displayAdviser) {
		this.displayAdviser = displayAdviser;
	}

	public Short getCurrCount() {
		return currCount;
	}

	public void setCurrCount(Short currCount) {
		this.currCount = currCount;
	}

	public Integer getSiteID() {
		return siteID;
	}

	public void setSiteID(Integer siteID) {
		this.siteID = siteID;
	}

}