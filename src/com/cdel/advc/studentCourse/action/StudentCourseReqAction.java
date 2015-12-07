package com.cdel.advc.studentCourse.action;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.studentCourse.domain.BuyCourse;
import com.cdel.advc.studentCourse.domain.StudentCourse;
import com.cdel.advc.studentCourse.facade.StudentCourseFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;

@SuppressWarnings("serial")
@ManagedBean
public class StudentCourseReqAction extends BaseAction<StudentCourse> implements
		Serializable {
	@ManagedProperty(value = "#{studentCourseFacade}")
	private StudentCourseFacade studentCourseFacade;
	private StudentCourse searchStudentCourse = new StudentCourse();// 搜索条件
	private List<BuyCourse> buyCourseList;

	/**
	 * 查询
	 * 
	 * @throws Exception
	 */
	public void search() {
		try {
			buyCourseList = studentCourseFacade
					.studentBuyCourse(searchStudentCourse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.updateComponent("searchForm:buyCourseList");
	}

	public void setStudentCourseFacade(StudentCourseFacade studentCourseFacade) {
		this.studentCourseFacade = studentCourseFacade;
	}

	public StudentCourse getSearchStudentCourse() {
		return searchStudentCourse;
	}

	public void setSearchStudentCourse(StudentCourse searchStudentCourse) {
		this.searchStudentCourse = searchStudentCourse;
	}

	public List<BuyCourse> getBuyCourseList() {
		return buyCourseList;
	}

	public void setBuyCourseList(List<BuyCourse> buyCourseList) {
		this.buyCourseList = buyCourseList;
	}

	public Map<Short, String> getSiteType() {
		return Contants.siteType;
	}

}