package com.cdel.advc.website.action;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.website.domain.Website;
import com.cdel.advc.website.facade.WebsiteFacade;
import com.cdel.util.BaseAction;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class WebsiteAction extends BaseAction<Website> implements Serializable {
	@ManagedProperty(value = "#{websiteFacade}")
	private WebsiteFacade websiteFacade;

	private LazyDataModel<Website> websitePage;
	private Website searchWebsite = new Website();

	@PostConstruct
	public void initSitenoticeAction() {
		websitePage = websiteFacade.findPage(searchWebsite);
	}

	/**
	 * 查询
	 */
	public void search() {
		pageTable.setFirst(0);
		websitePage = websiteFacade.findPage(searchWebsite);
		this.updateComponent("searchForm:websiteList");
	}

	public void setWebsiteFacade(WebsiteFacade websiteFacade) {
		this.websiteFacade = websiteFacade;
	}

	public LazyDataModel<Website> getWebsitePage() {
		return websitePage;
	}

	public void setWebsitePage(LazyDataModel<Website> websitePage) {
		this.websitePage = websitePage;
	}

	public Website getSearchWebsite() {
		return searchWebsite;
	}

	public void setSearchWebsite(Website searchWebsite) {
		this.searchWebsite = searchWebsite;
	}

}
