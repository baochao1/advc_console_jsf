package com.cdel.advc.membercall.acntion;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.apache.commons.lang.StringUtils;
import com.cdel.advc.divide.facade.DivideFacade;
import com.cdel.advc.membercall.domain.MemberCall;
import com.cdel.advc.membercall.domain.ReserveCall;
import com.cdel.advc.membercall.facade.MemberCallFacade;
import com.cdel.advc.membercall.facade.MemberCallRecordFacade;
import com.cdel.advc.membercall.facade.ReserveCallFacade;
import com.cdel.advc.memberclass.domain.MemberClass;
import com.cdel.advc.memberclass.facade.MemberClassFacade;
import com.cdel.advc.memberdefine.domain.Memberdefine;
import com.cdel.advc.memberdefine.domain.MemberdefineLog;
import com.cdel.advc.memberdefine.facade.MemberdefineFacade;
import com.cdel.advc.memberdefine.facade.MemberdefineLogFacade;
import com.cdel.advc.membermsg.domain.Membermsg;
import com.cdel.util.BaseAction;
import com.cdel.util.ExceptionUtil;

@ManagedBean
@SuppressWarnings("serial")
public class MemberCallRecordReqAction extends BaseAction<MemberCall> implements
		Serializable {
	@ManagedProperty(value = "#{memberdefineFacade}")
	private MemberdefineFacade memberdefineFacade;
	@ManagedProperty(value = ("#{memberdefineLogFacade}"))
	private MemberdefineLogFacade memberdefineLogFacade;
	@ManagedProperty(value = "#{memberCallFacade}")
	private MemberCallFacade memberCallFacade;
	@ManagedProperty(value = "#{memberCallRecordFacade}")
	private MemberCallRecordFacade memberCallRecordFacade;
	@ManagedProperty(value = "#{memberClassFacade}")
	private MemberClassFacade memberClassFacade;
	@ManagedProperty(value = "#{divideFacade}")
	private DivideFacade divideFacade;
	@ManagedProperty(value = "#{reserveCallFacade}")
	private ReserveCallFacade reserveCallFacade;

	private List<MemberdefineLog> logList;
	private String jobStatusStr;
	private String memoryStr;
	private String examKillStr;
	private String studyHabitStr;
	private String studyWayStr;
	private Short callStatus = 1;// 回访状态
	private Membermsg membermsg = new Membermsg();

	/**
	 * 编辑学员信息
	 * 
	 * @param userID
	 */
	public void updateShow(Integer userID) {
		// 获取学员基本信息
		Memberdefine mdefine = memberdefineFacade
				.getMemberdefineByUserID(userID);
		// 获取日志信息
		if (mdefine != null) {
			jobStatusStr = mdefine.getJobStatusStr();
			memoryStr = mdefine.getMemoryStr();
			examKillStr = mdefine.getExamKillStr();
			studyHabitStr = mdefine.getStudyHabitStr();
			studyWayStr = mdefine.getStudyWayStr();
			logList = memberdefineLogFacade.findList(mdefine.getDefineID());
		}
	}

	/**
	 * 更新学员反馈信息
	 * 
	 * @throws Exception
	 */
	public void updateMemberDefine() {
		byte submitSuccess = 0;// 修改是否成功
		if (StringUtils.isBlank(jobStatusStr)
				&& StringUtils.isBlank(jobStatusStr)
				&& StringUtils.isBlank(jobStatusStr)
				&& StringUtils.isBlank(jobStatusStr)
				&& StringUtils.isBlank(jobStatusStr)) {
			this.addWarnMessage("辅助信息不能全为空");
			return;
		}
		Memberdefine mdefine = new Memberdefine();
		mdefine.setAddiJobStatus(jobStatusStr);
		mdefine.setAddiMemory(memoryStr);
		mdefine.setAddiExamKill(examKillStr);
		mdefine.setAddiStudyHabit(studyHabitStr);
		mdefine.setAddiStudyWay(studyWayStr);
		MemberCallRecordAction memberCallRecordAction = (MemberCallRecordAction) this
				.getViewAction("memberCallRecordAction");
		mdefine.setUserID(memberCallRecordAction.getUserID());
		try {
			Memberdefine mf = memberdefineFacade
					.getMemberdefineByUserID(mdefine.getUserID());
			if (mf == null) {
				memberdefineFacade.add(mdefine);
			} else {
				mdefine.setDefineID(mf.getDefineID());
				memberdefineFacade.update(mdefine);
			}
			// 插入日志信息
			MemberdefineLog memberdefineLog = new MemberdefineLog();
			memberdefineLog.setDefineID(mdefine.getDefineID());
			memberdefineLog.setLogType((short) 1);
			memberdefineLog.setCreator(this.getCurrentUserID());
			memberdefineLog.setCreateTime(new Date());
			memberdefineLogFacade.add(memberdefineLog);
			submitSuccess = 1;
		} catch (Exception e) {
			submitSuccess = -1;
			e.printStackTrace();
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 更新回访 分类
	 */
	public void updateCallType() {
		byte submitSuccess = 0;// 修改是否成功
		try {
			MemberCallRecordAction mca = (MemberCallRecordAction) this
					.getViewAction("memberCallRecordAction");
			MemberCall mc = new MemberCall();
			mc.setCallType(mca.getReservecallInfo().getCallType());
			mc.setCallID(mca.getReservecallInfo().getCallID());
			memberCallFacade.update(mc);
			submitSuccess = 1;
		} catch (Exception e) {
			submitSuccess = -1;
			e.printStackTrace();
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 提交回访记录
	 */
	public void updateSubmit() {
		byte submitSuccess = 0;// 修改是否成功
		MemberCallRecordAction mca = (MemberCallRecordAction) this
				.getViewAction("memberCallRecordAction");
		if (callStatus == 1 && StringUtils.isBlank(mca.getStudyStatus2())
				&& StringUtils.isBlank(mca.getPersonalAsk2())
				&& StringUtils.isBlank(mca.getFeedback2())) {
			this.addWarnMessage("info", "请填写回访内容！");
			return;
		}
		Date callTime = new Date();
		MemberCall mc = new MemberCall();
		mc.setUserID(mca.getUserID());
		mc.setClassID(mca.getClassID());
		mc.setStudyStatus(mca.getStudyStatus2());
		mc.setPersonalAsk(mca.getPersonalAsk2());
		mc.setFeedback(mca.getFeedback2());
		mc.setCallStatus(callStatus);
		mc.setCaller(this.getCurrentUserID());
		mc.setCallTime(callTime);
		try {
			if (mca.getReservecallInfo() == null) {
				memberCallFacade.add(mc);
			} else {
				mc.setCallID(mca.getReservecallInfo().getCallID());
				memberCallFacade.update(mc);
				mca.setReservecallInfo(null);
			}
			// 更新ADVC_MEMBER_CALL_RECORD表
			memberCallRecordFacade.updateRecord(mca.getUserID(),
					mca.getClassID(), callTime, callStatus);
			this.updateComponent("searchForm:membetCallRecordTable1");
			this.updateComponent("searchForm2:mcrList");
			submitSuccess = 1;
		} catch (Exception e) {
			submitSuccess = -1;
			e.printStackTrace();
		}
		this.addCallbackParam("result", submitSuccess);
		this.addCallbackParam("callStatus", callStatus);
		this.addCallbackParam("callID", mc.getCallID());
		this.addCallbackParam("userID", mca.getUserID());
		this.addCallbackParam("classID", mca.getClassID());
		if (submitSuccess == 1) {
			callStatus = 1;// 置回初始状态
		}
	}

	/**
	 * 退班
	 * 
	 * @param userID
	 * @param classID
	 */
	public void exitClass(Integer userID, Integer classID) {
		byte submitSuccess = 0;// 修改是否成功
		MemberClass mc = new MemberClass();
		mc.setUserID(userID);
		mc.setClassID(classID);
		mc = memberClassFacade.getMemberClass(mc);
		if (mc == null || mc.getStatus() == 0) {
			this.addWarnMessage("info", "学员已不在该班，无法退班！");
		}
		mc.setStatus((short) 0);
		try {
			divideFacade.removeMemberClass(mc, this.getCurrentUserID(),
					this.getCurrentRealName());
			submitSuccess = 1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("userID:" + userID + "学员退班！");
			logger.error(ExceptionUtil.getEMsg(e));
			submitSuccess = -1;
		}
		MemberCallRecordAction mca = (MemberCallRecordAction) this
				.getViewAction("memberCallRecordAction");
		mca.setSearchBtn(false);
		mca.setExitBtn(false);
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 删除预约回访记录
	 * 
	 * @param callID
	 */
	public void delRecord(Integer callID) {
		byte submitSuccess = 0;// 修改是否成功
		try {
			ReserveCall rc = new ReserveCall();
			rc.setCallID(callID);
			rc.setCallStatus((short) 2);
			reserveCallFacade.delete(rc);
			logger.warn(this.getCurrentUserID() + "删除了预约回访记录，callID=" + callID);
			submitSuccess = 1;
		} catch (Exception e) {
			submitSuccess = -1;
			e.printStackTrace();
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public void setMemberdefineFacade(MemberdefineFacade memberdefineFacade) {
		this.memberdefineFacade = memberdefineFacade;
	}

	public List<MemberdefineLog> getLogList() {
		return logList;
	}

	public void setLogList(List<MemberdefineLog> logList) {
		this.logList = logList;
	}

	public void setMemberdefineLogFacade(
			MemberdefineLogFacade memberdefineLogFacade) {
		this.memberdefineLogFacade = memberdefineLogFacade;
	}

	public String getJobStatusStr() {
		return jobStatusStr;
	}

	public void setJobStatusStr(String jobStatusStr) {
		this.jobStatusStr = jobStatusStr;
	}

	public String getMemoryStr() {
		return memoryStr;
	}

	public void setMemoryStr(String memoryStr) {
		this.memoryStr = memoryStr;
	}

	public String getExamKillStr() {
		return examKillStr;
	}

	public void setExamKillStr(String examKillStr) {
		this.examKillStr = examKillStr;
	}

	public String getStudyHabitStr() {
		return studyHabitStr;
	}

	public void setStudyHabitStr(String studyHabitStr) {
		this.studyHabitStr = studyHabitStr;
	}

	public String getStudyWayStr() {
		return studyWayStr;
	}

	public void setStudyWayStr(String studyWayStr) {
		this.studyWayStr = studyWayStr;
	}

	public Short getCallStatus() {
		return callStatus;
	}

	public void setCallStatus(Short callStatus) {
		this.callStatus = callStatus;
	}

	public void setMemberCallFacade(MemberCallFacade memberCallFacade) {
		this.memberCallFacade = memberCallFacade;
	}

	public void setMemberCallRecordFacade(
			MemberCallRecordFacade memberCallRecordFacade) {
		this.memberCallRecordFacade = memberCallRecordFacade;
	}

	public Membermsg getMembermsg() {
		return membermsg;
	}

	public void setMembermsg(Membermsg membermsg) {
		this.membermsg = membermsg;
	}

	public void setMemberClassFacade(MemberClassFacade memberClassFacade) {
		this.memberClassFacade = memberClassFacade;
	}

	public void setDivideFacade(DivideFacade divideFacade) {
		this.divideFacade = divideFacade;
	}

	public void setReserveCallFacade(ReserveCallFacade reserveCallFacade) {
		this.reserveCallFacade = reserveCallFacade;
	}

}