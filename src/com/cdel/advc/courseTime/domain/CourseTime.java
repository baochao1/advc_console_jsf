package com.cdel.advc.courseTime.domain;

import java.io.Serializable;
import java.util.Date;

import com.cdel.util.Contants;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 课程要求听课时长设置表
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
public class CourseTime extends BaseDomain implements Serializable {

	private Integer courseTimeID;
	private Integer qzCourseID;
	private Integer stageID;
	private Integer scheduleTime;// 要求听课时长，单位：分钟
	private Short status;
	private Date createTime;

	// --------------------------------------------------------------

	private String stageName;

	public Integer getCourseTimeID() {
		return courseTimeID;
	}

	public void setCourseTimeID(Integer courseTimeID) {
		this.courseTimeID = courseTimeID;
	}

	public Integer getQzCourseID() {
		return qzCourseID;
	}

	public void setQzCourseID(Integer qzCourseID) {
		this.qzCourseID = qzCourseID;
	}

	public Integer getStageID() {
		return stageID;
	}

	public void setStageID(Integer stageID) {
		this.stageID = stageID;
	}

	public Integer getScheduleTime() {
		return scheduleTime;
	}

	public void setScheduleTime(Integer scheduleTime) {
		this.scheduleTime = scheduleTime;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public String getStatusStr() {
		if (this.status == null) {
			return "";
		}
		return Contants.status.get(status);
	}

}
