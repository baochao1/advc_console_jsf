package com.cdel.advc.testterm.facade;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdel.advc.classes.facade.ClassesFacade;
import com.cdel.advc.testterm.domain.Testterm;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class TesttermFacade extends BaseFacadeImpl<Testterm, Integer> {
	@Autowired
	private ClassesFacade classesFacade;

	/**
	 * 模糊匹配考期名称
	 * 
	 * @param testterm
	 * @return
	 */
	public List<String> getTermByTermName(String testName) {
		return baseDao.findSomeList(testName, "getTermByTermName");
	}

	public boolean checkTestterm(byte flag, Testterm testterm) {
		if (flag == 0) {
			if (testterm.getMajorID() == null) {
				return addWarnMessage("辅导名称不能为空！");
			}
		}
		if (testterm.getTermDate() == null) {
			return addWarnMessage("考期日期不能为空！");
		}
		if (StringUtils.isBlank(testterm.getTermName())) {
			return addWarnMessage("考期名称不能为空！");
		}
		if (StringUtils.isBlank(testterm.getClassShortName())) {
			return addWarnMessage("班级简称不能为空！");
		}
		if (testterm.getStartTime() == null) {
			return addWarnMessage("学习计划开始时间不能为空！");
		}
		if (testterm.getEndTime() == null) {
			return addWarnMessage("学习计划结束时间不能为空！");
		}
		if (testterm.getStartTime().compareTo(testterm.getEndTime()) > 0) {
			return addWarnMessage("学习计划开始时间不能大于结束时间！");
		}
		if (testterm.getMaxStudyTime() == null
				|| testterm.getMaxStudyTime() <= 0) {
			return addWarnMessage("学习计划默认最大学习时间要大于0！");
		}
		if (testterm.getPreStartTime() == null) {
			return addWarnMessage("预习计划开始时间不能为空！");
		}
		if (testterm.getPreEndTime() == null) {
			return addWarnMessage("预习计划结束时间不能为空！");
		}
		if (testterm.getPreStartTime().compareTo(testterm.getPreEndTime()) > 0) {
			return addWarnMessage("预习计划开始时间不能大于结束时间！");
		}
		if (testterm.getPreMaxStudyTime() == null
				|| testterm.getPreMaxStudyTime() <= 0) {
			return addWarnMessage("预习计划默认最大学习时间要大于0！");
		}
		return true;
	}

	/**
	 * 删除考期（增加是否有班级的判断）
	 * 
	 * @param termID
	 * @return
	 */
	public byte newDelete(Integer termID) throws Exception {
		if (classesFacade.getCountClassesByTermID(termID) == 0) {
			delete(termID);
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * 根据majorID返回考期list
	 * 
	 * @param testterm
	 * @return
	 */
	public List<Testterm> getTermListByMajorID(Integer majorID) {
		if (majorID != null && majorID != 0) {
			return findList(majorID);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * 根据班级ID 查找考期
	 * 
	 * @param classID
	 * @return
	 */
	public Testterm getTermByClassID(Integer classID) {
		return this.baseDao.get(classID, "getTermByClassID");
	}

}
