package com.cdel.advc.major.action;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.cdel.advc.major.domain.MainMajor;
import com.cdel.advc.major.domain.Major;
import com.cdel.advc.major.facade.MainMajorFacade;
import com.cdel.util.BaseAction;

/**
 * 
 * <p>主库辅导Action</p>
 * 
 * @author Du Haiying
 * Create at:2014-3-12 下午3:14:24
 */
@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class MainMajorAction extends BaseAction<Major> implements Serializable {

	@ManagedProperty(value = "#{mainMajorFacade}")
	private MainMajorFacade mainMajorFacade;

	private Integer siteID;// 网站ID
	
	private List<MainMajor> mainMajors;

	@PostConstruct
	public void initMajorAction() {
		siteID = this.getCurrentSiteID();
		this.mainMajors = this.mainMajorFacade.findMajorsBySiteID(siteID);
	}

	public List<MainMajor> getMainMajors() {
		return mainMajors;
	}

	public void setMainMajorFacade(MainMajorFacade mainMajorFacade) {
		this.mainMajorFacade = mainMajorFacade;
	}
}
