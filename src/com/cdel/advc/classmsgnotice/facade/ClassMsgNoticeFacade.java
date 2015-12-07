package com.cdel.advc.classmsgnotice.facade;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdel.advc.classes.facade.ClassesFacade;
import com.cdel.advc.classmsgnotice.domain.ClassMsgNotice;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.StringUtil;

@SuppressWarnings("serial")
@Service
public class ClassMsgNoticeFacade extends
		BaseFacadeImpl<ClassMsgNotice, Integer> implements Serializable {
	@Autowired
	private ClassesFacade classesFacade;

	/**
	 * 更改状态
	 * 
	 * @param classMsgID
	 * @param status
	 * @throws Exception
	 */
	public void changeStatus(Integer classMsgID, Integer status)
			throws Exception {
		ClassMsgNotice classMsgNotice = new ClassMsgNotice();
		classMsgNotice.setClassMsgID(classMsgID);
		Short newStatus;
		if (status == 1) {
			newStatus = 0;
		} else {
			newStatus = 1;
		}
		classMsgNotice.setStatus(newStatus);
		update(classMsgNotice);
	}

	/**
	 * 验证公告
	 * 
	 * @param msg
	 * @param context
	 * @param course
	 * @param siteID
	 * @return
	 */
	public boolean checkClassNotice(byte flag,
			ClassMsgNotice updateClassMsgNotice, Integer[] classIDs) {
		if (flag == 0) {
			if (classIDs == null || classIDs.length == 0) {
				return addWarnMessage("请至少选择一个班级！");
			}
			if (classIDs.length > 100) {
				return addWarnMessage("最多一次发送100个班级！");
			}
		}
		String msgTitle = StringUtil.nullToString(updateClassMsgNotice
				.getMsgTitle());
		if (msgTitle.equals("")) {
			return addWarnMessage("公告标题不能为空！");
		}
		String msgContent = StringUtil.nullToString(updateClassMsgNotice
				.getMsgContent());
		if (msgContent.equals("")) {
			return addWarnMessage("公告内容不能为空！");
		}
		return true;
	}

	@Override
	public void add(ClassMsgNotice cmsg) {
		super.add(cmsg);
		// 添加班级公告统计
		classesFacade.updateMsgTime(cmsg.getMsgType(), cmsg.getCreateTime(),
				cmsg.getClassID());
	}

}
