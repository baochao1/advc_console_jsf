package com.cdel.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cdel.database.DbContextHolder;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * <p>
 * 基础的数据库访问实现 (单个泛型DAO)
 * </p>
 * 
 * @author Du Haiying Create at:2013-8-27 上午11:13:20
 */
@SuppressWarnings("serial")
@Repository("baseDaoImpl")
public class BaseDaoImpl<T extends BaseDomain, PK extends Serializable> extends
		SqlSessionDaoSupport implements BaseDao<T, PK>, Serializable {

	@Autowired
	private SqlSession sqlSession;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insert(T entity, String sqlId) {
		this.sqlSession.insert(sqlId, entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <X> void insertSome(X entity, String sqlId) {
		this.sqlSession.insert(sqlId, entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insert(List<T> entityList, String sqlId) {
		this.sqlSession.insert(sqlId, entityList);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(Map<String, ?> map, String sqlId) {
		if (map.get(Contants.DATA_KEY) != null) {
			DbContextHolder.setDbType(map.get(Contants.DATA_KEY).toString());
		}
		this.sqlSession.update(sqlId, map);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <X> void updateSome(X entity, String sqlId) {
		this.sqlSession.update(sqlId, entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(T entity, String sqlId) {
		this.sqlSession.update(sqlId, entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(PK pk, String sqlId) {
		this.sqlSession.delete(sqlId, pk);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(T entity, String sqlId) {
		this.sqlSession.delete(sqlId, entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Map<String, ?> map, String sqlId) {
		this.sqlSession.delete(sqlId, map);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T getByID(PK pk, String sqlId) {
		return (T) this.sqlSession.selectOne(sqlId, pk);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T getByEntity(T t, String sqlId) {
		return (T) this.sqlSession.selectOne(sqlId, t);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <X, Y> X get(Y i, String sqlId) {
		return (X) this.sqlSession.selectOne(sqlId, i);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <X> X get(Map<String, ?> map, String sqlId) {
		if (map.get(Contants.DATA_KEY) != null) {
			DbContextHolder.setDbType(map.get(Contants.DATA_KEY).toString());
		}
		return (X) this.sqlSession.selectOne(sqlId, map);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <Y> int count(Y i, String sqlId) {
		return (Integer) this.sqlSession.selectOne(sqlId, i);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<T> findList(String sqlId) {
		return this.sqlSession.selectList(sqlId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<T> findListByEntity(T t, String sqlId) {
		return this.sqlSession.selectList(sqlId, t);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <Y> List<T> findList(Y y, String sqlId) {
		return this.sqlSession.selectList(sqlId, y);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <X, Y> List<X> findSomeList(Y y, String sqlId) {
		return this.sqlSession.selectList(sqlId, y);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <X> List<X> findList(Map<String, ?> m, String sqlId) {
		if (m.get(Contants.DATA_KEY) != null) {
			DbContextHolder.setDbType(m.get(Contants.DATA_KEY).toString());
		}
		return this.sqlSession.selectList(sqlId, m);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<T> findListByIDs(PK[] m, String sqlId) {
		return this.sqlSession.selectList(sqlId, m);
	}

	@SuppressWarnings("rawtypes")
	public <X> Map findMap(X x, String sqlId, String colName) {
		return this.sqlSession.selectMap(sqlId, x, colName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LazyDataModel<T> findPage(final T entity, final String countSqlId,
			final String listSqlId) {
		LazyDataModel<T> lazyModel = new LazyDataModel<T>() {
			@Override
			public void setRowIndex(int rowIndex) {
				/*
				 * The following is in ancestor (LazyDataModel): this.rowIndex =
				 * rowIndex == -1 ? rowIndex : (rowIndex % pageSize);
				 */
				if (rowIndex == -1 || getPageSize() == 0) {
					super.setRowIndex(-1);
				} else
					super.setRowIndex(rowIndex % getPageSize());
			}

			@Override
			public List<T> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, String> filters) {

				if (pageSize <= 0) {
					pageSize = 10;
				}
				this.setPageSize(pageSize);
				this.setRowIndex(first);
				int count = (Integer) sqlSession.selectOne(countSqlId, entity);
				this.setRowCount(count);

				if (first <= count) {
					entity.setSortField(sortField);
					entity.setSortOrder(sortOrder.name());
					entity.setRowNumStart(first + 1);
					entity.setRowNumEnd(first + pageSize);

					return sqlSession.selectList(listSqlId, entity);
				} else {
					return null;
				}
			}
		};
		return lazyModel;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public <X> LazyDataModel<X> findPage(final Map m, final String countSqlId,
			final String listSqlId) {
		LazyDataModel<X> lazyModel = new LazyDataModel<X>() {
			@Override
			public void setRowIndex(int rowIndex) {
				/*
				 * The following is in ancestor (LazyDataModel): this.rowIndex =
				 * rowIndex == -1 ? rowIndex : (rowIndex % pageSize);
				 */
				if (rowIndex == -1 || getPageSize() == 0) {
					super.setRowIndex(-1);
				} else
					super.setRowIndex(rowIndex % getPageSize());
			}

			@SuppressWarnings("unchecked")
			@Override
			public List<X> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, String> filters) {

				if (m.get(Contants.DATA_KEY) != null) {
					DbContextHolder.setDbType(m.get(Contants.DATA_KEY)
							.toString());
				}

				if (pageSize <= 0) {
					pageSize = 10;
				}
				this.setPageSize(pageSize);
				this.setRowIndex(first);
				int count = (Integer) sqlSession.selectOne(countSqlId, m);
				this.setRowCount(count);

				if (first <= count) {
					if (m != null) {

						m.put("rowNumStart", first + 1);
						m.put("rowNumEnd", first + pageSize);
						m.put("sortField", sortField);
						m.put("sortOrder", sortOrder.name());
					}

					return sqlSession.selectList(listSqlId, m);
				} else {
					return null;
				}
			}
		};
		return lazyModel;
	}

	public SqlSession getCurentSqlSession() {
		return super.getSqlSession();
	}

}
