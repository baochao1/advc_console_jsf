package com.cdel.advc.teacher.facade;

import org.springframework.stereotype.Service;
import com.cdel.advc.teacher.domain.TeacherStatistics;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class TeacherStatisticsFacade extends
		BaseFacadeImpl<TeacherStatistics, Integer> {
	/**
	 * 验证教师统计条件
	 * 
	 * @param msg
	 * @param context
	 * @param teacher
	 * @return
	 */
	public boolean checkTeaStatis(TeacherStatistics TeacherStatistics) {
		if (TeacherStatistics.getTeacherID() == null) {
			return addWarnMessage("info", "teacherID不能为空！");
		}
		if (TeacherStatistics.getStartDate() == null) {
			return addWarnMessage("info", "请输入开始时间");
		}
		if (TeacherStatistics.getEndDate() == null) {
			return addWarnMessage("info", "请输入结束时间");
		}
		return true;
	}

	/**
	 * 获取教师的统计信息
	 * 
	 * @param teacherStatistics
	 * @return
	 */
	public TeacherStatistics getTeacherStatistics(TeacherStatistics teacherID) {
		return baseDao.get(teacherID, "getTeacherStatisticses");
	}

}
