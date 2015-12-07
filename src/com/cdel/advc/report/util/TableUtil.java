package com.cdel.advc.report.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.cdel.advc.report.domain.CwTimeLong;
import com.cdel.advc.report.domain.RptCourseTime;
import com.cdel.qz.quesError.domain.QuesError;
import com.cdel.qz.score.center.domain.CenterScore;
import com.cdel.qz.score.point.domain.PointScore;

public class TableUtil {

	public static Map<String, Object> genRptTimeLongTable(
			Map<String, List<CwTimeLong>> timeMap, Integer reportID) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<RptCourseTime> ctList = new LinkedList<RptCourseTime>();
		StringBuffer sb = new StringBuffer();
		sb.append("<table class='rptTime'><TR><TH>课程</TH><TH>班次</TH><TH>章节</TH><TH>累计学习时间</TH><TH>最后结束时间</TH></TR>");
		Set<String> set = timeMap.keySet();
		int rowNum = 0;
		for (String courseName : set) {
			List<CwTimeLong> list = timeMap.get(courseName);
			int size = list.size();
			int timeLong = 0;
			if (size > 0) {
				sb.append("<tr><td rowspan='" + size + "'>").append(courseName)
						.append("</td>");
				for (int i = 0; i < size; i++) {
					CwTimeLong rt = list.get(i);
					Integer totalTime = rt.getTotalStudyTime();
					String strTotalTime = "0分钟";
					if (totalTime != null) {
						timeLong += totalTime;
						int hours = totalTime / 60;
						int minute = totalTime % 60;
						strTotalTime = hours + "小时" + minute + "分钟";
					}
					if (i != 0) {
						sb.append("<tr>");
					}
					sb.append("<td>")
							.append(rt.getClassLevelStr())
							.append("</td>")
							.append("<td>")
							.append(rt.getChapterName() == null ? "" : rt
									.getChapterName()).append("</td>")
							.append("<td>").append(strTotalTime)
							.append("</td>").append("<td>")
							.append(rt.getLastStudyTimeStr()).append("</td>");
					sb.append("</tr>");
					rowNum++;
				}
			}
			RptCourseTime courseTime = new RptCourseTime();
			courseTime.setReportID(reportID);
			courseTime.setCourseName(courseName);
			courseTime.setTimeLong(timeLong == 0 ? 1 : timeLong);
			ctList.add(courseTime);
		}
		if (rowNum == 0) {
			sb.append("<tr><td colspan='5'>您没有学习记录</td></tr>");
		}
		sb.append("</table>");
		map.put("table", sb.toString());
		map.put("courseTime", ctList);
		return map;
	}

	public static String genRptPointTable(List<PointScore> rptPointList) {
		StringBuffer sb = new StringBuffer();
		sb.append("<table class='rptPoint'><TR><TH>课程</TH><TH>章　节</TH><TH>知识点个数</TH><TH>测试题数</TH><TH>正确题数</TH><TH>正确率</TH></TR>");
		if (rptPointList == null || rptPointList.size() == 0) {
			sb.append("<tr><td colspan='6'>您没有知识点测试记录</td></tr>");
		} else {
			for (int i = 0; i < rptPointList.size(); i++) {
				PointScore rptPoint = rptPointList.get(i);
				float pointTestCount = Float
						.valueOf(rptPoint.getCorrectScale()).floatValue();
				String correctRateStr = pointTestCount * 100 + "%";
				sb.append("<tr>");
				sb.append("<td>").append(rptPoint.getCourseName())
						.append("</td>").append("<td>")
						.append(rptPoint.getChapterName()).append("</td>")
						.append("<td>").append(rptPoint.getPointNum())
						.append("</td>").append("<td>")
						.append(rptPoint.getTotleNum()).append("</td>")
						.append("<td>").append(rptPoint.getCorrectCount())
						.append("</td>").append("<td>").append(correctRateStr)
						.append("</td>");
				sb.append("</tr>");
			}
		}
		sb.append("</table>");
		return sb.toString();
	}

	public static String genRptCenterTable(List<CenterScore> rptCenterList) {
		StringBuffer sb = new StringBuffer();
		sb.append("<table class='rptCenter'><TR><TH>课程</TH><TH>中心名称</TH><TH>练习名称</TH><TH>客观题得分</TH><TH>试卷总分</TH><TH>答题次数</TH><TH>答题时间</TH></TR>");
		if (rptCenterList == null || rptCenterList.size() == 0) {
			sb.append("<tr><td colspan='7'>您没有测试记录</td></tr>");
		} else {
			for (int i = 0; i < rptCenterList.size(); i++) {
				CenterScore rptCenter = rptCenterList.get(i);
				sb.append("<tr>");
				sb.append("<td>").append(rptCenter.getCourseName())
						.append("</td>").append("<td>")
						.append(rptCenter.getCenterName()).append("</td>")
						.append("<td>").append(rptCenter.getPaperViewName())
						.append("</td>").append("<td>")
						.append(rptCenter.getMachineMark()).append("</td>")
						.append("<td>").append(rptCenter.getPaperTotalScore())
						.append("</td>").append("<td>")
						.append(rptCenter.getPlayCount()).append("</td>")
						.append("<td>").append(rptCenter.getLastPlayTimeStr2())
						.append("</td>");
				sb.append("</tr>");
			}
		}
		sb.append("</table>");
		return sb.toString();
	}

	public static String genRptErrorTable(List<QuesError> rptWrongList) {
		StringBuffer sb = new StringBuffer();
		sb.append("<table class='rptError'><TR><TH>课程</TH><TH>栏 目</TH><TH>试 卷</TH><TH>错题数量</TH><TH>最后做题时间</TH></TR>");
		if (rptWrongList == null || rptWrongList.size() == 0) {
			sb.append("<tr><td colspan='5'>您没有错题记录</td></tr>");
		} else {
			for (int i = 0; i < rptWrongList.size(); i++) {
				QuesError rptWrong = rptWrongList.get(i);
				sb.append("<tr>");
				sb.append("<td>").append(rptWrong.getCourseName())
						.append("</td>").append("<td>")
						.append(rptWrong.getTestType()).append("</td>")
						.append("<td>").append(rptWrong.getTestName())
						.append("</td>").append("<td>")
						.append(rptWrong.getErrorCount()).append("</td>")
						.append("<td>").append(rptWrong.getLastPlayTimeStr())
						.append("</td>");
				sb.append("</tr>");
			}
		}
		sb.append("</table>");
		return sb.toString();
	}
}
