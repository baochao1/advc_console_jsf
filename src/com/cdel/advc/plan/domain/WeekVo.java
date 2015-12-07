/*
 * @Title: WeekVo.java
 * @Package com.cdel.advc.plan.domain
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-8-2 下午12:10:20
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-8-2                          
 */
package com.cdel.advc.plan.domain;

import java.io.Serializable;
import java.util.Date;

import com.cdel.util.DateUtil;

/** 
 * <p>周次vo</p>
 * 
 * @author Du Haiying
 * Create at:2013-8-2 下午12:10:20
 */
@SuppressWarnings("serial")
public class WeekVo implements Serializable ,Comparable<WeekVo> {
	
	/** 周次序号 */
	private Short WeekNo;
	
	/** 周开始时间 */
	private Date weekStartTime;
	
	/** 周截止时间 */
	private Date weekEndTime;
	
	/** 开始显示值*/
	public String getWeekStartTimeStr(){
		
		if (this.weekStartTime == null) {
			return "";
		}else {
			return DateUtil.getNowDateString(this.weekStartTime, "yyyy-MM-dd");
		}
	}

	/** 开始显示值*/
	public String getWeekEndTimeStr(){
		
		if (this.weekEndTime == null) {
			return "";
		}else {
			return DateUtil.getNowDateString(this.weekEndTime, "yyyy-MM-dd");
		}
	}
	
	public Short getWeekNo() {
		return WeekNo;
	}

	public void setWeekNo(Short weekNo) {
		WeekNo = weekNo;
	}

	public Date getWeekStartTime() {
		return weekStartTime;
	}

	public void setWeekStartTime(Date weekStartTime) {
		this.weekStartTime = weekStartTime;
	}

	public Date getWeekEndTime() {
		return weekEndTime;
	}

	public void setWeekEndTime(Date weekEndTime) {
		this.weekEndTime = weekEndTime;
	}
	
	@Override
	public int compareTo(WeekVo o) {
		
		if (o == null || o.WeekNo == 0) {
			return -1;
		}
		
		if(this.WeekNo == 0){
			return 1;
		}
		
		return this.WeekNo - o.WeekNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((WeekNo == null) ? 0 : WeekNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WeekVo other = (WeekVo) obj;
		if (WeekNo == null) {
			if (other.WeekNo != null)
				return false;
		} else if (!WeekNo.equals(other.WeekNo))
			return false;
		return true;
	}
	
}
