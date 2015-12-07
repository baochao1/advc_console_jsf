package com.cdel.advc.membercall.domain;

import java.util.Date;
import com.cdel.util.DateUtil;

/**
 * 
 * @author 张苏磊 Create at:2013-7-10 上午9:40:58
 */
@SuppressWarnings("serial")
public class MemberCallPostRecord extends MemberCall implements
		java.io.Serializable {
	// 上次回访时间
	private Date beforeTime;
	// 本次回访时间
	private Date timeTime;
	// 相差天数
	private Integer dayNum;
	private Byte timeFlag;

	public Date getBeforeTime() {
		return beforeTime;
	}

	public String getBeforeTimeStr() {
		return DateUtil.getNowDateString(beforeTime, "yyyy-MM-dd");
	}

	public void setBeforeTime(Date beforeTime) {
		this.beforeTime = beforeTime;
	}

	public Date getTimeTime() {
		return timeTime;
	}

	public String getTimeTimeStr() {
		return DateUtil.getNowDateString(timeTime, "yyyy-MM-dd");
	}

	public void setTimeTime(Date timeTime) {
		this.timeTime = timeTime;
	}

	public Integer getDayNum() {
		return dayNum;
	}

	public void setDayNum(Integer dayNum) {
		this.dayNum = dayNum;
	}

	public Byte getTimeFlag() {
		return timeFlag;
	}

	public void setTimeFlag(Byte timeFlag) {
		this.timeFlag = timeFlag;
	}

}
