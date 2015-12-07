package com.cdel.qz.chapter.action;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.cdel.qz.chapter.domain.QzChapter;
import com.cdel.qz.chapter.facade.QzChapterFacade;

/**
 * 
 * <p>
 * 章节action
 * </p>
 * 
 * @author 张苏磊
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ChapterOther implements Serializable {

	@ManagedProperty("#{qzChapterFacade}")
	private QzChapterFacade qzChapterFacade;

	private List<QzChapter> qzChapterList;

	public void selectQzChapterList(Integer courseID) {
		this.qzChapterList = qzChapterFacade.findList(courseID);
	}

	public void setQzChapterFacade(QzChapterFacade qzChapterFacade) {
		this.qzChapterFacade = qzChapterFacade;
	}

	public List<QzChapter> getQzChapterList() {
		return qzChapterList;
	}

}
