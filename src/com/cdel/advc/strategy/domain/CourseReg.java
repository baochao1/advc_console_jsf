package com.cdel.advc.strategy.domain;

import java.io.Serializable;
import org.primefaces.model.DualListModel;
import com.cdel.advc.course.domain.Course;

/**
 * 
 * <p>
 * 策略规则实体
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-1 上午11:40:30
 */
@SuppressWarnings("serial")
public class CourseReg implements Serializable {

	private byte andOr = 0;// 逻辑与或(1与，0或)
	private byte piPei = 1;// 匹配(1："全部匹配",2："任意匹配")
	private DualListModel<Course> courseReg;// 规则
	private byte courseNum = 1;// 任选几门课组合

	public byte getAndOr() {
		return andOr;
	}

	public void setAndOr(byte andOr) {
		this.andOr = andOr;
	}

	public DualListModel<Course> getCourseReg() {
		return courseReg;
	}

	public void setCourseReg(DualListModel<Course> courseReg) {
		this.courseReg = courseReg;
	}

	public byte getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(byte courseNum) {
		this.courseNum = courseNum;
	}

	public byte getPiPei() {
		return piPei;
	}

	public void setPiPei(byte piPei) {
		this.piPei = piPei;
	}

}