/*
 *                                                                    
 */
package com.cdel.advc.faq.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 
 * <p>
 * 互助答疑
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-3 下午5:48:21
 */
@SuppressWarnings("serial")
public class Faq extends BaseDomain implements Serializable {

	/** ID */
	private Integer faqID;

	/** 父ID */
	private Integer parentID;

	/** 提问主题 */
	private String faqTitle;

	/** 提问内容 */
	private String faqContent;

	/** 答疑类别： 0主题；1继续（回帖） */
	private Short faqType;

	/** 创建人ID 或者 回复人为学员时的 回复人ID */
	private Integer userID;

	/** 创建时间 */
	private Date createTime;

	/** 班级ID */
	private Integer classID;

	/** 课程ID */
	private Integer courseID;

	/** 平均评分 */
	private Float score;

	/** 评分次数 */
	private Short scoreTimes;

	/** 点击数 */
	private Integer hit;

	/** 解决时间 */
	private Date solveTime;

	/** 解决状态 */
	private Short solveStatus;

	/** 答疑状态 */
	private Short status;

	// ------------------- 华丽丽的分割线 -------------------------/

	/** 辅导ID */
	private Integer majorID;

	/** 章节 */
	private Short chapterNum;

	/** 回复次数 */
	private Integer replyNum;

	/** 修改时间 */
	private Date modifyTime;

	/** 回复人为教师时的 教师ID */
	private Integer teacherID;

	// ------------------- dto,vo 等属性 -------------------------/

	/** 班级名称 */
	private String className;

	/** 回复人（学生）名称 */
	private String userName;

	/** 回复人（教师）名称 */
	private String teacherName;

	/** 回复人为教师时 教师编号 */
	private String teacherCode;

	/** 真正回复人名称 */
	private String replyUserName;

	/** 真正回复人ID */
	private Integer replyer;

	/** 开始时间 */
	private Date startDate;

	/** 结束时间 */
	private Date endDate;

	/** 回复状态 */
	private Short answerStatus;

	/**  */
	private String realName;

	/**  */
	private String faqReply;

	/**  */
	private String majorName;

	/**  */
	private Integer pageCount;

	/**  */
	private Integer continueCount;

	/**  */
	private Short sort;

	/**  */
	private String courseName;

	/**  */
	private Float virtualScore;

	/**  */
	private Integer orgID;

	/** 得到回复状态显示值 */
	public String getSolveStatusStr() {
		if (this.solveStatus == null) {
			this.solveStatus = 0;
		}
		return Contants.solveStatus.get(this.solveStatus);
	}

	/** 得到状态显示值 */
	public String getStatusStr() {
		if (this.status == null) {
			return "";
		} else {
			return Contants.status.get(this.status);
		}
	}

	/** 得到创建时间 */
	public String getCreateTimeStr() {
		if (this.createTime != null) {
			return DateUtil.getNowDateString(this.createTime,
					"yyyy-MM-dd HH:mm");
		} else {
			return "";
		}
	}

	/**
	 * 由于回复人可能为学员或教师，所以处理之
	 */
	public String getReplyUserName() {

		if (StringUtils.isNotBlank(this.userName)) {
			this.replyUserName = this.userName;
		} else if (StringUtils.isNotBlank(this.teacherName)) {
			this.replyUserName = "(教师)" + this.teacherName;
		} else {
			this.replyUserName = "";
		}

		return this.replyUserName;
	}

	/**
	 * 由 表中replyNum（回复次数） 得到是否回复状态
	 */
	public String getAnswerStatusStr() {
		if (this.replyNum > 0) {
			this.answerStatus = 1;
		} else {
			this.answerStatus = 0;
		}
		return Contants.answerStatus.get(this.answerStatus);
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getOrgID() {
		return orgID;
	}

	public void setOrgID(Integer orgID) {
		this.orgID = orgID;
	}

	public Short getAnswerStatus() {
		return answerStatus;
	}

	public void setAnswerStatus(Short answerStatus) {
		this.answerStatus = answerStatus;
	}

	public Short getChapterNum() {
		if (chapterNum == null) {
			return 0;
		}
		return chapterNum;
	}

	public void setChapterNum(Short chapterNum) {
		this.chapterNum = chapterNum;
	}

	public Integer getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(Integer teacherID) {
		this.teacherID = teacherID;
	}

	public Float getVirtualScore() {
		return virtualScore;
	}

	public void setVirtualScore(Float virtualScore) {
		this.virtualScore = virtualScore;
	}

	public Integer getCourseID() {
		return courseID;
	}

	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Date getSolveTime() {
		return solveTime;
	}

	public void setSolveTime(Date solveTime) {
		this.solveTime = solveTime;
	}

	public Short getSolveStatus() {
		return solveStatus;
	}

	public void setSolveStatus(Short solveStatus) {
		this.solveStatus = solveStatus;
	}

	public Integer getHit() {
		return hit;
	}

	public void setHit(Integer hit) {
		this.hit = hit;
	}

	public Short getSort() {
		return sort;
	}

	public void setSort(Short sort) {
		this.sort = sort;
	}

	public Integer getContinueCount() {
		return continueCount;
	}

	public void setContinueCount(Integer continueCount) {
		this.continueCount = continueCount;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
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

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
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

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Integer getFaqID() {
		return faqID;
	}

	public void setFaqID(Integer faqID) {
		this.faqID = faqID;
	}

	public String getFaqContent() {
		return faqContent;
	}

	public void setFaqContent(String faqContent) {
		this.faqContent = faqContent;
	}

	public String getFaqReply() {
		return faqReply;
	}

	public void setFaqReply(String faqReply) {
		this.faqReply = faqReply;
	}

	public String getFaqTitle() {
		return faqTitle;
	}

	public void setFaqTitle(String faqTitle) {
		this.faqTitle = faqTitle;
	}

	public Integer getReplyer() {
		return replyer;
	}

	public void setReplyer(Integer replyer) {
		this.replyer = replyer;
	}

	public Short getFaqType() {
		return faqType;
	}

	public void setFaqType(Short faqType) {
		this.faqType = faqType;
	}

	public Integer getParentID() {
		return parentID;
	}

	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}

	public Integer getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(Integer replyNum) {
		this.replyNum = replyNum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setReplyUserName(String replyUserName) {
		this.replyUserName = replyUserName;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public Short getScoreTimes() {
		return scoreTimes;
	}

	public void setScoreTimes(Short scoreTimes) {
		this.scoreTimes = scoreTimes;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getTeacherCode() {
		return teacherCode;
	}

	public void setTeacherCode(String teacherCode) {
		this.teacherCode = teacherCode;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}