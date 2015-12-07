package com.cdel.advc.serviceItem.domain;

import java.io.Serializable;

import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 服务项实体
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
public class ServiceItem extends BaseDomain implements Serializable {

	private Integer serviceID;
	private String serviceName;
	private Integer stageID;
	private Integer sort;

	public Integer getServiceID() {
		return serviceID;
	}

	public void setServiceID(Integer serviceID) {
		this.serviceID = serviceID;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Integer getStageID() {
		return stageID;
	}

	public void setStageID(Integer stageID) {
		this.stageID = stageID;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((serviceID == null) ? 0 : serviceID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ServiceItem))
			return false;
		ServiceItem other = (ServiceItem) obj;
		if (serviceID == null) {
			if (other.serviceID != null)
				return false;
		} else if (!serviceID.equals(other.serviceID))
			return false;
		return true;
	}

}
