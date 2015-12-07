package com.cdel.advc.stageRelative.facade;

import org.springframework.stereotype.Service;
import com.cdel.advc.stageRelative.domain.StageRelativeService;
import com.cdel.util.BaseFacadeImpl;

/**
 * 学习计划的阶段服务与发短信时间关系业务层
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@Service
public class StageRelativeServiceFacade extends
		BaseFacadeImpl<StageRelativeService, Integer> {

	/**
	 * 退班时/重新入班发短信规则置为无效/有效
	 * 
	 * @param sr
	 */
	public void updateService4RemoveClass(StageRelativeService sr) {
		this.baseDao.update(sr, "updateService4RemoveClass");
	}

}
