package com.cdel.advc.adminTeacher.action;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UISelectOne;
import javax.faces.event.AjaxBehaviorEvent;

import com.cdel.advc.adminTeacher.domain.AdminStatis;
import com.cdel.advc.adminTeacher.domain.Organize;
import com.cdel.advc.adminTeacher.faceade.OrganizeFacade;
import com.cdel.advc.classes.domain.AllClasses;
import com.cdel.util.BaseAction;

/**
 * 部门action 
 * @author xuxiaoguang
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class OrganizeAction  extends BaseAction<Organize> implements Serializable {
	
	@ManagedProperty(value = "#{organizeFacade}")
	private OrganizeFacade organizeFacade;

	private List<Organize> organizeList;// 部门List
	
	private List<Organize> groupList;// 小组列表
	

	public OrganizeFacade getOrganizeFacade() {
		return organizeFacade;
	}


	public void setOrganizeFacade(OrganizeFacade organizeFacade) {
		this.organizeFacade = organizeFacade;
	}


	public List<Organize> getOrganizeList() {
		return organizeList;
	}


	public void setOrganizeList() {
		Organize organize = new Organize();
		this.organizeList = organizeFacade.showList(organize); 
	}

	/**
	 * 根据部门信息取小组列表
	 * @param e
	 */
	public void changeOrgID(AjaxBehaviorEvent e) {
		Integer orgID = (Integer) ((UISelectOne) e.getSource()).getValue();
		groupList = organizeFacade.getGroupList(orgID);
	}


	public List<Organize> getGroupList() {
		return groupList;
	}
}
