package com.cdel.advc.innermsg.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.apache.commons.lang.StringUtils;
import com.cdel.advc.classteacher.action.ClassteacherOther;
import com.cdel.advc.innermsg.domain.InnerMsg;
import com.cdel.advc.innermsg.domain.InnerMsgReceive;
import com.cdel.advc.innermsg.facade.InnerMsgFacade;
import com.cdel.advc.innermsg.facade.InnerMsgReceiveFacade;
import com.cdel.advc.memberclass.action.MemberClassOther;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;
import com.cdel.util.ContantsUrl;
import com.cdel.util.ExceptionUtil;

@SuppressWarnings("serial")
@ManagedBean
public class InnerMsgReqAction extends BaseAction<InnerMsg> implements
		Serializable {
	@ManagedProperty(value = "#{innerMsgFacade}")
	private InnerMsgFacade innerMsgFacade;
	@ManagedProperty(value = "#{innerMsgReceiveFacade}")
	private InnerMsgReceiveFacade innerMsgReceiveFacade;

	private InnerMsg seaInnerMsg = new InnerMsg();
	private InnerMsg detailInnerMsg = new InnerMsg();
	private String faqurl = ContantsUrl.FAQ_MESSAGE_URL;
	private String secret = this.getAttribute(Contants.ADMIN_PASSWORD);
	private String userName = this.getCurrentUserName();
	private List<InnerMsg> selectInnerMsg;// 选中的列
	private boolean showBtn = true;
	private String[] innerMsgManagers;// 已经选中的管理员
	private byte submitSuccess = 0;

	/**
	 * 修改状态
	 */
	public void changeStatus(Integer innerMsgID, Integer status) {
		try {
			innerMsgFacade.changeStatus(innerMsgID, status);
			this.addMessage("info", SUCESSINFO);
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
		}
	}

	/**
	 * 批量标记
	 */
	public void markRead() {
		try {
			if (selectInnerMsg != null && selectInnerMsg.size() > 0) {
				List<InnerMsg> updInnerMsgList = new ArrayList<InnerMsg>();
				List<InnerMsgReceive> updInnerMsgRList = new ArrayList<InnerMsgReceive>();
				InnerMsgReceive imr;
				for (int i = 0; i < selectInnerMsg.size(); i++) {
					InnerMsg im = selectInnerMsg.get(i);
					if (im.getInnerMsgID() != null) {
						im.setInnerMsgContent(innerMsgFacade.updateContent(
								im.getInnerMsgContent(), "",
								this.getCurrentRealName()));
						updInnerMsgList.add(im);
						if (im.getReadStatus() == null
								|| im.getReadStatus() == 0) {
							imr = new InnerMsgReceive();
							imr.setReceiveID(im.getReceiveID());
							imr.setReadStatus((short) 1);
							imr.setReadTime(new Date());
							updInnerMsgRList.add(imr);
						}
					}
				}
				// 更新沟通消息
				innerMsgFacade.update(updInnerMsgList);
				// 更新阅读状态
				innerMsgReceiveFacade.update(updInnerMsgRList);
				submitSuccess = 1;
				InnerMsgAction innerMsgAction = (InnerMsgAction) this
						.getViewAction("innerMsgAction");
				innerMsgAction.search4U();
				this.addMessage("info", SUCESSINFO);
			} else {
				this.addWarnMessage("info", "请至少选中一条记录！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
		}
	}

	/**
	 * 查看接收人
	 * 
	 * @param innerMsgID
	 */
	public void getRecerveList(Integer innerMsgID, byte flag) {
		seaInnerMsg.setReceiverName(innerMsgReceiveFacade.getReceiverStr(
				innerMsgID, flag));
	}

	/**
	 * 查看详情
	 * 
	 * @param innerMsgID
	 */
	public void getRecerveDetail(Integer innerMsgID, Integer receiveID,
			byte flag) {
		if (flag == 1) {
			showBtn = false;
		} else {
			InnerMsgAction ia = (InnerMsgAction) this
					.getViewAction("innerMsgAction");
			showBtn = ia.isShowBtn();
		}
		detailInnerMsg = innerMsgFacade.getRecerveDetail(innerMsgID);
		detailInnerMsg.setReceiveID(receiveID);
	}

	/**
	 * 提交修改
	 */
	public void updateSubmit() {
		if (StringUtils.isBlank(detailInnerMsg.getReplyContent())) {
			this.addWarnMessage("回复内容不能为空！");
			return;
		}
		try {
			detailInnerMsg
					.setInnerMsgContent(innerMsgFacade.updateContent(
							detailInnerMsg.getInnerMsgContent(),
							detailInnerMsg.getReplyContent(),
							this.getCurrentRealName()));
			innerMsgFacade.update(detailInnerMsg);
			InnerMsgReceive imr = innerMsgReceiveFacade.findByID(detailInnerMsg
					.getReceiveID());
			if (imr.getReadStatus() == null || imr.getReadStatus() == 0) {
				imr.setReadStatus((short) 1);
				imr.setReadTime(new Date());
				innerMsgReceiveFacade.update(imr);
			}
			this.updateComponent("searchForm:innerMsgList");
			submitSuccess = 1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("detailInnerMsg=" + detailInnerMsg);
			logger.error(ExceptionUtil.getEMsg(e));
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 显示发送沟通消息界面(指定成员)
	 */
	public void showSendInnermsg(String userName, Integer classID) {
		detailInnerMsg.setReferUser(userName);
		detailInnerMsg.setReferClass(classID);
		ClassteacherOther inner = (ClassteacherOther) this
				.getViewAction("classteacherOther");
		inner.setManagers(classID);
	}

	/**
	 * 显示发送沟通消息界面(不指定成员)
	 */
	public void showSendInnermsg(Integer classID) {
		MemberClassOther mcr = (MemberClassOther) this
				.getViewAction("memberClassOther");
		mcr.setMemberList(classID);
		detailInnerMsg.setReferClass(classID);
		ClassteacherOther inner = (ClassteacherOther) this
				.getViewAction("classteacherOther");
		inner.setManagers(classID);
	}

	/**
	 * 新增沟通消息(班级成员)
	 * 
	 * @param:flag=0指定成员,=1不指定成员
	 */
	public void addInnerMsg(byte flag) {
		try {
			if (innerMsgFacade.checkMsg(detailInnerMsg, innerMsgManagers)) {
				detailInnerMsg.setSender(this.getCurrentUserID());
				innerMsgFacade.addInnerMsg(innerMsgManagers, detailInnerMsg);
				submitSuccess = 1;
			}
		} catch (Exception e) {
			submitSuccess = -1;
			e.printStackTrace();
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public Map<Short, String> getStatus() {
		return Contants.status;
	}

	public Map<Short, String> getReadStatus() {
		return Contants.readStatus;
	}

	public Map<Short, String> getMailType() {
		return Contants.mailType;
	}

	public void setInnerMsgFacade(InnerMsgFacade innerMsgFacade) {
		this.innerMsgFacade = innerMsgFacade;
	}

	public InnerMsg getSeaInnerMsg() {
		return seaInnerMsg;
	}

	public void setSeaInnerMsg(InnerMsg seaInnerMsg) {
		this.seaInnerMsg = seaInnerMsg;
	}

	public InnerMsg getDetailInnerMsg() {
		return detailInnerMsg;
	}

	public void setDetailInnerMsg(InnerMsg detailInnerMsg) {
		this.detailInnerMsg = detailInnerMsg;
	}

	public List<InnerMsg> getSelectInnerMsg() {
		return selectInnerMsg;
	}

	public void setSelectInnerMsg(List<InnerMsg> selectInnerMsg) {
		this.selectInnerMsg = selectInnerMsg;
	}

	public String getFaqurl() {
		return faqurl;
	}

	public void setFaqurl(String faqurl) {
		this.faqurl = faqurl;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setInnerMsgReceiveFacade(
			InnerMsgReceiveFacade innerMsgReceiveFacade) {
		this.innerMsgReceiveFacade = innerMsgReceiveFacade;
	}

	public boolean isShowBtn() {
		return showBtn;
	}

	public void setShowBtn(boolean showBtn) {
		this.showBtn = showBtn;
	}

	public String[] getInnerMsgManagers() {
		return innerMsgManagers;
	}

	public void setInnerMsgManagers(String[] innerMsgManagers) {
		this.innerMsgManagers = innerMsgManagers;
	}

}
