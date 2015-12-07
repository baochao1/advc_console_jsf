package com.cdel.advc.studentCourse.domain;

import java.util.Date;
import com.chnedu.plat.rad.domain.BaseDomain;

public class StudentCourse extends BaseDomain implements java.io.Serializable {
	private static final long serialVersionUID = 4395192876602468963L;
	private Integer courseID;// ID
	private String siteTypeName;// 网站名称
	private Integer siteType;// 网站名称
	private String userName;// 学员代码
	private String courseCode;// 课程代码
	private String courseName;// 课程名称
	private String majorName;// 课程名称
	private Date openTime;// 开课时间
	private Integer areaID;// areaID

	public Integer getCourseID() {
		return courseID;
	}

	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}

	public String getSiteTypeName() {
		return siteTypeName;
	}

	public void setSiteTypeName(String siteTypeName) {
		this.siteTypeName = siteTypeName;
	}

	public Integer getSiteType() {
		return siteType;
	}

	public void setSiteType(Integer siteType) {
		this.siteType = siteType;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Integer getAreaID() {
		return areaID;
	}

	public void setAreaID(Integer areaID) {
		this.areaID = areaID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
