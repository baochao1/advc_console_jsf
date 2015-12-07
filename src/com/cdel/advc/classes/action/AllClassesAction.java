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

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.classes.domain.AllClasses;
import com.cdel.advc.classes.facade.AllClassesFacade;
import com.cdel.util.BaseAction;

/**
 * 
 * <p>
 * 总班级管理查询相关bean
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-19 下午2:32:57
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class AllClassesAction extends BaseAction<AllClasses> {

	@ManagedProperty("#{allClassesFacade}")
	private AllClassesFacade allClassesFacade;

	/** DataTable组件分页模型 */
	private LazyDataModel<AllClasses> page;
	private AllClasses filterClasses = new AllClasses();
	private Integer siteID;

	@PostConstruct
	public void initAllClassesAction() {
		siteID = this.getCurrentSiteID();
		filterClasses.setSiteID(this.getCurrentSiteID());
		filterClasses.setTeacherID(this.getIntegerParameter("teacherID")); // 教师助教的班
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
		this.updateComponent("searchForm:allClassesList");
	}

	public void setAllClassesFacade(AllClassesFacade allClassesFacade) {
		this.allClassesFacade = allClassesFacade;
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

	public Integer getSiteID() {
		return siteID; 
	}

}
