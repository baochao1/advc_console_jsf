package com.cdel.advc.classnews.domain;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 
 * <p>
 * 班级动态（新鲜事，新闻） 实体
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-4 下午5:26:05
 */
@SuppressWarnings("serial")
public class Classnews extends BaseDomain implements Serializable {

	/** 班级新闻ID */
	private Integer classNewsID;

	/** 新闻标题 */
	private String newsTitle;

	/** 新闻类别：1'学习动态 ，2'活动动态， 3课件更新， 4 答疑板 */
	private Short newsType;

	/** 创建者 */
	private Integer creator;

	/** 创建时间 */
	private Date createTime;

	/** 状态 */
	private Short status;

	/** 班级ID */
	private Integer classID;

	// -----------vo dto --------//

	/** 班级名称 */
	private String className;

	/** 创建者名称-教师 */
	private String teacherName;

	/** 辅导ID */
	private Integer majorID;

	/**   */
	private Integer userID;

	/** 创建者名称-学员 */
	private String userName;

	/** 得到状态显示值 */
	public String getStatusStr() {
		if (this.status == null) {
			return "";
		} else {
			return Contants.status.get(this.status);
		}
	}

	/** 得到类别显示值 */
	public String getNewsTypeStr() {
		if (this.newsType == null) {
			return "";
		} else {
			return Contants.classnewsType.get(this.newsType);
		}
	}

	/**
	 * 得到创建者名称，可能为教师或学员
	 */
	public String getRealUserName() {
		if (StringUtils.isNotBlank(this.teacherName)) {
			return "(教师)" + this.teacherName;
		} else if (StringUtils.isNotBlank(this.userName)) {
			return "(学员)" + this.userName;
		} else {
			return "";
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}

	public Integer getClassNewsID() {
		return classNewsID;
	}

	public void setClassNewsID(Integer classNewsID) {
		this.classNewsID = classNewsID;
	}

	public Integer getClassID() {
		return classID;
	}

	public void setClassID(Integer classID) {
		this.classID = classID;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Short getNewsType() {
		return newsType;
	}

	public void setNewsType(Short newsType) {
		this.newsType = newsType;
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

	/** 得到创建时间 */
	public String getCreateTimeStr() {
		return DateUtil.getNowDateString(this.createTime, "yyyy-MM-dd HH:mm");
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

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

}