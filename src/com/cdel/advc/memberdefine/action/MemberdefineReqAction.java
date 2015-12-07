package com.cdel.advc.memberdefine.action;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.member.domain.Member;
import com.cdel.advc.member.facade.MemberFacade;
import com.cdel.advc.memberdefine.domain.Memberdefine;
import com.cdel.advc.memberdefine.domain.MemberdefineLog;
import com.cdel.advc.memberdefine.facade.MemberdefineFacade;
import com.cdel.advc.memberdefine.facade.MemberdefineLogFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;
import com.cdel.util.ExceptionUtil;

@SuppressWarnings("serial")
@ManagedBean
public class MemberdefineReqAction extends BaseAction<Memberdefine> implements
		Serializable {
	@ManagedProperty(value = "#{memberdefineFacade}")
	private MemberdefineFacade memberdefineFacade;
	@ManagedProperty(value = "#{memberdefineLogFacade}")
	private MemberdefineLogFacade memberdefineLogFacade;
	@ManagedProperty(value = "#{memberFacade}")
	private MemberFacade memberFacade;

	/**
	 * 删除反馈表
	 * 
	 * @param userID
	 */
	public void remove(Integer userID, Integer defineID) {
		byte submitSuccess = 0;// 修改是否成功
		try {
			MemberdefineLog memberdefineLog = new MemberdefineLog();
			memberdefineLog.setCreator(this.getCurrentUserID());
			memberdefineLog.setDefineID(defineID);
			memberdefineLog.setLogType((short) 2);
			memberdefineLog.setCreateTime(new Date());
			memberdefineLogFacade.add(memberdefineLog);
			memberdefineFacade.updateMemberdefine2(userID);
			submitSuccess = 1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除userID的反馈表=" + userID);
			logger.error(ExceptionUtil.getEMsg(e));
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 添加反馈
	 * 
	 * @param userID
	 * @param defineID
	 */
	public void add(Integer userID, Integer defineID) {
		byte submitSuccess = 0;// 修改是否成功
		MemberdefineAction mda = (MemberdefineAction) this
				.getViewAction("memberdefineAction");
		Memberdefine md = mda.getMemberdefineObject();
		try {
			if (memberdefineFacade.checkExamKill(mda.getExamkillList(), md)) {
				// 更新学员信息
				Member member = mda.getMember();
				memberFacade.update(member);
				// 更新学习相关
				md.setUserID(userID);
				md.setDefineID(defineID);
				md.setStudyTime(mda.getStudyTimeList());
				md.setStudyTimeLong();
				md.setStudyHabit(mda.getStudyHabitID());
				md.setExamKill(mda.getExamkillList());
				md.setUpdateDate(new Date());
				if (memberdefineFacade.getMemberdefineByUserID(userID) == null) {
					memberdefineFacade.add(md);
				} else {
					memberdefineFacade.update(md);
				}
				submitSuccess = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加userID的反馈表=" + userID);
			logger.error(ExceptionUtil.getEMsg(e));
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public String[] getStartTimes() {
		return Contants.times;
	}

	public String[] getEndTimes() {
		return Contants.times;
	}

	public Map<Boolean, String> getGenerMap() {
		return Contants.generMap;
	}

	public Map<Short, String> getEducationMap() {
		return Contants.educationMap;
	}

	public Map<Short, String> getStuJobStatus() {
		return Contants.stuJobStatus;
	}

	public Map<Short, String> getMemoryMap() {
		return Contants.stuMemory;
	}

	public Map<Short, String> getStudyHabitMap() {
		return Contants.studyHabitMap;
	}

	public Map<Short, String> getStuStudyWayMap() {
		return Contants.stuStudyWay;
	}

	public void setMemberdefineFacade(MemberdefineFacade memberdefineFacade) {
		this.memberdefineFacade = memberdefineFacade;
	}

	public void setMemberdefineLogFacade(
			MemberdefineLogFacade memberdefineLogFacade) {
		this.memberdefineLogFacade = memberdefineLogFacade;
	}

	public void setMemberFacade(MemberFacade memberFacade) {
		this.memberFacade = memberFacade;
	}

}
