package com.cdel.advc.innermsg.facade;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.cdel.advc.innermsg.domain.InnerMsgReceive;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.DateUtil;

@SuppressWarnings("serial")
@Service
public class InnerMsgReceiveFacade extends
		BaseFacadeImpl<InnerMsgReceive, Integer> implements Serializable {
	/*
	 * 查询接收人 flag：0查看接收人用的；1：查看详情用的
	 */
	public String getReceiverStr(Integer innerMsgID, byte flag) {
		List<InnerMsgReceive> innermsgRecerveList = findList(innerMsgID);
		// 查找该消息所有的接人及状态
		StringBuffer receiverStr = new StringBuffer("");
		if (innermsgRecerveList != null) {
			for (int i = 0; i < innermsgRecerveList.size(); i++) {
				InnerMsgReceive imr = innermsgRecerveList.get(i);
				Short readStatus = imr.getReadStatus();
				String status = "";
				if (readStatus != null && readStatus == 0) {
					status = "-<font color='red'>未读</font>";
				} else {
					if (flag == 1) {
						status = "-<font color='green'>已读("
								+ DateUtil.getNowDateString(imr.getReadTime(),
										"yyyy-MM-dd HH:mm") + ")</font>";
					}
				}
				receiverStr.append(imr.getTeacherCode()).append("(")
						.append(imr.getSenderName()).append(")").append(status)
						.append("，");
			}
		}
		if (!receiverStr.toString().equals("")) {
			return StringUtils.substring(receiverStr.toString(), 0,
					receiverStr.length() - 1);
		} else {
			return "";
		}
	}

}
