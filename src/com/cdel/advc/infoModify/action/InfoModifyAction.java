package com.cdel.advc.infoModify.action;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang.StringUtils;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.infoModify.domain.InfoModify;
import com.cdel.advc.infoModify.facade.InfoModifyFacade;
import com.cdel.util.BaseAction;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class InfoModifyAction extends BaseAction<InfoModify> implements
		Serializable {
	@ManagedProperty(value = "#{infoModifyFacade}")
	private InfoModifyFacade infoModifyFacade;

	private InfoModify searchInfoModify = new InfoModify();// 搜索条件
	private LazyDataModel<InfoModify> infoModifyPage;

	/**
	 * 查询
	 * 
	 * @throws Exception
	 */
	public void search() {
		if (StringUtils.isBlank(searchInfoModify.getMsgTitle())
				&& StringUtils.isBlank(searchInfoModify.getMsgContent())) {
			this.addWarnMessage("info", "请至少输入一个条件！");
			return;
		}
		pageTable.setFirst(0);
		this.infoModifyPage = this.infoModifyFacade.findPage(searchInfoModify);
		this.updateComponent("searchForm:infoModifyList");
	}

	public void setInfoModifyFacade(InfoModifyFacade infoModifyFacade) {
		this.infoModifyFacade = infoModifyFacade;
	}

	public InfoModify getSearchInfoModify() {
		return searchInfoModify;
	}

	public void setSearchInfoModify(InfoModify searchInfoModify) {
		this.searchInfoModify = searchInfoModify;
	}

	public LazyDataModel<InfoModify> getInfoModifyPage() {
		return infoModifyPage;
	}

	public void setInfoModifyPage(LazyDataModel<InfoModify> infoModifyPage) {
		this.infoModifyPage = infoModifyPage;
	}

}
