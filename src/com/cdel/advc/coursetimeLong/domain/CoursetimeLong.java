package com.cdel.advc.coursetimeLong.domain;

import java.io.Serializable;
import java.util.Date;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

@SuppressWarnings("serial")
public class CoursetimeLong extends BaseDomain implements Serializable {

	private Integer timeLongID;// 主键
	private Integer courseID;// 课程ID
	private String countDate;// 平均时长日期
	private Integer timeLong;// 学习时长
	private Date createTime;// 创建日期
	private Integer op;// 修改人
	private Date dCountDate;// 平均时长日期
	private String courseName;// 课程名称
	private String courseCode;// 课程代码
	private String teacherName;// 老师名称
	private String cwID;// 课件ID
	private Integer userNum;// 学习的人数

	public String getCreateTimeStr() {
		return DateUtil.getNowDateString(getCreateTime(), "yyyy-MM-dd HH:mm");
	}

	public Integer getCourseID() {
		return courseID;
	}

	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}

	public Integer getOp() {
		return op;
	}

	public void setOp(Integer op) {
		this.op = op;
	}

	public String getCountDate() {
		return countDate;
	}

	public void setCountDate(String countDate) {
		this.countDate = countDate;
	}

	public Integer getTimeLongID() {
		return timeLongID;
	}

	public void setTimeLongID(Integer timeLongID) {
		this.timeLongID = timeLongID;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getTimeLong() {
		return timeLong;
	}

	public int getTimeLongI() {
		if (timeLong == null) {
			return 0;
		}
		return timeLong;
	}

	public void setTimeLong(Integer timeLong) {
		this.timeLong = timeLong;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getCwID() {
		return cwID;
	}

	public void setCwID(String cwID) {
		this.cwID = cwID;
	}

	public Date getdCountDate() {
		return dCountDate;
	}

	public void setdCountDate(Date dCountDate) {
		this.dCountDate = dCountDate;
	}

	public Integer getUserNum() {
		return userNum;
	}

	public int getUserNumI() {
		if (userNum == null) {
			return 1;
		}
		return userNum;
	}

	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}

}
