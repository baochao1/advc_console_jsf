package com.cdel.advc.membermsglog.facade;

import java.io.Serializable;
import org.springframework.stereotype.Service;
import com.cdel.advc.membermsglog.domain.MembermsgLog;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class MembermsgLogFacade extends BaseFacadeImpl<MembermsgLog, Integer>
		implements Serializable {
	/**
	 * 根据msgID获取日志记录总数
	 * 
	 * @return
	 */
	public int getMembermsgLogCount(Integer msgID) {
		return (Integer) baseDao.get(msgID, "membermsgLogCount");
	}

}
