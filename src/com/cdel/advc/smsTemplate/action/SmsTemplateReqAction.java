package com.cdel.advc.smsTemplate.action;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.smsTemplate.domain.SmsTemplate;
import com.cdel.advc.smsTemplate.facade.SmsTemplateFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;
import com.cdel.util.ExceptionUtil;

/**
 * 发送短信模板
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
public class SmsTemplateReqAction extends BaseAction<SmsTemplate> implements
		Serializable {

	@ManagedProperty(value = "#{smsTemplateFacade}")
	private SmsTemplateFacade smsTemplateFacade;

	private Integer majorID;
	private SmsTemplate updateSmsTemplate = new SmsTemplate();
	private byte submitSuccess = 0;// 添加是否成功
	private byte flag = 0;

	/**
	 * 添加
	 */
	public void add(byte flag) {
		SmsTemplateAction sa = (SmsTemplateAction) this
				.getViewAction("smsTemplateAction");
		majorID = sa.getMajorID();
		this.flag = flag;
	}

	/**
	 * 更新状态
	 * 
	 * @throws Exception
	 */
	public void changeStatus(Integer templateID, Short status) throws Exception {
		SmsTemplateAction sa = (SmsTemplateAction) this
				.getViewAction("smsTemplateAction");
		if (templateID != null && status != null) {
			this.updateSmsTemplate = new SmsTemplate();
			this.updateSmsTemplate.setTemplateID(templateID);
			if (status == 1) {
				this.updateSmsTemplate.setStatus((short) 0);
			} else {
				this.updateSmsTemplate.setStatus((short) 1);
			}

			this.smsTemplateFacade.update(updateSmsTemplate);
			sa.search4U();
			this.addMessage("info", "设置成功！");
		}
	}

	/**
	 * 查看
	 */
	public void show(Integer templateID) {
		updateSmsTemplate = smsTemplateFacade.findByID(templateID);
	}

	/**
	 * 修改
	 */
	public void update(byte flag, Integer templateID, Integer majorID) {
		updateSmsTemplate = smsTemplateFacade.findByID(templateID);
		this.flag = flag;
		this.majorID = majorID;
	}

	/**
	 * 提交修改
	 */
	public void updateSubmit() {
		SmsTemplateAction sa = (SmsTemplateAction) this
				.getViewAction("smsTemplateAction");
		updateSmsTemplate.setMajorID(majorID);
		if (smsTemplateFacade.checkTemplate(flag, updateSmsTemplate)) {
			try {
				if (flag == 0) {
					updateSmsTemplate.setCreator(this.getCurrentUserID());
					updateSmsTemplate.setCreateTime(new Date());
					updateSmsTemplate.setStatus((short) 1);
					smsTemplateFacade.add(updateSmsTemplate);
					sa.search();
				} else {
					smsTemplateFacade.update(updateSmsTemplate);
					sa.search4U();
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

	public Map<Short, String> getStatus() {
		return Contants.status;
	}

	public Map<Byte, String> getWeek() {
		return Contants.week;
	}

	public Map<Byte, String> getStartOrEnd() {
		return Contants.startOrEnd;
	}

	public Map<Byte, String> getSendType() {
		return Contants.sendType;
	}

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}

	public SmsTemplate getUpdateSmsTemplate() {
		return updateSmsTemplate;
	}

	public void setUpdateSmsTemplate(SmsTemplate updateSmsTemplate) {
		this.updateSmsTemplate = updateSmsTemplate;
	}

	public void setSmsTemplateFacade(SmsTemplateFacade smsTemplateFacade) {
		this.smsTemplateFacade = smsTemplateFacade;
	}

	public byte getFlag() {
		return flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

}
