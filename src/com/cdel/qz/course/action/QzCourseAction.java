package com.cdel.qz.course.action;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.course.domain.Course;
import com.cdel.qz.course.domain.QzCourse;
import com.cdel.qz.course.facade.QzCourseFacade;
import com.cdel.util.BaseAction;

/**
 * 教务课程action
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class QzCourseAction extends BaseAction<Course> implements Serializable {

	@ManagedProperty(value = "#{qzCourseFacade}")
	private QzCourseFacade qzCourseFacade;

	private LazyDataModel<QzCourse> page;
	private QzCourse searchQzCourse = new QzCourse();// 搜索的课程
	private Integer siteID;

	@PostConstruct
	public void initSmsTemplateAction() {
		siteID = this.getCurrentSiteID();
		searchQzCourse.setSiteID(siteID);
		searchQzCourse.setStatus((short) 1);
		page = qzCourseFacade.findPage(searchQzCourse);
		super.heighti2 = super.calHeight(13f / 20);
	}

	/**
	 * 查询
	 */
	public void search() {
		pageTable.setFirst(0);
		page = qzCourseFacade.findPage(searchQzCourse);
		this.updateComponent("searchForm:qzCourseList");
	}

	public LazyDataModel<QzCourse> getPage() {
		return page;
	}

	public void setPage(LazyDataModel<QzCourse> page) {
		this.page = page;
	}

	public QzCourse getSearchQzCourse() {
		return searchQzCourse;
	}

	public void setSearchQzCourse(QzCourse searchQzCourse) {
		this.searchQzCourse = searchQzCourse;
	}

	public Integer getSiteID() {
		return siteID;
	}

	public void setQzCourseFacade(QzCourseFacade qzCourseFacade) {
		this.qzCourseFacade = qzCourseFacade;
	}

}
