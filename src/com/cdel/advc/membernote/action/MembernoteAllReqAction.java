/*
 * @Title: MembernoteAction.java
 * @Package com.cdel.advc.membernote.action
 * @Description: TODO
 * @author 张苏磊
 * @date 2013-7-4 下午4:01:13
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-7-4                          
 */
package com.cdel.advc.membernote.action;

import java.io.Serializable;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.membernote.domain.Membernote;
import com.cdel.advc.membernote.domain.MembernoteAll;
import com.cdel.advc.membernote.facade.MembernoteFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;

/**
 * <p>
 * 全部班级留言管理 bean
 * </p>
 * 
 * @author 张苏磊
 */
@SuppressWarnings("serial")
@ManagedBean
public class MembernoteAllReqAction extends BaseAction<MembernoteAll> implements
		Serializable {
	@ManagedProperty("#{membernoteFacade}")
	private MembernoteFacade membernoteFacade;

	/**
	 * 查看留言
	 */
	private Membernote note = new Membernote();

	/**
	 * 修改状态
	 */
	public void changeStatus(Integer noteID, Integer status) {
		MembernoteAll m = new MembernoteAll();
		m.setNoteID(noteID);
		Short newStatus;
		if (status == 1) {
			newStatus = 0;
		} else {
			newStatus = 1;
		}
		m.setStatus(newStatus);
		try {
			membernoteFacade.update(m);
			this.addInfoMessage("info", SUCESSINFO);
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
		}
	}

	/**
	 * 修改置顶
	 */
	public void changeTop(Integer noteID, Integer isTop) {
		MembernoteAll m = new MembernoteAll();
		m.setNoteID(noteID);
		Short newIsTop;
		if (isTop == 1) {
			newIsTop = 0;
		} else {
			newIsTop = 1;
		}
		m.setIsTop(newIsTop);
		try {
			membernoteFacade.updateMembernoteIsTop(m);
			this.addInfoMessage("info", SUCESSINFO);
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
		}
	}

	/**
	 * 查看留言
	 */
	public void showNote(Integer noteID) {
		if (noteID != null) {
			this.note = membernoteFacade.findByID(noteID);
		}
	}

	/**
	 * 修改解决状态
	 */
	public void updateSolveSubmit() {
		MembernoteAllAction ma = (MembernoteAllAction) this
				.getViewAction("membernoteAllAction");
		note = ma.getPage().getRowData();
		try {
			Short issolve = note.getIsSolve();
			if (issolve != null && issolve == 0) {
				note.setIsSolve(new Short("1"));
			}
			membernoteFacade.update(note);
			if (issolve != null && issolve == 0) {
				this.updateComponent("searchForm:membernoteTable");
			}
			this.addInfoMessage("info", SUCESSINFO);
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
		}
	}

	/** 消息类别 */
	public Map<Short, String> getTypes() {
		return Contants.membernoteType;
	}

	/** 状态 */
	public Map<Short, String> getStatuss() {
		return Contants.status;
	}

	/** 是否解决 */
	public Map<Short, String> getIsSolves() {
		return Contants.membernoteIsSolve;
	}

	public void setMembernoteFacade(MembernoteFacade membernoteFacade) {
		this.membernoteFacade = membernoteFacade;
	}

	public Membernote getNote() {
		return note;
	}

}
