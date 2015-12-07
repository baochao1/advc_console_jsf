/*
 * @Title: TeacherFacade.java
 * @Package com.cdel.advc.teacher.facade
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-7-2 下午2:55:05
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-7-2                          
 */
package com.cdel.advc.teacher.facade;

import java.util.List;
import org.springframework.stereotype.Service;
import com.cdel.advc.teacher.domain.Teacher;
import com.cdel.util.BaseFacadeImpl;

/**
 * <p>
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-2 下午2:55:05
 */
@SuppressWarnings("serial")
@Service
public class TeacherFacade extends BaseFacadeImpl<Teacher, Integer> {

	/**
	 * 获取老师信息和所在机构信息
	 * 
	 * @param teacherName
	 *            教师名称
	 * @return
	 */
	public Teacher getTeacherAndOrg(Integer teacherID) {
		return baseDao.getByID(teacherID, "getTeacherAndOrg");
	}

	/**
	 * 获取和当前老师在同一个组织机构下的身份是班级管理员的老师(包括本人)
	 * 
	 * @param teacherID
	 * @return
	 */
	public List<Integer> getSameOrgManagerTeacherList(Integer teacherID) {
		return baseDao.findSomeList(teacherID, "getSameOrgManagerTeacherList");
	}

	/**
	 * 获取教师的详细信息
	 * 
	 * @param teacherID
	 * @return
	 */
	public Teacher getTeacherByTeacherID(Integer teacherID) {
		return this.baseDao.getByID(teacherID, "getTeacherByID");
	}

	/**
	 * 根据teacherCode获取教师ID
	 * 
	 * @param teacherCode
	 * @return
	 */
	public Integer getTeacherByCode(String teacherCode) {
		return this.baseDao.get(teacherCode, "getTeacherByCode");
	}

	/**
	 * 根据teacherCode获取教师信息
	 * 
	 * @param teacherCode
	 * @return
	 */
	public Teacher getTeacherByCode2(String teacherCode) {
		return this.baseDao.get(teacherCode, "getTeacherByCode2");
	}

	/**
	 * 根据名字获取teacher
	 * 
	 * @param majorName
	 * @return
	 */
	public List<String> getTeacherByTeacherName(String teacherName, byte flag) {
		if (flag == 0) {
			return this.baseDao.findSomeList(teacherName,
					"getTeacherByTeacherName");
		} else if (flag == 1) {
			return this.baseDao.findSomeList(teacherName,
					"getTeacherByTeacherName2");
		} else {
			return this.baseDao.findSomeList(teacherName,
					"getTeacherByTeacherName3");
		}
	}

	/**
	 * 验证更新教师信息
	 * 
	 * @param msg
	 * @param context
	 * @param teacher
	 * @return
	 */
	public boolean checkTeacher(Teacher teacher) {
		if (teacher.getOrgID() == null) {
			return addWarnMessage("info", "请选择组织");
		} else if (teacher.getIsHeader() == null) {
			return addWarnMessage("info", "请选择职位");
		}
		return true;
	}

	/**
	 * 返回ishead=1的教师ID列表
	 * 
	 * @return
	 */
	public List<Integer> getHeadTeacherIDs() {
		return this.baseDao.findSomeList(null, "getHeadTeacherIDs");
	}

	/**
	 * 返回所有高端班教师
	 * 
	 * @param hasadvanc
	 *            = 5具有高端班答疑的权限
	 * 
	 * @return
	 */
	public List<Teacher> selectAllAdvanceTeacher() {
		return this.baseDao.findList(1, "selectAllAdvanceTeacher");
	}

	/**
	 * 排除该teacherID的列表
	 * 
	 * @param teacherCode
	 * @return
	 */
	public List<Teacher> getTeacherListNoID(String teacherCode) {
		return this.baseDao.findList(teacherCode, "getTeacherListNoID");
	}

}
