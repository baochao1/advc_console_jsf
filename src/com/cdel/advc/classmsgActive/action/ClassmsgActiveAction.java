package com.cdel.advc.classmsgActive.action;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.classmsgActive.domain.ClassmsgActive;
import com.cdel.advc.classmsgActive.facade.ClassmsgActiveFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.StringUtil;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClassmsgActiveAction extends BaseAction<ClassmsgActive> implements
		Serializable {
	@ManagedProperty(value = "#{classmsgActiveFacade}")
	private ClassmsgActiveFacade classmsgActiveFacade;

	private LazyDataModel<ClassmsgActive> classmsgActivePage;
	private Integer siteID;// 网站ID
	private Integer classID;
	private ClassmsgActive searchClassmsgActive = new ClassmsgActive();
	private ClassmsgActive updateClassmsgActive;
	private String from;// =class表示从班级管理连过来的

	@PostConstruct
	public void initTechnologyMsgAction() {
		from = StringUtil.nullToString(this.getParameter("from"));
		siteID = this.getCurrentSiteID();
		searchClassmsgActive.setMsgType((short) 3);
		if (from.equals("class")) {
			classID = this.getIntegerParameter("classID");
			searchClassmsgActive.setClassID(classID);
		}
		this.classmsgActivePage = this.classmsgActiveFacade
				.findPage(searchClassmsgActive);
		super.heighti2 = super.calHeight(11.5f / 20);
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
		this.classmsgActivePage = this.classmsgActiveFacade
				.findPage(searchClassmsgActive);
		this.updateComponent("searchForm:classmsgActiveList");
	}

	public void setClassmsgActiveFacade(
			ClassmsgActiveFacade classmsgActiveFacade) {
		this.classmsgActiveFacade = classmsgActiveFacade;
	}

	public LazyDataModel<ClassmsgActive> getClassmsgActivePage() {
		return classmsgActivePage;
	}

	public void setClassmsgActivePage(
			LazyDataModel<ClassmsgActive> classmsgActivePage) {
		this.classmsgActivePage = classmsgActivePage;
	}

	public Integer getSiteID() {
		return siteID;
	}

	public void setSiteID(Integer siteID) {
		this.siteID = siteID;
	}

	public ClassmsgActive getSearchClassmsgActive() {
		return searchClassmsgActive;
	}

	public void setSearchClassmsgActive(ClassmsgActive searchClassmsgActive) {
		this.searchClassmsgActive = searchClassmsgActive;
	}

	public ClassmsgActive getUpdateClassmsgActive() {
		return updateClassmsgActive;
	}

	public void setUpdateClassmsgActive(ClassmsgActive updateClassmsgActive) {
		this.updateClassmsgActive = updateClassmsgActive;
	}

	public Integer getClassID() {
		return classID;
	}

	public String getFrom() {
		return from;
	}

}
