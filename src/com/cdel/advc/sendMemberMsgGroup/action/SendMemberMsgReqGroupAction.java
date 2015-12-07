package com.cdel.advc.sendMemberMsgGroup.action;

import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.sendMemberMsgGroup.domain.SendMemberMsgGroup;
import com.cdel.advc.sendMemberMsgGroup.facade.SendMemberMsgGroupFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;

@SuppressWarnings("serial")
@ManagedBean
public class SendMemberMsgReqGroupAction extends BaseAction<SendMemberMsgGroup>
		implements Serializable {
	@ManagedProperty(value = "#{sendMemberMsgGroupFacade}")
	private SendMemberMsgGroupFacade sendMemberMsgGroupFacade;

	private SendMemberMsgGroup sendMemberMsgGroupInfo = new SendMemberMsgGroup();

	@PostConstruct
	public void initSendMemberMsgGroupAction() {
		sendMemberMsgGroupInfo.setMsgType((short) 3);
	}

	/**
	 * 发送短信
	 * 
	 * @param sendGroupInfo
	 * @throws Exception
	 */
	public void sendMessage() {
		byte submitSuccess = 0;// 添加是否成功
		try {
			if (sendMemberMsgGroupFacade
					.checkSendMessage(sendMemberMsgGroupInfo)) {
				if (sendMemberMsgGroupFacade.sendMemberMsg(
						this.getCurrentUserID(), sendMemberMsgGroupInfo)) {
					submitSuccess = 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public SendMemberMsgGroup getSendMemberMsgGroupInfo() {
		return sendMemberMsgGroupInfo;
	}

	public void setSendMemberMsgGroupInfo(
			SendMemberMsgGroup sendMemberMsgGroupInfo) {
		this.sendMemberMsgGroupInfo = sendMemberMsgGroupInfo;
	}

	public void setSendMemberMsgGroupFacade(
			SendMemberMsgGroupFacade sendMemberMsgGroupFacade) {
		this.sendMemberMsgGroupFacade = sendMemberMsgGroupFacade;
	}

	/**
	 * 20140527 ADD XXG 得到消息类型列表 msgType = 3 // 普通消息 msgType = 4 // 备考要点/提醒
	 * 
	 */
	public Map<Short, String> getMsgType() {
		return Contants.memberMsgType;
	}

}
