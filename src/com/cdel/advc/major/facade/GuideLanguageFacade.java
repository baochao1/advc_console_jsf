package com.cdel.advc.major.facade;

import java.io.Serializable;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.cdel.advc.major.domain.GuideLanguage;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class GuideLanguageFacade extends BaseFacadeImpl<GuideLanguage, Integer>
		implements Serializable {

	/**
	 * 检验参数不能为空
	 * 
	 * @param flag
	 * @param guideLanguage
	 * @return
	 */
	public boolean checkGuideLanguage(byte flag, GuideLanguage guideLanguage) {
		if (flag == 0) {
			if (guideLanguage.getStageID() == null) {
				return addWarnMessage("阶段名称不能为空！");
			}
			if (guideLanguage.getServiceID() == null) {
				return addWarnMessage("服务项名称不能为空！");
			}
		}
		if (guideLanguage.getPromptKey() == null) {
			return addWarnMessage("提示类型不能为空！");
		} else {
			if (guideLanguage.getPromptKey() == 4) {
				if (guideLanguage.getEndTime() == null) {
					return addWarnMessage("结束时间不能为空！");
				}
			}
			if (guideLanguage.getPromptKey() == 5) {
				if (guideLanguage.getBeginTime() == null) {
					return addWarnMessage("开始时间不能为空！");
				}
			}
			if (guideLanguage.getPromptKey() == 6) {
				if (guideLanguage.getBeginTime() == null
						|| guideLanguage.getEndTime() == null) {
					return addWarnMessage("开始时间 和 结束时间都不能为空！");
				} else {
					if (guideLanguage.getBeginTime().getTime() > guideLanguage
							.getEndTime().getTime()) {
						return addWarnMessage("开始时间  不能大于结束时间!");
					}
				}
			}
		}
		if (StringUtils.isBlank(guideLanguage.getPromptContent())) {
			return addWarnMessage("提示内容不能为空！");
		}
		if (flag == 0 && checkIsNotGuideLanguage(guideLanguage)) {
			return addWarnMessage("该服务项下已经存在该提示类型的有效记录了！");
		}
		return true;
	}

	/**
	 * 检验 一个阶段 对应的一个服务项 是否存在对应的时间点
	 */
	public boolean checkIsNotGuideLanguage(GuideLanguage guideLanguage) {
		boolean f = false;
		if (this.baseDao.count(guideLanguage,
				"guidelanguage.getGuideLanguageCount") > 0) {
			f = true;
		}
		return f;
	}

}
