package com.cdel.advc.membercall.acntion;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang.StringUtils;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.membercall.domain.MemberCallAll;
import com.cdel.advc.membercall.facade.MemberCallAllFacade;
import com.cdel.advc.teacher.domain.Teacher;
import com.cdel.util.BaseAction;

/**
 * 
 * <p>
 * 全部回访查询 bean
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-12 下午4:46:09
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MemberCallAllAction extends BaseAction<MemberCallAll> implements
		Serializable {

	@ManagedProperty(value = "#{memberCallAllFacade}")
	private MemberCallAllFacade memberCallAllFacade;

	/** 过滤查询条件 */
	private MemberCallAll searchMemberCall = new MemberCallAll();
	private LazyDataModel<MemberCallAll> memberCallPage;
	protected DataTable pageTable4T;
	private LazyDataModel<MemberCallAll> memberCallPage4T;
	private Integer siteID;// 网站ID
	private boolean showTeacher = true;

	@PostConstruct
	public void initMemberCallAction() {
		siteID = this.getCurrentSiteID();
		String teacherName = this.getParameterDecode("teacherName");
		if (StringUtils.isNotBlank(teacherName)) {
			searchMemberCall.setTeacherName(teacherName);
		}
		Teacher teacher = this.getCurrentTeacher();// 查询登录人信息
		if (teacher.getIsHeader() == 3) {
			// 普通组员
			searchMemberCall.setTeacherName(teacher.getTeacherCode());
			showTeacher = false;// 不需要教师查询条件，只能查询自己
		}
		searchMemberCall.setStatus((short) 1);
		memberCallPage = memberCallAllFacade.findPage(searchMemberCall);
	}

	/**
	 * 查询
	 */
	public void search() {
		if (searchMemberCall.getCallStatus() != null
				&& searchMemberCall.getCallStatus() == 3) {
			searchMemberCall.setStartDate(null);
			searchMemberCall.setEndDate(null);
		}
		pageTable.setFirst(0);
	}

	/**
	 * 按教师查询
	 */
	public void searchTeacherInit() {
		memberCallPage4T = memberCallAllFacade
				.findPage4Teacher(searchMemberCall);
		super.heighti2 = super.calHeight(10f / 20);
	}

	public void searchTeacher() {
		pageTable4T.setFirst(0);
	}

	public void setMemberCallAllFacade(MemberCallAllFacade memberCallAllFacade) {
		this.memberCallAllFacade = memberCallAllFacade;
	}

	public MemberCallAll getSearchMemberCall() {
		return searchMemberCall;
	}

	public void setSearchMemberCall(MemberCallAll searchMemberCall) {
		this.searchMemberCall = searchMemberCall;
	}

	public LazyDataModel<MemberCallAll> getMemberCallPage() {
		return memberCallPage;
	}

	public void setMemberCallPage(LazyDataModel<MemberCallAll> memberCallPage) {
		this.memberCallPage = memberCallPage;
	}

	public LazyDataModel<MemberCallAll> getMemberCallPage4T() {
		return memberCallPage4T;
	}

	public void setMemberCallPage4T(
			LazyDataModel<MemberCallAll> memberCallPage4T) {
		this.memberCallPage4T = memberCallPage4T;
	}

	public DataTable getPageTable4T() {
		return pageTable4T;
	}

	public void setPageTable4T(DataTable pageTable4T) {
		this.pageTable4T = pageTable4T;
	}

	public boolean isShowTeacher() {
		return showTeacher;
	}

	public Integer getSiteID() {
		return siteID;
	}

}
