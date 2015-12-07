package com.cdel.advc.plan.facade;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdel.advc.course.facade.CourseFacade;
import com.cdel.advc.memberclass.domain.MemberClass;
import com.cdel.advc.plan.domain.PlanLog;
import com.cdel.advc.plan.domain.StudyPlan;
import com.cdel.advc.smsTemplate.facade.SmsTemplateFacade;
import com.cdel.advc.stageRelative.domain.StageRelative;
import com.cdel.advc.stageRelative.facade.StageRelativeFacade;
import com.cdel.advc.stageRelative.facade.StageRelativeServiceFacade;
import com.cdel.advc.testterm.domain.Testterm;
import com.cdel.advc.testterm.facade.TesttermFacade;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.cdel.util.StringUtil;

@SuppressWarnings("serial")
@Service
public class StudyPlanFacade extends BaseFacadeImpl<StudyPlan, Integer>
		implements Serializable {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private PlanLogFacade planLogFacade;
	@Autowired
	private TesttermFacade testtermFacade;
	@Autowired
	private CourseFacade courseFacade;
	@Autowired
	private StageRelativeFacade stageRelativeFacade;
	@Autowired
	private SmsTemplateFacade smsTemplateFacade;
	@Autowired
	private StageRelativeServiceFacade stageRelativeServiceFacade;

	/**
	 * 为学员生成学习计划（包括预习计划）.
	 * 
	 * 同时记录生成计划日志.
	 * 
	 * 注意：忽略老项目中涉及老的学习计划的一些处理.
	 * 
	 * @param plan
	 * @throws Exception
	 */
	public void addStudyPlan(StudyPlan plan) throws Exception {

		if (plan == null || plan.getClassID() == null
				|| plan.getUserID() == null || plan.getPlanType() == null) {
			throw new IllegalArgumentException("非法参数！");
		}
		// 生成计划
		Integer planID = this.generateStudyPlan(plan.getClassID(),
				plan.getUserID(), plan.getPlanType(), plan.getStartDay(),
				plan.getEndDay(), plan.getCreateTime(), plan.getStudyCourse(),
				plan.getPreCourses());

		// 日志记录
		PlanLog log = new PlanLog();
		log.setCreator(plan.getPlanCreatorID());
		log.setPlanID(planID);
		log.setCreatorName(plan.getPlanCreatorName());
		this.planLogFacade.addLogOnAddPlan(log);

		// 生成阶段服务表
		List<StageRelative> srlist = null;
		if (plan.getPlanType() == 0) {
			srlist = stageRelativeFacade.getUserPlanStageStat(planID);
		} else {
			srlist = stageRelativeFacade.getUserPrePlanStageStat(planID);
		}
		if (srlist != null && srlist.size() > 0) {
			int count = stageRelativeFacade.getStageRelativeCount(planID);
			if (count > 0) {
				StageRelative o = new StageRelative();
				o.setPlanID(planID);
				stageRelativeServiceFacade.delete(planID);
				stageRelativeFacade.delete(o);
			}
			// 生成发短信时间
			for (StageRelative sr : srlist) {
				sr.setStatus((short) 1);
				stageRelativeFacade.add(sr);
				Integer majorID = getMajorIDByPlanID(planID);
				boolean result = smsTemplateFacade.createSendTime(
						sr.getSmsStageRelID(), planID, majorID,
						sr.getStageID(), sr.getStartTime(), sr.getEndTime(),
						plan.getUserID(), plan.getClassID());
				if (!result) {
					logger.error("生成发短信时间失败，planID=" + planID + ",majorID="
							+ majorID + ",sr.getStageID()=" + sr.getStageID()
							+ ",sr.getStartTime()=" + sr.getStartTime());
				}
			}
		}
	}

	/**
	 * 通过章节ID更新所有相关的学员的学习计划状态
	 * 
	 * @param chapterID
	 * @param planStatus
	 * @param open
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateStudyPlanStatusByChapter(Integer chapterID,
			Integer planStatus, Short open) {
		Map map = new HashMap();
		map.put("planStatus", planStatus);
		map.put("chapterID", chapterID);
		map.put("open", open);
		baseDao.update(map, "updateStudyPlanStatusByChapter");
	}

	/**
	 * 学员换班后，把学习计划对应到新的班级
	 * 
	 * @param newClassID
	 * @param oldClassID
	 * @param userID
	 */
	public void updateStudyPlanToNewClass(Integer newClassID,
			Integer oldClassID, Integer userID) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("newClassID", newClassID);
		map.put("classID", oldClassID);
		map.put("userID", userID);
		map.put("planStatus", 7);
		baseDao.update(map, "updateStudyPlanToNewClass");
	}

	/**
	 * 生成计划
	 * 
	 * @return:preCourse:批量生成学习计划时为空
	 */
	public Integer generateStudyPlan(Integer classID, Integer userID,
			Short planType, Date startDay, Date endDay, Date createTime,
			String studyCourse, String preCourse) {
		if (studyCourse == null) {
			throw new IllegalArgumentException("学员报课信息为空！");
		}
		StudyPlan plan = new StudyPlan();
		plan.setClassID(classID);
		plan.setStartDay(startDay);
		plan.setEndDay(endDay);
		// 主要获取计划开始和结束时间等,createTime=null批量排序不需要新生成计划的情况
		if (createTime != null) {
			if (planType == 0) {
				plan = this.getPlanStartAndEndDate(plan, createTime, planType);
				preCourse = "0";
			} else {
				plan = this.getPrePlanStartAndEndDate(plan, createTime);
				preCourse = courseFacade.getPreCoursesByCourseIDs(studyCourse);// 预习课程
				if (StringUtils.isBlank(preCourse)) {
					throw new IllegalArgumentException("学员的预习课程为空！");
				}
			}
		}
		// 封装存储过程参数
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("userID", userID);
		params.put("classID", classID);
		params.put("startTime", new java.sql.Date(plan.getStartDay().getTime()));
		params.put("endTime", new java.sql.Date(plan.getEndDay().getTime()));
		params.put("planType", planType);
		preCourse = StringUtil.filterStringDouhao(preCourse); // 去掉多余的逗号
		params.put("preCourses", preCourse);

		// 调用学习计划存储过程
		this.baseDao.get(params, "generateStudyPlan");
		Integer planID = (Integer) (params.get("planID") != null ? params
				.get("planID") : 0);
		if (planID == null || planID <= 0) {
			String errorMsg = Contants.generatePlanError.get(planID);
			if (errorMsg != null) {
				throw new IllegalArgumentException(errorMsg);
			} else {
				throw new IllegalArgumentException("生成计划发生错误：未知错误，请联系技术人员！");
			}
		}
		return planID;
	}

	/**
	 * 获取预习计划开始和结束时间
	 * 
	 * @param:createTime:入学时间
	 */
	public StudyPlan getPrePlanStartAndEndDate(StudyPlan plan, Date createTime) {
		Testterm t = testtermFacade.getTermByClassID(plan.getClassID());
		if (t == null || t.getPreStartTime() == null
				|| t.getPreEndTime() == null || createTime == null) {
			throw new IllegalArgumentException("考期或者用户信息不完整！");
		}
		if (createTime.compareTo(t.getPreEndTime()) > 0) {
			throw new IllegalArgumentException("学员入班时间大于考期预习计划的结束时间！");
		}
		if (createTime.compareTo(t.getPreStartTime()) <= 0) {
			plan.setStartDay(t.getPreStartTime());
		} else {
			plan.setStartDay(DateUtil.getStartDayTime(DateUtil
					.getNextDay(createTime)));
		}
		plan.setEndDay(t.getPreEndTime());
		if (plan.getStartDay() != null) {
			plan.setStartDay(plan.getStartDay());
		}
		if (plan.getEndDay() != null) {
			plan.setEndDay(plan.getEndDay());
		}
		return plan;
	}

	/**
	 * 获取计划开始和结束时间
	 * 
	 * @param:createTime:入学时间
	 */
	public StudyPlan getPlanStartAndEndDate(StudyPlan plan, Date createTime,
			Short planType) {
		Testterm t = testtermFacade.getTermByClassID(plan.getClassID());
		if (t == null || t.getStartTime() == null || t.getEndTime() == null
				|| createTime == null) {
			throw new IllegalArgumentException("考期或者用户信息不完整！");
		}
		if (planType == 0) {
			if (createTime.compareTo(t.getEndTime()) > 0) {
				throw new IllegalArgumentException("学员入班时间晚于考期学习计划的结束时间！");
			}
		} else {
			if (createTime.compareTo(t.getPreEndTime()) > 0) {
				throw new IllegalArgumentException("学员入班时间晚于考期预习计划的结束时间！");
			}
		}
		if (planType == 0) {
			if (createTime.compareTo(t.getStartTime()) <= 0) {
				plan.setStartDay(t.getStartTime());
			} else {
				plan.setStartDay(DateUtil.getStartDayTime(DateUtil
						.getNextDay(createTime)));
			}
			plan.setEndDay(t.getEndTime());
		} else {
			if (createTime.compareTo(t.getPreStartTime()) <= 0) {
				plan.setStartDay(t.getPreStartTime());
			} else {
				plan.setStartDay(DateUtil.getStartDayTime(DateUtil
						.getNextDay(createTime)));
			}
			plan.setEndDay(t.getPreEndTime());
		}
		if (plan.getStartDay() != null) {
			plan.setStartDay(plan.getStartDay());
		}
		if (plan.getEndDay() != null) {
			plan.setEndDay(plan.getEndDay());
		}
		return plan;
	}

	/**
	 * 按班级删除学习计划
	 * 
	 * @param map
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void removeStudyPlan(Map map) {
		this.baseDao.delete(map, "removeStudyPlan");
	}

	/**
	 * 查询还没有生成学习计划的学员
	 * 
	 * @param plan
	 * @return
	 */
	public List<StudyPlan> getMembersForAddPlan(StudyPlan plan) {
		if (plan == null || plan.getClassID() == null
				|| plan.getPlanType() == null) {
			throw new IllegalArgumentException("非法参数！");
		}
		return this.baseDao.findList(plan, "getMembersForAddPlan");
	}

	/**
	 * 
	 * 根据计划逻辑主键查找计划
	 * 
	 * @param classID
	 *            学员班级ID
	 * @param userID
	 *            学员ID
	 * @param planType
	 *            计划类型
	 * @return
	 */
	public StudyPlan getStudyPlanByLogicID(Integer classID, Integer userID,
			Short planType) {

		StudyPlan plan = new StudyPlan();
		plan.setUserID(userID);
		plan.setClassID(classID);
		plan.setPlanType(planType);

		return this.baseDao.getByEntity(plan, "getStudyPlanByLogicID");
	}

	/**
	 * 更新计划开始或结束日期,并记录日志
	 * 
	 * @param plan
	 * @throws Exception
	 */
	public void updatePlanDay(StudyPlan plan) throws Exception {

		if (plan == null || plan.getPlanID() == null
				|| (plan.getStartDay() == null && plan.getEndDay() == null)) {

			throw new IllegalArgumentException("非法参数!");
		}

		this.update(plan);

		this.planLogFacade.addLog(plan.getPlanID(), plan.getPlanCreatorID(),
				plan.getPlanCreatorName(), "修改计划时间");
	}

	/**
	 * 更新计划状态
	 * 
	 * @param planID
	 * @param status
	 * @throws Exception
	 */
	public void updatePlanStatus(Integer planID, Short status) throws Exception {

		if (planID == null || status == null) {
			throw new IllegalArgumentException("非法参数!");
		}
		StudyPlan plan = new StudyPlan();
		plan.setPlanID(planID);
		plan.setPlanStatus(status);

		this.update(plan);
	}

	/**
	 * 学员所学课程是否与计划课程一致
	 */
	public boolean judgeSameCourse(MemberClass memberClass, StudyPlan plan,
			Integer planType) {
		if (memberClass == null || plan == null) {
			return true;
		}
		if (planType == 0) {
			if (memberClass.getStudyCourse().equals(plan.getStudyCourse())) {
				return true;
			}
		} else {
			if (courseFacade.getCourseIDsStr(
					memberClass.getPreStudyCourseList()).equals(
					plan.getPreCourses())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 学习计划是否需要重新生成
	 */
	public boolean judgeReGeneratePlan(StudyPlan plan, boolean sameCourse) {
		if (plan != null) {
			if (plan.getPlanStatus() != 1 || !sameCourse) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	/**
	 * 根据planID获取majorID
	 * 
	 * @param courseID
	 * @return
	 */
	public Integer getMajorIDByPlanID(Integer planID) {
		return baseDao.get(planID, "getMajorIDByPlanID");
	}

}
