package com.cdel.advc.major.domain;

import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 
 * <p>主库 辅导 模型</p>
 * 
 * @author Du Haiying
 * Create at:2014-3-12 下午3:00:01
 */
@SuppressWarnings("serial")
public class MainMajor extends BaseDomain implements java.io.Serializable {
	
	private Integer majorID;
	
	private String majorName;
	
	public Integer getMajorID() {
		return majorID;
	}
	
	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}
	
	public String getMajorName() {
		return majorName;
	}
	
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((majorID == null) ? 0 : majorID.hashCode());
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
		MainMajor other = (MainMajor) obj;
		if (majorID == null) {
			if (other.majorID != null)
				return false;
		} else if (!majorID.equals(other.majorID))
			return false;
		return true;
	}
	
}