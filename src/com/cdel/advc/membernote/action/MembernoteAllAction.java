/*
 * @Title: MembernoteAction.java
 * @Package com.cdel.advc.membernote.action
 * @Description: TODO
 * @author 张苏磊
 * @date 2013-7-4 下午4:01:13
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-7-4                          
 */
package com.cdel.advc.membernote.action;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.membernote.domain.MembernoteAll;
import com.cdel.advc.membernote.facade.MembernoteAllFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.StringUtil;

/**
 * <p>
 * 全部班级留言管理 bean
 * </p>
 * 
 * @author 张苏磊
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MembernoteAllAction extends BaseAction<MembernoteAll> implements
		Serializable {

	@ManagedProperty("#{membernoteAllFacade}")
	private MembernoteAllFacade membernoteAllFacade;

	private Integer siteID;// 网站ID
	private LazyDataModel<MembernoteAll> page;
	private MembernoteAll filterNote = new MembernoteAll();
	private String from;// =class表示从班级管理连过来的

	@PostConstruct
	public void initMembernoteAction() {
		from = StringUtil.nullToString(this.getParameter("from"));
		siteID = this.getCurrentSiteID();
		if (from.equals("class")) {
			filterNote.setClassID(this.getIntegerParameter("classID"));
			super.heighti2 = super.calHeight(15.5f / 20);
		} else {
			super.heighti2 = super.calHeight(11.5f / 20);
		}
		this.page = this.membernoteAllFacade.findPage(filterNote);
	}

	/**
	 * 条件查询，强制分页从位置索引0开始
	 */
	public void search() {
		this.pageTable.setFirst(0);
		this.page = this.membernoteAllFacade.findPage(filterNote);
		this.updateComponent("searchForm:membernoteTable");
	}

	/**
	 * 导出数据之前的判断
	 */
	public void exportXls() {
		byte submitSuccess = 0;// 添加是否成功
		int count = membernoteAllFacade.getCount(filterNote);
		if (count > 3000) {
			this.addWarnMessage("info", "记录不能超过3000！");
		} else {
			search();
			submitSuccess = 1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public LazyDataModel<MembernoteAll> getPage() {
		return page;
	}

	public void setPage(LazyDataModel<MembernoteAll> page) {
		this.page = page;
	}

	public MembernoteAll getFilterNote() {
		return filterNote;
	}

	public void setFilterNote(MembernoteAll filterNote) {
		this.filterNote = filterNote;
	}

	public void setMembernoteAllFacade(MembernoteAllFacade membernoteAllFacade) {
		this.membernoteAllFacade = membernoteAllFacade;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public Integer getSiteID() {
		return siteID;
	}

}
