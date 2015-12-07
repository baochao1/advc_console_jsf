/*
 * @Title: FaqAction.java
 * @Package com.cdel.advc.faq.action
 * @Description: TODO
 * @author 张苏磊
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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.faq.domain.Faq;
import com.cdel.advc.faq.facade.FaqFacade;
import com.cdel.util.BaseAction;

/**
 * <p>
 * (全部互助答疑)互助答疑 bean
 * </p>
 * 
 * @author 张苏磊 Create at:2013-7-3 下午7:55:53
 */
@SuppressWarnings("serial")
@ManagedBean
public class FaqReqAction extends BaseAction<Faq> implements Serializable {
	@ManagedProperty("#{faqFacade}")
	private FaqFacade faqFacade;

	/**
	 * 修改状态
	 */
	public void changeStatus(Integer faqID, Integer status) {
		Faq faq = new Faq();
		faq.setFaqID(faqID);
		Short newStatus;
		if (status == 1) {
			newStatus = 0;
		} else {
			newStatus = 1;
		}
		faq.setStatus(newStatus);
		try {
			faqFacade.update(faq);
			this.addInfoMessage("info", SUCESSINFO);
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
		}
	}

	public void setFaqFacade(FaqFacade faqFacade) {
		this.faqFacade = faqFacade;
	}

}
