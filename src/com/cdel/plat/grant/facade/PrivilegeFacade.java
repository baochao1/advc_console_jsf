package com.cdel.plat.grant.facade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.cdel.plat.grant.domain.Privilege;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.ExceptionUtil;

/**
 * Class description goes here.
 * 
 * @version 1.0 2008-1-17
 * @author LiXuFang - j2eeli@chinaacc.com
 */
@Service
@SuppressWarnings("serial")
public class PrivilegeFacade extends BaseFacadeImpl<Privilege, Integer>
		implements Serializable {
	private Log log = LogFactory.getLog(PrivilegeFacade.class);

	/**
	 * 按角色ID和用户ID查找权限并初始化权限Map
	 * 
	 * @param context
	 * @param systemTypes
	 * @param adminID
	 */
	@SuppressWarnings(value = { "unchecked", "rawtypes" })
	public List<String> loadAUTH(int systemType, Integer adminID,
			List<Integer> roleIDList) {
		List<String> result = null;
		Map pri = new HashMap();
		pri.put("adminID", adminID);
		pri.put("systemType", systemType);
		pri.put("roleIDList", roleIDList);
		try {
			List<String> priList = baseDao.findList(pri,
					"getAllPrivilegeRoleAndUser");
			result = new ArrayList<String>();
			if (priList != null && priList.size() > 0) {
				for (int i = 0; i < priList.size(); i++) {
					result.add(priList.get(i).toLowerCase());
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("map=" + pri.toString());
			log.error(ExceptionUtil.getEMsg(e));
			return null;
		}
	}

	/**
	 * 查找用户有权限的节点,用于左侧树显示
	 * 
	 * @param con
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Privilege> getPriByUser(Map con) {
		return baseDao.findList(con, "getPriByUser");
	}

}
