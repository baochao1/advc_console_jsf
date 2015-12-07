/*
 * @Title: ClassnewsAction.java
 * @Package com.cdel.advc.classnews.action
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-7-4 下午5:49:35
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-7-4                          
 */
package com.cdel.advc.classnews.action;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.classnews.domain.Classnews;
import com.cdel.advc.classnews.facade.ClassnewsFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;
import com.cdel.util.ExceptionUtil;

/**
 * <p>
 * 班级动态 bean
 * </p>
 * 
 * @author 张苏磊
 */
@SuppressWarnings("serial")
@ManagedBean
public class ClassnewsReqAction extends BaseAction<Classnews> implements
		Serializable {
	@ManagedProperty("#{classnewsFacade}")
	private ClassnewsFacade classnewsFacade;

	private byte flag = -1;// 添加/编辑状态,0添加,1编辑
	/** 添加，编辑 bean */
	private Classnews classnews = new Classnews();
	private byte submitSuccess = 0;// 修改是否成功

	/**
	 * 显示添加页面同时预处理
	 */
	public void showAdd() {
		flag = 0;
		this.classnews = new Classnews();
		ClassnewsAction cnews = (ClassnewsAction) this
				.getViewAction("classnewsAction");
		this.classnews.setClassID(cnews.getClassID());
	}

	/**
	 * 修改状态
	 */
	public void changeStatus(Integer classNewsID, Integer status) {
		Classnews c = new Classnews();
		c.setClassNewsID(classNewsID);
		Short newStatus;
		if (status == 1) {
			newStatus = 0;
		} else {
			newStatus = 1;
		}
		c.setStatus(newStatus);
		try {
			classnewsFacade.update(c);
			this.addInfoMessage("info", SUCESSINFO);
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
		}
	}

	/**
	 * 显示修改页面
	 */
	public void update(Integer id) {
		flag = 1;
		this.classnews = this.classnewsFacade.findByID(id);
	}

	/**
	 * 添加/修改动态
	 * 
	 * @throws Exception
	 */
	public void updateSubmit(byte flag) throws Exception {
		ClassnewsAction cnews = (ClassnewsAction) this
				.getViewAction("classnewsAction");
		try {
			if (classnewsFacade.checkNews(classnews)) {
				if (flag == 0) {
					this.classnews.setCreator(this.getCurrentUserID());
					this.classnews.setStatus((short) 1);
					this.classnews.setCreateTime(new Date());
					this.classnewsFacade.add(classnews);
					cnews.search();
				} else {
					this.classnewsFacade.update(classnews);
					cnews.search4U();
				}
				submitSuccess = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtil.getEMsg(e));
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/** 新闻动态类别 */
	public Map<Short, String> getTypes() {
		return Contants.classnewsType;
	}

	/** 状态 */
	public Map<Short, String> getStatuss() {
		return Contants.status;
	}

	public Classnews getClassnews() {
		return classnews;
	}

	public void setClassnews(Classnews classnews) {
		this.classnews = classnews;
	}

	public byte getFlag() {
		return flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

	public void setClassnewsFacade(ClassnewsFacade classnewsFacade) {
		this.classnewsFacade = classnewsFacade;
	}

}
