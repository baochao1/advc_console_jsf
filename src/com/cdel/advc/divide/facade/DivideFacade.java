package com.cdel.advc.divide.facade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdel.advc.classes.domain.Classes;
import com.cdel.advc.classes.facade.ClassesFacade;
import com.cdel.advc.combo.domain.Combo;
import com.cdel.advc.combo.facade.ComboFacade;
import com.cdel.advc.course.domain.Course;
import com.cdel.advc.course.facade.CourseFacade;
import com.cdel.advc.divide.domain.Divide;
import com.cdel.advc.divide.domain.DivideLog;
import com.cdel.advc.innermsg.facade.InnerMsgFacade;
import com.cdel.advc.major.domain.Major;
import com.cdel.advc.membercall.facade.MemberCallFacade;
import com.cdel.advc.memberclass.domain.MemberClass;
import com.cdel.advc.memberclass.facade.MemberClassFacade;
import com.cdel.advc.membermsg.facade.MembermsgFacade;
import com.cdel.advc.plan.facade.StudyPlanFacade;
import com.cdel.advc.report.facade.ReportFacade;
import com.cdel.advc.stageRelative.domain.StageRelative;
import com.cdel.advc.stageRelative.domain.StageRelativeService;
import com.cdel.advc.stageRelative.facade.StageRelativeFacade;
import com.cdel.advc.stageRelative.facade.StageRelativeServiceFacade;
import com.cdel.advc.strategy.domain.Strategy;
import com.cdel.advc.strategy.facade.StrategyFacade;
import com.cdel.advc.studentCourse.domain.BuyCourse;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.CodeUtil;
import com.cdel.util.DateUtil;
import com.cdel.util.InterpreterUtil;
import com.cdel.util.StringUtil;
import com.cdel.advc.smsTemplate.facade.SmsTemplateFacade;

/**
 * 
 * 分班相关业务实现
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@Service
public class DivideFacade extends BaseFacadeImpl<Divide, Integer> implements
		Serializable {
	@Autowired
	private CourseFacade courseFacade;
	@Autowired
	private ComboFacade comboFacade;
	@Autowired
	private StrategyFacade strategyFacade;
	@Autowired
	private MemberClassFacade memberClassFacade;
	@Autowired
	private ClassesFacade classesFacade;
	@Autowired
	private InnerMsgFacade innerMsgFacade;
	@Autowired
	private DivideLogFacade divideLogFacade;
	@Autowired
	private StudyPlanFacade studyPlanFacade;
	@Autowired
	private ReportFacade reportFacade;
	@Autowired
	private MembermsgFacade membermsgFacade;
	@Autowired
	private MemberCallFacade memberCallFacade;
	@Autowired
	private SmsTemplateFacade smsTemplateFacade;
	@Autowired
	private StageRelativeFacade stageRelativeFacade;
	@Autowired
	private StageRelativeServiceFacade stageRelativeServiceFacade;

	private Map<Major, Set<Course>>[] buyCourseArr;

	/**
	 * 获取学员报的实验班和精品班课程，套餐
	 * 
	 * @param siteID
	 * @return
	 */
	public List<Object> getSelCourseCodeList(Integer siteID,
			List<BuyCourse> bcList) {
		List<Object> result = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer("");
		for (BuyCourse bcourse : bcList) {
			sb.append("'").append(bcourse.getCourseCode()).append("'")
					.append(",");
		}
		String courseCodes = sb.substring(0, sb.length() - 1);
		Course c = new Course();
		c.setSiteID(siteID);
		c.setCourseTypes("1,2");// 1和2代表的是实验班和精品班
		c.setStatus(Short.valueOf("1"));
		c.setCourseCodes(courseCodes);
		List<Course> idList = courseFacade.checkCourseCode(c);
		result.add(idList);
		Combo combo = new Combo();
		combo.setSiteID(siteID);
		combo.setStatus((short) 1);
		combo.setComboCodes(courseCodes);
		List<Combo> cmbList = comboFacade.findList(combo);
		result.add(cmbList);
		List<String> sccList = new ArrayList<String>();
		for (Course course : idList) {
			sccList.add(course.getCourseCode());
		}
		for (Combo cb : cmbList) {
			sccList.add(cb.getComboCode());
		}
		// 学员所报课程与本系统实验精品课程、套餐无一匹配
		if (sccList.size() == 0) {
			result.add(null);
		} else {
			List<BuyCourse> list = new ArrayList<BuyCourse>();
			for (BuyCourse bc : bcList) {
				if (sccList.contains(bc.getCourseCode())) {
					list.add(bc);
				}
			}
			result.add(list);
		}
		return result;
	}

	/**
	 * 返回策略
	 * 
	 * @param idList学员报的课程
	 * @param cmbList学员报的套餐
	 * @param mcList学员所在班级
	 * @param isInterface是否从接口调用
	 * @return
	 */
	public List<Divide> getAutoMap(List<Course> idList, List<Combo> cmbList,
			List<MemberClass> mcList, boolean isInterface) throws Exception {
		// 解析并获取所买课程(去掉同一辅导下出现的重复课程,主要是套餐包含的课程)，分为面授课和普通课
		buyCourseArr = getCourseList(idList, cmbList);
		// 存能匹配上的策略
		List<Divide> autoList = new ArrayList<Divide>();
		for (int z = 0; z < buyCourseArr.length; z++) {
			Map<Major, Set<Course>> buyCourseMap = buyCourseArr[z];
			if (buyCourseMap != null && buyCourseMap.size() > 0) {
				Set<Major> majorSet = buyCourseMap.keySet();
				List<Major> majorList = new LinkedList<Major>();
				majorList.addAll(majorSet);
				// 当学员已经分班，判断学员报课信息是否发生变化，如果有变化，则重新分班（把学员退班）
				if (isInterface) {
					checkUserBuyNewCourse(mcList, buyCourseMap);
				}
				// 去掉已加入，并且班级状态为有效的班的辅导
				majorList = removeMajorAndDealWithClasses(z, mcList, majorList);
				// 获取辅导下的所有参与自动分班的分班策略
				List<Divide> styList = getStrategyAndTerm(majorList);
				Major major = null;
				for (Divide divide : styList) {
					major = new Major();
					major.setMajorID(divide.getMajorID());
					// 获得所买课程
					StringBuffer sb = new StringBuffer("");
					for (Course c : buyCourseMap.get(major)) {
						Integer courseID = c.getCourseID();
						sb.append(",").append(courseID).append(",");
					}
					List<Strategy> sList = getStrategyListByReg(sb.toString(),
							divide.getStrategyList(),
							getBuyCourseType(buyCourseMap.get(major))); // 一个辅导能匹配上的策略
					if (sList == null || sList.size() == 0) {
						continue;
					}
					divide.setStrategyList(sList);
					int index = autoList.indexOf(divide);
					if (index == -1) {
						autoList.add(divide);
					} else {
						autoList.get(index).getStrategyList().addAll(sList);
					}
				}
			}
		}
		return autoList;
	}

	/**
	 * 解析并获取所买课程(去掉同一辅导下出现的重复课程,主要是套餐包含的课程)
	 * 
	 * @param idList
	 * @param cmbList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<Major, Set<Course>>[] getCourseList(List<Course> idList,
			List<Combo> cmbList) throws Exception {
		List<Course> ccList = null;
		// 获取套餐下的课程
		if (cmbList != null && cmbList.size() > 0) {
			StringBuffer sb = new StringBuffer("");
			for (Combo cm : cmbList) {
				sb.append(cm.getCourseSet()).append(",");
			}
			ccList = courseFacade.findCoursesByIDs(StringUtil.splitIDs(sb
					.toString()));
		}
		Major major = null;
		if (ccList != null && ccList.size() > 0) {
			idList.addAll(ccList);
		}
		List<Course> courseList1 = new ArrayList<Course>();// 正常选课
		List<Course> courseList2 = new ArrayList<Course>();// 面授课
		if (idList != null && idList.size() > 0) {
			for (Course c : idList) {
				if (c.getFaceType() == 0) {// 正常选课
					courseList1.add(c);
				} else {// 面授课程
					courseList2.add(c);
				}
			}
		}
		List<Course>[] listArr = new List[2];
		listArr[0] = courseList1;
		listArr[1] = courseList2;
		Map<Major, Set<Course>>[] rsArr = new Map[2];
		Map<Major, Set<Course>> rsMap = null;
		for (int i = 0; i < listArr.length; i++) {
			rsMap = new HashMap<Major, Set<Course>>();
			for (Course course : listArr[i]) {
				major = new Major();
				major.setMajorID(course.getMajorID());
				major.setMajorName(course.getMajorName());
				Set<Course> rsSet = rsMap.get(major);
				if (rsSet == null) {
					rsSet = new HashSet<Course>();
					rsMap.put(major, rsSet);
				}
				rsSet.add(course);
			}
			rsArr[i] = rsMap;
		}
		return rsArr;
	}

	/**
	 * 当学员已经分班，判断学员报课信息是否发生变化，如果有变化，则重新分班（把学员退班）
	 * 
	 * @param mcList
	 * @param buyCourseMap
	 */
	public void checkUserBuyNewCourse(List<MemberClass> mcList,
			Map<Major, Set<Course>> buyCourseMap) throws Exception {
		Set<Course> settmp = null;
		if (mcList != null && mcList.size() > 0) {
			Iterator<MemberClass> itmc = mcList.iterator();
			while (itmc.hasNext()) {
				MemberClass mc = itmc.next();
				Integer majorID = mc.getMajorID();
				Iterator<Entry<Major, Set<Course>>> it = buyCourseMap
						.entrySet().iterator();
				while (it.hasNext()) {
					Entry<Major, Set<Course>> entry = it.next();
					Major m = entry.getKey();
					if (m.getMajorID() == majorID) {
						// 开始判断学员报的课程是否发生变化
						String studyCourse = "," + mc.getStudyCourse() + ",";
						Set<Course> set = entry.getValue();
						Iterator<Course> it2 = set.iterator();
						settmp = new HashSet<Course>();
						while (it2.hasNext()) {
							Course c = it2.next();
							Integer courseID = c.getCourseID();
							if (studyCourse.indexOf("," + courseID + ",") > -1) {
								it2.remove();
								studyCourse = StringUtils.replace(studyCourse,
										courseID + ",", "");
								settmp.add(c);
							}
						}
						if (!studyCourse.equals(",") || set.size() != 0) {
							// 退班
							logger.warn("调用分班接口时，进行了退班操作，userID="
									+ mc.getUserID() + "，classID="
									+ mc.getClassID());
							if (mc.getStatus() != 0) {
								mc.setStatus((short) 0);
								removeMemberClass(mc, 1, "system");
							}
							itmc.remove();
						}
						// 之前被删除的course集合还要恢复回来
						set.addAll(settmp);
						continue;
					}
				}
			}
		}
	}

	/**
	 * 去掉已加入，并且班级状态为有效的班的辅导
	 * 
	 * @param mcList
	 * @param majorList
	 * @param msFlag
	 *            ,0:普通课,1:面授课
	 * @return
	 * @throws Exception
	 */
	private List<Major> removeMajorAndDealWithClasses(int msFlag,
			List<MemberClass> mcList, List<Major> majorList) throws Exception {
		if (mcList == null || mcList.size() == 0) {
			return majorList;
		}
		List<Integer> majorIDList = new ArrayList<Integer>();
		for (MemberClass mc : mcList) {
			if ((msFlag == 1 && mc.getStrategyType() == 4)
					|| (msFlag == 0 && mc.getStrategyType() < 4)) {
				majorIDList.add(mc.getMajorID());
			}
		}
		Iterator<Major> it = majorList.iterator();
		while (it.hasNext()) {
			Major m = it.next();
			if (majorIDList.contains(m.getMajorID())) {
				it.remove();
			}
		}
		return majorList;
	}

	/**
	 * 获取辅导下的分班策略
	 * 
	 * @param majorList
	 * @return
	 */
	public List<Divide> getStrategyAndTerm(List<Major> majorList) {
		List<Divide> result = new ArrayList<Divide>();
		if (majorList != null && majorList.size() > 0) {
			Integer[] majorIDs = new Integer[majorList.size()];
			for (int i = 0; i < majorList.size(); i++) {
				majorIDs[i] = majorList.get(i).getMajorID();
			}
			// 根据辅导IDs获取相关的策略，并且是参与自动分班的
			List<Strategy> list = strategyFacade.getStrategyAndTerm(majorIDs,
					(short) 1);
			Divide d = null;
			for (Strategy st : list) {
				d = new Divide();
				d.setMajorID(st.getMajorID());
				d.setMajorName(st.getMajorName());
				int index = result.indexOf(d);
				if (index >= 0) {
					result.get(index).getStrategyList().add(st);
				} else {
					List<Strategy> stList = new LinkedList<Strategy>();
					stList.add(st);
					d.setStrategyList(stList);
					result.add(d);
				}
			}
		}
		return result;
	}

	/**
	 * 获得所买课程的类型 1 实验 2 精品 3 混合
	 * 
	 * @param cSet
	 * @return
	 */
	public byte getBuyCourseType(Set<Course> cSet) {
		byte type = 0;
		if (cSet == null || cSet.size() == 0) {
			return type;
		}
		Iterator<Course> it = cSet.iterator();
		while (it.hasNext()) {
			Course course = it.next();
			if (type == 0) // 初始化
				type = course.getCourseType().byteValue();
			else {
				if (type != course.getCourseType().byteValue()) { // 所报课程包括精品和实验
					type = 3;
					break;
				}
			}
		}
		return type;
	}

	/**
	 * 一个辅导下可匹配的策略
	 * 
	 * @param sb
	 *            所买课程
	 * @param styList
	 * @param buyCourseType
	 * @return
	 */
	public List<Strategy> getStrategyListByReg(String sb,
			List<Strategy> styList, byte buyCourseType) throws Exception {
		if (buyCourseType == 0 || styList == null || styList.size() == 0
				|| sb.toString().equals("")) {
			return null;
		}
		List<Strategy> list = new ArrayList<Strategy>();
		for (int i = 0; i < styList.size(); i++) {
			Strategy strat = styList.get(i);
			if (strat.getStrategyType() != 4
					&& (strat.getTermType() != buyCourseType || strat
							.getStrategyType() != buyCourseType)) { // 策略和考期的类型与购买课程的类型不符(不考虑特殊策略)
				continue;
			}
			boolean b = getStrategyByReg(sb, strat);
			if (b) {
				strat.setCourseNames(courseFacade
						.getCourseByCourseIDs(StringUtil.splitIDs(strat
								.getCourseSet())));
				strat.setStudyCourse(sb);
				list.add(strat);
			}
		}
		return list;
	}

	/**
	 * 策略是否匹配用户所报的课程
	 * 
	 * @param sb
	 * @param strat
	 * @param buyCourseType
	 * @return
	 * @throws Exception
	 */
	public boolean getStrategyByReg(String sb, Strategy strat) throws Exception {
		boolean b = false;
		String reg = strat.getCourseRegex();
		String andOrStr = strat.getAndOrStr();
		byte f = 0;// 策略类别，2：任选，1：必选
		if (StringUtils.isNotBlank(andOrStr)) {
			// 复杂规则
			f = 3;
			String[] regCourseArr = StringUtils
					.split(strat.getCourseSet(), ",");
			String[] sbArr = StringUtils.split(sb, ",");
			String regSeq = strat.getRegSeq();
			if (StringUtil.arrContain(regCourseArr, sbArr)) {
				String[] regArr = StringUtils.split(reg, "_");
				for (int j = 0; j < regArr.length; j++) {
					String regStr = regArr[j];
					if (Pattern.compile(regStr).matcher(sb).find()) {
						regSeq = StringUtils.replace(regSeq, (j + 1) + "",
								"true");
					} else {
						regSeq = StringUtils.replace(regSeq, (j + 1) + "",
								"false");
					}
				}
				String[] regSeqArr = StringUtils.split(regSeq, "+");
				String[] andOrStrArr = StringUtils.split(andOrStr, ",");
				StringBuffer regSeqNew = new StringBuffer();// 新的正则匹配表达式
				for (int j = 0; j < regSeqArr.length; j++) {
					String tmp = regSeqArr[j];
					if (j == 0) {
						regSeqNew.append(tmp);
					} else {
						if (andOrStrArr[j - 1].equals("0")) {
							regSeqNew.append("||").append(tmp);
						} else {
							regSeqNew.append("&&").append(tmp);
						}
					}
				}
				InterpreterUtil iu = new InterpreterUtil();
				if (iu.checkExp(regSeqNew.toString())) {
					if (iu.exeExpReBoolean(regSeqNew.toString())) {
						b = true;
					}
				}
			}
		} else {
			// 单一规则
			byte courseNum = strategyFacade.getCourseCourse(reg);
			if (courseNum == StringUtils.split(reg, "|").length) {
				f = 1;
			} else {
				f = 2;
			}
			String[] regCourseArr = strategyFacade.getCourseIDsArr(reg);
			String[] sbArr = StringUtils.split(sb, ",");
			Arrays.sort(sbArr);
			if (f == 1) {
				if (Arrays.equals(regCourseArr, sbArr)) {
					b = true;
				}
			}
			if (f == 2) {
				if (StringUtil.arrContain(regCourseArr, sbArr)
						&& Pattern.compile(reg).matcher(sb).find()) {
					b = true;
				}
			}
		}
		return b;
	}

	/**
	 * 分班前的验证
	 * 
	 * @param msg
	 * @param context
	 * @param userID
	 * @param termID
	 * @param strategyID
	 * @param studyCourse
	 * @param strategyType
	 * @param majorID
	 * @return
	 */
	public MemberClass validate(Integer userID, Integer termID,
			Integer strategyID, String studyCourse, Short strategyType,
			Integer majorID, Short limitNum) {
		if (userID == null || termID == null || strategyID == null
				|| studyCourse == null || strategyType == null
				|| majorID == null || limitNum == null) {
			this.addWarnMessage("info", "信息不完整！");
			return null;
		}
		MemberClass mc = new MemberClass();
		mc.setUserID(userID);
		mc.setStrategyType(strategyType);
		mc.setMajorID(majorID);
		mc.setTermID(termID);
		List<MemberClass> mcList = memberClassFacade
				.getMemberClassesInfoShort(mc);
		if (mcList != null && mcList.size() > 0) {
			for (MemberClass mClass : mcList) {
				if (mClass.getStatus() == 1) {
					addWarnMessage("info", "学员已加入同一辅导，同一考期的一个班！");
					return null;
				}
				if (mClass.getStatus() == 0) {
					return mClass;
				}
			}
		}
		return new MemberClass();
	}

	/**
	 * 加入班级
	 * 
	 * @param mc
	 * @param oldmc
	 * @param creator
	 * @param strategyID
	 * @throws Exception
	 */
	public void addMemberToClass(MemberClass mc, MemberClass oldMc,
			Integer creator, String creatorName, Integer strategyID,
			Short limitNum, Integer majorID) throws Exception {
		int classSeq = 0;// 班级里的空闲座位号
		Date firstTime = null; // 旧班里的第一次加入时间
		Integer oldClassID = null; // 旧班ID
		Date createTime = new Date();
		// 曾经加入过班级，那么将状态设为1即可
		if (oldMc != null) {
			classSeq = memberClassFacade.getMemberInClassSeq(
					oldMc.getClassID(), limitNum);
			if (classSeq == -1
					|| strategyID.intValue() != oldMc.getStrategyID()
							.intValue()) { // 班内没有空座位或新老策略不一样，不能分到旧班需清除旧班信息，并将学习报告等其它信息更改到新的班级里
				firstTime = oldMc.getFirstTime();
				memberClassFacade.delete(oldMc);
				oldClassID = oldMc.getClassID();
				oldMc = null;
			} else {
				oldMc.setCreateTime(createTime);
				oldMc.setStatus((short) 1);
				oldMc.setSequence(classSeq);
				oldMc.setStudyCourse(mc.getStudyCourse());
				memberClassFacade.update(oldMc, (byte) 1, mc.getUserID());
				// 学员直接分回到旧班级，此时直接把发短信状态置为有效即可
				StageRelative sr = new StageRelative();
				sr.setUserID(oldMc.getUserID());
				sr.setClassID(oldMc.getClassID());
				sr.setStatus((short) 1);
				stageRelativeFacade.update4RemoveClass(sr);
				StageRelativeService sr2 = new StageRelativeService();
				sr2.setUserID(oldMc.getUserID());
				sr2.setClassID(oldMc.getClassID());
				sr2.setStatus((short) 1);
				stageRelativeServiceFacade.updateService4RemoveClass(sr2);
				mc.setClassID(oldMc.getClassID());
			}
		}
		// 没有加入过该辅导下的班级
		if (oldMc == null) {
			Classes c = new Classes();
			c.setTermID(mc.getTermID());
			c.setStrategyID(strategyID);
			c.setStatus((short) 1);
			c.setLimitNum(limitNum);
			Classes cls = classesFacade.getNotFullClasses(c); // 获得一个学员未满的班
			mc.setCreateTime(createTime);
			mc.setStatus((short) 1);
			mc.setFirstTime(firstTime == null ? (createTime) : firstTime);
			if (cls != null) { // 加入到该班
				Integer classID = cls.getClassID();
				String studentNo = CodeUtil.genStudentNewNo(cls.getClassCode(),
						cls.getCurrCount()); // 学号
				mc.setClassID(classID);
				mc.setStudentNo(studentNo);
				mc.setSequence(cls.getCurrSeq());
				memberClassFacade.add(mc);
			} else {
				// 创建新班级并加入
				mc.setStrategyID(strategyID);
				mc.setSequence(1);
				mc.setClassID(memberClassFacade.insertMemberClassInNewClass(mc));
			}
		}
		// 入班日志
		mc.setStudyCourse(null);
		mc = memberClassFacade.getMemberClass(mc);
		if (oldClassID != null) {
			// 如果曾经加入过本辅导下的班，学习报告等信息改到新班里
			modifyInfo(mc.getClassID(), oldClassID, mc.getUserID());
		}
		String logStr = "加入" + mc.getClassName();
		divideLogFacade.add(new DivideLog(mc.getUserID(), logStr, creator,
				creatorName));
		// 入学测试短信提醒
		StageRelative sr = new StageRelative();
		sr.setUserID(mc.getUserID());
		sr.setClassID(mc.getClassID());
		sr.setPlanID(0);
		sr.setStageID(-1);
		sr.setStartTime(createTime);
		Date endTime = DateUtil.getAfterDate(createTime, 7);
		sr.setEndTime(endTime);
		sr.setStatus((short) 1);
		stageRelativeFacade.add(sr);
		smsTemplateFacade.createSendTime(sr.getSmsStageRelID(), 0, majorID, -1,
				createTime, endTime, mc.getUserID(), mc.getClassID());
	}

	/**
	 * 退出班级
	 * 
	 * @param mc
	 * @throws Exception
	 */
	public void removeMemberClass(MemberClass mc, Integer creator,
			String creatorName) throws Exception {
		memberClassFacade.update(mc, (byte) -1, null);
		divideLogFacade.add(new DivideLog(mc.getUserID(), "退出"
				+ mc.getClassName(), creator, creatorName));
		// 发送短信置为无效，同时删除入学阶段短信
		StageRelative sr = new StageRelative();
		sr.setUserID(mc.getUserID());
		sr.setClassID(mc.getClassID());
		sr.setStageID(-1);
		stageRelativeFacade.delete4RemoveClass(sr);
		sr.setStatus((short) 0);
		stageRelativeFacade.update4RemoveClass(sr);
		StageRelativeService sr2 = new StageRelativeService();
		sr2.setUserID(mc.getUserID());
		sr2.setClassID(mc.getClassID());
		sr2.setStatus((short) 0);
		stageRelativeServiceFacade.updateService4RemoveClass(sr2);
	}

	/**
	 * 换班级
	 * 
	 * @param mc
	 * @throws Exception
	 */
	public void changeMemberClass(MemberClass mc, Integer creator,
			String creatorName) throws Exception {
		memberClassFacade.update(mc, (byte) -1, null);
		// 修改学习计划等到新的班
		modifyInfo(mc.getNewClassID(), mc.getClassID(), mc.getUserID());
		classesFacade.updateClassMemberCount(mc.getNewClassID(), 1);// 修改新班的人数
		// 去掉旧班该学员
		memberClassFacade.delete(mc);
		innerMsgFacade.addMessageForDivide(mc.getNewClassID(), mc.getUserID());// 发沟通消息
		divideLogFacade.add(new DivideLog(mc.getUserID(), "转到"
				+ mc.getClassName(), creator, creatorName));
	}

	/**
	 * 修改学习计划等到新的班
	 */
	public void modifyInfo(Integer newClassID, Integer oldClassID,
			Integer userID) {
		studyPlanFacade.updateStudyPlanToNewClass(newClassID, oldClassID,
				userID);
		reportFacade.updateReportToNewClass(newClassID, oldClassID, userID);
		membermsgFacade.updateMembermsgToNewClass(newClassID, oldClassID,
				userID);
		memberCallFacade.updateMembercallToNewClass(newClassID, oldClassID,
				userID);
		stageRelativeFacade.updateStageRelative(newClassID, oldClassID, userID);
	}

	public Map<Major, Set<Course>>[] getBuyCourseArr() {
		return buyCourseArr;
	}

	/**
	 * 重构学习课程
	 * 
	 * @param studyCourse
	 * @return
	 */
	public String restStudyCourse(String studyCourse) {
		studyCourse = StringUtils.replace(studyCourse, ",,", ",");
		if (StringUtils.endsWith(studyCourse, ",")) {
			studyCourse = StringUtils.substring(studyCourse, 0,
					studyCourse.length() - 1);
		}
		if (StringUtils.startsWith(studyCourse, ",")) {
			studyCourse = StringUtils.substring(studyCourse, 1,
					studyCourse.length());
		}
		return studyCourse;
	}

}