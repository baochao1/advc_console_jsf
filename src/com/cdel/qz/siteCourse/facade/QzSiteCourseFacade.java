package com.cdel.qz.siteCourse.facade;

import java.io.Serializable;
import java.util.List;
import org.springframework.stereotype.Service;
import com.cdel.advc.courseware.domain.Courseware;
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
public class QzSiteCourseFacade extends BaseFacadeImpl<QzSiteCourse, Integer>
		implements Serializable {

	/**
	 * 返回指定serverType课程
	 * 
	 * @param serverType
	 * @return
	 */
	public List<QzSiteCourse> getSiteCourseList(Integer serverType) {
		return baseDao.findList(serverType, "getSiteCourseList");
	}

	/**
	 * 根据课码获取对外课
	 * 
	 * @param List
	 */
	public List<QzSiteCourse> selectQzSiteCourseIDs(String s) {
		return baseDao.findList(s, "selectQzSiteCourseIDs");
	}

	/**
	 * 根据siteCourseID取对外课
	 * 
	 * @param List
	 */
	public List<QzSiteCourse> getSiteCourseByscids(String siteCourseIDs) {
		return baseDao.findList(siteCourseIDs, "getSiteCourseByscids");
	}

	/**
	 * 通过课件ID获取关联的对外课程ID
	 * 
	 * @param ccLists
	 * @return
	 */
	public List<Integer> getSiteCourseIDList(List<Courseware> ccList) {
		if (ccList == null || ccList.size() == 0) {
			return null;
		}
		return this.baseDao.findSomeList(ccList, "getSiteCourseIDList");
	}

	/**
	 * 通过课件ID获取关联的对外课程ID
	 * 
	 * @param ccLists
	 * @return
	 */
	public List<Integer> getSiteCourseIDList2(List<String> ccList) {
		if (ccList == null || ccList.size() == 0) {
			return null;
		}
		return this.baseDao.findSomeList(ccList, "getSiteCourseIDList2");
	}

}
