package com.cdel.advc.courseware.domain;

import java.io.Serializable;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 
 * <p>
 * 课程课件（关联） 实体
 * </p>
 * 
 * @author 张苏磊
 */
@SuppressWarnings("serial")
public class Courseware extends BaseDomain implements Serializable {
	/** 关联ID */
	private Integer cwRelationID;

	/** 课程ID */
	private String courseCode;

	/** 课件ID */
	private String cwID;

	public Integer getCwRelationID() {
		return cwRelationID;
	}

	public void setCwRelationID(Integer cwRelationID) {
		this.cwRelationID = cwRelationID;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCwID() {
		return cwID;
	}

	public void setCwID(String cwID) {
		this.cwID = cwID;
	}

}
