package com.cdel.advc.course.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.primefaces.event.CellEditEvent;
import com.cdel.advc.course.domain.Course;
import com.cdel.advc.course.domain.TempChapter;
import com.cdel.advc.course.facade.CourseFacade;
import com.cdel.advc.course.facade.TempChapterFacade;
import com.cdel.advc.plan.facade.StudyPlanFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;
import com.cdel.util.DateUtil;

/**
 * 
 * 计划项Bean
 * 
 * @author zhangsulei
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
public class TempChapterReqAction extends BaseAction<Course> implements
		Serializable {
	@ManagedProperty(value = "#{courseFacade}")
	private CourseFacade courseFacade;
	@ManagedProperty(value = "#{tempChapterFacade}")
	private TempChapterFacade tempChapterFacade;
	@ManagedProperty(value = "#{studyPlanFacade}")
	private StudyPlanFacade studyPlanFacade;

	private byte submitSuccess = 0;// 添加是否成功
	private Integer courseID;// 源课程ID
	private Integer copyCourseID;// 复制的课程ID
	private TempChapter tempChapter = new TempChapter();// 计划对象
	private Short modifyPlan = 0;// 是否更新学习计划
	private List<TempChapter> selTempChapters;// 选中的列

	/**
	 * 打开复制计划项页面
	 */
	public void copy(Integer courseID, Integer majorID) {
		TempChapterAction ta = (TempChapterAction) this
				.getViewAction("tempChapterAction");
		// 为了实现跨辅导复制学习计划项
		//	ta.setCourseList(courseFacade.findList(majorID));
		this.courseID = courseID;
	}

	/**
	 * 复制计划项
	 * 
	 * @param courseID
	 */
	public void copySubmit() {
		try {
			if (copyCourseID.intValue() == courseID) {
				this.addInfoMessage("info", "不需要自己复制自己！");
				return;
			}

			Integer plancnt = tempChapterFacade.getCountChapter(courseID);

			tempChapterFacade.doCopyChapter(courseID,
					tempChapterFacade.getTempChaptersByCourseID(copyCourseID));
			submitSuccess = 1;

			if (plancnt > 0) {
				logger.warn("复制课程代码："
						+ copyCourseID
						+ " 操作管理员："
						+ this.getCurrentUserID()
						+ " 操作日期："
						+ DateUtil.getNowDateString(new Date(),
								"yyyy-MM-dd HH:mm:ss"));
				this.addCallbackParam("warnmsg", "已经生成学习计划项，请重新生成学习计划！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);

	}

	/**
	 * 打开开通状态页面
	 */
	public void changeStatus(Integer chapterID) {
		tempChapter = tempChapterFacade.getTempChapterMini(chapterID);
	}

	/**
	 * 修改开通状态
	 */
	public void changeStatusSubmit() {
		try {
			tempChapter.setOpenStatus(new Short("1"));
			tempChapter.setOpenTime(new Date());
			tempChapter.setOpener(this.getCurrentUserID());
			tempChapterFacade.update(tempChapter);
			if (modifyPlan == 1) {
				studyPlanFacade.updateStudyPlanStatusByChapter(
						tempChapter.getChapterID(), 4,
						tempChapter.getOpenStatus());
			}
			submitSuccess = 1;
		} catch (Exception e) {
			e.printStackTrace();
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 批量修改开通状态
	 */
	public void changeStatusSubmitBatch() {
		try {
			List<TempChapter> updTempChapters = new ArrayList<TempChapter>();
			if (selTempChapters != null && selTempChapters.size() > 0) {
				for (int i = 0; i < selTempChapters.size(); i++) {
					TempChapter tc = selTempChapters.get(i);
					if (tc.getOpenStatus() == 0) {
						tc.setOpenStatus(new Short("1"));
						tc.setOpenTime(new Date());
						tc.setOpener(this.getCurrentUserID());
						updTempChapters.add(tc);
					}
				}
				tempChapterFacade.update(updTempChapters);
				submitSuccess = 1;
				TempChapterAction tempChapterAction = (TempChapterAction) this
						.getViewAction("tempChapterAction");
				tempChapterAction.search();
			} else {
				this.addWarnMessage("info", "请至少选中一条记录！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 查看
	 */
	public void show(Integer chapterID) {
		tempChapter = tempChapterFacade.findByID(chapterID);
	}

	/**
	 * 更新排序
	 */
	public void updateSeqSubmit(CellEditEvent event) {
		tempChapter = (TempChapter) getEditRow(event);
		try {
			if (tempChapterFacade.checkSeq(tempChapter)) {
				tempChapterFacade.update(tempChapter);
				this.addMessage("info", SUCESSINFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
		}
	}

	public Map<Short, String> getOpenStatus() {
		return Contants.openStatus;
	}

	public Map<Short, String> getYesorno() {
		return Contants.yesorno;
	}

	public void setTempChapterFacade(TempChapterFacade tempChapterFacade) {
		this.tempChapterFacade = tempChapterFacade;
	}

	public TempChapter getTempChapter() {
		return tempChapter;
	}

	public void setTempChapter(TempChapter tempChapter) {
		this.tempChapter = tempChapter;
	}

	public Integer getCourseID() {
		return courseID;
	}

	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}

	public void setCourseFacade(CourseFacade courseFacade) {
		this.courseFacade = courseFacade;
	}

	public Short getModifyPlan() {
		return modifyPlan;
	}

	public void setModifyPlan(Short modifyPlan) {
		this.modifyPlan = modifyPlan;
	}

	public void setStudyPlanFacade(StudyPlanFacade studyPlanFacade) {
		this.studyPlanFacade = studyPlanFacade;
	}

	public List<TempChapter> getSelTempChapters() {
		return selTempChapters;
	}

	public void setSelTempChapters(List<TempChapter> selTempChapters) {
		this.selTempChapters = selTempChapters;
	}

	public Integer getCopyCourseID() {
		return copyCourseID;
	}

	public void setCopyCourseID(Integer copyCourseID) {
		this.copyCourseID = copyCourseID;
	}

}