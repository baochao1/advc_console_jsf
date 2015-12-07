package com.cdel.advc.sitenotice.facade;

import java.io.Serializable;
import org.springframework.stereotype.Service;
import com.cdel.advc.sitenotice.domain.Sitenotice;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.StringUtil;

@SuppressWarnings("serial")
@Service
public class SitenoticeFacade extends BaseFacadeImpl<Sitenotice, Integer>
		implements Serializable {
	public boolean checkSitenotice(Short flag, Sitenotice updateSitenotice) {
		if (updateSitenotice.getSiteID() == null) {
			return addWarnMessage("网站不能为空！");
		}
		if (StringUtil.nullToString(updateSitenotice.getSubject()).equals("")) {
			return addWarnMessage("公告标题不能为空！");
		}
		if (updateSitenotice.getType() == null) {
			return addWarnMessage("公告类别不能为空！");
		}
		if (updateSitenotice.getStartTime() == null) {
			return addWarnMessage("公告开始时间不能为空！");
		}
		if (updateSitenotice.getEndTime() == null) {
			return addWarnMessage("公告结束时间不能为空！");
		}
		if (updateSitenotice.getStartTime().compareTo(
				updateSitenotice.getEndTime()) >= 0) {
			return addWarnMessage("公告开始时间要小于公告结束时间！");
		}
		if (StringUtil.nullToString(updateSitenotice.getContent()).equals("")) {
			return addWarnMessage("公告内容不能为空！");
		}
		return true;
	}

}
