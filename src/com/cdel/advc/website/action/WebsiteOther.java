package com.cdel.advc.website.action;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpServletRequest;

import com.cdel.advc.website.domain.Website;
import com.cdel.advc.website.facade.WebsiteFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;
import com.cdel.util.CookieUtil;

/**
 * 网站
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class WebsiteOther  extends BaseAction<Object>  implements Serializable {

	@ManagedProperty(value = "#{websiteFacade}")
	private WebsiteFacade websiteFacade;

	private List<Website> websiteList;
	// 选中的网站ID
	private Integer selectSiteID;

	public List<Website> getWebsiteList() {  
		websiteList = websiteFacade.findList(1);
		return websiteList;
	}

	/**
	 * 获取全部网站
	 */
	public void setWebsiteList() {
		websiteList = websiteFacade.findList(1);
	}

	public void setWebsiteFacade(WebsiteFacade websiteFacade) {
		this.websiteFacade = websiteFacade;
	}

	public Integer getSelectSiteID() {
		HttpServletRequest request =  this.getRequest();
		if(selectSiteID == null){
			selectSiteID = CookieUtil.getCookieInt(request, Contants.COOKIENAME_WEBSITE);
		}
		return selectSiteID;
	}

	public void setSelectSiteID(Integer selectSiteID) {
		this.selectSiteID = selectSiteID;
	}

}
