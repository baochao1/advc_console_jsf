package com.cdel.advc.membermsg.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.cdel.advc.classteacher.domain.Classteacher;
import com.cdel.advc.member.domain.Member;
import com.cdel.advc.memberclass.action.MemberClassOther;
import com.cdel.advc.membermsg.domain.Membermsg;
import com.cdel.advc.membermsg.domain.MembermsgRelRecall;
import com.cdel.advc.membermsg.facade.MembermsgFacade;
import com.cdel.advc.membermsg.facade.MembermsgRelRecallFacade;
import com.cdel.advc.membermsglog.domain.MembermsgLog;
import com.cdel.advc.membermsglog.facade.MembermsgLogFacade;
import com.cdel.advc.technologyMsg.action.TechnologyMsgAction;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;
import com.cdel.util.ExceptionUtil;
import com.cdel.util.MD5;

@SuppressWarnings("serial")
@ManagedBean
public class MembermsgReqAction extends BaseAction<Membermsg> implements
		Serializable {
	@ManagedProperty(value = "#{membermsgFacade}")
	private MembermsgFacade membermsgFacade;
	@ManagedProperty(value = "#{membermsgLogFacade}")
	private MembermsgLogFacade membermsgLogFacade;
	@ManagedProperty(value = "#{membermsgRelRecallFacade}")
	private MembermsgRelRecallFacade membermsgRelRecallFacade;

	private byte submitSuccess = 0;// 修改是否成功
	private Membermsg membermsg = new Membermsg();
	private List<MembermsgLog> membermsgLogList;// 修改日志
	private Integer logCount;
	private String flag;// flag=all全部学员消息，flag=''学员消息，flag='tech'技术消息

	private boolean showDialogShow = true;// 控制是否显示Dialog
	private List<Classteacher> teacherList;// 本班管理员
	private Integer[] members;// 需要发短信学员IDs
	private Integer userID;// 需要发短信学员ID
	private List<Membermsg> membermsgList;// 学员已往消息
	private List<Membermsg> membermsgList2;// 学员已往消息
	private Integer classID;
	private Integer callID;// !=0表示电话回访失败后，打开的，需要和回访记录关联；=0表示正常发消息

	/**
	 * 修改状态
	 */
	public void changeStatus(Integer msgID, Integer status) {
		Membermsg membermsg = new Membermsg();
		membermsg.setMsgID(msgID);
		Short newStatus;
		if (status == 1) {
			newStatus = 0;
		} else {
			newStatus = 1;
		}
		membermsg.setStatus(newStatus);
		try {
			membermsgFacade.update(membermsg);
			this.addInfoMessage("info", SUCESSINFO);
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
		}
	}

	/**
	 * 回复
	 */
	public void updateShow(Integer msgID, String flag) {
		membermsg = membermsgFacade.getMembermsgDetail(msgID);
		if (membermsg != null) {
			logCount = membermsgLogFacade.getMembermsgLogCount(msgID);
		}
		this.flag = flag;
	}

	/**
	 * 已往消息查询
	 * 
	 * @throws Exception
	 */
	public void search() {
		String userName = membermsg.getUserName();
		if (flag.equals("")) {
			MembermsgAction membermsgAction = (MembermsgAction) this
					.getViewAction("membermsgAction");
			membermsgAction.getSearchMembermsg().setUserName(userName);
			membermsgAction.getSearchMembermsg().setStatus(null);
			membermsgAction.getSearchMembermsg().setReply(null);
			membermsgAction.search();
		} else if (flag.equals("all")) {
			MembermsgAllAction membermsgAllAction = (MembermsgAllAction) this
					.getViewAction("membermsgAllAction");
			membermsgAllAction.getSearchMembermsg().setUserName(userName);
			membermsgAllAction.getSearchMembermsg().setStatus(null);
			membermsgAllAction.getSearchMembermsg().setReply(null);
			membermsgAllAction.search();
		} else {
			TechnologyMsgAction technologyMsgAction = (TechnologyMsgAction) this
					.getViewAction("technologyMsgAction");
			technologyMsgAction.getSearchTechnologyMsg().setUserName(userName);
			technologyMsgAction.getSearchTechnologyMsg().setStatus(null);
			technologyMsgAction.getSearchTechnologyMsg().setReply(null);
			technologyMsgAction.search();
		}
	}

	/**
	 * 其他功能-转为技术/转为正常
	 * 
	 * @throws Exception
	 */
	public void updateMessage(Short msgType) {
		try {
			membermsgFacade.updateMsg(msgType, this.getCurrentUserID(),
					this.getAttribute(Contants.ADMIN_REAL_NAME), membermsg);
			this.addInfoMessage("info", SUCESSINFO);
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
			logger.error("membermsg=" + membermsg);
			logger.error(ExceptionUtil.getEMsg(e));
		}
	}

	/**
	 * 提交
	 */
	public void updateMessage() {
		try {
			if (membermsg.getReplyContent().equals("")) {
				this.addWarnMessage("回复内容不能为空！");
				return;
			}
			membermsgFacade.updateMsg(this.getCurrentUserID(),
					this.getAttribute(Contants.ADMIN_REAL_NAME), membermsg);
			if (flag.equals("")) {
				MembermsgAction membermsgAction = (MembermsgAction) this
						.getViewAction("membermsgAction");
				membermsgAction.search();
			} else if (flag.equals("all")) {
				MembermsgAllAction membermsgAllAction = (MembermsgAllAction) this
						.getViewAction("membermsgAllAction");
				membermsgAllAction.search();
			} else {
				TechnologyMsgAction technologyMsgAction = (TechnologyMsgAction) this
						.getViewAction("technologyMsgAction");
				technologyMsgAction.search();
			}
			submitSuccess = 1;
		} catch (Exception e) {
			submitSuccess = -1;
			e.printStackTrace();
			logger.error("membermsg=" + membermsg);
			logger.error(ExceptionUtil.getEMsg(e));
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 显示学员发消息页面
	 * 
	 * @param userID
	 * @param classID
	 */
	public void showMsg(Integer userID, Integer classID) {
		this.classID = classID;
		Membermsg m = new Membermsg();
		m.setUserID(userID);
		m.setStatus((short) 0);
		membermsgList2 = membermsgFacade.findList(m);
		m.setClassID(classID);
		membermsgList = membermsgFacade.findList(m);
		members = new Integer[] { userID };
		MemberClassOther mcr = (MemberClassOther) this
				.getViewAction("memberClassOther");
		mcr.setMemberList(classID);
	}

	/**
	 * 显示学员发消息页面
	 * 
	 * @param userID
	 * @param callID
	 *            ：!=0表示电话回访失败后，打开的，需要和回访记录关联；=0表示正常发消息
	 */
	public void showMsg() {
		Integer classID = this.getIntegerRequestParameterByMap("classID");
		Integer callID = this.getIntegerRequestParameterByMap("callID");
		Integer userID = this.getIntegerRequestParameterByMap("userID");
		this.classID = classID;
		this.callID = callID;
		Membermsg m = new Membermsg();
		m.setUserID(userID);
		m.setStatus((short) 0);
		membermsgList2 = membermsgFacade.findList(m);
		m.setClassID(classID);
		membermsgList = membermsgFacade.findList(m);
		this.userID = userID;
		members = new Integer[] { userID };
		MemberClassOther mcr = (MemberClassOther) this
				.getViewAction("memberClassOther");
		mcr.setMemberList(classID);
	}

	/**
	 * 发送消息
	 */
	public void sendMessage() {
		if (membermsgFacade
				.checkSendMessage(membermsg, callID, members, userID)) {
			try {
				List<Member> newMemberList = new ArrayList<Member>();
				Member m = null;
				for (Integer userID : members) {
					if ((callID == null || callID == 0)
							|| (callID != null && callID != 0 && userID
									.intValue() != this.userID)) {
						m = new Member();
						m.setUserID(userID);
						m.setClassID(classID);
						newMemberList.add(m);
					}
				}
				membermsgFacade.addMemberMsg(this.getCurrentUserID(),
						membermsg, newMemberList);
				// 如果是电话回访失败发留言，则需要进行关联
				if (callID != null && callID != 0) {
					m = new Member();
					m.setUserID(userID);
					m.setClassID(classID);
					Membermsg mm = membermsgFacade.addMemberMsg(
							this.getCurrentUserID(), membermsg, m);
					MembermsgRelRecall mr = new MembermsgRelRecall();
					mr.setCallID(callID);
					mr.setMsgID(mm.getMsgID());
					membermsgRelRecallFacade.add(mr);
				}
				submitSuccess = 1;
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(ExceptionUtil.getEMsg(e));
				submitSuccess = -1;
			}
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 学员学习进度展示
	 * @param userID
	 * 
	 */
	public void showLearningProcess(Integer userID){
		String time =Long.toString(new Date().getTime());
		String key =MD5.Md5("hS59VB1Ylci"+ time + userID.toString(),32);
		this.executeScript("learningProcess('" + userID + "','" + time + "','" + key + "')");
	}

	
	public Map<Short, String> getStatus() {
		return Contants.status;
	}

	public Map<Short, String> getAnswerStatus() {
		return Contants.answerStatus;
	}

	public void setMembermsgFacade(MembermsgFacade membermsgFacade) {
		this.membermsgFacade = membermsgFacade;
	}

	public void setMembermsgLogFacade(MembermsgLogFacade membermsgLogFacade) {
		this.membermsgLogFacade = membermsgLogFacade;
	}

	public Membermsg getMembermsg() {
		return membermsg;
	}

	public void setMembermsg(Membermsg membermsg) {
		this.membermsg = membermsg;
	}

	public List<MembermsgLog> getMembermsgLogList() {
		return membermsgLogList;
	}

	public void setMembermsgLogList(List<MembermsgLog> membermsgLogList) {
		this.membermsgLogList = membermsgLogList;
	}

	public Integer getLogCount() {
		return logCount;
	}

	public void setLogCount(Integer logCount) {
		this.logCount = logCount;
	}

	public boolean isShowDialogShow() {
		return showDialogShow;
	}

	public void setShowDialogShow(boolean showDialogShow) {
		this.showDialogShow = showDialogShow;
	}

	public List<Classteacher> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<Classteacher> teacherList) {
		this.teacherList = teacherList;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer[] getMembers() {
		return members;
	}

	public void setMembers(Integer[] members) {
		this.members = members;
	}

	public List<Membermsg> getMembermsgList() {
		return membermsgList;
	}

	public Integer getClassID() {
		return classID;
	}

	public void setClassID(Integer classID) {
		this.classID = classID;
	}

	public List<Membermsg> getMembermsgList2() {
		return membermsgList2;
	}

	public Integer getCallID() {
		return callID;
	}

	public void setCallID(Integer callID) {
		this.callID = callID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public void setMembermsgRelRecallFacade(
			MembermsgRelRecallFacade membermsgRelRecallFacade) {
		this.membermsgRelRecallFacade = membermsgRelRecallFacade;
	}

}
