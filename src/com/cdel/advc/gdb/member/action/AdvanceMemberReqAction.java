package com.cdel.advc.gdb.member.action;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.gdb.member.domain.AdvanceMember;
import com.cdel.advc.gdb.member.facade.AdvanceMemberFacade;
import com.cdel.util.BaseAction;

@SuppressWarnings("serial")
@ManagedBean
public class AdvanceMemberReqAction extends BaseAction<AdvanceMember> implements
		Serializable {
	@ManagedProperty(value = "#{advanceMemberFacade}")
	private AdvanceMemberFacade advanceMemberFacade;

	/**
	 * 同步高端班学员
	 */
	public void getRemoteAdvanceMember() {
		byte submitSuccess = 0;// 是否同步成功
		try {
			advanceMemberFacade.getRemoteAdvanceMember();
			submitSuccess = 1;
		} catch (Exception e) {
			e.printStackTrace();
			submitSuccess = -1;
			logger.error("同步失败!");
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 同步高端班学员
	 */
	public void getRemoteAdvanceMemberForKF() {
		byte submitSuccess = 0;// 是否同步成功
		try {
			advanceMemberFacade.getRemoteAdvanceMemberForKF();
			submitSuccess = 1;
		} catch (Exception e) {
			e.printStackTrace();
			submitSuccess = -1;
			logger.error("同步失败!");
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public void setAdvanceMemberFacade(AdvanceMemberFacade advanceMemberFacade) {
		this.advanceMemberFacade = advanceMemberFacade;
	}

}
