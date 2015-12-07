package com.cdel.advc.classteacher.action;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.apache.commons.lang.StringUtils;

import com.cdel.advc.classes.domain.Classes;
import com.cdel.advc.classes.facade.ClassesFacade;
import com.cdel.advc.classteacher.domain.Classteacher;
import com.cdel.advc.classteacher.facade.ClassteacherFacade;
import com.cdel.advc.teacher.action.TeacherOther;
import com.cdel.advc.teacher.domain.Teacher;
import com.cdel.advc.teacher.facade.TeacherFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.ExceptionUtil;

/**
 * 
 * <p>
 * 班级管理员 相关action
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-24 上午9:49:30
 */
@SuppressWarnings("serial")
@ManagedBean
public class ClassteacherReqAction extends BaseAction<Classteacher> {

	@ManagedProperty("#{classteacherFacade}")
	private ClassteacherFacade classteacherFacade;
	@ManagedProperty(value = "#{classesFacade}")
	private ClassesFacade classesFacade;
	@ManagedProperty(value = "#{teacherFacade}")
	private TeacherFacade teacherFacade;

	private List<Classteacher> teacherList;// 本班管理员
	private boolean showDialogShow = false;// 控制是否显示Dialog
	private Classteacher selectedManager;// 总管理员

	/**
	 * 查看管理员
	 */
	public void show(Integer classID) {
		this.teacherList = this.classteacherFacade.findList(classID);
		showDialogShow = true;
	}

	/**
	 * 批量添加管理员页面
	 * 
	 * @throws Exception
	 */
	public void showAddManagers(Integer classID) throws Exception {
		Classes c = classesFacade.findByID(classID);
		String adviserTeacherCode = c.getAdviserTeacherCode();
		ClassteacherAction ctation = (ClassteacherAction) this
				.getViewAction("classteacherAction");
		ctation.setZhuRenTeacher(teacherFacade
				.getTeacherByCode2(adviserTeacherCode));
		TeacherOther tation = (TeacherOther) this.getViewAction("teacherOther");
		ctation.setClassID(classID);
		tation.setTeacherList(adviserTeacherCode);
		List<Classteacher> teacherList = classteacherFacade.findList(classID);
		if (teacherList != null && teacherList.size() > 0) {
			Integer[] teacherIDs = new Integer[teacherList.size()];
			int i = 0;
			for (Classteacher ct2 : teacherList) {
				if (ct2.getTeacherType().shortValue() == 1) {
					selectedManager = ct2;
				}
				teacherIDs[i++] = ct2.getTeacherID();
				if (ct2.getTeacherCode().equals(adviserTeacherCode)) {
					ct2.setIsZhuRen((byte) 1);
					ctation.setZhuRenID(ct2.getTeacherID());
				}
			}
			ctation.setTeacherList(teacherList);
			ctation.setTeacherIDs(teacherIDs);
		} else {
			ctation.setTeacherList(null);
			ctation.setTeacherIDs(null);
		}
	}

	/** 修改教师 */
	public void changeTeacherID() {
		ClassteacherAction ctation = (ClassteacherAction) this
				.getViewAction("classteacherAction");
		TeacherOther tation = (TeacherOther) this.getViewAction("teacherOther");
		List<Classteacher> l = classteacherFacade.resetSourseList(
				ctation.getTeacherIDs(), tation.getTeacherList(),
				ctation.getZhuRenID());
		Teacher t = ctation.getZhuRenTeacher();
		Classteacher ct = new Classteacher();
		ct.setTeacherName(t.getTeacherName());
		ct.setTeacherID(t.getTeacherID());
		ct.setTeacherDesc("");
		ct.setIsZhuRen((byte) 1);
		l.add(ct);
		ctation.setTeacherList(l);
	}

	/**
	 * 提交
	 */
	public void updateSubmit(Integer classID) {
		byte submitSuccess = 0;// 添加是否成功
		ClassteacherAction ctation = (ClassteacherAction) this
				.getViewAction("classteacherAction");
		List<Classteacher> tlist = ctation.getTeacherList();
		try {
			// 全部删除
			Classteacher ct = new Classteacher();
			ct.setClassID(classID);
			classteacherFacade.delete(ct);
			if (tlist != null) {
				for (Classteacher ct2 : tlist) {
					if (selectedManager != null
							&& ct2.getTeacherID().intValue() == selectedManager
									.getTeacherID()) {
						ct2.setTeacherType((short) 1);
					} else {
						ct2.setTeacherType((short) 0);
					}
					ct2.setClassID(classID);
					if (StringUtils.isBlank(ct2.getTeacherDesc())) {
						ct2.setTeacherDesc("");
					}
				}
				classteacherFacade.addList(tlist);
			}
			submitSuccess = 1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtil.getEMsg(e));
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public void setClassteacherFacade(ClassteacherFacade classteacherFacade) {
		this.classteacherFacade = classteacherFacade;
	}

	public List<Classteacher> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<Classteacher> teacherList) {
		this.teacherList = teacherList;
	}

	public boolean isShowDialogShow() {
		return showDialogShow;
	}

	public void setShowDialogShow(boolean showDialogShow) {
		this.showDialogShow = showDialogShow;
	}

	public Classteacher getSelectedManager() {
		return selectedManager;
	}

	public void setSelectedManager(Classteacher selectedManager) {
		this.selectedManager = selectedManager;
	}

	public void setClassesFacade(ClassesFacade classesFacade) {
		this.classesFacade = classesFacade;
	}

	public void setTeacherFacade(TeacherFacade teacherFacade) {
		this.teacherFacade = teacherFacade;
	}

}
