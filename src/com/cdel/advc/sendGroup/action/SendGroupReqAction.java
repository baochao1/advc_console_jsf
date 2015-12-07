package com.cdel.advc.sendGroup.action;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.sendGroup.domain.SendGroup;
import com.cdel.advc.sendGroup.facade.SendGroupFacade;
import com.cdel.util.BaseAction;

@SuppressWarnings("serial")
@ManagedBean
public class SendGroupReqAction extends BaseAction<SendGroup> implements
		Serializable {
	@ManagedProperty(value = "#{sendGroupFacade}")
	private SendGroupFacade sendGroupFacade;

	private SendGroup sendGroupInfo = new SendGroup();

	/**
	 * 发送短信
	 * 
	 * @param sendGroupInfo
	 * @throws Exception
	 */
	public void sendMessage() {
		byte submitSuccess = 0;// 添加是否成功
		try {
			if (sendGroupFacade.checkSendMessage(sendGroupInfo)) {
				if (sendGroupFacade.sendMessage(this.getCurrentUserID(),
						sendGroupInfo)) {
					submitSuccess = 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public SendGroup getSendGroupInfo() {
		return sendGroupInfo;
	}

	public void setSendGroupInfo(SendGroup sendGroupInfo) {
		this.sendGroupInfo = sendGroupInfo;
	}

	public void setSendGroupFacade(SendGroupFacade sendGroupFacade) {
		this.sendGroupFacade = sendGroupFacade;
	}

}
