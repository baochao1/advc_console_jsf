package com.cdel.advc.plan.facade;

import java.io.Serializable;
import org.springframework.stereotype.Service;
import com.cdel.advc.plan.domain.MemberChapter;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class MemberChapterFacade extends BaseFacadeImpl<MemberChapter, Integer>
		implements Serializable {
	/**
	 * 删除该课程下的学员章节完成进度
	 * 
	 * @param courseID
	 */
	public void removeMemberChapter(Integer courseID) {
		baseDao.delete(courseID, "removeMemberChapter");

	}

	/**
	 * 更新章节完成状态
	 * 
	 * @param chapterID
	 * @param planID
	 * @param userID
	 * @param status
	 * @throws Exception
	 */
	public void updateFinishStatus(Integer chapterID, Integer planID,
			Integer userID, Short status) throws Exception {
		MemberChapter mc = new MemberChapter();
		mc.setChapterID(chapterID);
		mc.setUserID(userID);
		mc.setPlanID(planID);
		mc.setFinishStatus(status);

		if (this.baseDao.getByEntity(mc, "findByID") == null) {
			this.add(mc);
		} else {
			this.update(mc);
		}
	}

}
