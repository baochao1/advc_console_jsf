package com.cdel.advc.smsTemplate.action;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang.StringUtils;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.major.domain.Major;
import com.cdel.advc.smsTemplate.domain.SendRuleOne;
import com.cdel.advc.smsTemplate.domain.SendRuleTwo;
import com.cdel.advc.smsTemplate.domain.SmsTemplate;
import com.cdel.advc.smsTemplate.facade.SmsTemplateFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.ExceptionUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 发送短信模板
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class SmsTemplateAction extends BaseAction<Major> implements
		Serializable {

	@ManagedProperty(value = "#{smsTemplateFacade}")
	private SmsTemplateFacade smsTemplateFacade;

	private Integer majorID;
	private LazyDataModel<SmsTemplate> page;
	private SmsTemplate searchSmsTemplate = new SmsTemplate();// 搜索的课程
	private SendRuleOne sendRuleOne = new SendRuleOne();
	private SendRuleTwo sendRuleTwo = new SendRuleTwo();
	private SmsTemplate updateSmsTemplate = new SmsTemplate();

	@PostConstruct
	public void initSmsTemplateAction() {
		majorID = this.getIntegerParameter("majorID");
		searchSmsTemplate.setMajorID(majorID);
		searchSmsTemplate.setStatus((short) 1);
		page = smsTemplateFacade.findPage(searchSmsTemplate);
		super.heighti2 = super.calHeight(14f / 20);
	}

	/**
	 * 查询
	 */
	public void search() {
		pageTable.setFirst(0);
		search4U();
	}

	/**
	 * 更新查询
	 */
	public void search4U() {
		page = smsTemplateFacade.findPage(searchSmsTemplate);
		this.updateComponent("searchForm:smsTemplateList");
	}

	/**
	 * 打开策略设置页面
	 * 
	 * @param templateID
	 * @param majorID
	 */
	public void setRule(Integer templateID, Integer stageID) {
		updateSmsTemplate = smsTemplateFacade.findByID(templateID);
		Gson gson = new Gson();
		String sendRule = updateSmsTemplate.getSendRule();
		if (updateSmsTemplate.getSendType() == 1) {
			if (StringUtils.isBlank(sendRule)) {
				sendRuleOne.setDayNum(1);
				sendRuleOne.setHour((short) 9);
				sendRuleOne.setStageID(stageID);
			} else {
				sendRuleOne = gson.fromJson(sendRule,
						new TypeToken<SendRuleOne>() {
						}.getType());
			}
		} else {
			if (StringUtils.isBlank(sendRule)) {
				sendRuleTwo.setHour((short) 9);
				sendRuleTwo.setWeekInterval((short) 2);
				sendRuleTwo.setWeekDay((byte) 1);
			} else {
				sendRuleTwo = gson.fromJson(sendRule,
						new TypeToken<SendRuleTwo>() {
						}.getType());
			}
		}
	}

	/**
	 * 提交策略设置
	 */
	public void setRuleSubmit() {
		byte submitSuccess = 0;
		try {
			Gson gson = new Gson();
			String sendRule = null;
			if (updateSmsTemplate.getSendType() == 1) {
				sendRule = gson.toJson(sendRuleOne);
			} else {
				sendRule = gson.toJson(sendRuleTwo);
			}
			updateSmsTemplate.setSendRule(sendRule);
			smsTemplateFacade.update(updateSmsTemplate);
			submitSuccess = 1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtil.getEMsg(e));
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public void setSmsTemplateFacade(SmsTemplateFacade smsTemplateFacade) {
		this.smsTemplateFacade = smsTemplateFacade;
	}

	public Integer getMajorID() {
		return majorID;
	}

	public LazyDataModel<SmsTemplate> getPage() {
		return page;
	}

	public void setPage(LazyDataModel<SmsTemplate> page) {
		this.page = page;
	}

	public SmsTemplate getSearchSmsTemplate() {
		return searchSmsTemplate;
	}

	public void setSearchSmsTemplate(SmsTemplate searchSmsTemplate) {
		this.searchSmsTemplate = searchSmsTemplate;
	}

	public SendRuleOne getSendRuleOne() {
		return sendRuleOne;
	}

	public void setSendRuleOne(SendRuleOne sendRuleOne) {
		this.sendRuleOne = sendRuleOne;
	}

	public SendRuleTwo getSendRuleTwo() {
		return sendRuleTwo;
	}

	public void setSendRuleTwo(SendRuleTwo sendRuleTwo) {
		this.sendRuleTwo = sendRuleTwo;
	}

	public SmsTemplate getUpdateSmsTemplate() {
		return updateSmsTemplate;
	}

	public void setUpdateSmsTemplate(SmsTemplate updateSmsTemplate) {
		this.updateSmsTemplate = updateSmsTemplate;
	}

}
