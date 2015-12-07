/*
 * @Title: ReportReqAction.java
 * @Package com.cdel.advc.report.action
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-8-26 下午4:22:12
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-8-26                          
 */
package com.cdel.advc.report.action;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.apache.commons.lang.StringUtils;
import com.cdel.advc.report.domain.Report;
import com.cdel.advc.report.facade.CreateReportFacade;
import com.cdel.advc.report.facade.ReportFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.ExceptionUtil;

/**
 * <p>
 * 学习报告 request Bean
 * </p>
 * 
 * @author Du Haiying Create at:2013-8-26 下午4:22:12
 */
@SuppressWarnings("serial")
@ManagedBean
public class ReportReqAction extends BaseAction<Report> {

	@ManagedProperty("#{reportFacade}")
	private ReportFacade reportFacade;

	@ManagedProperty("#{createReportFacade}")
	private CreateReportFacade createReportFacade;

	private Report report = new Report();

	private byte submitSuccess = 0;// 添加是否成功

	/**
	 * 生成报告
	 * 
	 * @throws Exception
	 */
	public void addReport() {

		ReportAction ra = ((ReportAction) this.getViewAction("reportAction"));
		report.setStartTime(ra.getInitStartTime());
		if (this.report == null || ra.getUserID() == null
				|| ra.getClassID() == null) {
			this.addWarnMessage("非法参数！");
			return;
		}
		if (this.report.getStartTime() == null
				|| this.report.getEndTime() == null) {
			this.addWarnMessage("请选择开始或结束时间！");
			return;
		}
		if (this.report.getStartTime().compareTo(this.report.getEndTime()) > 0) {
			this.addWarnMessage("结束时间必须大于开始时间！");
			return;
		}
		if (this.report.getEndTime().compareTo(new Date()) > 0) {
			this.addWarnMessage("结束时间必须小于当前时间！");
			return;
		}

		Integer siteID = ra.getSiteID();
		report.setCreator(this.getCurrentUserID());
		report.setUserID(ra.getUserID());
		report.setClassID(ra.getClassID());
		try {
			this.createReportFacade.addReport(report, siteID);
			this.addInfoMessage("生成成功！");
			ra.search();
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("生成失败！");
			logger.error(report);
			logger.error(ExceptionUtil.getEMsg(e));
		}
	}

	/**
	 * 更新报告状态
	 */
	public void updateStatus(Integer reportID, Short status) {
		if (reportID == null || status == null) {
			this.addWarnMessage("非法参数!");
			return;
		}

		if (status == 0) {// 与当前状态值相反
			this.reportFacade.updateStatus(reportID, (short) 1);
		} else {
			this.reportFacade.updateStatus(reportID, (short) 0);
		}

		this.addInfoMessage("状态修改成功");
	}

	/**
	 * 删除报告
	 */
	public void deleteReport(Integer reportID) {
		if (reportID == null) {
			this.addWarnMessage("非法参数!");
			return;
		}
		try {
			this.reportFacade.deleteReportByID(reportID);
			this.addInfoMessage("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("删除失败！");
			logger.error(report);
			logger.error(ExceptionUtil.getEMsg(e));
		}
	}

	/**
	 * 对报告添加评论
	 */
	public void addRemark() {
		ReportShowAction ra = (ReportShowAction) this
				.getViewAction("reportShowAction");
		Report report = ra.getReport();
		if (StringUtils.isBlank(report.getRemarkContent())) {
			this.addInfoMessage("评语内容不能为空！");
			return;
		}
		try {
			report.setApplyStatus((short)2);
			reportFacade.updateRemarkContent(report, this.getCurrentUserID());
			submitSuccess = 1;
		} catch (Exception e) {
			submitSuccess = -1;
			e.printStackTrace();
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public void setReportFacade(ReportFacade reportFacade) {
		this.reportFacade = reportFacade;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public void setCreateReportFacade(CreateReportFacade createReportFacade) {
		this.createReportFacade = createReportFacade;
	}
}
