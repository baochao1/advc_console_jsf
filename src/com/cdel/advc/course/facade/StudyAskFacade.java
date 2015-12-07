package com.cdel.advc.course.facade;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.cdel.advc.course.domain.StudyAsk;
import com.cdel.util.BaseFacadeImpl;

/**
 * 
 * 学习要求相关业务实现
 * 
 * @author zhangsulei
 * 
 */
@SuppressWarnings("serial")
@Service
public class StudyAskFacade extends BaseFacadeImpl<StudyAsk, Integer> implements
		Serializable {
	/**
	 * 获取chapterID对应的学习要求
	 * 
	 * @param chapterID
	 * @return
	 */
	public List<StudyAsk> getStudyAskList(Integer chapterID) {
		return baseDao.findList(chapterID, "getStudyAskList2");
	}

	/**
	 * 获取chapterID和type对应的学习要求
	 * 
	 * @param chapterID
	 * @return
	 */
	public StudyAsk getStudyAskListOne(StudyAsk studyAsk) {
		return baseDao.getByEntity(studyAsk, "getStudyAskList");
	}

	public Map<Short, String> getStudyAskTypeList(Integer chapterID) {
		List<Integer> result = baseDao.findSomeList(chapterID,
				"getStudyAskTypeList");
		Map<Short, String> map = new LinkedHashMap<Short, String>();
		if (result != null && result.size() > 0) {
			for (int i = 0; i < result.size(); i++) {
				if (result.get(i) == 1) {
					map.put(new Short("1"), "一门课程");
				} else if (result.get(i) == 2) {
					map.put(new Short("2"), "两门课程");
				} else if (result.get(i) == 3) {
					map.put(new Short("3"), "三门课程");
				} else {
					map.put(new Short("4"), "四门以上");
				}
			}
		}
		return map;
	}

	/**
	 * 拆分Content,修改时间
	 * 
	 * @param studyAsk
	 * @return
	 */
	public StudyAsk splitContent(StudyAsk studyAsk) {
		String content = studyAsk.getContent();
		if (content != null && !content.equals("")) {
			String str = "";
			String[] tmp = StringUtils.split(content, "{|||}");
			if (!tmp[0].equals("无")) {
				str = tmp[0];
			}
			studyAsk.setStudyAsk1(str);
			str = "";
			if (!tmp[1].equals("无")) {
				str = tmp[1];
			}
			studyAsk.setStudyAsk2(str);
			str = "";
			if (!tmp[2].equals("无")) {
				str = tmp[2];
			}
			studyAsk.setStudyAsk3(str);
			str = "";
			if (!tmp[3].equals("无")) {
				str = tmp[3];
			}
			studyAsk.setStudyAsk4(str);
		}
		Short suggestStyTime = studyAsk.getSuggestStyTime();
		if (suggestStyTime != null) {
			studyAsk.setSuggestStyTime4Hour(new Short(suggestStyTime / 60 + ""));
		}
		return studyAsk;
	}

	/**
	 * 删除该课程下的所有学习要求
	 * 
	 * @param courseID
	 */
	public void deleteBatchStudyAsk(Integer courseID) {
		baseDao.delete(courseID, "deleteBatchStudyAsk");
	}

	/**
	 * 批量添加
	 * 
	 * @param studyAskList
	 * @param chapterID
	 * @throws Exception
	 */
	public void insertBatch(List<StudyAsk> studyAskList, Integer chapterID)
			throws Exception {
		for (int i = 0; i < studyAskList.size(); i++) {
			StudyAsk studyAsk = studyAskList.get(i);
			studyAsk.setChapterID(chapterID);
			add(studyAsk);
		}
	}

	/**
	 * 根据章节ID，类型查找章节要求
	 * 
	 * @param chapterID
	 * @param type
	 * @return
	 */
	public StudyAsk getAskByChapterIDAndType(Integer chapterID, Short type) {

		StudyAsk ask = new StudyAsk();
		ask.setChapterID(chapterID);
		ask.setType(type);

		return this.baseDao.getByEntity(ask, "getStudyAskList");
	}

}