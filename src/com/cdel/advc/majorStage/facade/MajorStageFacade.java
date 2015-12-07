package com.cdel.advc.majorStage.facade;

import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdel.advc.majorStage.domain.MajorStage;
import com.cdel.advc.majorStage.domain.StageItem;
import com.cdel.advc.majorStage.domain.StageServiceItem;
import com.cdel.advc.serviceItem.domain.ServiceItem;
import com.cdel.advc.serviceItem.facade.ServiceItemFacade;
import com.cdel.advc.stage.domain.Stage;
import com.cdel.util.BaseFacadeImpl;

/**
 * 学习阶段和辅导关系业务层
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@Service
public class MajorStageFacade extends BaseFacadeImpl<MajorStage, Integer> {

	@Autowired
	private ServiceItemFacade serviceItemFacade;
	@Autowired
	private StageServiceItemFacade stageServiceItemFacade;

	/**
	 * 获取辅导关联的服务项
	 * 
	 * @param stageList
	 * @return
	 */
	public List<StageItem> getServiceItemModel(Integer majorID,
			List<Stage> stageList) {
		List<StageItem> result = new ArrayList<StageItem>();
		DualListModel<ServiceItem> serviceItemList = null;
		StageServiceItem sItem = null;
		for (Stage stage : stageList) {
			StageItem stageItem = new StageItem();
			stageItem.setStage(stage);

			serviceItemList = new DualListModel<ServiceItem>();
			Integer stageID = stage.getStageID();
			// 初始化的serverItem
			List<ServiceItem> sList = serviceItemFacade.findList(stageID);
			sItem = new StageServiceItem();
			sItem.setMajorID(majorID);
			sItem.setStageID(stageID);
			// 关联的serverItem
			List<StageServiceItem> ssiList = stageServiceItemFacade
					.findList(sItem);
			List<ServiceItem> targetList = new ArrayList<ServiceItem>();
			if (ssiList != null && ssiList.size() > 0) {
				for (StageServiceItem ssi : ssiList) {
					ServiceItem item = new ServiceItem();
					item.setServiceID(ssi.getServiceID());
					if (sList.contains(item)) {
						sList.remove(item);
						item.setServiceName(ssi.getServiceName());
						targetList.add(item);
					}
				}
			}
			serviceItemList.setSource(sList);
			serviceItemList.setTarget(targetList);

			stageItem.setServiceItemModel(serviceItemList);
			result.add(stageItem);
		}
		return result;
	}

	/**
	 * 获取全部有效阶段
	 * 
	 * @param majorID
	 * @return
	 */
	public List<MajorStage> getAllMajorStageList(Integer majorID) {
		return this.baseDao.findList(majorID, "getAllMajorStageList");
	}

}
