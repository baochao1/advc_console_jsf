package com.cdel.qz.course.action;

import java.io.Serializable;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.course.domain.Course;
import com.cdel.qz.course.facade.QzCourseFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;

/**
 * 教务课程action
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
public class QzCourseReqAction extends BaseAction<Course> implements
		Serializable {

	@ManagedProperty(value = "#{qzCourseFacade}")
	private QzCourseFacade qzCourseFacade;

	public Map<Short, String> getStatus() {
		return Contants.status;
	}

	public void setQzCourseFacade(QzCourseFacade qzCourseFacade) {
		this.qzCourseFacade = qzCourseFacade;
	}

}
