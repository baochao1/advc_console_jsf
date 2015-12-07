package com.cdel.advc.major.action;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.major.domain.Major;
import com.cdel.advc.major.domain.SpecialGeneral;
import com.cdel.advc.major.facade.SpecialGeneralFacade;
import com.cdel.util.BaseAction;

/**
 * 特殊时间设置action
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class SpecialGeneralAction extends BaseAction<Major> implements
		Serializable {
	@ManagedProperty(value = "#{specialGeneralFacade}")
	private SpecialGeneralFacade specialGeneralFacade;

	private LazyDataModel<SpecialGeneral> specialGeneralPage;
	private SpecialGeneral searchSpecialGeneral = new SpecialGeneral();
	private Integer majorID;

	@PostConstruct
	public void initSpecialGeneralAction() {
		majorID = this.getIntegerParameter("majorID");
		searchSpecialGeneral.setMajorID(majorID);
		specialGeneralPage = specialGeneralFacade
				.findPage(searchSpecialGeneral);
		super.heighti2 = super.calHeight(15f / 20);
	}

	/**
	 * 查询
	 */
	public void search() {
		pageTable.setFirst(0);
		specialGeneralPage = specialGeneralFacade
				.findPage(searchSpecialGeneral);
		this.updateComponent("searchForm:specialGeneralList");
	}

	public void setSpecialGeneralFacade(
			SpecialGeneralFacade specialGeneralFacade) {
		this.specialGeneralFacade = specialGeneralFacade;
	}

	public LazyDataModel<SpecialGeneral> getSpecialGeneralPage() {
		return specialGeneralPage;
	}

	public void setSpecialGeneralPage(
			LazyDataModel<SpecialGeneral> specialGeneralPage) {
		this.specialGeneralPage = specialGeneralPage;
	}

	public SpecialGeneral getSearchSpecialGeneral() {
		return searchSpecialGeneral;
	}

	public void setSearchSpecialGeneral(SpecialGeneral searchSpecialGeneral) {
		this.searchSpecialGeneral = searchSpecialGeneral;
	}

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}

}
