package com.cdel.advc.course.action;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang.StringUtils;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.course.domain.Course;
import com.cdel.advc.course.domain.TempChapter;
import com.cdel.advc.course.facade.CourseFacade;
import com.cdel.advc.course.facade.TempChapterFacade;
import com.cdel.qz.chapter.domain.QzChapter;
import com.cdel.qz.chapter.facade.QzChapterFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.ExceptionUtil;
import com.cdel.util.StringUtil;

/**
 * 
 * 学习计划项Bean
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class TempChapterAction extends BaseAction<Course> implements
		Serializable {
	@ManagedProperty(value = "#{tempChapterFacade}")
	private TempChapterFacade tempChapterFacade;
	@ManagedProperty(value = "#{qzChapterFacade}")
	private QzChapterFacade qzChapterFacade;
	@ManagedProperty(value = "#{courseFacade}")
	private CourseFacade courseFacade;

	private LazyDataModel<TempChapter> tempChapterPage;
	private TempChapter searchTempChapter = new TempChapter();// 搜索的课程
	private TempChapter updateTempChapter;// 添加/修改的计划项
	private String url = "";// 要加载的小页面
	private byte flag = -1;// 添加/编辑状态,0添加,1编辑
	private Integer courseID;// 课程ID
	private Integer majorID;
	private String courseName;// 课程名称
	private List<Course> courseList;// 可以复制计划项的课程
	private String[] qzChapterCheck;// 已经选中的题库章节ID
	private List<QzChapter> qzChapterList;// 题库章节

	@PostConstruct
	public void initCourseAction() {
		url = "../grant/null.xhtml";
		courseID = this.getIntegerParameter("courseID");
		courseName = this.getParameter("courseName");
		searchTempChapter.setCourseID(courseID);
		tempChapterPage = tempChapterFacade.findPage(searchTempChapter);
		if (courseID != null) {
			majorID = courseFacade.getMajorIDByCourseID(courseID);
		}
		super.heighti2 = super.calHeight(14f / 20);
	}

	/**
	 * 查询
	 */
	public void search() {
		pageTable.setFirst(0);
		tempChapterPage = tempChapterFacade.findPage(searchTempChapter);
		this.updateComponent("searchForm:tempChapterList");
	}

	/**
	 * 添加计划项
	 */
	public void add() {
		url = "load/tempChapterUpdate.xhtml";
		updateTempChapter = new TempChapter();
		flag = 0;
		qzChapterCheck = null;
	}

	/**
	 * 编辑
	 */
	public void update(Integer chapterID) {
		url = "load/tempChapterUpdate.xhtml";
		flag = 1;
		updateTempChapter = tempChapterFacade.findByID(chapterID);
		qzChapterList = qzChapterFacade.getQzChapterList(courseID);
		if (StringUtils.isNotBlank(updateTempChapter.getQzChapter())) {
			qzChapterCheck = StringUtils.split(
					updateTempChapter.getQzChapter(), ",");
		} else {
			qzChapterCheck = null;
		}
	}

	/**
	 * 添加/修改计划项
	 */
	public void updateSubmit() {
		byte submitSuccess = 0;// 添加是否成功
		if (tempChapterFacade.checkTempChapter(flag, updateTempChapter)) {
			String studyAsk1 = StringUtil.nullToString(updateTempChapter
					.getStudyAsk1());
			if (studyAsk1.equals(""))
				studyAsk1 = "无";
			String studyAsk2 = StringUtil.nullToString(updateTempChapter
					.getStudyAsk2());
			if (studyAsk2.equals(""))
				studyAsk2 = "无";
			String studyAsk3 = StringUtil.nullToString(updateTempChapter
					.getStudyAsk3());
			if (studyAsk3.equals(""))
				studyAsk3 = "无";
			String studyAsk4 = StringUtil.nullToString(updateTempChapter
					.getStudyAsk4());
			if (studyAsk4.equals(""))
				studyAsk4 = "无";
			String studyAsk = "";
			studyAsk = studyAsk1 + "{|||}" + studyAsk2 + "{|||}" + studyAsk3
					+ "{|||}" + studyAsk4;
			studyAsk = studyAsk.replaceAll("<p>", "");
			studyAsk = studyAsk.replaceAll("</p>", "");
			updateTempChapter.setStudyAsk(studyAsk);
			if (qzChapterCheck != null && qzChapterCheck.length > 0) {
				updateTempChapter.setQzChapter(StringUtils.join(qzChapterCheck,
						","));
			}
			try {
				if (flag == 0) {
					updateTempChapter.setCourseID(courseID);
					updateTempChapter.setOpenStatus(new Short("0"));
					updateTempChapter.setCreator(this.getCurrentUserID());
					updateTempChapter.setCreateTime(new Date());
					int num = tempChapterFacade.getMaxSequence(courseID);
					updateTempChapter.setSequence(num + 1);
					tempChapterFacade.add(updateTempChapter);
					search();
					this.addCallbackParam("chapterID",
							updateTempChapter.getChapterID());
					this.addCallbackParam("chapterName",
							updateTempChapter.getChapterName());
				} else {
					if (updateTempChapter.getOpenStatus() == 0) {
						updateTempChapter.setOpenTime(null);
					} else {
						updateTempChapter.setOpenTime(new Date());
					}
					tempChapterFacade.update(updateTempChapter);
					tempChapterPage = tempChapterFacade
							.findPage(searchTempChapter);
					this.updateComponent("searchForm:tempChapterList");
				}
				submitSuccess = 1;
				updateTempChapter = null;
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("updateTempChapter=" + updateTempChapter);
				logger.error(ExceptionUtil.getEMsg(e));
				submitSuccess = -1;
			}
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public TempChapter getSearchTempChapter() {
		return searchTempChapter;
	}

	public void setSearchTempChapter(TempChapter searchTempChapter) {
		this.searchTempChapter = searchTempChapter;
	}

	public LazyDataModel<TempChapter> getTempChapterPage() {
		return tempChapterPage;
	}

	public void setTempChapterPage(LazyDataModel<TempChapter> tempChapterPage) {
		this.tempChapterPage = tempChapterPage;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public byte getFlag() {
		return flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

	public Integer getCourseID() {
		return courseID;
	}

	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setTempChapterFacade(TempChapterFacade tempChapterFacade) {
		this.tempChapterFacade = tempChapterFacade;
	}

	public TempChapter getUpdateTempChapter() {
		return updateTempChapter;
	}

	public void setUpdateTempChapter(TempChapter updateTempChapter) {
		this.updateTempChapter = updateTempChapter;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public String[] getQzChapterCheck() {
		return qzChapterCheck;
	}

	public void setQzChapterCheck(String[] qzChapterCheck) {
		this.qzChapterCheck = qzChapterCheck;
	}

	public void setQzChapterFacade(QzChapterFacade qzChapterFacade) {
		this.qzChapterFacade = qzChapterFacade;
	}

	public List<QzChapter> getQzChapterList() {
		return qzChapterList;
	}

	public void setQzChapterList(List<QzChapter> qzChapterList) {
		this.qzChapterList = qzChapterList;
	}

	public void setCourseFacade(CourseFacade courseFacade) {
		this.courseFacade = courseFacade;
	}

	public Integer getMajorID() {
		return majorID;
	}

}