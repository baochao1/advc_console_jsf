/*
 * @Title: MembermessageAction.java
 * @Package com.cdel.advc.membermessage.action
 * @Description: TODO
 * @author zhangsulei
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
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.membermessage.domain.Membermessage;
import com.cdel.advc.membermessage.domain.MembermessageAll;
import com.cdel.advc.membermessage.facade.MembermessageFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;

/**
 * <p>
 * （全部班级短信）bean
 * </p>
 * 
 * @author 张苏磊 Create at:2013-7-4 上午11:36:09
 */
@SuppressWarnings("serial")
@ManagedBean
public class MembermessageAllReqAction extends BaseAction<MembermessageAll>
		implements Serializable {

	@ManagedProperty("#{membermessageFacade}")
	private MembermessageFacade membermessageFacade;

	private Membermessage msg;

	/**
	 * 修改状态
	 */
	public void changeStatus(Integer msgID, Integer status) {
		msg = new Membermessage();
		msg.setMsgID(msgID);
		Short newStatus;
		if (status == 1) {
			newStatus = 0;
		} else {
			newStatus = 1;
		}
		msg.setStatus(newStatus);
		try {
			membermessageFacade.update(msg);
			this.addInfoMessage("info", SUCESSINFO);
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
		}
	}

	/**
	 * 查看短信
	 * 
	 * @param msgID
	 *            短信ID
	 */
	public void showMsg(Integer msgID) {
		if (msgID != null) {
			this.msg = this.membermessageFacade.findByID(msgID);
		}
	}

	/**
	 * 学员短信阅读状态
	 */
	public Map<Short, String> getReadStatuss() {
		return Contants.readStatus;
	}

	public Membermessage getMsg() {
		return msg;
	}

	public void setMembermessageFacade(MembermessageFacade membermessageFacade) {
		this.membermessageFacade = membermessageFacade;
	}

}
