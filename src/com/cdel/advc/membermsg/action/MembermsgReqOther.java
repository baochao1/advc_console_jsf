package com.cdel.advc.membermsg.action;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import com.cdel.advc.common.JsfHelper;
import com.cdel.advc.membermsg.domain.Membermsg;
import com.cdel.advc.membermsg.facade.MembermsgFacade;
import com.cdel.advc.teacher.domain.Teacher;
import com.cdel.advc.teacher.facade.TeacherFacade;
import com.cdel.util.Contants;
import com.chnedu.plat.rad.util.RequestHandler;

@SuppressWarnings("serial")
@ManagedBean
public class MembermsgReqOther implements Serializable {
	@ManagedProperty(value = "#{membermsgFacade}")
	private MembermsgFacade membermsgFacade;
	@ManagedProperty(value = "#{teacherFacade}")
	private TeacherFacade teacherFacade;

	private Integer membermsgCount = 0;// 学员消息总数

	/**
	 * 获取学员消息总数
	 */
	public void initMembermsgCount() {
		Integer teacherID = RequestHandler.getCurrentAdmin(JsfHelper
				.getRequest(FacesContext.getCurrentInstance()));
		Membermsg searchMembermsg = new Membermsg();
		searchMembermsg.setStatus(new Short("1"));
		searchMembermsg.setReply(0);
		Teacher teacher = (Teacher) JsfHelper.getSession(
				FacesContext.getCurrentInstance()).getAttribute(
				Contants.CLASS_TEACHER);// 查询登录人信息
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
		membermsgCount = this.membermsgFacade.countMembermsg(searchMembermsg);
	}

	public Integer getMembermsgCount() {
		return membermsgCount;
	}

	public void setMembermsgFacade(MembermsgFacade membermsgFacade) {
		this.membermsgFacade = membermsgFacade;
	}

	public void setTeacherFacade(TeacherFacade teacherFacade) {
		this.teacherFacade = teacherFacade;
	}

}
