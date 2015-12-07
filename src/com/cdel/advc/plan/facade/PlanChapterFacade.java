/*
 * @Title: PlanChapterFacade.java
 * @Package com.cdel.advc.plan.facade
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-8-2 下午12:00:32
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-8-2                          
 */
package com.cdel.advc.plan.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.stereotype.Service;
import com.cdel.advc.plan.domain.PlanChapter;
import com.cdel.advc.plan.domain.WeekVo;
import com.cdel.util.BaseFacadeImpl;

/**
 * <p>
 * </p>
 * 
 * @author Du Haiying Create at:2013-8-2 下午12:00:32
 */
@SuppressWarnings("serial")
@Service
public class PlanChapterFacade extends BaseFacadeImpl<PlanChapter, Integer> {

	/**
	 * 查找学员学习计划下的章节计划列表
	 * 
	 * @param planID
	 *            主学习计划ID
	 * @param type
	 * @return
	 */
	public List<PlanChapter> getPlanChaptersByPlanID(Integer planID, Short type) {

		if (planID == null || type == null) {
			throw new IllegalArgumentException("非法参数！");
		}
		PlanChapter pc = new PlanChapter();
		pc.setPlanID(planID);
		pc.setType(type);

		return this.baseDao.findList(pc, "getPlanChaptersByPlanID");
	}

	/**
	 * 以周次分组显示 计划项
	 */
	public Map<WeekVo, List<PlanChapter>> toMap(List<PlanChapter> list) {
		Map<WeekVo, List<PlanChapter>> map = null;
		if (list != null) {
			map = new TreeMap<WeekVo, List<PlanChapter>>();
			PlanChapter chapter = null;// 临时变量 章节
			WeekVo week = null;// 临时变量 周次VO
			Short planWeek = 0;// 临时变量 周次
			List<PlanChapter> weekChapterList = null;// 临时变量 周次下的章节列表
			for (int i = 0, size = list.size(); i < size; i++) {
				chapter = list.get(i);
				planWeek = chapter.getPlanWeek();
				week = new WeekVo();
				week.setWeekNo(planWeek);

				if ((weekChapterList = map.get(week)) != null) {// 周次已存在的情况
					weekChapterList.add(chapter);
				} else {// 周次不存在的情况
					week.setWeekStartTime(chapter.getWeekStartTime());
					week.setWeekEndTime(chapter.getWeekEndTime());
					weekChapterList = new ArrayList<PlanChapter>();
					weekChapterList.add(chapter);

					map.put(week, weekChapterList);
				}
			}
		}
		return map;
	}

}
