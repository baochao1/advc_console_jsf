package com.cdel.advc.stageRelative.facade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdel.advc.stageRelative.domain.StageRelative;
import com.cdel.util.BaseFacadeImpl;

/**
 * 学习计划与阶段服务时间关系业务层
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@Service
public class StageRelativeFacade extends BaseFacadeImpl<StageRelative, Integer> {

	@Autowired
	private StageRelativeServiceFacade stageRelativeServiceFacade;

	/**
	 * 根据学习计划明细返回与阶段服务时间关系
	 * 
	 * @param planID
	 * @return
	 */
	public List<StageRelative> getUserPlanStageStat(Integer planID) {
		return this.baseDao.findList(planID, "getUserPlanStageStat");
	}

	/**
	 * 根据预习计划明细返回与阶段服务时间关系
	 * 
	 * @param planID
	 * @return
	 */
	public List<StageRelative> getUserPrePlanStageStat(Integer planID) {
		return this.baseDao.findList(planID, "getUserPrePlanStageStat");
	}

	/**
	 * 判断该planID是否生成过学习计划与阶段服务时间关系
	 * 
	 * @param planID
	 * @return
	 */
	public Integer getStageRelativeCount(Integer planID) {
		return this.baseDao.get(planID, "getStageRelativeCount");
	}

	/**
	 * 退班时/重新入班发短信规则置为无效/有效
	 * 
	 * @param sr
	 */
	public void update4RemoveClass(StageRelative sr) {
		this.baseDao.update(sr, "update4RemoveClass");
	}

	/**
	 * 退班时删除发短信规则
	 * 
	 * @param sr
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void delete4RemoveClass(StageRelative sr) {
		Map map = new HashMap();
		map.put("userID", sr.getUserID());
		map.put("classID", sr.getClassID());
		map.put("stageID", sr.getStageID());
		stageRelativeServiceFacade.delete(map);
		this.baseDao.delete(sr, "delete4RemoveClass");
	}

	/**
	 * 学员换班后，把发短信规则对应到新的班级
	 * 
	 * @param newClassID
	 * @param oldClassID
	 * @param userID
	 */
	public void updateStageRelative(Integer newClassID, Integer oldClassID,
			Integer userID) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("newClassID", newClassID);
		map.put("classID", oldClassID);
		map.put("userID", userID);
		baseDao.update(map, "updateStageRelativeToNewClass");
	}

}
