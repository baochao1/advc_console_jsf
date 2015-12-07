package com.cdel.advc.course.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * @author dell（ 章节 实体）学习计划项
 */
@SuppressWarnings("serial")
public class TempChapter extends BaseDomain implements Serializable {
	private Integer chapterID;// 主键
	private Short phaseNo;// 阶段号
	private String chapterName;// 计划项(章节)名
	private Short openStatus;// 开通状态
	private String noteName;// 短信章节名
	private Date openTime;// 开通时间
	private Date createTime;// 创建时间
	private Integer courseID;// 所属课程ID
	private String courseName;// 所属课程
	private String url;// URL
	private Integer opener;// 开通人
	private String openerName;// 开通人
	private Integer creator;// 创建人
	private String qzChapter;// 题库章节ID
	private Integer sequence;// 排序
	private String studyAsk;
	private String studyAsk1;
	private String studyAsk2;
	private String studyAsk3;
	private String studyAsk4;
	private Short suggestStyTime;// 建议时间
	private String teacherName;
	private Integer askNum;// 学习要求个数
	private List<StudyAsk> studyAskList = new ArrayList<StudyAsk>();// 学习要求
	private String stageName;// 阶段名

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public Short getPhaseNo() {
		return phaseNo;
	}

	public void setPhaseNo(Short phaseNo) {
		this.phaseNo = phaseNo;
	}

	public Short getOpenStatus() {
		return openStatus;
	}

	public void setOpenStatus(Short openStatus) {
		this.openStatus = openStatus;
	}

	public String getOpenStatusStr() {
		if (this.openStatus == null) {
			return "";
		}
		return Contants.openStatus.get(openStatus);
	}

	public String getNoteName() {
		return noteName;
	}

	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public String getOpenTimeStr() {
		return DateUtil.getNowDateString(openTime, "yyyy-MM-dd");
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateTimeStr() {
		return DateUtil.getNowDateString(createTime, "yyyy-MM-dd");
	}

	public Integer getCourseID() {
		return courseID;
	}

	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}

	public Integer getChapterID() {
		return chapterID;
	}

	public void setChapterID(Integer chapterID) {
		this.chapterID = chapterID;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOpenerName() {
		return openerName;
	}

	public void setOpenerName(String openerName) {
		this.openerName = openerName;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public String getQzChapter() {
		return qzChapter;
	}

	public void setQzChapter(String qzChapter) {
		this.qzChapter = qzChapter;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getStudyAsk() {
		return studyAsk;
	}

	public void setStudyAsk(String studyAsk) {
		this.studyAsk = studyAsk;
	}

	public String getStudyAsk1() {
		return studyAsk1;
	}

	public void setStudyAsk1(String studyAsk1) {
		this.studyAsk1 = studyAsk1;
	}

	public String getStudyAsk2() {
		return studyAsk2;
	}

	public void setStudyAsk2(String studyAsk2) {
		this.studyAsk2 = studyAsk2;
	}

	public String getStudyAsk3() {
		return studyAsk3;
	}

	public void setStudyAsk3(String studyAsk3) {
		this.studyAsk3 = studyAsk3;
	}

	public String getStudyAsk4() {
		return studyAsk4;
	}

	public void setStudyAsk4(String studyAsk4) {
		this.studyAsk4 = studyAsk4;
	}

	public Integer getOpener() {
		return opener;
	}

	public void setOpener(Integer opener) {
		this.opener = opener;
	}

	public Short getSuggestStyTime() {
		return suggestStyTime;
	}

	public void setSuggestStyTime(Short suggestStyTime) {
		this.suggestStyTime = suggestStyTime;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Integer getAskNum() {
		return askNum;
	}

	public void setAskNum(Integer askNum) {
		this.askNum = askNum;
	}

	public List<StudyAsk> getStudyAskList() {
		return studyAskList;
	}

	public void setStudyAskList(List<StudyAsk> studyAskList) {
		this.studyAskList = studyAskList;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

}
