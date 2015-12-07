/*
 * @Title: ClassnewsFacade.java
 * @Package com.cdel.advc.classnews.facade
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-7-4 下午5:46:18
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-7-4                          
 */
package com.cdel.advc.classnews.facade;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.cdel.advc.classnews.domain.Classnews;
import com.cdel.util.BaseFacadeImpl;

/**
 * <p>
 * 班级动态 facade层
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-4 下午5:46:18
 */
@SuppressWarnings("serial")
@Service
public class ClassnewsFacade extends BaseFacadeImpl<Classnews, Integer> {
	/**
	 * 验证数据
	 * 
	 * @param msg
	 * @param context
	 * @param classnews
	 * @return
	 */
	public boolean checkNews(Classnews classnews) {
		if (StringUtils.isBlank(classnews.getNewsTitle())) {
			return addWarnMessage("新闻标题不能为空！");
		}
		if (classnews.getNewsType() == null) {
			return addWarnMessage("请选择新闻类别！");
		}
		return true;
	}

}
