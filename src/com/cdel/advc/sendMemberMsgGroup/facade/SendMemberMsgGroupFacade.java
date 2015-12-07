package com.cdel.advc.sendMemberMsgGroup.facade;

import java.io.Serializable;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdel.advc.member.domain.Member;
import com.cdel.advc.membermsg.facade.MembermsgFacade;
import com.cdel.advc.sendMemberMsgGroup.domain.SendMemberMsgGroup;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class SendMemberMsgGroupFacade extends
		BaseFacadeImpl<SendMemberMsgGroup, Integer> implements Serializable {
	@Autowired
	private MembermsgFacade membermsgFacade;

	/**
	 * 验证发送信息
	 * 
	 * @return
	 */
	public boolean checkSendMessage(SendMemberMsgGroup sendMemberMsgGroupInfo) {
		if (sendMemberMsgGroupInfo.getMajorID() == null) {
			return addWarnMessage("辅导不能为空！");
		}
		if (sendMemberMsgGroupInfo.getCourseID() == null) {
			return addWarnMessage("课程不能为空！");
		}
		if (sendMemberMsgGroupInfo.getMsgType() == null) {
			return addWarnMessage("消息类型不能为空！");
		}
		if (StringUtils.isBlank(sendMemberMsgGroupInfo.getMsgTitle())) {
			return addWarnMessage("消息标题不能为空！");
		}
		if (StringUtils.isBlank(sendMemberMsgGroupInfo.getContent())) {
			return addWarnMessage("发送内容不能为空！");
		}
		return true;
	}

	/**
	 * 发送消息
	 * 
	 * @return
	 */
	public boolean sendMemberMsg(Integer userID,
			SendMemberMsgGroup sendMemberMsgGroupInfo) throws Exception {
		Member member = new Member();
		member.setMajorID(sendMemberMsgGroupInfo.getMajorID());
		member.setCourseIDStr(sendMemberMsgGroupInfo.getCourseID() + "");
		return membermsgFacade.addMemberMsg(userID, sendMemberMsgGroupInfo,
				member);
	}
}
