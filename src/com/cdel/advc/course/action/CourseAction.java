package com.cdel.advc.course.action;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UISelectOne;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.course.domain.Course;
import com.cdel.advc.course.facade.CourseFacade;
import com.cdel.qz.course.domain.QzCourse;
import com.cdel.qz.course.facade.QzCourseFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.ExceptionUtil;

/**
 * 
 * 课程Bean
 * 
 * @author Haiying Du
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CourseAction extends BaseAction<Course> implements Serializable {
	@ManagedProperty(value = "#{courseFacade}")
	private CourseFacade courseFacade;
	@ManagedProperty(value = "#{qzCourseFacade}")
	private QzCourseFacade qzCourseFacade;

	private LazyDataModel<Course> coursePage;
	private Course searchCourse = new Course();// 搜索的课程
	private Integer siteID;// 网站ID
	private List<QzCourse> qzCourseList;// 题库对内课程list
	private byte flag = -1;// 添加/编辑状态,0添加,1编辑
	private Course updateCourse;// 添加/修改的课程
	private String url = "";// 要加载的小页面

	@PostConstruct
	public void initCourseAction() {
		url = "../grant/null.xhtml";
		siteID = this.getCurrentSiteID();
		this.coursePage = this.courseFacade.findPage(searchCourse);
		super.heighti2 = super.calHeight(11.5f / 20);
	}

	/**
	 * 查询
	 */
	public void search() {
		pageTable.setFirst(0);
		search4U();
	}

	/**
	 * 查询
	 */
	public void search4U() {
		this.coursePage = this.courseFacade.findPage(searchCourse);
		this.updateComponent("searchForm:courseList");
	}

	/**
	 * 根据修改辅导，取题库的对内课程
	 * 
	 * @param e
	 */
	public void changeMajorID(AjaxBehaviorEvent e) {
		Integer majorID = (Integer) ((UISelectOne) e.getSource()).getValue();
		qzCourseList = qzCourseFacade.findList(majorID);
	}

	/**
	 * 添加课程
	 */
	public void add() {
		url = "load/courseUpdate.xhtml";
		updateCourse = new Course();
		flag = 0;
	}

	/**
	 * 添加/修改课程
	 */
	public void updateSubmit() {
		byte submitSuccess = 0;
		if (courseFacade.checkCourse(flag, updateCourse, siteID)) {
			try {
				if (flag == 0) {
					updateCourse.setStatus(Short.valueOf("1"));
					updateCourse.setPreviewCourse(0);
					updateCourse.setCreator(this.getCurrentUserID());
					updateCourse.setCreateTime(new Date());
					courseFacade.add(updateCourse);
					search();
				} else {
					courseFacade.update(updateCourse);
					search4U();
				}
				submitSuccess = 1;
				updateCourse = null;
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("updateCourse=" + updateCourse);
				logger.error(ExceptionUtil.getEMsg(e));
				submitSuccess = -1;
			}
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 编辑
	 */
	public void update(Integer courseID) {
		url = "load/courseUpdate.xhtml";
		flag = 1;
		updateCourse = courseFacade.getCourseByCourseID(courseID);
		qzCourseList = qzCourseFacade.findList(updateCourse.getMajorID());
	}

	public void setCourseFacade(CourseFacade courseFacade) {
		this.courseFacade = courseFacade;
	}

	public LazyDataModel<Course> getCoursePage() {
		return coursePage;
	}

	public Course getSearchCourse() {
		return searchCourse;
	}

	public void setSearchCourse(Course searchCourse) {
		this.searchCourse = searchCourse;
	}

	public void setQzCourseFacade(QzCourseFacade qzCourseFacade) {
		this.qzCourseFacade = qzCourseFacade;
	}

	public List<QzCourse> getQzCourseList() {
		return qzCourseList;
	}

	public Course getUpdateCourse() {
		return updateCourse;
	}

	public void setUpdateCourse(Course updateCourse) {
		this.updateCourse = updateCourse;
	}

	public byte getFlag() {
		return flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSiteID() {
		return siteID;
	}

}