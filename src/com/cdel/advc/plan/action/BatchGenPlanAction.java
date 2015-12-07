/*
 * @Title: BatchGenPlanAction.java
 * @Package com.cdel.advc.plan.action
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-8-21 下午1:53:36
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-8-21                          
 */
package com.cdel.advc.plan.action;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.cdel.advc.plan.domain.BatchGenPlan;
import com.cdel.util.BaseAction;

/**
 * <p>
 * 批量生成辅导下班级学员计划 bean
 * </p>
 * 
 * @author Du Haiying Create at:2013-8-21 下午1:53:36
 */
@ManagedBean
@SuppressWarnings("serial")
@ViewScoped
public class BatchGenPlanAction extends BaseAction<BatchGenPlan> {

	private Integer majorID;
	private Short planType;
	private Integer siteID;// 网站ID

	@PostConstruct
	public void initTechnologyMsgAction() {
		siteID = this.getCurrentSiteID();
		super.heighti2 = super.calHeight(12f / 20);
	}

	public void checkGenPlans(byte flag) {

		if (this.majorID == null) {
			this.addWarnMessage("info", "请选择辅导！");
			return;
		}
		if (this.planType == null) {
			this.addWarnMessage("info", "请选择计划类型！");
			return;
		}
		if (flag == 0) {
			this.updateComponent("searchForm:messagesTable");
			this.executeScript("cd.show()");
		} else {
			this.executeScript("cd1.show()");
		}
	}

	/** “删除计划、后门” */
	public boolean isCanDelete() {
		return 1242 == this.getCurrentUserID()
				|| 634 == this.getCurrentUserID();
	}

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}

	public Short getPlanType() {
		return planType;
	}

	public void setPlanType(Short planType) {
		this.planType = planType;
	}

	public Integer getSiteID() {
		return siteID;
	}

}
