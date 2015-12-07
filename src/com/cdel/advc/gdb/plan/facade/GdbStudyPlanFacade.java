package com.cdel.advc.gdb.plan.facade;

import java.io.Serializable;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.cdel.advc.gdb.plan.domain.GdbStudyPlan;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.CheckUtil;

/**
 * 
 * 高端班学习计划业务
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@Service
public class GdbStudyPlanFacade extends BaseFacadeImpl<GdbStudyPlan, Integer>
		implements Serializable {
	public boolean check(byte flag, GdbStudyPlan uGdbStudyPlan) {
		if (flag == 0) {
			if (uGdbStudyPlan.getSiteCourseID() == null) {
				return addWarnMessage("所属课程不能为空！");
			}
			if (uGdbStudyPlan.getWeekNum() == null) {
				return addWarnMessage("计划周次不能为空！");
			}
			if (uGdbStudyPlan.getStartDate() == null) {
				return addWarnMessage("开始时间不能为空！");
			}
			if (uGdbStudyPlan.getStatus() == null) {
				return addWarnMessage("状态不能为空！");
			}
			Float studyHours = uGdbStudyPlan.getStudyHours();

			if (studyHours == null || studyHours <= 0
					|| !CheckUtil.checkFloat(studyHours.toString(), 5, 2)) {
				return this.addWarnMessage("info", "建议时长必须是大于0的数字！");
			}
		}
		if (uGdbStudyPlan.getStartDate() == null) {
			return addWarnMessage("开始时间不能为空！");
		}
		if (uGdbStudyPlan.getStudyHours() == null) {
			return addWarnMessage("建议时长不能为空！");
		}
		if (StringUtils.isBlank(uGdbStudyPlan.getStudyContent())) {
			return addWarnMessage("学习要求不能为空！");
		}
		if (StringUtils.isBlank(uGdbStudyPlan.getStudyMethods())) {
			return addWarnMessage("学习方法不能为空！");
		}
		return true;
	}

}
