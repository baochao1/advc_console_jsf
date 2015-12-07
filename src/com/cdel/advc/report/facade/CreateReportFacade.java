package com.cdel.advc.report.facade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdel.advc.course.domain.Course;
import com.cdel.advc.course.facade.CourseFacade;
import com.cdel.advc.coursetimeLong.domain.CoursetimeLong;
import com.cdel.advc.coursetimeLong.facade.CoursetimeLongFacade;
import com.cdel.advc.courseware.domain.Courseware;
import com.cdel.advc.courseware.facade.CoursewareFacade;
import com.cdel.advc.major.domain.Major;
import com.cdel.advc.major.facade.MajorFacade;
import com.cdel.advc.memberclass.domain.MemberClass;
import com.cdel.advc.memberclass.facade.MemberClassFacade;
import com.cdel.advc.report.domain.CwTimeLong;
import com.cdel.advc.report.domain.Report;
import com.cdel.advc.report.domain.RptCourseTime;
import com.cdel.advc.report.util.TableUtil;
import com.cdel.qz.quesError.facade.QuesErrorFacade;
import com.cdel.qz.score.center.facade.CenterScoreFacade;
import com.cdel.qz.score.point.facade.PointScoreFacade;
import com.cdel.qz.siteCourse.facade.QzSiteCourseFacade;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.DateUtil;
import com.cdel.util.StringUtil;

/**
 * <p>
 * 生成学习报告 facade层
 * </p>
 * 
 * <p>
 * 由于学习报告生成的复杂性，单独提出来
 * </p>
 * 
 * @author Du Haiying Create at:2013-8-27 下午5:10:58
 */
@SuppressWarnings("serial")
@Service
public class CreateReportFacade extends BaseFacadeImpl<Report, Integer>
		implements Serializable {

	@Autowired
	private MajorFacade majorFacade;
	@Autowired
	private MemberClassFacade memberClassFacade;
	@Autowired
	private CourseFacade courseFacade;
	@Autowired
	private CoursewareFacade coursewareFacade;
	@Autowired
	private CwTimeLongFacade cwTimeLongFacade;
	@Autowired
	private CoursetimeLongFacade coursetimeLongFacade;
	@Autowired
	private QzSiteCourseFacade qzSiteCourseFacade;
	@Autowired
	private PointScoreFacade pointScoreFacade;
	@Autowired
	private CenterScoreFacade centerScoreFacade;
	@Autowired
	private QuesErrorFacade quesErrorFacade;
	@Autowired
	private ReportFacade reportFacade;
	@Autowired
	private RptCourseTimeFacade rptCourseTimeFacade;

	/***
	 * <p>
	 * 添加学员学习报告，主要是生成，学员在某一阶段（时间）间，班级下课程学习的统计数据。
	 * </p>
	 * 
	 * 包括：
	 * <ul>
	 * <li>（课程下的）各个章节学习时长统计，包括：章节累计学习时间，章节结束时间统计</li>
	 * <li>课程累计学习时长统计（课程下所有章节时长和）</li>
	 * <li>学员 每日学习时长（分钟） 统计</li>
	 * <li>知识点测试记录 统计</li>
	 * <li>中心测试记录 统计</li>
	 * <li>错题记录 统计</li>
	 * ...
	 * </ul>
	 */
	@SuppressWarnings("unchecked")
	public void addReport(Report report, Integer siteID) throws Exception {
		Integer userID = report.getUserID();
		if (siteID == null || report == null || userID == null
				|| report.getClassID() == null || report.getStartTime() == null
				|| report.getEndTime() == null || report.getCreator() == null) {
			throw new IllegalArgumentException("非法参数！");
		}

		report.setReportYear(DateUtil.getYear(report.getStartTime()));
		report.setStatus((short) 1);
		report.setCreateTime(new Date());

		MemberClass memberClass = new MemberClass();
		memberClass.setClassID(report.getClassID());
		memberClass.setUserID(userID);
		memberClass = this.memberClassFacade.getMemberClass(memberClass);
		String userName = memberClass.getUserName();
		String studyCourses = memberClass.getStudyCourse();
		Integer[] courseIDs = StringUtil.splitIDs(studyCourses);// 所学课程

		// （学员所在班级）课程
		List<Course> courseList = this.courseFacade.findCoursesByIDs(courseIDs);
		// （学员所在班级）课程的课件
		List<Courseware> ccList = this.coursewareFacade
				.getCwareByCourseIDs(courseList);
		// 分组后的课件（课件按课程分组）
		Map<String, List<Courseware>> codeKeyMap = list2map(ccList);
		Date otherEndDate = DateUtil.getNextDay(report.getEndTime());
		// 学习时长字符串
		report.setDayTimeLong(getDayTimeLong(courseList, ccList, codeKeyMap,
				userName, report.getStartTime(), otherEndDate));

		report.setUserName(userName);
		Major major = this.majorFacade.getMajorByClassID(report.getClassID());
		int num = 1 + reportFacade.getCountByClassIDAndUserID(userID,
				report.getClassID());
		String reportTitle = "《" + major.getMajorName() + "》" + "学习报告（" + num
				+ "）";
		report.setReportTitle(reportTitle);
		reportFacade.add(report);// 添加报告主表

		// 设置 知识点测试记录等三项
		setRecord(report, courseList, ccList, userID, userName);
		// 课程章节时长
		Map<String, List<CwTimeLong>> timeMap = getChapterTimeLong(courseList,
				ccList, codeKeyMap, userName, report.getStartTime(),
				otherEndDate);
		Map<String, Object> map = TableUtil.genRptTimeLongTable(timeMap,
				report.getReportID());
		report.setTimeLongRecord((String) map.get("table"));
		reportFacade.addReportDetail(report);// 添加报告详细表
		rptCourseTimeFacade
				.addList((List<RptCourseTime>) map.get("courseTime"));
	}

	private Map<String, List<Courseware>> list2map(List<Courseware> ccList) {
		Map<String, List<Courseware>> keyMap = new HashMap<String, List<Courseware>>();
		for (int i = 0, size = ccList.size(); i < size; i++) {
			Courseware cc = ccList.get(i);
			String courseCode = cc.getCourseCode().trim();
			List<Courseware> temp = keyMap.get(courseCode);
			if (temp == null) {
				temp = new ArrayList<Courseware>();
				keyMap.put(courseCode, temp);
			}
			temp.add(cc);
		}
		return keyMap;
	}

	/**
	 * 返回 ： 统计日期开始时间， 指定学员学习时长， 以及学员平均学习时长（阶段（一段时间（报告指定））内，以天为单位）
	 */
	private String getDayTimeLong(List<Course> courseList,
			List<Courseware> ccList, Map<String, List<Courseware>> codeKeyMap,
			String userName, Date startTime, Date otherEndDate)
			throws Exception {
		Date startTimeInit = startTime;
		List<String> countDateList = new ArrayList<String>();
		while (startTime.compareTo(otherEndDate) < 0) {
			countDateList.add(DateUtil
					.getNowDateString(startTime, "yyyy-MM-dd"));
			startTime = DateUtil.getNextDay(startTime);
		}

		String avgSb = getAvgTime(countDateList, courseList);

		String userStr = getUserEveryDayTime(countDateList, ccList, userName,
				startTimeInit, otherEndDate);

		StringBuffer result = new StringBuffer(DateUtil.getNowDateString(
				startTime, "yyyy-MM-dd") + "|");
		result.append(avgSb).append("|").append(userStr);
		return result.toString();
	}

	/**
	 * 每天每人每门课的平均学习时长
	 */
	private String getAvgTime(List<String> countDateList,
			List<Course> courseList) throws Exception {

		StringBuffer avgSb = new StringBuffer();
		Map<String, CoursetimeLong> timeLongMap = coursetimeLongFacade
				.getCourseTimeLong(courseList, countDateList);
		for (String countDate : countDateList) {
			Integer timeLong = (timeLongMap.get(countDate) == null ? null
					: timeLongMap.get(countDate).getTimeLong());
			avgSb.append((timeLong == null) ? 0 : timeLong).append(",");
		}
		if (avgSb.length() > 0) {
			avgSb = avgSb.deleteCharAt(avgSb.length() - 1);
		}
		return avgSb.toString();
	}

	/**
	 * 当前学员每日学习时长
	 */
	private String getUserEveryDayTime(List<String> countDateList,
			List<Courseware> ccList, String userName, Date startTime,
			Date otherEndDate) {
		List<CwTimeLong> list = cwTimeLongFacade.getUserDayTimeLong(ccList,
				userName, startTime, otherEndDate);
		StringBuffer sb = new StringBuffer();
		for (String countDate : countDateList) {
			boolean flag = true;
			for (CwTimeLong ctime : list) {
				if (countDate.equals(DateUtil.getNowDateString(
						ctime.getStudyDate(), "yyyy-MM-dd"))) {
					sb.append(ctime.getTotalStudyTime()).append(",");
					flag = false;
					break;
				}
			}
			if (flag) {
				sb.append(0).append(",");
			}
		}
		if (sb.length() > 0) {
			sb = sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	/**
	 * 设置 知识点测试记录等三项
	 */
	private void setRecord(Report report, List<Course> courseList,
			List<Courseware> ccList, Integer userID, String userName) {
		if (courseList != null && courseList.size() > 0) {
			List<Integer> siteCourseIDList = qzSiteCourseFacade
					.getSiteCourseIDList(ccList);
			if (userID != null && siteCourseIDList != null
					&& siteCourseIDList.size() > 0) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userID", userID);
				map.put("startDate", report.getStartTime());
				map.put("endDate", report.getEndTime());
				map.put("siteCourseIDList", siteCourseIDList);

				report.setPointRecord(TableUtil
						.genRptPointTable(pointScoreFacade
								.getRptPointStatList(map)));
				report.setCenterRecord(TableUtil
						.genRptCenterTable(centerScoreFacade
								.getRptCenterList(map)));
				report.setErrorRecord(TableUtil
						.genRptErrorTable(quesErrorFacade.getRptErrorList(map)));
			} else {
				report.setPointRecord(TableUtil.genRptPointTable(null));
				report.setCenterRecord(TableUtil.genRptCenterTable(null));
				report.setErrorRecord(TableUtil.genRptErrorTable(null));
			}
		}
	}

	/**
	 * 通过课件得到 课程章节时长
	 */
	private Map<String, List<CwTimeLong>> getChapterTimeLong(
			List<Course> courseList, List<Courseware> ccList,
			Map<String, List<Courseware>> codeKeyMap, String userName,
			Date startTime, Date otherEndDate) {
		Map<String, List<CwTimeLong>> timeMap = new HashMap<String, List<CwTimeLong>>();

		for (int i = 0; i < courseList.size(); i++) {
			Course course = courseList.get(i);
			String courseCode = course.getCourseCode().trim();
			String courseName = course.getCourseName();

			List<Courseware> ccwareList = codeKeyMap.get(courseCode);
			List<CwTimeLong> timeList = cwTimeLongFacade.getChapterTimeLong(
					ccwareList, userName, startTime, otherEndDate);

			timeMap.put(courseName, timeList);
		}
		return timeMap;
	}

}