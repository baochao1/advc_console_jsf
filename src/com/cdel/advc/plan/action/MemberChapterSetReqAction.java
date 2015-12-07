package com.cdel.advc.plan.action;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.plan.domain.MemberChapterSet;
import com.cdel.advc.plan.domain.StudyPlan;
import com.cdel.advc.plan.facade.MemberChapterSetFacade;
import com.cdel.util.BaseAction;

/**
 * 学习计划,单个章节学习要求
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
public class MemberChapterSetReqAction extends BaseAction<MemberChapterSet> {
	@ManagedProperty("#{memberChapterSetFacade}")
	private MemberChapterSetFacade memberChapterSetFacade;

	/** 对学员计划项的设置 */
	private MemberChapterSet chapterSet = new MemberChapterSet();
	private byte submitSuccess = 0;// 修改是否成功
	private Integer type;// 学员报课数
	/** 选中的计划章节 */
	private List<MemberChapterSet> selectedChapters;

	/**
	 * 显示时间要求等设置
	 */
	public void showChapterSet(Integer planID, Integer type, Integer chapterID,
			Integer userID, Double time) {
		this.type = type;
		if (chapterID == null || userID == null) {
			this.addWarnMessage("非法参数！");
			return;
		}

		this.chapterSet = this.memberChapterSetFacade
				.getMCSByChapterIDAndUserID(chapterID, userID);

		if (this.chapterSet == null) {
			this.chapterSet = new MemberChapterSet();
			this.chapterSet.setChapterID(chapterID);
			this.chapterSet.setUserID(userID);
			this.chapterSet.setSuggestStyTimeHour(time);
		}
		chapterSet.setPlanID(planID);
		this.updateComponent("updateChapterSetFrom:updateChapterSetDialog");
		this.executeScript("updateChapterSetDialog.show()");

	}

	/**
	 * 更新时间要求设置
	 */
	public void updateChapterSet() {
		if (this.chapterSet != null) {
			chapterSet.setType(type.shortValue());
			chapterSet.setCreator(this.getCurrentUserID());
			chapterSet.setCreatorName(this.getCurrentRealName());
			if (memberChapterSetFacade.checkHour(chapterSet)) {
				try {
					this.memberChapterSetFacade.addMemberChapterSet((short) 10,
							chapterSet);
					PlanChapterAction pa = (PlanChapterAction) this
							.getViewAction("planChapterAction");
					StudyPlan plan = pa.getPlan();
					plan.setPlanStatus((short) 10);
					pa.setReGeneratePlan(true);
					submitSuccess = 1;
				} catch (Exception e) {
					e.printStackTrace();
					submitSuccess = -1;
				}
			}
			this.addCallbackParam("result", submitSuccess);
		}
	}

	/**
	 * 批量停止学习章节
	 * 
	 * @throws Exception
	 * 
	 */
	public void updateStudyStatuss(Short status, Short planType,
			Integer planID, Integer userID) throws Exception {
		if (this.selectedChapters == null || this.selectedChapters.size() <= 0) {
			this.addWarnMessage("请选择要删除的数据！");
			return;
		}
		try {
			if (status == 0) {
				status = 1;
			} else {
				status = 0;
			}
			memberChapterSetFacade.updateStudyStatus(selectedChapters, userID,
					status, planID, this.getCurrentUserID(),
					this.getCurrentRealName(), planType);
			submitSuccess = 1;
		} catch (Exception e) {
			e.printStackTrace();
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public void setMemberChapterSetFacade(
			MemberChapterSetFacade memberChapterSetFacade) {
		this.memberChapterSetFacade = memberChapterSetFacade;
	}

	public MemberChapterSet getChapterSet() {
		return chapterSet;
	}

	public void setChapterSet(MemberChapterSet chapterSet) {
		this.chapterSet = chapterSet;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<MemberChapterSet> getSelectedChapters() {
		return selectedChapters;
	}

	public void setSelectedChapters(List<MemberChapterSet> selectedChapters) {
		this.selectedChapters = selectedChapters;
	}

}
