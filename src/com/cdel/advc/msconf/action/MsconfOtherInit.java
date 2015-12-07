package com.cdel.advc.msconf.action;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.cdel.advc.msconf.domain.Msconf;
import com.cdel.advc.msconf.facade.MsconfFacade;
import com.cdel.util.BaseAction;

/**
 * 面授班管理
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MsconfOtherInit extends BaseAction<Msconf> implements Serializable {

	@ManagedProperty(value = "#{msconfFacade}")
	private MsconfFacade msconfFacade;

	private Integer isMs;

	@PostConstruct
	public void init() {
		isMs = msconfFacade.getIsMs(this.getIntegerParameter("classID"));
	}

	public void setMsconfFacade(MsconfFacade msconfFacade) {
		this.msconfFacade = msconfFacade;
	}

	public Integer getIsMs() {
		return isMs;
	}

}
