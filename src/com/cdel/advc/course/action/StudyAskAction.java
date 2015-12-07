package com.cdel.advc.course.action;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.cdel.advc.course.domain.Course;
import com.cdel.advc.course.domain.StudyAsk;
import com.cdel.advc.course.facade.StudyAskFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.ExceptionUtil;
import com.cdel.util.StringUtil;

/**
 * 
 * 学习要求Bean
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class StudyAskAction extends BaseAction<Course> implements Serializable {
	@ManagedProperty(value = "#{studyAskFacade}")
	private StudyAskFacade studyAskFacade;

	private List<StudyAsk> studyAskList;
	private String url = "";// 要加载的小页面
	private byte flag = -1;// 添加/编辑状态,0添加,1编辑
	private StudyAsk addStudyAsk = new StudyAsk();// 添加对象
	private StudyAsk updateStudyAsk = new StudyAsk();// 修改对象
	private Integer chapterID;// 初始章节ID
	private String chapterName;// 初始章节名
	private Short targetType;// 复制目标type

	@PostConstruct
	public void initCourseAction() {
		url = "../grant/null.xhtml";
		chapterID = this.getIntegerParameter("chapterID");
		chapterName = this.getParameter("chapterName");
		addStudyAsk.setChapterID(chapterID);
		studyAskList = studyAskFacade.getStudyAskList(chapterID);
		if (studyAskList != null && studyAskList.size() > 0) {
			for (int i = 0; i < studyAskList.size(); i++) {
				if (studyAskList.get(i).getType() == 1) {
					addStudyAsk = studyAskFacade.splitContent(studyAskList
							.get(i));
					break;
				}
			}
		}
	}

	/**
	 * 查询
	 */
	public void search() {
		studyAskList = studyAskFacade.getStudyAskList(chapterID);
		this.updateComponent("searchForm:studyAskList");
	}

	/**
	 * 添加计划项
	 */
	public void addSubmit() {
		byte submitSuccess = 0;
		if (addStudyAsk.getSuggestStyTime4Hour() == null) {
			this.addWarnMessage("建议学习时间不能为空！");
			return;
		}
		String studyAsk1 = StringUtil.nullToString(addStudyAsk.getStudyAsk1());
		if (studyAsk1.equals(""))
			studyAsk1 = "无";
		String studyAsk2 = StringUtil.nullToString(addStudyAsk.getStudyAsk2());
		if (studyAsk2.equals(""))
			studyAsk2 = "无";
		String studyAsk3 = StringUtil.nullToString(addStudyAsk.getStudyAsk3());
		if (studyAsk3.equals(""))
			studyAsk3 = "无";
		String studyAsk4 = StringUtil.nullToString(addStudyAsk.getStudyAsk4());
		if (studyAsk4.equals(""))
			studyAsk4 = "无";
		String studyAsk = "";
		studyAsk = studyAsk1 + "{|||}" + studyAsk2 + "{|||}" + studyAsk3
				+ "{|||}" + studyAsk4;
		studyAsk = studyAsk.replaceAll("<p>", "");
		studyAsk = studyAsk.replaceAll("</p>", "");
		addStudyAsk.setContent(studyAsk);
		try {
			addStudyAsk.setSuggestStyTime(new Short(addStudyAsk
					.getSuggestStyTime4Hour() * 60 + ""));
			StudyAsk sa = studyAskFacade.getStudyAskListOne(addStudyAsk);
			if (sa != null) {
				studyAskFacade.update(addStudyAsk);
			} else {
				studyAskFacade.add(addStudyAsk);
			}
			submitSuccess = 1;
			search();
		} catch (java.lang.NumberFormatException e2) {
			e2.printStackTrace();
			submitSuccess = -1;
			this.addErrorMessage("数字太大！");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("addStudyAsk=" + addStudyAsk);
			logger.error(ExceptionUtil.getEMsg(e));
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 切换type
	 */
	public void changeType() {
		Short type = addStudyAsk.getType();
		addStudyAsk.setAskID(null);
		StudyAsk sa = studyAskFacade.getStudyAskListOne(addStudyAsk);
		if (sa != null) {
			addStudyAsk = studyAskFacade.splitContent(sa);
		} else {
			addStudyAsk = new StudyAsk();
			addStudyAsk.setChapterID(chapterID);
			addStudyAsk.setType(type);
		}
	}

	/**
	 * 删除
	 * 
	 * @param askID
	 */
	public void delete(Integer askID) {
		byte submitSuccess = 0;
		try {
			studyAskFacade.delete(askID);
			submitSuccess = 1;
			search();
		} catch (Exception e) {
			e.printStackTrace();
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 编辑
	 */
	public void update(Integer askID) {
		url = "load/studyAskUpdate.xhtml";
		flag = 1;
		updateStudyAsk = studyAskFacade.splitContent(studyAskFacade
				.findByID(askID));
	}

	/**
	 * 修改计划项
	 */
	public void updateSubmit() {
		byte submitSuccess = 0;
		if (updateStudyAsk.getSuggestStyTime4Hour() == null) {
			this.addWarnMessage("建议学习时间不能为空！");
			return;
		}
		String studyAsk1 = StringUtil.nullToString(updateStudyAsk
				.getStudyAsk1());
		if (studyAsk1.equals(""))
			studyAsk1 = "无";
		String studyAsk2 = StringUtil.nullToString(updateStudyAsk
				.getStudyAsk2());
		if (studyAsk2.equals(""))
			studyAsk2 = "无";
		String studyAsk3 = StringUtil.nullToString(updateStudyAsk
				.getStudyAsk3());
		if (studyAsk3.equals(""))
			studyAsk3 = "无";
		String studyAsk4 = StringUtil.nullToString(updateStudyAsk
				.getStudyAsk4());
		if (studyAsk4.equals(""))
			studyAsk4 = "无";
		String studyAsk = "";
		studyAsk = studyAsk1 + "{|||}" + studyAsk2 + "{|||}" + studyAsk3
				+ "{|||}" + studyAsk4;
		studyAsk = studyAsk.replaceAll("<p>", "");
		studyAsk = studyAsk.replaceAll("</p>", "");
		updateStudyAsk.setContent(studyAsk);
		updateStudyAsk.setSuggestStyTime(new Short(updateStudyAsk
				.getSuggestStyTime4Hour() * 60 + ""));
		try {
			studyAskFacade.update(updateStudyAsk);
			submitSuccess = 1;
			search();
			updateStudyAsk = null;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateStudyAsk=" + updateStudyAsk);
			logger.error(ExceptionUtil.getEMsg(e));
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 复制
	 * 
	 * @param askID
	 *            :目标askID
	 * @param type
	 *            :目标type
	 */
	public void copy(Integer askID, Short type) {
		byte submitSuccess = 0;
		if (targetType == null) {
			this.addWarnMessage("info", "请选择要复制的课程！");
			return;
		}
		if (targetType == type) {
			this.addWarnMessage("info", "原课程与目标课程一致！");
			return;
		}
		try {
			StudyAsk studyAsk = new StudyAsk();// 原
			studyAsk.setChapterID(chapterID);
			studyAsk.setType(targetType);
			studyAsk = studyAskFacade.getStudyAskListOne(studyAsk);
			studyAsk.setType(type);
			if (askID == 0) {
				// 添加
				studyAskFacade.add(studyAsk);
			} else {
				// 更新
				studyAsk.setAskID(askID);
				studyAskFacade.update(studyAsk);
			}
			addStudyAsk = studyAskFacade.splitContent(studyAsk);
			submitSuccess = 1;
			search();
			this.updateComponent("copyRadio");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtil.getEMsg(e));
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public byte getFlag() {
		return flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

	public List<StudyAsk> getStudyAskList() {
		return studyAskList;
	}

	public void setStudyAskList(List<StudyAsk> studyAskList) {
		this.studyAskList = studyAskList;
	}

	public void setStudyAskFacade(StudyAskFacade studyAskFacade) {
		this.studyAskFacade = studyAskFacade;
	}

	public StudyAsk getAddStudyAsk() {
		return addStudyAsk;
	}

	public void setAddStudyAsk(StudyAsk addStudyAsk) {
		this.addStudyAsk = addStudyAsk;
	}

	public Integer getChapterID() {
		return chapterID;
	}

	public void setChapterID(Integer chapterID) {
		this.chapterID = chapterID;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public Map<Short, String> getTypeMap() {
		return new LinkedHashMap<Short, String>() {
			{
				put(Short.parseShort("1"), "一门课程");
				put(Short.parseShort("2"), "两门课程");
				put(Short.parseShort("3"), "三门课程");
				put(Short.parseShort("4"), "四门以上");
			}
		};
	}

	/**
	 * 已经添加过的类型
	 * 
	 * @return
	 */
	public Map<Short, String> getHasTypeMap() {
		return studyAskFacade.getStudyAskTypeList(chapterID);
	}

	public StudyAsk getUpdateStudyAsk() {
		return updateStudyAsk;
	}

	public void setUpdateStudyAsk(StudyAsk updateStudyAsk) {
		this.updateStudyAsk = updateStudyAsk;
	}

	public Short getTargetType() {
		return targetType;
	}

	public void setTargetType(Short targetType) {
		this.targetType = targetType;
	}

}