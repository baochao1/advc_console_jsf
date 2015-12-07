package com.cdel.advc.strategy.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.course.domain.Course;
import com.cdel.advc.strategy.domain.CourseReg;
import com.cdel.advc.strategy.domain.Strategy;
import com.cdel.advc.strategy.facade.StrategyFacade;
import com.cdel.advc.testterm.domain.Testterm;
import com.cdel.util.BaseAction;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class StrategyAction extends BaseAction<Strategy> implements
		Serializable {
	@ManagedProperty(value = "#{strategyFacade}")
	private StrategyFacade strategyFacade;

	private LazyDataModel<Strategy> strategyPage;
	private Strategy searchStrategy = new Strategy();// 搜索条件
	private Integer siteID;// 网站ID
	private List<Course> courseList;// 课程List
	private List<Course> courseList2;// 辅导下的全部课程List，不随着策略类别而变化
	private List<Testterm> termList;// 考期List
	private List<Testterm> termList2;// 辅导下的全部考期List，不随着策略类别而变化
	private List<CourseReg> courseRegs = new ArrayList<CourseReg>();// 策略规则里的课程

	@PostConstruct
	public void initSitenoticeAction() {
		siteID = this.getCurrentSiteID();
		strategyPage = strategyFacade.findPage(searchStrategy, this.getCurrentSiteID());
		super.heighti2 = super.calHeight(11.5f / 20);
	}

	/**
	 * 查询，需要回第一页
	 */
	public void search() {
		pageTable.setFirst(0);
		search4U();
	}

	/**
	 * 查询，不需要回到第一页
	 */
	public void search4U() {
		strategyPage = strategyFacade.findPage(searchStrategy, siteID);
		this.updateComponent("searchForm:strategyList");
	}

	public void setStrategyFacade(StrategyFacade strategyFacade) {
		this.strategyFacade = strategyFacade;
	}

	public LazyDataModel<Strategy> getStrategyPage() {
		return strategyPage;
	}

	public void setStrategyPage(LazyDataModel<Strategy> strategyPage) {
		this.strategyPage = strategyPage;
	}

	public Strategy getSearchStrategy() {
		return searchStrategy;
	}

	public void setSearchStrategy(Strategy searchStrategy) {
		this.searchStrategy = searchStrategy;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public List<Course> getCourseList2() {
		return courseList2;
	}

	public void setCourseList2(List<Course> courseList2) {
		this.courseList2 = courseList2;
	}

	public List<Testterm> getTermList() {
		return termList;
	}

	public void setTermList(List<Testterm> termList) {
		this.termList = termList;
	}

	public List<Testterm> getTermList2() {
		return termList2;
	}

	public void setTermList2(List<Testterm> termList2) {
		this.termList2 = termList2;
	}

	public Integer getSiteID() {
		return siteID;
	}

	public List<CourseReg> getCourseRegs() {
		return courseRegs;
	}

	public void setCourseRegs(List<CourseReg> courseRegs) {
		this.courseRegs = courseRegs;
	}

}