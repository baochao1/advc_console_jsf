package com.cdel.advc.coursetimeLong.facade;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdel.advc.course.domain.Course;
import com.cdel.advc.course.facade.CourseFacade;
import com.cdel.advc.coursetimeLong.domain.CoursetimeLong;
import com.cdel.advc.courseware.facade.CoursewareFacade;
import com.cdel.advc.report.facade.CwTimeLongFacade;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class CoursetimeLongFacade extends
		BaseFacadeImpl<CoursetimeLong, Integer> implements Serializable {
	@Autowired
	private CoursewareFacade coursewareFacade;
	@Autowired
	private CwTimeLongFacade cwTimeLongFacade;
	@Autowired
	private CourseFacade courseFacade;

	/**
	 * 重新获取平均值
	 * 
	 * @param courseID
	 * @param countDate
	 * @return
	 */
	public Integer getAvgTimeLong(Integer courseID, String countDate) {
		Course course = courseFacade.findByID(courseID);
		List<String> cwIDList = coursewareFacade.getCwareByCourseID(course
				.getCourseCode());
		return cwTimeLongFacade.getAvgTime(cwIDList, countDate);
	}

	/**
	 * 按指定多个日期，指定多个课程查找， 以日期为key，日期对应的多个课程平均时长为value返回
	 */
	@SuppressWarnings("unchecked")
	public Map<String, CoursetimeLong> getCourseTimeLong(
			List<Course> courseList, List<String> countDateList) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("course", courseList);
		map.put("countDate", countDateList);
		return this.baseDao.findMap(map, "getCourseTimeLongSet", "countDate");
	}

}