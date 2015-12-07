package com.cdel.qz.siteCourse.action;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.cdel.qz.siteCourse.domain.QzSiteCourse;
import com.cdel.qz.siteCourse.facade.QzSiteCourseFacade;
import com.cdel.util.BaseAction;

/**
 * 
 * 题库对外课程
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class QzSiteCourseOther extends BaseAction<QzSiteCourse> implements
		Serializable {
	@ManagedProperty(value = "#{qzSiteCourseFacade}")
	private QzSiteCourseFacade qzSiteCourseFacade;

	private List<QzSiteCourse> siteCourseList;

	/**
	 * 根据siteCourseID取对外课
	 * 
	 * @param s
	 */
	public void setQzSiteCourse() {
		String siteCourseIDs = this.getRequestParameterByMap("siteCourseIDs");
		siteCourseList = qzSiteCourseFacade.getSiteCourseByscids(siteCourseIDs);
	}

	public List<QzSiteCourse> getSiteCourseList() {
		return siteCourseList;
	}

	public void setQzSiteCourseFacade(QzSiteCourseFacade qzSiteCourseFacade) {
		this.qzSiteCourseFacade = qzSiteCourseFacade;
	}

}