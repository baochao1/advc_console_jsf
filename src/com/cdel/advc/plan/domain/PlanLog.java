package com.cdel.advc.plan.domain;

import java.io.Serializable;
import java.util.Date;

import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 
 * <p>
 * 学习计划修改日志 实体
 * </p>
 * 
 * @author Du Haiying Create at:2013-8-1 上午8:49:45
 */
@SuppressWarnings("serial")
public class PlanLog extends BaseDomain implements Serializable {

	private Integer planLogID;

	/** 修改描述 */
	private String planLogDesc;

	private Integer planID;

	/** 计划修改人ID */
	private Integer creator;

	/** 修改时间 */
	private Date createTime;

	// ------------- vo dto --------------//

	/** 修改人姓名 */
	private String creatorName;

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Integer getPlanLogID() {
		return planLogID;
	}

	public void setPlanLogID(Integer planLogID) {
		this.planLogID = planLogID;
	}

	public String getPlanLogDesc() {
		return planLogDesc;
	}

	public void setPlanLogDesc(String planLogDesc) {
		this.planLogDesc = planLogDesc;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Integer getPlanID() {
		return planID;
	}

	public void setPlanID(Integer planID) {
		this.planID = planID;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}