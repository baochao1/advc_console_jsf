package com.cdel.advc.majorStage.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.cdel.advc.majorStage.domain.MajorStage;
import com.cdel.advc.majorStage.domain.StageItem;
import com.cdel.advc.majorStage.domain.StageServiceItem;
import com.cdel.advc.majorStage.facade.MajorStageFacade;
import com.cdel.advc.majorStage.facade.StageServiceItemFacade;
import com.cdel.advc.serviceItem.domain.ServiceItem;
import com.cdel.advc.stage.domain.Stage;
import com.cdel.util.BaseAction;

/**
 * 学习阶段action
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
public class MajorStageReqAction extends BaseAction<MajorStage> implements
		Serializable {

	@ManagedProperty(value = "#{majorStageFacade}")
	private MajorStageFacade majorStageFacade;
	@ManagedProperty(value = "#{stageServiceItemFacade}")
	private StageServiceItemFacade stageServiceItemFacade;

	private byte submitSuccess = 0;// 添加是否成功

	/**
	 * 保存辅导和服务项的关系
	 * 
	 * @param majorID
	 */
	public void updateSubmit(Integer majorID) {
		MajorStageAction ma = (MajorStageAction) this
				.getViewAction("majorStageAction");
		List<StageItem> stageItemList = ma.getStageList();
		try {
			if (stageItemList != null && stageItemList.size() > 0) {
				List<MajorStage> majorStageList = new ArrayList<MajorStage>();
				List<StageServiceItem> stageServiceItemList = new ArrayList<StageServiceItem>();
				for (StageItem stageItem : stageItemList) {
					List<ServiceItem> targetItem = stageItem
							.getServiceItemModel().getTarget();
					Stage stage = stageItem.getStage();
					boolean f = false;
					if (targetItem != null && targetItem.size() > 0) {
						StageServiceItem item = null;
						for (ServiceItem serviceItem : targetItem) {
							f = true;
							item = new StageServiceItem();
							item.setMajorID(majorID);
							item.setStageID(stage.getStageID());
							item.setServiceID(serviceItem.getServiceID());
							stageServiceItemList.add(item);
						}
					}
					if (f) {
						MajorStage majorStage = new MajorStage();
						majorStage.setMajorID(majorID);
						majorStage.setStageID(stage.getStageID());
						majorStageList.add(majorStage);
					}
				}

				// 删除以前的数据
				majorStageFacade.delete(majorID);
				stageServiceItemFacade.delete(majorID);
				majorStageFacade.addList(majorStageList);
				stageServiceItemFacade.addList(stageServiceItemList);
				submitSuccess = 1;
			}
		} catch (Exception e) {
			submitSuccess = -1;
			e.printStackTrace();
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public void setMajorStageFacade(MajorStageFacade majorStageFacade) {
		this.majorStageFacade = majorStageFacade;
	}

	public void setStageServiceItemFacade(
			StageServiceItemFacade stageServiceItemFacade) {
		this.stageServiceItemFacade = stageServiceItemFacade;
	}

}
