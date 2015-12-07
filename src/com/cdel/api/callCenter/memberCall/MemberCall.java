package com.cdel.api.callCenter.memberCall;

import java.io.Serializable;
import java.util.Date;

/**
 *   
 * <p>
 * 实验班与精品班‘回访记录表（为呼叫中心定制开发的 DTO VO）
 * </p>
 * 
 * @author XU xiaoguang Create at:2014-03-14 上午11:53:33
 */
@SuppressWarnings("serial")
public class MemberCall   implements Serializable {

	/** 回访ID */
	private Integer outboundID;
	// 学员编号
	private Integer userID;
	// 班级ID
	private Integer classID;
	// 学习状况
	private String studyStatus;
	// 回访状态
	private Integer callStatus; // 回访状态 0电话打不通、1成功回访
	
	// 回访人 就是班主任
	private Integer  caller;
	// 回访时间
	private Date callTime;
	 
	// 最后一次更新时间
	private  Date updateTime;
	
	// 关联表中的回访记录主键
	private Integer callID;
	
	//回访系统标志type : 0: 原先第一个回访系统的的标志，1：第二个回访系统个标志
	private Integer type;
	
	
	 
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getCallID() {
		return callID;
	}
	public void setCallID(Integer callID) {
		this.callID = callID;
	}
	 
	 
	public Date getCallTime() {
		return callTime;
	}
	public void setCallTime(Date callTime) {
		this.callTime = callTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getCaller() {
		return caller;
	}
	public void setCaller(Integer caller) {
		this.caller = caller;
	}
	public Integer getOutboundID() {
		return outboundID;
	}
	public void setOutboundID(Integer outboundID) {
		this.outboundID = outboundID;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Integer getClassID() {
		return classID;
	}
	public void setClassID(Integer classID) {
		this.classID = classID;
	}
	public String getStudyStatus() {
		return studyStatus;
	}
	public void setStudyStatus(String studyStatus) {
		this.studyStatus = studyStatus;
	}
	public Integer getCallStatus() {
		return callStatus;
	}
	public void setCallStatus(Integer callStatus) {
		this.callStatus = callStatus;
	}
}