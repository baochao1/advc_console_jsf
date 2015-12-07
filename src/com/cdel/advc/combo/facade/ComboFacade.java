package com.cdel.advc.combo.facade;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdel.advc.combo.domain.Combo;
import com.cdel.advc.course.domain.Course;
import com.cdel.advc.course.facade.CourseFacade;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.StringUtil;

/**
 * 
 * 套餐业务实现
 * 
 * @author Haiying Du
 * 
 */
@SuppressWarnings("serial")
@Service
public class ComboFacade extends BaseFacadeImpl<Combo, Integer> implements
		Serializable {
	@Autowired
	private CourseFacade courseFacade;

	/**
	 * 根据名称获取combo
	 * 
	 * @param comboName
	 * @param flag
	 * @return
	 */
	public List<String> getComboByComboName(String comboName, byte flag) {
		if (flag == 0) {
			return this.baseDao.findSomeList(comboName,
					"getComboByComboName");
		} else {
			return this.baseDao.findSomeList(comboName,
					"getComboByComboName2");
		}
	}

	public boolean checkCombo(byte flag, Combo combo) {
		if (flag == 0) {
			Integer majorID = combo.getMajorID();
			if (majorID == null) {
				return addWarnMessage("辅导名称不能为空！");
			}
		}
		if (flag != 3) {
			Integer[] courseIDs = combo.getCourseIDs();
			if (courseIDs == null || courseIDs.length == 0) {
				return addWarnMessage("必须选择课程！");
			}
			String comboName = StringUtil.nullToString(combo.getComboName());
			if (comboName.equals("")) {
				return addWarnMessage("套餐名称不能为空！");
			}
			String comboCode = StringUtil.nullToString(combo.getComboCode());
			if (comboCode.equals("")) {
				return addWarnMessage("套餐代码不能为空！");
			}
		}
		int count = judgeComboCodeInUpdate(combo);
		if (count > 0) {
			if (flag != 3) {
				return addWarnMessage("本网站已存在代码为" + combo.getComboCode()
						+ "的有效套餐，请设为无效后再添加！");
			} else {
				return addWarnMessage("info",
						"本网站已存在代码为" + combo.getComboCode() + "的有效套餐，请设为无效后再添加！");
			}
		}
		Course course = new Course();
		course.setSiteID(combo.getSiteID());
		course.setCourseCode(combo.getComboCode());
		List<Course> list = courseFacade.checkCourseCode(course);
		if (list != null && list.size() > 0) {
			if (flag != 3) {
				return addWarnMessage("本网站已存在代码为" + combo.getComboCode()
						+ "的有效课程，请设为无效后再添加！");
			} else {
				return addWarnMessage("info",
						"本网站已存在代码为" + combo.getComboCode() + "的有效课程，请设为无效后再添加！");
			}
		}
		return true;
	}

	/**
	 * 判断重名
	 * 
	 * @param combo
	 * @return
	 */
	public Integer judgeComboCodeInUpdate(Combo combo) {
		return this.baseDao.get(combo, "judgeComboCodeInUpdate");
	}

	/**
	 * 通过comboCodes获取其中的课程code
	 * 
	 * @param comboCodes
	 * @return
	 */
	public List<String> getCourseCodesByCombo(String comboCodes) {
		return baseDao.findSomeList(comboCodes, "getCourseCodesByCombo");
	}

}