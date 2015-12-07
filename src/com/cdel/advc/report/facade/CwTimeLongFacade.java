package com.cdel.advc.report.facade;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.springframework.stereotype.Service;
import com.cdel.advc.courseware.domain.Courseware;
import com.cdel.advc.report.domain.CwTimeLong;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.Contants;

@SuppressWarnings("serial")
@Service
public class CwTimeLongFacade extends BaseFacadeImpl<CwTimeLong, Integer>
		implements Serializable {
	/**
	 * 返回平均时长
	 * 
	 * @param map
	 * @return
	 */
	public Integer getAvgTime(List<String> cwIDList, String countDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Contants.DATA_KEY, Contants.COURSE_WARE);
		map.put("cwIDList", cwIDList);
		map.put("countDate", countDate);
		Integer sum = this.baseDao.get(map, "getAvgTime");
		return sum == null ? 0 : sum.intValue();
	}

	/**
	 * 获取每一章节的看课时长等信息
	 * 
	 * @param ccList
	 * @param userName
	 * @param startTime
	 * @param otherEndDate
	 * @return
	 */
	public List<CwTimeLong> getChapterTimeLong(List<Courseware> ccList,
			String userName, Date startTime, Date otherEndDate) {
		if (ccList != null && ccList.size() > 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(Contants.DATA_KEY, Contants.COURSE_WARE);
			map.put("ccList", ccList);
			map.put("userName", userName);
			map.put("startTime", startTime);
			map.put("endTime", otherEndDate);
			return this.baseDao.findList(map, "getChapterTimeLong");
		} else {
			return null;
		}
	}

	/**
	 * 获取学员每天的看课时长
	 * 
	 * @param ccList
	 * @param userName
	 * @param startTime
	 * @param otherEndDate
	 * @return
	 */
	public List<CwTimeLong> getUserDayTimeLong(List<Courseware> ccList,
			String userName, Date startTime, Date otherEndDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Contants.DATA_KEY, Contants.COURSE_WARE);
		map.put("ccList", ccList);
		map.put("userName", userName);
		map.put("startTime", startTime);
		map.put("endTime", otherEndDate);
		return this.baseDao.findList(map, "getUserDayTimeLong");
	}

	@Override
	public LazyDataModel<CwTimeLong> findPage(Map<String, Object> map) {
		map.put(Contants.DATA_KEY, Contants.COURSE_WARE);
		return super.findPage(map);
	}

}