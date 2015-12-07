package com.cdel.advc.innermsg.domain;

@SuppressWarnings("serial")
public class InnerMsgAll extends InnerMsg implements java.io.Serializable {
	private Integer orgID;// 教师所在机构

	public Integer getOrgID() {
		return orgID;
	}

	public void setOrgID(Integer orgID) {
		this.orgID = orgID;
	}

}
