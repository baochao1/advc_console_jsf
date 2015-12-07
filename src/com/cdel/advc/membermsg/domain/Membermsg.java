package com.cdel.advc.membermsg.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 
 * <p>
 * 学员-教师间消息
 * </p>
 * 
 * @author 张苏磊 Create at:2013-7-4 上午11:14:58
 */
@SuppressWarnings("serial")
public class Membermsg extends BaseDomain implements Serializable {

	/** 消息ID */
	private Integer msgID;

	/** 班级ID */
	private Integer classID;

	private String classcode;// 班级代码

	/** 班级名称 */
	private String className;

	/** 班主任名称 */
	private String displayAdviser;//

	/** 消息主题 */
	private String msgTitle;

	/** 消息内容 */
	private String msgContent;

	/** 发送时间 */
	private Date sentTime;

	/** 回复时间 */
	private Date replyTime;

	/** 回复内容 */
	private String replyContent;

	private String replyContentOld;// 原回复

	/** 状态 */
	private Short status;

	/** 发送人 ID */
	private Integer userID;// 学员ID

	private String userName;// 学员名称

	private Integer teacherID;// 老师ID

	/** 老师名称 */
	private String teacherName;//

	/** 老师代码 */
	private String teacherCode;

	private Integer orgID;

	/** 开始时间 */
	private Date startTime;

	/** 结束时间 */
	private Date endTime;

	private Integer reply;// 回复状态
	private Integer majorID;// 辅导id
	private String manager;// 班主任或管理员
	/** 是否已读 */
	private Short readFlag;

	private Short msgType;// 消息类型: 正常0、技术1、教师发送的消息3

	/** 过期小时数 */
	private Integer postHours;

	/** 是否教务人员 */
	private Boolean teachUser;

	/** 和老师在同一个组织机构下的其他老师ID */
	private List<Integer> sameOrgTeachers;

	/** 消息类型显示值 */
	public String getMsgTypeStr() {
		if (this.msgType == null) {
			return "";
		} else {
			return Contants.msgType.get(msgType);
		}
	}

	/** 发送时间格式化显示 */
	public String getSentTimeStr() {
		return DateUtil.getNowDateString(sentTime, "yyyy-MM-dd HH:mm");
	}

	/** 状态显示值 */
	public String getStatusStr() {
		if (this.status == null) {
			return "";
		} else {
			return Contants.status.get(status);
		}
	}

	/** 回复时间格式化显示 */
	public String getReplyTimeStr() {
		return DateUtil.getNowDateString(replyTime, "yyyy-MM-dd HH:mm");
	}

	/** 阅读状态显示值 */
	public String getReadFlagStr() {
		if (this.readFlag == null) {
			return "";
		} else {
			return Contants.readStatus.get(this.readFlag);
		}
	}

	public Integer getPostHours() {
		return postHours;
	}

	public void setPostHours(Integer postHours) {
		this.postHours = postHours;
	}

	public String getTeacherCode() {
		return teacherCode;
	}

	public void setTeacherCode(String teacherCode) {
		this.teacherCode = teacherCode;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getMsgID() {
		return msgID;
	}

	public void setMsgID(Integer msgID) {
		this.msgID = msgID;
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public Date getSentTime() {
		return sentTime;
	}

	public void setSentTime(Date sentTime) {
		this.sentTime = sentTime;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	public Short getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(Short readFlag) {
		this.readFlag = readFlag;
	}

	public Integer getClassID() {
		return classID;
	}

	public void setClassID(Integer classID) {
		this.classID = classID;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClasscode() {
		return classcode;
	}

	public void setClasscode(String classcode) {
		this.classcode = classcode;
	}

	public String getDisplayAdviser() {
		return displayAdviser;
	}

	public void setDisplayAdviser(String displayAdviser) {
		this.displayAdviser = displayAdviser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Integer getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(Integer teacherID) {
		this.teacherID = teacherID;
	}

	public String getReplyContentOld() {
		return replyContentOld;
	}

	public void setReplyContentOld(String replyContentOld) {
		this.replyContentOld = replyContentOld;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Short getMsgType() {
		return msgType;
	}

	public void setMsgType(Short msgType) {
		this.msgType = msgType;
	}

	public Integer getOrgID() {
		return orgID;
	}

	public void setOrgID(Integer orgID) {
		this.orgID = orgID;
	}

	public Integer getReply() {
		return reply;
	}

	public void setReply(Integer reply) {
		this.reply = reply;
	}

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public Boolean getTeachUser() {
		return teachUser;
	}

	public void setTeachUser(Boolean teachUser) {
		this.teachUser = teachUser;
	}

	public List<Integer> getSameOrgTeachers() {
		return sameOrgTeachers;
	}

	public void setSameOrgTeachers(List<Integer> sameOrgTeachers) {
		this.sameOrgTeachers = sameOrgTeachers;
	}

	public String getReplayFlagStr() {
		if (replyTime == null) {
			return Contants.answerStatus.get(new Short("0"));
		} else {
			return Contants.answerStatus.get(new Short("1"));
		}
	}

	public Short getReplayFlag() {
		if (replyTime == null) {
			return 0;
		} else {
			return 1;
		}
	}

}