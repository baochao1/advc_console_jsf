package com.cdel.advc.course.action;

import java.io.Serializable;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UISelectItems;
import com.cdel.advc.course.domain.Course;
import com.cdel.advc.course.facade.CourseFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;

/**
 * 
 * 课程Bean
 * 
 * @author Haiying Du
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
public class CourseReqAction extends BaseAction<Course> implements Serializable {
	@ManagedProperty(value = "#{courseFacade}")
	private CourseFacade courseFacade;

	private Course showCourse = new Course();// 课程对象
	private UISelectItems preCourseSelectItems; // 预习课程选项对象
	private byte submitSuccess = 0;// 添加是否成功

	/**
	 * 修改状态
	 */
	public void changeStatus(Integer courseID, String courseCode, Integer status) {
		CourseAction courseAction = (CourseAction) this
				.getViewAction("courseAction");
		showCourse.setCourseID(courseID);
		Short newStatus;
		if (status == 1) {
			newStatus = 0;
		} else {
			newStatus = 1;
		}
		showCourse.setStatus(newStatus);
		if (newStatus == 0) {
			try {
				courseFacade.update(showCourse);
				courseAction.search4U();
			} catch (Exception e) {
				e.printStackTrace();
				this.addErrorMessage("info", FAILINFO);
			}
		} else {
			showCourse.setCourseCode(courseCode);
			if (courseFacade.checkCourse((byte) 3, showCourse,
					courseAction.getSiteID())) {
				try {
					courseFacade.update(showCourse);
					courseAction.search4U();
				} catch (Exception e) {
					e.printStackTrace();
					this.addErrorMessage("info", FAILINFO);
				}
			}
		}
		this.addMessage("info", SUCESSINFO);
	}

	/**
	 * 查看
	 */
	public void show(Integer courseID) {
		showCourse = courseFacade.getCourseByCourseID(courseID);
	}

	/**
	 * 打开预习课程关联页面
	 * 
	 * @param courseID
	 */
	public void updatePre(Integer courseID, Integer majorID) {
		showCourse.setIsPre(new Short("1"));
		showCourse.setMajorID(majorID);
		showCourse.setCourseID(courseID);
		preCourseSelectItems.setValue(courseFacade
				.getCourseListByMajor(showCourse));
		showCourse = courseFacade.findByID(courseID);
	}

	/**
	 * 关联预习课程
	 */
	public void addPreSubmit() {
		try {
			courseFacade.update(showCourse);
			submitSuccess = 1;
			this.updateComponent("searchForm:courseList");
		} catch (Exception e) {
			submitSuccess = -1;
			e.printStackTrace();
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public void setCourseFacade(CourseFacade courseFacade) {
		this.courseFacade = courseFacade;
	}

	public Course getShowCourse() {
		return showCourse;
	}

	public void setShowCourse(Course showCourse) {
		this.showCourse = showCourse;
	}

	public Integer[] getYears() {
		return Contants.years;
	}

	public Map<Short, String> getCourseTypeMap() {
		return Contants.courseTypeMap;
	}

	public Map<Integer, String> getIsPreMap() {
		return Contants.isPreMap;
	}

	public Map<Short, String> getFaceTypeMap() {
		return Contants.yesorno;
	}

	public Map<Short, String> getStatus() {
		return Contants.status;
	}

	public Map<Short, String> getIsMemberMsgOpen() {
		return Contants.yesorno;
	}

	public UISelectItems getPreCourseSelectItems() {
		return preCourseSelectItems;
	}

	public void setPreCourseSelectItems(UISelectItems preCourseSelectItems) {
		this.preCourseSelectItems = preCourseSelectItems;
	}

}
