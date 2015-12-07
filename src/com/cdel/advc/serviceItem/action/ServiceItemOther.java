package com.cdel.advc.serviceItem.action;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UISelectOne;
import javax.faces.event.AjaxBehaviorEvent;
import com.cdel.advc.serviceItem.domain.ServiceItem;
import com.cdel.advc.serviceItem.facade.ServiceItemFacade;
import com.cdel.util.BaseAction;

/**
 * 
 * 服务相对象相关bean
 * 
 * @author 张苏磊
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ServiceItemOther extends BaseAction<ServiceItem> {

	@ManagedProperty("#{serviceItemFacade}")
	private ServiceItemFacade serviceItemFacade;

	private List<ServiceItem> serviceItemList;

	public List<ServiceItem> getServiceItemList() {
		return serviceItemList;
	}

	public void setServiceItemFacade(ServiceItemFacade serviceItemFacade) {
		this.serviceItemFacade = serviceItemFacade;
	}

	/**
	 * 根据辅导的修改取班级信息
	 * 
	 * @param e
	 */
	public void changeStageID(AjaxBehaviorEvent e) {
		Integer stageID = (Integer) ((UISelectOne) e.getSource()).getValue();
		serviceItemList = serviceItemFacade.findList(stageID);
	}

}
