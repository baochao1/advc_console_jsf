/*
 * @Title: MemberTermHoursFacade.java
 * @Package com.cdel.advc.plan.facade
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-8-5 上午9:34:57
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-8-5                          
 */
package com.cdel.advc.plan.facade;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdel.advc.plan.domain.PlanChapterSequenceSet;
import com.cdel.advc.plan.domain.StudyPlan;
import com.cdel.util.BaseFacadeImpl;

/**
 * <p>
 * 学习计划章节排序
 * </p>
 * 
 * @author 张苏磊
 */
@SuppressWarnings("serial")
@Service
public class PlanChapterSequenceSetFacade extends
		BaseFacadeImpl<PlanChapterSequenceSet, Integer> {

	@Autowired
	private StudyPlanFacade studyPlanFacade;
	@Autowired
	private PlanLogFacade planLogFacade;

	/**
	 * 排序查找计划项章节，提出计划中重复的章节
	 * 
	 * @param planID
	 * @param type
	 * @return
	 */
	public List<PlanChapterSequenceSet> findChaptersForSort(Integer planID) {
		return this.baseDao.findList(planID, "findChaptersForSort");
	}

	/**
	 * 
	 * 更新计划章节顺序
	 * 
	 * @param planID
	 * @param pcs
	 * @throws Exception
	 */
	public void updateChapterSequences(Integer planID, Integer creator,
			String createName, List<PlanChapterSequenceSet> pcs)
			throws Exception {

		if (planID == null || pcs == null || pcs.size() == 0) {
			throw new IllegalArgumentException("非法参数！");
		}

		StudyPlan tempPlan = this.studyPlanFacade.findByID(planID);

		if (tempPlan == null) {
			throw new IllegalArgumentException("没有找到原来的学习计划记录！");
		} else {
			tempPlan.setPlanCreatorID(creator);
			tempPlan.setPlanCreatorName(createName);
		}

		// 先删除
		this.delete(planID);
		// 再添加
		for (int i = 0, size = pcs.size(); i < size; i++) {
			pcs.get(i).setSequence(i + 1);
			pcs.get(i).setPlanID(planID);
		}
		this.addList(pcs);

		// 重新生成计划
		this.studyPlanFacade.generateStudyPlan(tempPlan.getClassID(),
				tempPlan.getUserID(), tempPlan.getPlanType(),
				tempPlan.getStartDay(), tempPlan.getEndDay(), null,
				tempPlan.getStudyCourse(), tempPlan.getPreCourses());

		// 日志记录
		this.planLogFacade.addLog(planID, creator, createName, "批量修改学习计划章节顺序");

	}

}