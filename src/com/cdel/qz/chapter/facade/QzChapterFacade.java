package com.cdel.qz.chapter.facade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.cdel.qz.chapter.domain.QzChapter;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class QzChapterFacade extends BaseFacadeImpl<QzChapter, Integer>
		implements Serializable {
	/**
	 * 查询题库对应课程的章节数（无用）
	 * 
	 * @param courseID
	 * @return
	 */
	public Integer getQzChaptersCountByCourseID(Integer courseID) {
		return baseDao.get(courseID, "getQzChaptersCountByCourseID");
	}

	public List<QzChapter> getQzChapterList(Integer courseID) {
		List<QzChapter> qzChapterList = null;
		Integer qzChapterCount = getQzChaptersCountByCourseID(courseID);
		if (qzChapterCount != null && qzChapterCount > 0) {
			qzChapterList = new ArrayList<QzChapter>();
			for (int i = 0; i < qzChapterCount; i++) {
				QzChapter qc = new QzChapter();
				qc.setChapterID(i);
				if (i == 0) {
					qc.setChapterName("综合");
				} else {
					qc.setChapterName("第" + i + "章");
				}
				qzChapterList.add(qc);
			}
		}
		return qzChapterList;
	}

}
