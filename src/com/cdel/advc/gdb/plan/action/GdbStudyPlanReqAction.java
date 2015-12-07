package com.cdel.advc.gdb.plan.action;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.gdb.plan.domain.GdbStudyPlan;
import com.cdel.advc.gdb.plan.facade.GdbStudyPlanFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;
import com.cdel.util.DateUtil;

@SuppressWarnings("serial")
@ManagedBean
public class GdbStudyPlanReqAction extends BaseAction<GdbStudyPlan> implements
		Serializable {

	@ManagedProperty("#{gdbStudyPlanFacade}")
	private GdbStudyPlanFacade gdbStudyPlanFacade;

	private GdbStudyPlan uGdbStudyPlan = new GdbStudyPlan();
	private byte submitSuccess = 0;// 修改是否成功

	/**
	 * 更新状态
	 * 
	 * @throws Exception
	 */
	public void changeStatus(Integer studyPlanID, Short status)
			throws Exception {
		GdbStudyPlan gs = new GdbStudyPlan();
		gs.setStudyPlanID(studyPlanID);
		if (status == 1) {
			gs.setStatus((short) 0);
		} else {
			gs.setStatus((short) 1);
		}
		try {
			this.gdbStudyPlanFacade.update(gs);
			GdbStudyPlanAction ga = (GdbStudyPlanAction) this
					.getViewAction("gdbStudyPlanAction");
			ga.search4U();
			this.addMessage("info", SUCESSINFO);
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
		}
	}

	/**
	 * 打开修改页面
	 * 
	 * @param studyPlanID
	 */
	public void update(Integer studyPlanID) {
		uGdbStudyPlan = gdbStudyPlanFacade.findByID(studyPlanID);
	}

	/**
	 * 打开添加页面
	 * 
	 * @param studyPlanID
	 */
	public void add() {
		uGdbStudyPlan.setStatus((short) 1);
	}

	/**
	 * 提交修改
	 */
	public void updateSubmit(byte flag) {
		try {
			if (gdbStudyPlanFacade.check(flag, uGdbStudyPlan)) {
				GdbStudyPlanAction ga = (GdbStudyPlanAction) this
						.getViewAction("gdbStudyPlanAction");
				if (flag == 0) {
					uGdbStudyPlan.setUserID(ga.getUserID());
					uGdbStudyPlan.setEndDate(DateUtil.getAfterDate(
							uGdbStudyPlan.getStartDate(), 7));
					uGdbStudyPlan.setCreateTime(new Date());
					uGdbStudyPlan.setCreateUserId(this.getCurrentUserID());
					gdbStudyPlanFacade.add(uGdbStudyPlan);
					ga.search();
				} else {
					uGdbStudyPlan.setUpdateTime(new Date());
					gdbStudyPlanFacade.update(uGdbStudyPlan);
					ga.search4U();
				}
				submitSuccess = 1;
			}
		} catch (Exception e) {
			submitSuccess = -1;
			e.printStackTrace();
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public Map<Integer, String> getWeekNum() {
		Map<Integer, String> map = new LinkedHashMap<Integer, String>();
		for (int i = 1; i <= 52; i++) {
			map.put(i, "第" + i + "周");
		}
		return map;
	}

	public Map<Short, String> getStatus() {
		return Contants.status;
	}

	public void setGdbStudyPlanFacade(GdbStudyPlanFacade gdbStudyPlanFacade) {
		this.gdbStudyPlanFacade = gdbStudyPlanFacade;
	}

	public GdbStudyPlan getuGdbStudyPlan() {
		return uGdbStudyPlan;
	}

	public void setuGdbStudyPlan(GdbStudyPlan uGdbStudyPlan) {
		this.uGdbStudyPlan = uGdbStudyPlan;
	}

}
