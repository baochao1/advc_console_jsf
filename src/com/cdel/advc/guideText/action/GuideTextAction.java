package com.cdel.advc.guideText.action;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.guideText.domain.GuideText;
import com.cdel.advc.guideText.facade.GuideTextFacade;
import com.cdel.advc.major.domain.Major;
import com.cdel.util.BaseAction;

/**
 * 阶段引导语action
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class GuideTextAction extends BaseAction<Major> implements Serializable {

	@ManagedProperty(value = "#{guideTextFacade}")
	private GuideTextFacade guideTextFacade;

	private LazyDataModel<GuideText> page;
	private GuideText searchGuideText = new GuideText();
	private Integer majorID;

	@PostConstruct
	public void initMajorAction() {
		majorID = this.getIntegerParameter("majorID");
		searchGuideText.setMajorID(majorID);
		searchGuideText.setStatus((short) 1);
		page = guideTextFacade.findPage(searchGuideText);
		super.heighti2 = super.calHeight(14f / 20);
	}

	/**
	 * 查询
	 */
	public void search() {
		pageTable.setFirst(0);
		search4U();
	}

	/**
	 * 查询
	 */
	public void search4U() {
		page = guideTextFacade.findPage(searchGuideText);
		this.updateComponent("searchForm:guideTextList");
	}

	public LazyDataModel<GuideText> getPage() {
		return page;
	}

	public void setPage(LazyDataModel<GuideText> page) {
		this.page = page;
	}

	public GuideText getSearchGuideText() {
		return searchGuideText;
	}

	public void setSearchGuideText(GuideText searchGuideText) {
		this.searchGuideText = searchGuideText;
	}

	public void setGuideTextFacade(GuideTextFacade guideTextFacade) {
		this.guideTextFacade = guideTextFacade;
	}

	public Integer getMajorID() {
		return majorID;
	}

}
