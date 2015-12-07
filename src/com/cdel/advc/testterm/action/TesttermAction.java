package com.cdel.advc.testterm.action;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.testterm.domain.Testterm;
import com.cdel.advc.testterm.facade.TesttermFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.DateUtil;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class TesttermAction extends BaseAction<Testterm> implements
		Serializable {

	@ManagedProperty(value = "#{testtermFacade}")
	private TesttermFacade testtermFacade;

	private Testterm searchTestterm = new Testterm();
	private Integer siteID;// 网站ID
	private LazyDataModel<Testterm> testtermPage;

	@PostConstruct
	public void initMemberCallAction() {
		siteID = this.getCurrentSiteID();
		searchTestterm.setSiteID(siteID);
		testtermPage = testtermFacade.findPage(searchTestterm);
		super.heighti2 = super.calHeight(11.5f / 20);
	}

	/**
	 * 查询
	 * 
	 * @throws Exception
	 */
	public void search() {
		pageTable.setFirst(0);
		search4U();
	}

	public void search4U() {
		Date date = searchTestterm.getTermDate();
		if (date != null) {
			searchTestterm.setTermYear(DateUtil.getYear(date));
			searchTestterm.setTermMonth(DateUtil.getMonth(date));
		}
		testtermPage = testtermFacade.findPage(searchTestterm);
		this.updateComponent("searchForm:testtermList");
	}

	public Testterm getSearchTestterm() {
		return searchTestterm;
	}

	public void setSearchTestterm(Testterm searchTestterm) {
		this.searchTestterm = searchTestterm;
	}

	public LazyDataModel<Testterm> getTesttermPage() {
		return testtermPage;
	}

	public void setTesttermPage(LazyDataModel<Testterm> testtermPage) {
		this.testtermPage = testtermPage;
	}

	public void setTesttermFacade(TesttermFacade testtermFacade) {
		this.testtermFacade = testtermFacade;
	}

	public Integer getSiteID() {
		return siteID;
	}

}
