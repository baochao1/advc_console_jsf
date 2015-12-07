package com.cdel.advc.membercall.acntion;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang.StringUtils;
import org.primefaces.model.LazyDataModel;

import com.cdel.advc.membercall.domain.MemberCall;
import com.cdel.advc.membercall.domain.MemberCallNoCall;
import com.cdel.advc.membercall.facade.MemberCallNoCallFacade;
import com.cdel.advc.teacher.domain.Teacher;
import com.cdel.advc.teacher.facade.TeacherFacade;
import com.cdel.util.BaseAction;

/**
 * 
 * <p>
 * 回访提醒 查询 bean
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-12 下午4:46:09
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MemberCallNoCallAction extends BaseAction<MemberCall> implements
		Serializable {

	@ManagedProperty(value = "#{memberCallNoCallFacade}")
	private MemberCallNoCallFacade memberCallNoCallFacade;
	@ManagedProperty(value = "#{teacherFacade}")
	private TeacherFacade teacherFacade;

	/** 过滤查询条件 */
	private MemberCallNoCall searchMemberCall = new MemberCallNoCall();
	private LazyDataModel<MemberCallNoCall> memberCallPage;
	private Integer siteID;// 网站ID
	private boolean show = false;// 是否显示“过期回访记录”

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
			show = true;
		} else {
			searchMemberCall.setTeacherID(teacherID); // 教师助教的班
		}
		searchMemberCall.setOrgSet(StringUtils.join(searchMemberCall.getOrgIDs(), ","));
		memberCallPage = memberCallNoCallFacade.findPage(searchMemberCall);
		super.heighti2 = super.calHeight(11.5f / 20);
	}

	/**
	 * 查询
	 */
	public void search() {
		searchMemberCall.setOrgSet(StringUtils.join(searchMemberCall.getOrgIDs(), ","));
		memberCallPage = memberCallNoCallFacade.findPage(searchMemberCall);
		pageTable.setFirst(0);
	}

	public MemberCallNoCall getSearchMemberCall() {
		return searchMemberCall;
	}

	public void setSearchMemberCall(MemberCallNoCall searchMemberCall) {
		this.searchMemberCall = searchMemberCall;
	}

	public LazyDataModel<MemberCallNoCall> getMemberCallPage() {
		return memberCallPage;
	}

	public void setMemberCallPage(LazyDataModel<MemberCallNoCall> memberCallPage) {
		this.memberCallPage = memberCallPage;
	}

	public void setMemberCallNoCallFacade(
			MemberCallNoCallFacade memberCallNoCallFacade) {
		this.memberCallNoCallFacade = memberCallNoCallFacade;
	}

	public void setTeacherFacade(TeacherFacade teacherFacade) {
		this.teacherFacade = teacherFacade;
	}

	public Integer getSiteID() {
		return siteID;
	}

	public boolean isShow() {
		return show;
	}

}
