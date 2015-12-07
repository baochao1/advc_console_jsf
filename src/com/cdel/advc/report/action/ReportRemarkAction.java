/*
 * @Title: ReportAction.java
 * @Package com.cdel.advc.report.action
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-8-26 下午4:28:03
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-8-26                          
 */
package com.cdel.advc.report.action;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.report.domain.ReportRemark;
import com.cdel.advc.report.facade.ReportRemarkFacade;
import com.cdel.util.BaseAction;

/**
 * <p>
 * 学习报告评语Bean
 * </p>
 * 
 * @author 张苏磊
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ReportRemarkAction extends BaseAction<ReportRemark> {

	@ManagedProperty("#{reportRemarkFacade}")
	private ReportRemarkFacade reportRemarkFacade;

	private LazyDataModel<ReportRemark> reportRemarkPage;
	private ReportRemark searchReport = new ReportRemark();// 搜索的课程
	private Integer siteID;// 网站ID

	@PostConstruct
	public void initReportRemarkAction() {
		siteID = this.getCurrentSiteID();
		Integer teacherID = this.getCurrentUserID();
		searchReport.setClassQueryType((short) 1);
		searchReport.setApplyStatus((short) 1);
		searchReport.setTeacherID(teacherID);
		this.reportRemarkPage = this.reportRemarkFacade.findPage(searchReport);
	}

	/**
	 * 条件查询，强制分页从位置索引0开始
	 */
	public void search() {
		this.pageTable.setFirst(0);
		this.reportRemarkPage = this.reportRemarkFacade.findPage(searchReport);
	}

	public LazyDataModel<ReportRemark> getReportRemarkPage() {
		return reportRemarkPage;
	}

	public void setReportRemarkPage(LazyDataModel<ReportRemark> reportRemarkPage) {
		this.reportRemarkPage = reportRemarkPage;
	}

	public ReportRemark getSearchReport() {
		return searchReport;
	}

	public void setSearchReport(ReportRemark searchReport) {
		this.searchReport = searchReport;
	}

	public void setReportRemarkFacade(ReportRemarkFacade reportRemarkFacade) {
		this.reportRemarkFacade = reportRemarkFacade;
	}

	public Integer getSiteID() {
		return siteID;
	}

}
