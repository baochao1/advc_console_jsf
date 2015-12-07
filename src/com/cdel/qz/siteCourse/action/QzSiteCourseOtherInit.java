package com.cdel.qz.siteCourse.action;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.cdel.qz.siteCourse.domain.QzSiteCourse;
import com.cdel.qz.siteCourse.facade.QzSiteCourseFacade;
import com.cdel.util.Contants;

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
public class QzSiteCourseOtherInit implements Serializable {
	@ManagedProperty(value = "#{qzSiteCourseFacade}")
	private QzSiteCourseFacade qzSiteCourseFacade;

	private List<QzSiteCourse> siteCourseList;

	@PostConstruct
	public void initSiteCourseList() {
		this.siteCourseList = qzSiteCourseFacade
				.getSiteCourseList(Contants.gdbServerType);
	}

	public List<QzSiteCourse> getSiteCourseList() {
		return siteCourseList;
	}

	public void setQzSiteCourseFacade(QzSiteCourseFacade qzSiteCourseFacade) {
		this.qzSiteCourseFacade = qzSiteCourseFacade;
	}

}