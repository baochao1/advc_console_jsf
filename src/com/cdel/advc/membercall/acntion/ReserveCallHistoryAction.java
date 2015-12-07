package com.cdel.advc.membercall.acntion;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.membercall.domain.ReserveCallAll;
import com.cdel.advc.membercall.domain.ReserveCallHistory;
import com.cdel.advc.membercall.facade.ReserveCallHistoryFacade;
import com.cdel.util.BaseAction;

/**
 * 
 * <p>
 * 预约回访历史查询 bean
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-16 下午6:28:35
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ReserveCallHistoryAction extends BaseAction<ReserveCallAll>
		implements Serializable {

	@ManagedProperty(value = "#{reserveCallHistoryFacade}")
	private ReserveCallHistoryFacade reserveCallHistoryFacade;

	/** 过滤查询条件 */
	private ReserveCallHistory searchMemberCall = new ReserveCallHistory();
	private LazyDataModel<ReserveCallHistory> memberCallPage;

	@PostConstruct
	public void initMemberCallAction() {
		memberCallPage = reserveCallHistoryFacade.findPage(searchMemberCall);
		super.heighti2 = super.calHeight(12.5f / 20);
	}

	/**
	 * 查询
	 */
	public void search() {
		pageTable.setFirst(0);
	}

	public ReserveCallHistory getSearchMemberCall() {
		return searchMemberCall;
	}

	public void setSearchMemberCall(ReserveCallHistory searchMemberCall) {
		this.searchMemberCall = searchMemberCall;
	}

	public LazyDataModel<ReserveCallHistory> getMemberCallPage() {
		return memberCallPage;
	}

	public void setMemberCallPage(
			LazyDataModel<ReserveCallHistory> memberCallPage) {
		this.memberCallPage = memberCallPage;
	}

	public void setReserveCallHistoryFacade(
			ReserveCallHistoryFacade reserveCallHistoryFacade) {
		this.reserveCallHistoryFacade = reserveCallHistoryFacade;
	}

}
