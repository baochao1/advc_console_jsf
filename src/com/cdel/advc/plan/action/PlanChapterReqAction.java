package com.cdel.advc.plan.action;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.course.facade.CourseFacade;
import com.cdel.advc.plan.domain.PlanChapter;
import com.cdel.advc.plan.domain.StudyPlan;
import com.cdel.advc.plan.facade.StudyPlanFacade;
import com.cdel.util.BaseAction;

/**
 * 
 * <p>
 * 生成单个学习计划
 * </p>
 * 
 * @author Du Haiying Create at:2013-8-22 下午5:24:21
 */
@SuppressWarnings("serial")
@ManagedBean
public class PlanChapterReqAction extends BaseAction<PlanChapter> {

	@ManagedProperty(value = "#{courseFacade}")
	private CourseFacade courseFacade;
	@ManagedProperty("#{studyPlanFacade}")
	private StudyPlanFacade studyPlanFacade;

	private byte submitSuccess = 0;// 添加是否成功

	/**
	 * 生成计划
	 * 
	 * @throws Exception
	 */
	public void generatePlan(Integer userID) {
		PlanChapterAction pa = (PlanChapterAction) this
				.getViewAction("planChapterAction");
		StudyPlan plan = pa.getPlan();
		if (plan.getClassID() == null || userID == null
				|| plan.getPlanType() == null) {
			this.addErrorMessage("info", "非法参数！");
			return;
		}
		if (plan.getStartDay() == null || plan.getEndDay() == null) {
			this.addErrorMessage("info", "起始时间和结束时间不能为空！");
			return;
		}
		if (plan.getStartDay().compareTo(plan.getEndDay()) > 0) {
			this.addErrorMessage("info", "起始时间要小于结束时间！");
			return;
		}
		plan.setUserID(userID);
		plan.setPlanCreatorID(this.getCurrentUserID());
		plan.setPlanCreatorName(this.getCurrentUserName());
		if (plan.getPlanType() == 0) {
			plan.setPreCourses("0");
		} else {
			Integer[] preCourseIDs = pa.getPreCourseIDs();
			if (preCourseIDs == null || preCourseIDs.length == 0) {
				this.addWarnMessage("info", "请选择预习课程！");
				return;
			}
			String idstring = courseFacade.getCourseIDsStr(preCourseIDs);
			if ("".equals(idstring)) {
			}
			plan.setPreCourses(idstring);
		}

		try {
			this.studyPlanFacade.addStudyPlan(plan);
			submitSuccess = 1;
		} catch (Exception e) {
			e.printStackTrace();
			this.addWarnMessage("info", e.getMessage());
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public void setCourseFacade(CourseFacade courseFacade) {
		this.courseFacade = courseFacade;
	}

	public void setStudyPlanFacade(StudyPlanFacade studyPlanFacade) {
		this.studyPlanFacade = studyPlanFacade;
	}

}
