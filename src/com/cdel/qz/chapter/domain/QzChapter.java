package com.cdel.qz.chapter.domain;

import java.io.Serializable;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * @author dell 题库章节
 */
@SuppressWarnings("serial")
public class QzChapter extends BaseDomain implements Serializable {
	private String chapterName;// 计划名
	private Integer chapterID;// 主键

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public Integer getChapterID() {
		return chapterID;
	}

	public void setChapterID(Integer chapterID) {
		this.chapterID = chapterID;
	}

}
