package com.cdel.advc.course.facade;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdel.advc.course.domain.TempChapter;
import com.cdel.advc.plan.facade.MemberChapterFacade;
import com.cdel.advc.plan.facade.TempDetailFacade;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.CheckUtil;
import com.cdel.util.StringUtil;

/**
 * 
 * 计划项相关业务实现
 * 
 * @author zhangsulei
 * 
 */
@SuppressWarnings("serial")
@Service
public class TempChapterFacade extends BaseFacadeImpl<TempChapter, Integer>
		implements Serializable {
	@Autowired
	private StudyAskFacade studyAskFacade;
	@Autowired
	private TempDetailFacade tempDetailFacade;
	@Autowired
	private MemberChapterFacade memberChapterFacade;

	/**
	 * 验证输入信息
	 * 
	 * @param flag
	 * @param msg
	 * @param context
	 * @param updateTempChapter
	 * @return
	 */
	public boolean checkTempChapter(byte flag, TempChapter updateTempChapter) {
		Short phaseNo = updateTempChapter.getPhaseNo();
		if (phaseNo == null) {
			return addWarnMessage("阶段名不能为空！");
		}
		if (StringUtil.nullToString(updateTempChapter.getChapterName()).equals(
				"")) {
			return addWarnMessage("计划项(章节)名不能为空！");
		}
		String url = StringUtil.nullToString(updateTempChapter.getUrl());
		if (!url.equals("") && !CheckUtil.checkUrl(url)) {
			return addWarnMessage("url地址输入不合法！");
		}
		return true;
	}

	public boolean checkSeq(TempChapter tempChapter) {
		Integer sequence = tempChapter.getSequence();
		if (sequence == null || !CheckUtil.checkInt(sequence.toString(), 3)) {
			return this.addWarnMessage("info", "排序必须是大于0的数字,且长度不能超过3个字符！");
		}
		return true;
	}

	/**
	 * 获取计划项最大排序号
	 * 
	 * @param courseID
	 * @return
	 */
	public Integer getMaxSequence(Integer courseID) {
		Integer count = baseDao.get(courseID, "getMaxSequence");
		if (count == null) {
			return 0;
		} else {
			return count;
		}
	}

	/**
	 * 获取已经使用的计划项数
	 * 
	 * @param courseID
	 * @return
	 */
	public Integer getCountChapter(Integer courseID) {
		Integer count = baseDao.get(courseID, "getCountChapter");
		if (count == null) {
			return 0;
		} else {
			return count;
		}
	}

	/**
	 * 获取TempChapter
	 * 
	 * @param chapterID
	 * @return
	 */
	public TempChapter getTempChapterMini(Integer chapterID) {
		return baseDao.get(chapterID, "getTempChapterMini");
	}

	/**
	 * 删除计划项
	 * 
	 * @param courseID
	 */
	public void removeChaptersByCourseID(Integer courseID) {
		baseDao.delete(courseID, "removeChaptersByCourseID");
	}

	/**
	 * 复制计划项
	 * 
	 * @param courseID
	 * @param copyCourseID
	 */
	public void doCopyChapter(Integer courseID,
			List<TempChapter> tempChapterList) throws Exception {
		studyAskFacade.deleteBatchStudyAsk(courseID);
		tempDetailFacade.removeBatch(courseID);
		memberChapterFacade.removeMemberChapter(courseID);
		removeChaptersByCourseID(courseID);
		for (int i = 0; i < tempChapterList.size(); i++) {
			TempChapter tc = tempChapterList.get(i);
			tc.setCourseID(courseID);
			add(tc);
			studyAskFacade.insertBatch(tc.getStudyAskList(), tc.getChapterID());
		}
	}

	/**
	 * 通过课程ID获取计划项
	 * 
	 * @param courseID
	 * @return
	 */
	public List<TempChapter> getTempChaptersByCourseID(Integer courseID) {
		return baseDao.findList(courseID, "getTempChaptersByCourseID");
	}

}