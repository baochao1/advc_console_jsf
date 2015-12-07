package com.cdel.advc.innermsg.action;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.innermsg.domain.InnerMsgAll;
import com.cdel.advc.innermsg.facade.InnerMsgAllFacade;
import com.cdel.advc.teacher.domain.Teacher;
import com.cdel.util.BaseAction;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class InnerMsgAllAction extends BaseAction<InnerMsgAll> implements
		Serializable {
	@ManagedProperty(value = "#{innerMsgAllFacade}")
	private InnerMsgAllFacade innerMsgAllFacade;

	private LazyDataModel<InnerMsgAll> innerMsgPage;
	private InnerMsgAll searchInnerMsg = new InnerMsgAll();
	private List<InnerMsgAll> innerMsgList;
	private Integer siteID;// 网站ID

	@PostConstruct
	public void initInnerMsgAction() {
		siteID = this.getCurrentSiteID();
		Teacher teacher = this.getCurrentTeacher();
		Integer orgID = teacher.getOrgID();
		Short isHeader = teacher.getIsHeader();
		if (isHeader == 0) {// 超管
			searchInnerMsg.setOrgID(null);
		} else if (isHeader == 1 || isHeader == 4) {// 经理
			searchInnerMsg.setOrgID(null);
		} else if (isHeader == 2) {// 组长
			searchInnerMsg.setOrgID(orgID);
		}
		this.innerMsgPage = this.innerMsgAllFacade.findPage(searchInnerMsg);
	}

	/**
	 * 查询
	 * 
	 * @throws Exception
	 */
	public void search() {
		pageTable.setFirst(0);
		this.innerMsgPage = this.innerMsgAllFacade.findPage(searchInnerMsg);
		this.updateComponent("searchForm:innerMsgList");
	}

	public LazyDataModel<InnerMsgAll> getInnerMsgPage() {
		return innerMsgPage;
	}

	public void setInnerMsgPage(LazyDataModel<InnerMsgAll> innerMsgPage) {
		this.innerMsgPage = innerMsgPage;
	}

	public InnerMsgAll getSearchInnerMsg() {
		return searchInnerMsg;
	}

	public void setSearchInnerMsg(InnerMsgAll searchInnerMsg) {
		this.searchInnerMsg = searchInnerMsg;
	}

	public List<InnerMsgAll> getInnerMsgList() {
		return innerMsgList;
	}

	public void setInnerMsgList(List<InnerMsgAll> innerMsgList) {
		this.innerMsgList = innerMsgList;
	}

	public void setInnerMsgAllFacade(InnerMsgAllFacade innerMsgAllFacade) {
		this.innerMsgAllFacade = innerMsgAllFacade;
	}

	public Integer getSiteID() {
		return siteID;
	}

}
