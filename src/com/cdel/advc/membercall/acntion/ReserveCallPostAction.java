package com.cdel.advc.membercall.acntion;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.membercall.domain.ReserveCallPost;
import com.cdel.advc.membercall.facade.ReserveCallPostFacade;
import com.cdel.util.BaseAction;

/**
 * 
 * <p>
 * 过期预约回访查询 bean
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-17 上午11:53:31
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ReserveCallPostAction extends BaseAction<ReserveCallPost>
		implements Serializable {

	@ManagedProperty(value = "#{reserveCallPostFacade}")
	private ReserveCallPostFacade reserveCallPostFacade;

	/** 过滤查询条件 */
	private ReserveCallPost searchMemberCall = new ReserveCallPost();
	private LazyDataModel<ReserveCallPost> memberCallPage;

	@PostConstruct
	public void initMemberCallReservePostAction() {
		memberCallPage = this.reserveCallPostFacade.findPage(searchMemberCall);
		super.heighti2 = super.calHeight(11.5f / 20);
	}

	/**
	 * 查询
	 */
	public void search() {
		pageTable.setFirst(0);
	}

	public ReserveCallPost getSearchMemberCall() {
		return searchMemberCall;
	}

	public void setSearchMemberCall(ReserveCallPost searchMemberCall) {
		this.searchMemberCall = searchMemberCall;
	}

	public LazyDataModel<ReserveCallPost> getMemberCallPage() {
		return memberCallPage;
	}

	public void setMemberCallPage(LazyDataModel<ReserveCallPost> memberCallPage) {
		this.memberCallPage = memberCallPage;
	}

	public void setReserveCallPostFacade(
			ReserveCallPostFacade reserveCallPostFacade) {
		this.reserveCallPostFacade = reserveCallPostFacade;
	}

}
