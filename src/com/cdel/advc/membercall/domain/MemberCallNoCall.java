package com.cdel.advc.membercall.domain;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;


/**
 * 
 * <p>
 * 回访提醒 实体
 * </p>
 * 
 * @author 张苏磊 Create at:2013-7-10 上午9:40:58
 */
@SuppressWarnings("serial")
public class MemberCallNoCall extends MemberCall implements
		java.io.Serializable {

	/** 多个组织  */
	private String orgSet;
	
	/** 组织ID 数组  */
	private Integer[] orgIDs;

	public String getOrgSet() {
		return orgSet;
	}

	public void setOrgSet(String orgSet) {
		this.orgSet = orgSet;
	}

	public Integer[] getOrgIDs() {
		return orgIDs;
	}

	public void setOrgIDs(Integer[] orgIDs) {
		this.orgIDs = orgIDs;
	}

	@Override
	public String toString() {
		return "MemberCallNoCall [orgSet=" + orgSet + ", orgIDs="
				+ Arrays.toString(orgIDs) + "]";
	}

}
