/*
 * @Title: MemberClassAction.java
 * @Package com.cdel.advc.memberclass.action
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-7-11 上午9:21:29
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-7-11                          
 */
package com.cdel.advc.memberclass.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.cdel.advc.classes.domain.Classes;
import com.cdel.advc.classes.facade.ClassesFacade;
import com.cdel.advc.classteacher.facade.ClassteacherFacade;
import com.cdel.advc.course.domain.Course;
import com.cdel.advc.course.facade.CourseFacade;
import com.cdel.advc.memberclass.domain.MemberClass;
import com.cdel.advc.memberclass.facade.MemberClassFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.StringUtil;

/**
 * <p>
 * 班级成员bean
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-11 上午9:21:29
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MemberClassAction extends BaseAction<MemberClass> implements
		Serializable {

	@ManagedProperty("#{memberClassFacade}")
	private MemberClassFacade memberClassFacade;
	@ManagedProperty("#{classesFacade}")
	private ClassesFacade classesFacade;
	@ManagedProperty("#{courseFacade}")
	private CourseFacade courseFacade;
	@ManagedProperty(value = "#{classteacherFacade}")
	private ClassteacherFacade classteacherFacade;

	private LazyDataModel<MemberClass> page;
	private MemberClass searchMemberClass = new MemberClass();
	private Classes classes;
	private Integer classID;
	private Integer siteID;// 网站ID
	private String userName;// 在班级查询里输入的学员name
	private List<Course> coursesList =  new ArrayList<Course>();// 对应的课程列表
	@PostConstruct
	public void init() {
		siteID = this.getCurrentSiteID();
		classID = this.getIntegerParameter("classID");// 班级ID
		userName = this.getParameter("userName");
		if (classID != null) {
			searchMemberClass.setClassID(classID);
			classes = this.classesFacade.findByID(classID);
			if (this.classes != null) {
				classes.setTeacherNames(classteacherFacade.findList(classID));
				classes.setCourseNames(courseFacade.findCoursesByIDs(StringUtil.splitIDs(this.classes.getCourseSet())));
				coursesList = courseFacade.findCoursesByIDs(StringUtil.splitIDs(this.classes.getCourseSet()));
				searchMemberClass.setStatus((short) 1);
				searchMemberClass.setUserName(userName);
				
				this.page = this.memberClassFacade.findPage(searchMemberClass);
			}
		} else {
			this.addWarnMessage("info", "classID不能为空！");
		}
		super.heighti2 = super.calHeight(12.2f / 20);
	}

	/**
	 * 查询
	 */
	public void search() {
		pageTable.setFirst(0);
		search4U();
	}

	/**
	 * 查询
	 */
	public void search4U() {
		// 根据学习课程查询 
		String  courseIDsStr  = searchMemberClass.getCourseIDsStr();
		searchMemberClass.setCourseIDsStr(courseIDsStr);
		searchMemberClass.setCourseIDs(searchMemberClass.getCourseIDs());
		this.page = this.memberClassFacade.findPage(searchMemberClass);
		this.updateComponent("searchForm:memberClassTable");
	}

	public LazyDataModel<MemberClass> getPage() {
		return page;
	}

	public void setPage(LazyDataModel<MemberClass> page) {
		this.page = page;
	}

	public Classes getClasses() {
		return classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

	public void setMemberClassFacade(MemberClassFacade memberClassFacade) {
		this.memberClassFacade = memberClassFacade;
	}

	public void setClassesFacade(ClassesFacade classesFacade) {
		this.classesFacade = classesFacade;
	}

	public void setCourseFacade(CourseFacade courseFacade) {
		this.courseFacade = courseFacade;
	}

	public void setClassteacherFacade(ClassteacherFacade classteacherFacade) {
		this.classteacherFacade = classteacherFacade;
	}

	public Integer getClassID() {
		return classID;
	}

	public Integer getSiteID() {
		return siteID;
	}

	public String getUserName() {
		return userName;
	}

	public MemberClass getSearchMemberClass() {
		return searchMemberClass;
	}

	public void setSearchMemberClass(MemberClass searchMemberClass) {
		this.searchMemberClass = searchMemberClass;
	}

	public List<Course> getCoursesList() {
		return coursesList;
	}

	public void setCoursesList(List<Course> coursesList) {
		this.coursesList = coursesList;
	}

}
