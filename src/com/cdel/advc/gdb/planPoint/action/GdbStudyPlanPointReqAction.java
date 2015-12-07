package com.cdel.advc.gdb.planPoint.action;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.gdb.planPoint.domain.GdbStudyPlanPoint;
import com.cdel.advc.gdb.planPoint.facade.GdbStudyPlanPointFacade;
import com.cdel.qz.point.domain.Point;
import com.cdel.util.BaseAction;

@SuppressWarnings("serial")
@ManagedBean
public class GdbStudyPlanPointReqAction extends BaseAction<GdbStudyPlanPoint>
		implements Serializable {
	@ManagedProperty("#{gdbStudyPlanPointFacade}")
	private GdbStudyPlanPointFacade gdbStudyPlanPointFacade;

	private List<Point> selPointList;
	private byte submitSuccess = 0;// 添加是否成功

	/**
	 * 删除知识点
	 * 
	 * @param id
	 */
	public void delete(Integer id) {
		try {
			gdbStudyPlanPointFacade.delete(id);
			this.addInfoMessage("info", "删除成功！");
			GdbStudyPlanPointAction ga = (GdbStudyPlanPointAction) this
					.getViewAction("gdbStudyPlanPointAction");
			ga.search();
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", "删除失败！");
		}
	}

	/**
	 * 高端班关联知识点
	 * 
	 * @param courseID
	 * @param userID
	 * @param studyPlanID
	 * @param siteCourseID
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addPoint(Integer courseID, Integer userID, Integer studyPlanID,
			Integer siteCourseID) {
		if (selPointList == null || selPointList.size() == 0) {
			this.addInfoMessage("info", "请选择要关联的知识点！");
			return;
		}
		Map map = new HashMap();
		map.put("courseID", courseID);
		map.put("userID", userID);
		map.put("studyPlanID", studyPlanID);
		map.put("siteCourseID", siteCourseID);
		map.put("createUserID", this.getCurrentUserID());
		map.put("createTime", new Date());
		map.put("pointList", selPointList);
		try {
			gdbStudyPlanPointFacade.addMap(map);
			GdbStudyPlanPointAction ga = (GdbStudyPlanPointAction) this
					.getViewAction("gdbStudyPlanPointAction");
			ga.search();
			submitSuccess = 1;
			this.addInfoMessage("info", "添加成功！");
		} catch (Exception e) {
			submitSuccess = -1;
			e.printStackTrace();
			this.addErrorMessage("info", "添加失败！");
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public void setGdbStudyPlanPointFacade(
			GdbStudyPlanPointFacade gdbStudyPlanPointFacade) {
		this.gdbStudyPlanPointFacade = gdbStudyPlanPointFacade;
	}

	public List<Point> getSelPointList() {
		return selPointList;
	}

	public void setSelPointList(List<Point> selPointList) {
		this.selPointList = selPointList;
	}

}
