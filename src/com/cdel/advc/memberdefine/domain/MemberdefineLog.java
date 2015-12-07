package com.cdel.advc.memberdefine.domain;

import java.io.Serializable;
import java.util.Date;

import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

@SuppressWarnings("serial")
public class MemberdefineLog extends BaseDomain implements Serializable {
	private Integer defineLogID;
	private Integer defineID;
	private Integer creator;
	private Date createTime;
	private String teacherName;
	private Short logType;

	public Integer getDefineLogID() {
		return defineLogID;
	}

	public void setDefineLogID(Integer defineLogID) {
		this.defineLogID = defineLogID;
	}

	public Integer getDefineID() {
		return defineID;
	}

	public void setDefineID(Integer defineID) {
		this.defineID = defineID;
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

	public String getCreateTimeStr() {
		return DateUtil.getNowDateString(createTime, "yyyy-MM-dd HH:mm:ss");
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Short getLogType() {
		return logType;
	}

	public String getLogTypeStr() {
		if (logType == null) {
			return "";
		} else {
			return Contants.logTypeMap.get(logType);
		}
	}

	public void setLogType(Short logType) {
		this.logType = logType;
	}

}