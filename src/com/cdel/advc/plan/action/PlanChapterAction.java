/*
 * @Title: PlanChapterAction.java
 * @Package com.cdel.advc.plan.action
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-8-2 下午2:21:50
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-8-2                          
 */
package com.cdel.advc.plan.action;

import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.cdel.advc.classes.domain.Classes;
import com.cdel.advc.classes.facade.ClassesFacade;
import com.cdel.advc.classteacher.facade.ClassteacherFacade;
import com.cdel.advc.course.domain.Course;
import com.cdel.advc.course.facade.CourseFacade;
import com.cdel.advc.memberclass.domain.MemberClass;
import com.cdel.advc.memberclass.facade.MemberClassFacade;
import com.cdel.advc.memberdefine.domain.Memberdefine;
import com.cdel.advc.memberdefine.facade.MemberdefineFacade;
import com.cdel.advc.plan.domain.MemberTermHours;
import com.cdel.advc.plan.domain.PlanChapter;
import com.cdel.advc.plan.domain.StudyPlan;
import com.cdel.advc.plan.domain.WeekVo;
import com.cdel.advc.plan.facade.MemberTermHoursFacade;
import com.cdel.advc.plan.facade.PlanChapterFacade;
import com.cdel.advc.plan.facade.StudyPlanFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;
import com.cdel.util.StringUtil;

/**
 * <p>
 * 生成单个学习计划
 * </p>
 * 
 * @author Du Haiying Create at:2013-8-2 下午2:21:50
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PlanChapterAction extends BaseAction<PlanChapter> {

	@ManagedProperty(value = "#{classesFacade}")
	private ClassesFacade classesFacade;

	@ManagedProperty(value = "#{classteacherFacade}")
	private ClassteacherFacade classteacherFacade;

	@ManagedProperty("#{planChapterFacade}")
	private PlanChapterFacade planChapterFacade;

	@ManagedProperty("#{studyPlanFacade}")
	private StudyPlanFacade studyPlanFacade;

	@ManagedProperty("#{memberClassFacade}")
	private MemberClassFacade memberClassFacade;

	@ManagedProperty("#{courseFacade}")
	private CourseFacade courseFacade;

	@ManagedProperty("#{memberTermHoursFacade}")
	private MemberTermHoursFacade memberTermHoursFacade;

	@ManagedProperty(value = "#{memberdefineFacade}")
	private MemberdefineFacade memberdefineFacade;

	/** 班级信息 */
	private Classes classesInfo = null;

	/** 当前学员所在班级等有关信息 */
	private MemberClass memberClass;

	/** 学员学习计划 */
	private StudyPlan plan;

	private boolean sameCourse;

	private boolean reGeneratePlan;

	/** 学员反馈信息 */
	private Memberdefine define;

	/** 由教师等设置的学员学习时间 */
	private MemberTermHours hours;

	/** 以周次分组而组织的学员 计划项（所学课程下的章 计划安排）列表 */
	private Map<WeekVo, List<PlanChapter>> weekChapters;

	/** 学员 计划项（所学课程下的章 计划安排）列表 */
	private List<PlanChapter> planChapters;

	private Integer siteID;
	private Integer termID;
	private String userName;
	private Integer type;// 学习要求课程数
	private List<Course> preCourseList;// 预习课程List
	private String prePlanStatusStr;// 预习计划状态
	private Integer[] preCourseIDs;// 选中的预约课程

	@PostConstruct
	public void initPlanChapterAction() {
		siteID = this.getCurrentSiteID();
		Integer classID = this.getIntegerParameter("classID");
		Integer userID = this.getIntegerParameter("userID");
		Integer planType = this.getIntegerParameter("planType");
		termID = this.getIntegerParameter("termID");
		userName = this.getParameter("userName");
		if (classID == null || userID == null || planType == null) {
			this.addWarnMessage("info", "非法参数！");
			return;
		}
		try {
			classesInfo = classesFacade.findByID(classID);
			classesInfo.setTeacherNames(classteacherFacade.findList(classID));

			MemberClass mc = new MemberClass();
			mc.setClassID(classID);
			mc.setUserID(userID);
			List<MemberClass> mcList = memberClassFacade
					.getMemberClassAndCourse(mc);
			if (mcList != null && mcList.size() > 0) {
				memberClass = mcList.get(0);
			}
			memberClass.setUserName(this.getParameter("userName"));

			// 学习计划/预习计划
			this.plan = this.studyPlanFacade.getStudyPlanByLogicID(classID,
					userID, planType.shortValue());

			// 班级课程
			Integer[] courseIDs = StringUtil.splitIDs(this.memberClass
					.getStudyCourse());
			if (courseIDs != null && courseIDs.length > 0) {// 学习课程
				if (planType == 1) {// 预习课程
					preCourseList = this.courseFacade
							.getPreCoursesByCourseIDs(courseIDs);
					preCourseIDs = this.courseFacade
							.getCourseIDsArr(preCourseList);
					memberClass.setPreStudyCourseList(preCourseList);
				}
			}

			if (planType == 0) {
				StudyPlan prePlan = this.studyPlanFacade.getStudyPlanByLogicID(
						classID, userID, (short) 1);
				if (prePlan == null) {
					prePlanStatusStr = "未生成";
				} else {
					prePlanStatusStr = "已生成";
				}
			}
			this.sameCourse = studyPlanFacade.judgeSameCourse(memberClass,
					plan, planType);// 课程是否和计划一致
			this.reGeneratePlan = studyPlanFacade.judgeReGeneratePlan(plan,
					sameCourse);// 是否需要计划重新生成

			define = memberdefineFacade.getMemberdefineByUserID(userID);// 学员反馈时间
			if (define != null) {
				String studytimelongstr = define.getStudyTimeLong();
				define.setWeek(studytimelongstr);
			} else {
				define = new Memberdefine();
				define.setExamDate(null);
			}

			// 教师设定时间
			this.hours = this.memberTermHoursFacade.getMemberTermHours(userID,
					classesInfo.getTermID(), planType.shortValue());
			if (this.hours == null) {
				this.hours = new MemberTermHours();
				this.hours.setMthType(planType.shortValue());
				this.hours.setTermID(termID);
				this.hours.setUserID(this.memberClass.getUserID());
			}
			this.hours.forHours();// 分钟转小时

			// 计划开始时间，结束时间(最大，最小等时间)
			if (plan == null) {
				hours.setPlanID(null);
				plan = new StudyPlan();
				plan.setClassID(classID);
				plan = studyPlanFacade.getPlanStartAndEndDate(plan,
						memberClass.getEnterTime(), planType.shortValue());
				plan.setStudyCourse(memberClass.getStudyCourse());
			} else {
				hours.setPlanID(plan.getPlanID());
				// 获取type（计划项设置类型）
				String[] courseSet = memberClass.getStudyCourse().split(",");
				type = courseSet.length;
				if (type > 4) {
					type = 4;
				}
				this.planChapters = this.planChapterFacade
						.getPlanChaptersByPlanID(plan.getPlanID(),
								type.shortValue());
				this.weekChapters = this.planChapterFacade
						.toMap(this.planChapters);
			}
			plan.setPlanType(planType.shortValue());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			this.addWarnMessage("info", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			this.addWarnMessage("info", e.getMessage());
		}
		this.updateComponent("info");
	}

	/**
	 * 学员所学课程是否与计划课程一致 显示值
	 */
	public String getIsSameCourseStr() {
		if (this.sameCourse) {
			return "";
		} else {
			return "所学课程和计划课程设置不一致";
		}
	}

	/**
	 * 返回学习计划状态
	 */
	public String getPlanStatusStr() {
		if (this.plan == null) {
			return Contants.planStatus1.get((short) 0);
		} else {
			return this.plan.getPlanStatusStr();
		}
	}

	public List<PlanChapter> getPlanChapters() {
		return planChapters;
	}

	public void setPlanChapters(List<PlanChapter> planChapters) {
		this.planChapters = planChapters;
	}

	public void setCourseFacade(CourseFacade courseFacade) {
		this.courseFacade = courseFacade;
	}

	public Classes getClassesInfo() {
		return classesInfo;
	}

	public MemberTermHours getHours() {
		return hours;
	}

	public void setHours(MemberTermHours hours) {
		this.hours = hours;
	}

	public void setMemberTermHoursFacade(
			MemberTermHoursFacade memberTermHoursFacade) {
		this.memberTermHoursFacade = memberTermHoursFacade;
	}

	public Memberdefine getDefine() {
		return define;
	}

	public Map<WeekVo, List<PlanChapter>> getWeekChapters() {
		return weekChapters;
	}

	public void setWeekChapters(Map<WeekVo, List<PlanChapter>> weekChapters) {
		this.weekChapters = weekChapters;
	}

	public MemberClass getMemberClass() {
		return memberClass;
	}

	public void setMemberClass(MemberClass memberClass) {
		this.memberClass = memberClass;
	}

	public StudyPlan getPlan() {
		return plan;
	}

	public void setPlan(StudyPlan plan) {
		this.plan = plan;
	}

	public void setPlanChapterFacade(PlanChapterFacade planChapterFacade) {
		this.planChapterFacade = planChapterFacade;
	}

	public void setStudyPlanFacade(StudyPlanFacade studyPlanFacade) {
		this.studyPlanFacade = studyPlanFacade;
	}

	public void setMemberClassFacade(MemberClassFacade memberClassFacade) {
		this.memberClassFacade = memberClassFacade;
	}

	public boolean isReGeneratePlan() {
		return reGeneratePlan;
	}

	public void setReGeneratePlan(boolean reGeneratePlan) {
		this.reGeneratePlan = reGeneratePlan;
	}

	public boolean isSameCourse() {
		return sameCourse;
	}

	public void setClassesFacade(ClassesFacade classesFacade) {
		this.classesFacade = classesFacade;
	}

	public void setClassteacherFacade(ClassteacherFacade classteacherFacade) {
		this.classteacherFacade = classteacherFacade;
	}

	public void setMemberdefineFacade(MemberdefineFacade memberdefineFacade) {
		this.memberdefineFacade = memberdefineFacade;
	}

	public Integer getSiteID() {
		return siteID;
	}

	public Integer getType() {
		return type;
	}

	public Integer getTermID() {
		return termID;
	}

	public String getUserName() {
		return userName;
	}

	public List<Course> getPreCourseList() {
		return preCourseList;
	}

	public void setPreCourseList(List<Course> preCourseList) {
		this.preCourseList = preCourseList;
	}

	public String getPrePlanStatusStr() {
		return prePlanStatusStr;
	}

	public Integer[] getPreCourseIDs() {
		return preCourseIDs;
	}

	public void setPreCourseIDs(Integer[] preCourseIDs) {
		this.preCourseIDs = preCourseIDs;
	}

}