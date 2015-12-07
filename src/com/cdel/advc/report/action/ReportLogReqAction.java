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

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.report.domain.ReportLog;
import com.cdel.advc.report.facade.ReportLogFacade;
import com.cdel.util.BaseAction;

/**
 * <p>
 * 学习报告日志Bean
 * </p>
 * 
 * @author 张苏磊
 */
@SuppressWarnings("serial")
@ManagedBean
public class ReportLogReqAction extends BaseAction<ReportLog> {

	@ManagedProperty("#{reportLogFacade}")
	private ReportLogFacade reportLogFacade;

	private List<ReportLog> reportList;

	/**
	 * 加载日志
	 */
	public void loadLog(Integer reportID) {
		if (reportID != null) {
			reportList = this.reportLogFacade.findList(reportID);
		}
	}

	public List<ReportLog> getReportList() {
		return reportList;
	}

	public void setReportList(List<ReportLog> reportList) {
		this.reportList = reportList;
	}

	public void setReportLogFacade(ReportLogFacade reportLogFacade) {
		this.reportLogFacade = reportLogFacade;
	}

}
