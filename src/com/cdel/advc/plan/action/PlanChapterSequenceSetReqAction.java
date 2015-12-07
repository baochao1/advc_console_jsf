package com.cdel.advc.plan.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.plan.domain.PlanChapterSequenceSet;
import com.cdel.advc.plan.facade.PlanChapterSequenceSetFacade;
import com.cdel.util.BaseAction;

/**
 * 学习计划章节排序action
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
public class PlanChapterSequenceSetReqAction extends
		BaseAction<PlanChapterSequenceSet> implements Serializable {

	@ManagedProperty(value = "#{planChapterSequenceSetFacade}")
	private PlanChapterSequenceSetFacade planChapterSequenceSetFacade;

	/** 学员 计划项排序 列表 */
	private List<PlanChapterSequenceSet> sortChapters = new ArrayList<PlanChapterSequenceSet>();
	private Integer planID;
	private byte submitSuccess = 0;// 添加是否成功

	/**
	 * 显示排序列表
	 * 
	 * @param planID
	 * @param planType
	 */
	public void showSortChapters(Integer planID) {

		if (planID == null) {
			this.addWarnMessage("info", "非法参数！");
			return;
		}
		this.planID = planID;
		this.sortChapters = this.planChapterSequenceSetFacade
				.findChaptersForSort(planID);

		this.updateComponent("updateBatchSortForm:updateBatchSortDialog");
		this.executeScript("updateBatchSortDialog.show()");

	}

	/**
	 * 重新排序
	 * 
	 * @throws Exception
	 */
	public void updateChapterSequences(Integer planID) {
		try {
			this.planChapterSequenceSetFacade.updateChapterSequences(planID,
					this.getCurrentUserID(), this.getCurrentUserName(),
					this.sortChapters);
			submitSuccess = 1;
		} catch (Exception e) {
			e.printStackTrace();
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public void setPlanChapterSequenceSetFacade(
			PlanChapterSequenceSetFacade planChapterSequenceSetFacade) {
		this.planChapterSequenceSetFacade = planChapterSequenceSetFacade;
	}

	public List<PlanChapterSequenceSet> getSortChapters() {
		return sortChapters;
	}

	public void setSortChapters(List<PlanChapterSequenceSet> sortChapters) {
		this.sortChapters = sortChapters;
	}

	public Integer getPlanID() {
		return planID;
	}

	public void setPlanID(Integer planID) {
		this.planID = planID;
	}

}
