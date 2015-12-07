package com.cdel.advc.membercall.facade;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.cdel.advc.membercall.domain.MemberCall;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class MemberCallFacade extends BaseFacadeImpl<MemberCall, Integer>
		implements Serializable {

	/**
	 * 获取电话回访
	 * 
	 * @param memberCall
	 * @return
	 */
	public MemberCall getMemberCallInfo(MemberCall memberCall) {
		return baseDao.getByEntity(memberCall, "getMemberCallInfo");
	}

	/**
	 * 学员换班后，把电话回访对应到新的班级
	 * 
	 * @param newClassID
	 * @param oldClassID
	 * @param userID
	 */
	public void updateMembercallToNewClass(Integer newClassID,
			Integer oldClassID, Integer userID) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("newClassID", newClassID);
		map.put("classID", oldClassID);
		map.put("userID", userID);
		baseDao.update(map, "updateMembercallToNewClass");
	}

}
