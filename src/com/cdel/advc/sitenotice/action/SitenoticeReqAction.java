package com.cdel.advc.sitenotice.action;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.sitenotice.domain.Sitenotice;
import com.cdel.advc.sitenotice.facade.SitenoticeFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;
import com.cdel.util.ExceptionUtil;

@SuppressWarnings("serial")
@ManagedBean
public class SitenoticeReqAction extends BaseAction<Sitenotice> implements
		Serializable {
	@ManagedProperty(value = "#{sitenoticeFacade}")
	private SitenoticeFacade sitenoticeFacade;

	private Sitenotice updateSitenotice = new Sitenotice();
	private Short flag = -1;

	/**
	 * 修改状态
	 */
	public void changeStatus(Integer siteNoticeID, Integer status) {
		Sitenotice s = new Sitenotice();
		s.setSiteNoticeID(siteNoticeID);
		Short newStatus;
		if (status == 1) {
			newStatus = 0;
		} else {
			newStatus = 1;
		}
		s.setStatus(newStatus);
		try {
			sitenoticeFacade.update(s);
			this.addMessage("info", SUCESSINFO);
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
		}
	}

	/**
	 * 打开添加页面
	 */
	public void add() {
		flag = 0;
	}

	/**
	 * 提交添加/修改
	 * 
	 * @param flag
	 */
	public void addSubmit() {
		byte submitSuccess = 0;// 添加是否成功
		if (sitenoticeFacade.checkSitenotice(flag, updateSitenotice)) {
			if (flag == 0) {
				updateSitenotice.setStatus(Short.valueOf("1"));
				updateSitenotice.setCreator(this.getCurrentUserID());
				updateSitenotice.setCreateTime(new Date());
			}
			try {
				SitenoticeAction sitenoticeAction = (SitenoticeAction) this
						.getViewAction("sitenoticeAction");
				if (flag == 0) {
					sitenoticeFacade.add(updateSitenotice);
					sitenoticeAction.search();
				} else {
					sitenoticeFacade.update(updateSitenotice);
					sitenoticeAction.search4U();
				}
				submitSuccess = 1;
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("updateSitenotice=" + updateSitenotice);
				logger.error(ExceptionUtil.getEMsg(e));
				submitSuccess = -1;
			}
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 打开修改页面
	 */
	public void update(Integer siteNoticeID) {
		flag = 1;
		updateSitenotice = sitenoticeFacade.findByID(siteNoticeID);
	}

	public Map<Short, String> getType() {
		return Contants.sitenoticeType;
	}

	public Map<Short, String> getStatus() {
		return Contants.status;
	}

	public void setSitenoticeFacade(SitenoticeFacade sitenoticeFacade) {
		this.sitenoticeFacade = sitenoticeFacade;
	}

	public Sitenotice getUpdateSitenotice() {
		return updateSitenotice;
	}

	public void setUpdateSitenotice(Sitenotice updateSitenotice) {
		this.updateSitenotice = updateSitenotice;
	}

	public Short getFlag() {
		return flag;
	}

	public void setFlag(Short flag) {
		this.flag = flag;
	}

}