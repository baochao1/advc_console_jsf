/*
 * @Title: MembernoteFacade.java
 * @Package com.cdel.advc.membernote.facade
 * @Description: TODO
 * @author zhangsulei
 * @date 2013-7-4 下午3:08:02
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-7-4                          
 */
package com.cdel.advc.membernote.facade;

import org.springframework.stereotype.Service;
import com.cdel.advc.membernote.domain.MembernoteAll;
import com.cdel.util.BaseFacadeImpl;

/**
 * <p>
 * 全部班级留言
 * </p>
 * 
 * @author 张苏磊 Create at:2013-7-4 下午3:08:02
 */
@SuppressWarnings("serial")
@Service
public class MembernoteAllFacade extends BaseFacadeImpl<MembernoteAll, Integer> {
	/**
	 * 返回记录总数
	 * 
	 * @param membernoteAll
	 * @return
	 */
	public int getCount(MembernoteAll membernoteAll) {
		return (Integer) this.baseDao.get(membernoteAll, "countMembernoteAll");
	}

}
