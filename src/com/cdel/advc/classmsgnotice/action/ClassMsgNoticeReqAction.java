package com.cdel.advc.classmsgnotice.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.cdel.advc.classes.action.ClassesOther;
import com.cdel.advc.classes.domain.Classes;
import com.cdel.advc.classes.facade.ClassesFacade;
import com.cdel.advc.classmsgnotice.domain.ClassMsgNotice;
import com.cdel.advc.classmsgnotice.facade.ClassMsgNoticeFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;
import com.cdel.util.ExceptionUtil;

@SuppressWarnings("serial")
@ManagedBean
public class ClassMsgNoticeReqAction extends BaseAction<ClassMsgNotice>
		implements Serializable {
	@ManagedProperty(value = "#{classMsgNoticeFacade}")
	private ClassMsgNoticeFacade classMsgNoticeFacade;
	@ManagedProperty(value = "#{classesFacade}")
	private ClassesFacade classesFacade;

	private ClassMsgNotice updateClassMsgNotice = new ClassMsgNotice();
	private List<Classes> classList;// 班级List
	private Integer[] classIDs;

	/**
	 * 修改状态
	 */
	public void changeStatus(Integer classMsgID, Integer status) {
		try {
			classMsgNoticeFacade.changeStatus(classMsgID, status);
			this.addInfoMessage("info", SUCESSINFO);
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
		}
	}

	/**
	 * 添加公告
	 */
	public void add() {
		ClassMsgNoticeAction cma = (ClassMsgNoticeAction) this
				.getViewAction("classMsgNoticeAction");
		if (cma.getFrom().equals("")) {
			ClassesOther cother = (ClassesOther) this
					.getViewAction("classesOther");
			cother.setClassList4Update(null);
		} else {
			updateClassMsgNotice.setClassID(cma.getClassID());
			updateClassMsgNotice.setClassName(classesFacade.findByID(
					cma.getClassID()).getClassName());
		}
	}

	/**
	 * 编辑公告
	 */
	public void update(Integer classMsgID) {
		updateClassMsgNotice = classMsgNoticeFacade.findByID(classMsgID);
	}

	/**
	 * 修改公告提交 
	 */
	public void updateSubmit(byte flag) {
		byte submitSuccess = 0;// 修改是否成功
		if ((classIDs == null || classIDs.length == 0)
				&& null != updateClassMsgNotice.getClassID()) {
			classIDs = new Integer[1];
			classIDs[0] = updateClassMsgNotice.getClassID();
		}

		if (classMsgNoticeFacade.checkClassNotice(flag, updateClassMsgNotice,
				classIDs)) {

			try {
				ClassMsgNoticeAction classMsgNoticeAction = (ClassMsgNoticeAction) this
						.getViewAction("classMsgNoticeAction");
				Date date = new Date();
				if (flag == 0) {
					List<ClassMsgNotice> list = new ArrayList<ClassMsgNotice>();
					ClassMsgNotice cm;
					for (Integer classID : classIDs) {
						cm = new ClassMsgNotice();
						cm.setMsgTitle(updateClassMsgNotice.getMsgTitle());
						cm.setMsgContent(updateClassMsgNotice.getMsgContent());
						cm.setCreator(this.getCurrentUserID());
						cm.setCreateTime(date);
						cm.setStatus(Short.valueOf("1"));
						cm.setMsgType(Short.valueOf("1"));
						cm.setClassID(classID);
						list.add(cm);
					}
					classMsgNoticeFacade.addList(list);
					classMsgNoticeAction.search();
				} else {
					classMsgNoticeFacade.update(updateClassMsgNotice);
					classMsgNoticeAction.search4Update();
				}
				// 不管新增 还是修改都要 进行 更新 时间
				if (flag == 0) {
					for (Integer classID : classIDs) {
						classesFacade.updateMsgTime(Short.valueOf("1"), date,
								classID);
					}
				} else {
					classesFacade.updateMsgTime(Short.valueOf("1"), date,
							updateClassMsgNotice.getClassID());
				}
				submitSuccess = 1;
			} catch (Exception e) {
				e.printStackTrace();
				submitSuccess = -1;
				logger.error("updateClassMsgNotice=" + updateClassMsgNotice);
				logger.error(ExceptionUtil.getEMsg(e));
			}
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public Map<Short, String> getMsgTypeMap() {
		return Contants.classMsgType;
	}

	public void setClassMsgNoticeFacade(
			ClassMsgNoticeFacade classMsgNoticeFacade) {
		this.classMsgNoticeFacade = classMsgNoticeFacade;
	}

	public Map<Short, String> getStatus() {
		return Contants.status;
	}

	public ClassMsgNotice getUpdateClassMsgNotice() {
		return updateClassMsgNotice;
	}

	public void setUpdateClassMsgNotice(ClassMsgNotice updateClassMsgNotice) {
		this.updateClassMsgNotice = updateClassMsgNotice;
	}

	public List<Classes> getClassList() {
		return classList;
	}

	public void setClassList(List<Classes> classList) {
		this.classList = classList;
	}

	public void setClassesFacade(ClassesFacade classesFacade) {
		this.classesFacade = classesFacade;
	}

	public Integer[] getClassIDs() {
		return classIDs;
	}

	public void setClassIDs(Integer[] classIDs) {
		this.classIDs = classIDs;
	}

}
