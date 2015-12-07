package com.cdel.advc.record.facade;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdel.advc.course.domain.Course;
import com.cdel.advc.course.facade.CourseFacade;
import com.cdel.advc.courseware.facade.CoursewareFacade;
import com.cdel.advc.report.domain.CwTimeLong;
import com.cdel.advc.report.facade.CwTimeLongFacade;
import com.cdel.qz.score.center.domain.CenterScore;
import com.cdel.qz.score.center.facade.CenterScoreFacade;
import com.cdel.qz.score.point.domain.PointScore;
import com.cdel.qz.score.point.facade.PointScoreFacade;
import com.cdel.qz.siteCourse.facade.QzSiteCourseFacade;
import com.cdel.util.BaseFacadeImpl;
import com.chnedu.plat.rad.domain.BaseDomain;

@SuppressWarnings("serial")
@Service
public class RecordFacade extends BaseFacadeImpl<BaseDomain, Integer> implements
		Serializable {
	@Autowired
	private QzSiteCourseFacade qzSiteCourseFacade;
	@Autowired
	private PointScoreFacade pointScoreFacade;
	@Autowired
	private CenterScoreFacade centerScoreFacade;
	@Autowired
	private CoursewareFacade coursewareFacade;
	@Autowired
	private CwTimeLongFacade cwTimeLongFacade;
	@Autowired
	private CourseFacade courseFacade;

	/**
	 * 检查实验班课程是否找到过对应的题库对外课，如果没有就插入
	 * 
	 * @param siteCourseMap
	 * @param selectCourseID
	 */
	public void checkMap(Map<Integer, List<String>> cwIDsMap,
			Map<Integer, List<Integer>> siteCourseMap, Integer selectCourseID) {
		if (siteCourseMap != null && siteCourseMap.get(selectCourseID) == null) {
			Course course = courseFacade.findByID(selectCourseID);
			List<String> cwIDs = coursewareFacade.getCwareByCourseID(course
					.getCourseCode());
			cwIDsMap.put(selectCourseID, cwIDs);
			siteCourseMap.put(selectCourseID,
					qzSiteCourseFacade.getSiteCourseIDList2(cwIDs));
		}
	}

	/**
	 * 获取知识点测试列表
	 * 
	 * @param userID
	 * @param siteCourseIDList
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<PointScore> showList(Integer userID,
			List<Integer> siteCourseIDList, Date startDate, Date endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userID", userID);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("siteCourseIDList", siteCourseIDList);
		return pointScoreFacade.getRptPointList(map);
	}

	/**
	 * 获取考试记录
	 * 
	 * @param userID
	 * @param siteCourseIDList
	 * @return
	 */
	public List<CenterScore> showList(Integer userID,
			List<Integer> siteCourseIDList) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userID", userID);
		map.put("siteCourseIDList", siteCourseIDList);
		return centerScoreFacade.getRptCenterList(map);
	}

	/**
	 * 获取听课详细信息
	 * 
	 * @param userID
	 * @param cwIDs
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public LazyDataModel<CwTimeLong> showList(String userName,
			List<String> cwIDs, Date startDate, Date endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
		map.put("startTime", startDate);
		map.put("endTime", endDate);
		map.put("cwIDs", cwIDs);
		return cwTimeLongFacade.findPage(map);
	}

}