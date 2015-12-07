package com.cdel.advc.gdb.paper.action;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.gdb.paper.domain.GdbTestPaper;
import com.cdel.advc.gdb.paper.facade.GdbTestPaperFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class GdbTestPaperAction extends BaseAction<GdbTestPaper> implements
		Serializable {
	@ManagedProperty(value = "#{gdbTestPaperFacade}")
	private GdbTestPaperFacade gdbTestPaperFacade;

	private LazyDataModel<GdbTestPaper> page;
	private GdbTestPaper searchGdbTestPaper = new GdbTestPaper();
	private Integer siteCourseID;
	private Integer courseID;
	private Integer studyPlanID;
	private String qzurl = Contants.qzConsoleViewPaper;
	private String qzurl2 = Contants.qzConsoleAddPaper;
	private String qzurl3 = Contants.qzConsoleEditPaper;

	@PostConstruct
	public void initTechnologyMsgAction() {
		siteCourseID = this.getIntegerParameter("siteCourseID");
		courseID = this.getIntegerParameter("courseID");
		studyPlanID = this.getIntegerParameter("studyPlanID");
		searchGdbTestPaper.setStudyPlanID(studyPlanID);
		this.page = this.gdbTestPaperFacade.findPage(searchGdbTestPaper);
		super.heighti2 = super.calHeight(14.5f / 20);
	}

	/**
	 * 查询
	 * 
	 * @throws Exception
	 */
	public void search() {
		pageTable.setFirst(0);
		search4U();
	}

	public void search4U() {
		this.page = this.gdbTestPaperFacade.findPage(searchGdbTestPaper);
		this.updateComponent("searchForm:paperTable");
	}

	public LazyDataModel<GdbTestPaper> getPage() {
		return page;
	}

	public void setPage(LazyDataModel<GdbTestPaper> page) {
		this.page = page;
	}

	public GdbTestPaper getSearchGdbTestPaper() {
		return searchGdbTestPaper;
	}

	public void setSearchGdbTestPaper(GdbTestPaper searchGdbTestPaper) {
		this.searchGdbTestPaper = searchGdbTestPaper;
	}

	public void setGdbTestPaperFacade(GdbTestPaperFacade gdbTestPaperFacade) {
		this.gdbTestPaperFacade = gdbTestPaperFacade;
	}

	public Integer getStudyPlanID() {
		return studyPlanID;
	}

	public Integer getSiteCourseID() {
		return siteCourseID;
	}

	public Integer getCourseID() {
		return courseID;
	}

	public String getQzurl() {
		return qzurl;
	}

	public String getQzurl2() {
		return qzurl2;
	}

	public String getQzurl3() {
		return qzurl3;
	}

}
