package com.cdel.advc.gdb.membercall.action;

import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.apache.commons.lang.StringUtils;
import com.cdel.advc.gdb.membercall.domain.GdbMemberCall;
import com.cdel.advc.gdb.membercall.facade.GdbMemberCallFacade;
import com.cdel.advc.gdb.membercall.facade.GdbMemberCallRecordFacade;
import com.cdel.util.BaseAction;

@ManagedBean
@SuppressWarnings("serial")
public class GdbMemberCallRecordReqAction extends BaseAction<GdbMemberCall>
		implements Serializable {

	@ManagedProperty(value = "#{gdbMemberCallFacade}")
	private GdbMemberCallFacade gdbMemberCallFacade;
	@ManagedProperty(value = "#{gdbMemberCallRecordFacade}")
	private GdbMemberCallRecordFacade gdbMemberCallRecordFacade;

	private String jobStatusStr;
	private String memoryStr;
	private String examKillStr;
	private String studyHabitStr;
	private String studyWayStr;
	private Short callStatus = 1;// 回访状态

	/**
	 * 提交回访记录
	 */
	public void updateSubmit() {
		byte submitSuccess = 0;// 修改是否成功
		GdbMemberCallRecordAction mca = (GdbMemberCallRecordAction) this
				.getViewAction("gdbMemberCallRecordAction");
		if (callStatus == 1 && StringUtils.isBlank(mca.getStudyStatus2())
				&& StringUtils.isBlank(mca.getPersonalAsk2())
				&& StringUtils.isBlank(mca.getFeedback2())) {
			this.addWarnMessage("info", "请填写回访内容！");
			return;
		}
		Date callTime = new Date();
		GdbMemberCall mc = new GdbMemberCall();
		mc.setUserID(mca.getUserID());
		mc.setStudyStatus(mca.getStudyStatus2());
		mc.setPersonalAsk(mca.getPersonalAsk2());
		mc.setFeedback(mca.getFeedback2());
		mc.setCallStatus(callStatus);
		mc.setCaller(this.getCurrentUserID());
		mc.setCallTime(callTime);
		try {
			if (mca.getReservecallInfo() == null) {
				gdbMemberCallFacade.add(mc);
			} else {
				mc.setCallID(mca.getReservecallInfo().getCallID());
				gdbMemberCallFacade.update(mc);
				mca.setReservecallInfo(null);
			}
			// 更新GDB_MEMBER_CALL_RECORD表
			gdbMemberCallRecordFacade.updateRecord(mca.getUserID(), callTime,
					callStatus);
			this.updateComponent("searchForm:membetCallRecordTable1");
			this.updateComponent("searchForm2:mcrList");
			submitSuccess = 1;
		} catch (Exception e) {
			submitSuccess = -1;
			e.printStackTrace();
		}
		this.addCallbackParam("result", submitSuccess);
		this.addCallbackParam("callStatus", callStatus);
		if (submitSuccess == 1) {
			callStatus = 1;// 置回初始状态
		}
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

	public void setGdbMemberCallFacade(GdbMemberCallFacade gdbMemberCallFacade) {
		this.gdbMemberCallFacade = gdbMemberCallFacade;
	}

	public void setGdbMemberCallRecordFacade(
			GdbMemberCallRecordFacade gdbMemberCallRecordFacade) {
		this.gdbMemberCallRecordFacade = gdbMemberCallRecordFacade;
	}

}