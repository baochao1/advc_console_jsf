package com.cdel.advc.gdb.plan.action;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.gdb.plan.domain.GdbStudyPlan;
import com.cdel.advc.gdb.plan.facade.GdbStudyPlanFacade;
import com.cdel.util.BaseAction;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class GdbStudyPlanAction extends BaseAction<GdbStudyPlan> implements
		Serializable {
	@ManagedProperty("#{gdbStudyPlanFacade}")
	private GdbStudyPlanFacade gdbStudyPlanFacade;

	/** DataTable组件分页模型 */
	private LazyDataModel<GdbStudyPlan> page;
	/** 查询条件 */
	private GdbStudyPlan filterGdbPlan = new GdbStudyPlan();
	private String siteCourseIDs;
	private Integer userID;

	@PostConstruct
	public void initMyAdvanceMemberAction() {
		userID = this.getIntegerParameter("userID");
		siteCourseIDs = this.getParameter("siteCourseIds");
		filterGdbPlan.setUserID(userID);
		super.heighti2 = super.calHeight(14.5f / 20);
		page = gdbStudyPlanFacade.findPage(filterGdbPlan);
	}

	public void search() {
		pageTable.setFirst(0);
		search4U();
	}

	public void search4U() {
		page = gdbStudyPlanFacade.findPage(filterGdbPlan);
		this.updateComponent("searchForm:studyPlanTable");
	}

	public LazyDataModel<GdbStudyPlan> getPage() {
		return page;
	}

	public void setPage(LazyDataModel<GdbStudyPlan> page) {
		this.page = page;
	}

	public GdbStudyPlan getFilterGdbPlan() {
		return filterGdbPlan;
	}

	public void setFilterGdbPlan(GdbStudyPlan filterGdbPlan) {
		this.filterGdbPlan = filterGdbPlan;
	}

	public void setGdbStudyPlanFacade(GdbStudyPlanFacade gdbStudyPlanFacade) {
		this.gdbStudyPlanFacade = gdbStudyPlanFacade;
	}

	public String getSiteCourseIDs() {
		return siteCourseIDs;
	}

	public Integer getUserID() {
		return userID;
	}

}
