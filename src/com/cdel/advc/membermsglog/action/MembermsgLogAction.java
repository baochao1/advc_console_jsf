package com.cdel.advc.membermsglog.action;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.cdel.advc.membermsglog.domain.MembermsgLog;
import com.cdel.advc.membermsglog.facade.MembermsgLogFacade;
import com.cdel.util.BaseAction;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MembermsgLogAction extends BaseAction<MembermsgLog> implements
		Serializable {

	@ManagedProperty("#{membermsgLogFacade}")
	private MembermsgLogFacade membermsgLogFacade;

	private List<MembermsgLog> membermsgLogList;

	@PostConstruct
	public void initMembermsgLogAction() {
		MembermsgLog lg = new MembermsgLog();
		lg.setMsgID(this.getIntegerParameter("msgID"));
		membermsgLogList = membermsgLogFacade.findList(lg);
	}

	public List<MembermsgLog> getMembermsgLogList() {
		return membermsgLogList;
	}

	public void setMembermsgLogList(List<MembermsgLog> membermsgLogList) {
		this.membermsgLogList = membermsgLogList;
	}

	public void setMembermsgLogFacade(MembermsgLogFacade membermsgLogFacade) {
		this.membermsgLogFacade = membermsgLogFacade;
	}

}
