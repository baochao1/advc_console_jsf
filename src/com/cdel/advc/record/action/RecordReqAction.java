package com.cdel.advc.record.action;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.primefaces.model.LazyDataModel;

import com.cdel.advc.record.facade.RecordFacade;
import com.cdel.qz.score.center.domain.CenterScore;
import com.cdel.qz.score.point.domain.PointScore;
import com.cdel.qz.score.point.facade.PointScoreFacade;
import com.cdel.qz.score.selfhelp.domain.SelfHelp;
import com.cdel.qz.score.selfhelp.facade.SelfHelpFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * <p>
 * 学习记录
 * </p>
 * 
 * @author 张苏磊
 */
@SuppressWarnings("serial")
@ManagedBean
public class RecordReqAction extends BaseAction<BaseDomain> implements
		Serializable {

	@ManagedProperty("#{pointScoreFacade}")
	private PointScoreFacade pointScoreFacade;
	@ManagedProperty("#{recordFacade}")
	private RecordFacade recordFacade;
	
	@ManagedProperty("#{selfHelpFacade}")
	private SelfHelpFacade selfHelpFacade;

	private Date startDate;
	private Date endDate;
	private List<PointScore> pointScoreList;// 知识点记录
	private List<PointScore> pointStatList;// 知识点统计
	private List<CenterScore> centerScoreList;// 中心记录
	
	/** DataTable组件分页模型 */
	private LazyDataModel<SelfHelp> page;
 
	
	private Map<Short, String> typeMap = new HashMap<Short, String>() {
		{
			put((short) 1, "[知识点测试记录]");
			put((short) 2, "[试卷做题记录]");
			put((short) 3, "[详细听课记录]");
			put((short) 4, "[自助练习]");
		}
	};
	private String qzurl = Contants.qzConsolePointPaper;
	private String qzurl2 = Contants.qzConsolePaperScore;

	public void selectChange(Integer selectCourseID, Integer selectType) {
		RecordAction ra = (RecordAction) this.getViewAction("recordAction");
		ra.setSelectCourseID(selectCourseID);
		ra.setSelectType(selectType);
		selectChange();
	}

	/**
	 * 修改课程/类型
	 */
	public void selectChange() {
		RecordAction ra = (RecordAction) this.getViewAction("recordAction");
		Integer selectCourseID = ra.getSelectCourseID();
		Integer selectType = ra.getSelectType();
		recordFacade.checkMap(ra.getCwIDsMap(), ra.getSiteCourseMap(),
				selectCourseID);
		Map<Integer, List<Integer>> siteCourseMap = ra.getSiteCourseMap();
		if (siteCourseMap != null && siteCourseMap.size() > 0
				&& selectCourseID != null) {
			this.updateComponent("searchForm");
			if (selectType == 1) {
				pointScoreList = recordFacade.showList(ra.getUserID(),
						siteCourseMap.get(selectCourseID), startDate, endDate);
				this.updateComponent("searchForm:pointScoreList");
			}
			if (selectType == 2) {
				centerScoreList = recordFacade.showList(ra.getUserID(),
						siteCourseMap.get(selectCourseID));
				this.updateComponent("searchForm:centerScoreList");
			}
			if (selectType == 3) {
				Map<Integer, List<String>> cwIDsMap = ra.getCwIDsMap();
				ra.setTimePage(recordFacade.showList(ra.getUserName(),
						cwIDsMap.get(selectCourseID), startDate, endDate));
				this.updateComponent("searchForm:timeList");
			}
			
			if (selectType == 4) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("creator", ra.getUserID());
				map.put("siteCourseIDList", siteCourseMap.get(selectCourseID));
				page = selfHelpFacade.findPage(map);
				this.updateComponent("searchForm:selfHelpPaperList");
			}
		} else {
			this.addWarnMessage("info", "对不起，没有读取到课程数据！");
		}
	}

	/**
	 * 知识点统计
	 */
	public void statPoint() {
		RecordAction ra = (RecordAction) this.getViewAction("recordAction");
		Map<Integer, List<Integer>> siteCourseMap = ra.getSiteCourseMap();
		Integer selectCourseID = ra.getSelectCourseID();
		if (siteCourseMap != null && siteCourseMap.size() > 0
				&& selectCourseID != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userID", ra.getUserID());
			map.put("siteCourseIDList", siteCourseMap.get(selectCourseID));
			pointStatList = pointScoreFacade.getRptPointStatList(map);
		}
	}

	/**
	 * 详细听课记录翻页
	 */
	public void searchTime() {
		RecordAction ra = (RecordAction) this.getViewAction("recordAction");
		ra.getPageTable().setFirst(0);
		selectChange();
	}

	public Map<Short, String> getTypeMap() {
		return typeMap;
	}

	public void setPointScoreFacade(PointScoreFacade pointScoreFacade) {
		this.pointScoreFacade = pointScoreFacade;
	}

	public List<PointScore> getPointScoreList() {
		return pointScoreList;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getQzurl() {
		return qzurl;
	}

	public List<PointScore> getPointStatList() {
		return pointStatList;
	}

	public void setRecordFacade(RecordFacade recordFacade) {
		this.recordFacade = recordFacade;
	}

	public List<CenterScore> getCenterScoreList() {
		return centerScoreList;
	}

	public String getQzurl2() {
		return qzurl2;
	}

	public LazyDataModel<SelfHelp> getPage() {
		return page;
	}

	public void setPage(LazyDataModel<SelfHelp> page) {
		this.page = page;
	}

	public void setSelfHelpFacade(SelfHelpFacade selfHelpFacade) {
		this.selfHelpFacade = selfHelpFacade;
	}

	public SelfHelpFacade getSelfHelpFacade() {
		return selfHelpFacade;
	}
	
}