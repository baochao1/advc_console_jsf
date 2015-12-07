package com.cdel.advc.classmsgActive.facade;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdel.advc.classes.facade.ClassesFacade;
import com.cdel.advc.classmsgActive.domain.ClassmsgActive;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class ClassmsgActiveFacade extends
		BaseFacadeImpl<ClassmsgActive, Integer> implements Serializable {
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
		ClassmsgActive classmsgActive = new ClassmsgActive();
		classmsgActive.setClassMsgID(classMsgID);
		Short newStatus;
		if (status == 1) {
			newStatus = 0;
		} else {
			newStatus = 1;
		}
		classmsgActive.setStatus(newStatus);
		update(classmsgActive);
	}

	/**
	 * 验证活动
	 * 
	 * @param msg
	 * @param context
	 * @param course
	 * @param siteID
	 * @return
	 */
	public boolean checkClassmsgActive(byte flag,
			ClassmsgActive updateClassmsgActive, Integer[] classIDs) {
		if (flag == 0) {
			if (classIDs == null || classIDs.length == 0) {
				return addWarnMessage("请选择班级！");
			}
		}
		if (StringUtils.isBlank(updateClassmsgActive.getMsgTitle())) {
			return addWarnMessage("活动标题不能为空！");
		}
		if (StringUtils.isBlank(updateClassmsgActive.getMsgContent())) {
			return addWarnMessage("活动内容不能为空！");
		}
		return true;
	}

	@Override
	public void add(ClassmsgActive classmsgActive) {
		super.add(classmsgActive);
		// 添加班级公告统计
		classesFacade.updateMsgTime(classmsgActive.getMsgType(),
				classmsgActive.getCreateTime(), classmsgActive.getClassID());
	}

	@Override
	public void addList(List<ClassmsgActive> cmsgList) {
		super.addList(cmsgList);
		// 添加班级公告统计
		for (ClassmsgActive msg : cmsgList) {
			classesFacade.updateMsgTime(msg.getMsgType(), msg.getCreateTime(),
					msg.getClassID());
		}
	}

}
