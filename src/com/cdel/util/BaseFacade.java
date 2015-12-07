/**
 * 
 */
package com.cdel.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;

/**
 * 
 * <p>
 * 基于泛型的基础业务接口
 * </p>
 * 
 * @author Du Haiying Create at:2013-8-27 上午11:26:23
 */
public interface BaseFacade<T, PK extends Serializable> {

	/**
	 * 添加实体（默认提供）
	 * 
	 * @param entity
	 */
	public void add(T entity);

	/**
	 * 批量添加实体（默认提供）
	 * 
	 * @param list
	 *            @
	 */
	public void addList(List<T> list);

	/**
	 * 批量添加实体（默认提供）
	 * 
	 * @param list
	 *            @
	 */
	public void addMap(Map<String, Object> map);

	/**
	 * 更新实体（默认提供）
	 * 
	 * @param entity
	 */
	public void update(T entity);

	/**
	 * 批量更新实体（默认提供）
	 * 
	 * @param entity
	 */
	public void update(List<T> list);

	/**
	 * 根据ID删除实体（默认提供）
	 * 
	 * @param pk
	 */
	public void delete(PK pk);

	/**
	 * 删除实体（默认提供）
	 * 
	 * @param pk
	 */
	public void delete(T entity);

	/**
	 * 按不定条件Map 删除实体（默认提供）
	 * 
	 * 
	 * @param map
	 * 
	 */
	public void delete(Map<String, Object> map);

	/**
	 * 按主键查询（默认提供）
	 * 
	 * @param pk
	 * @return
	 */
	public T findByID(PK pk);

	public T findByObject(T entity);

	/**
	 * 默认list查询（默认提供）
	 * 
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity);

	/**
	 * 默认返回list
	 * 
	 * @param entity
	 * @return
	 */
	public List<T> findList(Integer i);

	/**
	 * 默认分页
	 * 
	 * @param entity
	 * @return
	 */
	public LazyDataModel<T> findPage(T entity);

	/**
	 * 默认分页
	 * 
	 * @param entity
	 * @return
	 */
	public LazyDataModel<T> findPage(Map<String, Object> m);

}
