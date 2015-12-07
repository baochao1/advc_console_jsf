package com.cdel.advc.combo.action;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.apache.commons.lang.StringUtils;

import com.cdel.advc.combo.domain.Combo;
import com.cdel.advc.combo.facade.ComboFacade;
import com.cdel.advc.course.domain.Course;
import com.cdel.advc.course.facade.CourseFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.ExceptionUtil;
import com.cdel.util.StringUtil;

/**
 * 
 * 套餐Bean
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
public class ComboReqAction extends BaseAction<Combo> implements Serializable {
	@ManagedProperty(value = "#{comboFacade}")
	private ComboFacade comboFacade;
	@ManagedProperty(value = "#{courseFacade}")
	private CourseFacade courseFacade;

	private byte flag = -1;
	private Combo updateCombo = new Combo();

	/**
	 * 修改状态
	 */
	public void changeStatus(Integer comboID, String comboCode, Integer status) {
		ComboAction comboAction = (ComboAction) this
				.getViewAction("comboAction");
		updateCombo.setComboID(comboID);
		Short newStatus;
		if (status == 1) {
			newStatus = 0;
		} else {
			newStatus = 1;
		}
		updateCombo.setStatus(newStatus);
		if (newStatus == 0) {
			try {
				comboFacade.update(updateCombo);
				comboAction.search4U();
			} catch (Exception e) {
				e.printStackTrace();
				this.addErrorMessage("info", FAILINFO);
			}
		} else {
			updateCombo.setSiteID(comboAction.getSiteID());
			updateCombo.setComboCode(comboCode);
			if (comboFacade.checkCombo((byte) 3, updateCombo)) {
				try {
					comboFacade.update(updateCombo);
					comboAction.search4U();
				} catch (Exception e) {
					e.printStackTrace();
					this.addErrorMessage("info", FAILINFO);
				}
			}
		}
		this.addMessage("info", SUCESSINFO);
	}

	/**
	 * 打开添加页面
	 */
	public void add() {
		flag = 0;
		updateCombo = new Combo();
		ComboAction comboAction = (ComboAction) this
				.getViewAction("comboAction");
		comboAction.setCourseList(null);
	}

	/**
	 * 打开编辑页面
	 */
	public void update(Integer comboID) {
		flag = 1;
		updateCombo = comboFacade.findByID(comboID);
		ComboAction comboAction = (ComboAction) this
				.getViewAction("comboAction");
		Course c = new Course();
		c.setMajorID(updateCombo.getMajorID());
		c.setStatus((short) 1);
		c.setIsPre((short) 2);
		comboAction.setCourseList(courseFacade.getCourseListByMajor(c));
	}
	
	/**
	 * 打开查看 套餐下对应的课程列表 页面
	 */
	public void queryCourses(Integer comboID) {
		flag = 1;
		updateCombo = comboFacade.findByID(comboID);
		ComboAction comboAction = (ComboAction) this.getViewAction("comboAction");
		Course c = new Course();
		c.setMajorID(updateCombo.getMajorID());
		c.setCourseSet(StringUtil.filterStringDouhao(updateCombo.getCourseSet()));
		comboAction.setCourseList(courseFacade.getCourseListByComboID(c));
	}

	/**
	 * 提交
	 */
	public void updateSubmit() {
		byte submitSuccess = 0;// 添加是否成功
		ComboAction comboAction = (ComboAction) this
				.getViewAction("comboAction");
		updateCombo.setSiteID(comboAction.getSiteID());
		if (comboFacade.checkCombo(flag, updateCombo)) {
			updateCombo.setCourseSet(StringUtils.join(
					updateCombo.getCourseIDs(), ","));
			if (flag == 0) {
				updateCombo.setStatus(Short.valueOf("1"));
				updateCombo.setCreator(this.getCurrentUserID());
				updateCombo.setCreateTime(new Date());
			}
			try {
				if (flag == 0) {
					comboFacade.add(updateCombo);
					comboAction.search();
				} else {
					comboFacade.update(updateCombo);
					comboAction.search4U();
				}
				submitSuccess = 1;
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("comboFacade=" + comboFacade);
				logger.error(ExceptionUtil.getEMsg(e));
				submitSuccess = -1;
			}
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public void setComboFacade(ComboFacade comboFacade) {
		this.comboFacade = comboFacade;
	}

	public byte getFlag() {
		return flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

	public Combo getUpdateCombo() {
		return updateCombo;
	}

	public void setUpdateCombo(Combo updateCombo) {
		this.updateCombo = updateCombo;
	}

	public void setCourseFacade(CourseFacade courseFacade) {
		this.courseFacade = courseFacade;
	}

}