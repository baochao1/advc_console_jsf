package com.cdel.advc.technologyMsg.action;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.technologyMsg.domain.TechnologyMsg;
import com.cdel.advc.technologyMsg.facade.TechnologyMsgFacade;
import com.cdel.util.BaseAction;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class TechnologyMsgAction extends BaseAction<TechnologyMsg> implements
		Serializable {
	@ManagedProperty(value = "#{technologyMsgFacade}")
	private TechnologyMsgFacade technologyMsgFacade;

	private TechnologyMsg searchTechnologyMsg = new TechnologyMsg();
	private LazyDataModel<TechnologyMsg> technologyMsgPage;
	private Integer siteID;// 网站ID

	@PostConstruct
	public void initTechnologyMsgAction() {
		siteID = this.getCurrentSiteID();
		searchTechnologyMsg.setStatus(new Short("1"));
		searchTechnologyMsg.setReply(0);
		this.technologyMsgPage = this.technologyMsgFacade
				.findPage(searchTechnologyMsg);
	}

	/**
	 * 查询
	 * 
	 * @throws Exception
	 */
	public void search() {
		pageTable.setFirst(0);
		this.technologyMsgPage = this.technologyMsgFacade
				.findPage(searchTechnologyMsg);
		this.updateComponent("searchForm:membermsgList");
	}

	public void setTechnologyMsgFacade(TechnologyMsgFacade technologyMsgFacade) {
		this.technologyMsgFacade = technologyMsgFacade;
	}

	public TechnologyMsg getSearchTechnologyMsg() {
		return searchTechnologyMsg;
	}

	public void setSearchTechnologyMsg(TechnologyMsg searchTechnologyMsg) {
		this.searchTechnologyMsg = searchTechnologyMsg;
	}

	public LazyDataModel<TechnologyMsg> getTechnologyMsgPage() {
		return technologyMsgPage;
	}

	public void setTechnologyMsgPage(
			LazyDataModel<TechnologyMsg> technologyMsgPage) {
		this.technologyMsgPage = technologyMsgPage;
	}

	public Integer getSiteID() {
		return siteID;
	}

}