package com.cdel.advc.classmsgnotice.action;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.classmsgnotice.domain.ClassMsgNotice;
import com.cdel.advc.classmsgnotice.facade.ClassMsgNoticeFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.StringUtil;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClassMsgNoticeAction extends BaseAction<ClassMsgNotice> implements
		Serializable {

	@ManagedProperty(value = "#{classMsgNoticeFacade}")
	private ClassMsgNoticeFacade classMsgNoticeFacade;

	private LazyDataModel<ClassMsgNotice> classMsgNoticePage;
	private Integer siteID;// 网站ID
	private Integer classID;
	private ClassMsgNotice searchClassMsgNotice = new ClassMsgNotice();
	private String from;// =class表示从班级管理连过来的

	@PostConstruct
	public void initTechnologyMsgAction() {
		from = StringUtil.nullToString(this.getParameter("from"));
		siteID = this.getCurrentSiteID();
		searchClassMsgNotice.setMsgType((short) 1);
		if (from.equals("class")) {
			classID = this.getIntegerParameter("classID");
			searchClassMsgNotice.setClassID(classID);
		}
		this.classMsgNoticePage = this.classMsgNoticeFacade
				.findPage(searchClassMsgNotice);
		super.heighti2 = super.calHeight(11.5f / 20);
	}

	/**
	 * 查询
	 * 
	 * @throws Exception
	 */
	public void search() {
		pageTable.setFirst(0);
		search4Update();
	}

	public void search4Update() {
		this.classMsgNoticePage = this.classMsgNoticeFacade
				.findPage(searchClassMsgNotice);
		this.updateComponent("searchForm:classmsgNoticeList");
	}

	public void setClassMsgNoticeFacade(
			ClassMsgNoticeFacade classMsgNoticeFacade) {
		this.classMsgNoticeFacade = classMsgNoticeFacade;
	}

	public LazyDataModel<ClassMsgNotice> getClassMsgNoticePage() {
		return classMsgNoticePage;
	}

	public void setClassMsgNoticePage(
			LazyDataModel<ClassMsgNotice> classMsgNoticePage) {
		this.classMsgNoticePage = classMsgNoticePage;
	}

	public ClassMsgNotice getSearchClassMsgNotice() {
		return searchClassMsgNotice;
	}

	public void setSearchClassMsgNotice(ClassMsgNotice searchClassMsgNotice) {
		this.searchClassMsgNotice = searchClassMsgNotice;
	}

	public String getFrom() {
		return from;
	}

	public Integer getSiteID() {
		return siteID;
	}

	public Integer getClassID() {
		return classID;
	}

}
