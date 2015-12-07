/*
 * @Title: FaqFacade.java
 * @Package com.cdel.advc.faq.facade
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-7-3 下午5:38:34
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-7-3                          
 */
package com.cdel.advc.faq.facade;

import org.primefaces.model.LazyDataModel;
import org.springframework.stereotype.Service;
import com.cdel.advc.faq.domain.Faq;
import com.cdel.util.BaseFacadeImpl;

/**
 * <p>
 * 互助答疑 facade层
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-3 下午5:38:34
 */
@SuppressWarnings("serial")
@Service
public class FaqFacade extends BaseFacadeImpl<Faq, Integer> {

	/**
	 * 查询答疑的最佳回复
	 * 
	 * @param faqID
	 * @return
	 */
	public Faq findBestFaq(Integer faqID) {
		return this.baseDao.getByID(faqID, "findBestFaq");
	}

	/**
	 * 根据ID获取faq
	 * 
	 * @param faqID
	 * @return
	 */
	public Faq getFaqMini(Integer faqID) {
		return this.baseDao.getByID(faqID, "getFaqMini");
	}

	/**
	 * 根据ID查询互助答疑的回复帖子
	 * 
	 * @param faqID
	 * @return
	 */
	public LazyDataModel<Faq> findReplyFaqs(Integer faqID) {
		Faq faq = new Faq();
		faq.setFaqID(faqID);

		return this.baseDao.findPage(faq, "countReplyFaqs", "findReplyFaqs");
	}

	/**
	 * 返回记录总数
	 * 
	 * @param membernoteAll
	 * @return
	 */
	public int getCount(Faq faq) {
		return (Integer) this.baseDao.get(faq, "countFaq");
	}

}
