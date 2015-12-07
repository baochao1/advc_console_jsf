package com.cdel.advc.plan.action;

import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UISelectOne;
import javax.faces.event.AjaxBehaviorEvent;
import com.cdel.advc.plan.domain.MemberChapter;
import com.cdel.advc.plan.facade.MemberChapterFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;

/**
 * 学习计划,单个章节学习要求
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
public class MemberChapterReqAction extends BaseAction<MemberChapter> {

	@ManagedProperty("#{memberChapterFacade}")
	private MemberChapterFacade memberChapterFacade;

	/**
	 * 更新计划项章节状态
	 * 
	 * @throws Exception
	 */
	public void updateFinishStatus(AjaxBehaviorEvent e) {

		UISelectOne currentSelect = (UISelectOne) e.getSource();
		Short finishStatus = (Short) currentSelect.getValue();
		Integer chapterID = (Integer) currentSelect.getAttributes().get(
				"chapterID");
		Integer planID = (Integer) currentSelect.getAttributes().get("planID");
		Integer userID = (Integer) currentSelect.getAttributes().get("userID");

		if (finishStatus != null && chapterID != null && planID != null
				&& finishStatus != null) {
			try {
				this.memberChapterFacade.updateFinishStatus(chapterID, planID,
						userID, finishStatus);
			} catch (Exception ex) {
				ex.printStackTrace();
				this.addErrorMessage("info", FAILINFO);
			}
			this.addInfoMessage("info", SUCESSINFO);
		} else {
			this.addWarnMessage("info", "非法参数");
		}
	}

	/**
	 * 课程章节计完成状态值
	 */
	public Map<Short, String> getPlanFinishStatuss() {
		return Contants.planChapterFinishStatus;
	};

	public void setMemberChapterFacade(MemberChapterFacade memberChapterFacade) {
		this.memberChapterFacade = memberChapterFacade;
	}

}
