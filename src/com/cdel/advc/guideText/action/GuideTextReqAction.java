package com.cdel.advc.guideText.action;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.guideText.domain.GuideText;
import com.cdel.advc.guideText.facade.GuideTextFacade;
import com.cdel.advc.major.domain.Major;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;
import com.cdel.util.ExceptionUtil;

/**
 * 阶段引导语action
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
public class GuideTextReqAction extends BaseAction<Major> implements
		Serializable {

	@ManagedProperty(value = "#{guideTextFacade}")
	private GuideTextFacade guideTextFacade;

	private GuideText updateGuideText = new GuideText();
	private byte submitSuccess = 0;// 添加是否成功
	private byte flag = 0;

	/**
	 * 更新状态
	 * 
	 * @throws Exception
	 */
	public void changeStatus(Integer guideID, Short status, Integer stageID,
			Short type) throws Exception {
		GuideTextAction ga = (GuideTextAction) this
				.getViewAction("guideTextAction");
		if (guideID != null && status != null) {
			this.updateGuideText = new GuideText();
			this.updateGuideText.setGuideID(guideID);
			if (status == 1) {
				this.updateGuideText.setStatus((short) 0);
			} else {
				this.updateGuideText.setStatus((short) 1);
			}
			updateGuideText.setMajorID(ga.getMajorID());
			updateGuideText.setStageID(stageID);
			updateGuideText.setType(type);
			if (status == 0
					&& guideTextFacade.hasSameGuideText(updateGuideText) > 0) {
				this.addWarnMessage("info", "已经添加过该辅导、阶段、类型的引导语！");
			} else {
				this.guideTextFacade.update(updateGuideText);
				ga.search4U();
				this.addMessage("info", "设置成功！");
			}
		}
	}

	/**
	 * 查看
	 */
	public void show(Integer guideID) {
		updateGuideText = guideTextFacade.findByID(guideID);
	}

	/**
	 * 修改
	 */
	public void update(byte flag, Integer guideID) {
		updateGuideText = guideTextFacade.findByID(guideID);
		this.flag = flag;
	}

	/**
	 * 添加
	 */
	public void add(byte flag) {
		this.flag = flag;
	}

	/**
	 * 提交修改
	 */
	public void updateSubmit() {
		GuideTextAction ga = (GuideTextAction) this
				.getViewAction("guideTextAction");
		updateGuideText.setMajorID(ga.getMajorID());
		if (guideTextFacade.checkGuideText(flag, updateGuideText)) {
			try {
				if (flag == 0) {
					updateGuideText.setCreateTime(new Date());
					updateGuideText.setStatus((short) 1);
					guideTextFacade.add(updateGuideText);
					ga.search();
				} else {
					guideTextFacade.update(updateGuideText);
					ga.search4U();
				}
				submitSuccess = 1;
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(ExceptionUtil.getEMsg(e));
				submitSuccess = -1;
			}
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public Map<Short, String> getStatus() {
		return Contants.status;
	}

	public Map<Short, String> getTypes() {
		return Contants.guideTextType;
	}

	public void setGuideTextFacade(GuideTextFacade guideTextFacade) {
		this.guideTextFacade = guideTextFacade;
	}

	public GuideText getUpdateGuideText() {
		return updateGuideText;
	}

	public void setUpdateGuideText(GuideText updateGuideText) {
		this.updateGuideText = updateGuideText;
	}

	public byte getFlag() {
		return flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

}
