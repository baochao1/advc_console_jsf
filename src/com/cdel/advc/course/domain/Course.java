package com.cdel.advc.course.domain;

import java.util.Date;

import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 课程 entity.
 * 
 * @author Haiying Du
 */

public class Course extends BaseDomain implements java.io.Serializable {

	private static final long serialVersionUID = 4395192876602468963L;
	private Integer courseID;// ID
	private Integer majorID;// 辅导（职称）ID
	private String courseName;// 课程名称
	private Short courseType;// 课程类型：实验班1、精品班2
	private String courseCode;// 课程代码，来自选课系统
	private Short courseYear;// 课程年份
	private Integer previewCourse;// 预习课程
	private Short status;// 状态：1 正常，2 异常
	private Integer creator;// 创建人
	private Date createTime;// 创建时间
	private String courseSmsName;// 短信课程名
	private Short isMemberMsgOpen;// 是否开通
	private String memberMsgTitle;// 消息标题
	private String memberMsgContent;// 消息内容
	private Integer listID;// 内容答疑板ID(对内课ID)
	private Short isPre;// 是否预习课程标识
	private Integer siteID;// 站点ID
	private Short faceType;// 是否面授课，0：否；1：是

	// ---vo相关属性
	private String majorName;
	private String teacherName;// 创建者老师名字
	private String previewCourseName;
	private String courseTypes;// 课程类型：实验班1、精品班2
	private String courseCodes;
	private String courseSet;// 套餐下对应的 课程集合
	public String getCourseSet() {
		return courseSet;
	}

	public void setCourseSet(String courseSet) {
		this.courseSet = courseSet;
	}

	public Course() {
		super();
	}

	public Integer getCourseID() {
		return courseID;
	}

	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Short getCourseType() {
		return courseType;
	}

	public void setCourseType(Short courseType) {
		this.courseType = courseType;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public Short getCourseYear() {
		return courseYear;
	}

	public void setCourseYear(Short courseYear) {
		this.courseYear = courseYear;
	}

	public Integer getPreviewCourse() {
		return previewCourse;
	}

	public void setPreviewCourse(Integer previewCourse) {
		this.previewCourse = previewCourse;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCourseSmsName() {
		return courseSmsName;
	}

	public void setCourseSmsName(String courseSmsName) {
		this.courseSmsName = courseSmsName;
	}

	public Short getIsMemberMsgOpen() {
		return isMemberMsgOpen;
	}

	public void setIsMemberMsgOpen(Short isMemberMsgOpen) {
		this.isMemberMsgOpen = isMemberMsgOpen;
	}

	public String getMemberMsgTitle() {
		return memberMsgTitle;
	}

	public void setMemberMsgTitle(String memberMsgTitle) {
		this.memberMsgTitle = memberMsgTitle;
	}

	public String getMemberMsgContent() {
		return memberMsgContent;
	}

	public void setMemberMsgContent(String memberMsgContent) {
		this.memberMsgContent = memberMsgContent;
	}

	public Integer getListID() {
		return listID;
	}

	public void setListID(Integer listID) {
		this.listID = listID;
	}

	public Short getIsPre() {
		return isPre;
	}

	public void setIsPre(Short isPre) {
		this.isPre = isPre;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	/**
	 * 得到课程类型名称
	 */
	public String getCourseTypeStr() {
		if (this.courseType == null || this.courseType == 0) {
			return "";
		}
		return Contants.courseTypeMap.get(this.courseType);
	}

	public String getPreviewCourseName() {
		return previewCourseName;
	}

	public void setPreviewCourseName(String previewCourseName) {
		this.previewCourseName = previewCourseName;
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

	public String getCreateTimeStr() {
		return DateUtil.getNowDateString(createTime, "yyyy-MM-dd");
	}

	public Integer getSiteID() {
		return siteID;
	}

	public void setSiteID(Integer siteID) {
		this.siteID = siteID;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Short getFaceType() {
		return faceType;
	}

	public void setFaceType(Short faceType) {
		this.faceType = faceType;
	}

	public String getFaceTypeStr() {
		if (this.faceType == null) {
			return "";
		}
		return Contants.yesorno.get(faceType);
	}

	public String getCourseTypes() {
		return courseTypes;
	}

	public void setCourseTypes(String courseTypes) {
		this.courseTypes = courseTypes;
	}

	public String getCourseCodes() {
		return courseCodes;
	}

	public void setCourseCodes(String courseCodes) {
		this.courseCodes = courseCodes;
	}

	@Override
	public String toString() {
		return "Course [courseID=" + courseID + ", majorID=" + majorID
				+ ", courseName=" + courseName + ", courseType=" + courseType
				+ ", courseCode=" + courseCode + ", courseYear=" + courseYear
				+ ", previewCourse=" + previewCourse + ", status=" + status
				+ ", creator=" + creator + ", createTime=" + createTime
				+ ", courseSmsName=" + courseSmsName + ", listID=" + listID
				+ ", isPre=" + isPre + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((courseID == null) ? 0 : courseID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Course))
			return false;
		Course other = (Course) obj;
		if (courseID == null) {
			if (other.courseID != null)
				return false;
		} else if (!courseID.equals(other.courseID))
			return false;
		return true;
	}

}