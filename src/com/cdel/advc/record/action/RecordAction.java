package com.cdel.advc.record.action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.course.domain.Course;
import com.cdel.advc.courseware.facade.CoursewareFacade;
import com.cdel.advc.memberclass.domain.MemberClass;
import com.cdel.advc.memberclass.facade.MemberClassFacade;
import com.cdel.advc.report.domain.CwTimeLong;
import com.cdel.qz.siteCourse.facade.QzSiteCourseFacade;
import com.cdel.util.BaseAction;
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
@ViewScoped
public class RecordAction extends BaseAction<BaseDomain> implements
		Serializable {

	@ManagedProperty("#{memberClassFacade}")
	private MemberClassFacade memberClassFacade;
	@ManagedProperty("#{coursewareFacade}")
	private CoursewareFacade coursewareFacade;
	@ManagedProperty("#{qzSiteCourseFacade}")
	private QzSiteCourseFacade qzSiteCourseFacade;

	private Integer userID;
	private Integer classID;
	private String userName;
	private List<Course> courseList;// 学员报的课程
	private Integer initSelectCourseID;// 初始选择的课程
	private Integer selectCourseID;// 选择的课程
	private Integer selectType = 1;// 选择的类型
	private Map<Integer, List<String>> cwIDsMap;// key:实验班课程ID,list学员报的课件
	private Map<Integer, List<Integer>> siteCourseMap;// key:实验班课程ID,list题库对外课程
	private LazyDataModel<CwTimeLong> timePage;// 学习时长记录

	@PostConstruct
	public void initRecordAction() {
		userID = this.getIntegerParameter("userID");
		classID = this.getIntegerParameter("classID");
		userName = this.getParameter("userName");
		MemberClass mc = new MemberClass();
		mc.setUserID(userID);
		mc.setClassID(classID);
		mc.setStatus((short) 1);
		List<MemberClass> mclist = memberClassFacade
				.getMemberClassAndCourse(mc);
		if (mclist != null && mclist.size() > 0) {
			cwIDsMap = new HashMap<Integer, List<String>>();
			siteCourseMap = new HashMap<Integer, List<Integer>>();
			courseList = mclist.get(0).getStudyCourseList();
			if (courseList != null && courseList.size() > 0) {
				initSelectCourseID = courseList.get(0).getCourseID();
				List<String> cwIDs = coursewareFacade
						.getCwareByCourseID(courseList.get(0).getCourseCode());
				List<Integer> siteCourseIDList = qzSiteCourseFacade
						.getSiteCourseIDList2(cwIDs);
				cwIDsMap.put(initSelectCourseID, cwIDs);
				siteCourseMap.put(initSelectCourseID, siteCourseIDList);
			}
		}
		super.heighti2 = super.calHeight(11.5f / 20);
	}

	public void setMemberClassFacade(MemberClassFacade memberClassFacade) {
		this.memberClassFacade = memberClassFacade;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setQzSiteCourseFacade(QzSiteCourseFacade qzSiteCourseFacade) {
		this.qzSiteCourseFacade = qzSiteCourseFacade;
	}

	public Integer getUserID() {
		return userID;
	}

	public Map<Integer, List<Integer>> getSiteCourseMap() {
		return siteCourseMap;
	}

	public Map<Integer, List<String>> getCwIDsMap() {
		return cwIDsMap;
	}

	public String getUserName() {
		return userName;
	}

	public Integer getInitSelectCourseID() {
		return initSelectCourseID;
	}

	public void setCoursewareFacade(CoursewareFacade coursewareFacade) {
		this.coursewareFacade = coursewareFacade;
	}

	public LazyDataModel<CwTimeLong> getTimePage() {
		return timePage;
	}

	public void setTimePage(LazyDataModel<CwTimeLong> timePage) {
		this.timePage = timePage;
	}

	public Integer getSelectCourseID() {
		return selectCourseID;
	}

	public void setSelectCourseID(Integer selectCourseID) {
		this.selectCourseID = selectCourseID;
	}

	public Integer getSelectType() {
		return selectType;
	}

	public void setSelectType(Integer selectType) {
		this.selectType = selectType;
	}

}