package com.cdel.advc.membercall.acntion;

import java.io.Serializable;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.membercall.domain.MemberCall;
import com.cdel.advc.membercall.facade.MemberCallFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;

@SuppressWarnings("serial")
@ManagedBean
public class MemberCallReqAction extends BaseAction<MemberCall> implements
		Serializable {
	@ManagedProperty(value = "#{memberCallFacade}")
	private MemberCallFacade memberCallFacade;

	private boolean showDialogShow = false;// 控制是否显示Dialog
	private MemberCall memberCall;

	/** 回访状态 */
	public Map<Short, String> getCallStatuss() {
		return Contants.callStatus;
	}

	/** 查看详情 */
	public void showMemberCall(Integer callID) {
		memberCall = this.memberCallFacade.findByID(callID);
		showDialogShow = true;
	}

	public boolean isShowDialogShow() {
		return showDialogShow;
	}

	public void setShowDialogShow(boolean showDialogShow) {
		this.showDialogShow = showDialogShow;
	}

	public void setMemberCallFacade(MemberCallFacade memberCallFacade) {
		this.memberCallFacade = memberCallFacade;
	}

	public MemberCall getMemberCall() {
		return memberCall;
	}

	public void setMemberCall(MemberCall memberCall) {
		this.memberCall = memberCall;
	}

}
