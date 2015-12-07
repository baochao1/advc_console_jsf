package com.cdel.qz.score.selfhelp.facade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.cdel.qz.score.selfhelp.domain.SelfHelp;
import com.cdel.util.BaseFacadeImpl;

/**
 * 知识点测试结果
 * 
 * @author 张苏磊
 */
@SuppressWarnings("serial")
@Service
public class SelfHelpFacade extends BaseFacadeImpl<SelfHelp, Integer> {

	public List<SelfHelp> showList(Integer userID, List<Integer> siteCourseIDList) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("creator", userID);
		map.put("siteCourseIDList", siteCourseIDList);
		return this.baseDao.findList(map, "findPageSelfHelp");
	}

}
