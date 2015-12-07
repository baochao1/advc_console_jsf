package com.cdel.advc.course.action;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UISelectOne;
import javax.faces.event.AjaxBehaviorEvent;
import com.cdel.advc.course.domain.Course;
import com.cdel.advc.course.facade.CourseFacade;

/**
 * 课程action
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CourseOther implements Serializable {
	@ManagedProperty(value = "#{courseFacade}")
	private CourseFacade courseFacade;

	private List<Course> courseList;

	public List<Course> getCourseList() {
		return courseList;
	}

	public void changeMajorID(AjaxBehaviorEvent e) {
		Integer majorID = (Integer) ((UISelectOne) e.getSource()).getValue();
		courseList = courseFacade.findList(majorID);
	}

	public void setCourseFacade(CourseFacade courseFacade) {
		this.courseFacade = courseFacade;
	}

}
