/*
 * @Title: ClassesFacade.java
 * @Package com.cdel.advc.classes.facade
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-7-2 上午10:57:30
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-7-2                          
 */
package com.cdel.advc.classes.facade;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdel.advc.classes.domain.Classes;
import com.cdel.advc.memberclass.facade.MemberClassFacade;
import com.cdel.util.BaseFacadeImpl;

/**
 * <p>
 * 班级facade层
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-2 上午10:57:30
 */
@SuppressWarnings("serial")
@Service
public class ClassesFacade extends BaseFacadeImpl<Classes, Integer> {
	@Autowired
	private MemberClassFacade memberClassFacade;

	/**
	 * 更新班级公告的最新日期
	 * 
	 * @param msg
	 */
	public void updateMsgTime(Short msgType, Date createTime, Integer classID) {
		if (msgType != null && (msgType == 1 || msgType == 3)) {
			Classes classes = new Classes();
			classes.setClassID(classID);
			if (msgType == 1) {
				classes.setClassMsg1Time(createTime);
			}
			if (msgType == 3) {
				classes.setClassMsg2Time(createTime);
			}
			update(classes);
		}
	}

	/**
	 * 根据辅导查询其下班级列表--作为查询过滤条件使用（实体中包含ID,name属性）
	 */
	public List<Classes> getClassesListByMajorID(Integer majorID) {
		return this.baseDao.findList(majorID, "getClassesListByMajorID");
	}

	public List<Classes> getClassesListByMajorID4Status(Integer majorID) {
		return this.baseDao.findList(majorID, "getClassesListByMajorID4Status");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Classes> getClassesListByMajorID2(Map map) {
		return this.baseDao.findList(map, "getClassesListByMajorID2");
	}

	/**
	 * 模糊查询班级名称列表，默认最大显示20个
	 * 
	 * @param className
	 *            班级名称
	 * @return
	 */
	public List<String> findClassNames(String className) {
		return this.baseDao.findSomeList(className, "findClassNames");
	}

	/**
	 * 模糊查询班级名称列表，默认最大显示20个
	 * 
	 * @param className
	 *            班级名称
	 * @return
	 */
	public List<Classes> findClasss(String className) {
		return this.baseDao.findList(className, "findClasss");
	}

	/**
	 * 获取教师的管理的班级信息
	 * 
	 * @param Integer
	 * @return
	 */
	public List<Classes> getTeacherClasses(Integer teacherID) {
		return baseDao.findList(teacherID, "getTeacherClasses");
	}

	/**
	 * 更新班级学员个数
	 * 
	 * @param classID
	 * @param changeCount
	 */
	public void updateClassMemberCount(Integer classID, int changeCount) {

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("classID", classID);
		map.put("changeCount", changeCount);

		this.baseDao.update(map, "updateClassesMemberCount");
	}

	/**
	 * 
	 * 得到事务所班级个数
	 * 
	 * @param majorID
	 * @return
	 */
	public int getCPAClassesCount(Integer majorID) {
		return (Integer) this.baseDao.get(majorID, "getCPAClassesCount");
	}

	/**
	 * 验证班级搜索条件
	 * 
	 * @param msg
	 * @param context
	 * @param teacher
	 * @return
	 */
	public boolean checkTeaClass(Classes classes) {
		if (classes.getMajorID() == null) {
			return addWarnMessage("info", "请选择辅导");
		} else if (classes.getTermID() == null) {
			return addWarnMessage("info", "请选择考期");
		}
		return true;
	}

	/**
	 * 查看考期下是否有班级
	 * 
	 * @param termID
	 * @return
	 */
	public int getCountClassesByTermID(Integer termID) {
		return (Integer) this.baseDao.get(termID, "getCountClassesByTermID");
	}

	/**
	 * 获取考期下的班级
	 * 
	 * @param Integer
	 * @return
	 */
	public List<Classes> getClassesByTerm(Classes classes) {
		return baseDao.findList(classes, "getClassesByTerm");
	}

	/**
	 * 更新考期下的班级状态
	 * 
	 * @param classes
	 */
	public void updateClassesStatusInTerm(Integer termID, Short status) {
		Classes classes = new Classes();
		classes.setTermID(termID);
		classes.setStatus(status);
		this.baseDao.update(classes, "updateClassesStatusInTerm");
	}

	/**
	 * 获取指定策略下的班级数
	 * 
	 * @param strategyID
	 * @return
	 */
	public int getCountClassByStrategyID(Integer strategyID) {
		return (Integer) this.baseDao.get(strategyID,
				"getCountClassByStrategyID");
	}

	/**
	 * 获取指定策略下的班级
	 * 
	 * @param strategyID
	 * @return
	 */
	public List<Classes> getClassByStrategyID(Classes classes) {
		return this.baseDao.findList(classes, "getClassByStrategyID");
	}

	/**
	 * 获取一个学员未满的班级
	 * 
	 * @param c
	 * @return
	 * @throws Exception
	 */
	public Classes getNotFullClasses(Classes c) throws Exception {
		List<Classes> classList = this.baseDao.findList(c, "getNotFullClasses");
		for (Classes cls : classList) {
			int classSeq = memberClassFacade.getMemberInClassSeq(
					cls.getClassID(), c.getLimitNum());
			if (classSeq != -1) {
				cls.setCurrSeq(classSeq);
				return cls;
			}
		}
		return null;
	}

	/**
	 * 查看重Code
	 * 
	 * @param strategyID
	 * @return
	 */
	public Integer checkCode(Classes classes) {
		return this.baseDao.get(classes, "checkCode");
	}

	/**
	 * 获取teacherCode是班主任的班级列表
	 * 
	 * @param strategyID
	 * @return
	 */
	public List<Classes> getZhuRenClassList(String teacherCode) {
		return this.baseDao.findList(teacherCode, "getZhuRenClassList");
	}

	/**
	 * 验证添加班级
	 * 
	 * @param flag
	 *            =0班级,=1自定义班级
	 * @param context
	 * @param classes
	 * @return
	 */
	public boolean checkClass(byte flag, Classes classes) {
		if (flag == 1) {
			if (classes.getTermID() == null) {
				return addWarnMessage("考期不能为空！");
			}
			if (classes.getStrategyID() == null) {
				return addWarnMessage("策略不能为空！");
			}
		}
		if (StringUtils.isBlank(classes.getClassName())) {
			return addWarnMessage("班级名称不能为空！");
		}
		if (StringUtils.isBlank(classes.getClassCode())) {
			return addWarnMessage("班级编号不能为空！");
		}
		Integer count = this.checkCode(classes);
		if (count > 0) {
			return addWarnMessage("班级编号已存在！");
		}
		return true;
	}

}
