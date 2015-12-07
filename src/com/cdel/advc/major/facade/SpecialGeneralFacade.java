package com.cdel.advc.major.facade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import com.cdel.advc.major.domain.SpecialGeneral;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.CheckUtil;
import com.cdel.util.DateUtil;

@SuppressWarnings("serial")
@Service
public class SpecialGeneralFacade extends
		BaseFacadeImpl<SpecialGeneral, Integer> implements Serializable {
	public boolean checkSpecialGeneral(Short flag, Date start, Date end,
			Integer specialHours) {
		if (flag == 0) {
			if (start == null || end == null || specialHours == null) {
				return addWarnMessage("开始时间，结束时间和学习时间都不能为空！");
			}
			if (start.compareTo(end) > 0) {
				return addWarnMessage("开始时间不能大于结束时间！");
			}
		} else {
			if (specialHours == null) {
				return addWarnMessage("学习时间都不能为空！");
			}
		}
		if (!CheckUtil.checkInt(specialHours.toString(), 3)) {
			return addWarnMessage("学习时间必须为数字,且长度不能超过3个字符！");
		}
		return true;
	}

	/**
	 * 根据起始和结束日期批量插入数据
	 * 
	 * @param sg
	 * @param start
	 * @param end
	 */
	public String addList(SpecialGeneral sg, Date start, Date end) {
		List<SpecialGeneral> list = new ArrayList<SpecialGeneral>();
		String str = "";
		while (start.compareTo(end) <= 0) {
			SpecialGeneral specialGeneral = new SpecialGeneral();
			specialGeneral.setMajorID(sg.getMajorID());
			specialGeneral.setGeneralDay(start);
			SpecialGeneral ps = getSpecialGeneralByMajor(specialGeneral);
			if (ps != null) {
				str += DateUtil.getNowDateString(start, "yyyy-MM-dd") + ",";
			} else {
				specialGeneral.setLongTime(sg.getLongTime());
				specialGeneral.setCreator(sg.getCreator());
				specialGeneral.setCreateTime(sg.getCreateTime());
				list.add(specialGeneral);
			}
			start = DateUtil.getNextDay(start);
		}
		if (list.size() > 0) {
			insertAll(list);
		}
		return str;
	}

	/**
	 * 根据辅导等信息获取SpecialGeneral
	 * 
	 * @param sg
	 * @return
	 */
	public SpecialGeneral getSpecialGeneralByMajor(SpecialGeneral sg) {
		return baseDao.getByEntity(sg, "getSpecialGeneralByMajor");
	}

	/**
	 * 批量插入
	 * 
	 * @param list
	 */
	public void insertAll(List<SpecialGeneral> list) {
		baseDao.insert(list, "insertSpecialGeneralList");
	}

}
