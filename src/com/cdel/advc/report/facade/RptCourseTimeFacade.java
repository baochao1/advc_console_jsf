/*
 * @Title: RptCourseTimeFacade.java
 * @Package com.cdel.advc.report.facade
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-8-26 下午2:52:15
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-8-26                          
 */
package com.cdel.advc.report.facade;

import java.util.List;
import org.springframework.stereotype.Service;
import com.cdel.advc.report.domain.RptCourseTime;
import com.cdel.util.BaseFacadeImpl;

/**
 * 
 * @author Du Haiying Create at:2013-8-26 下午2:52:15
 */
@SuppressWarnings("serial")
@Service
public class RptCourseTimeFacade extends BaseFacadeImpl<RptCourseTime, Integer> {
	/**
	 * 根据学习报告ID，查找学员对应的各个课程学习时长
	 * 
	 * @param reportID
	 */
	public List<RptCourseTime> getCourseTimeByReportID(Integer reportID) {

		if (reportID == null) {
			throw new IllegalArgumentException("非法参数！");
		}
		return this.baseDao.findSomeList(reportID, "getCourseTimeByReportID");
	}

}
