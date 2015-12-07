package com.cdel.advc.teacherorg.action;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.cdel.advc.teacherorg.domain.TeacherOrg;
import com.cdel.advc.teacherorg.facade.TeacherOrgFacade;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class TeacherOrgOther implements Serializable {

	@ManagedProperty("#{teacherOrgFacade}")
	private TeacherOrgFacade teacherOrgFacade;

	private List<TeacherOrg> teacherOrgs;

	public List<TeacherOrg> getTeacherOrgs() {
		return teacherOrgs;
	}

	public void setTeacherOrgs() {
		this.teacherOrgs = teacherOrgFacade.getOrgList();
	}

	public void setTeacherOrgFacade(TeacherOrgFacade teacherOrgFacade) {
		this.teacherOrgFacade = teacherOrgFacade;
	}

}
