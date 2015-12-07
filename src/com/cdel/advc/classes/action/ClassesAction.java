/*
 * @Title: ClassesAction.java
 * @Package com.cdel.advc.classes.action
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-7-2 上午11:10:39
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-7-2                          
 */
package com.cdel.advc.classes.action;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.classes.domain.Classes;
import com.cdel.advc.classes.facade.ClassesFacade;
import com.cdel.advc.classteacher.facade.ClassteacherFacade;
import com.cdel.advc.teacher.domain.Teacher;
import com.cdel.advc.teacher.facade.TeacherFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.StringUtil;

/**
 * <p>
 * 班级管理查询相关bean
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-2 上午11:10:39
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClassesAction extends BaseAction<Classes> implements Serializable {

	@ManagedProperty("#{classesFacade}")
	private ClassesFacade classesFacade;
	@ManagedProperty(value = "#{teacherFacade}")
	private TeacherFacade teacherFacade;
	@ManagedProperty(value = "#{classteacherFacade}")
	private ClassteacherFacade classteacherFacade;

	/** DataTable组件分页模型 */
	private LazyDataModel<Classes> page;
	/** 查询条件 */
	private Classes filterClasses = new Classes();
	/** 网站ID */
	private Integer siteID;
	private String from;// =strategy表示从策略管理连过来的

	@PostConstruct
	public void initFacadeAction() {
		from = StringUtil.nullToString(this.getParameter("from"));
		siteID = this.getCurrentSiteID();
		if (from.equals("strategy")) {
			// 从策略管理班级数过来的链接，带有strategyID
			filterClasses.setStrategyID(this.getIntegerParameter("strategyID"));
			super.heighti2 = super.calHeight(16f / 20);
		}else{
			super.heighti2 = super.heighti;
		}
		this.filterClasses.setStatus((short) 1);
		filterClasses.setSiteID(siteID);
		Integer teacherID = this.getCurrentUserID();
		Teacher teacher = this.getCurrentTeacher();
		if (teacher.getParentID() == 1) {// 如果是教务人员可以看本组成员在其他班是总管理员的班级
			filterClasses.setTeachUser(true);
			List<Integer> list = teacherFacade
					.getSameOrgManagerTeacherList(teacherID);
			filterClasses.setSameOrgTeachers(list);
		} else {
			filterClasses.setTeachUser(false);
		}
		if (teacher.getIsHeader() == 0 || teacher.getIsHeader() == 1
				|| teacher.getOrgID() == 23)
			filterClasses.setTeacherID(null);// 技术部，经理，超管可以看所有班
		else if (teacher.getIsHeader() == 4) {// 教务督导，在不分配班级的情况下，可以查看所有班级
			int tutorListCount = classteacherFacade
					.getTutorCountByTeacherID(teacherID);
			if (tutorListCount > 0)
				filterClasses.setTeacherID(teacherID); // 教师助教的班
			else {
				filterClasses.setTeacherID(null);
			}
		} else {
			filterClasses.setTeacherID(teacherID); // 教师助教的班
		}
		this.page = this.classesFacade.findPage(filterClasses);
	}

	/**
	 * 条件查询，强制设置 分页 从索引0位置 开始查询
	 */
	public void search() {
		pageTable.setFirst(0);
	}

	public Classes getFilterClasses() {
		return filterClasses;
	}

	public void setFilterClasses(Classes filterClasses) {
		this.filterClasses = filterClasses;
	}

	public void setClassesFacade(ClassesFacade classesFacade) {
		this.classesFacade = classesFacade;
	}

	public LazyDataModel<Classes> getPage() {
		return page;
	}

	public void setPage(LazyDataModel<Classes> page) {
		this.page = page;
	}

	public void setSiteID(Integer siteID) {
		this.siteID = siteID;
	}

	public Integer getSiteID() {
		return siteID;
	}

	public String getFrom() {
		return from;
	}

	public void setTeacherFacade(TeacherFacade teacherFacade) {
		this.teacherFacade = teacherFacade;
	}

	public void setClassteacherFacade(ClassteacherFacade classteacherFacade) {
		this.classteacherFacade = classteacherFacade;
	}

}
