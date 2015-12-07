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
package com.cdel.advc.classteacher.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.cdel.advc.classes.domain.Classes;
import com.cdel.advc.classteacher.domain.Classteacher;
import com.cdel.advc.teacher.domain.Teacher;
import com.cdel.util.BaseFacadeImpl;

/**
 * <p>
 * 班级老师(管理员信息) facade层
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-2 上午10:57:30
 */
@SuppressWarnings("serial")
@Service
public class ClassteacherFacade extends BaseFacadeImpl<Classteacher, Integer> {

	/**
	 * 获取teacherID是管理员的个数
	 * 
	 * @param teacherID
	 * @return
	 */
	public Integer getTutorCountByTeacherID(Integer teacherID) {
		return baseDao.get(teacherID, "getTutorCountByTeacherID");
	}

	/**
	 * 返回teacherList
	 * 
	 * @param courseArr
	 * @param courseListAll
	 * @return
	 */
	public List<Classteacher> resetSourseList(Integer[] teacherIDs,
			List<Teacher> teacherListAll, Integer zhuRenID) {
		List<Classteacher> teacherList = new ArrayList<Classteacher>();
		Classteacher ct = null;
		Teacher t2 = null;
		if (teacherIDs != null && teacherIDs.length > 0) {
			for (int i = 0; i < teacherIDs.length; i++) {
				Teacher t = new Teacher();
				t.setTeacherID(teacherIDs[i]);
				int index = teacherListAll.indexOf(t);
				if (index >= 0) {
					ct = new Classteacher();
					t2 = teacherListAll.get(index);
					ct.setTeacherID(t2.getTeacherID());
					ct.setTeacherName(t2.getTeacherName());
					ct.setTeacherDesc("");
					teacherList.add(ct);
				}
			}
		}
		return teacherList;
	}

	/**
	 * 为管理员批量添加负责的班级
	 * 
	 * @param classesList
	 * @param teacherID
	 * @throws Exception
	 */
	public void addClassTeachers(List<Classes> classesList, Integer teacherID,
			Integer[] classIDs) throws Exception {
		if (classesList == null || teacherID == null || classesList.size() == 0) {
			return;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("teacherID", teacherID);
		map.put("classesList", classesList);
		this.delete(map);
		List<Classteacher> clist = new ArrayList<Classteacher>();
		for (int i = 0; i < classIDs.length; i++) {
			Classteacher c = new Classteacher();
			c.setClassID(classIDs[i]);
			c.setTeacherType((short) 0);
			c.setTeacherID(teacherID);
			clist.add(c);
		}
		this.addList(clist);
	}

	/**
	 * 返回班级的所有老师ID
	 * 
	 * @param classID
	 * @return
	 */
	public List<Integer> getTeacherIDsInClass(Integer classID) {
		return this.baseDao.findSomeList(classID, "getTeacherIDsInClass");
	}

	/**
	 * 删除班主任
	 */
	public void deleteTC(Integer teacherID, Integer classID) {
		Classteacher ct = new Classteacher();
		ct.setClassID(classID);
		ct.setTeacherID(teacherID);
		this.delete(ct);
	}

	/**
	 * 添加班主任
	 */
	public void addTC(Integer teacherID, Integer classID, Short teacherType) {
		Classteacher ct = new Classteacher();
		ct.setClassID(classID);
		ct.setTeacherID(teacherID);
		ct.setTeacherType(teacherType);
		this.add(ct);
	}

}
