package com.cdel.advc.courseTime.action;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.RowEditEvent;
import com.cdel.advc.course.domain.Course;
import com.cdel.advc.courseTime.domain.CourseTime;
import com.cdel.advc.courseTime.facade.CourseTimeFacade;
import com.cdel.util.BaseAction;

/**
 * 课程要求听课时长设置
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CourseTimeAction extends BaseAction<Course> implements
		Serializable {

	@ManagedProperty(value = "#{courseTimeFacade}")
	private CourseTimeFacade courseTimeFacade;

	private List<CourseTime> courseTimeList;
	// 当前选中的qzCourseID
	private Integer qzCourseID;

	public void getNewCourseTimeList(Integer qzCourseID) {
		CourseTime c = new CourseTime();
		this.qzCourseID = qzCourseID;
		c.setQzCourseID(qzCourseID);
		courseTimeList = courseTimeFacade.findList(c);
	}

	/**
	 * 更新行
	 * 
	 * @param event
	 */
	public void updateSubmit(RowEditEvent event) {
		CourseTime cTime = (CourseTime) this.getEditRow(event);
		try {
			if (courseTimeFacade.checkTime((byte) 1, cTime)) {
				courseTimeFacade.update(cTime);
				this.addMessage("info", SUCESSINFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
		}
	}

	public void setCourseTimeFacade(CourseTimeFacade courseTimeFacade) {
		this.courseTimeFacade = courseTimeFacade;
	}

	public List<CourseTime> getCourseTimeList() {
		return courseTimeList;
	}

	public void setCourseTimeList(List<CourseTime> courseTimeList) {
		this.courseTimeList = courseTimeList;
	}

	public Integer getQzCourseID() {
		return qzCourseID;
	}

}
