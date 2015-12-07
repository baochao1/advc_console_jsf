package com.cdel.advc.major.action;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.major.domain.GuideLanguage;
import com.cdel.advc.major.domain.Major;
import com.cdel.advc.major.facade.GuideLanguageFacade;
import com.cdel.util.BaseAction;

/**
 * 服务项引导语设置action
 * 
 * @author xxg
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class GuideLangueAction extends BaseAction<Major> implements
		Serializable {

	@ManagedProperty(value = "#{guideLanguageFacade}")
	private GuideLanguageFacade guideLanguageFacade;

	private LazyDataModel<GuideLanguage> guideLanguagePage;
	private GuideLanguage searchGuideLanguage = new GuideLanguage();// 搜索的服务项引导语
	private Integer majorID; // 辅导ID

	@PostConstruct
	public void initGuideLanguageAction() {
		majorID = this.getIntegerParameter("majorID");
		searchGuideLanguage.setMajorID(majorID);
		guideLanguagePage = guideLanguageFacade.findPage(searchGuideLanguage);
		super.heighti = super.calHeight(14f / 20);
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
		guideLanguagePage = guideLanguageFacade.findPage(searchGuideLanguage);
		this.updateComponent("searchForm:guideLangueList");
	}

	public void setGuideLanguageFacade(GuideLanguageFacade guideLanguageFacade) {
		this.guideLanguageFacade = guideLanguageFacade;
	}

	public LazyDataModel<GuideLanguage> getGuideLanguagePage() {
		return guideLanguagePage;
	}

	public void setGuideLanguagePage(
			LazyDataModel<GuideLanguage> guideLanguagePage) {
		this.guideLanguagePage = guideLanguagePage;
	}

	public GuideLanguage getSearchGuideLanguage() {
		return searchGuideLanguage;
	}

	public void setSearchGuideLanguage(GuideLanguage searchGuideLanguage) {
		this.searchGuideLanguage = searchGuideLanguage;
	}

	public Integer getMajorID() {
		return majorID;
	}

}
