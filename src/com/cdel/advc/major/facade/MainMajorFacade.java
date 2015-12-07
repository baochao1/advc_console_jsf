package com.cdel.advc.major.facade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cdel.advc.major.domain.MainMajor;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.Contants;

/**
 * 
 * <p>
 * 主库 辅导 facade业务层
 * </p>
 * 
 * @author Du Haiying Create at:2014-3-12 下午3:01:51
 */
@SuppressWarnings("serial")
@Service
public class MainMajorFacade extends BaseFacadeImpl<MainMajor, Integer>
		implements Serializable {

	/***
	 * 查询不同网校下的主库辅导列表
	 * 
	 * @param siteID
	 *            网校ID
	 * @return
	 */
	public List<MainMajor> findMajorsBySiteID(Integer siteID) {

		if (siteID == null) {
			return new ArrayList<MainMajor>();
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put(Contants.DATA_KEY, Contants.ACC_MAIN);
		params.put("siteID", siteID);

		return this.baseDao.findList(params, "findMainMajorsBySiteID");
	}

}
