/*
 * @Title: TeacherOrgFacade.java
 * @Package com.cdel.advc.teacherorg.facade
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-7-2 下午3:23:58
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-7-2                          
 */
package com.cdel.advc.teacherorg.facade;

import java.util.List;
import org.springframework.stereotype.Service;
import com.cdel.advc.teacherorg.domain.TeacherOrg;
import com.cdel.util.BaseFacadeImpl;

/**
 * <p>
 * 教师组别（组织） facade层
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-2 下午3:23:58
 */
@SuppressWarnings("serial")
@Service
public class TeacherOrgFacade extends BaseFacadeImpl<TeacherOrg, Integer> {

	/**
	 * 取公司组织架构
	 * 
	 * @return
	 */
	public List<TeacherOrg> getOrgList() {
		return baseDao.findList("getOrgList");
	}

}
