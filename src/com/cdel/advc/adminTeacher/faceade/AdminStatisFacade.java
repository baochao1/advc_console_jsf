package com.cdel.advc.adminTeacher.faceade;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cdel.advc.adminTeacher.domain.AdminStatis;
import com.cdel.util.BaseFacadeImpl;

/**
 * <p> 管理员班主任信息统计 </p> 
 * @author xuxiaoguang
 */
@SuppressWarnings("serial")
@Service
public class AdminStatisFacade   extends BaseFacadeImpl<AdminStatis, Integer>  {

	public List<AdminStatis> showList(AdminStatis adminStatis) {
		  	return this.baseDao.findList(adminStatis, "findPageAdminStatis");
	}

}
