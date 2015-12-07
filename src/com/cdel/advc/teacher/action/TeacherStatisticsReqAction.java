package com.cdel.advc.teacher.action;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.teacher.domain.Teacher;
import com.cdel.advc.teacher.domain.TeacherStatistics;
import com.cdel.advc.teacher.facade.TeacherStatisticsFacade;
import com.cdel.util.BaseAction;

@ManagedBean
@SuppressWarnings("serial")
public class TeacherStatisticsReqAction extends BaseAction<Teacher> implements
		Serializable {

	@ManagedProperty(value = "#{teacherStatisticsFacade}")
	private TeacherStatisticsFacade teacherStatisticsFacade;

	private TeacherStatistics teacherStatistics;// 查询结果
	private TeacherStatistics filterTeaStatis = new TeacherStatistics();// 搜索条件

	/**
	 * 初始保存teacheerID
	 * 
	 * @param teacherID
	 */
	public void checkStat(Integer teacherID) {
		filterTeaStatis.setTeacherID(teacherID);
	}

	/**
	 * 获取教师工作统计信息
	 */
	public void search() {
		if (teacherStatisticsFacade.checkTeaStatis(filterTeaStatis)) {
			try {
				teacherStatistics = teacherStatisticsFacade
						.getTeacherStatistics(filterTeaStatis);
				this.updateComponent("statisticsForm:statisTable");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void setTeacherStatisticsFacade(
			TeacherStatisticsFacade teacherStatisticsFacade) {
		this.teacherStatisticsFacade = teacherStatisticsFacade;
	}

	public TeacherStatistics getTeacherStatistics() {
		return teacherStatistics;
	}

	public void setTeacherStatistics(TeacherStatistics teacherStatistics) {
		this.teacherStatistics = teacherStatistics;
	}

	public TeacherStatistics getFilterTeaStatis() {
		return filterTeaStatis;
	}

	public void setFilterTeaStatis(TeacherStatistics filterTeaStatis) {
		this.filterTeaStatis = filterTeaStatis;
	}

}
