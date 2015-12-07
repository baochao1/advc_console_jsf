package com.cdel.advc.website.action;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.website.domain.Website;
import com.cdel.advc.website.facade.WebsiteFacade;
import com.cdel.util.BaseAction;

@SuppressWarnings("serial")
@ManagedBean
public class WebsiteReqAction extends BaseAction<Website> implements
		Serializable {
	@ManagedProperty(value = "#{websiteFacade}")
	private WebsiteFacade websiteFacade;

	private Website updateWebsite = new Website();

	/**
	 * 打开修改页面
	 */
	public void update(Integer siteID) {
		updateWebsite = websiteFacade.findByID(siteID);
	}

	/**
	 * 提交添修改
	 * 
	 */
	public void updateSubmit() {
		byte submitSuccess = 0;// 修改是否成功
		try {
			WebsiteAction websiteAction = (WebsiteAction) this
					.getViewAction("websiteAction");
			websiteFacade.update(updateWebsite);
			websiteAction.search();
			submitSuccess = 1;
		} catch (Exception e) {
			e.printStackTrace();
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public WebsiteFacade getWebsiteFacade() {
		return websiteFacade;
	}

	public void setWebsiteFacade(WebsiteFacade websiteFacade) {
		this.websiteFacade = websiteFacade;
	}

	public Website getUpdateWebsite() {
		return updateWebsite;
	}

	public void setUpdateWebsite(Website updateWebsite) {
		this.updateWebsite = updateWebsite;
	}

}
