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
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;

import com.cdel.advc.plan.domain.PlanChapter;
import com.cdel.advc.plan.domain.PlanSpecialGeneral;
import com.cdel.advc.plan.facade.PlanSpecialGeneralFacade;
import com.cdel.util.BaseAction;

/**
 * <p>
 * 学员设置特殊学习时间
 * </p>
 * 
 * @author 张苏磊
 */
@ManagedBean
@SuppressWarnings("serial")
@ViewScoped
public class PlanSpecialGeneralAction extends BaseAction<PlanChapter> {

	@ManagedProperty(value = "#{planSpecialGeneralFacade}")
	private PlanSpecialGeneralFacade planSpecialGeneralFacade;

	private Integer planID;
	private LazyDataModel<PlanSpecialGeneral> specialGeneralPage;
	private PlanSpecialGeneral searchSpecialGeneral = new PlanSpecialGeneral();

	@PostConstruct
	public void initTechnologyMsgAction() {
		planID = this.getIntegerParameter("planID");
		searchSpecialGeneral.setPlanID(planID);
		specialGeneralPage = planSpecialGeneralFacade
				.findPage(searchSpecialGeneral);
		super.heighti2 = super.calHeight(15f / 20);
	}

	/**
	 * 查询
	 */
	public void search() {
		pageTable.setFirst(0);
		specialGeneralPage = planSpecialGeneralFacade
				.findPage(searchSpecialGeneral);
		this.updateComponent("searchForm:specialGeneralList");
	}

	public Integer getPlanID() {
		return planID;
	}

	public void setPlanSpecialGeneralFacade(
			PlanSpecialGeneralFacade planSpecialGeneralFacade) {
		this.planSpecialGeneralFacade = planSpecialGeneralFacade;
	}

	public LazyDataModel<PlanSpecialGeneral> getSpecialGeneralPage() {
		return specialGeneralPage;
	}

	public void setSpecialGeneralPage(
			LazyDataModel<PlanSpecialGeneral> specialGeneralPage) {
		this.specialGeneralPage = specialGeneralPage;
	}

	public PlanSpecialGeneral getSearchSpecialGeneral() {
		return searchSpecialGeneral;
	}

	public void setSearchSpecialGeneral(PlanSpecialGeneral searchSpecialGeneral) {
		this.searchSpecialGeneral = searchSpecialGeneral;
	}

}
