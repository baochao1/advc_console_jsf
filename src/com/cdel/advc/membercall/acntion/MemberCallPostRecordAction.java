package com.cdel.advc.membercall.acntion;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.membercall.domain.MemberCall;
import com.cdel.advc.membercall.domain.MemberCallPostRecord;
import com.cdel.advc.membercall.facade.MemberCallPostRecordFacade;
import com.cdel.util.BaseAction;

/**
 * 
 * <p>
 * 过期回访记录查询 bean
 * </p>
 * 
 * @author 张苏磊
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MemberCallPostRecordAction extends BaseAction<MemberCall>
		implements Serializable {

	@ManagedProperty(value = "#{memberCallPostRecordFacade}")
	private MemberCallPostRecordFacade memberCallPostRecordFacade;

	private Integer siteID;// 网站ID
	private MemberCallPostRecord searchMemberCall = new MemberCallPostRecord();
	private LazyDataModel<MemberCallPostRecord> memberCallPage;

	@PostConstruct
	public void initMemberCallAction() {
		siteID = this.getCurrentSiteID();
		searchMemberCall.setCallStatus((short) -1);
		memberCallPage = memberCallPostRecordFacade.findPage(searchMemberCall);
	}

	/**
	 * 查询
	 */
	public void search() {
		pageTable.setFirst(0);
	}

	public MemberCallPostRecord getSearchMemberCall() {
		return searchMemberCall;
	}

	public void setSearchMemberCall(MemberCallPostRecord searchMemberCall) {
		this.searchMemberCall = searchMemberCall;
	}

	public LazyDataModel<MemberCallPostRecord> getMemberCallPage() {
		return memberCallPage;
	}

	public void setMemberCallPage(
			LazyDataModel<MemberCallPostRecord> memberCallPage) {
		this.memberCallPage = memberCallPage;
	}

	public void setMemberCallPostRecordFacade(
			MemberCallPostRecordFacade memberCallPostRecordFacade) {
		this.memberCallPostRecordFacade = memberCallPostRecordFacade;
	}

	public Integer getSiteID() {
		return siteID;
	}

}
