package com.cdel.advc.membercall.domain;

/**
 * 
 * <p>
 * 过期预约回访记录 实体
 * </p>
 * 
 * @author 张苏磊 Create at:2013-7-10 上午9:40:58
 */
@SuppressWarnings("serial")
public class ReserveCallPost extends ReserveCallAll implements
		java.io.Serializable {
	/** 预约回访过期小时数 */
	private Integer postHours;

	public Integer getPostHours() {
		return postHours;
	}

}
