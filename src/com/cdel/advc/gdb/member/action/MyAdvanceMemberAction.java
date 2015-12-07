package com.cdel.advc.gdb.member.action;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.gdb.member.domain.MyAdvanceMember;
import com.cdel.advc.gdb.member.facade.MyAdvanceMemberFacade;
import com.cdel.advc.teacher.domain.Teacher;
import com.cdel.util.BaseAction;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MyAdvanceMemberAction extends BaseAction<MyAdvanceMember>
		implements Serializable {
	@ManagedProperty("#{myAdvanceMemberFacade}")
	private MyAdvanceMemberFacade myGdbMemberFacade;

	/** DataTable组件分页模型 */
	private LazyDataModel<MyAdvanceMember> page;
	/** 查询条件 */
	private MyAdvanceMember filterGdbMember = new MyAdvanceMember();
	/** 网站ID */
	private Integer siteID;
	private List<Teacher> gdbteacherList;// 高端班教室List

	@PostConstruct
	public void initMyAdvanceMemberAction() {
		siteID = this.getCurrentSiteID();
		Teacher teacher = this.getCurrentTeacher();
		Integer orgID = teacher.getOrgID();
		Short isHeader = teacher.getIsHeader();
		if (isHeader == 0) {// 超管
			filterGdbMember.setOrgID(null);
		} else if (isHeader == 1 || isHeader == 4) {// 经理
			filterGdbMember.setOrgID(null);
		} else if (isHeader == 2) {// 组长
			filterGdbMember.setOrgID(orgID);
		} else {
			filterGdbMember.setTeacherID(this.getCurrentUserID());
		}
		filterGdbMember.setCourseStatus((short) -1);
		page = myGdbMemberFacade.findPage(filterGdbMember);
	}

	public void search() {
		pageTable.setFirst(0);
		search4U();
	}

	public void search4U() {
		page = myGdbMemberFacade.findPage(filterGdbMember);
		this.updateComponent("searchForm:advanceMemberList");
	}

	public LazyDataModel<MyAdvanceMember> getPage() {
		return page;
	}

	public void setPage(LazyDataModel<MyAdvanceMember> page) {
		this.page = page;
	}

	public MyAdvanceMember getFilterGdbMember() {
		return filterGdbMember;
	}

	public void setFilterGdbMember(MyAdvanceMember filterGdbMember) {
		this.filterGdbMember = filterGdbMember;
	}

	public Integer getSiteID() {
		return siteID;
	}

	public void setMyGdbMemberFacade(MyAdvanceMemberFacade myGdbMemberFacade) {
		this.myGdbMemberFacade = myGdbMemberFacade;
	}

	public void setGdbteacherList(List<Teacher> gdbteacherList) {
		this.gdbteacherList = gdbteacherList;
	}

	public List<Teacher> getGdbteacherList() {
		return gdbteacherList;
	}

}
