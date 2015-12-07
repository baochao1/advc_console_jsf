package com.cdel.advc.membernote.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 
 * <p>
 * 班级留言（学员端）实体
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-4 下午3:11:29
 */
@SuppressWarnings("serial")
public class Membernote extends BaseDomain implements Serializable {

	/** 班级消息ID */
	private Integer noteID;

	/** 班级ID */
	private Integer classID;

	/** 消息标题 */
	private String msgTitle;

	/** 消息内容 */
	private String msgContent;

	/** 创建者 */
	private Integer creator;

	/** 创建时间 */
	private Date createTime;

	/** 消息类别 ：1留言本；2学习心得 */
	private Short msgType;

	/** 点击数 */
	private Integer hit;

	/** 状态 */
	private Short status;

	/** 辅导ID */
	private Integer majorID;

	/** 显示类型(可见范围) */
	private Short viewType;

	/** 是否标记 */
	private String marks;

	/** 是否解决 */
	private Short isSolve;

	/** 是否置顶 */
	private Short isTop;

	// ----------------- vo dto ------------------//

	/** 排序方式 */
	private Short sort;

	/** 消息个数 ？？？？？ */
	private Integer noteCount;

	/** 组织ID */
	private Integer orgID;

	/** 辅导名称 */
	private String majorName;

	/** 教师名称 ？？？？ */
	private String teacherName;

	/** 创建者名称 */
	private String userName;

	/** 班级名称 */
	private String className;

	/** 开始时间 */
	private Date startDate;

	/** 结束时间 */
	private Date endDate;

	/** 得到状态显示值 */
	public String getStatusStr() {
		if (this.status == null) {
			return "";
		} else {
			return Contants.status.get(this.status);
		}
	}

	/** 得到类别显示值 */
	public String getMsgTypeStr() {
		if (this.msgType == null) {
			return "";
		} else {
			return Contants.membernoteType.get(this.msgType);
		}
	}

	/** 得到创建时间 */
	public String getCreateTimeStr() {
		return DateUtil.getNowDateString(this.createTime, "yyyy-MM-dd HH:mm");
	}

	/** 是否解决显示值 */
	public String getIsSolveStr() {
		if (this.isSolve == null) {
			this.isSolve = 0;
		}
		return Contants.membernoteIsSolve.get(this.isSolve);
	}

	/** 是否置顶显示值 */
	public String getIsTopStr() {
		if (this.isTop == null) {
			this.isTop = 0;
		}
		return Contants.membernoteIsTop.get(this.isTop);
	}

	/** 显示范围 显示值 */
	public String getViewTypeStr() {
		if (this.msgType == 2) {
			if (this.viewType == null) {
				return "";
			} else {
				return Contants.membernoteViewType.get(this.viewType);
			}
		} else {
			return "--";
		}
	}

	public Short getIsTop() {
		return isTop;
	}

	public void setIsTop(Short isTop) {
		this.isTop = isTop;
	}

	public String getMarks() {
		return marks;
	}

	public void setMarks(String marks) {
		this.marks = marks;
	}

	public Short getIsSolve() {
		return isSolve;
	}

	public void setIsSolve(Short isSolve) {
		this.isSolve = isSolve;
	}

	public Integer getOrgID() {
		return orgID;
	}

	public void setOrgID(Integer orgID) {
		this.orgID = orgID;
	}

	public Short getViewType() {
		return viewType;
	}

	public void setViewType(Short viewType) {
		this.viewType = viewType;
	}

	public Integer getNoteCount() {
		return noteCount;
	}

	public void setNoteCount(Integer noteCount) {
		this.noteCount = noteCount;
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

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}

	public Integer getClassID() {
		return classID;
	}

	public void setClassID(Integer classID) {
		this.classID = classID;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Integer getNoteID() {
		return noteID;
	}

	public void setNoteID(Integer noteID) {
		this.noteID = noteID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	public Short getMsgType() {
		return msgType;
	}

	public void setMsgType(Short msgType) {
		this.msgType = msgType;
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

	public String getCreateTimeAsStream() {

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String date = null;
		if (this.getCreateTime() != null) {
			date = sf.format(this.getCreateTime());
		}
		return date;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
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