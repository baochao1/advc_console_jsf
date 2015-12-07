package com.cdel.advc.sendGroup.facade;

import java.io.Serializable;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdel.advc.memberSms.domain.MemberSms;
import com.cdel.advc.memberSms.facade.MemberSmsFacade;
import com.cdel.advc.sendGroup.domain.SendGroup;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class SendGroupFacade extends BaseFacadeImpl<SendGroup, Integer>
		implements Serializable {
	@Autowired
	private MemberSmsFacade memberSmsFacade;

	/**
	 * 验证短信
	 * 
	 * @return
	 */
	public boolean checkSendMessage(SendGroup sendGroupInfo) {
		if (sendGroupInfo.getMajorID() == null) {
			return addWarnMessage("辅导不能为空！");
		}
		if (sendGroupInfo.getCourseID() == null) {
			return addWarnMessage("课程不能为空！");
		}
		if (StringUtils.isBlank(sendGroupInfo.getContent())) {
			return addWarnMessage("短信内容不能为空！");
		}
		return true;
	}

	public boolean sendMessage(Integer userID, SendGroup sendGroupInfo)
			throws Exception {
		MemberSms sms = new MemberSms();
		sms.setCourseID(sendGroupInfo.getCourseID());
		sms.setSender(userID);
		sms.setContent(sendGroupInfo.getContent());
		sms.setMajorID(sendGroupInfo.getMajorID());
		return memberSmsFacade.addClassMemberSmss(sms);
	}

}
