package com.cdel.advc.membercall.domain;

import com.cdel.util.Contants;

/**
 * 
 * <p>
 * 全部预约回访记录 实体
 * </p>
 * 
 * @author 张苏磊 Create at:2013-7-10 上午9:40:58
 */
@SuppressWarnings("serial")
public class ReserveCallAll extends MemberCall implements
		java.io.Serializable {
	/**
	 * 设置 "预约回访"状态
	 */
	public Short getReserveStatus() {
		if (getReservedDate() != null) {
			return 0;
		} else {
			if (getCallDay() != null) {
				if (getCallDay() >= 25 && getCallDay() < 30) {
					return 1;
				} else if (getCallDay() >= 30) {
					return 2;
				} else {
					return null;
				}
			} else {
				return null;
			}
		}
	}

	/** 预约回访状态 显示值 */
	public String getReserveStatusStr() {
		if (getReserveStatus() == null) {
			return "";
		} else {
			return Contants.memberCallNotifyType.get(getReserveStatus());
		}
	}

}
