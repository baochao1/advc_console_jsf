package com.cdel.qz.pointList.action;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.cdel.qz.pointList.domain.PointList;
import com.cdel.qz.pointList.facade.PointListFacade;

/**
 * 
 * <p>
 * 知识点策略action
 * </p>
 * 
 * @author 张苏磊
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PointListOther implements Serializable {

	@ManagedProperty("#{pointListFacade}")
	private PointListFacade pointListFacade;

	private List<PointList> pointListList;

	public void selectPointListList(Integer courseID) {
		this.pointListList = pointListFacade.findList(courseID);
	}

	public void setPointListFacade(PointListFacade pointListFacade) {
		this.pointListFacade = pointListFacade;
	}

	public List<PointList> getPointListList() {
		return pointListList;
	}

}
