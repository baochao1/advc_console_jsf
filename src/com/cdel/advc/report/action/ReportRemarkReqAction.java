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

import java.util.Map;
import javax.faces.bean.ManagedBean;
import com.cdel.advc.report.domain.ReportRemark;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;

/**
 * <p>
 * 学习报告评语Bean
 * </p>
 * 
 * @author 张苏磊
 */
@SuppressWarnings("serial")
@ManagedBean
public class ReportRemarkReqAction extends BaseAction<ReportRemark> {

	public Map<Short, String> getApplyStatuss() {
		return Contants.applyStatus;
	}

}
