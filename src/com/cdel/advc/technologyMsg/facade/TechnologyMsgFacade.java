package com.cdel.advc.technologyMsg.facade;

import java.io.Serializable;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdel.advc.membermsg.domain.Membermsg;
import com.cdel.advc.membermsg.facade.MembermsgFacade;
import com.cdel.advc.membermsglog.domain.MembermsgLog;
import com.cdel.advc.membermsglog.facade.MembermsgLogFacade;
import com.cdel.advc.technologyMsg.domain.TechnologyMsg;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.DateUtil;
import com.cdel.util.StringUtil;

@SuppressWarnings("serial")
@Service
public class TechnologyMsgFacade extends BaseFacadeImpl<TechnologyMsg, Integer>
		implements Serializable {
	@Autowired
	private MembermsgFacade membermsgFacade;
	@Autowired
	private MembermsgLogFacade membermsgLogFacade;

	/**
	 * 转为技术/转为正常
	 * 
	 * @param msgType
	 * @param membermsg
	 *            userID操作人 userName操作人
	 */
	public void updateMsg(Short msgType, Integer userID, String userName,
			Membermsg membermsg) throws Exception {
		membermsg.setMsgType(msgType);
		MembermsgLog membermsgLog = null;
		if (!StringUtil.nullToString(membermsg.getReplyContentOld()).equals("")) {
			membermsgLog = new MembermsgLog();
			membermsgLog.setLogType((short) 1);
			String typeName = "";
			if (msgType == 1) {
				typeName = "技术";
			}
			if (msgType == 0) {
				typeName = "正常";
			}
			String content = "老师：" + userName + " 转为" + typeName + "消息" + " ["
					+ DateUtil.getNowToString("yyyy-MM-dd HH:mm") + "]";
			membermsgLog.setLogContent(content);
			membermsgLog.setMsgID(membermsg.getMsgID());
			membermsgLog.setCreateTime(new Date());
			membermsgLog.setCreator(userID);
		}
		membermsgFacade.update(membermsg);
		if (membermsgLog != null) {
			membermsgLogFacade.add(membermsgLog);
		}
	}

	/**
	 * 提交
	 * 
	 * @param userID
	 * @param userName
	 * @param membermsg
	 * @throws Exception
	 */
	public void updateMsg(Integer userID, String userName, Membermsg membermsg)
			throws Exception {
		MembermsgLog membermsgLog = null;
		String replyContent = StringUtil.nullToString(membermsg
				.getReplyContent());
		if (!replyContent.equals("")) {
			membermsg.setReplyContent(StringUtil.changeContent(replyContent));
			membermsg.setReplyTime(new Date());
			membermsg.setTeacherID(userID);
		}
		String replyContentOld = StringUtil.nullToString(membermsg
				.getReplyContentOld());
		if (!replyContentOld.equals("")) {
			membermsgLog = new MembermsgLog();
			membermsgLog.setLogType((short) 2);
			String content = "老师："
					+ userName
					+ "<br/>原回复："
					+ replyContentOld
					+ "<br/>原回复时间："
					+ DateUtil.getNowDateString(membermsg.getReplyTime(),
							"yyyy-MM-dd HH:mm");
			membermsgLog.setLogContent(content);
			membermsgLog.setMsgID(membermsg.getMsgID());
			membermsgLog.setCreateTime(new Date());
			membermsgLog.setCreator(userID);
		}
		membermsgFacade.update(membermsg);
		if (membermsgLog != null) {
			membermsgLogFacade.add(membermsgLog);
		}
	}

}