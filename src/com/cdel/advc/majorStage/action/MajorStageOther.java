package com.cdel.advc.majorStage.action;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.cdel.advc.majorStage.domain.MajorStage;
import com.cdel.advc.majorStage.facade.MajorStageFacade;
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
public class MajorStageOther extends BaseAction<MajorStage> implements
		Serializable {

	@ManagedProperty(value = "#{majorStageFacade}")
	private MajorStageFacade majorStageFacade;

	private List<MajorStage> majorStageList;

	/**
	 * 获取辅导关联的阶段
	 * 
	 * @param majorID
	 * @return
	 */
	public void selectMajorStageByMajor(Integer majorID) {
		majorStageList = majorStageFacade.findList(majorID);
	}

	/**
	 * 获取辅导关联的阶段
	 * 
	 * @param majorID
	 * @return
	 */
	public void selectMajorStageByMajor2(Integer majorID) {
		majorStageList = majorStageFacade.getAllMajorStageList(majorID);
	}

	/**
	 * 获取辅导关联的阶段
	 * 
	 * @return
	 */
	public void selectMajorStageByMajor2() {
		Integer majorID = this.getIntegerRequestParameterByMap("majorID");
		// 需要更新的页面元素
		String updateName = this.getRequestParameterByMap("updateName");
		majorStageList = majorStageFacade.getAllMajorStageList(majorID);
		this.updateComponent(updateName);
	}

	public void setMajorStageFacade(MajorStageFacade majorStageFacade) {
		this.majorStageFacade = majorStageFacade;
	}

	public List<MajorStage> getMajorStageList() {
		return majorStageList;
	}

	public void setMajorStageList(List<MajorStage> majorStageList) {
		this.majorStageList = majorStageList;
	}

}
