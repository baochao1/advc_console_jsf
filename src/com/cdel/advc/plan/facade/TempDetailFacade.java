package com.cdel.advc.plan.facade;

import java.io.Serializable;
import org.springframework.stereotype.Service;
import com.cdel.advc.plan.domain.TempDetail;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class TempDetailFacade extends BaseFacadeImpl<TempDetail, Integer>
		implements Serializable {
	/**
	 * 删除该课程下的所有模板
	 * 
	 * @param courseID
	 */
	public void removeBatch(Integer courseID) {
		baseDao.delete(courseID, "removeBatch");
	}

}
