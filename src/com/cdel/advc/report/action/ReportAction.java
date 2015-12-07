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

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.cdel.advc.report.domain.Report;
import com.cdel.advc.report.facade.ReportFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.DateUtil;

/**
 * <p>
 * 学习报告Bean
 * </p>
 * 
 * @author Du Haiying Create at:2013-8-26 下午4:28:03
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ReportAction extends BaseAction<Report> {

	@ManagedProperty("#{reportFacade}")
	private ReportFacade reportFacade;

	private LazyDataModel<Report> page;
	private Report searchReport = new Report();

	// ------- 临时处理 ----------//
	private Integer siteID;
	private Integer userID;
	private Integer classID;
	private Date initStartTime;// 某个用户在班级里最近一次生成报告的结束时间

	@PostConstruct
	public void initReportAction() {
		siteID = this.getCurrentSiteID();
		classID = this.getIntegerParameter("classID");
		userID = this.getIntegerParameter("userID");

		if (userID == null || classID == null) {
			this.addWarnMessage("非法参数！");
			return;
		}
		searchReport.setClassID(classID);
		searchReport.setUserID(userID);
		this.page = this.reportFacade.findPage(searchReport);
		super.heighti2 = super.calHeight(15f / 20);
		initStartTime = reportFacade.getMaxEndTime(searchReport);
		initStartTime = DateUtil.getNextDay(initStartTime);
	}

	/**
	 * 查询
	 */
	public void search() {
		pageTable.setFirst(0);
		this.page = this.reportFacade.findPage(searchReport);
		this.updateComponent("searchForm:reportTable");
	}

	public LazyDataModel<Report> getPage() {
		return page;
	}

	public void setPage(LazyDataModel<Report> page) {
		this.page = page;
	}

	public void setReportFacade(ReportFacade reportFacade) {
		this.reportFacade = reportFacade;
	}

	public Integer getUserID() {
		return userID;
	}

	public Integer getClassID() {
		return classID;
	}

	public Integer getSiteID() {
		return siteID;
	}

	public Date getInitStartTime() {
		return initStartTime;
	}

	public void setInitStartTime(Date initStartTime) {
		this.initStartTime = initStartTime;
	}

}
