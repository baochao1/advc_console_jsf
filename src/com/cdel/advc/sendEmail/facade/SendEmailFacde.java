package com.cdel.advc.sendEmail.facade;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdel.advc.classes.domain.Classes;
import com.cdel.advc.classes.facade.ClassesFacade;
import com.cdel.advc.sendEmail.domain.SendEmail;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class SendEmailFacde extends BaseFacadeImpl<SendEmail, Integer> {
	@Autowired
	private ClassesFacade classesFacade;

	/**
	 * 验证邮件
	 * 
	 * @param msg
	 * @param context
	 * @param course
	 * @param siteID
	 * @return
	 */
	public boolean check(SendEmail sendEmail) {
		if (StringUtils.isBlank(sendEmail.getTitle())) {
			return this.addWarnMessage("邮件标题不能为空！");
		}
		if (StringUtils.isBlank(sendEmail.getContent())) {
			return this.addWarnMessage("邮件内容不能为空！");
		}
		Classes c = classesFacade.findByID(sendEmail.getClassID());
		if (c.getStatus() == 0) {
			return this.addWarnMessage("本班已关闭,不能为本班人员发送邮件！");
		}
		return true;
	}

}
