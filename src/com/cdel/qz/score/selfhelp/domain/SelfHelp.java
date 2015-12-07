package com.cdel.qz.score.selfhelp.domain;

import java.util.Date;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 自助练习
 * 
 * @author 张苏磊
 */

public class SelfHelp extends BaseDomain implements java.io.Serializable {
	private static final long serialVersionUID = 5937446608344962761L;
	private Integer helpPaperID;
	private String helpPaperName;
	private Integer creator;
	private String realName;
	private Date createTime;

	public Integer getHelpPaperID() {
		return helpPaperID;
	}

	public void setHelpPaperID(Integer helpPaperID) {
		this.helpPaperID = helpPaperID;
	}

	public String getHelpPaperName() {
		return helpPaperName;
	}

	public void setHelpPaperName(String helpPaperName) {
		this.helpPaperName = helpPaperName;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String getCreateTimeStr() {
		return DateUtil.getNowDateString(createTime, "yyyy-MM-dd HH:mm");
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}