package com.cdel.advc.gdb.member.action;

import java.io.Serializable;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.gdb.member.domain.AdvanceMember;
import com.cdel.advc.gdb.member.domain.MyAdvanceMember;
import com.cdel.advc.gdb.member.facade.AdvanceMemberFacade;
import com.cdel.advc.member.domain.Member;
import com.cdel.advc.member.facade.MemberFacade;
import com.cdel.advc.teacher.facade.TeacherFacade;
import com.cdel.advc.website.domain.Website;
import com.cdel.advc.website.facade.WebsiteFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;
import com.cdel.util.JsonUtil;

@SuppressWarnings("serial")
@ManagedBean
public class MyAdvanceMemberReqAction extends BaseAction<MyAdvanceMember>
		implements Serializable {

	@ManagedProperty("#{websiteFacade}")
	private WebsiteFacade websiteFacade;
	@ManagedProperty("#{memberFacade}")
	private MemberFacade memberFacade;
	@ManagedProperty("#{advanceMemberFacade}")
	private AdvanceMemberFacade advanceMemberFacade;
	@ManagedProperty("#{teacherFacade}")
	private TeacherFacade teacherFacade;

	private AdvanceMember member = new AdvanceMember();
	private byte submitSuccess = 0;// 修改是否成功

	/**
	 * 更新高端班学员手机号
	 * 
	 * @param classID
	 * @param siteID
	 */
	public void updateMemberPhone(String userName, Integer id, Integer siteID) {
		try {
			Website website = websiteFacade.findByID(siteID);
			if (website == null) {
				this.addWarnMessage("info", "当前网站不能为空！");
				return;
			}
			member.setId(id);
			Member m2 = JsonUtil.parseMember(memberFacade.getMemberFromRemote(
					userName, website, null));
			if (m2 != null) {
				member.setTelPhone(m2.getTelPhone());
			}
			advanceMemberFacade.update(member);
			MyAdvanceMemberAction mca = (MyAdvanceMemberAction) this
					.getViewAction("myAdvanceMemberAction");
			mca.search4U();
			this.addInfoMessage("info", SUCESSINFO);
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
		}
	}

	/**
	 * 学员换老师
	 * 
	 * @param id
	 * @param teacherID
	 *            ：原教师ID
	 */
	public void changeTeacher(Integer id, String userName, Integer teacherID) {
		member.setId(id);
		member.setTeacherID(teacherID);
		member.setUserName(userName);
		MyAdvanceMemberAction ma = (MyAdvanceMemberAction) this
				.getViewAction("myAdvanceMemberAction");
		ma.setGdbteacherList(teacherFacade.selectAllAdvanceTeacher());
	}

	/**
	 * 学员换老师提交
	 */
	public void updateMemberTeacher() {
		try {
			advanceMemberFacade.update(member);
			MyAdvanceMemberAction ma = (MyAdvanceMemberAction) this
					.getViewAction("myAdvanceMemberAction");
			ma.search4U();
			submitSuccess = 1;
		} catch (Exception e) {
			e.printStackTrace();
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 退班
	 * 
	 * @param userID
	 * @param majorID
	 */
	public void updateAdvanceMemberStatus(Integer userID, Integer majorID) {
		AdvanceMember advanceMember = new AdvanceMember();
		try {
			advanceMember.setUserID(userID);
			advanceMember.setStatus((short) 0);
			advanceMember.setMajorID(majorID);
			advanceMemberFacade.updateAdvanceMemberStatus(advanceMember);
			this.addInfoMessage("info", "退班成功！");
			MyAdvanceMemberAction ma = (MyAdvanceMemberAction) this
					.getViewAction("myAdvanceMemberAction");
			ma.search4U();
		} catch (Exception e) {
			this.addErrorMessage("info", FAILINFO);
			e.printStackTrace();
		}
	}

	public Map<Short, String> getStatus() {
		return Contants.status;
	}

	public void setWebsiteFacade(WebsiteFacade websiteFacade) {
		this.websiteFacade = websiteFacade;
	}

	public void setMemberFacade(MemberFacade memberFacade) {
		this.memberFacade = memberFacade;
	}

	public void setAdvanceMemberFacade(AdvanceMemberFacade advanceMemberFacade) {
		this.advanceMemberFacade = advanceMemberFacade;
	}

	public AdvanceMember getMember() {
		return member;
	}

	public void setMember(AdvanceMember member) {
		this.member = member;
	}

	public void setTeacherFacade(TeacherFacade teacherFacade) {
		this.teacherFacade = teacherFacade;
	}

}
