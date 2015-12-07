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

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.cdel.advc.classes.domain.Classes;
import com.cdel.advc.classes.facade.ClassesFacade;
import com.cdel.advc.plan.domain.StudyPlan;
import com.cdel.advc.plan.facade.StudyPlanFacade;
import com.cdel.util.BaseAction;

/**
 * <p>
 * 班级成员管理，批量生成学习计划
 * </p>
 * 
 * @author Du Haiying Create at:2013-8-1 下午5:19:10
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class StudyPlanAction extends BaseAction<StudyPlan> {

	@ManagedProperty("#{studyPlanFacade}")
	private StudyPlanFacade studyPlanFacade;

	@ManagedProperty("#{classesFacade}")
	private ClassesFacade classesFacade;

	private StudyPlan plan;
	private String classeName;
	private List<StudyPlan> plans;

	@PostConstruct
	public void initStudyPlanAction() {
		Integer classID = this.getIntegerParameter("classID");
		if (classID != null) {
			Classes classes = this.classesFacade.findByID(classID);
			classeName = classes.getClassName();
			plan = new StudyPlan();
			plan.setClassID(classID);
		}

	}

	/**
	 * 查找需要生成学习计划的学员计划
	 * 
	 */
	public void searchPlanForAdd() {
		if (plan == null || plan.getClassID() == null
				|| plan.getPlanType() == null) {
			this.addWarnMessage("info", "非法参数！");
			return;
		}
		if (plan.getPlanType() == null) {
			this.addWarnMessage("info", "请选择计划类型！");
			return;
		}
		this.plans = this.studyPlanFacade.getMembersForAddPlan(plan);
	}

	public StudyPlan getPlan() {
		return plan;
	}

	public void setPlan(StudyPlan plan) {
		this.plan = plan;
	}

	public void setClassesFacade(ClassesFacade classesFacade) {
		this.classesFacade = classesFacade;
	}

	public void setStudyPlanFacade(StudyPlanFacade studyPlanFacade) {
		this.studyPlanFacade = studyPlanFacade;
	}

	public String getClasseName() {
		return classeName;
	}

	public List<StudyPlan> getPlans() {
		return plans;
	}

	public void setPlans(List<StudyPlan> plans) {
		this.plans = plans;
	}

}
