package com.cdel.advc.major.action;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.TabChangeEvent;
import com.cdel.advc.major.domain.Major;
import com.cdel.advc.major.domain.PhaseSet;
import com.cdel.advc.major.facade.PhaseSetFacade;
import com.cdel.advc.majorStage.domain.MajorStage;
import com.cdel.advc.majorStage.facade.MajorStageFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.ExceptionUtil;

/**
 * 舍弃阶段
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PhaseSetAction extends BaseAction<Major> implements Serializable {
	@ManagedProperty(value = "#{phaseSetFacade}")
	private PhaseSetFacade phaseSetFacade;
	@ManagedProperty(value = "#{majorStageFacade}")
	private MajorStageFacade majorStageFacade;

	private List<PhaseSet> phaseSetList1;// 实验
	private List<PhaseSet> phaseSetList2;// 精品
	private List<PhaseSet> phaseSetList3;// 混合
	private Integer majorID;// 当前辅导ID
	private boolean show = false;
	private byte submitSuccess = 0;// 修改是否成功
	private List<MajorStage> majorStageList;// 辅导关联的阶段

	/**
	 * 打开页面加载舍弃阶段， classType：1实验班,2精品班,3混合班
	 * 
	 * @param majorID
	 */
	public void showPhaseSetDialog(Integer majorID, Short classType) {
		show = true;
		this.majorID = majorID;
		PhaseSet phaseSet = new PhaseSet();
		phaseSet.setMajorID(majorID);
		phaseSet.setClassType(classType);
		phaseSetList1 = phaseSetFacade.findList(phaseSet);
		phaseSet.setClassType((short) 2);
		phaseSetList2 = phaseSetFacade.findList(phaseSet);
		phaseSet.setClassType((short) 3);
		phaseSetList3 = phaseSetFacade.findList(phaseSet);
		majorStageList = majorStageFacade.findList(majorID);
	}

	/**
	 * 切换页签
	 * 
	 * @param event
	 */
	public void onChangePhaseSet(TabChangeEvent event) {
		// 不需要事件了
	}

	/**
	 * 删除行
	 * 
	 * @param row
	 */
	public void remove(int index, Short classType) {
		if (classType == 1) {
			phaseSetList1.remove(index);
		} else if (classType == 2) {
			phaseSetList2.remove(index);
		} else {
			phaseSetList3.remove(index);
		}
	}

	/**
	 * 添加行
	 * 
	 * @param classType
	 */
	public void add(Short classType) {
		PhaseSet phaseSet = new PhaseSet();
		phaseSet.setMajorID(majorID);
		phaseSet.setClassType(classType);
		phaseSet.setPhaseNo(null);
		phaseSet.setSequence(null);
		if (classType == 1) {
			phaseSetList1.add(phaseSet);
		} else if (classType == 2) {
			phaseSetList2.add(phaseSet);
		} else {
			phaseSetList3.add(phaseSet);
		}
	}

	/**
	 * 提交修改
	 * 
	 * @param classType
	 */
	public void addSubmit() {
		List<PhaseSet> phaseSetList = null;
		// 先验证数据有效性
		boolean f = false;
		for (short classType = 1; classType <= 3; classType++) {
			if (classType == 1) {
				phaseSetList = phaseSetList1;
			} else if (classType == 2) {
				phaseSetList = phaseSetList2;
			} else {
				phaseSetList = phaseSetList3;
			}
			if (phaseSetList != null && phaseSetList.size() > 0
					&& phaseSetFacade.checkPhaseSet(phaseSetList)) {

			} else {
				f = true;
				break;
			}
		}
		if (f) {
			this.addErrorMessage("msg","所有班级都要设置舍弃阶段！");
			return;
		}

		for (short classType = 1; classType <= 3; classType++) {
			if (classType == 1) {
				phaseSetList = phaseSetList1;
			} else if (classType == 2) {
				phaseSetList = phaseSetList2;
			} else {
				phaseSetList = phaseSetList3;
			}
			try {
				phaseSetFacade.updatePhaseSetList(majorID, classType,
						phaseSetList);
				submitSuccess = 1;
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(ExceptionUtil.getEMsg(e));
				submitSuccess = -1;
			}
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public List<PhaseSet> getPhaseSetList1() {
		return phaseSetList1;
	}

	public void setPhaseSetList1(List<PhaseSet> phaseSetList1) {
		this.phaseSetList1 = phaseSetList1;
	}

	public List<PhaseSet> getPhaseSetList2() {
		return phaseSetList2;
	}

	public void setPhaseSetList2(List<PhaseSet> phaseSetList2) {
		this.phaseSetList2 = phaseSetList2;
	}

	public List<PhaseSet> getPhaseSetList3() {
		return phaseSetList3;
	}

	public void setPhaseSetList3(List<PhaseSet> phaseSetList3) {
		this.phaseSetList3 = phaseSetList3;
	}

	public void setPhaseSetFacade(PhaseSetFacade phaseSetFacade) {
		this.phaseSetFacade = phaseSetFacade;
	}

	public List<MajorStage> getMajorStageList() {
		return majorStageList;
	}

	public void setMajorStageList(List<MajorStage> majorStageList) {
		this.majorStageList = majorStageList;
	}

	public void setMajorStageFacade(MajorStageFacade majorStageFacade) {
		this.majorStageFacade = majorStageFacade;
	}

}
