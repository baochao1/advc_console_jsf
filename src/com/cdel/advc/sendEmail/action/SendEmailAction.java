package com.cdel.advc.sendEmail.action;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.sendEmail.domain.SendEmail;
import com.cdel.advc.sendEmail.facade.SendEmailFacde;
import com.cdel.util.BaseAction;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class SendEmailAction extends BaseAction<SendEmail> implements
		Serializable {

	@ManagedProperty("#{sendEmailFacde}")
	private SendEmailFacde sendEmailFacde;

	/** 分页模型 对象 */
	private LazyDataModel<SendEmail> page;
	private SendEmail secrchSendEmail = new SendEmail();

	@PostConstruct
	public void initSendEmailAction() {
		secrchSendEmail.setClassID(this.getIntegerParameter("classID"));
		page = sendEmailFacde.findPage(secrchSendEmail);
		super.heighti2 = super.calHeight(18f / 20);
	}

	public void setSendEmailFacde(SendEmailFacde sendEmailFacde) {
		this.sendEmailFacde = sendEmailFacde;
	}

	public LazyDataModel<SendEmail> getPage() {
		return page;
	}

	public void setPage(LazyDataModel<SendEmail> page) {
		this.page = page;
	}

}
