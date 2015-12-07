package com.cdel.advc.gdb.membercall.domain;

import java.util.Date;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 
 * 高端班回访记录统计表
 * 
 * @author 张苏磊 Create at:2013-7-10 上午9:40:58
 */
@SuppressWarnings("serial")
public class GdbMemberCallRecord extends BaseDomain implements
		java.io.Serializable {
	private Integer callRecordID;
	private Integer userID;
	private Date successCallTime;
	private Date lastCallTime;

	public Integer getCallRecordID() {
		return callRecordID;
	}

	public void setCallRecordID(Integer callRecordID) {
		this.callRecordID = callRecordID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Date getSuccessCallTime() {
		return successCallTime;
	}

	public void setSuccessCallTime(Date successCallTime) {
		this.successCallTime = successCallTime;
	}

	public Date getLastCallTime() {
		return lastCallTime;
	}

	public void setLastCallTime(Date lastCallTime) {
		this.lastCallTime = lastCallTime;
	}

}
