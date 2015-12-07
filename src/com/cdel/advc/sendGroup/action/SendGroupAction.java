package com.cdel.advc.sendGroup.action;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UISelectOne;
import javax.faces.event.AjaxBehaviorEvent;
import com.cdel.advc.course.domain.Course;
import com.cdel.advc.course.facade.CourseFacade;
import com.cdel.advc.sendGroup.domain.SendGroup;
import com.cdel.util.BaseAction;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class SendGroupAction extends BaseAction<SendGroup> implements
		Serializable {

	@ManagedProperty(value = "#{courseFacade}")
	private CourseFacade courseFacade;

	private List<Course> courseList;// 班级List
	private SendGroup searchSendGroup = new SendGroup();

	private Integer siteID;// 网站ID

	@PostConstruct
	public void initTechnologyMsgAction() {
		siteID = this.getCurrentSiteID();
	}

	/**
	 * 根据辅导的修改取班级信息
	 * 
	 * @param e
	 */
	public void changeMajorID(AjaxBehaviorEvent e) {
		Integer majorID = (Integer) ((UISelectOne) e.getSource()).getValue();
		courseList = courseFacade.findList(majorID);
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public Integer getSiteID() {
		return siteID;
	}

	public void setSiteID(Integer siteID) {
		this.siteID = siteID;
	}

	public void setCourseFacade(CourseFacade courseFacade) {
		this.courseFacade = courseFacade;
	}

	public SendGroup getSearchSendGroup() {
		return searchSendGroup;
	}

	public void setSearchSendGroup(SendGroup searchSendGroup) {
		this.searchSendGroup = searchSendGroup;
	}

}
