package com.cdel.qz.score.selfhelp.action;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.qz.score.selfhelp.domain.SelfHelp;
import com.cdel.qz.score.selfhelp.facade.SelfHelpFacade;
import com.cdel.util.BaseAction;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class SelfHelpListAction extends BaseAction<SelfHelp> implements
		Serializable {
	@ManagedProperty("#{selfHelpFacade}")
	private SelfHelpFacade selfHelpFacade;

	/** DataTable组件分页模型 */
	private LazyDataModel<SelfHelp> page;
	private SelfHelp filterSelfHelp = new SelfHelp();
	private Integer userID;

	/**
	 * 打开高端班关联知识点页面
	 * 
	 * @param courseID
	 * @param userID
	 * @param studyPlanID
	 */
	public void showList(Integer userID) {
		this.userID = userID;
		filterSelfHelp.setCreator(userID);
		super.heighti2 = super.calHeight(10f / 20);
		page = selfHelpFacade.findPage(filterSelfHelp);
	}

	public Integer getUserID() {
		return userID;
	}

	public LazyDataModel<SelfHelp> getPage() {
		return page;
	}

	public void setPage(LazyDataModel<SelfHelp> page) {
		this.page = page;
	}

	public SelfHelp getFilterSelfHelp() {
		return filterSelfHelp;
	}

	public void setFilterSelfHelp(SelfHelp filterSelfHelp) {
		this.filterSelfHelp = filterSelfHelp;
	}

	public void setSelfHelpFacade(SelfHelpFacade selfHelpFacade) {
		this.selfHelpFacade = selfHelpFacade;
	}

}
