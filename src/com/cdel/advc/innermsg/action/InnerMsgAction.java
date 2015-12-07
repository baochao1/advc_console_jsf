package com.cdel.advc.innermsg.action;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.cdel.advc.innermsg.domain.InnerMsg;
import com.cdel.advc.innermsg.facade.InnerMsgFacade;
import com.cdel.util.BaseAction;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class InnerMsgAction extends BaseAction<InnerMsg> implements
		Serializable {
	@ManagedProperty(value = "#{innerMsgFacade}")
	private InnerMsgFacade innerMsgFacade;

	private LazyDataModel<InnerMsg> innerMsgPage;
	private InnerMsg searchInnerMsg = new InnerMsg();
	private List<InnerMsg> innerMsgList;
	private Integer siteID;// 网站ID
	private boolean showMark = true;// 是否显示标记按钮
	private boolean showBtn = true;// 是否可以提交回复

	@PostConstruct
	public void initInnerMsgAction() {
		Integer loginID = this.getCurrentUserID();
		siteID = this.getCurrentSiteID();
		if (loginID != null) {
			searchInnerMsg.setReceiveTeacherID(loginID);
			searchInnerMsg.setSender(loginID);
		}
		Integer flag = this.getIntegerParameter("flag");
		// 我的沟通消息页面，可以从班级管理中的沟通消息进入。从班级管理中进入我的沟通消息，需要flag区分收信箱：1、 发信箱：2
		if (flag != null && (flag == 1 || flag == 2)) {
			super.heighti2 = super.calHeight(14.6f / 20);
		} else {
			super.heighti2 = super.calHeight(12f / 20);
		}
		if (flag == null) {
			flag = 1;
		}
		if (flag == 1) {
			searchInnerMsg.setMailType((short) 1);
		} else if (flag == 2) {
			showBtn = false;
			searchInnerMsg.setMailType((short) 2);
		}
		searchInnerMsg.setReadStatus((short) 0);
		this.innerMsgPage = this.innerMsgFacade.findPage(searchInnerMsg);
	}

	/**
	 * 查询
	 * 
	 * @throws Exception
	 */
	public void search() {
		if (searchInnerMsg.getMailType() == null
				|| searchInnerMsg.getMailType() == 2) {
			showMark = false;
			showBtn = false;
		} else {
			showMark = true;
			showBtn = true;
		}
		pageTable.setFirst(0);
		search4U();
	}

	/**
	 * 查询
	 * 
	 * @throws Exception
	 */
	public void search4U() {
		this.innerMsgPage = this.innerMsgFacade.findPage(searchInnerMsg);
		this.updateComponent("searchForm:innerMsgList");
		this.updateComponent("searchForm:showMarkBtn");
	}

	public void setInnerMsgFacade(InnerMsgFacade innerMsgFacade) {
		this.innerMsgFacade = innerMsgFacade;
	}

	public InnerMsg getSearchInnerMsg() {
		return searchInnerMsg;
	}

	public void setSearchInnerMsg(InnerMsg searchInnerMsg) {
		this.searchInnerMsg = searchInnerMsg;
	}

	public LazyDataModel<InnerMsg> getInnerMsgPage() {
		return innerMsgPage;
	}

	public void setInnerMsgPage(LazyDataModel<InnerMsg> innerMsgPage) {
		this.innerMsgPage = innerMsgPage;
	}

	public List<InnerMsg> getInnerMsgList() {
		return innerMsgList;
	}

	public void setInnerMsgList(List<InnerMsg> innerMsgList) {
		this.innerMsgList = innerMsgList;
	}

	public boolean isShowMark() {
		return showMark;
	}

	public void setShowMark(boolean showMark) {
		this.showMark = showMark;
	}

	public Integer getSiteID() {
		return siteID;
	}

	public boolean isShowBtn() {
		return showBtn;
	}

}
