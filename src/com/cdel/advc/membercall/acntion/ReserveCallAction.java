package com.cdel.advc.membercall.acntion;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.membercall.domain.ReserveCall;
import com.cdel.advc.membercall.facade.ReserveCallFacade;
import com.cdel.advc.teacher.domain.Teacher;
import com.cdel.advc.teacher.facade.TeacherFacade;
import com.cdel.util.BaseAction;

/**
 * 
 * <p>
 * 预约回访查询 bean
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-16 下午5:26:55
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ReserveCallAction extends BaseAction<ReserveCall> implements
		Serializable {

	@ManagedProperty(value = "#{reserveCallFacade}")
	private ReserveCallFacade reserveCallFacade;
	@ManagedProperty(value = "#{teacherFacade}")
	private TeacherFacade teacherFacade;

	private Integer siteID;// 网站ID
	private ReserveCall searchMemberCall = new ReserveCall();
	private LazyDataModel<ReserveCall> memberCallPage;

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
				|| teacher.getOrgID() == 23 || teacher.getIsHeader() == 4)
			searchMemberCall.setTeacherID(null);// 技术部，经理，超管可以看所有班
		else {
			searchMemberCall.setTeacherID(teacherID); // 教师助教的班
		}
		memberCallPage = this.reserveCallFacade.findPage(searchMemberCall);
		super.heighti2 = super.calHeight(11.5f / 20);
	}

	/**
	 * 查询
	 */
	public void search() {
		pageTable.setFirst(0);
	}

	public Integer getSiteID() {
		return siteID;
	}

	public void setTeacherFacade(TeacherFacade teacherFacade) {
		this.teacherFacade = teacherFacade;
	}

	public ReserveCall getSearchMemberCall() {
		return searchMemberCall;
	}

	public void setSearchMemberCall(ReserveCall searchMemberCall) {
		this.searchMemberCall = searchMemberCall;
	}

	public LazyDataModel<ReserveCall> getMemberCallPage() {
		return memberCallPage;
	}

	public void setMemberCallPage(LazyDataModel<ReserveCall> memberCallPage) {
		this.memberCallPage = memberCallPage;
	}

	public void setReserveCallFacade(ReserveCallFacade reserveCallFacade) {
		this.reserveCallFacade = reserveCallFacade;
	}

}
