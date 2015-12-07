package com.cdel.api.callCenter.classes;


import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.cdel.api.callCenter.Constant;
import com.cdel.util.BaseFacadeImpl;

/**
 * 
 * <p>为呼叫中心提供的特定接口 facade层</p>
 * 
 * @author Du Haiying
 * Create at:2013-10-14 下午1:50:48
 */
@SuppressWarnings("serial")
@Service("callCenterClassesFacade")
public class ClassesFacade extends BaseFacadeImpl<com.cdel.advc.classes.domain.Classes, Integer>  {

	
	/**
	 * 根据 classes 例子查询班级 （暂时只支持 班级名称，教师名称条件）
	 * 
	 * @param classes 
	 * 
	 * @return 返回班级ID，班级名称，教师名称，考期名称, 学生人数列表
	 */
	public List<Classes> findClasses(Classes classes){
		
		if (classes == null) {
			throw new IllegalArgumentException("非法参数:参数不能为null!"); 
		}
		if (StringUtils.isBlank(classes.getClassName())) {
			throw new IllegalArgumentException("非法参数:班级名称不能为null!"); 
		}
		if (StringUtils.isBlank(classes.getTeacherName())) {
			throw new IllegalArgumentException("非法参数:教师名称不能为null!"); 
		}
		
		return this.baseDao.findSomeList(classes, Constant.FINDCLASSES);
	}
	/**
	 * 根据  updateTime 最后一次更新  时间 查询班级 信息
	 * @param classes
	 * @return  返回班级ID，考期编号，班级名称，班级编号, 班主任,当前人数,状态,创建时间，修改时间
	 */
	public List<Classes> findClassesByUpdateTime(Classes classes){
		if (classes == null) {
			throw new IllegalArgumentException("非法参数:参数不能为null!"); 
		}
		if (StringUtils.isBlank(classes.getUpdateTime())) {
			throw new IllegalArgumentException("非法参数:班级 最后一次更新时间不能为null!"); 
		}
		Integer  count = classes.getCount();
		if(count == null){
			throw new IllegalArgumentException("非法参数:查询的记录数不能为null!"); 
		}
		if(null == classes.getSiteID()){
			throw new IllegalArgumentException("非法参数:查询的记录数不能为null!"); 
		}
		return this.baseDao.findSomeList(classes, Constant.FINDCLASSESBYUPDATETIME);
	}
}
