package com.cdel.advc.classteacher.action;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.cdel.advc.classteacher.domain.Classteacher;
import com.cdel.advc.teacher.domain.Teacher;
import com.cdel.advc.teacher.facade.TeacherFacade;
import com.cdel.util.BaseAction;

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
public class ClassteacherAction extends BaseAction<Classteacher> {

	@ManagedProperty(value = "#{teacherFacade}")
	private TeacherFacade teacherFacade;

	private Integer classID;// 要操作的classID
	private List<Classteacher> teacherList;// 本班管理员
	private Integer[] teacherIDs;// 选中的教师
	private Integer zhuRenID;// 班主任ID
	private Teacher zhuRenTeacher;// 班主任

	/**
	 * 删除管理员
	 * 
	 * @param index
	 */
	public void deleteManager(int index) {
		teacherList.remove(index);
	}

	public Integer getClassID() {
		return classID;
	}

	public void setClassID(Integer classID) {
		this.classID = classID;
	}

	public List<Classteacher> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<Classteacher> teacherList) {
		this.teacherList = teacherList;
	}

	public void setTeacherFacade(TeacherFacade teacherFacade) {
		this.teacherFacade = teacherFacade;
	}

	public Integer[] getTeacherIDs() {
		return teacherIDs;
	}

	public void setTeacherIDs(Integer[] teacherIDs) {
		this.teacherIDs = teacherIDs;
	}

	public Integer getZhuRenID() {
		return zhuRenID;
	}

	public void setZhuRenID(Integer zhuRenID) {
		this.zhuRenID = zhuRenID;
	}

	public Teacher getZhuRenTeacher() {
		return zhuRenTeacher;
	}

	public void setZhuRenTeacher(Teacher zhuRenTeacher) {
		this.zhuRenTeacher = zhuRenTeacher;
	}

}
