/*
 * @Title: FaqAction.java
 * @Package com.cdel.advc.faq.action
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-7-3 下午7:55:53
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-7-3                          
 */
package com.cdel.advc.faq.action;

import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.faq.domain.Faq;
import com.cdel.advc.faq.facade.FaqFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;
import com.cdel.util.StringUtil;

/**
 * <p>
 * (全部互助答疑)互助答疑 bean
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-3 下午7:55:53
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FaqAction extends BaseAction<Faq> implements Serializable {

	@ManagedProperty("#{faqFacade}")
	private FaqFacade faqFacade;

	private LazyDataModel<Faq> page;
	private Faq filterFaq = new Faq();
	private Integer siteID;// 网站ID
	private String from;// =class表示从班级管理连过来的

	@PostConstruct
	public void initFaqAction() {
		from = StringUtil.nullToString(this.getParameter("from"));
		siteID = this.getCurrentSiteID();
		if (from.equals("class")) {
			filterFaq.setClassID(this.getIntegerParameter("classID"));
			super.heighti2 = super.calHeight(15.5f / 20);
		}else{
			super.heighti2 = super.calHeight(11.5f / 20);
		}
		this.page = this.faqFacade.findPage(this.filterFaq);
	}

	/**
	 * 条件查询，强制从索引0位置开始
	 */
	public void search() {
		this.pageTable.setFirst(0);
	}

	/**
	 * 导出数据之前的判断
	 */
	public void exportXls() {
		byte submitSuccess = 0;// 添加是否成功
		int count = faqFacade.getCount(filterFaq);
		if (count > 3000) {
			this.addWarnMessage("info", "记录不能超过3000！");
		} else {
			search();
			submitSuccess = 1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 状态常量列表
	 */
	public Map<Short, String> getStatuss() {
		return Contants.status;
	}

	public Faq getFilterFaq() {
		return filterFaq;
	}

	public void setFilterFaq(Faq filterFaq) {
		this.filterFaq = filterFaq;
	}

	public LazyDataModel<Faq> getPage() {
		return page;
	}

	public void setPage(LazyDataModel<Faq> page) {
		this.page = page;
	}

	public void setFaqFacade(FaqFacade faqFacade) {
		this.faqFacade = faqFacade;
	}

	public String getFrom() {
		return from;
	}

	public Integer getSiteID() {
		return siteID;
	}

}
