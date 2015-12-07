/*
 * @Title: PlanLogFacade.java
 * @Package com.cdel.advc.plan.facade
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-8-1 上午8:53:15
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-8-1                          
 */
package com.cdel.advc.plan.facade;

import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.cdel.advc.plan.domain.PlanLog;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.DateUtil;

/**
 * <p>
 * 学习计划修改日志 facade层
 * </p>
 * 
 * @author Du Haiying Create at:2013-8-1 上午8:53:15
 */
@SuppressWarnings("serial")
@Service
public class PlanLogFacade extends BaseFacadeImpl<PlanLog, Integer> {

	/**
	 * 生成学习计划时，记录日志；
	 * 
	 * 日志描述不用指定，由方法生成
	 * 
	 * @param log
	 * @throws Exception
	 */
	public void addLogOnAddPlan(PlanLog log) throws Exception {
		if (log == null || log.getPlanID() == null || log.getCreator() == null
				|| StringUtils.isBlank(log.getCreatorName())) {
			throw new IllegalArgumentException("非法参数！");
		}

		log.setCreateTime(new Date());
		log.setPlanLogDesc(log.getCreatorName()
				+ "于"
				+ DateUtil.getNowDateString(log.getCreateTime(),
						"yyyy-MM-dd HH:mm:ss") + "生成学习计划");

		this.add(log);
	}

	/**
	 * 添加日志
	 */
	public void addLog(Integer planID, Integer creator, String creatorName,
			String desc) throws Exception {
		if (planID == null || creator == null
				|| StringUtils.isBlank(creatorName)
				|| StringUtils.isBlank(desc)) {
			throw new IllegalArgumentException("非法参数！");
		}
		PlanLog log = new PlanLog();
		log.setPlanID(planID);
		log.setCreator(creator);
		log.setCreatorName(creatorName);
		log.setCreateTime(new Date());
		log.setPlanLogDesc(log.getCreatorName()
				+ "于"
				+ DateUtil.getNowDateString(log.getCreateTime(),
						"yyyy-MM-dd HH:mm:ss") + desc);

		this.add(log);
	}

}
