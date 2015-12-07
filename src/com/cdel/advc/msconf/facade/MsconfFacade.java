/*
 * @Title: MemberClassFacade.java
 * @Package com.cdel.advc.memberclass.facade
 * @Description: TODO
 * @author zhangsulei
 * @date 2013-7-11 上午9:19:47
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-7-11                          
 */
package com.cdel.advc.msconf.facade;

import org.springframework.stereotype.Service;
import com.cdel.advc.msconf.domain.Msconf;
import com.cdel.util.BaseFacadeImpl;

/**
 * <p>
 * 面授班 实体 facade层
 * </p>
 * 
 * @author 张苏磊
 */
@SuppressWarnings("serial")
@Service
public class MsconfFacade extends BaseFacadeImpl<Msconf, Integer> {
	/**
	 * 获取面授班管理标志
	 * 
	 * @param classID
	 * @return
	 */
	public Integer getIsMs(Integer classID) {
		return this.baseDao.get(classID, "getIsMs");
	}

}
