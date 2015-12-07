package com.cdel.advc.gdb.membercall.facade;

import java.io.Serializable;
import org.springframework.stereotype.Service;
import com.cdel.advc.gdb.membercall.domain.GdbMemberCall;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class GdbMemberCallFacade extends BaseFacadeImpl<GdbMemberCall, Integer>
		implements Serializable {
	/**
	 * 获取电话回访
	 * 
	 * @param memberCall
	 * @return
	 */
	public GdbMemberCall getGdbMemberCallInfo(GdbMemberCall memberCall) {
		return baseDao.get(memberCall, "getGdbMemberCallInfo");
	}

}
