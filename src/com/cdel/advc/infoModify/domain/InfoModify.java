package com.cdel.advc.infoModify.domain;

import java.io.Serializable;
import com.cdel.advc.membermsg.domain.Membermsg;
import com.cdel.util.StringUtil;

@SuppressWarnings("serial")
public class InfoModify extends Membermsg implements Serializable {
	private Integer myCount;// 记录总数
	private String msgContent4TextArea;// 转换为textarea可以换行的内容
	private String msgContentNew;// 新的内容

	public Integer getMyCount() {
		return myCount;
	}

	public void setMyCount(Integer myCount) {
		this.myCount = myCount;
	}

	public String getMsgContent4TextArea() {
		msgContent4TextArea = StringUtil.changeContent2(getMsgContent());
		return msgContent4TextArea;
	}

	public String getMsgContent4TextArea2() {
		return msgContent4TextArea;
	}

	public void setMsgContent4TextArea(String msgContent4TextArea) {
		this.msgContent4TextArea = msgContent4TextArea;
	}

	public String getMsgContentNew() {
		return msgContentNew;
	}

	public void setMsgContentNew(String msgContentNew) {
		this.msgContentNew = msgContentNew;
	}

}