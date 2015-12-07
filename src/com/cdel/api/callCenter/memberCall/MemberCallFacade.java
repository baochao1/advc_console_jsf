package com.cdel.api.callCenter.memberCall;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdel.advc.membercall.facade.MemberCallRecordFacade;
import com.cdel.api.callCenter.Constant;
import com.cdel.util.BaseFacadeImpl;

/**
 * 
 * <p>
 * 为呼叫中心提供的特定接口 回访记录facade层
 * </p>
 * 
 * @author XU xiaoguang Create at:2014-03-18 下午2:11:59
 */
@SuppressWarnings("serial")
@Service("callCenterMemberCallFacade")
public class MemberCallFacade extends
		BaseFacadeImpl<com.cdel.advc.membercall.domain.MemberCall, Integer> {
	protected static Logger logger = Logger.getLogger(MemberCallFacade.class);

	@Autowired
	private MemberCallRecordFacade memberCallRecordFacade;

	/**
	 * 保存 回访记录
	 * 
	 * @param memberCall
	 */
	public void insertMemberCall(List<MemberCall> memberCallList,Integer type)
			throws Exception {

		if (memberCallList == null) {
			throw new IllegalArgumentException("非法参数:参数不能为null!");
		} else {
			Integer outboundID = 0;
			Integer classID = 0;
			Integer teacherID = 0;
			
			for (MemberCall memberCall : memberCallList) {
				// 根据classID 查询回访人(就是班主任)
				outboundID = memberCall.getOutboundID(); // 新增回访记录主键
				classID = memberCall.getClassID();
				memberCall.setType(type);
				teacherID = this.baseDao.get(classID,
						Constant.GETTEACHERBYCLASSID);
				if (null != classID && null != teacherID) {
					// 从关联表中查询 回访记录是否存在，如果不存在，需要保存，如果存在，不要保存
					Integer cID = this.baseDao.get(memberCall,
							Constant.GETCALLID);
					memberCall.setCaller(teacherID); // 设置回访人
					memberCall.setCallTime(memberCall.getCallTime());
					if (cID == null) { // 说明不存在
						// 保存回访记录表
						this.baseDao.insertSome(memberCall,
								Constant.INSERTMEMBERCALL);
						Integer callID = memberCall.getCallID();
						// 保存关联表
						memberCall.setCallID(callID);
						memberCall.setOutboundID(outboundID);
						this.baseDao.insertSome(memberCall,
								Constant.INSERTCALLID);
					} else { // 更新操作
						memberCall.setCallID(cID); // 设置主键
						this.baseDao.updateSome(memberCall,
								Constant.UPDATEMEMBERCALL);
					}
					// 更新ADVC_MEMBER_CALL_RECORD表
					memberCallRecordFacade.updateRecord(memberCall.getUserID(),
							memberCall.getClassID(), memberCall.getCallTime(),
							memberCall.getCallStatus().shortValue());
				} else {
					logger.error("参数异常classID，" + classID + " teacherID: "
							+ teacherID);
				}
			}
		}
	}
}
