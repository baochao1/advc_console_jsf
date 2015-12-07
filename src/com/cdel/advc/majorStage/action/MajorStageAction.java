package com.cdel.advc.majorStage.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.cdel.advc.majorStage.domain.MajorStage;
import com.cdel.advc.majorStage.domain.StageItem;
import com.cdel.advc.majorStage.facade.MajorStageFacade;
import com.cdel.advc.stage.domain.Stage;
import com.cdel.advc.stage.facade.StageFacade;
import com.cdel.util.BaseAction;

/**
 * 学习阶段action
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MajorStageAction extends BaseAction<MajorStage> implements
		Serializable {

	@ManagedProperty(value = "#{majorStageFacade}")
	private MajorStageFacade majorStageFacade;
	@ManagedProperty(value = "#{stageFacade}")
	private StageFacade stageFacade;

	private List<StageItem> stageList = new ArrayList<StageItem>();// 所有的阶段
	private Integer majorID;

	/**
	 * 获取辅导关联的阶段，用于辅导管理
	 * 
	 * @param majorID
	 */
	public void showStageDialog(Integer majorID) {
		this.majorID = majorID;
		List<Stage> stageListInner = stageFacade.findList(new Stage());
		if (stageListInner != null && stageListInner.size() > 0) {
			stageList = majorStageFacade.getServiceItemModel(majorID,
					stageListInner);
		}
	}

	public void setStageFacade(StageFacade stageFacade) {
		this.stageFacade = stageFacade;
	}

	public void setMajorStageFacade(MajorStageFacade majorStageFacade) {
		this.majorStageFacade = majorStageFacade;
	}

	public List<StageItem> getStageList() {
		return stageList;
	}

	public void setStageList(List<StageItem> stageList) {
		this.stageList = stageList;
	}

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}

}
