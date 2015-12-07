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
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.faq.domain.Faq;
import com.cdel.advc.faq.facade.FaqFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.ExceptionUtil;

/**
 * <p>
 * 互助答疑 回复 bean
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-3 下午7:55:53
 */
@SuppressWarnings("serial")
@ManagedBean
public class ReplyFaqReqAction extends BaseAction<Faq> implements Serializable {
	@ManagedProperty("#{faqFacade}")
	private FaqFacade faqFacade;

	private Faq updateFaq = new Faq();
	private byte flag = -1;

	/**
	 * 显示添加页面，并初始化处理
	 */
	public void showAdd() {
		flag = 0;
	}

	/**
	 * 显示添加页面，并初始化处理
	 */
	public void showUpdate(Integer faqID) {
		flag = 1;
		updateFaq = faqFacade.getFaqMini(faqID);
	}

	/**
	 * 教师添加解答（回复），同时要更新原来帖子的回复次数
	 * 
	 * @throws Exception
	 */
	public void submitFaqContinue() {
		byte submitSuccess = 0;// 添加是否成功
		ReplyFaqAction replyFaqAction = (ReplyFaqAction) this
				.getViewAction("replyFaqAction");
		if (updateFaq.getFaqContent().equals("")) {
			this.addWarnMessage("回复内容不能为空！");
			return;
		}
		Faq faq = replyFaqAction.getFaq();
		if (flag == 0) {
			updateFaq.setFaqType((short) 1);
			updateFaq.setParentID(faq.getFaqID());
			updateFaq.setFaqTitle("无");
			updateFaq.setClassID(faq.getClassID());
			updateFaq.setCourseID(faq.getCourseID());
			updateFaq.setTeacherID(this.getCurrentUserID());
			updateFaq.setCreateTime(new Date());
			updateFaq.setStatus((short) 1);
			updateFaq.setMajorID(faq.getMajorID());
		}
		try {
			if (flag == 0) {
				this.faqFacade.add(updateFaq);
				// 更新回复数
				faq.setReplyNum(faq.getReplyNum() + 1);
				faqFacade.update(faq);
				replyFaqAction.search();
			} else {
				this.faqFacade.update(updateFaq);
				replyFaqAction.search4U();
			}
			submitSuccess = 1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateFaq=" + updateFaq);
			logger.error(ExceptionUtil.getEMsg(e));
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public Faq getUpdateFaq() {
		return updateFaq;
	}

	public void setUpdateFaq(Faq updateFaq) {
		this.updateFaq = updateFaq;
	}

	public byte getFlag() {
		return flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

	public void setFaqFacade(FaqFacade faqFacade) {
		this.faqFacade = faqFacade;
	}

}
