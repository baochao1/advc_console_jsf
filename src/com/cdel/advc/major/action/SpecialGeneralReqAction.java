package com.cdel.advc.major.action;

import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.primefaces.event.CellEditEvent;
import com.cdel.advc.major.domain.Major;
import com.cdel.advc.major.domain.SpecialGeneral;
import com.cdel.advc.major.facade.SpecialGeneralFacade;
import com.cdel.util.BaseAction;

/**
 * 特殊时间设置action
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
public class SpecialGeneralReqAction extends BaseAction<Major> implements
		Serializable {
	@ManagedProperty(value = "#{specialGeneralFacade}")
	private SpecialGeneralFacade specialGeneralFacade;

	private Date start;
	private Date end;
	private Integer specialMin;
	private Integer specialGeneralID;
	private byte submitSuccess = 0;

	/**
	 * 添加
	 */
	public void addSubmit(Integer majorID) {
		String str = "";
		if (specialGeneralFacade.checkSpecialGeneral(new Short("0"), start,
				end, specialMin)) {
			try {
				SpecialGeneral sg = new SpecialGeneral();
				sg.setMajorID(majorID);
				sg.setLongTime(specialMin * 60);
				sg.setCreateTime(new Date());
				sg.setCreator(this.getCurrentUserID());
				str = specialGeneralFacade.addList(sg, start, end);
				SpecialGeneralAction specialGeneralAction = (SpecialGeneralAction) this
						.getViewAction("specialGeneralAction");
				specialGeneralAction.search();
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
	public void delete(Integer specialGeneralID) {
		try {
			specialGeneralFacade.delete(specialGeneralID);
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
		SpecialGeneral sg = (SpecialGeneral) this.getEditRow(event);
		Integer longTimeMin = sg.getLongTimeMin();
		if (specialGeneralFacade.checkSpecialGeneral(new Short("1"), null,
				null, longTimeMin)) {
			try {
				sg.setLongTime(longTimeMin * 60);
				specialGeneralFacade.update(sg);
				this.addInfoMessage("info", SUCESSINFO);
			} catch (Exception e) {
				e.printStackTrace();
				this.addErrorMessage("info", FAILINFO);
			}
		}
	}

	public void setSpecialGeneralFacade(
			SpecialGeneralFacade specialGeneralFacade) {
		this.specialGeneralFacade = specialGeneralFacade;
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

	public Integer getSpecialMin() {
		return specialMin;
	}

	public void setSpecialMin(Integer specialMin) {
		this.specialMin = specialMin;
	}

	public Integer getSpecialGeneralID() {
		return specialGeneralID;
	}

	public void setSpecialGeneralID(Integer specialGeneralID) {
		this.specialGeneralID = specialGeneralID;
	}

}
