/*
 * @Title: MembermsgPostAction.java
 * @Package com.cdel.advc.membermsg.action
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-7-12 上午9:26:51
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-7-12                          
 */
package com.cdel.advc.membermsg.action;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.membermsg.domain.MembermsgPost;
import com.cdel.advc.membermsg.facade.MembermsgPostFacade;
import com.cdel.advc.teacher.domain.Teacher;
import com.cdel.util.BaseAction;

/**
 * <p>
 * 过期消息（学员-教师间消息）查询 bean
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-12 上午9:26:51
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MembermsgPostAction extends BaseAction<MembermsgPost> implements
		Serializable {

	@ManagedProperty("#{membermsgPostFacade}")
	private MembermsgPostFacade membermsgPostFacade;

	/** DataTable组件分页模型 */
	private LazyDataModel<MembermsgPost> page;
	/** 查询条件 */
	private MembermsgPost filterMembermsg = new MembermsgPost();

	@PostConstruct
	public void initMembermsgPostAction() {
		Integer userID = this.getCurrentUserID();
		Teacher teacher = this.getCurrentTeacher();
		Integer orgID = teacher.getOrgID();
		Short isHeader = teacher.getIsHeader();
		if (isHeader == 0) {// 超管
			filterMembermsg.setOrgID(null);
		} else if (isHeader == 1 || isHeader == 4) {// 经理
			filterMembermsg.setOrgID(null);
		} else if (isHeader == 2) {// 组长
			filterMembermsg.setOrgID(orgID);
		} else {
			filterMembermsg.setTeacherID(userID);
		}
		this.page = this.membermsgPostFacade.findPage(filterMembermsg);
	}

	/**
	 * 条件查询，强制设置 分页 从索引0位置 开始查询
	 */
	public void search() {
		pageTable.setFirst(0);
	}

	public LazyDataModel<MembermsgPost> getPage() {
		return page;
	}

	public void setPage(LazyDataModel<MembermsgPost> page) {
		this.page = page;
	}

	public MembermsgPost getFilterMembermsg() {
		return filterMembermsg;
	}

	public void setFilterMembermsg(MembermsgPost filterMembermsg) {
		this.filterMembermsg = filterMembermsg;
	}

	public void setMembermsgPostFacade(MembermsgPostFacade membermsgPostFacade) {
		this.membermsgPostFacade = membermsgPostFacade;
	}

}
