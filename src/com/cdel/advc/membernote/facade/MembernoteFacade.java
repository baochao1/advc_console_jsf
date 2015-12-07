/*
 * @Title: MembernoteFacade.java
 * @Package com.cdel.advc.membernote.facade
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
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

import com.cdel.advc.membernote.domain.Membernote;
import com.cdel.util.BaseFacadeImpl;

/**
 * <p>
 * 班级留言（学员端）
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-4 下午3:08:02
 */
@SuppressWarnings("serial")
@Service
public class MembernoteFacade extends BaseFacadeImpl<Membernote, Integer> {

	/**
	 * 更新置顶状态
	 * 
	 * @param membernote
	 */
	public void updateMembernoteIsTop(Membernote membernote) {
		this.baseDao.update(membernote, "updateMembernoteIsTop");
	}

}
