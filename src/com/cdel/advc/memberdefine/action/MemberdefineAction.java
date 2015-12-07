package com.cdel.advc.memberdefine.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang.StringUtils;
import com.cdel.advc.member.domain.Member;
import com.cdel.advc.member.facade.MemberFacade;
import com.cdel.advc.memberdefine.domain.Examkill;
import com.cdel.advc.memberdefine.domain.Memberdefine;
import com.cdel.advc.memberdefine.domain.StudyTime;
import com.cdel.advc.memberdefine.facade.MemberdefineFacade;
import com.cdel.advc.occupation.domain.Occupation;
import com.cdel.advc.occupation.facade.OccupationFacade;
import com.cdel.advc.specialty.domain.Specialty;
import com.cdel.advc.specialty.facade.SpecialtyFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MemberdefineAction extends BaseAction<Memberdefine> implements
		Serializable {
	@ManagedProperty(value = "#{specialtyFacade}")
	private SpecialtyFacade specialtyFacade;
	@ManagedProperty(value = "#{occupationFacade}")
	private OccupationFacade occupationFacade;
	@ManagedProperty(value = "#{memberFacade}")
	private MemberFacade memberFacade;
	@ManagedProperty(value = "#{memberdefineFacade}")
	private MemberdefineFacade memberdefineFacade;

	private Integer siteID;// 网站ID
	private Integer userID;
	private List<Specialty> spList;// 专业List
	private List<Occupation> opList;// 职业List
	private Member member;
	private Memberdefine memberdefineObject;
	private Short studyHabitID;// 学习习惯ID
	private List<Examkill> examkillList = new ArrayList<Examkill>();// 已往学习经历
	private List<StudyTime> studyTimeList = new ArrayList<StudyTime>();// 已往学习经历
	private int maxTimeCount = 1;// 最大时段数

	@PostConstruct
	public void initMemberdefineAction() {
		siteID = this.getIntegerParameter("siteID");
		userID = this.getIntegerParameter("userID");
		Specialty sp = new Specialty();
		sp.setSiteID(this.siteID);
		spList = specialtyFacade.findList(sp);
		Occupation op = new Occupation();
		op.setSiteID(this.siteID);
		opList = occupationFacade.findList(op);
		member = memberFacade.getMemberByName(this.getParameter("userName"));
		memberdefineObject = memberdefineFacade.getMemberdefineByUserID(userID);
		if (memberdefineObject != null) {
			String studytimelongstr = memberdefineObject.getStudyTimeLong();
			memberdefineObject.setWeek(studytimelongstr);
			if (memberdefineObject.getStudyHabit() != null) {
				Map<Short, String> studyHabitMap = Contants.studyHabitMap;
				Iterator<Entry<Short, String>> it = studyHabitMap.entrySet()
						.iterator();
				while (it.hasNext()) {
					Entry<Short, String> entry = it.next();
					if (entry.getValue().equals(
							memberdefineObject.getStudyHabit())) {
						studyHabitID = entry.getKey();
					}
				}
				if (studyHabitID == null) {
					studyHabitID = 3;
				}
			}
			Examkill examkill = null;
			String examKill = memberdefineObject.getExamKill();
			if (StringUtils.isNotBlank(examKill)) {
				String[] examArr = StringUtils.split(examKill, "|");
				for (String exam : examArr) {
					examkill = new Examkill();
					String[] arr = StringUtils.split(exam, ",");
					if (arr.length >= 1)
						examkill.setExamName(arr[0]);
					if (arr.length >= 2)
						examkill.setYear(arr[1]);
					if (arr.length >= 3)
						examkill.setScore(arr[2]);
					examkillList.add(examkill);
				}
			} else {
//				examkill = new Examkill();
//				examkillList.add(examkill);
			}
			StudyTime studyTime = null;
			String studyTimeStr = memberdefineObject.getStudyTime();
			if (StringUtils.isNotBlank(studyTimeStr)) {
				String[] studyTimeArr = StringUtils.split(studyTimeStr, "|");
				for (int i = 0; i < studyTimeArr.length; i++) {
					studyTime = new StudyTime();
					String[] arr = StringUtils.split(studyTimeArr[i], "-");
					studyTime.setTimeName("第" + (i + 1) + "阶段");
					if (arr.length >= 1)
						studyTime.setStartTime(arr[0]);
					if (arr.length >= 2)
						studyTime.setEndTime(arr[1]);
					studyTimeList.add(studyTime);
				}
				maxTimeCount = studyTimeArr.length;
			} else {
				studyTime = new StudyTime();
				studyTime.setTimeName("第1阶段");
				studyTimeList.add(studyTime);
			}
		} else {
			memberdefineObject = new Memberdefine();
			StudyTime studyTime = new StudyTime();
			studyTime.setTimeName("第1阶段");
			studyTimeList.add(studyTime);
		//	examkillList.add(new Examkill());
		}
	}

	/**
	 * 添加考试经历
	 */
	public void addExamkill() {
		examkillList.add(new Examkill());
	}

	/**
	 * 删除考试经历
	 */
	public void removeExamkill(int index) {
		examkillList.remove(index);
	}

	/**
	 * 添加学习时段
	 */
	public void addStudyTime() {
		StudyTime studyTime = new StudyTime();
		maxTimeCount++;
		studyTime.setTimeName("第" + maxTimeCount + "阶段");
		studyTimeList.add(studyTime);
	}

	/**
	 * 删除学习时段
	 */
	public void removeStudyTime(int index) {
		if (studyTimeList.size() == index + 1) {
			maxTimeCount--;
		}
		studyTimeList.remove(index);
	}

	public void setSpecialtyFacade(SpecialtyFacade specialtyFacade) {
		this.specialtyFacade = specialtyFacade;
	}

	public List<Specialty> getSpList() {
		return spList;
	}

	public void setSpList(List<Specialty> spList) {
		this.spList = spList;
	}

	public void setOccupationFacade(OccupationFacade occupationFacade) {
		this.occupationFacade = occupationFacade;
	}

	public void setMemberFacade(MemberFacade memberFacade) {
		this.memberFacade = memberFacade;
	}

	public List<Occupation> getOpList() {
		return opList;
	}

	public void setOpList(List<Occupation> opList) {
		this.opList = opList;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public void setMemberdefineFacade(MemberdefineFacade memberdefineFacade) {
		this.memberdefineFacade = memberdefineFacade;
	}

	public Memberdefine getMemberdefineObject() {
		return memberdefineObject;
	}

	public void setMemberdefineObject(Memberdefine memberdefineObject) {
		this.memberdefineObject = memberdefineObject;
	}

	public Short getStudyHabitID() {
		return studyHabitID;
	}

	public void setStudyHabitID(Short studyHabitID) {
		this.studyHabitID = studyHabitID;
	}

	public List<Examkill> getExamkillList() {
		return examkillList;
	}

	public void setExamkillList(List<Examkill> examkillList) {
		this.examkillList = examkillList;
	}

	public List<StudyTime> getStudyTimeList() {
		return studyTimeList;
	}

	public void setStudyTimeList(List<StudyTime> studyTimeList) {
		this.studyTimeList = studyTimeList;
	}

	public Integer getUserID() {
		return userID;
	}

}
