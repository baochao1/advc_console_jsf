package com.cdel.advc.memberSms.action;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.apache.commons.lang.StringUtils;
import com.cdel.advc.member.domain.Member;
import com.cdel.advc.member.facade.MemberFacade;
import com.cdel.advc.memberSms.domain.MemberSms;
import com.cdel.advc.memberSms.facade.MemberSmsFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.ExceptionUtil;

/**
 * 学员发短信
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
public class MemberSmsReqAction extends BaseAction<MemberSms> {
	@ManagedProperty("#{memberSmsFacade}")
	private MemberSmsFacade memberSmsFacade;
	@ManagedProperty("#{memberFacade}")
	private MemberFacade memberFacade;

	private MemberSms memberSms = new MemberSms();
	private Member member = new Member();

	private byte submitSuccess = 0;

	/**
	 * 显示给个人发短信页面
	 * 
	 * @param classID
	 * @param userID
	 *            ：classID = 0特指高端班
	 */
	public void showSms(Integer classID, Integer userID) {
		MemberSmsAction smsAction = (MemberSmsAction) this
				.getViewAction("memberSmsAction");
		smsAction.setClassID(classID);
		smsAction.setUserID(userID);
		smsAction.initSearch();
		member = memberFacade.findByID(userID);
	}

	/**
	 * 修改学员接收短信状态
	 * 
	 * @param userID
	 * @param smsStatus
	 */
	public void changeSmsStatus() {
		Short newStatus;
		if (member.getSmsStatus() == 1) {
			newStatus = 0;
		} else {
			newStatus = 1;
		}
		member.setSmsStatus(newStatus);
		try {
			memberFacade.update(member);
			this.addInfoMessage("info", SUCESSINFO);
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
		}
	}

	/**
	 * 发送短信
	 * 
	 * @param userID
	 * @param classID
	 */
	public void updateSubmit() {
		try {
			if (memberSmsFacade.checkSms(memberSms, member)) {
				MemberSmsAction smsAction = (MemberSmsAction) this
						.getViewAction("memberSmsAction");
				memberSms.setUserID(member.getUserID());
				memberSms.setClassID(smsAction.getClassID());
				memberSms.setMobile(member.getTelPhone());
				memberSms.setSendTime(new Date());
				memberSms.setSender(this.getCurrentUserID());
				memberSms.setStatus((short) 1);
				this.memberSmsFacade.addMemberSms(memberSms);
				submitSuccess = 1;
				smsAction.search();
			}
		} catch (Exception e) {
			submitSuccess = -1;
			e.printStackTrace();
			logger.error(ExceptionUtil.getEMsg(e));
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 显示给班级成员发送短信页面
	 * 
	 * @param classID
	 */
	public void showClassSms(Integer classID) {
		memberSms.setClassID(classID);
	}

	/**
	 * 给班级成员发送短信
	 */
	public void updateSubmits() {
		memberSms.setSender(this.getCurrentUserID());
		if (StringUtils.isBlank(memberSms.getContent())) {
			this.addWarnMessage("短信内容不能为空！");
			return;
		}
		try {
			this.memberSmsFacade.addClassMemberSmss(memberSms);
			submitSuccess = 1;
		} catch (Exception e) {
			submitSuccess = -1;
			e.printStackTrace();
			logger.error(ExceptionUtil.getEMsg(e));
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public MemberSms getMemberSms() {
		return memberSms;
	}

	public void setMemberSms(MemberSms memberSms) {
		this.memberSms = memberSms;
	}

	public void setMemberSmsFacade(MemberSmsFacade memberSmsFacade) {
		this.memberSmsFacade = memberSmsFacade;
	}

	public void setMemberFacade(MemberFacade memberFacade) {
		this.memberFacade = memberFacade;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}
