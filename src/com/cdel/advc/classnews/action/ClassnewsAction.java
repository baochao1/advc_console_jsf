/*
 * @Title: ClassnewsAction.java
 * @Package com.cdel.advc.classnews.action
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-7-4 下午5:49:35
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-7-4                          
 */
package com.cdel.advc.classnews.action;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.classnews.domain.Classnews;
import com.cdel.advc.classnews.facade.ClassnewsFacade;
import com.cdel.util.BaseAction;

/**
 * <p>
 * 班级动态 bean
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-4 下午5:49:35
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClassnewsAction extends BaseAction<Classnews> implements
		Serializable {

	@ManagedProperty("#{classnewsFacade}")
	private ClassnewsFacade classnewsFacade;

	/** DataTable组件分页模型 */
	private LazyDataModel<Classnews> page;
	/** 查询条件 */
	private Classnews filterNews = new Classnews();
	private Integer classID;

	@PostConstruct
	public void initClassnewsAction() {
		classID = this.getIntegerParameter("classID");
		if (classID != null) {
			this.filterNews.setClassID(classID);
			this.page = this.classnewsFacade.findPage(filterNews);
		}
		super.heighti2 = super.calHeight(14.5f / 20);
	}

	/**
	 * 条件查询，强制分页从位置索引0开始
	 */
	public void search() {
		this.pageTable.setFirst(0);
		search4U();
	}

	public void search4U() {
		this.updateComponent("searchForm:classnewsList");
	}

	public LazyDataModel<Classnews> getPage() {
		return page;
	}

	public void setPage(LazyDataModel<Classnews> page) {
		this.page = page;
	}

	public Classnews getFilterNews() {
		return filterNews;
	}

	public void setFilterNews(Classnews filterNews) {
		this.filterNews = filterNews;
	}

	public void setClassnewsFacade(ClassnewsFacade classnewsFacade) {
		this.classnewsFacade = classnewsFacade;
	}

	public Integer getClassID() {
		return classID;
	}

}
