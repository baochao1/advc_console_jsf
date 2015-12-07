package com.cdel.advc.report.facade;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdel.advc.report.domain.Report;
import com.cdel.advc.report.domain.ReportLog;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class ReportFacade extends BaseFacadeImpl<Report, Integer> implements
		Serializable {
	@Autowired
	private ReportLogFacade reportLogFacade;

	/**
	 * 删除报告，同时删除计划详细及课程章节时间等相关信息
	 * 
	 * @param reportID
	 */
	public void deleteReportByID(Integer reportID) {

		if (reportID == null) {
			throw new IllegalArgumentException("非法参数！");
		}

		this.baseDao.delete(reportID, "deleteReportByID");
	}

	/**
	 * 
	 * 更新报告状态
	 * 
	 * @param reportID
	 */
	public void updateStatus(Integer reportID, Short status) {

		if (reportID == null || status == null) {
			throw new IllegalArgumentException("非法参数！");
		}

		Report t = new Report();
		t.setReportID(reportID);
		t.setStatus(status);

		this.baseDao.update(t, "updateStatus");
	}

	/**
	 * 学员换班后，把学习报告对应到新的班级
	 * 
	 * @param newClassID
	 * @param oldClassID
	 * @param userID
	 */
	public void updateReportToNewClass(Integer newClassID, Integer oldClassID,
			Integer userID) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("newClassID", newClassID);
		map.put("classID", oldClassID);
		map.put("userID", userID);
		baseDao.update(map, "updateReportToNewClass");
	}

	/**
	 * 更新学习报告评语
	 * 
	 * @param report
	 */
	public void updateRemarkContent(Report report, Integer creator) {
		Date now = new Date();
		report.setRemarkTime(now);
		report.setTeacherID(creator);
		super.update(report);
		this.baseDao.update(report, "updateRemarkContent");
		this.baseDao.update(report, "updateReport");
		ReportLog reportLog = new ReportLog();
		reportLog.setRemarkContent(report.getRemarkContent());
		reportLog.setReportID(report.getReportID());
		reportLog.setRemarker(creator);
		reportLog.setRemarkTime(now);
		reportLogFacade.add(reportLog);
	}

	/**
	 * 查找班级学员下的报告个数
	 */
	public int getCountByClassIDAndUserID(Integer userID, Integer classID) {
		Report t = new Report();
		t.setUserID(userID);
		t.setClassID(classID);
		return (Integer) this.baseDao.get(t, "getCountByClassIDAndUserID");
	}

	/**
	 * 添加报告详细
	 * 
	 * @param report
	 */
	public void addReportDetail(Report report) {
		this.baseDao.insert(report, "insertReportDetail");
	}

	/**
	 * 获取某个用户在班级里最近一次生成报告的结束时间
	 * 
	 * @param report
	 */
	public Date getMaxEndTime(Report report) {
		Report r = this.baseDao.getByEntity(report, "getMaxEndTime");
		if (r != null) {
			return r.getEndTime();
		}
		return null;
	}

}