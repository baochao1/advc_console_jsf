package com.cdel.advc.membercall.facade;

import java.io.Serializable;
import org.springframework.stereotype.Service;
import com.cdel.advc.membercall.domain.ReserveCall;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class ReserveCallFacade extends BaseFacadeImpl<ReserveCall, Integer>
		implements Serializable {
	/**
	 * 预约回访数
	 * 
	 * @param reserveCallAll
	 * @return
	 */
	public int countReserveCall(ReserveCall reserveCall) {
		return (Integer) this.baseDao.get(reserveCall, "countReserveCall");
	}
}
