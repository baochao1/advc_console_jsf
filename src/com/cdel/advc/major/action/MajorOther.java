package com.cdel.advc.major.action;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.cdel.advc.major.domain.Major;
import com.cdel.advc.major.facade.MajorFacade;

/**
 * 辅导action
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MajorOther implements Serializable {
	@ManagedProperty(value = "#{majorFacade}")
	private MajorFacade majorFacade;

	private List<Major> majorList;// 辅导List

	public List<Major> getMajorList() {
		return majorList;
	}

	public void setMajorList(Integer siteID) {
		Major major = new Major();
		major.setSiteID(siteID);
		major.setStatus(Short.valueOf("1"));
		this.majorList = majorFacade.findList(major);
	}

	public void setMajorSendList(Integer siteID) {
		Major major = new Major();
		major.setSiteID(siteID);
		major.setStatus(Short.valueOf("1"));
		major.setIsNewService(Short.valueOf("1"));
		this.majorList = majorFacade.findList(major);
	}

	public void setMajorFacade(MajorFacade majorFacade) {
		this.majorFacade = majorFacade;
	}

}
