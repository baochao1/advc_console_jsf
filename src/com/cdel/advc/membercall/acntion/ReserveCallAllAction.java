package com.cdel.advc.membercall.acntion;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.membercall.domain.ReserveCallAll;
import com.cdel.advc.membercall.facade.ReserveCallAllFacade;
import com.cdel.advc.teacher.domain.Teacher;
import com.cdel.util.BaseAction;

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
@ViewScoped
public class ReserveCallAllAction extends BaseAction<ReserveCallAll> implements
		Serializable {

	@ManagedProperty(value = "#{reserveCallAllFacade}")
	private ReserveCallAllFacade reserveCallAllFacade;

	private Integer siteID;// 网站ID
	private ReserveCallAll searchMemberCall = new ReserveCallAll();
	private LazyDataModel<ReserveCallAll> memberCallPage;

	@PostConstruct
	public void initMemberCallAction() {
		siteID = this.getCurrentSiteID();
		Teacher teacher = this.getCurrentTeacher();
		Short isHeader = teacher.getIsHeader();
		if (isHeader == 2) {// 组长
			searchMemberCall.setOrgID(teacher.getOrgID());
		}
		memberCallPage = this.reserveCallAllFacade.findPage(searchMemberCall);
		super.heighti2 = super.calHeight(11.5f / 20);
	}

	/**
	 * 查询
	 */
	public void search() {
		pageTable.setFirst(0);
	}

	public ReserveCallAll getSearchMemberCall() {
		return searchMemberCall;
	}

	public void setSearchMemberCall(ReserveCallAll searchMemberCall) {
		this.searchMemberCall = searchMemberCall;
	}

	public LazyDataModel<ReserveCallAll> getMemberCallPage() {
		return memberCallPage;
	}

	public void setMemberCallPage(LazyDataModel<ReserveCallAll> memberCallPage) {
		this.memberCallPage = memberCallPage;
	}

	public void setReserveCallAllFacade(
			ReserveCallAllFacade reserveCallAllFacade) {
		this.reserveCallAllFacade = reserveCallAllFacade;
	}

	public Integer getSiteID() {
		return siteID;
	}

}
