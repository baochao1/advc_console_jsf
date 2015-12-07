package com.cdel.qz.course.facade;

import java.io.Serializable;
import java.util.List;
import org.springframework.stereotype.Service;
import com.cdel.qz.course.domain.QzCourse;
import com.cdel.qz.siteCourse.domain.QzSiteCourse;
import com.cdel.util.BaseFacadeImpl;

/**
 * 
 * 课程相关业务实现
 * 
 * @author Haiying Du
 * 
 */
@SuppressWarnings("serial")
@Service
public class QzCourseFacade extends BaseFacadeImpl<QzCourse, Integer> implements
		Serializable {
	/**
	 * 获取siteCourseName
	 * 
	 * @param siteCourseList
	 * @param siteCourseID
	 * @return
	 */
	public String getSiteCourseName(List<QzSiteCourse> siteCourseList,
			Integer siteCourseID) {
		String name = "";
		if (siteCourseList != null && siteCourseID != null) {
			for (QzSiteCourse c : siteCourseList) {
				if (siteCourseID.intValue() == c.getSiteCourseID()) {
					name = c.getSiteCourseName();
					break;
				}
			}
		}
		return name;
	}
}
