package com.cdel.advc.membermsg.action;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.membermsg.domain.Membermsg;
import com.cdel.advc.membermsg.domain.MembermsgAll;
import com.cdel.advc.membermsg.facade.MembermsgFacade;
import com.cdel.util.BaseAction;

/**
 * <p>
 * 全部学员消息 查询 bean
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-12 下午3:23:06
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MembermsgAllAction extends BaseAction<MembermsgAll> implements
		Serializable {

	@ManagedProperty(value = "#{membermsgFacade}")
	private MembermsgFacade membermsgFacade;

	private Membermsg searchMembermsg = new Membermsg();
	private LazyDataModel<Membermsg> membermsgPage;
	private Integer siteID;// 网站ID

	@PostConstruct
	public void initMembermsgAllAction() {
		siteID = this.getCurrentSiteID();
		searchMembermsg.setStatus(new Short("1"));
		searchMembermsg.setReply(0);
		this.membermsgPage = this.membermsgFacade.findPage(searchMembermsg);
	}

	/**
	 * 查询
	 * 
	 * @throws Exception
	 */
	public void search() {
		pageTable.setFirst(0);
		this.membermsgPage = this.membermsgFacade.findPage(searchMembermsg);
		this.updateComponent("searchForm:membermsgList");
	}

	public void setMembermsgFacade(MembermsgFacade membermsgFacade) {
		this.membermsgFacade = membermsgFacade;
	}

	public Membermsg getSearchMembermsg() {
		return searchMembermsg;
	}

	public void setSearchMembermsg(Membermsg searchMembermsg) {
		this.searchMembermsg = searchMembermsg;
	}

	public LazyDataModel<Membermsg> getMembermsgPage() {
		return membermsgPage;
	}

	public void setMembermsgPage(LazyDataModel<Membermsg> membermsgPage) {
		this.membermsgPage = membermsgPage;
	}

	public Integer getSiteID() {
		return siteID;
	}

}
