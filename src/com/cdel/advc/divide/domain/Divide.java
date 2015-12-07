package com.cdel.advc.divide.domain;

import java.util.List;
import com.cdel.advc.strategy.domain.Strategy;
import com.chnedu.plat.rad.domain.BaseDomain;

@SuppressWarnings("serial")
public class Divide extends BaseDomain implements java.io.Serializable {
	private Integer majorID;
	private String majorName;
	private Integer userID;
	private List<Strategy> strategyList;
	private String userName;

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

	public List<Strategy> getStrategyList() {
		return strategyList;
	}

	public void setStrategyList(List<Strategy> strategyList) {
		this.strategyList = strategyList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
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
		if (!(obj instanceof Divide))
			return false;
		Divide other = (Divide) obj;
		if (majorID == null) {
			if (other.majorID != null)
				return false;
		} else if (!majorID.equals(other.majorID))
			return false;
		return true;
	}

}
