package com.cdel.advc.innermsg.facade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdel.advc.classteacher.facade.ClassteacherFacade;
import com.cdel.advc.innermsg.domain.InnerMsg;
import com.cdel.advc.innermsg.domain.InnerMsgReceive;
import com.cdel.advc.memberclass.domain.MemberClass;
import com.cdel.advc.memberclass.facade.MemberClassFacade;
import com.cdel.advc.teacher.facade.TeacherFacade;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.DateUtil;

@SuppressWarnings("serial")
@Service
public class InnerMsgFacade extends BaseFacadeImpl<InnerMsg, Integer> implements
		Serializable {
	@Autowired
	private InnerMsgReceiveFacade innerMsgReceiveFacade;
	@Autowired
	private MemberClassFacade memberClassFacade;
	@Autowired
	private TeacherFacade teacherFacade;
	@Autowired
	private ClassteacherFacade classteacherFacade;

	/**
	 * 更改状态
	 * 
	 * @param innerMsgID
	 * @param status
	 * @throws Exception
	 */
	public void changeStatus(Integer innerMsgID, Integer status)
			throws Exception {
		InnerMsg innerMsg = new InnerMsg();
		innerMsg.setInnerMsgID(innerMsgID);
		Short newStatus;
		if (status == 1) {
			newStatus = 0;
		} else {
			newStatus = 1;
		}
		innerMsg.setStatus(newStatus);
		update(innerMsg);
	}

	/**
	 * 查询明细信息
	 * 
	 * @param innerMsgID
	 * @return
	 */
	public InnerMsg getRecerveDetail(Integer innerMsgID) {
		InnerMsg innerMsg = findByID(innerMsgID);
		innerMsg.setReceiverName(innerMsgReceiveFacade.getReceiverStr(
				innerMsgID, (byte) 1));
		return innerMsg;
	}

	/**
	 * 追加回复
	 * 
	 * @param content
	 * @return
	 */
	public String updateContent(String content, String newcontent,
			String userName) {
		StringBuffer sb = new StringBuffer(content);
		sb.append("<br>").append(newcontent).append("<font color='blue'>")
				.append(userName).append("</font>|<font color='blue'>")
				.append(DateUtil.getNowToString("yyyy-MM-dd HH:mm:ss"))
				.append("</font>)");
		return sb.toString();
	}

	/**
	 * 发送沟通消息，并且保存沟通消息接收人
	 * 
	 * @param innerMsg
	 * @throws Exception
	 */
	public void addInnerMsg(String[] innerMsgManagers, InnerMsg innerMsg)
			throws Exception {
		innerMsg.setSendTime(new Date());
		innerMsg.setStatus((short) 1);
		this.add(innerMsg);
		// 保存沟通消息接收人
		InnerMsgReceive imr = null;
		List<InnerMsgReceive> msgList = new ArrayList<InnerMsgReceive>();
		for (int i = 0, l = innerMsgManagers.length; i < l; i++) {
			imr = new InnerMsgReceive();
			imr.setInnerMsgID(innerMsg.getInnerMsgID());
			imr.setReceiveTeacherID(Integer.parseInt(innerMsgManagers[i]));
			imr.setReadStatus((short) 0);
			msgList.add(imr);
		}
		innerMsgReceiveFacade.addList(msgList);
	}

	/**
	 * 分班\退班\换班发班内消息
	 * 
	 * @param classID
	 * @param userID
	 * @throws Exception
	 */
	public void addMessageForDivide(Integer classID, Integer userID) {
		MemberClass memberClass = new MemberClass();
		memberClass.setClassID(classID);
		memberClass.setUserID(userID);
		List<MemberClass> mlist = memberClassFacade
				.getMemberClassesInfo(memberClass);
		if (mlist != null && mlist.size() > 0) {
			memberClass = mlist.get(0);
			// 新建内部消息start
			InnerMsg innermsg = new InnerMsg();
			innermsg.setReferClass(classID);
			innermsg.setReferUser(memberClass.getUserName());
			innermsg.setSender(635);
			innermsg.setInnerMsgContent(memberClass.getUserName() + " 分到 "
					+ memberClass.getClassName());
			innermsg.setSendTime(new Date());
			innermsg.setStatus(Short.valueOf("1"));
			add(innermsg);

			// 获取班主任ID
			String displayAdviser = memberClass.getDisplayAdviser();
			Integer displayAdviserID = null;
			if (displayAdviser != null) {// 处理班主任
				if (displayAdviser.indexOf("号") != -1) {
					String displayAdviserCode = displayAdviser.substring(0,
							displayAdviser.indexOf("号"));
					displayAdviserID = teacherFacade
							.getTeacherByCode(displayAdviserCode);
				}
			}
			// 获取班级管理员
			List<Integer> classTeacherList = classteacherFacade
					.getTeacherIDsInClass(classID);
			List<Integer> teacherList = teacherFacade.getHeadTeacherIDs();
			teacherList.addAll(classTeacherList);
			if (displayAdviserID != null) {
				// 遍历一下list，看班主任是否在list里，如果存在，则无需再添加
				boolean b = true;
				if (teacherList.size() > 0) {
					for (Integer id : teacherList) {
						if (displayAdviserID.intValue() == id) {
							b = false;
							break;
						}
					}
				}
				if (b) {
					teacherList.add(displayAdviserID);
				}
			}
			// 向接收人发送
			if (teacherList != null && teacherList.size() > 0) {
				List<InnerMsgReceive> msgList = new ArrayList<InnerMsgReceive>();
				InnerMsgReceive innermsgReceive = null;
				for (Integer teacherID : teacherList) {
					innermsgReceive = new InnerMsgReceive();
					innermsgReceive.setInnerMsgID(innermsg.getInnerMsgID());
					innermsgReceive.setReceiveTeacherID(teacherID);
					innermsgReceive.setReadStatus(Short.valueOf("0"));
					msgList.add(innermsgReceive);
				}
				innerMsgReceiveFacade.addList(msgList);
			}
		}
	}

	/**
	 * 验证消息数据
	 * 
	 * @param flag
	 * @param msg
	 * @param context
	 * @param detailInnerMsg
	 * @param innerMsgManagers
	 * @param innerMsgMembers
	 * @return
	 */
	public boolean checkMsg(InnerMsg detailInnerMsg, String[] innerMsgManagers) {
		if (innerMsgManagers == null || innerMsgManagers.length == 0) {
			return addWarnMessage("至少选择一个管理员！");
		}
		if (StringUtils.isBlank(detailInnerMsg.getInnerMsgContent())) {
			return addWarnMessage("内容不能为空！");
		}
		if (StringUtils.isBlank(detailInnerMsg.getReferUser())) {
			return addWarnMessage("相关学员不能为空！");
		}
		return true;
	}

	/**
	 * 获取我的沟通消息总数
	 * 
	 * @param detailInnerMsg
	 * @return
	 */
	public int countInnerMsg(InnerMsg detailInnerMsg) {
		return (Integer) this.baseDao.get(detailInnerMsg, "countInnerMsg");
	}

}
