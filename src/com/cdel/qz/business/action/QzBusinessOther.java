package com.cdel.qz.business.action;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.cdel.qz.business.domain.QzBusiness;
import com.cdel.qz.business.facade.QzBusinessFacade;

/**
 * 
 * <p>
 * 专业action
 * </p>
 * 
 * @author 张苏磊
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class QzBusinessOther implements Serializable {

	@ManagedProperty(value = "#{qzBusinessFacade}")
	private QzBusinessFacade qzBusinessFacade;

	private List<QzBusiness> businessList;// 专业List

	public List<QzBusiness> getBusinessList() {
		return businessList;
	}

	public void setBusinessList() {
		this.businessList = qzBusinessFacade.findList(1);
	}

	public void setQzBusinessFacade(QzBusinessFacade qzBusinessFacade) {
		this.qzBusinessFacade = qzBusinessFacade;
	}

}
