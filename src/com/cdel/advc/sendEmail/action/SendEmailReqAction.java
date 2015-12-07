package com.cdel.advc.sendEmail.action;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.apache.commons.lang.StringUtils;

import com.cdel.advc.memberclass.domain.MemberClass;
import com.cdel.advc.memberclass.facade.MemberClassFacade;
import com.cdel.advc.sendEmail.domain.SendEmail;
import com.cdel.advc.sendEmail.facade.SendEmailFacde;
import com.cdel.util.DateUtil;
import com.cdel.util.MailUtil;
import com.cdel.util.BaseAction;

@SuppressWarnings("serial")
@ManagedBean
public class SendEmailReqAction extends BaseAction<SendEmail> implements
		Serializable {
	@ManagedProperty(value = "#{sendEmailFacde}")
	private SendEmailFacde sendEmailFacde;
	@ManagedProperty(value = "#{memberClassFacade}")
	private MemberClassFacade memberClassFacade;

	private SendEmail sendEmail = new SendEmail();
	private SendEmail emailDetail = new SendEmail();
	private byte submitSuccess = 0;

	/** 群发邮件显示页面 */
	public void showSendEmail(Integer classID) {
		sendEmail.setClassID(classID);
	}

	/** 群发班级邮件 */
	public void sendClassEmail() {
		try {
			if (sendEmailFacde.check(sendEmail)) {
				String content = mailContent(sendEmail.getContent());
				MemberClass mc = new MemberClass();
				mc.setStatus((short) 1);
				mc.setClassID(sendEmail.getClassID());
				List<MemberClass> memberList = memberClassFacade.findList(mc);
				if (memberList == null || memberList.size() == 0) {
					this.addWarnMessage("本班没有学员！");
					return;
				}
				Integer sendCount = 0;
				for (MemberClass member : memberList) {
					if (StringUtils.isBlank(member.getEmail())) {
						continue;
					}
					MailUtil themail = new MailUtil();
					boolean status = themail.mailSendOut(member.getEmail(),
							sendEmail.getTitle(), content);
					if (status) {
						sendCount = sendCount + 1;
					}
				}
				if (sendCount == 0) {
					this.addWarnMessage("本班下学员邮件都不合格！");
					return;
				}
				sendEmail.setContent(content);
				sendEmail.setSender(this.getCurrentUserID());
				sendEmail.setSendTime(new Date());
				sendEmailFacde.add(sendEmail);
				submitSuccess = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/** 编辑邮件 格式 */
	private String mailContent(String content) {
		String jiaowuName = "中华会计网校教务部";
		String contents = "亲爱的学员，您好！<br/><br/>";
		contents += "&nbsp;&nbsp;&nbsp;&nbsp;" + content;
		contents += "<br/><center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				+ jiaowuName
				+ "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				+ DateUtil.getNowToString("yyyy-MM-dd") + "</center>";
		contents += "<br><br> ---------------------------------------------------------------------------<br>本邮件为系统发送，请勿回复该邮件。";
		return contents;
	}

	public void showEmailDetail(Integer mailID) {
		emailDetail = sendEmailFacde.findByID(mailID);
	}

	public void setSendEmailFacde(SendEmailFacde sendEmailFacde) {
		this.sendEmailFacde = sendEmailFacde;
	}

	public SendEmail getSendEmail() {
		return sendEmail;
	}

	public void setSendEmail(SendEmail sendEmail) {
		this.sendEmail = sendEmail;
	}

	public void setMemberClassFacade(MemberClassFacade memberClassFacade) {
		this.memberClassFacade = memberClassFacade;
	}

	public SendEmail getEmailDetail() {
		return emailDetail;
	}

	public void setEmailDetail(SendEmail emailDetail) {
		this.emailDetail = emailDetail;
	}

}
