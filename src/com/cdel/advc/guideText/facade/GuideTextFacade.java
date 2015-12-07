package com.cdel.advc.guideText.facade;

import java.io.Serializable;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.cdel.advc.guideText.domain.GuideText;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class GuideTextFacade extends BaseFacadeImpl<GuideText, Integer>
		implements Serializable {

	public boolean checkGuideText(byte flag, GuideText updateGuideText) {
		if (flag == 0) {
			if (updateGuideText.getStageID() == null) {
				return addWarnMessage("必须选择阶段！");
			}
			if (updateGuideText.getMajorID() == null) {
				return addWarnMessage("必须选择辅导！");
			}
			if (updateGuideText.getType() == null) {
				return addWarnMessage("类型不能为空！");
			}
		}
		if (StringUtils.isBlank(updateGuideText.getContent())) {
			return addWarnMessage("内容不能为空！");
		}
		if (updateGuideText.getLeftDays() == null) {
			return addWarnMessage("剩余天数不能为空！");
		}
		// 验证是否存在
		if (flag == 0) {
			if (hasSameGuideText(updateGuideText) > 0) {
				return addWarnMessage("已经添加过该辅导、阶段、类型的引导语！");
			}
		}
		return true;
	}

	/**
	 * 判断是否重复添加
	 * 
	 * @param updateGuideText
	 * @return
	 */
	public int hasSameGuideText(GuideText updateGuideText) {
		return (Integer) this.baseDao.get(updateGuideText, "hasSameGuideText");
	}

}
