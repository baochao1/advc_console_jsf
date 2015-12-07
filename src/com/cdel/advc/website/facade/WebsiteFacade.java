package com.cdel.advc.website.facade;

import java.io.Serializable;
import org.springframework.stereotype.Service;
import com.cdel.advc.website.domain.Website;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class WebsiteFacade extends BaseFacadeImpl<Website, Integer> implements
		Serializable {

	/**
	 * 根据班级id获取所属网站
	 * 
	 * @param classID
	 * @return
	 */
	public Website getWebsiteByClassID(Integer classID) {
		return this.baseDao.get(classID, "getWebsiteByClassID");
	}

}