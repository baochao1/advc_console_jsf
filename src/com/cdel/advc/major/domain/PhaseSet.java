package com.cdel.advc.major.domain;

import java.io.Serializable;

import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * @author 张苏磊 舍弃阶段
 */
@SuppressWarnings("serial")
public class PhaseSet extends BaseDomain implements Serializable {

	private Integer majorID;
	private Short classType;// 1实验班,2精品班,3混合班
	private Short phaseNo;
	private Short sequence;

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}

	public Short getClassType() {
		return classType;
	}

	public void setClassType(Short classType) {
		this.classType = classType;
	}

	public Short getPhaseNo() {
		return phaseNo;
	}

	public void setPhaseNo(Short phaseNo) {
		this.phaseNo = phaseNo;
	}

	public Short getSequence() {
		return sequence;
	}

	public void setSequence(Short sequence) {
		this.sequence = sequence;
	}

}
