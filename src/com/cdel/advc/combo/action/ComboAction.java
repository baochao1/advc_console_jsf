package com.cdel.advc.combo.action;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UISelectOne;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.combo.domain.Combo;
import com.cdel.advc.combo.facade.ComboFacade;
import com.cdel.advc.course.domain.Course;
import com.cdel.advc.course.facade.CourseFacade;
import com.cdel.util.BaseAction;

/**
 * 
 * 套餐Bean
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ComboAction extends BaseAction<Combo> implements Serializable {

	@ManagedProperty(value = "#{comboFacade}")
	private ComboFacade comboFacade;
	@ManagedProperty(value = "#{courseFacade}")
	private CourseFacade courseFacade;

	private LazyDataModel<Combo> comboPage;
	private Combo searchCombo = new Combo();// 搜索的课程
	private Integer siteID;// 网站ID
	private List<Course> courseList;// 课程List

	private Combo updateCombo;// 添加/修改的课程

	@PostConstruct
	public void initCourseAction() {
		siteID = this.getCurrentSiteID();
		this.comboPage = this.comboFacade.findPage(searchCombo);
		super.heighti2 = super.calHeight(11.5f / 20);
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
		this.comboPage = this.comboFacade.findPage(searchCombo);
		this.updateComponent("searchForm:tableList");
	}

	/**
	 * 根据修改辅导，取课程
	 * 
	 * @param e
	 */
	public void changeMajorID(AjaxBehaviorEvent e) {
		Integer majorID = (Integer) ((UISelectOne) e.getSource()).getValue();
		Course c = new Course();
		c.setMajorID(majorID);
		c.setStatus((short) 1);
		c.setIsPre((short) 2);
		courseList = courseFacade.getCourseListByMajor(c);
	}

	public LazyDataModel<Combo> getComboPage() {
		return comboPage;
	}

	public void setComboPage(LazyDataModel<Combo> comboPage) {
		this.comboPage = comboPage;
	}

	public Combo getSearchCombo() {
		return searchCombo;
	}

	public void setSearchCombo(Combo searchCombo) {
		this.searchCombo = searchCombo;
	}

	public Combo getUpdateCombo() {
		return updateCombo;
	}

	public void setUpdateCombo(Combo updateCombo) {
		this.updateCombo = updateCombo;
	}

	public void setComboFacade(ComboFacade comboFacade) {
		this.comboFacade = comboFacade;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public void setCourseFacade(CourseFacade courseFacade) {
		this.courseFacade = courseFacade;
	}

	public Integer getSiteID() {
		return siteID;
	}

}