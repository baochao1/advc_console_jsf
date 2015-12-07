package com.cdel.advc.testterm.action;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.apache.commons.lang.StringUtils;
import com.cdel.advc.classes.facade.ClassesFacade;
import com.cdel.advc.testterm.domain.Testterm;
import com.cdel.advc.testterm.facade.TesttermFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.cdel.util.ExceptionUtil;

@SuppressWarnings("serial")
@ManagedBean
public class TesttermReqAction extends BaseAction<Testterm> implements
		Serializable {
	@ManagedProperty(value = "#{testtermFacade}")
	private TesttermFacade testtermFacade;
	@ManagedProperty(value = "#{classesFacade}")
	private ClassesFacade classesFacade;

	Testterm testterm = new Testterm();

	/**
	 * 修改状态
	 */
	public void changeStatus(Integer termID, Integer status) {
		testterm.setTermID(termID);
		Short newStatus;
		if (status == 1) {
			newStatus = 0;
		} else {
			newStatus = 1;
		}
		testterm.setStatus(newStatus);
		try {
			testtermFacade.update(testterm);
			this.addInfoMessage("info", SUCESSINFO);
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
		}
	}

	/**
	 * 添加
	 */
	public void add() {
		testterm.setTermType((short) 1);
		testterm.setMaxStudyTime(8);
		testterm.setPreMaxStudyTime(8);
	}

	/**
	 * 提交添加
	 */
	public void addSubmit() {
		byte submitSuccess = 0;// 添加是否成功
		if (testtermFacade.checkTestterm((byte) 0, testterm)) {
			try {
				testterm.setMaxStudyTime(testterm.getMaxStudyTime() * 60);
				testterm.setPreMaxStudyTime(testterm.getPreMaxStudyTime() * 60);
				testterm.setTermYear(DateUtil.getYear(testterm.getTermDate()));
				testterm.setTermMonth(DateUtil.getMonth(testterm.getTermDate()));
				testterm.setCreator(this.getCurrentUserID());
				testterm.setCreateTime(new Date());
				testterm.setStatus((short) 1);
				testtermFacade.add(testterm);
				submitSuccess = 1;
				TesttermAction testtermAction = (TesttermAction) this
						.getViewAction("testtermAction");
				testtermAction.search();
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("testterm=" + testterm);
				logger.error(ExceptionUtil.getEMsg(e));
				submitSuccess = -1;
			}
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 生成默认考期名称
	 */
	public void changeTermName() {
		testterm.getMajorID();
		String testType = "";
		if (testterm.getTermType() != null) {
			testType = Contants.termTypeMap.get(testterm.getTermType());
		}
		testterm.setTermName(DateUtil.getYear(testterm.getTermDate())
				+ StringUtils.stripToEmpty(testterm.getMajorName()) + testType);
	}

	/** 考期类型 */
	public Map<Short, String> getTermTypes() {
		return Contants.termTypeMap;
	}

	/**
	 * 修改
	 */
	public void update(Integer termID) {
		testterm = testtermFacade.findByID(termID);
		testterm.setTermDate(DateUtil.stringToDate(testterm.getTermYear() + "-"
				+ testterm.getTermMonth(), "yyyy-MM"));
		testterm.setMaxStudyTime(testterm.getMaxStudyTime() / 60);
		testterm.setPreMaxStudyTime(testterm.getPreMaxStudyTime() / 60);
	}

	/**
	 * 提交修改
	 */
	public void updateSubmit() {
		byte submitSuccess = 0;// 添加是否成功
		if (testtermFacade.checkTestterm((byte) 1, testterm)) {
			try {
				testterm.setMaxStudyTime(testterm.getMaxStudyTime() * 60);
				testterm.setPreMaxStudyTime(testterm.getPreMaxStudyTime() * 60);
				testterm.setTermYear(DateUtil.getYear(testterm.getTermDate()));
				testterm.setTermMonth(DateUtil.getMonth(testterm.getTermDate()));
				testtermFacade.update(testterm);
				submitSuccess = 1;
				TesttermAction testtermAction = (TesttermAction) this
						.getViewAction("testtermAction");
				testtermAction.search4U();
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("testterm=" + testterm);
				logger.error(ExceptionUtil.getEMsg(e));
				submitSuccess = -1;
			}
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 删除
	 * 
	 * @param termID
	 */
	public void delete(Integer termID) {
		try {
			if (testtermFacade.newDelete(termID) == 0) {
				this.addWarnMessage("info", "该考期下已存在班级，不允许删除！");
			} else {
				TesttermAction testtermAction = (TesttermAction) this
						.getViewAction("testtermAction");
				testtermAction.search4U();
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addWarnMessage("info", "数据错误，删除失败！");
		}
	}

	/**
	 * 批量关班
	 * 
	 * @param termID
	 */
	public void closeClass(Integer termID, Short status) {
		try {
			classesFacade.updateClassesStatusInTerm(termID, status);
			this.addInfoMessage("info", "关闭成功！");
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
		}
	}

	public Testterm getTestterm() {
		return testterm;
	}

	public void setTestterm(Testterm testterm) {
		this.testterm = testterm;
	}

	public void setTesttermFacade(TesttermFacade testtermFacade) {
		this.testtermFacade = testtermFacade;
	}

	public void setClassesFacade(ClassesFacade classesFacade) {
		this.classesFacade = classesFacade;
	}

}
