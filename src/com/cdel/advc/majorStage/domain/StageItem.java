package com.cdel.advc.majorStage.domain;

import java.io.Serializable;
import org.primefaces.model.DualListModel;
import com.cdel.advc.serviceItem.domain.ServiceItem;
import com.cdel.advc.stage.domain.Stage;

/**
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
public class StageItem implements Serializable {

	private Stage stage;
	private DualListModel<ServiceItem> serviceItemModel;

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public DualListModel<ServiceItem> getServiceItemModel() {
		return serviceItemModel;
	}

	public void setServiceItemModel(DualListModel<ServiceItem> serviceItemModel) {
		this.serviceItemModel = serviceItemModel;
	}

}
