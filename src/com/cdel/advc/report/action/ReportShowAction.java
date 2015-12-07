/*
 * @Title: ReportShowAction.java
 * @Package com.cdel.advc.report.action
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-8-28 下午3:07:09
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-8-26                          
 */
package com.cdel.advc.report.action;

import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;
import com.cdel.advc.memberclass.domain.MemberClass;
import com.cdel.advc.memberclass.facade.MemberClassFacade;
import com.cdel.advc.report.domain.Report;
import com.cdel.advc.report.domain.RptCourseTime;
import com.cdel.advc.report.facade.ReportFacade;
import com.cdel.advc.report.facade.RptCourseTimeFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.DateUtil;

/**
 * 
 * <p>
 * 学习报告展示 bean
 * </p>
 * 
 * @author Du Haiying Create at:2013-8-28 下午3:07:09
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ReportShowAction extends BaseAction<Report> {

	@ManagedProperty("#{reportFacade}")
	private ReportFacade reportFacade;

	@ManagedProperty("#{memberClassFacade}")
	private MemberClassFacade memberClassFacade;

	@ManagedProperty("#{rptCourseTimeFacade}")
	private RptCourseTimeFacade rptCourseTimeFacade;

	private Integer siteID;

	private Integer reportID;

	private Report report;

	private MemberClass memberClass;

	private CartesianChartModel lineChartModel = new CartesianChartModel();

	private int lineMaxY = 0;

	private PieChartModel pieChartModel = new PieChartModel();

	@PostConstruct
	public void initReportShowAction() {
		siteID = this.getCurrentSiteID();
		reportID = this.getIntegerParameter("reportID");
		if (reportID == null) {
			this.addWarnMessage("reportID为空！");
			return;
		}
		this.report = this.reportFacade.findByID(reportID);

		if (this.report == null) {
			this.addWarnMessage("非法参数！");
			return;
		}

		MemberClass mc = new MemberClass();
		mc.setUserID(this.report.getUserID());
		mc.setClassID(this.report.getClassID());
		this.memberClass = this.memberClassFacade.getMemberClass(mc);

		this.loadLineChart(this.report.getStartTime(),
				this.report.getDayTimeLong());

		this.loadPieChartModel(this.report.getReportID());
	}

	/**
	 * 加载 学习时长 线性图 相关数据
	 * 
	 * @param startDate
	 * @param dayTimeLong
	 */
	private void loadLineChart(Date startDate, String dayTimeLong) {

		this.lineChartModel = new CartesianChartModel();

		if (dayTimeLong == null) {
			return;
		}

		String[] timeLongs = dayTimeLong.split("\\|");
		String[] myTimeLongs = null;// 我的学长
		String[] avTimeLongs = null;// 平均学长
		if (timeLongs.length > 2) {
			avTimeLongs = timeLongs[1].split(",");
			myTimeLongs = timeLongs[2].split(",");
			if (myTimeLongs.length != avTimeLongs.length) {
				return;
			}
			ChartSeries myCs = new ChartSeries("我的每日时长");
			ChartSeries avCs = new ChartSeries("学员平均时长");

			String tempDay = null;
			int myY = 0, avY = 0;
			for (int i = 0, l = avTimeLongs.length; i < l; i++) {
				tempDay = DateUtil.getNowDateString(startDate, "dd");
				startDate = DateUtil.getNextDay(startDate);
				myY = Integer.parseInt(myTimeLongs[i]);
				avY = Integer.parseInt(avTimeLongs[i]);
				// maxY取数据中的最大值，以控制线图中的Y轴最大值
				if (myY > this.lineMaxY) {
					this.lineMaxY = myY;
				}
				if (avY > this.lineMaxY) {
					this.lineMaxY = avY;
				}

				myCs.set(tempDay, myY);
				avCs.set(tempDay, avY);
			}

			this.lineChartModel.addSeries(myCs);
			this.lineChartModel.addSeries(avCs);
		}
	}

	/**
	 * 加载学习报告对应各个课程学习时长 数据
	 * 
	 * @param reportID
	 */
	private void loadPieChartModel(Integer reportID) {
		this.pieChartModel = new PieChartModel();
		List<RptCourseTime> list = rptCourseTimeFacade
				.getCourseTimeByReportID(reportID);
		if (list != null) {
			int m = 0;
			for (int i = 0, size = list.size(); i < size; i++) {
				m = list.get(i).getTimeLong();
				this.pieChartModel.set(list.get(i).getCourseName() + " "
						+ DateUtil.minute2HourMinute(m), m);
			}
		}
	}

	/**
	 * 使得线图中的Y轴最大值 太小时，保持 100
	 */
	public int getLineMaxY() {

		if (lineMaxY < 100) {
			lineMaxY = 100;
		}
		return lineMaxY;
	}

	public Integer getReportID() {
		return reportID;
	}

	public void setReportID(Integer reportID) {
		this.reportID = reportID;
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

	public MemberClass getMemberClass() {
		return memberClass;
	}

	public void setMemberClass(MemberClass memberClass) {
		this.memberClass = memberClass;
	}

	public void setMemberClassFacade(MemberClassFacade memberClassFacade) {
		this.memberClassFacade = memberClassFacade;
	}

	public CartesianChartModel getLineChartModel() {
		return lineChartModel;
	}

	public PieChartModel getPieChartModel() {
		return pieChartModel;
	}

	public Integer getSiteID() {
		return siteID;
	}

	public void setRptCourseTimeFacade(RptCourseTimeFacade rptCourseTimeFacade) {
		this.rptCourseTimeFacade = rptCourseTimeFacade;
	}

}
