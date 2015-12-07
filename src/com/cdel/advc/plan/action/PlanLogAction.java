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

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.plan.domain.PlanLog;
import com.cdel.advc.plan.facade.PlanLogFacade;
import com.cdel.util.BaseAction;

/**
 * <p>
 * 学习计划日志
 * </p>
 * 
 * @author Du Haiying Create at:2013-8-21 下午1:53:36
 */
@ManagedBean
@SuppressWarnings("serial")
@ViewScoped
public class PlanLogAction extends BaseAction<PlanLog> {

	@ManagedProperty("#{planLogFacade}")
	private PlanLogFacade planLogFacade;

	/** 计划操作日志 */
	private LazyDataModel<PlanLog> logPage;
	private PlanLog filterPlanLog = new PlanLog();

	/**
	 * 查询计划修改日志
	 * 
	 * @param planID
	 */
	public void showLogs(Integer planID) {
		if (planID != null) {
			filterPlanLog.setPlanID(planID);
			this.logPage = this.planLogFacade.findPage(filterPlanLog);
		}
	}
	
	public void search() {
		this.pageTable.setFirst(0);
		this.logPage = this.planLogFacade.findPage(filterPlanLog);
		this.updateComponent("logForm:logList");
	}

	public LazyDataModel<PlanLog> getLogPage() {
		return logPage;
	}

	public void setLogPage(LazyDataModel<PlanLog> logPage) {
		this.logPage = logPage;
	}

	public void setPlanLogFacade(PlanLogFacade planLogFacade) {
		this.planLogFacade = planLogFacade;
	}

	public PlanLog getFilterPlanLog() {
		return filterPlanLog;
	}

	public void setFilterPlanLog(PlanLog filterPlanLog) {
		this.filterPlanLog = filterPlanLog;
	}

}
