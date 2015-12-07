/**
 * 
 */
package com.cdel.util;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 
 * <p>
 * 基于泛型的基础业务实现
 * </p>
 * 
 * @author Du Haiying Create at:2013-8-27 下午2:16:10
 */
@SuppressWarnings("serial")
public abstract class BaseFacadeImpl<T extends BaseDomain, PK extends Serializable>
		implements BaseFacade<T, PK>, Serializable {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	protected BaseDao<T, PK> baseDao;

	private final static String INSERT = "insert";

	private final static String UPDATE = "update";

	private final static String DELETE = "delete";

	private final static String GET = "get";

	private final static String FINDPAGE = "findPage";

	private final static String COUNT = "count";

	private Class<T> entityClass;

	private String nameSpacePerfix;

	private String className;

	@SuppressWarnings("unchecked")
	public BaseFacadeImpl() {
		this.entityClass = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		className = this.entityClass.getSimpleName();
		this.nameSpacePerfix = className.toLowerCase() + ".";
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public void add(T entity) {
		this.baseDao.insert(entity, nameSpacePerfix + INSERT + className);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addList(List<T> list) {
		if (list != null && list.size() > 0)
			this.baseDao.insert(list, nameSpacePerfix + INSERT + className
					+ "List");
	}

	@Override
	public void addMap(Map<String, Object> map) {
		if (map != null && map.size() > 0)
			this.baseDao.insertSome(map, nameSpacePerfix + INSERT + className
					+ "Map");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(T entity) {
		this.baseDao.update(entity, nameSpacePerfix + UPDATE + className);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(List<T> list) {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				this.baseDao.update(list.get(i), nameSpacePerfix + UPDATE
						+ className);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(PK id) {
		this.baseDao.delete(id, nameSpacePerfix + DELETE + className);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(T entity) {
		this.baseDao.delete(entity, nameSpacePerfix + DELETE + className
				+ "ByObject");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Map<String, Object> map) {
		this.baseDao.delete(map, nameSpacePerfix + DELETE + className + "Map");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T findByID(PK id) {
		return (T) this.baseDao.getByID(id, nameSpacePerfix + GET + className);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T findByObject(T entity) {
		return (T) this.baseDao.getByEntity(entity, nameSpacePerfix + GET
				+ className + "ByObject");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<T> findList(T entity) {
		return this.baseDao.findListByEntity(entity, nameSpacePerfix + GET
				+ className + "List");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<T> findList(Integer i) {
		return this.baseDao.findList(i, nameSpacePerfix + GET + className
				+ "List4I");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LazyDataModel<T> findPage(final T entity) {
		return this.baseDao.findPage(entity, nameSpacePerfix + COUNT
				+ className, nameSpacePerfix + FINDPAGE + className);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LazyDataModel<T> findPage(Map<String, Object> map) {
		return this.baseDao.findPage(map, nameSpacePerfix + COUNT + className,
				nameSpacePerfix + FINDPAGE + className);
	}

	protected boolean addWarnMessage(String info) {
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_WARN);
		MessageUtil
				.addMessage(message, FacesContext.getCurrentInstance(), info);
		return false;
	}

	protected boolean addWarnMessage(String id, String info) {
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_WARN);
		MessageUtil.addMessage(id, message, FacesContext.getCurrentInstance(),
				info);
		return false;
	}

}
