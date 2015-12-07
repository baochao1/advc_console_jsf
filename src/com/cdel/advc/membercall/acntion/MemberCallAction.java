package com.cdel.advc.membercall.acntion;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.classteacher.facade.ClassteacherFacade;
import com.cdel.advc.membercall.domain.MemberCall;
import com.cdel.advc.membercall.facade.MemberCallFacade;
import com.cdel.advc.teacher.domain.Teacher;
import com.cdel.advc.teacher.facade.TeacherFacade;
import com.cdel.util.BaseAction;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MemberCallAction extends BaseAction<MemberCall> implements
		Serializable {
	@ManagedProperty(value = "#{memberCallFacade}")
	private MemberCallFacade memberCallFacade;
	@ManagedProperty(value = "#{teacherFacade}")
	private TeacherFacade teacherFacade;
	@ManagedProperty(value = "#{classteacherFacade}")
	private ClassteacherFacade classteacherFacade;

	private MemberCall searchMemberCall = new MemberCall();
	private Integer siteID;// 网站ID
	private LazyDataModel<MemberCall> memberCallPage;

	@PostConstruct
	public void initMemberCallAction() {
		siteID = this.getCurrentSiteID();
		searchMemberCall.setSiteID(siteID);
		Integer teacherID = this.getCurrentUserID();
		Teacher teacher = this.getCurrentTeacher();// 查询登录人信息
		if (teacher.getParentID() == 1) {// 如果是教务人员可以看本组成员在其他班是总管理员的班级
			searchMemberCall.setTeachUser(true);
			List<Integer> list = teacherFacade
					.getSameOrgManagerTeacherList(teacherID);
			searchMemberCall.setSameOrgTeachers(list);
		} else {
			searchMemberCall.setTeachUser(false);
		}
		if (teacher.getIsHeader() == 0 || teacher.getIsHeader() == 1
				|| teacher.getOrgID() == 23)
			searchMemberCall.setTeacherID(null);// 技术部，经理，超管可以看所有班
		else if (teacher.getIsHeader() == 4) {// 教务督导，在不分配班级的情况下，可以查看所有班级
			int tutorListCount = classteacherFacade
					.getTutorCountByTeacherID(teacherID);
			if (tutorListCount > 0)
				searchMemberCall.setTeacherID(teacherID); // 教师助教的班
			else {
				searchMemberCall.setTeacherID(null);
			}
		} else {
			searchMemberCall.setTeacherID(teacherID); // 教师助教的班
		}
		memberCallPage = memberCallFacade.findPage(searchMemberCall);
	}

	/**
	 * 查询
	 * 
	 * @throws Exception
	 */
	public void search() {
		pageTable.setFirst(0);
		memberCallPage = memberCallFacade.findPage(searchMemberCall);
		this.updateComponent("searchForm:memberCallList");
	}

	public void setMemberCallFacade(MemberCallFacade memberCallFacade) {
		this.memberCallFacade = memberCallFacade;
	}

	public MemberCall getSearchMemberCall() {
		return searchMemberCall;
	}

	public void setSearchMemberCall(MemberCall searchMemberCall) {
		this.searchMemberCall = searchMemberCall;
	}

	public LazyDataModel<MemberCall> getMemberCallPage() {
		return memberCallPage;
	}

	public void setMemberCallPage(LazyDataModel<MemberCall> memberCallPage) {
		this.memberCallPage = memberCallPage;
	}

	public void setTeacherFacade(TeacherFacade teacherFacade) {
		this.teacherFacade = teacherFacade;
	}

	public void setClassteacherFacade(ClassteacherFacade classteacherFacade) {
		this.classteacherFacade = classteacherFacade;
	}

	public Integer getSiteID() {
		return siteID;
	}

}
