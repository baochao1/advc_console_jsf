package com.cdel.advc.membermsg.action;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.membermsg.domain.Membermsg;
import com.cdel.advc.membermsg.facade.MembermsgFacade;
import com.cdel.advc.teacher.domain.Teacher;
import com.cdel.advc.teacher.facade.TeacherFacade;
import com.cdel.util.BaseAction;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MembermsgAction extends BaseAction<Membermsg> implements
		Serializable {
	@ManagedProperty(value = "#{membermsgFacade}")
	private MembermsgFacade membermsgFacade;
	@ManagedProperty(value = "#{teacherFacade}")
	private TeacherFacade teacherFacade;

	private Membermsg searchMembermsg = new Membermsg();
	private LazyDataModel<Membermsg> membermsgPage;
	private Integer siteID;// 网站ID

	@PostConstruct
	public void initTechnologyMsgAction() {
		siteID = this.getCurrentSiteID();
		searchMembermsg.setStatus(new Short("1"));
		searchMembermsg.setReply(0);
		Integer teacherID = this.getCurrentUserID();
		Teacher teacher = this.getCurrentTeacher();// 查询登录人信息
		if (teacher.getParentID() == 1) {// 如果是教务人员可以看本组成员在其他班是总管理员的班级
			searchMembermsg.setTeachUser(true);
			List<Integer> list = teacherFacade
					.getSameOrgManagerTeacherList(teacherID);
			searchMembermsg.setSameOrgTeachers(list);
		} else {
			searchMembermsg.setTeachUser(false);
		}
		if (teacher.getIsHeader().intValue() == 0
				|| teacher.getIsHeader().intValue() == 1
				|| teacher.getIsHeader().intValue() == 4
				|| teacher.getOrgID().intValue() == 23)
			searchMembermsg.setTeacherID(null);// 技术部，经理，超管可以看所有班
		else {
			searchMembermsg.setTeacherID(teacherID); // 教师助教的班
		}
		this.membermsgPage = this.membermsgFacade.findPage(searchMembermsg);
	}

	/**
	 * 查询
	 * 
	 * @throws Exception
	 */
	public void search() {
		pageTable.setFirst(0);
		this.membermsgPage = this.membermsgFacade.findPage(searchMembermsg);
		this.updateComponent("searchForm:membermsgList");
	}

	public void setMembermsgFacade(MembermsgFacade membermsgFacade) {
		this.membermsgFacade = membermsgFacade;
	}

	public Membermsg getSearchMembermsg() {
		return searchMembermsg;
	}

	public void setSearchMembermsg(Membermsg searchMembermsg) {
		this.searchMembermsg = searchMembermsg;
	}

	public LazyDataModel<Membermsg> getMembermsgPage() {
		return membermsgPage;
	}

	public void setMembermsgPage(LazyDataModel<Membermsg> membermsgPage) {
		this.membermsgPage = membermsgPage;
	}

	public void setTeacherFacade(TeacherFacade teacherFacade) {
		this.teacherFacade = teacherFacade;
	}

	public Integer getSiteID() {
		return siteID;
	}

}
