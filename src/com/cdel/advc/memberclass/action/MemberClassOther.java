package com.cdel.advc.memberclass.action;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.cdel.advc.member.domain.Member;
import com.cdel.advc.member.facade.MemberFacade;

/**
 * 用于其它地方
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MemberClassOther implements Serializable {

	@ManagedProperty(value = "#{memberFacade}")
	private MemberFacade memberFacade;

	private List<Member> memberList;// 本班学员列表

	public List<Member> getMemberList() {
		return memberList;
	}
	
	public void setMemberList(Integer classID) {
		this.memberList = memberFacade.findList(classID);
	}

	public void setMemberFacade(MemberFacade memberFacade) {
		this.memberFacade = memberFacade;
	}

}
