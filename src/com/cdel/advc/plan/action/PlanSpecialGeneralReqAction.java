package com.cdel.advc.plan.action;

import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.primefaces.event.CellEditEvent;
import com.cdel.advc.plan.domain.PlanChapter;
import com.cdel.advc.plan.domain.PlanSpecialGeneral;
import com.cdel.advc.plan.facade.PlanSpecialGeneralFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.DateUtil;

/**
 * 特殊时间设置action
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
public class PlanSpecialGeneralReqAction extends BaseAction<PlanChapter>
		implements Serializable {
	@ManagedProperty(value = "#{planSpecialGeneralFacade}")
	private PlanSpecialGeneralFacade planSpecialGeneralFacade;

	private Date start;
	private Date end;
	private Short specialMin;
	private Integer specialGeneralID;
	private byte submitSuccess = 0;

	/**
	 * 添加
	 */
	public void addSubmit(Integer planID) {
		String str = "";
		if (planSpecialGeneralFacade.checkSpecialGeneral(new Short("0"), start,
				end, specialMin)) {
			try {
				PlanSpecialGeneral sg = new PlanSpecialGeneral();
				sg.setPlanID(planID);
				sg.setSpecialHours((short) (specialMin * 60));
				sg.setCreateTime(new Date());
				sg.setCreator(this.getCurrentUserID());
				String logDesc = this.getCurrentRealName() + "于"
						+ (DateUtil.getNowToString("yyyy-MM-dd HH:mm:ss"))
						+ "添加";
				sg.setLogDesc(logDesc);
				str = planSpecialGeneralFacade.addList(sg, start, end);
				PlanSpecialGeneralAction sa = (PlanSpecialGeneralAction) this
						.getViewAction("planSpecialGeneralAction");
				sa.search();
				submitSuccess = 1;
			} catch (Exception e) {
				e.printStackTrace();
				submitSuccess = -1;
			}
		}
		this.addCallbackParam("result", submitSuccess);
		this.addCallbackParam("str", str);
	}

	/**
	 * 删除
	 * 
	 * @param specialGeneralID
	 */
	public void delete(Integer planID, Integer specialID) {
		try {
			planSpecialGeneralFacade.delete(planID, specialID);
			submitSuccess = 1;
		} catch (Exception e) {
			e.printStackTrace();
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 提交修改
	 */
	public void updateSubmit(CellEditEvent event) {
		PlanSpecialGeneral sg = (PlanSpecialGeneral) this.getEditRow(event);
		sg.setSpecialMin();
		if (planSpecialGeneralFacade.checkSpecialGeneral(new Short("1"), null,
				null, sg.getSpecialHours())) {
			try {
				String logDesc = sg.getLogDesc() + "<br/>"
						+ this.getCurrentRealName() + "于"
						+ (DateUtil.getNowToString("yyyy-MM-dd HH:mm:ss"))
						+ "修改";
				sg.setLogDesc(logDesc);
				planSpecialGeneralFacade.update(sg);
				this.addInfoMessage("info", SUCESSINFO);
			} catch (Exception e) {
				e.printStackTrace();
				this.addErrorMessage("info", FAILINFO);
			}
		}
	}

	public void setPlanSpecialGeneralFacade(
			PlanSpecialGeneralFacade planSpecialGeneralFacade) {
		this.planSpecialGeneralFacade = planSpecialGeneralFacade;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Short getSpecialMin() {
		return specialMin;
	}

	public void setSpecialMin(Short specialMin) {
		this.specialMin = specialMin;
	}

	public Integer getSpecialGeneralID() {
		return specialGeneralID;
	}

	public void setSpecialGeneralID(Integer specialGeneralID) {
		this.specialGeneralID = specialGeneralID;
	}

}
