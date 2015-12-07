/*
 * @Title: BatchGenPlanAction.java
 * @Package com.cdel.advc.plan.action
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-8-21 下午1:53:36
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-8-21                          
 */
package com.cdel.advc.plan.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.cdel.advc.classes.domain.Classes;
import com.cdel.advc.classes.facade.ClassesFacade;
import com.cdel.advc.memberclass.domain.MemberClass;
import com.cdel.advc.memberclass.facade.MemberClassFacade;
import com.cdel.advc.plan.domain.BatchGenPlan;
import com.cdel.advc.plan.domain.StudyPlan;
import com.cdel.advc.plan.facade.StudyPlanFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;
import com.cdel.util.ExceptionUtil;

/**
 * <p>
 * 批量生成辅导下班级学员计划 bean
 * </p>
 * 
 * @author Du Haiying Create at:2013-8-21 下午1:53:36
 */
@ManagedBean
@SuppressWarnings("serial")
public class BatchGenPlanReqAction extends BaseAction<BatchGenPlan> {

	@ManagedProperty("#{studyPlanFacade}")
	private StudyPlanFacade studyPlanFacade;
	@ManagedProperty("#{memberClassFacade}")
	private MemberClassFacade memberClassFacade;
	@ManagedProperty("#{classesFacade}")
	private ClassesFacade classesFacade;

	private Integer majorID;
	private Short planType;
	private List<String> messages;
	private byte delClassNum;// 删除班级数
	private byte submitSuccess = 0;// 修改是否成功

	/**
	 * 批量生成辅导下的计划
	 * 
	 * @throws Exception
	 */
	public void genPlans() throws Exception {
		BatchGenPlanAction ba = (BatchGenPlanAction) this
				.getViewAction("batchGenPlanAction");
		majorID = ba.getMajorID();
		planType = ba.getPlanType();
		if (majorID == null || planType == null) {
			this.addWarnMessage("info", "非法参数！");
			return;
		}
		// 先查找学员
		MemberClass mc = new MemberClass();
		mc.setMajorID(majorID);
		mc.setClassStatus((short) 1);
		mc.setStatus((short) 1);
		List<MemberClass> memberPlans = memberClassFacade
				.getMemberClassList(mc);
		int count = 0;
		if (memberPlans == null || (count = memberPlans.size()) == 0) {
			this.addInfoMessage("info", "辅导下不存在学员！");
			return;
		}
		this.messages = new ArrayList<String>();
		this.messages.add("<font style='color:red'>共查找到" + count
				+ "个学员，开始生成...</font>");

		StudyPlan sp = new StudyPlan();
		sp.setPlanType(planType);
		sp.setPlanCreatorID(this.getCurrentUserID());
		sp.setPlanCreatorName(this.getCurrentUserName());

		for (MemberClass memberPlan : memberPlans) {
			sp.setClassID(memberPlan.getClassID());
			sp.setUserID(memberPlan.getUserID());
			sp.setCreateTime(memberPlan.getEnterTime());
			sp.setStudyCourse(memberPlan.getStudyCourse());

			try {
				this.studyPlanFacade.addStudyPlan(sp);
				this.messages.add("<font style='color:blue'>学员"
						+ memberPlan.getUserName() + "生成成功！</font>");
			} catch (IllegalArgumentException e) {
				this.messages
						.add("<font style='color:red'>学员<font style='color:blue'>"
								+ memberPlan.getUserName()
								+ "</font>生成计划失败，原因:"
								+ e.getMessage()
								+ "</font>");
			}
		}

		this.messages.add("<font style='color:blue'>操作结束.</font>");

		this.updateComponent("searchForm:messagesTable");
	}

	/**
	 * 删除学习计划
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deletePlans() {
		BatchGenPlanAction ba = (BatchGenPlanAction) this
				.getViewAction("batchGenPlanAction");
		majorID = ba.getMajorID();
		planType = ba.getPlanType();
		if (majorID == null || planType == null) {
			this.addWarnMessage("info", "非法参数！");
			return;
		}
		// 先查找班级
		try {
			HashMap map = new HashMap();
			map.put("majorID", majorID);
			map.put("num", delClassNum);
			List<Classes> classesList = classesFacade
					.getClassesListByMajorID2(map);
			for (Classes classes : classesList) {
				HashMap paramMap = new HashMap();
				paramMap.put("classID", classes.getClassID());
				paramMap.put("planType", planType);
				studyPlanFacade.removeStudyPlan(paramMap);
			}
			submitSuccess = 1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除学习计划");
			logger.error(ExceptionUtil.getEMsg(e));
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
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

	public List<String> getMessages() {
		return messages;
	}

	public void setMemberClassFacade(MemberClassFacade memberClassFacade) {
		this.memberClassFacade = memberClassFacade;
	}

	public byte getDelClassNum() {
		return delClassNum;
	}

	public void setDelClassNum(byte delClassNum) {
		this.delClassNum = delClassNum;
	}

	public void setClassesFacade(ClassesFacade classesFacade) {
		this.classesFacade = classesFacade;
	}

}
