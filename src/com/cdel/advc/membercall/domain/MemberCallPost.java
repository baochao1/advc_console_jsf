package com.cdel.advc.membercall.domain;


/**
 * 
 * <p>
 * 全部过期回访记录 实体
 * </p>
 * 
 * @author 张苏磊 Create at:2013-7-10 上午9:40:58
 */
@SuppressWarnings("serial")
public class MemberCallPost extends MemberCall implements java.io.Serializable {
	// 设置超时查询条件
	private Byte dayNumber;	
	
	public Byte getDayNumber() {
		return dayNumber;
	}

	public void setDayNumber(Byte dayNumber) {
		this.dayNumber = dayNumber;
		this.setStartAndEndDay(dayNumber);
	}
}
