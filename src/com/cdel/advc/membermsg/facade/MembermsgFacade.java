/*
 * @Title: MembermessageFacade.java
 * @Package com.cdel.advc.membermessage.facade
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-7-4 上午11:34:51
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-7-4                          
 */
package com.cdel.advc.membermsg.facade;

import org.apache.commons.lang.StringUtils;
import org.primefaces.model.LazyDataModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdel.advc.infoModify.domain.InfoModify;
import com.cdel.advc.member.domain.Member;
import com.cdel.advc.member.facade.MemberFacade;
import com.cdel.advc.membermsg.domain.Membermsg;
import com.cdel.advc.membermsglog.domain.MembermsgLog;
import com.cdel.advc.membermsglog.facade.MembermsgLogFacade;
import com.cdel.advc.sendMemberMsgGroup.domain.SendMemberMsgGroup;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.DateUtil;
import com.cdel.util.StringUtil;

/**
 * <p>
 * 学员-教师间消息 ("班级短信")facade层
 * </p>
 * 
 * @author 张苏磊 Create at:2013-7-4 上午11:34:51
 */
@SuppressWarnings("serial")
@Service
public class MembermsgFacade extends BaseFacadeImpl<Membermsg, Integer> {
	@Autowired
	private MembermsgLogFacade membermsgLogFacade;
	@Autowired
	private MemberFacade memberFacade;

	/**
	 * 根据msgID获取Membermsg
	 * 
	 * @return
	 */
	public Membermsg getMembermsgDetail(Integer msgID) {
		Membermsg msg = this.findByID(msgID);
		String replyContent = msg.getReplyContent();
		replyContent = StringUtil.changeContent2(replyContent);
		msg.setReplyContent(replyContent);
		return msg;
	}

	/**
	 * 分页查询过期消息
	 * 
	 * @param membermsg
	 *            条件参数封装对象：教师代码，开始、结束时间
	 * @return
	 */
	public LazyDataModel<Membermsg> getMembermsgPost(Membermsg membermsg) {
		return this.baseDao.findPage(membermsg, "getMembermsgPostListCount",
				"getMembermsgPostList");
	}

	/**
	 * 转为技术/转为正常
	 * 
	 * @param msgType
	 * @param membermsg
	 *            userID操作人 userName操作人
	 */
	public void updateMsg(Short msgType, Integer userID, String userName,
			Membermsg membermsg) throws Exception {
		membermsg.setMsgType(msgType);
		MembermsgLog membermsgLog = null;
		if (!StringUtil.nullToString(membermsg.getReplyContentOld()).equals("")) {
			membermsgLog = new MembermsgLog();
			membermsgLog.setLogType((short) 1);
			String typeName = "";
			if (msgType == 1) {
				typeName = "技术";
			}
			if (msgType == 0) {
				typeName = "正常";
			}
			String content = "老师：" + userName + " 转为" + typeName + "消息" + " ["
					+ DateUtil.getNowToString("yyyy-MM-dd HH:mm") + "]";
			membermsgLog.setLogContent(content);
			membermsgLog.setMsgID(membermsg.getMsgID());
			membermsgLog.setCreateTime(new Date());
			membermsgLog.setCreator(userID);
		}
		update(membermsg);
		if (membermsgLog != null) {
			membermsgLogFacade.add(membermsgLog);
		}
	}

	/**
	 * 提交
	 * 
	 * @param userID
	 * @param userName
	 * @param membermsg
	 * @throws Exception
	 */
	public void updateMsg(Integer userID, String userName, Membermsg membermsg)
			throws Exception {
		MembermsgLog membermsgLog = null;
		String replyContent = StringUtil.nullToString(membermsg
				.getReplyContent());
		if (!replyContent.equals("")) {
			replyContent = StringUtil.changeContent(replyContent);
			membermsg.setReplyContent(replyContent);
			membermsg.setReplyTime(new Date());
			membermsg.setTeacherID(userID);
		}
		String replyContentOld = StringUtil.nullToString(membermsg
				.getReplyContentOld());
		if (!replyContentOld.equals("")) {
			membermsgLog = new MembermsgLog();
			membermsgLog.setLogType((short) 2);
			String content = "老师："
					+ userName
					+ "<br/>原回复："
					+ replyContentOld
					+ "<br/>原回复时间："
					+ DateUtil.getNowDateString(membermsg.getReplyTime(),
							"yyyy-MM-dd HH:mm");
			membermsgLog.setLogContent(content);
			membermsgLog.setMsgID(membermsg.getMsgID());
			membermsgLog.setCreateTime(new Date());
			membermsgLog.setCreator(userID);
		}
		update(membermsg);
		if (membermsgLog != null) {
			membermsgLogFacade.add(membermsgLog);
		}
	}

	/**
	 * 重写 批量插入消息 msgType = 3 // 普通消息 msgType = 4 // 备考要点/提醒
	 * 
	 * @param teacherID
	 * @param title
	 * @param content
	 * @param member
	 * @param msg
	 * @param context
	 * @throws Exception
	 */
	public boolean addMemberMsg(Integer teacherID,
			SendMemberMsgGroup sendMemberMsgGroupInfo, Member member)
			throws Exception {
		List<Member> members = this.memberFacade.getMemberList(member);
		List<Membermsg> msList = new ArrayList<Membermsg>();
		if (members != null && members.size() > 0) {
			for (int i = 0; i < members.size(); i++) {
				Member memberInner = members.get(i);
				Membermsg membermsg = new Membermsg();
				membermsg.setUserID(memberInner.getUserID());
				membermsg.setClassID(memberInner.getClassID());
				membermsg.setTeacherID(teacherID);
				membermsg.setMsgType(sendMemberMsgGroupInfo.getMsgType());
				membermsg.setMsgTitle(sendMemberMsgGroupInfo.getMsgTitle());
				membermsg.setMsgContent(StringUtil
						.changeContent(sendMemberMsgGroupInfo.getContent()));
				membermsg.setSentTime(new Date());
				// membermsg.setReplyContent("班主任发送的消息");
				// membermsg.setReplyTime(new Date());
				membermsg.setReadFlag((short) 0);
				membermsg.setStatus((short) 1);
				msList.add(membermsg);
			}
		}
		if (msList == null || msList.size() == 0) {
			return addWarnMessage("没有查到学员！");
		}
		// 保存数据
		this.addMemberMsgs(msList);
		return true;
	}

	/**
	 * 批量插入消息
	 * 
	 * @param teacherID
	 * @param title
	 * @param content
	 * @param members
	 * @return
	 * @throws Exception
	 */
	public boolean addMemberMsg(Integer teacherID, Membermsg msg,
			List<Member> members) throws Exception {
		List<Membermsg> msList = new ArrayList<Membermsg>();
		if (members != null && members.size() > 0) {
			for (int i = 0; i < members.size(); i++) {
				Member memberInner = members.get(i);
				Membermsg membermsg = new Membermsg();
				membermsg.setUserID(memberInner.getUserID());
				membermsg.setClassID(memberInner.getClassID());
				membermsg.setTeacherID(teacherID);
				membermsg.setMsgType((short) 3);
				membermsg.setMsgTitle(msg.getMsgTitle());
				membermsg.setMsgContent(StringUtil.changeContent(msg
						.getMsgContent()));
				membermsg.setSentTime(new Date());
				// membermsg.setReplyContent("班主任发送的消息");
				// membermsg.setReplyTime(new Date());
				membermsg.setReadFlag((short) 0);
				membermsg.setStatus((short) 1);
				msList.add(membermsg);
			}
		}
		// 保存数据
		this.addMemberMsgs(msList);
		return true;
	}

	/**
	 * 插入消息
	 * 
	 * @param teacherID
	 * @param title
	 * @param content
	 * @param members
	 * @return
	 * @throws Exception
	 */
	public Membermsg addMemberMsg(Integer teacherID, Membermsg msg,
			Member memberInner) throws Exception {
		Membermsg membermsg = new Membermsg();
		membermsg.setUserID(memberInner.getUserID());
		membermsg.setClassID(memberInner.getClassID());
		membermsg.setTeacherID(teacherID);
		membermsg.setMsgType((short) 3);
		membermsg.setMsgTitle(msg.getMsgTitle());
		membermsg.setMsgContent(StringUtil.changeContent(msg.getMsgContent()));
		membermsg.setSentTime(new Date());
		// membermsg.setReplyContent("班主任发送的消息");
		// membermsg.setReplyTime(new Date());
		membermsg.setReadFlag((short) 0);
		membermsg.setStatus((short) 1);
		// 保存数据
		this.add(membermsg);
		return membermsg;
	}

	/**
	 * 批量添加手机短信（添加数据库）
	 * 
	 * @param smss
	 * @throws Exception
	 */
	public void addMemberMsgs(List<Membermsg> msList) throws Exception {
		if (msList != null && msList.size() > 0) {
			List<Membermsg> list = new ArrayList<Membermsg>(); // 临时集合
			for (int i = 0; i < msList.size(); i++) {
				if (i != 0 && i % 200 == 0) { // 每200条提交一次
					this.baseDao.insert(list, "insertMembermsgList");
					list.clear();
				}
				list.add(msList.get(i));
			}
			if (!list.isEmpty()) {
				this.baseDao.insert(list, "insertMembermsgList");
			}
		}
	}

	/**
	 * 更新消息内容
	 * 
	 * @param update
	 * @throws Exception
	 */
	public void updateContent(InfoModify update) throws Exception {
		this.baseDao.update(update, "updateContent");
	}

	/**
	 * 学员换班后，把消息对应到新的班级
	 * 
	 * @param newClassID
	 * @param oldClassID
	 * @param userID
	 */
	public void updateMembermsgToNewClass(Integer newClassID,
			Integer oldClassID, Integer userID) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("newClassID", newClassID);
		map.put("classID", oldClassID);
		map.put("userID", userID);
		this.baseDao.update(map, "updateMembermsgToNewClass");
	}

	/**
	 * 验证发送信息
	 * 
	 * @return
	 */
	public boolean checkSendMessage(Membermsg membermsg, Integer callID,
			Integer[] members, Integer userID) {
		if (callID == null && (members == null || members.length == 0)) {
			return addWarnMessage("请选择要发送的学员！");
		}
		if (callID != null && callID > 0 && userID == null) {
			return addWarnMessage("请选择要发送的学员！");
		}
		if (StringUtils.isBlank(membermsg.getMsgTitle())) {
			return addWarnMessage("消息标题不能为空！");
		}
		if (StringUtils.isBlank(membermsg.getMsgContent())) {
			return addWarnMessage("发送内容不能为空！");
		}
		return true;
	}

	/**
	 * 学员消息总数
	 * 
	 * @param membermsg
	 * @return
	 */
	public int countMembermsg(Membermsg membermsg) {
		return (Integer) this.baseDao.get(membermsg, "countMembermsg");
	}

	/**
	 * 查询学员已往消息或已往班内消息
	 * 
	 * @param membermsg
	 * @return
	 */
	@Override
	public List<Membermsg> findList(Membermsg membermsg) {
		if (membermsg.getClassID() == null) {
			return this.baseDao.findList(membermsg, "getMembermsgList2");
		} else {
			return super.findList(membermsg);
		}
	}

}
