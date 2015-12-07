package com.cdel.advc.memberSms.action;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.memberSms.domain.MemberSms;
import com.cdel.advc.memberSms.facade.MemberSmsFacade;
import com.cdel.util.BaseAction;

/**
 * 学员发短信
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MemberSmsAction extends BaseAction<MemberSms> {
	@ManagedProperty("#{memberSmsFacade}")
	private MemberSmsFacade memberSmsFacade;

	private LazyDataModel<MemberSms> page;
	private MemberSms filterMemberSms = new MemberSms();
	private Integer classID;
	private Integer userID;

	public void initSearch() {
		this.filterMemberSms.setUserID(userID);
		this.filterMemberSms.setClassID(classID);
		this.page = this.memberSmsFacade.findPage(filterMemberSms);
	}

	/**
	 * 查询
	 * 
	 * @throws Exception
	 */
	public void search() {
		this.pageTable.setFirst(0);
		this.page = this.memberSmsFacade.findPage(filterMemberSms);
	}

	public LazyDataModel<MemberSms> getPage() {
		return page;
	}

	public void setPage(LazyDataModel<MemberSms> page) {
		this.page = page;
	}

	public MemberSms getFilterMemberSms() {
		return filterMemberSms;
	}

	public void setFilterMemberSms(MemberSms filterMemberSms) {
		this.filterMemberSms = filterMemberSms;
	}

	public void setMemberSmsFacade(MemberSmsFacade memberSmsFacade) {
		this.memberSmsFacade = memberSmsFacade;
	}

	public Integer getClassID() {
		return classID;
	}

	public void setClassID(Integer classID) {
		this.classID = classID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

}
