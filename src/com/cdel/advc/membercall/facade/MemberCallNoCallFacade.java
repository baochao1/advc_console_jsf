package com.cdel.advc.membercall.facade;

import java.io.Serializable;
import org.springframework.stereotype.Service;
import com.cdel.advc.membercall.domain.MemberCallNoCall;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class MemberCallNoCallFacade extends
		BaseFacadeImpl<MemberCallNoCall, Integer> implements Serializable {

	/**
	 * 回访提醒数
	 * 
	 * @param memberCallNoCall
	 * @return
	 */
	public int countMemberCallNoCall(MemberCallNoCall memberCallNoCall) {
		return (Integer) this.baseDao.get(memberCallNoCall,
				"countMemberCallNoCall");
	}

}
