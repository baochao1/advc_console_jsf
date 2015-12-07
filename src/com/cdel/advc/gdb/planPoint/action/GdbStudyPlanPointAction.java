package com.cdel.advc.gdb.planPoint.action;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.gdb.planPoint.domain.GdbStudyPlanPoint;
import com.cdel.advc.gdb.planPoint.facade.GdbStudyPlanPointFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class GdbStudyPlanPointAction extends BaseAction<GdbStudyPlanPoint>
		implements Serializable {
	@ManagedProperty("#{gdbStudyPlanPointFacade}")
	private GdbStudyPlanPointFacade gdbStudyPlanPointFacade;

	/** DataTable组件分页模型 */
	private LazyDataModel<GdbStudyPlanPoint> page;
	/** 查询条件 */
	private GdbStudyPlanPoint filterGdbPlanPoint = new GdbStudyPlanPoint();
	private Integer userID;
	private Integer studyPlanID;
	private Integer siteCourseID;
	private Integer courseID;
	private String qzConsoleUrl = Contants.qzConsoleOpenPaper;

	@PostConstruct
	public void initMyAdvanceMemberAction() {
		userID = this.getIntegerParameter("userID");
		studyPlanID = this.getIntegerParameter("studyPlanID");
		siteCourseID = this.getIntegerParameter("siteCourseID");
		courseID = this.getIntegerParameter("courseID");
		filterGdbPlanPoint.setUserID(userID);
		filterGdbPlanPoint.setStudyPlanID(studyPlanID);
		filterGdbPlanPoint.setSiteCourseID(siteCourseID);
		super.heighti2 = super.calHeight(14.5f / 20);
		page = gdbStudyPlanPointFacade.findPage(filterGdbPlanPoint);
	}

	public void search() {
		pageTable.setFirst(0);
		search4U();
	}

	public void search4U() {
		page = gdbStudyPlanPointFacade.findPage(filterGdbPlanPoint);
		this.updateComponent("searchForm:studyPlanPointTable");
	}

	public LazyDataModel<GdbStudyPlanPoint> getPage() {
		return page;
	}

	public void setPage(LazyDataModel<GdbStudyPlanPoint> page) {
		this.page = page;
	}

	public GdbStudyPlanPoint getFilterGdbPlanPoint() {
		return filterGdbPlanPoint;
	}

	public void setFilterGdbPlanPoint(GdbStudyPlanPoint filterGdbPlanPoint) {
		this.filterGdbPlanPoint = filterGdbPlanPoint;
	}

	public void setGdbStudyPlanPointFacade(
			GdbStudyPlanPointFacade gdbStudyPlanPointFacade) {
		this.gdbStudyPlanPointFacade = gdbStudyPlanPointFacade;
	}

	public String getQzConsoleUrl() {
		return qzConsoleUrl;
	}

	public Integer getCourseID() {
		return courseID;
	}

	public Integer getUserID() {
		return userID;
	}

	public Integer getStudyPlanID() {
		return studyPlanID;
	}

	public Integer getSiteCourseID() {
		return siteCourseID;
	}

}
