package com.cdel.advc.gdb.member.facade;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdel.advc.course.domain.Course;
import com.cdel.advc.course.facade.CourseFacade;
import com.cdel.advc.gdb.member.domain.AdvanceMember;
import com.cdel.qz.siteCourse.domain.QzSiteCourse;
import com.cdel.qz.siteCourse.facade.QzSiteCourseFacade;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.Contants;

/**
 * 
 * 高端班学员相关业务实现
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@Service
public class AdvanceMemberFacade extends BaseFacadeImpl<AdvanceMember, Integer>
		implements Serializable {
	@Autowired
	private CourseFacade courseFacade;
	@Autowired
	private QzSiteCourseFacade qzSiteCourseFacade;

	/**
	 * 删除教师和高端班学员的关系
	 * 
	 * @param teacherID
	 */
	public void deleteTeacherMember(Integer teacherID) {

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("teacherID", teacherID);

		baseDao.update(map, "deleteTeacherMember");
	}

	/**
	 * 查找某个学员报的课程
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<String> selectAdvanceCourse(Map m) {
		return baseDao.findList(m, "selectAdvanceCourse");
	}

	/**
	 * 同步高端班学员
	 */
	public void getRemoteAdvanceMember() throws Exception {
		String courseCodes = advcAdvanceCourse(courseFacade
				.getAdvcAdvanceCourse());
		// 查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Contants.DATA_KEY, Contants.ACC_MAIN);
		map.put("courseIDs", courseCodes);
		List<AdvanceMember> advanceList = baseDao.findList(map,
				"getAllAdvanceMember");
		StringBuffer allUserIDs = new StringBuffer("");
		if (advanceList != null && advanceList.size() > 0) {
			Map<String, Object> mapNew = new HashMap<String, Object>();
			mapNew.put("courseIDs", courseCodes);
			for (int i = 0; i < advanceList.size(); i++) {
				mapNew.put(Contants.DATA_KEY, Contants.ACC_MAIN);
				AdvanceMember sb = advanceList.get(i);
				allUserIDs.append(sb.getUserID()).append(",");// 把高端班学员的userID放到一起
				mapNew.put("userID", sb.getUserID());
				// 查找每个人的报课情况
				List<String> courses = selectAdvanceCourse(mapNew);
				mapNew.put("courses", courses);
				// 在教务库中查找课程以及辅导ID
				mapNew.put(Contants.DATA_KEY, null);
				List<Course> advccourses = courseFacade
						.getAdvanceCourse(mapNew);
				if (advccourses != null && advccourses.size() > 0) {
					Integer majorID = advccourses.get(0).getMajorID();
					String studycourse = advccourses.get(0).getCourseCode();
					if (advccourses.size() > 1) {
						for (int j = 1; j < courses.size(); j++) {
							if (advccourses.get(j).getMajorID().intValue() == majorID) {
								studycourse = studycourse + ","
										+ advccourses.get(j).getCourseCode();
								// 如果迭代到了最后，就执行
								if (courses.size() - j == 1) {
									sb.setMajorID(majorID);
									sb.setStudyCourse(studycourse);
									this.dataInsert(sb);
								}
							} else {
								// 插入上一个majorID相应的内容
								sb.setMajorID(majorID);
								sb.setStudyCourse(studycourse);
								this.dataInsert(sb);
								majorID = advccourses.get(j).getMajorID();
								studycourse = advccourses.get(j)
										.getCourseCode();
								// 如果迭代到了最后就执行
								if (courses.size() - j == 1) {
									this.dataInsert(sb);
								}
							}
						}
					} else {
						sb.setStudyCourse(studycourse);
						sb.setMajorID(majorID);
						this.dataInsert(sb);
					}
				}
			}
		}
		// 去掉已经退了班的学员
		if (advanceList != null && advanceList.size() > 0) {
			// 目前所有的一起处理
			if (!allUserIDs.equals("")) {
				map.put("allUserIDs",
						allUserIDs.substring(0, allUserIDs.length() - 1));
				this.delete(map);
			}
		}
	}

	/**
	 * 客户同步
	 */
	public void getRemoteAdvanceMemberForKF() throws Exception {
		String courseCodes = advcAdvanceCourse(courseFacade
				.getAdvcAdvanceCourse());
		// 查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Contants.DATA_KEY, Contants.ACC_MAIN);
		map.put("courseIDs", courseCodes);
		List<AdvanceMember> advanceList = baseDao.findList(map,
				"getAllAdvanceMember");
		StringBuffer allUserIDs = new StringBuffer("");
		if (advanceList != null && advanceList.size() > 0) {
			Map<String, Object> mapNew = new HashMap<String, Object>();
			mapNew.put("courseIDs", courseCodes);
			for (int i = 0; i < advanceList.size(); i++) {
				mapNew.put(Contants.DATA_KEY, Contants.ACC_MAIN);
				AdvanceMember sb = advanceList.get(i);
				sb.setSuccessType(Short.valueOf("1"));// 初始同步成功
				allUserIDs.append(sb.getUserID()).append(",");// 把高端班学员的userID放到一起
				mapNew.put("userID", sb.getUserID());
				// 查找每个人的报课情况
				List<String> courses = selectAdvanceCourse(mapNew);
				mapNew.put("courses", courses);
				// 在教务库中查找课程以及辅导ID
				mapNew.put(Contants.DATA_KEY, null);
				List<Course> advccourses = courseFacade
						.getAdvanceCourse(mapNew);
				if (advccourses != null && advccourses.size() > 0) {
					Integer majorID = advccourses.get(0).getMajorID();
					String studycourse = advccourses.get(0).getCourseCode();
					if (advccourses.size() > 1) {
						for (int j = 1; j < courses.size(); j++) {
							if (advccourses.get(j).getMajorID().intValue() == majorID) {
								sb.setMajorID(majorID);
								studycourse = studycourse + ","
										+ advccourses.get(j).getCourseCode();
								sb.setStudyCourse(studycourse);
								// 如果迭代到了最后，就执行
								if (courses.size() - j == 1) {
									this.dataInsert(sb);
								}
							} else {
								// 插入上一个majorID相应的内容
								this.dataInsert(sb);
								majorID = advccourses.get(j).getMajorID();
								studycourse = advccourses.get(j)
										.getCourseCode();
								// 如果迭代到了最后就执行
								if (courses.size() - j == 1) {
									this.dataInsert(sb);
								}
							}
						}
					} else {
						sb.setStudyCourse(studycourse);
						sb.setMajorID(majorID);
						this.dataInsert(sb);
					}
				}
			}
		}
		if (advanceList != null && advanceList.size() > 0) {
			// 目前所有的一起处理
			if (!allUserIDs.equals("")) {
				map.put("allUserIDs",
						allUserIDs.substring(0, allUserIDs.length() - 1));
				this.delete(map);
			}
		}
	}

	public void dataInsert(AdvanceMember advanceMember) throws Exception {
		Integer advanceMemberCount = this.baseDao.get(advanceMember,
				"getAdvanceMemberCount");
		if (advanceMemberCount == 0) {// 不为0说明已经有符合的数据了
			// 处理studyCourse
			String studyCourse = advanceMember.getStudyCourse();
			if (!StringUtils.isBlank(studyCourse) && studyCourse.length() > 0) {
				studyCourse = studyCourse.replaceAll(",", "','");
				studyCourse = "'" + studyCourse + "'";
				List<QzSiteCourse> list = qzSiteCourseFacade
						.selectQzSiteCourseIDs(studyCourse);
				if (list != null && list.size() > 0) {
					String siteCourseIds = "";
					for (int k = 0; k < list.size(); k++) {
						siteCourseIds += list.get(k).getSiteCourseID() + ",";
					}
					siteCourseIds = siteCourseIds.substring(0,
							siteCourseIds.length() - 1);
					advanceMember.setSiteCourseIds(siteCourseIds);
					this.add(advanceMember);
				}
			}
		}
	}

	public String advcAdvanceCourse(List<String> courses) {
		String courseCodes = "'";
		if (courses != null && courses.size() > 0) {
			for (String code : courses) {
				courseCodes += code + "','";
			}
			courseCodes = courseCodes.substring(0, courseCodes.length() - 2);
		}
		if (courseCodes.equals("'")) {
			courseCodes = Contants.ADVC_ADVANCE_COURSE;
		}
		return courseCodes;
	}

	/**
	 * 学员退班
	 * 
	 * @param advanceMember
	 */
	public void updateAdvanceMemberStatus(AdvanceMember advanceMember) {
		this.baseDao.update(advanceMember, "updateAdvanceMemberStatus");
	}

}