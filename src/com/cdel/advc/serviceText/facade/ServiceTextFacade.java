package com.cdel.advc.serviceText.facade;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.cdel.advc.serviceText.domain.ServiceText;
import com.cdel.util.BaseFacadeImpl;

/**
 * 学习建议业务层
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@Service
public class ServiceTextFacade extends BaseFacadeImpl<ServiceText, Integer> {

	public boolean checkServiceText(byte flag, ServiceText updateServiceText) {
		if (flag == 0) {
			if (updateServiceText.getMajorID() == null) {
				return addWarnMessage("必须选择辅导！");
			}
			if (updateServiceText.getCourseID() == null) {
				return addWarnMessage("必须选择课程！");
			}
			if (updateServiceText.getStageID() == null) {
				return addWarnMessage("必须选择阶段！");
			}
			if (updateServiceText.getServiceID() == null) {
				return addWarnMessage("必须选择服务项！");
			}
		}
		if (StringUtils.isBlank(updateServiceText.getContent())) {
			return addWarnMessage("内容不能为空！");
		}
		// 验证是否存在
		if (flag == 0) {
			if (hasSameServiceText(updateServiceText) > 0) {
				return addWarnMessage("已经添加过该课程、阶段、服务项的学习建议！");
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
	public int hasSameServiceText(ServiceText updateServiceText) {
		return (Integer) this.baseDao.get(updateServiceText,
				"hasSameServiceText");
	}

}
