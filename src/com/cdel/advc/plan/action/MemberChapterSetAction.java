package com.cdel.advc.plan.action;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.cdel.advc.plan.domain.MemberChapterSet;
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
@ViewScoped
public class MemberChapterSetAction extends BaseAction<MemberChapterSet> {
	@ManagedProperty("#{memberChapterSetFacade}")
	private MemberChapterSetFacade memberChapterSetFacade;

	private List<MemberChapterSet> planChapters;
	private Integer planID;
	private Integer userID;
	private Short planType;
	private byte flag = -1;

	/**
	 * 显示批量删除页面
	 * 
	 * @param isStudy
	 *            =0已停止学习，1正在学习
	 * @param preCourses
	 *            :学习\预习课程的id
	 */
	public void showChangeChapters(Short isStudy, Short planType,
			Integer planID, Integer userID, String courseIDs) {
		if (planID == null) {
			this.addWarnMessage("info", "非法参数！");
			return;
		}
		this.planID = planID;
		this.userID = userID;
		this.planType = planType;
		this.flag = isStudy.byteValue();
		planChapters = this.memberChapterSetFacade.findChaptersForChange(
				isStudy, planID, userID, courseIDs);
		this.updateComponent("deleteForm:deleteDialog");
		this.executeScript("deleteDialog.show()");
	}

	public List<MemberChapterSet> getPlanChapters() {
		return planChapters;
	}

	public void setPlanChapters(List<MemberChapterSet> planChapters) {
		this.planChapters = planChapters;
	}

	public Integer getPlanID() {
		return planID;
	}

	public Integer getUserID() {
		return userID;
	}

	public Short getPlanType() {
		return planType;
	}

	public byte getFlag() {
		return flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

	public void setMemberChapterSetFacade(
			MemberChapterSetFacade memberChapterSetFacade) {
		this.memberChapterSetFacade = memberChapterSetFacade;
	}

}
