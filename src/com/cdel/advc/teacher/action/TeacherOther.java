package com.cdel.advc.teacher.action;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.cdel.advc.teacher.domain.Teacher;
import com.cdel.advc.teacher.facade.TeacherFacade;

@ManagedBean
@SuppressWarnings("serial")
@ViewScoped
public class TeacherOther implements Serializable {

	@ManagedProperty(value = "#{teacherFacade}")
	private TeacherFacade teacherFacade;

	private List<Teacher> teacherList;

	public void setTeacherFacade(TeacherFacade teacherFacade) {
		this.teacherFacade = teacherFacade;
	}

	public List<Teacher> getTeacherList() {
		return teacherList;
	}

	/**
	 * 排除该teacherID的列表
	 * 
	 * @param teacherID
	 */
	public void setTeacherList(String teacherCode) {
		this.teacherList = teacherFacade.getTeacherListNoID(teacherCode);
	}

	public void setTeacherList() {
		this.teacherList = teacherFacade.findList(1);
	}

}
