package com.cdel.advc.divide.action;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.classes.domain.Classes;
import com.cdel.advc.classes.facade.ClassesFacade;
import com.cdel.advc.divide.domain.Divide;
import com.cdel.advc.divide.facade.DivideFacade;
import com.cdel.advc.memberclass.domain.MemberClass;
import com.cdel.advc.memberclass.facade.MemberClassFacade;
import com.cdel.advc.strategy.domain.Strategy;
import com.cdel.advc.strategy.facade.StrategyFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.ExceptionUtil;

/**
 * 学员分班
 * 
 * @author zhangsulei
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
public class DivideReqAction extends BaseAction<Divide> implements Serializable {
	@ManagedProperty(value = "#{divideFacade}")
	private DivideFacade divideFacade;
	@ManagedProperty(value = "#{memberClassFacade}")
	private MemberClassFacade memberClassFacade;
	@ManagedProperty(value = "#{strategyFacade}")
	private StrategyFacade strategyFacade;
	@ManagedProperty(value = "#{classesFacade}")
	private ClassesFacade classesFacade;

	private Integer newClassID;// 换班后的新classID
	private Integer oldClassID;// 换班前的classID
	private Integer userID;

	/**
	 * 分班
	 */
	public void divide(Integer userID, Integer termID, Integer strategyID,
			String studyCourse, Short strategyType, Integer majorID,
			Short limitNum) {
		MemberClass oldMc = divideFacade.validate(userID, termID, strategyID,
				studyCourse, strategyType, majorID, limitNum);
		byte submitSuccess = 0;// 修改是否成功
		try {
			if (oldMc != null) {
				MemberClass mc = new MemberClass();
				mc.setUserID(userID);
				mc.setTermID(termID);
				mc.setStudyCourse(divideFacade.restStudyCourse(studyCourse));
				if (oldMc.getClassID() == null) {
					oldMc = null;
				}
				divideFacade.addMemberToClass(mc, oldMc,
						this.getCurrentUserID(), this.getCurrentRealName(),
						strategyID, limitNum, majorID);
				submitSuccess = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("userID:" + userID + "学员分班！");
			logger.error(ExceptionUtil.getEMsg(e));
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 退班
	 * 
	 * @param userID
	 * @param classID
	 */
	public void exitClass(Integer userID, Integer classID) {
		byte submitSuccess = 0;// 修改是否成功
		MemberClass mc = new MemberClass();
		mc.setUserID(userID);
		mc.setClassID(classID);
		mc = memberClassFacade.getMemberClass(mc);
		if (mc == null || mc.getStatus() == 0) {
			this.addWarnMessage("info", "学员已不在该班，无法退班！");
		}
		mc.setStatus((short) 0);
		try {
			divideFacade.removeMemberClass(mc, this.getCurrentUserID(),
					this.getCurrentRealName());
			submitSuccess = 1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("userID:" + userID + "学员退班！");
			logger.error(ExceptionUtil.getEMsg(e));
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 显示换班页面
	 * 
	 * @param strategyID
	 */
	public void showChangeClass(Integer userID, Integer strategyID,
			Integer classID) {
		if (strategyID == null) {
			this.addWarnMessage("info", "策略ID为空！");
		}
		Strategy st = strategyFacade.findByID(strategyID);
		if (st == null) {
			this.addWarnMessage("info", "分班异常,策略为空！");
		}
		Classes cs = new Classes();
		cs.setStrategyID(strategyID);
		cs.setStatus(Short.valueOf("1"));
		DivideAction divideAction = (DivideAction) this
				.getViewAction("divideAction");
		divideAction.setClassList(classesFacade.getClassByStrategyID(cs));
		newClassID = classID;
		oldClassID = classID;
		this.userID = userID;
	}

	/**
	 * 换班
	 */
	public void changeClass() {
		byte submitSuccess = 0;// 修改是否成功
		try {
			if (newClassID == null || oldClassID == null) {
				this.addWarnMessage("班级ID为空！");
				return;
			}
			if (newClassID.intValue() == oldClassID.intValue()) {
				this.addWarnMessage("换班数据不准确，两个班ID相同！");
				return;
			}
			Classes newClass = classesFacade.findByID(newClassID);
			if (newClass == null) {
				this.addWarnMessage("新班级为空！");
				return;
			}
			MemberClass mc = new MemberClass();
			mc.setUserID(userID);
			mc.setClassID(newClassID);
			mc = memberClassFacade.getMemberClass(mc);
			if (mc != null && mc.getStatus().intValue() == 1) {
				this.addWarnMessage("学员已在该班！");
				return;
			}
			if (mc != null && mc.getStatus() == 0) {
				memberClassFacade.delete(mc);
			}
			int numOfPeople = 0;
			if (newClass.getCurrCount() != null) {
				numOfPeople = newClass.getCurrCount().intValue();
			}
			if (newClass.getLimitNum() == null) {
				this.addWarnMessage("未找到该班的限制人数！");
				return;
			}
			if (numOfPeople >= newClass.getLimitNum().intValue()) {
				this.addWarnMessage("班级人数已满！");
				return;
			}
			int classSequence = memberClassFacade.getMemberInClassSeq(
					newClassID, newClass.getLimitNum());
			if (classSequence == -1) {
				this.addWarnMessage("不能找到班内空闲的座位！");
				return;
			}
			mc = new MemberClass();
			mc.setUserID(userID);
			mc.setClassID(oldClassID);
			mc.setNewClassID(newClassID);
			mc.setSequence(classSequence);
			mc.setClassName(newClass.getClassName());
			divideFacade.changeMemberClass(mc, this.getCurrentUserID(),
					this.getCurrentRealName());
			submitSuccess = 1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("userID:" + userID + "学员换班！");
			logger.error(ExceptionUtil.getEMsg(e));
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public void setDivideFacade(DivideFacade divideFacade) {
		this.divideFacade = divideFacade;
	}

	public void setMemberClassFacade(MemberClassFacade memberClassFacade) {
		this.memberClassFacade = memberClassFacade;
	}

	public void setStrategyFacade(StrategyFacade strategyFacade) {
		this.strategyFacade = strategyFacade;
	}

	public void setClassesFacade(ClassesFacade classesFacade) {
		this.classesFacade = classesFacade;
	}

	public Integer getNewClassID() {
		return newClassID;
	}

	public void setNewClassID(Integer newClassID) {
		this.newClassID = newClassID;
	}

	public Integer getOldClassID() {
		return oldClassID;
	}

	public void setOldClassID(Integer oldClassID) {
		this.oldClassID = oldClassID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

}
