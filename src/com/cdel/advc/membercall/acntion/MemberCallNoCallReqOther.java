package com.cdel.advc.membercall.acntion;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.membercall.domain.MemberCallNoCall;
import com.cdel.advc.membercall.facade.MemberCallNoCallFacade;
import com.cdel.advc.teacher.domain.Teacher;
import com.cdel.advc.teacher.facade.TeacherFacade;
import com.cdel.util.BaseNoLoginAction;

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
public class MemberCallNoCallReqOther extends
		BaseNoLoginAction<MemberCallNoCallReqOther> implements Serializable {

	@ManagedProperty(value = "#{memberCallNoCallFacade}")
	private MemberCallNoCallFacade memberCallNoCallFacade;
	@ManagedProperty(value = "#{teacherFacade}")
	private TeacherFacade teacherFacade;

	private Integer memberCallNoCallCount = 0;

	/**
	 * 回访提醒数
	 */
	public void initMemberCallNoCallCount() {
		MemberCallNoCall searchMemberCall = new MemberCallNoCall();
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
		memberCallNoCallCount = this.memberCallNoCallFacade
				.countMemberCallNoCall(searchMemberCall);
	}

	public Integer getMemberCallNoCallCount() {
		return memberCallNoCallCount;
	}

	public void setMemberCallNoCallFacade(
			MemberCallNoCallFacade memberCallNoCallFacade) {
		this.memberCallNoCallFacade = memberCallNoCallFacade;
	}

	public void setTeacherFacade(TeacherFacade teacherFacade) {
		this.teacherFacade = teacherFacade;
	}

}
