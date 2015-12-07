package com.cdel.advc.serviceText.action;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.course.domain.Course;
import com.cdel.advc.serviceText.domain.ServiceText;
import com.cdel.advc.serviceText.facade.ServiceTextFacade;
import com.cdel.util.BaseAction;

/**
 * 学习建议
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ServiceTextAction extends BaseAction<Course> implements
		Serializable {

	@ManagedProperty(value = "#{serviceTextFacade}")
	private ServiceTextFacade serviceTextFacade;

	private Integer majorID;
	private Integer courseID;
	private LazyDataModel<ServiceText> page;
	private ServiceText searchServiceText = new ServiceText();

	@PostConstruct
	public void initSmsTemplateAction() {
		majorID = this.getIntegerParameter("majorID");
		courseID = this.getIntegerParameter("courseID");
		searchServiceText.setCourseID(courseID);
		searchServiceText.setStatus((short) 1);
		page = serviceTextFacade.findPage(searchServiceText);
		super.heighti2 = super.calHeight(14f / 20);
	}

	/**
	 * 查询
	 */
	public void search() {
		pageTable.setFirst(0);
		search4U();
	}

	/**
	 * 更新查询
	 */
	public void search4U() {
		page = serviceTextFacade.findPage(searchServiceText);
		this.updateComponent("searchForm:serviceTextList");
	}

	public LazyDataModel<ServiceText> getPage() {
		return page;
	}

	public void setPage(LazyDataModel<ServiceText> page) {
		this.page = page;
	}

	public ServiceText getSearchServiceText() {
		return searchServiceText;
	}

	public void setSearchServiceText(ServiceText searchServiceText) {
		this.searchServiceText = searchServiceText;
	}

	public Integer getCourseID() {
		return courseID;
	}

	public void setServiceTextFacade(ServiceTextFacade serviceTextFacade) {
		this.serviceTextFacade = serviceTextFacade;
	}

	public Integer getMajorID() {
		return majorID;
	}

}
