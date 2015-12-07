package com.cdel.advc.smsTemplate.facade;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdel.advc.smsTemplate.domain.SendRuleOne;
import com.cdel.advc.smsTemplate.domain.SendRuleTwo;
import com.cdel.advc.smsTemplate.domain.SmsTemplate;
import com.cdel.advc.stageRelative.domain.StageRelativeService;
import com.cdel.advc.stageRelative.facade.StageRelativeServiceFacade;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.DateUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 发送短信模板业务层
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@Service
public class SmsTemplateFacade extends BaseFacadeImpl<SmsTemplate, Integer> {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private StageRelativeServiceFacade stageRelativeServiceFacade;

	public boolean checkTemplate(byte flag, SmsTemplate smsTemplate) {
		if (flag == 0) {
			if (smsTemplate.getStageID() == null) {
				return addWarnMessage("必须选择阶段！");
			}
			if (smsTemplate.getSendType() == null) {
				return addWarnMessage("必须选择发送类别！");
			}
		}
		if (smsTemplate.getMajorID() == null) {
			return addWarnMessage("辅导不能为空！");
		}
		if (StringUtils.isBlank(smsTemplate.getTitle())) {
			return addWarnMessage("模板名称不能为空！");
		}
		if (StringUtils.isBlank(smsTemplate.getSendContent())) {
			return addWarnMessage("模板内容不能为空！");
		}
		return true;
	}

	/**
	 * 生成下次发送短信的时间点
	 * 
	 * @param majorID
	 * @param stageID
	 * @param startTime
	 *            ：开始时间
	 * @param endTime
	 *            ：阶段结束时间
	 * @return
	 */
	public boolean createSendTime(Integer smsStageRelID, Integer planID,
			Integer majorID, Integer stageID, Date startTime, Date endTime,
			Integer userID, Integer classID) {
		try {
			SmsTemplate st = new SmsTemplate();
			st.setMajorID(majorID);
			st.setStageID(stageID);
			st.setStatus((short) 1);
			List<SmsTemplate> stList = this.findList(st);
			if (stList != null && stList.size() > 0) {
				for (int i = 0; i < stList.size(); i++) {
					st = stList.get(i);
					Gson gson = new Gson();
					Date serviceTime = null;
					if (StringUtils.isBlank(st.getSendRule())) {
						logger.warn("TemplateID=" + st.getTemplateID()
								+ ",SendType=" + st.getSendType() + "的模板没有设置规则");
						return true;
					}
					if (st.getSendType() == 1) {
						SendRuleOne sendRuleOne = gson.fromJson(
								st.getSendRule(), new TypeToken<SendRuleOne>() {
								}.getType());
						if (sendRuleOne.getStartOrEnd() == 1) {
							// 开始后
							serviceTime = DateUtil.getAfterDate(startTime,
									sendRuleOne.getDayNum(),
									sendRuleOne.getHour());
							if (serviceTime.compareTo(endTime) > 0) {
								serviceTime = DateUtil.getStartDayTime(endTime,
										sendRuleOne.getHour(), (short) 0,
										(short) 0);
							}
						} else {
							// 结束前
							serviceTime = DateUtil.getAfterDate(endTime,
									sendRuleOne.getDayNum() * -1,
									sendRuleOne.getHour());
							if (serviceTime.compareTo(startTime) < 0) {
								serviceTime = DateUtil.getStartDayTime(
										startTime, sendRuleOne.getHour(),
										(short) 0, (short) 0);
							}
						}
					} else {
						SendRuleTwo sendRuleTwo = gson.fromJson(
								st.getSendRule(), new TypeToken<SendRuleTwo>() {
								}.getType());
						serviceTime = DateUtil.getWeekDay(
								sendRuleTwo.getWeekInterval(),
								sendRuleTwo.getWeekDay(),
								sendRuleTwo.getHour(), startTime, endTime);
						if (serviceTime != null
								&& serviceTime.compareTo(endTime) > 0) {
							serviceTime = null;
						}
					}
					if (serviceTime != null) {
						StageRelativeService srs = new StageRelativeService();
						srs.setSmsStageRelID(smsStageRelID);
						srs.setServiceTime(serviceTime);
						srs.setTemplateID(st.getTemplateID());
						srs.setStatus((short) 1);
						StageRelativeService tmp = stageRelativeServiceFacade
								.findByObject(srs);
						if (tmp == null) {
							stageRelativeServiceFacade.add(srs);
						} else {
							stageRelativeServiceFacade.update(srs);
						}
					}
				}
			} else {
				logger.info("未设置发送规则，majorID=" + majorID + ",stageID="
						+ stageID);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
