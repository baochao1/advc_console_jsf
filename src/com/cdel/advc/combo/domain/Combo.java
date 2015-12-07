package com.cdel.advc.combo.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 选课套餐.
 * 
 * @author 张苏磊
 */
@SuppressWarnings("serial")
public class Combo extends BaseDomain implements Serializable {

	private Integer comboID;
	// 套餐代码
	private String comboCode;
	private String comboName;
	// 关联的课程ID
	private String courseSet;
	private Date createTime;
	private Integer majorID;
	private Integer creator;
	private Short status;
	// 关联的课程ID，数组
	private Integer[] courseIDs;

	// other
	private Integer siteID;
	private String majorName;
	private String comboCodes;
	private String courseCode;// 套餐中的课程的code

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public Short getStatus() {
		return status;
	}

	public String getStatusStr() {
		if (this.status == null) {
			return "";
		}
		return Contants.status.get(status);
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getCourseSet() {
		return courseSet;
	}

	public void setCourseSet(String courseSet) {
		this.courseSet = courseSet;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String getCreateTimeStr() {
		return DateUtil.getNowDateString(createTime, "yyyy-MM-dd");
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getComboName() {
		return comboName;
	}

	public void setComboName(String comboName) {
		this.comboName = comboName;
	}

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public String getComboCode() {
		return comboCode;
	}

	public void setComboCode(String comboCode) {
		this.comboCode = comboCode;
	}

	public Integer getComboID() {
		return comboID;
	}

	public void setComboID(Integer comboID) {
		this.comboID = comboID;
	}

	public Integer getSiteID() {
		return siteID;
	}

	public void setSiteID(Integer siteID) {
		this.siteID = siteID;
	}

	public Integer[] getCourseIDs() {
		if (!StringUtils.isBlank(courseSet)) {
			String[] arr = StringUtils.split(courseSet, ",");
			courseIDs = new Integer[arr.length];
			for (int i = 0; i < arr.length; i++) {
				courseIDs[i] = new Integer(arr[i]);
			}
		}
		return courseIDs;
	}

	public void setCourseIDs(Integer[] courseIDs) {
		this.courseIDs = courseIDs;
	}

	public String getComboCodes() {
		return comboCodes;
	}

	public void setComboCodes(String comboCodes) {
		this.comboCodes = comboCodes;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

}