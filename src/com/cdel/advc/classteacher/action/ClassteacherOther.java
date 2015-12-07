package com.cdel.advc.classteacher.action;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.cdel.advc.classteacher.domain.Classteacher;
import com.cdel.advc.classteacher.facade.ClassteacherFacade;

/**
 * 
 * <p>
 * 班级管理员 相关action
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-24 上午9:49:30
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClassteacherOther implements Serializable {

	@ManagedProperty("#{classteacherFacade}")
	private ClassteacherFacade classteacherFacade;

	private List<Classteacher> managers;// 班级管理员

	public List<Classteacher> getManagers() {
		return managers;
	}

	public void setManagers(Integer classID) {
		this.managers = classteacherFacade.findList(classID);
	}

	public void setClassteacherFacade(ClassteacherFacade classteacherFacade) {
		this.classteacherFacade = classteacherFacade;
	}

}
