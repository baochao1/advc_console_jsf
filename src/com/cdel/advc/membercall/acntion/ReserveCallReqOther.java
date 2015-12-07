package com.cdel.advc.membercall.acntion;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.membercall.domain.ReserveCall;
import com.cdel.advc.membercall.facade.ReserveCallFacade;
import com.cdel.advc.teacher.domain.Teacher;
import com.cdel.advc.teacher.facade.TeacherFacade;
import com.cdel.util.BaseNoLoginAction;
import com.cdel.util.Contants;

/**
 * 
 * <p>
 * 全部预约回访查询 bean
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-16 下午5:26:55
 */
@SuppressWarnings("serial")
@ManagedBean
public class ReserveCallReqOther extends BaseNoLoginAction<ReserveCallReqOther>
		implements Serializable {

	@ManagedProperty(value = "#{reserveCallFacade}")
	private ReserveCallFacade reserveCallFacade;
	@ManagedProperty(value = "#{teacherFacade}")
	private TeacherFacade teacherFacade;

	private Integer reservecallCount = 0;// 全部预约回访数

	/**
	 * 全部预约回访数
	 */
	public void initReservecallAllCount() {
		ReserveCall searchMemberCall = new ReserveCall();
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
		reservecallCount = this.reserveCallFacade
				.countReserveCall(searchMemberCall);
	}

	/** 回访类别 */
	public Map<Short, String> getCallTypes() {
		return Contants.stuCallType;
	}

	public Integer getReservecallCount() {
		return reservecallCount;
	}

	public void setReserveCallFacade(ReserveCallFacade reserveCallFacade) {
		this.reserveCallFacade = reserveCallFacade;
	}

	public void setTeacherFacade(TeacherFacade teacherFacade) {
		this.teacherFacade = teacherFacade;
	}

}
