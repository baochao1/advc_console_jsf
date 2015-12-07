package com.cdel.advc.teacher.action;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.classes.domain.Classes;
import com.cdel.advc.teacher.domain.Teacher;
import com.cdel.advc.teacher.facade.TeacherFacade;
import com.cdel.advc.testterm.domain.Testterm;
import com.cdel.util.BaseAction;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class TeacherAction extends BaseAction<Teacher> implements Serializable {

	@ManagedProperty(value = "#{teacherFacade}")
	private TeacherFacade teacherFacade;

	// 查询条件对象
	private Teacher filterTeacher = new Teacher();
	// 主件分页模型
	private LazyDataModel<Teacher> page;
	private Integer siteID;
	private List<Testterm> termList;
	private List<Classes> classesList;// 考期下的班级

	@PostConstruct
	public void initTeacherAction() {
		siteID = this.getCurrentSiteID();
		page = teacherFacade.findPage(filterTeacher);
	}

	/**
	 * 查询教师信息
	 */
	public void search() {
		pageTable.setFirst(0);
		search4U();
	}

	public void search4U() {
		page = teacherFacade.findPage(filterTeacher);
		this.updateComponent("searchForm:teacherList");
	}

	public void setTeacherFacade(TeacherFacade teacherFacade) {
		this.teacherFacade = teacherFacade;
	}

	public Teacher getFilterTeacher() {
		return filterTeacher;
	}

	public void setFilterTeacher(Teacher filterTeacher) {
		this.filterTeacher = filterTeacher;
	}

	public LazyDataModel<Teacher> getPage() {
		return page;
	}

	public void setPage(LazyDataModel<Teacher> page) {
		this.page = page;
	}

	public Integer getSiteID() {
		return siteID;
	}

	public List<Testterm> getTermList() {
		return termList;
	}

	public void setTermList(List<Testterm> termList) {
		this.termList = termList;
	}

	public List<Classes> getClassesList() {
		return classesList;
	}

	public void setClassesList(List<Classes> classesList) {
		this.classesList = classesList;
	}

}
