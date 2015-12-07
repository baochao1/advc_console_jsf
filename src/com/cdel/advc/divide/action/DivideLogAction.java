package com.cdel.advc.divide.action;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.divide.domain.DivideLog;
import com.cdel.advc.divide.facade.DivideLogFacade;
import com.cdel.util.BaseAction;

/**
 * 学员分班日志
 * 
 * @author zhangsulei
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class DivideLogAction extends BaseAction<DivideLog> implements
		Serializable {
	@ManagedProperty(value = "#{divideLogFacade}")
	private DivideLogFacade divideLogFacade;

	private LazyDataModel<DivideLog> divideLogPage;
	private DivideLog searchDivideLog = new DivideLog();
	private boolean show = false;

	@PostConstruct
	public void initMajorAction() {
		divideLogPage = divideLogFacade.findPage(searchDivideLog);
	}

	/**
	 * 查询
	 */
	public void search() {
		pageTable.setFirst(0);
		divideLogPage = divideLogFacade.findPage(searchDivideLog);
		this.updateComponent("searchForm:majorList");
	}

	/**
	 * 显示日志页面
	 */
	public void showLog() {
		show = true;
	}

	public void setDivideLogFacade(DivideLogFacade divideLogFacade) {
		this.divideLogFacade = divideLogFacade;
	}

	public LazyDataModel<DivideLog> getDivideLogPage() {
		return divideLogPage;
	}

	public void setDivideLogPage(LazyDataModel<DivideLog> divideLogPage) {
		this.divideLogPage = divideLogPage;
	}

	public DivideLog getSearchDivideLog() {
		return searchDivideLog;
	}

	public void setSearchDivideLog(DivideLog searchDivideLog) {
		this.searchDivideLog = searchDivideLog;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

}
