/*
 * 
 */
package com.cdel.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;

import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 
 * <p>
 * 基础的数据库访问接口 (单个泛型DAO)
 * </p>
 * 
 * @author Du Haiying Create at:2013-8-27 上午11:14:52
 */

public interface BaseDao<T extends BaseDomain, PK extends Serializable> {
	
	
	/**
	 * 添加
	 * 
	 * @param entity
	 * @param sqlId
	 */
	public void insert(T entity, String sqlId);

	/**
	 * 不定参数 添加
	 * 
	 * @param entity
	 * @param sqlId
	 */
	public <X> void insertSome(X entity, String sqlId);

	/**
	 * 批量添加
	 * 
	 * @param entity
	 * @param sqlId
	 */
	public void insert(List<T> entityList, String sqlId);

	/**
	 * 更改
	 * 
	 * @param entity
	 * @param sqlId
	 */
	public void update(T entity, String sqlId);
	/**
	 * 更改
	 * 
	 * @param entity
	 * @param sqlId
	 */
	public <X> void updateSome(X entity, String sqlId);
	/**
	 * 不定参数 更改
	 * 
	 * @param entity
	 * @param sqlId
	 */
	public void update(Map<String, ?> map, String sqlId);

	/**
	 * 根据ID 删除
	 * 
	 * @param pk
	 * @param sqlId
	 */
	public void delete(PK pk, String sqlId);

	/**
	 * 按当前实体 删除
	 * 
	 * @param entity
	 * @param sqlId
	 */
	public void delete(T entity, String sqlId);

	/**
	 * 不定参数 删除
	 * 
	 * @param entity
	 * @param sqlId
	 */
	public void delete(Map<String, ?> map, String sqlId);

	/**
	 * 按主键查找，返回单个对象
	 * 
	 * @param pk
	 * @param sqlId
	 * @return
	 */
	public T getByID(PK pk, String sqlId);

	/**
	 * 按当前实体，返回单个对象
	 * 
	 * @param t
	 * @param sqlId
	 * @return
	 */
	public T getByEntity(T t, String sqlId);

	/**
	 * 不定单个参数，返回单个不定实体
	 * 
	 * @param i
	 * @param sqlId
	 * @return
	 */
	public <X, Y> X get(Y i, String sqlId);

	/**
	 * 不定参数， 返回单个不定实体
	 * 
	 * @param map
	 * @param sqlId
	 * @return
	 */
	public <X> X get(Map<String, ?> map, String sqlId);

	/**
	 * 不定参数， 返回个数
	 * 
	 * @param i
	 * @param sqlId
	 * @return
	 */
	public <Y> int count(Y i, String sqlId);

	/**
	 * 无参， 返回 list对象
	 * 
	 * @param sqlId
	 * @return
	 */
	public List<T> findList(String sqlId);

	/**
	 * 按对象查找，返回list对象
	 * 
	 * @param t
	 * @param sqlId
	 * @return
	 */
	public List<T> findListByEntity(T t, String sqlId);

	/**
	 * 根据主键数组查询，返回list对象
	 * 
	 * @param ids
	 * @param sqlId
	 * @return
	 */
	public List<T> findListByIDs(PK[] ids, String sqlId);

	/**
	 * 按不定单个参数查找，返回list对象
	 * 
	 * @param y
	 * @param sqlId
	 * @return
	 */
	public <Y> List<T> findList(Y y, String sqlId);

	/**
	 * 按不定单个参数查找，返回不定对象
	 * 
	 * @param y
	 * @param sqlId
	 * @return
	 */
	public <X, Y> List<X> findSomeList(Y y, String sqlId);

	/**
	 * 按 不定参数查找，返回list不定实体
	 * 
	 * @param map
	 * @param sqlId
	 * @return
	 */
	public <X> List<X> findList(Map<String, ?> map, String sqlId);

	@SuppressWarnings("rawtypes")
	public <X> Map findMap(X x, String sqlId, String colName);

	/**
	 * 按当前实体 分页查询
	 * 
	 * @param entity
	 * @return
	 */
	public LazyDataModel<T> findPage(T entity, String countSqlId,
			String listSqlId);

	/**
	 * 按不定参数 分页查询，返回不定实体
	 * 
	 * @param map
	 * @return
	 */

	@SuppressWarnings("rawtypes")
	public <X> LazyDataModel<X> findPage(Map map, String countSqlId,
			String listSqlId);

}
