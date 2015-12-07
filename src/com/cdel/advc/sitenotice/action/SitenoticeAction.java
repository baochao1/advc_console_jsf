package com.cdel.advc.sitenotice.action;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.sitenotice.domain.Sitenotice;
import com.cdel.advc.sitenotice.facade.SitenoticeFacade;
import com.cdel.advc.website.domain.Website;
import com.cdel.advc.website.facade.WebsiteFacade;
import com.cdel.util.BaseAction;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class SitenoticeAction extends BaseAction<Sitenotice> implements
		Serializable {
	@ManagedProperty(value = "#{sitenoticeFacade}")
	private SitenoticeFacade sitenoticeFacade;
	@ManagedProperty(value = "#{websiteFacade}")
	private WebsiteFacade websiteFacade;

	private LazyDataModel<Sitenotice> sitenoticePage;
	private Sitenotice searchSitenotice = new Sitenotice();// 搜索的公告
	private String url;
	private Integer siteID;// 网站ID
	private List<Website> websiteList;// 网站List

	@PostConstruct
	public void initSitenoticeAction() {
		url = "../grant/null.xhtml";
		siteID = this.getCurrentSiteID();
		websiteList = websiteFacade.findList(1);
		searchSitenotice.setSiteID(siteID);
		sitenoticePage = sitenoticeFacade.findPage(searchSitenotice);
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
		sitenoticePage = sitenoticeFacade.findPage(searchSitenotice);
		this.updateComponent("searchForm:sitenoticeList");
	}

	public LazyDataModel<Sitenotice> getSitenoticePage() {
		return sitenoticePage;
	}

	public void setSitenoticePage(LazyDataModel<Sitenotice> sitenoticePage) {
		this.sitenoticePage = sitenoticePage;
	}

	public Sitenotice getSearchSitenotice() {
		return searchSitenotice;
	}

	public void setSearchSitenotice(Sitenotice searchSitenotice) {
		this.searchSitenotice = searchSitenotice;
	}

	public void setSitenoticeFacade(SitenoticeFacade sitenoticeFacade) {
		this.sitenoticeFacade = sitenoticeFacade;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Website> getWebsiteList() {
		return websiteList;
	}

	public void setWebsiteList(List<Website> websiteList) {
		this.websiteList = websiteList;
	}

	public void setWebsiteFacade(WebsiteFacade websiteFacade) {
		this.websiteFacade = websiteFacade;
	}

}