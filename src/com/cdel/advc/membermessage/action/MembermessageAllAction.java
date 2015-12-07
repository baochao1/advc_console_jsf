/*
 * @Title: MembermessageAction.java
 * @Package com.cdel.advc.membermessage.action
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-7-4 上午11:36:09
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-7-4                          
 */
package com.cdel.advc.membermessage.action;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.membermessage.domain.MembermessageAll;
import com.cdel.advc.membermessage.facade.MembermessageAllFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.StringUtil;

/**
 * <p>
 * （全部班级短信）bean
 * </p>
 * 
 * @author 张苏磊 Create at:2013-7-4 上午11:36:09
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MembermessageAllAction extends BaseAction<MembermessageAll>
		implements Serializable {

	@ManagedProperty("#{membermessageAllFacade}")
	private MembermessageAllFacade membermessageAllFacade;

	private Integer siteID;// 网站ID
	/** DataTable组件分页模型 */
	private LazyDataModel<MembermessageAll> page;
	/** 查询条件 */
	private MembermessageAll filterMsg = new MembermessageAll();
	private String from;// =class表示从班级管理连过来的

	@PostConstruct
	public void initMembermessageAction() {
		from = StringUtil.nullToString(this.getParameter("from"));
		siteID = this.getCurrentSiteID();
		if (from.equals("class")) {
			filterMsg.setClassID(this.getIntegerParameter("classID"));
			super.heighti2 = super.calHeight(15.5f / 20);
		} else {
			super.heighti2 = super.heighti;
		}
		this.page = this.membermessageAllFacade.findPage(filterMsg);
	}

	/**
	 * 条件查询，强制分页从位置索引0开始
	 */
	public void search() {
		this.pageTable.setFirst(0);
	}

	public LazyDataModel<MembermessageAll> getPage() {
		return page;
	}

	public void setPage(LazyDataModel<MembermessageAll> page) {
		this.page = page;
	}

	public MembermessageAll getFilterMsg() {
		return filterMsg;
	}

	public void setFilterMsg(MembermessageAll filterMsg) {
		this.filterMsg = filterMsg;
	}

	public void setMembermessageAllFacade(
			MembermessageAllFacade membermessageAllFacade) {
		this.membermessageAllFacade = membermessageAllFacade;
	}

	public String getFrom() {
		return from;
	}

	public Integer getSiteID() {
		return siteID;
	}

}
