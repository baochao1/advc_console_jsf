package com.cdel.advc.membercall.acntion;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.classes.domain.Classes;
import com.cdel.advc.classes.facade.ClassesFacade;
import com.cdel.advc.classteacher.facade.ClassteacherFacade;
import com.cdel.advc.membercall.domain.MemberCall;
import com.cdel.advc.membercall.domain.MemberCallAll;
import com.cdel.advc.membercall.facade.MemberCallAllFacade;
import com.cdel.advc.membercall.facade.MemberCallFacade;
import com.cdel.advc.membercall.facade.MemberCallRecordFacade;
import com.cdel.advc.memberclass.domain.MemberClass;
import com.cdel.advc.memberclass.facade.MemberClassFacade;
import com.cdel.advc.memberdefine.domain.Memberdefine;
import com.cdel.advc.memberdefine.facade.MemberdefineFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.ExceptionUtil;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MemberCallRecordAction extends BaseAction<MemberCall> implements
		Serializable {
	@ManagedProperty(value = "#{memberdefineFacade}")
	private MemberdefineFacade memberdefineFacade;
	@ManagedProperty(value = "#{memberCallAllFacade}")
	private MemberCallAllFacade memberCallAllFacade;
	@ManagedProperty(value = "#{memberClassFacade}")
	private MemberClassFacade memberClassFacade;
	@ManagedProperty(value = "#{classesFacade}")
	private ClassesFacade classesFacade;
	@ManagedProperty(value = "#{classteacherFacade}")
	private ClassteacherFacade classteacherFacade;
	@ManagedProperty(value = "#{memberCallFacade}")
	private MemberCallFacade memberCallFacade;
	@ManagedProperty(value = "#{memberCallRecordFacade}")
	private MemberCallRecordFacade memberCallRecordFacade;

	private String userName;
	private Integer userID;
	private Integer classID;
	private Memberdefine memberdefine;// 学员信息反馈
	private Classes classesInfo = null;// 班级信息
	private MemberClass memberClassInfo = null;// 报课信息
	private MemberCall reservecallInfo = null;// 预约回访信息
	private MemberCallAll searchMemberCall = new MemberCallAll();
	private LazyDataModel<MemberCallAll> page;// 主件分页模型
	private String studyStatus;// 学习状态
	private String personalAsk;// 个性要求
	private String feedback;// 意见反馈
	private boolean searchBtn = true;// 提交回访记录按钮
	private boolean exitBtn = false;// 退班按钮
	private boolean delBtn = false;// 删除按钮
	private Integer siteID;// 网站ID
	private byte mcFlag;// 打开页面时，判断学员报课状态

	@PostConstruct
	public void initMemberCallRecordAction() {
		userID = this.getIntegerParameter("userID");
		classID = this.getIntegerParameter("classID");
		userName = this.getParameter("userName");
		memberdefine = memberdefineFacade.getMemberdefineByUserID(userID);
		if (userID != null && classID != null) {
			// 检测学员报课情况
			siteID = this.getCurrentSiteID();
			classesInfo = classesFacade.findByID(classID);
			try {
				mcFlag = memberCallRecordFacade.checkMemberInit(siteID, userID,
						classID, userName);
				if (mcFlag == 0) {
					searchBtn = false;
					delBtn = true;
					this.addWarnMessage("info", "学员已经退班！");
				} else if (mcFlag == -1) {
					searchBtn = false;
					this.addWarnMessage("info", "网站对应的ASP选课数据服务器没有配置！");
				} else if (mcFlag == 1) {
					exitBtn = true;
					delBtn = true;
					this.addWarnMessage("info",
							"学员在" + classesInfo.getClassName() + "中的报课课程已关闭！");
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("userID=" + userID + ",classID=" + classID
						+ ",userName=" + userName + ",siteID=" + siteID);
				logger.error(ExceptionUtil.getEMsg(e));
			}
			classesInfo.setTeacherNames(classteacherFacade.findList(classID));
			MemberClass mc = new MemberClass();
			mc.setUserID(userID);
			mc.setClassID(classID);
			List<MemberClass> mcList = memberClassFacade
					.getMemberClassAndCourse(mc);
			if (mcList != null && mcList.size() > 0) {
				memberClassInfo = mcList.get(0);
			}
			MemberCall mcall = new MemberCall();
			mcall.setUserID(userID);
			mcall.setClassID(classID);
			mcall.setCallStatus((short) 2);
			reservecallInfo = memberCallFacade.getMemberCallInfo(mcall);
			searchMemberCall.setUserID(userID);
			page = memberCallAllFacade.findPage(searchMemberCall);
		} else {
			this.addWarnMessage("info", "userID或classID为空！");
		}
	}

	public MemberCall getReservecallInfo() {
		return reservecallInfo;
	}

	public void setReservecallInfo(MemberCall reservecallInfo) {
		this.reservecallInfo = reservecallInfo;
	}

	public MemberClass getMemberClassInfo() {
		return memberClassInfo;
	}

	public Classes getClassesInfo() {
		return classesInfo;
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

	public LazyDataModel<MemberCallAll> getPage() {
		return page;
	}

	public void setPage(LazyDataModel<MemberCallAll> page) {
		this.page = page;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Integer getClassID() {
		return classID;
	}

	public void setMemberCallAllFacade(MemberCallAllFacade memberCallAllFacade) {
		this.memberCallAllFacade = memberCallAllFacade;
	}

	public void setMemberClassFacade(MemberClassFacade memberClassFacade) {
		this.memberClassFacade = memberClassFacade;
	}

	public void setClassesFacade(ClassesFacade classesFacade) {
		this.classesFacade = classesFacade;
	}

	public void setClassteacherFacade(ClassteacherFacade classteacherFacade) {
		this.classteacherFacade = classteacherFacade;
	}

	public void setMemberCallFacade(MemberCallFacade memberCallFacade) {
		this.memberCallFacade = memberCallFacade;
	}

	public void setSearchMemberCall(MemberCallAll searchMemberCall) {
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

	public void setMemberCallRecordFacade(
			MemberCallRecordFacade memberCallRecordFacade) {
		this.memberCallRecordFacade = memberCallRecordFacade;
	}

	public void setSearchBtn(boolean searchBtn) {
		this.searchBtn = searchBtn;
	}

	public void setExitBtn(boolean exitBtn) {
		this.exitBtn = exitBtn;
	}

	public boolean isSearchBtn() {
		return searchBtn;
	}

	public boolean isExitBtn() {
		return exitBtn;
	}

	public byte getMcFlag() {
		return mcFlag;
	}

	public Integer getSiteID() {
		return siteID;
	}

	public boolean isDelBtn() {
		return delBtn;
	}

	public void setDelBtn(boolean delBtn) {
		this.delBtn = delBtn;
	}

}
