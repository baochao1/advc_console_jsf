package com.cdel.advc.major.action;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.major.domain.GuideLanguage;
import com.cdel.advc.major.domain.Major;
import com.cdel.advc.major.domain.PromptTime;
import com.cdel.advc.major.facade.GuideLanguageFacade;
import com.cdel.advc.major.facade.PromptTimeFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;
import com.cdel.util.ExceptionUtil;

/**
 * 服务项引导语设置 action
 * 
 * @author xxg
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
public class GuideLangueReqAction extends BaseAction<Major> implements
		Serializable {

	@ManagedProperty(value = "#{guideLanguageFacade}")
	private GuideLanguageFacade guideLanguageFacade;
	@ManagedProperty(value = "#{promptTimeFacade}")
	private PromptTimeFacade promptTimeFacade;

	private GuideLanguage guideLanguage = new GuideLanguage();;// 新增或 修改的 服务项引导语
	private PromptTime promptTime = new PromptTime();// 新增修改的对象
	private byte submitSuccess = 0;// 修改是否成功
	private String isShow; // 用来判断 页面中 开始时间和结束时间组件是否显示

	/**
	 * 修改状态
	 */
	public void changeStatus(Integer guideLanguageID, Integer stageID,
			Integer serviceID, Short promptKey, Short status) {
		guideLanguage.setGuideLanguageID(guideLanguageID);
		Short newStatus;
		if (status == 1) {
			newStatus = 0;
		} else {
			newStatus = 1;
			GuideLangueAction ga = (GuideLangueAction) this
					.getViewAction("guideLangueAction");
			guideLanguage.setMajorID(ga.getMajorID());
			guideLanguage.setStageID(stageID);
			guideLanguage.setServiceID(serviceID);
			guideLanguage.setPromptKey(promptKey);
			if (guideLanguageFacade.checkIsNotGuideLanguage(guideLanguage)) {
				this.addErrorMessage("info", "该服务项下已经存在该提示类型的有效记录了！");
				return;
			}
		}
		guideLanguage.setStatus(newStatus);
		try {
			guideLanguageFacade.update(guideLanguage);
			this.addInfoMessage("info", SUCESSINFO);
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
		}
	}

	/**
	 * 删除 操作
	 */
	public void delete(Integer guideLanguageID) {
		try {
			// 先删除主表
			guideLanguage.setGuideLanguageID(guideLanguageID);
			guideLanguageFacade.delete(guideLanguage);
			// 删除 时间提示表
			promptTime.setGuideLanguageID(guideLanguageID);
			promptTimeFacade.delete(promptTime);
			this.addInfoMessage("info", SUCESSINFO);
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
		}
	}

	/**
	 * 打开编辑 页面 初始化编辑数据
	 */
	public void update(Integer guideLanguageID) {
		guideLanguage = guideLanguageFacade.findByID(guideLanguageID);
	}

	/**
	 * 添加或者 修改 服务项引导语 提交数据
	 */
	public void updateSubmit(byte flag) {
		GuideLangueAction ga = (GuideLangueAction) this
				.getViewAction("guideLangueAction");
		guideLanguage.setMajorID(ga.getMajorID());
		if (guideLanguageFacade.checkGuideLanguage(flag, guideLanguage)) {
			try {
				if (flag == 0) { // 新增操作
					guideLanguage.setCreateID(this.getCurrentUserID());
					guideLanguage.setStatus((short) 1);
					guideLanguage.setCreateTime(new Date());
					guideLanguageFacade.add(guideLanguage);
					if (guideLanguage.getPromptKey() >= 4
							&& guideLanguage.getPromptKey() <= 6) {
						promptTime.setGuideLanguageID(guideLanguage
								.getGuideLanguageID());
						promptTime.setBeginTime(guideLanguage.getBeginTime());
						promptTime.setEndTime(guideLanguage.getEndTime());
						promptTimeFacade.add(promptTime);
					}
					ga.search();
				} else {
					// 先更新 提示内容
					guideLanguageFacade.update(guideLanguage);
					// 在更新提示时间
					if (guideLanguage.getPromptKey() >= 4
							&& guideLanguage.getPromptKey() <= 6) {
						promptTime.setPromptID(guideLanguage.getPromptID());
						promptTime.setBeginTime(guideLanguage.getBeginTime());
						promptTime.setEndTime(guideLanguage.getEndTime());
						promptTimeFacade.update(promptTime);
					}
					ga.search4U();
				}
				submitSuccess = 1;
			} catch (Exception e) {
				e.printStackTrace();
				submitSuccess = -1;
				logger.error("guideLanguage=" + guideLanguage);
				logger.error(ExceptionUtil.getEMsg(e));
			}
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 得到有效无效下拉列表
	 * 
	 * @return
	 */
	public Map<Short, String> getStatus() {
		return Contants.status;
	}

	/**
	 * 得到提示类型集合
	 * 
	 * @return
	 */
	public Map<Short, String> getPromptTimeMap() {
		return Contants.promptKeyMap;
	}

	public void setGuideLanguageFacade(GuideLanguageFacade guideLanguageFacade) {
		this.guideLanguageFacade = guideLanguageFacade;
	}

	public GuideLanguage getGuideLanguage() {
		return guideLanguage;
	}

	public void setGuideLanguage(GuideLanguage guideLanguage) {
		this.guideLanguage = guideLanguage;
	}

	public void setPromptTimeFacade(PromptTimeFacade promptTimeFacade) {
		this.promptTimeFacade = promptTimeFacade;
	}

	public PromptTime getPromptTime() {
		return promptTime;
	}

	public void setPromptTime(PromptTime promptTime) {
		this.promptTime = promptTime;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

}
