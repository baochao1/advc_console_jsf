package com.cdel.advc.stage.domain;

import java.io.Serializable;

import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 学习阶段实体
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
public class Stage extends BaseDomain implements Serializable {

	private Integer stageID;
	private String stageName;
	private Integer status;

	public Integer getStageID() {
		return stageID;
	}

	public void setStageID(Integer stageID) {
		this.stageID = stageID;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
