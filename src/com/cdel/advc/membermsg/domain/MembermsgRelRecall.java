package com.cdel.advc.membermsg.domain;

import java.io.Serializable;

import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 
 * <p>
 * 如果是电话回访失败发留言，则需要进行关联，此为电话回访和学员短信关系表：ADVC_HOME_MEMBER_CALL_MSG
 * </p>
 * 
 * @author 张苏磊 Create at:2013-7-4 上午11:14:58
 */
@SuppressWarnings("serial")
public class MembermsgRelRecall extends BaseDomain implements Serializable {

	private Integer msgID;

	private Integer callID;

	public Integer getMsgID() {
		return msgID;
	}

	public void setMsgID(Integer msgID) {
		this.msgID = msgID;
	}

	public Integer getCallID() {
		return callID;
	}

	public void setCallID(Integer callID) {
		this.callID = callID;
	}

}