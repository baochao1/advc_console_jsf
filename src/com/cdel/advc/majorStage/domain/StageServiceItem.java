package com.cdel.advc.majorStage.domain;

import java.io.Serializable;

import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 服务项目和辅导关系实体
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
public class StageServiceItem extends BaseDomain implements Serializable {

	private Integer majorID;
	private Integer stageID;
	private Integer serviceID;
	private String serviceName;

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}

	public Integer getStageID() {
		return stageID;
	}

	public void setStageID(Integer stageID) {
		this.stageID = stageID;
	}

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

}
