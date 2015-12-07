/*
 * @Title: TeacherFacade.java
 * @Package com.cdel.advc.teacher.facade
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-7-2 下午2:55:05
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-7-2                          
 */
package com.cdel.qz.score.point.facade;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.cdel.qz.score.point.domain.PointScore;
import com.cdel.util.BaseFacadeImpl;

/**
 * 知识点测试结果
 * 
 * @author 张苏磊
 */
@SuppressWarnings("serial")
@Service
public class PointScoreFacade extends BaseFacadeImpl<PointScore, Integer> {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<PointScore> getRptPointStatList(Map map) {
		return this.baseDao.findList(map, "getRptPointStatList");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<PointScore> getRptPointList(Map map) {
		return this.baseDao.findList(map, "getRptPointList");
	}

}
