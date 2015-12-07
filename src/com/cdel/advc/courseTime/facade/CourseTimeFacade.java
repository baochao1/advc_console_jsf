package com.cdel.advc.courseTime.facade;

import org.springframework.stereotype.Service;
import com.cdel.advc.courseTime.domain.CourseTime;
import com.cdel.util.BaseFacadeImpl;

/**
 * 课程要求听课时长设置业务层
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@Service
public class CourseTimeFacade extends BaseFacadeImpl<CourseTime, Integer> {

	public boolean checkTime(byte flag, CourseTime updateCourseTime) {
		if (flag == 0) {
			if (updateCourseTime.getStageID() == null) {
				return addWarnMessage("必须选择阶段！");
			}
			if (updateCourseTime.getQzCourseID() == null) {
				return addWarnMessage("必须选择课程！");
			}
		}
		if (updateCourseTime.getScheduleTime() == null
				|| updateCourseTime.getScheduleTime() < 0
				|| updateCourseTime.getScheduleTime() > 100000) {
			return addWarnMessage("课件时长不能为空且大于0小于100000！");
		}
		// 验证是否存在
		if (flag == 0 || (flag == 1 && updateCourseTime.getStatus() == 1)) {
			if (hasSameTime(updateCourseTime) > 0) {
				if (flag == 0)
					return addWarnMessage("已经添加过该课程、阶段的课件时长！");
				else
					return addWarnMessage("info", "已经添加过该课程、阶段的课件时长！");
			}
		}
		return true;
	}

	public int hasSameTime(CourseTime updateCourseTime) {
		return (Integer) this.baseDao.get(updateCourseTime, "hasSameTime");
	}

}
