package com.cdel.advc.gdb.membercall.action;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.gdb.membercall.domain.GdbMemberCall;
import com.cdel.advc.gdb.membercall.facade.GdbMemberCallFacade;
import com.cdel.advc.memberdefine.domain.Memberdefine;
import com.cdel.advc.memberdefine.facade.MemberdefineFacade;
import com.cdel.util.BaseAction;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class GdbMemberCallRecordAction extends BaseAction<GdbMemberCall>
		implements Serializable {
	@ManagedProperty(value = "#{memberdefineFacade}")
	private MemberdefineFacade memberdefineFacade;
	@ManagedProperty(value = "#{gdbMemberCallFacade}")
	private GdbMemberCallFacade gdbMemberCallFacade;

	private String userName;
	private Integer userID;
	private Memberdefine memberdefine;// 学员信息反馈
	private GdbMemberCall reservecallInfo = null;// 预约回访信息
	private GdbMemberCall searchMemberCall = new GdbMemberCall();
	private LazyDataModel<GdbMemberCall> page;// 主件分页模型
	private String studyStatus;// 学习状态
	private String personalAsk;// 个性要求
	private String feedback;// 意见反馈
	private Integer siteID;// 网站ID

	@PostConstruct
	public void initGdbMemberCallRecordAction() {
		userID = this.getIntegerParameter("userID");
		userName = this.getParameter("userName");
		memberdefine = memberdefineFacade.getMemberdefineByUserID(userID);
		siteID = this.getCurrentSiteID();
		if (userID != null) {
			// 检测学员报课情况
			GdbMemberCall mcall = new GdbMemberCall();
			mcall.setUserID(userID);
			mcall.setCallStatus((short) 2);
			reservecallInfo = gdbMemberCallFacade.getGdbMemberCallInfo(mcall);
			searchMemberCall.setUserID(userID);
			page = gdbMemberCallFacade.findPage(searchMemberCall);
		} else {
			this.addWarnMessage("info", "userID或classID为空！");
		}
	}

	public GdbMemberCall getReservecallInfo() {
		return reservecallInfo;
	}

	public void setReservecallInfo(GdbMemberCall reservecallInfo) {
		this.reservecallInfo = reservecallInfo;
	}

	public Memberdefine getMemberdefine() {
		return memberdefine;
	}

	public void setMemberdefineFacade(MemberdefineFacade memberdefineFacade) {
		this.memberdefineFacade = memberdefineFacade;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public LazyDataModel<GdbMemberCall> getPage() {
		return page;
	}

	public void setPage(LazyDataModel<GdbMemberCall> page) {
		this.page = page;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public void setGdbMemberCallFacade(GdbMemberCallFacade gdbMemberCallFacade) {
		this.gdbMemberCallFacade = gdbMemberCallFacade;
	}

	public GdbMemberCall getSearchMemberCall() {
		return searchMemberCall;
	}

	public void setSearchMemberCall(GdbMemberCall searchMemberCall) {
		this.searchMemberCall = searchMemberCall;
	}

	public String getStudyStatus() {
		if (reservecallInfo == null) {
			studyStatus = "";
		} else {
			studyStatus = reservecallInfo.getStudyStatus();
		}
		return studyStatus;
	}

	public String getStudyStatus2() {
		return studyStatus;
	}

	public void setStudyStatus(String studyStatus) {
		this.studyStatus = studyStatus;
	}

	public String getPersonalAsk() {
		if (reservecallInfo == null) {
			personalAsk = "";
		} else {
			personalAsk = reservecallInfo.getPersonalAsk();
		}
		return personalAsk;
	}

	public String getPersonalAsk2() {
		return personalAsk;
	}

	public void setPersonalAsk(String personalAsk) {
		this.personalAsk = personalAsk;
	}

	public String getFeedback() {
		if (reservecallInfo == null) {
			feedback = "";
		} else {
			feedback = reservecallInfo.getFeedback();
		}
		return feedback;
	}

	public String getFeedback2() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Integer getSiteID() {
		return siteID;
	}

}
