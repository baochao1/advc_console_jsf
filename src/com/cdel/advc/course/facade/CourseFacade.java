package com.cdel.advc.course.facade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdel.advc.combo.domain.Combo;
import com.cdel.advc.combo.facade.ComboFacade;
import com.cdel.advc.course.domain.Course;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.StringUtil;

/**
 * 
 * 课程相关业务实现
 * 
 * @author Haiying Du
 * 
 */
@SuppressWarnings("serial")
@Service
public class CourseFacade extends BaseFacadeImpl<Course, Integer> implements
		Serializable {
	@Autowired
	private ComboFacade comboFacade;

	/**
	 * 根据课程名称返回课程
	 * 
	 * @param courseName
	 * @return
	 */
	public List<Course> getCourseByCourseName(String courseName) {
		return baseDao.findList(courseName, "getCourseByCourseName");
	}

	/**
	 * 根据courseID获取Course
	 * 
	 * @param c
	 * @return
	 */
	public Course getCourseByCourseID(Integer courseID) {
		return baseDao.getByID(courseID, "getCourseByCourseID");
	}

	/**
	 * 判断是否有重复的课程代码
	 * 
	 * @param courseCode
	 * @return
	 */
	public List<Course> checkCourseCode(Course course) {
		course.setStatus(Short.valueOf("1"));
		return baseDao.findList(course, "checkCourseCode");
	}

	/**
	 * 根据辅导信息获取课程
	 * 
	 * @param course
	 * @return
	 */
	public List<Course> getCourseListByMajor(Course course) {
		return baseDao.findList(course, "getCourseListByMajor");
	}

	/**
	 * 根据courseIDs获取课程
	 * 
	 * @param course
	 * @return 只包括id,name属性
	 */
	public List<Course> getCourseByCourseIDs(Integer[] ids) {
		if (ids == null || ids.length <= 0) {
			return null;
		}
		return baseDao.findListByIDs(ids, "getCourseByCourseIDs");
	}

	/**
	 * 获取课程代码
	 * 
	 * @return
	 */
	public List<String> getAdvcAdvanceCourse() {
		Integer courseType = 5;
		return baseDao.findSomeList(courseType, "getAdvcAdvanceCourse");
	}

	/**
	 * 根据班级策略ID 查询班级策略下的课程列表
	 * 
	 * 结果中暂只包含ID和名称
	 * 
	 * @param strategyID
	 *            策略ID
	 * @return
	 */
	public List<Course> findCoursesByStrategyID(Integer strategyID) {
		return baseDao.findList(strategyID, "getCoursesByStrategyID");
	}

	/**
	 * 根据课程id数组查找课程列表
	 * 
	 * @param ids
	 */
	public List<Course> findCoursesByIDs(Integer[] ids) {
		if (ids == null || ids.length <= 0) {
			return null;
		}
		return baseDao.findList(ids, "findCoursesByIDs");
	}

	/**
	 * 在教务库中查找课程以及辅导ID
	 * 
	 * @param ids
	 */
	public List<Course> getAdvanceCourse(Map<String, Object> map) {
		return baseDao.findList(map, "getAdvanceCourse");
	}

	/**
	 * 验证课程的输入信息
	 * 
	 * @param msg
	 * @param context
	 * @param course
	 * @param siteID
	 * @return
	 */
	public boolean checkCourse(byte flag, Course course, Integer siteID) {
		if (flag != 3) {
			if (course.getMajorID() == null) {
				return addWarnMessage("辅导名称不能为空！");
			}
			if (StringUtils.isBlank(course.getCourseName())) {
				return addWarnMessage("课程名称不能为空！");
			}
			if (course.getCourseType() == null) {
				return addWarnMessage("课程类别不能为空！");
			}
			if (StringUtils.isBlank(course.getCourseCode())) {
				return addWarnMessage("课程代码不能为空！");
			}
			if (course.getCourseYear() == null) {
				return addWarnMessage("课程年份不能为空！");
			}
			if (course.getIsPre() == null) {
				return addWarnMessage("是否预习课程不能为空！");
			}
			if (StringUtils.isBlank(course.getCourseSmsName())) {
				return addWarnMessage("短信课程名不能为空！");
			}
		}
		Combo combo = new Combo();
		combo.setSiteID(siteID);
		combo.setComboCode(course.getCourseCode());
		int count = comboFacade.judgeComboCodeInUpdate(combo);
		if (count > 0) {
			if (flag != 3) {
				return addWarnMessage("本网站已存在代码为" + course.getCourseCode()
						+ "的有效套餐，请设为无效后再添加！");
			} else {
				return addWarnMessage("info",
						"本网站已存在代码为" + course.getCourseCode()
								+ "的有效套餐，请设为无效后再添加！");
			}
		}
		course.setSiteID(siteID);
		List<Course> list = checkCourseCode(course);
		if (list != null && list.size() > 0) {
			if (flag != 3) {
				return addWarnMessage("本网站已存在代码为" + course.getCourseCode()
						+ "的有效课程，请设为无效后再添加！");
			} else {
				return addWarnMessage("info",
						"本网站已存在代码为" + course.getCourseCode()
								+ "的有效课程，请设为无效后再添加！");
			}
		}
		return true;
	}

	/**
	 * 查找课程的预习课程列表
	 * 
	 * @param ids
	 * @return
	 */
	public List<Course> getPreCoursesByCourseIDs(Integer[] ids) {
		if (ids != null && ids.length > 0) {
			List<Course> courses = this.baseDao.findList(ids,
					"getPreCoursesByCourseIDs");
			int size = 0;
			List<Integer> preIDs = null;
			if (courses != null && (size = courses.size()) > 0) {
				preIDs = new ArrayList<Integer>(size);
				for (int i = 0; i < size; i++) {
					preIDs.add(courses.get(i).getCourseID());
				}
			}
			return courses;
		} else {
			return null;
		}
	}

	/**
	 * 查找课程的预习课程IDs
	 * 
	 * @param studyCourse
	 * @return
	 */
	public String getPreCoursesByCourseIDs(String studyCourse) {
		if (StringUtils.isBlank(studyCourse)) {
			return null;
		} else {
			List<Course> cList = getPreCoursesByCourseIDs(StringUtil
					.splitIDs(studyCourse));
			StringBuffer sb = new StringBuffer("");
			for (Course c : cList) {
				sb.append(c.getCourseID()).append(",");
			}
			if (sb.length() > 0) {
				sb.substring(0, sb.length() - 1);
			}
			return sb.toString();
		}
	}

	/**
	 * 查找选中的课程IDs
	 * 
	 * @return
	 */
	public String getCourseIDsStr(Integer[] courseIDs) {
		String idsStr = "";
		int i = 0;
		if (courseIDs != null && courseIDs.length > 0) {
			for (Integer courseID : courseIDs) {
				if (i == 0) {
					idsStr = courseID.toString();
				} else {
					idsStr = idsStr + "," + courseID;
				}
				i++;
			}
		}
		return idsStr;
	}

	/**
	 * 查找课程IDs
	 * 
	 * @return
	 */
	public String getCourseIDsStr(List<Course> courses) {
		String idsStr = "";
		int i = 0;
		if (courses != null && courses.size() > 0) {
			for (Course c : courses) {
				if (i == 0) {
					idsStr = c.getCourseID().toString();
				} else {
					idsStr = idsStr + "," + c.getCourseID();
				}
				i++;
			}
		}
		return idsStr;
	}

	/**
	 * 查找课程IDs
	 * 
	 * @return
	 */
	public Integer[] getCourseIDsArr(List<Course> courses) {
		if (courses != null && courses.size() > 0) {
			Integer[] courseIDs = new Integer[courses.size()];
			int i = 0;
			for (Course c : courses) {
				courseIDs[i++] = c.getCourseID();
			}
			return courseIDs;
		} else {
			return null;
		}
	}

	/**
	 * 根据课程ID获取辅导ID
	 * 
	 * @param courseID
	 * @return
	 */
	public Integer getMajorIDByCourseID(Integer courseID) {
		return baseDao.get(courseID, "getMajorIDByCourseID");
	}

	public List<Course> getCourseListByComboID(Course course) {
		return baseDao.findList(course, "getCourseListByComboID");
	}

}