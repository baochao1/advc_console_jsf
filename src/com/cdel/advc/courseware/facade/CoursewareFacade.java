package com.cdel.advc.courseware.facade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cdel.advc.course.domain.Course;
import com.cdel.advc.courseware.domain.Courseware;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.Contants;

@SuppressWarnings("serial")
@Service
public class CoursewareFacade extends BaseFacadeImpl<Courseware, Integer>
		implements Serializable {
	/**
	 * 根据courseID获取课件ID
	 * 
	 * @param courseID
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<String> getCwareByCourseID(String courseCode) {
		Map map = new HashMap();
		map.put(Contants.DATA_KEY, Contants.ACC_MAIN);
		map.put("courseCode", courseCode);
		return baseDao.findList(map, "getCwareByCourseID");
	}

	/**
	 * 查找课程下的课件
	 * 
	 * @param ids
	 *            课程ids
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Courseware> getCwareByCourseIDs(List<Course> courseList) {

		if (courseList == null || courseList.size() <= 0) {
			return new ArrayList<Courseware>();
		}

		Map map = new HashMap();
		map.put(Contants.DATA_KEY, Contants.ACC_MAIN);
		map.put("courseList", courseList);
		return this.baseDao.findList(map, "getCwareByCourseIDs");
	}

}