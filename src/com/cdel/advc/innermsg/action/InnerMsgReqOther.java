package com.cdel.advc.innermsg.action;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import com.cdel.advc.common.JsfHelper;
import com.cdel.advc.innermsg.domain.InnerMsg;
import com.cdel.advc.innermsg.facade.InnerMsgFacade;
import com.chnedu.plat.rad.util.RequestHandler;

@SuppressWarnings("serial")
@ManagedBean
public class InnerMsgReqOther implements Serializable {
	@ManagedProperty(value = "#{innerMsgFacade}")
	private InnerMsgFacade innerMsgFacade;

	private Integer innermsgCount = 0;// 我的沟通消息总数

	/**
	 * 获取我的沟通消息总数
	 */
	public void initInnerMsgCount() {
		Integer loginID = RequestHandler.getCurrentAdmin(JsfHelper
				.getRequest(FacesContext.getCurrentInstance()));
		InnerMsg searchInnerMsg = new InnerMsg();
		searchInnerMsg.setReceiveTeacherID(loginID);
		searchInnerMsg.setSender(loginID);
		searchInnerMsg.setMailType((short) 1);
		searchInnerMsg.setReadStatus((short) 0);
		innermsgCount = innerMsgFacade.countInnerMsg(searchInnerMsg);
	}

	public Integer getInnermsgCount() {
		return innermsgCount;
	}

	public void setInnerMsgFacade(InnerMsgFacade innerMsgFacade) {
		this.innerMsgFacade = innerMsgFacade;
	}

}
