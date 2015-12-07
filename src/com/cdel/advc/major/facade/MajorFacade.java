package com.cdel.advc.major.facade;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;
import com.cdel.advc.major.domain.Major;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class MajorFacade extends BaseFacadeImpl<Major, Integer> implements
		Serializable {
	
	/**
	 * 根据名字获取major
	 * 
	 * @param majorName
	 * @return
	 */
	public List<String> getMajorNameByMajorName(String majorName) {
		return baseDao.findSomeList(majorName, "getMajorNameByMajorName");
	}
	
	/**
	 * 通过班级ID查找其所属辅导
	 * 
	 * @param classID
	 * @return
	 */
	public Major getMajorByClassID(Integer classID){
		if (classID == null) {
			
			throw new IllegalArgumentException("非法参数");
		}
		return this.baseDao.get(classID, "getMajorByClassID");
	}

}
