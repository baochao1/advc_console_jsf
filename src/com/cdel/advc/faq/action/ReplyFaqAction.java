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
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.faq.domain.Faq;
import com.cdel.advc.faq.facade.FaqFacade;
import com.cdel.util.BaseAction;

/**
 * <p>
 * 互助答疑 回复 bean
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-3 下午7:55:53
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ReplyFaqAction extends BaseAction<Faq> implements Serializable {

	@ManagedProperty("#{faqFacade}")
	private FaqFacade faqFacade;

	/** DataTable组件分页模型 -- 回复帖子 */
	private LazyDataModel<Faq> page;
	/** 当前答疑 */
	private Faq faq;
	/** 最佳回复 */
	private Faq bestFaq;
	private Integer faqID;

	@PostConstruct
	public void initFaqAction() {
		faqID = this.getIntegerParameter("faqID");
		if (faqID != null) {
			this.faq = this.faqFacade.findByID(faqID);// 当前贴子
			this.bestFaq = this.faqFacade.findBestFaq(faqID);// 最佳答案
			this.page = this.faqFacade.findReplyFaqs(faqID);// 回复帖子
		}
	}

	/**
	 * 修改状态 flag:0更新状态，flag：1更新最佳答案状态
	 */
	public void changeStatus(Integer faqID, Integer status, byte flag) {
		Short newStatus;
		if (status == 1) {
			newStatus = 0;
		} else {
			newStatus = 1;
		}
		if (flag == 0) {
			faq.setStatus(newStatus);
		} else {
			bestFaq.setStatus(newStatus);
		}
		try {
			if (flag == 0) {
				faqFacade.update(faq);
			} else {
				faqFacade.update(bestFaq);
			}
			this.addInfoMessage("info", SUCESSINFO);
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
		}
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
		this.page = this.faqFacade.findReplyFaqs(faqID);
		this.updateComponent("searchForm:faqTable");
	}

	public Faq getFaq() {
		return faq;
	}

	public Faq getBestFaq() {
		return bestFaq;
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

}
