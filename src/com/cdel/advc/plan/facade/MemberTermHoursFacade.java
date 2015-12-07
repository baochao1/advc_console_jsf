/*
 * @Title: MemberTermHoursFacade.java
 * @Package com.cdel.advc.plan.facade
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-8-5 上午9:34:57
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-8-5                          
 */
package com.cdel.advc.plan.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdel.advc.plan.domain.MemberTermHours;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.CheckUtil;

/**
 * <p>
 * 学员学习时间设置 facade层
 * </p>
 * 
 * @author Du Haiying Create at:2013-8-5 上午9:34:57
 */
@SuppressWarnings("serial")
@Service
public class MemberTermHoursFacade extends
		BaseFacadeImpl<MemberTermHours, Integer> {

	@Autowired
	private StudyPlanFacade studyPlanFacade;

	@Autowired
	private PlanLogFacade planLogFacade;

	public boolean checkHour(MemberTermHours hours) {
		if (!checkTime(hours.getSundayHour()))
			return false;
		if (!checkTime(hours.getMondayHour()))
			return false;
		if (!checkTime(hours.getTuesdayHour()))
			return false;
		if (!checkTime(hours.getWednesdayHour()))
			return false;
		if (!checkTime(hours.getThursdayHour()))
			return false;
		if (!checkTime(hours.getFridayHour()))
			return false;
		if (!checkTime(hours.getSaturdayHour()))
			return false;
		if (!checkTime2(hours.getMaxStudyTimeHour()))
			return false;
		return true;
	}

	public boolean checkTime(Double input) {
		if (input == null || input < 0 || input >= 24
				|| !CheckUtil.checkFloat2(input.toString(), 2, 1)) {
			return this.addWarnMessage("info", "建议时间只能是0-24之间的整数或带有1位小数！");
		}
		return true;
	}

	public boolean checkTime2(Double input) {
		if (input == null || input <= 0 || input >= 24
				|| !CheckUtil.checkFloat(input.toString(), 5, 2)) {
			return this.addWarnMessage("info", "最高学习时间只能是0-24之间的数字！");
		}
		return true;
	}

	/**
	 * 查找时间设置
	 * 
	 * @param userID
	 *            学员ID
	 * @param termID
	 *            考期ID
	 * @param mthType
	 *            时间设置类型
	 * @return
	 */
	public MemberTermHours getMemberTermHours(Integer userID, Integer termID,
			Short mthType) {

		if (userID == null || termID == null || mthType == null) {

			throw new IllegalArgumentException("非法参数！");
		}

		MemberTermHours temp = new MemberTermHours();
		temp.setUserID(userID);
		temp.setTermID(termID);
		temp.setMthType(mthType);

		return this.baseDao.getByEntity(temp, "getMemberTermHours");
	}

	public void add(MemberTermHours hours, Integer planID, Integer creator,
			String creatorName) throws Exception {
		super.add(hours);
		if (planID != null) {
			studyPlanFacade.updatePlanStatus(planID, (short) 7);
			planLogFacade.addLog(planID, creator, creatorName, "修改学习时长/要求");
		}
	}

	public void update(MemberTermHours hours, Integer planID, Integer creator,
			String creatorName) throws Exception {
		super.update(hours);
		if (planID != null) {
			studyPlanFacade.updatePlanStatus(planID, (short) 7);
			planLogFacade.addLog(planID, creator, creatorName, "修改学习时长/要求");
		}
	}

}