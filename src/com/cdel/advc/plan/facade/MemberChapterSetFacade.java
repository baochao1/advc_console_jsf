/*
 * @Title: MemberChapterSetFacade.java
 * @Package com.cdel.advc.plan.domain
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-8-15 下午3:09:53
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-8-15                          
 */
package com.cdel.advc.plan.facade;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdel.advc.course.domain.StudyAsk;
import com.cdel.advc.course.facade.StudyAskFacade;
import com.cdel.advc.plan.domain.MemberChapterSet;
import com.cdel.advc.plan.facade.PlanLogFacade;
import com.cdel.advc.plan.facade.StudyPlanFacade;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.CheckUtil;

/**
 * <p>
 * 学员计划 章节项设置 facade层
 * </p>
 * 
 * @author Du Haiying Create at:2013-8-15 下午3:09:53
 */
@Service
@SuppressWarnings("serial")
public class MemberChapterSetFacade extends
		BaseFacadeImpl<MemberChapterSet, Integer> {

	@Autowired
	private StudyPlanFacade studyPlanFacade;

	@Autowired
	private StudyAskFacade studyAskFacade;

	@Autowired
	private PlanLogFacade planLogFacade;

	/**
	 * 
	 * 根据章节ID和学员ID 查找 对学员的计划项设置
	 */
	public MemberChapterSet getMCSByChapterIDAndUserID(Integer chapterID,
			Integer userID) {

		if (chapterID == null || userID == null) {
			throw new IllegalArgumentException("非法参数！");
		}

		MemberChapterSet set = new MemberChapterSet();
		set.setChapterID(chapterID);
		set.setUserID(userID);
		set = this.baseDao.getByEntity(set, "getMCSByChapterIDAndUserID");

		if (set != null && set.getStudyAsk() != null) {
			String[] studyAskArray = set.getStudyAsk().split("\\{\\|\\|\\|\\}");
			set.setStudyAsk1(studyAskArray[0]);
			set.setStudyAsk2(studyAskArray[1]);
			set.setStudyAsk3(studyAskArray[2]);
			set.setStudyAsk4(studyAskArray[3]);
		}
		return set;
	}

	public boolean checkHour(MemberChapterSet chapterSet) {
		Double d = chapterSet.getSuggestStyTimeHour();
		if (d == null || d <= 0 || d >= 24
				|| !CheckUtil.checkFloat(d.toString(), 5, 2)) {
			return this.addWarnMessage("info", "建议时长必须是0-24之间的数字！");
		}
		return true;
	}

	/**
	 * 添加设置，与此同时， 如果更新计划项时长调整，那么主计划状态相应修改为：标识章节已修改，需重新生成
	 * 
	 * @param chapterSet
	 * @throws Exception
	 */
	public void addMemberChapterSet(Short planStatus,
			MemberChapterSet chapterSet) throws Exception {
		if (chapterSet == null || chapterSet.getChapterID() == null
				|| chapterSet.getUserID() == null
				|| chapterSet.getSuggestStyTime() == null) {
			throw new IllegalArgumentException("非法参数！");
		}
		this.addOrUpdate(planStatus, chapterSet);
	}

	private void addOrUpdate(Short planStatus, MemberChapterSet chapterSet)
			throws Exception {

		StringBuffer studyAsk = new StringBuffer();
		studyAsk.append(StringUtils.isNotBlank(chapterSet.getStudyAsk1()) ? chapterSet
				.getStudyAsk1() : "空");
		studyAsk.append("{|||}");
		studyAsk.append(StringUtils.isNotBlank(chapterSet.getStudyAsk2()) ? chapterSet
				.getStudyAsk2() : "空");
		studyAsk.append("{|||}");
		studyAsk.append(StringUtils.isNotBlank(chapterSet.getStudyAsk3()) ? chapterSet
				.getStudyAsk3() : "空");
		studyAsk.append("{|||}");
		studyAsk.append(StringUtils.isNotBlank(chapterSet.getStudyAsk4()) ? chapterSet
				.getStudyAsk4() : "空");
		chapterSet.setStudyAsk(studyAsk.toString().replaceAll("<p>", "")
				.replaceAll("</p>", ""));

		if (chapterSet.getChapterSetID() == null) {
			this.add(chapterSet);
		} else {
			this.update(chapterSet);
		}

		// 更新计划状态(当学习时间发生变化)
		StudyAsk ask = this.studyAskFacade.getAskByChapterIDAndType(
				chapterSet.getChapterID(), chapterSet.getType());
		if (ask.getSuggestStyTime() != chapterSet.getSuggestStyTime()) {
			this.studyPlanFacade.updatePlanStatus(chapterSet.getPlanID(),
					planStatus);
			planLogFacade.addLog(chapterSet.getPlanID(),
					chapterSet.getCreator(), chapterSet.getCreatorName(),
					"修改学习时长/要求");
		}
	}

	/**
	 * 更新学习计划项学习状态（停止学习，恢复学习）, 于此同时，更新主计划状态，添加计划修改日志
	 * 
	 * @throws Exception
	 */
	public void updateStudyStatus(List<MemberChapterSet> selectedChapters,
			Integer userID, Short status, Integer planID, Integer creator,
			String creatorName, Short Short) throws Exception {
		for (MemberChapterSet chapterSet : selectedChapters) {
			if (chapterSet == null || chapterSet.getChapterID() == null
					|| userID == null || status == null
					|| chapterSet.getChapterName() == null) {

				throw new IllegalArgumentException("非法参数！");
			}
			chapterSet.setIsStudy(status);
			if (chapterSet.getChapterSetID() == null) {
				chapterSet.setUserID(userID);
				chapterSet.setPreChapter(Short);
				this.add(chapterSet);
			} else {
				this.update(chapterSet);
			}
			// 添加日志
			String desc = "";
			if (status == 0) {
				desc = "删除学习章节[";
			} else {
				desc = "恢复学习章节[";
			}
			this.planLogFacade.addLog(planID, creator, creatorName, desc
					+ chapterSet.getChapterName() + "]");
		}
		// 更新状态
		this.studyPlanFacade.updatePlanStatus(planID, (short) 11);
	}

	/**
	 * 获取学习要求列表
	 * 
	 * @param planID
	 * @return
	 */
	public List<MemberChapterSet> findChaptersForChange(Short isStudy,
			Integer planID, Integer userID, String courseIDs) {
		MemberChapterSet se = new MemberChapterSet();
		se.setPlanID(planID);
		se.setUserID(userID);
		se.setCourseIDs(courseIDs);
		if (isStudy == 1)
			return this.baseDao.findList(se, "findChaptersForChange");
		else
			return this.baseDao.findList(se, "findChaptersForChange2");
	}

}
