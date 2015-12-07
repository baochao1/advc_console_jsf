/*
 * @Title: ClassesAction.java
 * @Package com.cdel.advc.classes.action
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-7-2 上午11:10:39
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-7-2                          
 */
package com.cdel.advc.classes.action;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UISelectOne;
import javax.faces.event.AjaxBehaviorEvent;

import com.cdel.advc.classes.domain.AllClasses;
import com.cdel.advc.classes.domain.Classes;
import com.cdel.advc.classes.facade.ClassesFacade;
import com.cdel.util.BaseAction;

/**
 * 
 * <p>
 * 总班级管理查询相关bean
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-19 下午2:32:57
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClassesOther extends BaseAction<AllClasses> {

	@ManagedProperty("#{classesFacade}")
	private ClassesFacade classesFacade;

	private List<Classes> classList;// 用于查询的班级List
	private List<Classes> classList4Update;// 用于更新的班级List

	public List<Classes> getClassList() {
		return classList;
	}

	public List<Classes> getClassList4Update() {
		return classList4Update;
	}

	public void setClassList4Update(List<Classes> classList4Update) {
		this.classList4Update = classList4Update;
	}

	public void setClassesFacade(ClassesFacade classesFacade) {
		this.classesFacade = classesFacade;
	}

	/**
	 * 根据辅导的修改取班级信息
	 * 
	 * @param e
	 */
	public void changeMajorID(AjaxBehaviorEvent e) {
		Integer majorID = (Integer) ((UISelectOne) e.getSource()).getValue();
		String id = ((UISelectOne) e.getSource()).getId();
		if (id.equals("sMajorID")) {
			classList = classesFacade.getClassesListByMajorID(majorID);
		} else if (id.equals("uMajorID4Status")) {
			classList4Update = classesFacade.getClassesListByMajorID4Status(majorID);
		} else {
			classList4Update = classesFacade.getClassesListByMajorID(majorID);
		}
	}

}
