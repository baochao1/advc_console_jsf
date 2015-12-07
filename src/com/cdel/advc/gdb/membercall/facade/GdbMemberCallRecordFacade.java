package com.cdel.advc.gdb.membercall.facade;

import java.io.Serializable;
import java.util.Date;
import org.springframework.stereotype.Service;
import com.cdel.advc.gdb.membercall.domain.GdbMemberCallRecord;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class GdbMemberCallRecordFacade extends
		BaseFacadeImpl<GdbMemberCallRecord, Integer> implements Serializable {

	/**
	 * 更新回访统计
	 * 
	 * @param userID
	 * @param classID
	 * @param callTime
	 * @param callStatus
	 * @throws Exception
	 */
	public void updateRecord(Integer userID, Date callTime, Short callStatus)
			throws Exception {
		GdbMemberCallRecord mcr = new GdbMemberCallRecord();
		mcr.setUserID(userID);
		mcr.setLastCallTime(callTime);
		if (callStatus == 1) {
			mcr.setSuccessCallTime(callTime);
		}
		if (getMemberCallRecord(mcr) == null) {
			add(mcr);
		} else {
			update(mcr);
		}
	}

	/**
	 * 获取记录
	 * 
	 * @param mcr
	 * @return
	 */
	public GdbMemberCallRecord getMemberCallRecord(GdbMemberCallRecord mcr) {
		return this.baseDao.get(mcr, "getGdbMemberCallRecord");
	}

}
