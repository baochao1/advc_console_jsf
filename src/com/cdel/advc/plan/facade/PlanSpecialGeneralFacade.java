package com.cdel.advc.plan.facade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdel.advc.plan.domain.PlanSpecialGeneral;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.CheckUtil;
import com.cdel.util.DateUtil;

@SuppressWarnings("serial")
@Service
public class PlanSpecialGeneralFacade extends
		BaseFacadeImpl<PlanSpecialGeneral, Integer> implements Serializable {

	@Autowired
	private StudyPlanFacade studyPlanFacade;

	public boolean checkSpecialGeneral(Short flag, Date start, Date end,
			Short specialHours) {
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
	public String addList(PlanSpecialGeneral sg, Date start, Date end)
			throws Exception {
		List<PlanSpecialGeneral> list = new ArrayList<PlanSpecialGeneral>();
		String str = "";
		Integer planID = sg.getPlanID();
		while (start.compareTo(end) <= 0) {
			PlanSpecialGeneral specialGeneral = new PlanSpecialGeneral();
			specialGeneral.setPlanID(sg.getPlanID());
			specialGeneral.setSpecialDate(start);
			PlanSpecialGeneral ps = getSpecialGeneralByPlan(specialGeneral);
			if (ps != null) {
				str += DateUtil.getNowDateString(start, "yyyy-MM-dd") + ",";
			} else {
				specialGeneral.setSpecialHours(sg.getSpecialHours());
				specialGeneral.setCreator(sg.getCreator());
				specialGeneral.setCreateTime(sg.getCreateTime());
				specialGeneral.setLogDesc(sg.getLogDesc());
				specialGeneral.setStatus((short) 1);
				list.add(specialGeneral);
			}
			start = DateUtil.getNextDay(start);
		}
		if (list.size() > 0) {
			insertAll(planID, list);
		}
		return str;
	}

	/**
	 * 根据planID信息获取SpecialGeneral
	 * 
	 * @param sg
	 * @return
	 */
	public PlanSpecialGeneral getSpecialGeneralByPlan(PlanSpecialGeneral sg) {
		return baseDao.getByEntity(sg, "getSpecialGeneralByPlan");
	}

	/**
	 * 批量插入
	 * 
	 * @param list
	 */
	public void insertAll(Integer planID, List<PlanSpecialGeneral> list)
			throws Exception {
		baseDao.insert(list, "insertPlanSpecialGeneralList");
		studyPlanFacade.updatePlanStatus(planID, (short) 6);
	}

	public void delete(Integer planID, Integer specialID) throws Exception {
		super.delete(specialID);
		studyPlanFacade.updatePlanStatus(planID, (short) 6);
	}

	@Override
	public void update(PlanSpecialGeneral sg) {
		super.update(sg);
		try {
			studyPlanFacade.updatePlanStatus(sg.getPlanID(), (short) 6);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
