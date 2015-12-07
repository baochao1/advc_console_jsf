package com.cdel.plat.grant.facade;

import java.io.Serializable;
import org.springframework.stereotype.Service;
import com.cdel.plat.grant.domain.AdminRole;
import com.cdel.util.BaseFacadeImpl;

/**
 * 角色facade
 * 
 * @author zhangsulei
 */
@SuppressWarnings("serial")
@Service
public class AdminRoleFacade extends BaseFacadeImpl<AdminRole, Integer>
		implements Serializable {
	/**
	 * 根据角色名获取角色ID
	 * 
	 * @param user
	 * @return
	 */
	public Integer getRoleIDByName(String roleName) {
		return baseDao.get(roleName, "getRoleIDByName");
	}

}
