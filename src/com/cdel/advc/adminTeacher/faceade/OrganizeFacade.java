package com.cdel.advc.adminTeacher.faceade;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cdel.advc.adminTeacher.domain.AdminStatis;
import com.cdel.advc.adminTeacher.domain.Organize;
import com.cdel.util.BaseFacadeImpl;
 
@SuppressWarnings("serial")
@Service
public class OrganizeFacade  extends BaseFacadeImpl<Organize, Integer> implements  Serializable {
	
	public List<Organize> showList(Organize organize) {
	  	return this.baseDao.findList(organize, "getOrganizeList");
}

	public List<Organize> getGroupList(Integer orgID) {
		 	return this.baseDao.findList(orgID,"getGroupListByOrgID");
	}
}
