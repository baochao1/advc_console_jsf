/*
 * @Title: ClassesReqAction.java
 * @Package com.cdel.advc.classes.action
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-7-3 下午3:24:52
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-7-3                          
 */
package com.cdel.advc.classes.action;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.apache.commons.lang.StringUtils;
import com.cdel.advc.classes.domain.Classes;
import com.cdel.advc.classes.facade.ClassesFacade;
import com.cdel.advc.classteacher.facade.ClassteacherFacade;
import com.cdel.advc.memberclass.facade.MemberClassFacade;
import com.cdel.advc.teacher.action.TeacherOther;
import com.cdel.advc.teacher.domain.Teacher;
import com.cdel.advc.teacher.facade.TeacherFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;
import com.cdel.util.ExceptionUtil;
import com.cdel.util.FilterSql;

/**
 * <p>
 * 班级管理--编辑操作bean
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-3 下午3:24:52
 */
@SuppressWarnings("serial")
@ManagedBean
public class ClassesReqAction extends BaseAction<Classes> implements
		Serializable {

	@ManagedProperty("#{classesFacade}")
	private ClassesFacade classesFacade;
	@ManagedProperty("#{memberClassFacade}")
	private MemberClassFacade memberClassFacade;
	@ManagedProperty("#{teacherFacade}")
	private TeacherFacade teacherFacade;
	@ManagedProperty("#{classteacherFacade}")
	private ClassteacherFacade classteacherFacade;

	private Classes classes = new Classes();
	private String userName;// 批量添加学员
	private byte classflag;// =0全部班级,=1自定义班级
	private byte submitSuccess = 0;// 修改是否成功
	private String oldDisplayAdviser;// 原班主任

	/**
	 * 查看班级
	 * 
	 * @param: flag=0全部班级,1自定义班级
	 */
	public void showClass(Integer classID, byte classflag) {
		this.classflag = classflag;
		this.classes = this.classesFacade.findByID(classID);
		classes.setAdviserTeacherCodeStr(classes.getDisplayAdviser());
		oldDisplayAdviser = classes.getAdviserTeacherCode();
		// 查询老师
		TeacherOther tother = (TeacherOther) this.getViewAction("teacherOther");
		tother.setTeacherList();
	}

	/**
	 * 修改班级口号
	 * 
	 * @throws Exception
	 */
	public void updateClassDesc() {
		ClassesAction ca = (ClassesAction) this.getViewAction("classesAction");
		classes = ca.getPage().getRowData();
		try {
			this.classesFacade.update(classes);
			this.addInfoMessage("info", SUCESSINFO);
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
			logger.error(ExceptionUtil.getEMsg(e));
		}
	}

	/**
	 * 更新状态
	 * 
	 * @throws Exception
	 */
	public void changeStatus(Integer classID, Short status) throws Exception {

		if (classID != null && status != null) {
			this.classes = new Classes();
			this.classes.setClassID(classID);
			if (status == 1) {
				this.classes.setStatus((short) 0);
			} else {
				this.classes.setStatus((short) 1);
			}

			this.classesFacade.update(classes);

			this.addMessage("info", "设置成功！");
		}
	}

	/**
	 * 更新班级
	 * 
	 * @throws Exception
	 */
	public void updateClasses() {
		if (classes == null || classes.getClassID() == null
				|| StringUtils.isBlank(classes.getClassName())
				|| StringUtils.isBlank(classes.getClassCode())) {
			this.addWarnMessage("info", "非法参数！");
			return;
		}
		try {
			if (classesFacade.checkClass((byte) 0, classes)) {
				String tCode = classes.getAdviserTeacherCode();
				Teacher t = new Teacher();
				if (StringUtils.isNotBlank(tCode) && !tCode.equals("-1")) {
					t = teacherFacade.getTeacherByCode2(tCode);
				}
				classes.setDisplayAdviserStr(tCode, t.getTeacherName());
				this.classesFacade.update(classes);
				if (StringUtils.isBlank(oldDisplayAdviser)) {
					// 把班主任添加为本班管理员
					classteacherFacade.addTC(t.getTeacherID(),
							classes.getClassID(), (short) 0);
				} else {
					if (StringUtils.isBlank(t.getTeacherCode())) {
						// 删除班主任
						t = teacherFacade.getTeacherByCode2(oldDisplayAdviser);
						classteacherFacade.deleteTC(t.getTeacherID(),
								classes.getClassID());
					}
					if (!oldDisplayAdviser.equals(t.getTeacherCode())) {
						// 换班主任
						classteacherFacade.addTC(t.getTeacherID(),
								classes.getClassID(), (short) 0);
						t = teacherFacade.getTeacherByCode2(oldDisplayAdviser);
						classteacherFacade.deleteTC(t.getTeacherID(),
								classes.getClassID());
					}
				}

				if (classflag == 0) {
					AllClassesAction aClass = (AllClassesAction) this
							.getViewAction("allClassesAction");
					aClass.search4U();
				} else {
					UserDefineClassesAction udaction = (UserDefineClassesAction) this
							.getViewAction("userDefineClassesAction");
					udaction.search4U();
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

	/**
	 * 
	 * 更新满员状态
	 * 
	 * @param classedID
	 * 
	 * @param hasFull
	 * @throws Exception
	 */
	public void updateHasFull(Integer classID, Short hasFull) throws Exception {
		if (classID != null && hasFull != null) {
			this.classes.setClassID(classID);
			if (hasFull == 1) {
				this.classes.setHasFull((short) 0);
			} else {
				this.classes.setHasFull((short) 1);
			}
			this.classesFacade.update(classes);
			this.addMessage("info", "设置成功！");
		}
	}

	/**
	 * 显示班级批量添加学员（自定义目前）页面
	 * 
	 * @throws Exception
	 */
	public void showAddMembers(Integer classID) throws Exception {
		UserDefineClassesAction udaction = (UserDefineClassesAction) this
				.getViewAction("userDefineClassesAction");
		udaction.setClassID(classID);
	}

	/**
	 * 班级批量添加学员（自定义目前）
	 * 
	 * @throws Exception
	 */
	public void addMembers(Integer classID) throws Exception {
		if (classID == null || StringUtils.isBlank(userName)) {
			this.addWarnMessage("classID或userName为空！");
			return;
		}
		userName = FilterSql.isValid(userName);
		try {
			UserDefineClassesAction udaction = (UserDefineClassesAction) this
					.getViewAction("userDefineClassesAction");
			if (this.memberClassFacade.addClassMembers(classID, userName,
					udaction.getSiteID()) == 1) {
				submitSuccess = 1;
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			logger.error(ExceptionUtil.getEMsg(e));
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 显示添加班级页面
	 */
	public void showAddClasses() {
		this.classes = new Classes();
	}

	/**
	 * 添加自定义班级
	 * 
	 * @throws Exception
	 */
	public void addClasses() throws Exception {
		try {
			if (classesFacade.checkClass((byte) 1, classes)) {
				this.classes.setAreaID(Contants.USER_DEFINED);
				this.classes.setCreateTime(new Date());
				this.classes.setStatus((short) 1);
				this.classes.setCurrCount((short) 0);
				this.classesFacade.add(this.classes);
				UserDefineClassesAction udaction = (UserDefineClassesAction) this
						.getViewAction("userDefineClassesAction");
				udaction.search();
				submitSuccess = 1;
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			logger.error(ExceptionUtil.getEMsg(e));
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}
	
	/** 状态 */
	public Map<Short, String> getStatuss() {
		return Contants.status;
	}

	public Classes getClasses() {
		return classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

	public void setClassesFacade(ClassesFacade classesFacade) {
		this.classesFacade = classesFacade;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public byte getClassflag() {
		return classflag;
	}

	public void setClassflag(byte classflag) {
		this.classflag = classflag;
	}

	public void setMemberClassFacade(MemberClassFacade memberClassFacade) {
		this.memberClassFacade = memberClassFacade;
	}

	public void setTeacherFacade(TeacherFacade teacherFacade) {
		this.teacherFacade = teacherFacade;
	}

	public void setClassteacherFacade(ClassteacherFacade classteacherFacade) {
		this.classteacherFacade = classteacherFacade;
	}

	public String getOldDisplayAdviser() {
		return oldDisplayAdviser;
	}

	public void setOldDisplayAdviser(String oldDisplayAdviser) {
		this.oldDisplayAdviser = oldDisplayAdviser;
	}
	public Map<Short, String> getStatus() {
		return Contants.status;
	}
}
