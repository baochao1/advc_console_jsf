package com.cdel.advc.membercall.facade;

import java.io.Serializable;
import org.primefaces.model.LazyDataModel;
import org.springframework.stereotype.Service;
import com.cdel.advc.membercall.domain.MemberCallAll;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class MemberCallAllFacade extends BaseFacadeImpl<MemberCallAll, Integer>
		implements Serializable {
	/**
	 * 按教师查询的翻页
	 * 
	 * @param memberCallAll
	 */
	public LazyDataModel<MemberCallAll> findPage4Teacher(
			MemberCallAll memberCallAll) {
		return this.baseDao.findPage(memberCallAll,
				"membercallall.countMemberCallAll4T",
				"membercallall.findPageMemberCallAll4T");
	}

}
