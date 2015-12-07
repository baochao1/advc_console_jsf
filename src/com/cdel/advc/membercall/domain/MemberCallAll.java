package com.cdel.advc.membercall.domain;

import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;

import com.cdel.util.Contants;

/**
 * 
 * <p>
 * 全部回访记录 实体
 * </p>
 * 
 * @author 张苏磊 Create at:2013-7-10 上午9:40:58
 */
@SuppressWarnings("serial")
public class MemberCallAll extends MemberCall implements java.io.Serializable {
	private Integer callCount;// 按教师统计时,教师的回访个数

	public Integer getCallCount() {
		return callCount;
	}

	public void setCallCount(Integer callCount) {
		this.callCount = callCount;
	}

	public String getTeacherNameEncode() {
		if (StringUtils.isBlank(getTeacherName())) {
			return "";
		} else {
			try {
				return URLEncoder.encode(getTeacherName(), Contants.CODE);
			} catch (Exception e) {
				return "";
			}
		}
	}

}
