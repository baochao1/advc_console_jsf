package com.cdel.advc.major.facade;

import java.io.Serializable;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.cdel.advc.major.domain.ClassNo;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class ClassNoFacade extends BaseFacadeImpl<ClassNo, Integer> implements
		Serializable {
	/**
	 * 获取辅导下的设置的班级数
	 * 
	 * @param major
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<Short, ClassNo> getClassNoByMajor(Integer majorID) {
		return baseDao.findMap(majorID, "getClassNoByMajor", "classType");
	}

	/**
	 * 更新班级数
	 * 
	 * @param classNo
	 */
	public void updateClassNo(ClassNo classNo) {
		baseDao.update(classNo, "updateClassNo");
	}

}
