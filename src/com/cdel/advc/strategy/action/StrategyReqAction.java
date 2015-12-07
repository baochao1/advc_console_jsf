package com.cdel.advc.strategy.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.apache.commons.lang.StringUtils;
import org.primefaces.model.DualListModel;
import com.cdel.advc.classes.facade.ClassesFacade;
import com.cdel.advc.course.domain.Course;
import com.cdel.advc.course.facade.CourseFacade;
import com.cdel.advc.strategy.domain.CourseReg;
import com.cdel.advc.strategy.domain.Strategy;
import com.cdel.advc.strategy.facade.StrategyFacade;
import com.cdel.advc.testterm.domain.Testterm;
import com.cdel.advc.testterm.facade.TesttermFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;

@SuppressWarnings("serial")
@ManagedBean
public class StrategyReqAction extends BaseAction<Strategy> implements
		Serializable {
	@ManagedProperty(value = "#{strategyFacade}")
	private StrategyFacade strategyFacade;
	@ManagedProperty(value = "#{courseFacade}")
	private CourseFacade courseFacade;
	@ManagedProperty(value = "#{testtermFacade}")
	private TesttermFacade testtermFacade;
	@ManagedProperty(value = "#{classesFacade}")
	private ClassesFacade classesFacade;

	private Strategy updateStrategy = new Strategy();
	private List<CourseReg> courseRegs = new ArrayList<CourseReg>();// 策略规则里的课程
	private byte flag = -1;

	/**
	 * 修改状态
	 */
	public void changeStatus(Integer strategyID, Short strategyType,
			Integer majorID, String courseRegex, Integer status) {
		updateStrategy.setStrategyID(strategyID);
		Short newStatus;
		if (status == 1) {
			newStatus = 0;
		} else {
			updateStrategy.setMajorID(majorID);
			updateStrategy.setStrategyType(strategyType);
			updateStrategy.setCourseRegex(courseRegex);
			if (strategyFacade.judgeRegexHasExistByRegex(updateStrategy)) {
				this.addWarnMessage("info", "本辅导下已存在有效的相同策略，请先将其设置为无效！");
				return;
			}
			newStatus = 1;
		}
		updateStrategy.setStatus(newStatus);
		try {
			strategyFacade.update(updateStrategy);
			this.addInfoMessage("info", SUCESSINFO);
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
		}
	}

	/**
	 * 添加策略
	 */
	public void add() {
		flag = 0;
		StrategyAction strategyAction = (StrategyAction) this
				.getViewAction("strategyAction");
		updateStrategy.setStrategyType((short) 1);
		updateStrategy.setLimitNum((short) 50);
		updateStrategy.setPriorityLevel((short) 100);
		updateStrategy.setIsAuto((short) 1);
		updateStrategy.setRegSeq("1");
		strategyAction.setCourseList(null);
		strategyAction.setCourseList2(null);
		strategyAction.setTermList(null);
		strategyAction.setTermList2(null);
		strategyAction.setCourseRegs(new ArrayList<CourseReg>());
	}

	/**
	 * 修改策略
	 */
	public void update(Integer strategyID) {
		flag = 1;
		updateStrategy = strategyFacade.findByID(strategyID);
		String regSeq = updateStrategy.getRegSeq();
		changeMajorID();
		// 因为在changeMajorID方法里会把regSeq的值更新
		updateStrategy.setRegSeq(regSeq);
		String reg = updateStrategy.getCourseRegex();
		String andOrStr = updateStrategy.getAndOrStr();
		StrategyAction strategyAction = (StrategyAction) this
				.getViewAction("strategyAction");
		List<Course> courseList = strategyAction.getCourseList();
		List<CourseReg> courseRegs = strategyAction.getCourseRegs();
		courseRegs.clear();
		String[] arr = StringUtils.split(reg, "_");
		String[] andOrStrArr = StringUtils.split(andOrStr, ",");
		if (arr.length > 0) {
			for (int i = 0; i < arr.length; i++) {
				CourseReg cr = new CourseReg();
				String regStr = arr[i];
				byte courseNum = strategyFacade.getCourseCourse(regStr);
				if (courseNum == StringUtils.split(regStr, "|").length) {
					// 全匹配
					cr.setPiPei((byte) 1);
				} else {
					// 任意匹配
					cr.setPiPei((byte) 2);
				}
				cr.setCourseNum(courseNum);
				if (i == 0) {
					cr.setAndOr((byte) 0);
				} else {
					cr.setAndOr(Byte.parseByte(andOrStrArr[i - 1]));
				}
				DualListModel<Course> courseReg = new DualListModel<Course>();
				List<Course> courseListTmp = new ArrayList<Course>();
				courseListTmp.addAll(courseList);
				List<List<Course>> larr = strategyFacade
						.getUpdateCourseList(courseListTmp, StringUtils
								.substringAfter(StringUtils.substringBefore(
										regStr, ",)"), "(("));
				courseReg.setSource(larr.get(0));
				courseReg.setTarget(larr.get(1));
				cr.setCourseReg(courseReg);
				courseRegs.add(cr);
			}
		}
	}

	/**
	 * 更改辅导获取课程
	 * 
	 * @param e
	 */
	public void changeMajorID() {
		Integer majorID = updateStrategy.getMajorID();
		StrategyAction strategyAction = (StrategyAction) this
				.getViewAction("strategyAction");
		Course c = new Course();
		c.setMajorID(majorID);
		c.setStatus((short) 1);
		c.setIsPre((short) 2);
		List<Course> courseList2 = courseFacade.getCourseListByMajor(c);
		strategyAction.setCourseList2(courseList2);
		List<Testterm> termList2 = testtermFacade.getTermListByMajorID(majorID);
		strategyAction.setTermList2(termList2);
		showCourseAndTerm(majorID, updateStrategy.getStrategyType(),
				courseList2, termList2);
	}

	/**
	 * 更改策略获取课程
	 * 
	 * @param e
	 */
	public void changeStrategyType() {
		StrategyAction strategyAction = (StrategyAction) this
				.getViewAction("strategyAction");
		showCourseAndTerm(updateStrategy.getMajorID(),
				updateStrategy.getStrategyType(),
				strategyAction.getCourseList2(), strategyAction.getTermList2());
	}

	/**
	 * 提交添加
	 */
	public void updateSubmit() {
		byte submitSuccess = 0;// 修改是否成功
		StrategyAction strategyAction = (StrategyAction) this
				.getViewAction("strategyAction");
		try {
			List<CourseReg> courseRegs = strategyAction.getCourseRegs();
			if (strategyFacade.checkStrategy(flag, updateStrategy, courseRegs)) {
				String[] arr = strategyFacade.makeCourseReg(courseRegs);
				updateStrategy.setCourseRegex(arr[0]);
				updateStrategy.setCourseSet(arr[1]);
				updateStrategy.setAndOrStr(arr[2]);
				if (flag == 0) {
					updateStrategy.setCreator(this.getCurrentUserID());
					updateStrategy.setCreateTime(new Date());
					updateStrategy.setStatus((short) 1);
					if (strategyFacade
							.judgeRegexHasExistByRegex(updateStrategy)) {
						this.addWarnMessage("本辅导下已存在有效的相同策略，请先将其设置为无效！");
					} else {
						strategyFacade.add(updateStrategy);
						submitSuccess = 1;
						strategyAction.search();
					}
				} else {
					if (strategyFacade
							.judgeRegexHasExistByRegex(updateStrategy)) {
						this.addWarnMessage("本辅导下已存在有效的相同策略，请先将其设置为无效！");
					} else {
						strategyFacade.update(updateStrategy);
						submitSuccess = 1;
						strategyAction.search4U();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			submitSuccess = -1;
			logger.error("updateStrategy=" + updateStrategy);
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 删除策略
	 * 
	 * @param strategyID
	 */
	public void delete(Integer strategyID) {
		byte submitSuccess = 0;
		try {
			if (classesFacade.getCountClassByStrategyID(strategyID) > 0) {
				this.addWarnMessage("info", "该策略下已存在班级，不允许删除！");
			} else {
				StrategyAction strategyAction = (StrategyAction) this
						.getViewAction("strategyAction");
				strategyFacade.delete(strategyID);
				strategyAction.search();
				submitSuccess = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 根据策略的变化过滤课程和考期
	 */
	public void showCourseAndTerm(Integer majorID, Short strategyType,
			List<Course> courseList2, List<Testterm> termList2) {
		StrategyAction strategyAction = (StrategyAction) this
				.getViewAction("strategyAction");
		List<Course> courseList = new ArrayList<Course>();
		if (majorID != null && courseList2 != null && courseList2.size() > 0) {
			Iterator<Course> it = courseList2.iterator();
			while (it.hasNext()) {
				Course course = it.next();
				if ((strategyType == 1 && course.getCourseType() == 1 && course
						.getFaceType() == 0)
						|| (strategyType == 2 && course.getCourseType() == 2 && course
								.getFaceType() == 0)) {
					courseList.add(course);
				}
				if (strategyType == 3 && course.getFaceType() == 0) {
					courseList.add(course);
				}
				if (strategyType == 4 && course.getFaceType() == 1) {
					courseList.add(course);
				}
			}
		}
		strategyAction.setCourseList(courseList);
		List<Testterm> termList = new ArrayList<Testterm>();
		if (majorID != null && termList2 != null && termList2.size() > 0) {
			Iterator<Testterm> it = termList2.iterator();
			while (it.hasNext()) {
				Testterm term = it.next();
				if ((strategyType == 1 && term.getTermType() == 1)
						|| (strategyType == 2 && term.getTermType() == 2)
						|| (strategyType == 3 && term.getTermType() == 3)
						|| (strategyType == 4 && term.getTermType() == 4)) {
					termList.add(term);
				}
			}
		}
		strategyAction.setTermList(termList);
		strategyAction.getCourseRegs().clear();
		addReg();
	}

	/**
	 * 添加规则
	 */
	public void addReg() {
		StrategyAction strategyAction = (StrategyAction) this
				.getViewAction("strategyAction");
		List<CourseReg> courseRegs = strategyAction.getCourseRegs();
		DualListModel<Course> courseReg = new DualListModel<Course>();
		List<Course> scourseList = strategyAction.getCourseList();
		if (scourseList == null || scourseList.size() == 0) {
			this.addWarnMessage("msg", "请先选择辅导！");
		} else {
			courseReg.setSource(scourseList);
			courseReg.setTarget(new ArrayList<Course>());
			CourseReg cr = new CourseReg();
			cr.setCourseReg(courseReg);
			courseRegs.add(cr);
			createRegSeq(courseRegs);
		}
	}

	/**
	 * 删除规则
	 */
	public void delReg(int index) {
		StrategyAction strategyAction = (StrategyAction) this
				.getViewAction("strategyAction");
		List<CourseReg> courseRegs = strategyAction.getCourseRegs();
		courseRegs.remove(index);
		createRegSeq(courseRegs);
	}

	/**
	 * 生成规则表达式
	 */
	public void createRegSeq(List<CourseReg> courseRegs) {
		StringBuffer regSeq = new StringBuffer("");
		for (int i = 0; i < courseRegs.size(); i++) {
			if (i == 0) {
				regSeq.append(i + 1);
			} else {
				regSeq.append("+").append(i + 1);
			}
		}
		updateStrategy.setRegSeq(regSeq.toString());
	}

	/**
	 * 生成描述
	 */
	public void createDesc() {
		StringBuffer sb = new StringBuffer("");
		StrategyAction strategyAction = (StrategyAction) this
				.getViewAction("strategyAction");
		try {
			List<CourseReg> courseRegs = strategyAction.getCourseRegs();
			if (strategyFacade.checkStrategy((byte) 3, updateStrategy,
					courseRegs)) {
				CourseReg creg = courseRegs.get(0);
				List<Course> clist = creg.getCourseReg().getTarget();
				for (int i = 0; i < clist.size(); i++) {
					if (i == 0) {
						sb.append(clist.get(i).getCourseName());
					} else {
						sb.append("+").append(clist.get(i).getCourseName());
					}
				}
				if (creg.getPiPei() == 2) {
					sb.append("【任选").append(creg.getCourseNum())
							.append("门或以上】");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		updateStrategy.setSetDesc(sb.toString());
	}

	public Map<Short, String> getStrategyTypes() {
		return Contants.strategyTypeMap;
	}

	public void setStrategyFacade(StrategyFacade strategyFacade) {
		this.strategyFacade = strategyFacade;
	}

	public Strategy getUpdateStrategy() {
		return updateStrategy;
	}

	public void setUpdateStrategy(Strategy updateStrategy) {
		this.updateStrategy = updateStrategy;
	}

	public byte getFlag() {
		return flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

	public void setCourseFacade(CourseFacade courseFacade) {
		this.courseFacade = courseFacade;
	}

	public void setTesttermFacade(TesttermFacade testtermFacade) {
		this.testtermFacade = testtermFacade;
	}

	public void setClassesFacade(ClassesFacade classesFacade) {
		this.classesFacade = classesFacade;
	}

	public List<CourseReg> getCourseRegs() {
		return courseRegs;
	}

	public void setCourseRegs(List<CourseReg> courseRegs) {
		this.courseRegs = courseRegs;
	}

	public Map<Byte, String> getAndOrs() {
		return Contants.andOrs;
	}

	public Map<Byte, String> getPiPeis() {
		return Contants.piPeis;
	}

	public Map<Short, String> getIsAutos() {
		return Contants.yesorno;
	}
	public Map<Short, String> getStatus() {
		return Contants.status;
	}
}