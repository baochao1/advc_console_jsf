package com.cdel.advc.member.domain;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 
 * <p>
 * 学员 实体
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-18 上午8:35:12
 */
@SuppressWarnings("serial")
public class Member extends BaseDomain implements Serializable {

	/** 学员ID */
	private Integer userID;

	/** 学员代码 */
	private String userName;

	/** 真实姓名 */
	private String realName;

	/** 性别 */
	private Boolean gener;

	/** 出生日期 */
	private Date brithday;

	/** 手机号码 */
	private String telPhone;

	/** 联系地址 */
	private String address;

	/** 网站ID */
	private Integer siteID;

	/** 短信状态 */
	private Short smsStatus;

	/** 创建时间 */
	private Date createTime;

	/** 状态 */
	private Short status;

	// -------- VO DTO-------------//
	private Integer classID;
	private Integer courseID;
	private Integer majorID;
	private String email;
	private String memberSatuts;
	private String courseIDStr;

	private String showUserName;// 对外显示的学员代码=学员代码+网站名称
	
	public String getShowUserName() {
		if(this.siteID==1){
			showUserName = userName +"(会计网)";
		}else if(this.siteID==2){
			showUserName = userName +"(法律网)";
		}else if(this.siteID==3){
			showUserName = userName +"(自考365网)";
		}else if(this.siteID==4){
			showUserName = userName +"(建设网)";
		}else if(this.siteID==5){
			showUserName = userName +"(医学网)";
		}else if(this.siteID==6){
			showUserName = userName +"(中小学教育网)";
		}else if(this.siteID==7){
			showUserName = userName +"(外语教育网)";
		}else if(this.siteID==8){
			showUserName = userName +"(考研网)";
		}else if(this.siteID==9){
			showUserName = userName +"(职教网)";
		}else if(this.siteID==10){
			showUserName = userName +"(财考网)";
		} 
		return showUserName;
	}

	public void setShowUserName(String showUserName) {
		this.showUserName = showUserName;
	}

	/** 学员短信发送状态显示值 */
	public String getSmsStatusStr() {
		if (this.smsStatus == null) {
			return "";
		} else {
			return Contants.smsStatus.get(this.smsStatus);
		}
	}

	public Short getSmsStatus() {
		return smsStatus;
	}

	public void setSmsStatus(Short smsStatus) {
		this.smsStatus = smsStatus;
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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getGener() {
		return gener;
	}

	public void setGener(Boolean gener) {
		this.gener = gener;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getSiteID() {
		return siteID;
	}

	public void setSiteID(Integer siteID) {
		this.siteID = siteID;
	}

	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Date getBrithday() {
		return brithday;
	}

	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}

	public Integer getClassID() {
		return classID;
	}

	public void setClassID(Integer classID) {
		this.classID = classID;
	}

	public Integer getCourseID() {
		return courseID;
	}

	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Member))
			return false;
		Member other = (Member) obj;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		return true;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setBirthdayStr(String birthdayStr) {
		if (StringUtils.isNotBlank(birthdayStr) && !birthdayStr.equals("-")) {
			if (birthdayStr.indexOf("-") > 0) {
				this.brithday = DateUtil
						.stringToDate(birthdayStr, "yyyy-MM-dd");
			} else {
				String str = birthdayStr.substring(0, 4) + "-"
						+ birthdayStr.substring(4, 6) + "-"
						+ birthdayStr.substring(6, 8);
				this.brithday = DateUtil.stringToDate(str, "yyyy-MM-dd");
			}

		}
	}

	public String getMemberSatuts() {
		return memberSatuts;
	}

	public void setMemberSatuts(String memberSatuts) {
		this.memberSatuts = memberSatuts;
	}

	public String getCourseIDStr() {
		return courseIDStr;
	}

	public void setCourseIDStr(String courseIDStr) {
		this.courseIDStr = courseIDStr;
	}

}