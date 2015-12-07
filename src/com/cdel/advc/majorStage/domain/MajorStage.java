package com.cdel.advc.majorStage.domain;

import java.io.Serializable;

import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 学习阶段和辅导关系实体
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
public class MajorStage extends BaseDomain implements Serializable {

	private Integer majorID;
	private Integer stageID;
	private String stageName;

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

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

}
