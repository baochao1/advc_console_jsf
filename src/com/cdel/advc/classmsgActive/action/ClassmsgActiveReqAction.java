package com.cdel.advc.classmsgActive.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.cdel.advc.classes.action.ClassesOther;
import com.cdel.advc.classes.facade.ClassesFacade;
import com.cdel.advc.classmsgActive.domain.ClassmsgActive;
import com.cdel.advc.classmsgActive.facade.ClassmsgActiveFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;

@SuppressWarnings("serial")
@ManagedBean
public class ClassmsgActiveReqAction extends BaseAction<ClassmsgActive>
		implements Serializable {
	@ManagedProperty(value = "#{classmsgActiveFacade}")
	private ClassmsgActiveFacade classmsgActiveFacade;
	@ManagedProperty(value = "#{classesFacade}")
	private ClassesFacade classesFacade;

	private ClassmsgActive updateClassmsgActive = new ClassmsgActive();
	private byte flag = -1;// 添加/编辑状态,0添加,1编辑
	private Integer[] classIDs;

	/**
	 * 修改状态
	 */
	public void changeStatus(Integer classMsgID, Integer status) {
		try {
			classmsgActiveFacade.changeStatus(classMsgID, status);
			this.addInfoMessage("info", SUCESSINFO);
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
		}
	}

	/**
	 * 添加活动
	 */
	public void add() {
		flag = 0;
		ClassmsgActiveAction cma = (ClassmsgActiveAction) this
				.getViewAction("classmsgActiveAction");
		if (cma.getFrom().equals("")) {
			ClassesOther cother = (ClassesOther) this
					.getViewAction("classesOther");
			cother.setClassList4Update(null);
		} else {
			updateClassmsgActive.setClassID(cma.getClassID());
			updateClassmsgActive.setClassName(classesFacade.findByID(
					cma.getClassID()).getClassName());
		}
	}

	/**
	 * 编辑活动
	 */
	public void update(Integer classMsgID) {
		flag = 1;
		updateClassmsgActive = classmsgActiveFacade.findByID(classMsgID);
	}

	/**
	 * 添加修改活动提交
	 */
	/**
	 */
	public void updateSubmit() {
		byte submitSuccess = 0;// 修改是否成功
		ClassmsgActiveAction classmsgActiveAction = (ClassmsgActiveAction) this
				.getViewAction("classmsgActiveAction");
		if (classmsgActiveAction.getFrom().equals("class")) {
			classIDs = new Integer[] { updateClassmsgActive.getClassID() };
		}
		if (classmsgActiveFacade.checkClassmsgActive(flag,
				updateClassmsgActive, classIDs)) {
			try {
				if (flag == 0) {
					List<ClassmsgActive> list = new ArrayList<ClassmsgActive>();
					ClassmsgActive ca;
					for (int i = 0; i < classIDs.length; i++) {
						ca = new ClassmsgActive();
						ca.setMsgTitle(updateClassmsgActive.getMsgTitle());
						ca.setMsgContent(updateClassmsgActive.getMsgContent());
						ca.setCreator(this.getCurrentUserID());
						ca.setCreateTime(new Date());
						ca.setStatus(Short.valueOf("1"));
						ca.setMsgType(Short.valueOf("3"));
						ca.setClassID(classIDs[i]);
						list.add(ca);
					}
					classmsgActiveFacade.addList(list);
					classmsgActiveAction.search();
				} else {
					classmsgActiveFacade.update(updateClassmsgActive);
					classmsgActiveAction.search4U();
				}
				submitSuccess = 1;
			} catch (Exception e) {
				e.printStackTrace();
				submitSuccess = -1;
			}
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public Map<Short, String> getStatus() {
		return Contants.status;
	}

	public byte getFlag() {
		return flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

	public void setClassmsgActiveFacade(
			ClassmsgActiveFacade classmsgActiveFacade) {
		this.classmsgActiveFacade = classmsgActiveFacade;
	}

	public ClassmsgActive getUpdateClassmsgActive() {
		return updateClassmsgActive;
	}

	public void setUpdateClassmsgActive(ClassmsgActive updateClassmsgActive) {
		this.updateClassmsgActive = updateClassmsgActive;
	}

	public Integer[] getClassIDs() {
		return classIDs;
	}

	public void setClassIDs(Integer[] classIDs) {
		this.classIDs = classIDs;
	}

	public void setClassesFacade(ClassesFacade classesFacade) {
		this.classesFacade = classesFacade;
	}

}
