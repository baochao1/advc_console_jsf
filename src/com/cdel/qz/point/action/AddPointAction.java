package com.cdel.qz.point.action;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.qz.point.domain.Point;
import com.cdel.qz.point.facade.PointFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class AddPointAction extends BaseAction<Point> implements Serializable {
	@ManagedProperty("#{pointFacade}")
	private PointFacade pointFacade;

	/** DataTable组件分页模型 */
	private LazyDataModel<Point> page;
	private Point filterPoint = new Point();
	private Integer userID;
	private Integer studyPlanID;
	private Integer siteCourseID;
	private Integer courseID;
	private String qzConsoleUrl = Contants.qzConsoleOpenPaper;

	/**
	 * 打开高端班关联知识点页面
	 * 
	 * @param courseID
	 * @param userID
	 * @param studyPlanID
	 */
	public void add(Integer courseID, Integer userID, Integer studyPlanID,
			Integer siteCourseID) {
		this.userID = userID;
		this.courseID = courseID;
		this.studyPlanID = studyPlanID;
		this.siteCourseID = siteCourseID;
		filterPoint.setCourseID(courseID);
		filterPoint.setUserID(userID);
		filterPoint.setStudyPlanID(studyPlanID);
		super.heighti2 = super.calHeight(10f / 20);
		page = pointFacade.findPageAdd(filterPoint);
	}

	public void search() {
		pageTable.setFirst(0);
		search4U();
	}

	public void search4U() {
		page = pointFacade.findPageAdd(filterPoint);
		this.updateComponent("addForm:addPointTable");
	}

	public LazyDataModel<Point> getPage() {
		return page;
	}

	public void setPage(LazyDataModel<Point> page) {
		this.page = page;
	}

	public Point getFilterPoint() {
		return filterPoint;
	}

	public void setFilterPoint(Point filterPoint) {
		this.filterPoint = filterPoint;
	}

	public void setPointFacade(PointFacade pointFacade) {
		this.pointFacade = pointFacade;
	}

	public String getQzConsoleUrl() {
		return qzConsoleUrl;
	}

	public Integer getUserID() {
		return userID;
	}

	public Integer getStudyPlanID() {
		return studyPlanID;
	}

	public Integer getSiteCourseID() {
		return siteCourseID;
	}

	public Integer getCourseID() {
		return courseID;
	}

}
