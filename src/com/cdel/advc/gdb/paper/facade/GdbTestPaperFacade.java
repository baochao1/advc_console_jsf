package com.cdel.advc.gdb.paper.facade;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.cdel.advc.gdb.paper.domain.GdbTestPaper;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class GdbTestPaperFacade extends BaseFacadeImpl<GdbTestPaper, Integer>
		implements Serializable {
	/**
	 * 验证
	 * 
	 * @param paper
	 * @return
	 */
	public boolean check(GdbTestPaper paper, byte flag) {
		if (flag == 0) {
			if (paper.getStudyPlanID() == null) {
				return addWarnMessage("studyPlanID不能为空！");
			}
		}
		if (StringUtils.isBlank(paper.getPaperName())) {
			return addWarnMessage("试卷名称不能为空！");
		}
		if (paper.getPaperType() == null) {
			return addWarnMessage("试卷类型不能为空！");
		}
		if (paper.getTestTime() == null) {
			return addWarnMessage("测试时间不能为空！");
		}
		if (StringUtils.isBlank(paper.getPaperDesc())) {
			return addWarnMessage("试卷描述不能为空！");
		}
		return true;
	}

}
