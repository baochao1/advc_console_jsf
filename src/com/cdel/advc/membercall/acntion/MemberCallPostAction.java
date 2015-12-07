package com.cdel.advc.membercall.acntion;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;

import com.cdel.advc.classes.domain.Classes;
import com.cdel.advc.classes.facade.ClassesFacade;
import com.cdel.advc.membercall.domain.MemberCall;
import com.cdel.advc.membercall.domain.MemberCallPost;
import com.cdel.advc.membercall.facade.MemberCallPostFacade;
import com.cdel.advc.teacher.domain.Teacher;
import com.cdel.advc.teacher.facade.TeacherFacade;
import com.cdel.util.BaseAction;

/**
 * 
 * <p>
 * 过期电话回访查询 bean
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-16 上午10:55:02
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MemberCallPostAction extends BaseAction<MemberCallPost> implements
		Serializable {

	@ManagedProperty(value = "#{memberCallPostFacade}")
	private MemberCallPostFacade memberCallPostFacade;
	@ManagedProperty(value = "#{teacherFacade}")
	private TeacherFacade teacherFacade;
	@ManagedProperty(value = "#{classesFacade}")
	private ClassesFacade classesFacade;

	private Integer siteID;// 网站ID
	private MemberCallPost searchMemberCall = new MemberCallPost();
	private LazyDataModel<MemberCallPost> memberCallPage;
	private MemberCall memberCall;
	private boolean showSelect = false;// 班级名称是否按select方式显示
	private List<Classes> cl;// 用户是班主任的班级

	@PostConstruct
	public void initMemberCallAction() {
		siteID = this.getCurrentSiteID();
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
				|| teacher.getOrgID() == 23 || teacher.getIsHeader() == 4) {
			searchMemberCall.setTeacherID(null);// 技术部，经理，超管可以看所有班
		} else {
			searchMemberCall.setTeacherID(teacherID); // 教师助教的班
			cl = classesFacade.getZhuRenClassList(teacher.getTeacherCode());
			if (cl != null && cl.size() > 0) {
				showSelect = true;
			}
		}
		searchMemberCall.setDayNumber((byte) 60);
		memberCallPage = memberCallPostFacade.findPage(searchMemberCall);
	}

	/**
	 * 查询
	 */
	public void search() {
		pageTable.setFirst(0);
	}

	public void setMemberCallPostFacade(
			MemberCallPostFacade memberCallPostFacade) {
		this.memberCallPostFacade = memberCallPostFacade;
	}

	public MemberCallPost getSearchMemberCall() {
		return searchMemberCall;
	}

	public void setSearchMemberCall(MemberCallPost searchMemberCall) {
		this.searchMemberCall = searchMemberCall;
	}

	public LazyDataModel<MemberCallPost> getMemberCallPage() {
		return memberCallPage;
	}

	public void setMemberCallPage(LazyDataModel<MemberCallPost> memberCallPage) {
		this.memberCallPage = memberCallPage;
	}

	public MemberCall getMemberCall() {
		return memberCall;
	}

	public void setMemberCall(MemberCall memberCall) {
		this.memberCall = memberCall;
	}

	public Integer getSiteID() {
		return siteID;
	}

	public void setTeacherFacade(TeacherFacade teacherFacade) {
		this.teacherFacade = teacherFacade;
	}

	public boolean isShowSelect() {
		return showSelect;
	}

	public void setClassesFacade(ClassesFacade classesFacade) {
		this.classesFacade = classesFacade;
	}

	public List<Classes> getCl() {
		return cl;
	}

	public void setCl(List<Classes> cl) {
		this.cl = cl;
	}

}
