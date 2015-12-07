/*
 * @Title: StudyPlanAction.java
 * @Package com.cdel.advc.plan.action
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-8-1 下午5:19:10
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-8-1                          
 */
package com.cdel.advc.plan.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.plan.domain.StudyPlan;
import com.cdel.advc.plan.facade.StudyPlanFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;

/**
 * <p>
 * 班级成员管理，批量生成学习计划
 * </p>
 * 
 * @author zhangsulei
 */
@SuppressWarnings("serial")
@ManagedBean
public class StudyPlanReqAction extends BaseAction<StudyPlan> {

	@ManagedProperty("#{studyPlanFacade}")
	private StudyPlanFacade studyPlanFacade;

	private List<StudyPlan> selectPlans;
	private List<String> messages;

	/**
	 * 前提检查
	 */
	public void checkGenPlans() {
		if (selectPlans == null || selectPlans.size() == 0) {
			this.addWarnMessage("info", "请选择学员！");
			return;
		} else {
			this.executeScript("cd.show()");
		}
	}

	/**
	 * 批量生成学习计划
	 * 
	 * @throws Exception
	 */
	public void generatePlans() throws Exception {
		if (this.selectPlans != null && this.selectPlans.size() > 0) {
			StudyPlanAction sa = (StudyPlanAction) this
					.getViewAction("studyPlanAction");
			StudyPlan plan = sa.getPlan();
			if (plan == null || plan.getClassID() == null
					|| plan.getPlanType() == null) {
				this.addWarnMessage("info", "非法参数！");
				return;
			}
			this.messages = new ArrayList<String>();
			this.messages.add("<font style='color:red'>共查找到"
					+ selectPlans.size() + "个学员，开始生成...</font>");
			plan.setPlanCreatorID(this.getCurrentUserID());
			plan.setPlanCreatorName(this.getCurrentUserName());
			for (StudyPlan studyPlan : selectPlans) {
				plan.setUserID(studyPlan.getUserID());
				plan.setCreateTime(studyPlan.getCreateTime());
				plan.setStudyCourse(studyPlan.getStudyCourse());
				try {
					studyPlanFacade.addStudyPlan(plan);
					this.messages.add("<font style='color:blue'>学员"
							+ studyPlan.getUserName() + "生成成功！</font>");
				} catch (IllegalArgumentException e) {
					this.messages
							.add("<font style='color:red'>学员<font style='color:blue'>"
									+ studyPlan.getUserName()
									+ "</font>生成计划失败，原因:"
									+ e.getMessage()
									+ "</font>");
					continue;
				}
			}
			sa.searchPlanForAdd();
		}
	}

	/**
	 * 更新计划的开始时间，结束时间
	 * 
	 * @throws Exception
	 */
	public void updatePlanTime(String flag) throws Exception {
		PlanChapterAction pa = (PlanChapterAction) this
				.getViewAction("planChapterAction");
		StudyPlan plan = pa.getPlan();
		if (plan == null || plan.getPlanID() == null) {
			this.addWarnMessage("info", "非法参数!");
			return;
		}
		if ("start".equals(flag)) {
			plan.setPlanStatus((short) 5);
		} else {
			plan.setPlanStatus((short) 8);
		}
		try {
			plan.setPlanCreatorID(this.getCurrentUserID());
			plan.setPlanCreatorName(this.getCurrentUserName());
			studyPlanFacade.updatePlanDay(plan);
			pa.setReGeneratePlan(true);
			this.addInfoMessage("info", SUCESSINFO);
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
		}
	}

	/**
	 * 学习计划类型
	 */
	public Map<Short, String> getPlanTypes() {
		return Contants.planType;
	}

	public void setStudyPlanFacade(StudyPlanFacade studyPlanFacade) {
		this.studyPlanFacade = studyPlanFacade;
	}

	public List<StudyPlan> getSelectPlans() {
		return selectPlans;
	}

	public void setSelectPlans(List<StudyPlan> selectPlans) {
		this.selectPlans = selectPlans;
	}

	public List<String> getMessages() {
		return messages;
	}

}
