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

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UISelectOne;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.classes.domain.AllClasses;
import com.cdel.advc.classes.facade.AllClassesFacade;
import com.cdel.advc.strategy.domain.Strategy;
import com.cdel.advc.strategy.facade.StrategyFacade;
import com.cdel.advc.testterm.domain.Testterm;
import com.cdel.advc.testterm.facade.TesttermFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;

/**
 * 
 * <p>
 * 自定义班级管理查询相关bean
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-19 下午2:32:57
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UserDefineClassesAction extends BaseAction<AllClasses> {

	@ManagedProperty("#{allClassesFacade}")
	private AllClassesFacade allClassesFacade;
	@ManagedProperty("#{testtermFacade}")
	private TesttermFacade testtermFacade;
	@ManagedProperty("#{strategyFacade}")
	private StrategyFacade strategyFacade;

	/** DataTable组件分页模型 */
	private LazyDataModel<AllClasses> page;
	/** 查询条件 */
	private AllClasses filterClasses = new AllClasses();
	private Integer classID;// 要操作的classID
	private Integer siteID;
	/** 查询条件---考期列表 */
	private List<Testterm> terms;
	private List<Strategy> strategys;

	@PostConstruct
	public void initUserDefineClassesAction() {
		siteID = this.getCurrentSiteID();
		filterClasses.setAreaID(Contants.USER_DEFINED);// 自定义班级标识
		filterClasses.setSiteID(this.getCurrentSiteID());
		this.page = this.allClassesFacade.findPage(filterClasses);
		super.heighti2 = super.calHeight(11.5f / 20);
	}

	/**
	 * 条件查询，强制设置 分页 从索引0位置 开始查询
	 */
	public void search() {
		pageTable.setFirst(0);
		search4U();
	}

	public void search4U() {
		this.updateComponent("searchForm:udClassesList");
	}

	/**
	 * 根据辅导的修改取班级信息
	 * 
	 * @param e
	 */
	public void changeMajorID(AjaxBehaviorEvent e) {
		Integer majorID = (Integer) ((UISelectOne) e.getSource()).getValue();
		terms = testtermFacade.findList(majorID);
		strategys = strategyFacade.findList(majorID);
	}

	public LazyDataModel<AllClasses> getPage() {
		return page;
	}

	public void setPage(LazyDataModel<AllClasses> page) {
		this.page = page;
	}

	public AllClasses getFilterClasses() {
		return filterClasses;
	}

	public void setFilterClasses(AllClasses filterClasses) {
		this.filterClasses = filterClasses;
	}

	public void setAllClassesFacade(AllClassesFacade allClassesFacade) {
		this.allClassesFacade = allClassesFacade;
	}

	public Integer getClassID() {
		return classID;
	}

	public void setClassID(Integer classID) {
		this.classID = classID;
	}

	public Integer getSiteID() {
		return siteID;
	}

	public void setTesttermFacade(TesttermFacade testtermFacade) {
		this.testtermFacade = testtermFacade;
	}

	public List<Testterm> getTerms() {
		return terms;
	}

	public void setTerms(List<Testterm> terms) {
		this.terms = terms;
	}

	public void setStrategyFacade(StrategyFacade strategyFacade) {
		this.strategyFacade = strategyFacade;
	}

	public List<Strategy> getStrategys() {
		return strategys;
	}

	public void setStrategys(List<Strategy> strategys) {
		this.strategys = strategys;
	}

}
