/*
 * @Title: MemberClassFacade.java
 * @Package com.cdel.advc.memberclass.facade
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-7-11 上午9:19:47
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-7-11                          
 */
package com.cdel.advc.memberclass.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdel.advc.classes.domain.Classes;
import com.cdel.advc.classes.facade.ClassesFacade;
import com.cdel.advc.classmsgActive.domain.ClassmsgActive;
import com.cdel.advc.classmsgActive.facade.ClassmsgActiveFacade;
import com.cdel.advc.combo.domain.Combo;
import com.cdel.advc.course.domain.Course;
import com.cdel.advc.course.facade.CourseFacade;
import com.cdel.advc.divide.facade.DivideFacade;
import com.cdel.advc.innermsg.facade.InnerMsgFacade;
import com.cdel.advc.major.domain.ClassNo;
import com.cdel.advc.major.facade.ClassNoFacade;
import com.cdel.advc.member.domain.Member;
import com.cdel.advc.member.facade.MemberFacade;
import com.cdel.advc.memberclass.domain.MemberClass;
import com.cdel.advc.msconf.domain.Msconf;
import com.cdel.advc.msconf.facade.MsconfFacade;
import com.cdel.advc.strategy.domain.Strategy;
import com.cdel.advc.strategy.facade.StrategyFacade;
import com.cdel.advc.studentCourse.domain.BuyCourse;
import com.cdel.advc.studentCourse.facade.StudentCourseFacade;
import com.cdel.advc.testterm.domain.Testterm;
import com.cdel.advc.testterm.facade.TesttermFacade;
import com.cdel.advc.website.domain.Website;
import com.cdel.advc.website.facade.WebsiteFacade;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.CodeUtil;
import com.cdel.util.JsonUtil;
import com.cdel.util.StringUtil;

/**
 * <p>
 * 班级成员 实体 facade层
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-11 上午9:19:47
 */
@SuppressWarnings("serial")
@Service
public class MemberClassFacade extends BaseFacadeImpl<MemberClass, Integer> {

	@Autowired
	private ClassesFacade classesFacade;
	@Autowired
	private MemberFacade memberFacade;
	@Autowired
	private StrategyFacade strategyFacade;
	@Autowired
	private TesttermFacade testtermFacade;
	@Autowired
	private ClassmsgActiveFacade classmsgActiveFacade;
	@Autowired
	private ClassNoFacade classNoFacade;
	@Autowired
	private InnerMsgFacade innerMsgFacade;
	@Autowired
	private MsconfFacade msconfFacade;
	@Autowired
	private DivideFacade divideFacade;
	@Autowired
	private WebsiteFacade websiteFacade;
	@Autowired
	private CourseFacade courseFacade;
	@Autowired
	private StudentCourseFacade studentCourseFacade;

	/**
	 * 为班级批量添加学员
	 * 
	 * @param classID
	 *            班级ID
	 * @param memberIDs
	 *            学员ID
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public byte addClassMembers(Integer classID, String userName, Integer siteID)
			throws Exception {
		Member member = this.memberFacade.getMemberByName(userName);
		if (member == null) {
			addWarnMessage("学员不存在！");
			return 0;
		}
		Classes classes = this.classesFacade.findByID(classID);
		if (classes == null) {
			addWarnMessage("班级不存在！");
			return 0;
		}
		Strategy s = strategyFacade.findByID(classes.getStrategyID());
		if (s == null) {
			addWarnMessage("策略不存在！");
			return 0;
		}
		Integer majorID = s.getMajorID();
		if (majorID == null) {
			addWarnMessage("班级的策略无辅导！");
			return 0;
		}
		MemberClass mc = new MemberClass();
		mc.setUserID(member.getUserID());
		List<MemberClass> mcList = this.getMemberClassesInfoShort(mc);
		if (mcList != null && mcList.size() > 0) {
			for (MemberClass m : mcList) {
				if (m.getMajorID() != null
						&& majorID.intValue() == m.getMajorID().intValue()) {
					if (m.getStatus() == 1) {
						addWarnMessage("学员已经加入同一辅导下的班级！");
						return 0;
					} else {
						this.delete(m);// 删除已经退班的记录
					}
				}
			}
		}
		mc.setClassID(classID);
		mc = this.getMemberClass(mc);
		Website website = websiteFacade.findByID(siteID);
		String resultData = studentCourseFacade.getBuyCourseFromRemote(
				userName.trim(), website, member.getUserID());
		List<BuyCourse> bcList = JsonUtil.parseBuyCourse(resultData);
		List<Object> result = divideFacade.getSelCourseCodeList(siteID, bcList);
		bcList = (List<BuyCourse>) result.get(2);
		StringBuffer courseIDs = new StringBuffer("");
		if (bcList == null || bcList.size() == 0) {
			addWarnMessage("学员所报课程与本系统实验精品课程、套餐无一匹配！");
			return 0;
		}
		List<Course> idList = (List<Course>) result.get(0);
		List<Combo> cmbList = (List<Combo>) result.get(1);
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
		if (ccList != null && ccList.size() > 0) {
			idList.addAll(ccList);
		}
		int i = 0;
		for (Course c : idList) {
			Integer courseID = c.getCourseID();
			if (i++ == 0)
				courseIDs.append(courseID);
			else
				courseIDs.append(",").append(courseID);
		}
		if (mc == null) {
			mc = new MemberClass();
			mc.setUserID(member.getUserID());
			mc.setClassID(classID);
			mc.setCreateTime(new Date());
			mc.setStudyCourse(courseIDs.toString());// 得到当前学员课程id字符串
			mc.setStudentNo(CodeUtil.genStudentNewNo(classes.getClassCode(),
					classes.getCurrCount()));
			mc.setStatus((short) 1);
			mc.setFirstTime(new Date());
			mc.setSequence(this.getMemberInClassSeq(classID,
					classes.getLimitNum()));
			this.add(mc);
		} else {
			if (mc.getStatus() == 0) {
				mc.setStatus((short) 1);
				mc.setStudyCourse(courseIDs.toString());
				this.update(mc, (byte) 1, member.getUserID());
			} else {
				addWarnMessage("学员已经加入班级！");
				return 0;
			}
		}
		return 1;
	}

	/**
	 * 添加成员的同时，更新班级成员个数,发消息 {@inheritDoc}
	 */
	@Override
	public void add(MemberClass memberClass) {
		super.add(memberClass);
		this.classesFacade.updateClassMemberCount(memberClass.getClassID(), 1);
		innerMsgFacade.addMessageForDivide(memberClass.getClassID(),
				memberClass.getUserID());// 发沟通消息
	}

	/**
	 * 更新成员的同时，更新班级成员个数 {@inheritDoc}
	 * 
	 * @param memberClass
	 * @param count
	 *            ：更新成员各数
	 * @param userID
	 * @throws Exception
	 */
	public void update(MemberClass memberClass, byte count, Integer userID)
			throws Exception {
		super.update(memberClass);
		this.classesFacade.updateClassMemberCount(memberClass.getClassID(),
				count);
		if (userID != null) {
			innerMsgFacade
					.addMessageForDivide(memberClass.getClassID(), userID);// 发沟通消息
		}
	}

	/**
	 * 获取学员的其他信息
	 * 
	 * @param memberClass
	 * @return
	 */
	public MemberClass getMemberClass(MemberClass memberClass) {
		return baseDao.getByEntity(memberClass, "getMemberClass");
	}

	/**
	 * 获取学员的其他信息
	 * 
	 * @param memberClass
	 * @return
	 */
	public List<MemberClass> getMemberClassList(MemberClass memberClass) {
		return baseDao.findList(memberClass, "getMemberClass");
	}

	/**
	 * 获取学员的报课信息(返回课程名)
	 * 
	 * @param memberClass
	 * @return
	 */
	public List<MemberClass> getMemberClassesInfo(MemberClass memberClass) {
		return baseDao.findList(memberClass, "getMemberClassesInfo");
	}

	/**
	 * 获取学员的报课信息(返回课程名,少量信息)
	 * 
	 * @param memberClass
	 * @return
	 */
	public List<MemberClass> getMemberClassAndCourse(MemberClass memberClass) {
		return baseDao.findList(memberClass, "getMemberClassAndCourse");
	}

	/**
	 * 获取学员的报课信息(返回少量信息)
	 * 
	 * @param memberClass
	 * @return
	 */
	public List<MemberClass> getMemberClassesInfoShort(MemberClass memberClass) {
		return baseDao.findList(memberClass, "getMemberClassesInfoShort");
	}

	/**
	 * 返回班级里的空闲座位号
	 * 
	 * @param classID
	 * @return
	 * @throws Exception
	 */
	public int getMemberInClassSeq(int classID, Short limitNum)
			throws Exception {
		int i = limitNum;
		MemberClass mc = new MemberClass();
		mc.setClassID(classID);
		mc.setStatus((short) 1);
		List<MemberClass> mcList = findList(mc);
		List<Integer> mcSeqList = new ArrayList<Integer>();
		for (MemberClass mclass : mcList) {
			if (mclass.getSequence() != null) {
				mcSeqList.add(mclass.getSequence());
			}
		}
		int seq = 1;
		boolean hasSeq = false;
		for (; seq <= i; seq++) {
			if (!mcSeqList.contains(seq)) {
				hasSeq = true;
				break;
			}
		}
		if (!hasSeq) {
			return -1;
		}
		return seq;
	}

	/**
	 * 单个学员加入班级
	 * 
	 * @param mc
	 */
	public Integer insertMemberClassInNewClass(MemberClass mc) throws Exception {
		Strategy strategy = strategyFacade
				.getStrategy4Class(mc.getStrategyID());
		Short currClassNo = null;
		if (strategy.getCurrClassNo() == null) {
			currClassNo = Short.parseShort("1");
		} else {
			currClassNo = (short) (strategy.getCurrClassNo() + 1);
		}
		// 去掉4的忌讳
		Pattern p = Pattern.compile("[4]+");
		Matcher m = p.matcher(currClassNo.toString());
		while (m.find()) {
			currClassNo = (short) (currClassNo + 1);
			m = p.matcher(currClassNo.toString());
		}
		Testterm term = testtermFacade.findByID(mc.getTermID());
		Short termType = term.getTermType();
		String className = "";
		if (termType.intValue() != 4) {
			if (termType.intValue() != 3) {
				className = term.getClassShortName()
						+ (termType == 1 ? "实验" : "精品") + "(" + currClassNo
						+ ")班";
			} else {
				className = term.getClassShortName() + "(" + currClassNo + ")班";
			}
		} else {
			if (strategy.getSetDesc().indexOf("周末面授") != -1) {
				className = term.getClassShortName() + "周末(" + currClassNo
						+ ")班";
			} else if (strategy.getSetDesc().indexOf("封闭集训") != -1) {
				className = term.getClassShortName() + "集训(" + currClassNo
						+ ")班";
			} else {
				className = term.getClassShortName() + "(" + currClassNo + ")班";
			}
		}
		String classCode = CodeUtil.genClassCode(term.getTermYear(),
				term.getTermMonth(), strategy.getStrategyID(), currClassNo);
		Classes classes = new Classes();
		classes.setStrategyID(mc.getStrategyID());
		classes.setTermID(mc.getTermID());
		classes.setCurrCount((short) 0);
		classes.setClassName(className);
		classes.setClassCode(classCode);
		classes.setCreateTime(new Date());
		classes.setStatus((short) 1);
		classesFacade.add(classes);
		if (termType.intValue() == 4) {// 面授班
			Msconf f = new Msconf();
			f.setClassID(classes.getClassID());
			f.setStatus(0);
			f.setCreator(888);
			f.setCreatTime(new Date());
			msconfFacade.add(f);
		}

		// 自动加入班级公告
		ClassmsgActive classmsg = new ClassmsgActive();
		classmsg.setClassID(classes.getClassID());
		classmsg.setMsgType(Short.valueOf("1"));
		classmsg.setMsgTitle("欢迎您加入!");
		classmsg.setMsgContent("亲爱的学员：</br>　　您好！</br>欢迎加入本班学习，这里是我们共同的温暖家园，让我们载着梦想起航！祝您学习愉快！");
		classmsg.setCreator(8888);
		classmsg.setCreateTime(new Date());
		classmsg.setStatus(Short.valueOf("1"));
		classmsgActiveFacade.add(classmsg);
		// update major
		ClassNo classNo = new ClassNo();
		classNo.setMajorID(strategy.getMajorID());
		classNo.setClassType(strategy.getStrategyType());
		classNo.setCurrClassNo(currClassNo);
		classNoFacade.updateClassNo(classNo);
		// 加入班级 不需要update Classes
		String studentNo = CodeUtil.genStudentNewNo(classCode, (short) 0);
		mc.setClassID(classes.getClassID());
		mc.setStudentNo(studentNo);
		add(mc);
		return classes.getClassID();
	}

	/**
	 * 获取班级学员的报课code
	 * 
	 * @param memberClass
	 * @return
	 */
	public List<String> getMemberClassCodes(MemberClass memberClass) {
		return baseDao.findSomeList(memberClass, "getMemberClassCodes");
	}

}
