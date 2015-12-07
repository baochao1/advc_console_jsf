package com.cdel.advc.courseTime.action;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.course.domain.Course;
import com.cdel.advc.courseTime.domain.CourseTime;
import com.cdel.advc.courseTime.facade.CourseTimeFacade;
import com.cdel.qz.course.facade.QzCourseFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;

/**
 * 课程要求听课时长设置
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
public class CourseTimeReqAction extends BaseAction<Course> implements
		Serializable {

	@ManagedProperty(value = "#{courseTimeFacade}")
	private CourseTimeFacade courseTimeFacade;
	@ManagedProperty(value = "#{qzCourseFacade}")
	private QzCourseFacade qzCourseFacade;

	private String courseName;
	private CourseTime updateCourseTime = new CourseTime();
	private byte submitSuccess = 0;// 添加是否成功

	/**
	 * 打开设置页面
	 * 
	 * @param courseID
	 */
	public void courseTimeShow(Integer courseID) {
		updateCourseTime.setQzCourseID(courseID);
		courseName = qzCourseFacade.findByID(courseID).getCourseName();
		CourseTimeAction ca = (CourseTimeAction) this
				.getViewAction("courseTimeAction");
		ca.getNewCourseTimeList(courseID);
	}

	/**
	 * 提交添加
	 */
	public void updateSubmit(byte flag) {
		try {
			if (courseTimeFacade.checkTime(flag, updateCourseTime)) {
				if (flag == 0) {
					updateCourseTime.setStatus((short) 1);
					updateCourseTime.setCreateTime(new Date());
					courseTimeFacade.add(updateCourseTime);
					CourseTimeAction ca = (CourseTimeAction) this
							.getViewAction("courseTimeAction");
					ca.getNewCourseTimeList(ca.getQzCourseID());
				}
				submitSuccess = 1;
			}
		} catch (Exception e) {
			submitSuccess = -1;
			e.printStackTrace();
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public Map<Short, String> getStatus() {
		return Contants.status;
	}

	public void setCourseTimeFacade(CourseTimeFacade courseTimeFacade) {
		this.courseTimeFacade = courseTimeFacade;
	}

	public CourseTime getUpdateCourseTime() {
		return updateCourseTime;
	}

	public void setUpdateCourseTime(CourseTime updateCourseTime) {
		this.updateCourseTime = updateCourseTime;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setQzCourseFacade(QzCourseFacade qzCourseFacade) {
		this.qzCourseFacade = qzCourseFacade;
	}

}
