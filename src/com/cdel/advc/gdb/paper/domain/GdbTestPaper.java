package com.cdel.advc.gdb.paper.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 高端班测试试卷
 * 
 * @author 张苏磊
 */
@SuppressWarnings("serial")
public class GdbTestPaper extends BaseDomain implements Serializable {

	private Integer id;
	private Integer paperID;
	private Integer paperViewID;
	private Integer studyPlanID;
	private Short paperType;
	private String paperName;
	private Date testTime;
	private Integer createUserId;
	private Date createTime;
	private String realName;
	private String paperDesc;
	// ----------------------------vo----------------------------------------
	private List<Integer> pointIDList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPaperID() {
		return paperID;
	}

	public void setPaperID(Integer paperID) {
		this.paperID = paperID;
	}

	public Integer getPaperViewID() {
		return paperViewID;
	}

	public void setPaperViewID(Integer paperViewID) {
		this.paperViewID = paperViewID;
	}

	public Integer getStudyPlanID() {
		return studyPlanID;
	}

	public void setStudyPlanID(Integer studyPlanID) {
		this.studyPlanID = studyPlanID;
	}

	public Short getPaperType() {
		return paperType;
	}

	public String getPaperTypeStr() {
		if (paperType == null || paperType == 0) {
			return "";
		} else {
			return Contants.gdbPaperType.get(paperType);
		}
	}

	public void setPaperType(Short paperType) {
		this.paperType = paperType;
	}

	public Date getTestTime() {
		return testTime;
	}

	public void setTestTime(Date testTime) {
		this.testTime = testTime;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateTimeStr() {
		return DateUtil.getNowDateString(createTime, "yyyy-MM-dd HH:mm:ss");
	}

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPaperDesc() {
		return paperDesc;
	}

	public void setPaperDesc(String paperDesc) {
		this.paperDesc = paperDesc;
	}

	public Short getViewType() {
		if (paperType == 1) {
			return 7;
		}
		if (paperType == 2) {
			return 8;
		}
		if (paperType == 3) {
			return 9;
		}
		return 0;
	}

	public List<Integer> getPointIDList() {
		return pointIDList;
	}

	public void setPointIDList(List<Integer> pointIDList) {
		this.pointIDList = pointIDList;
	}

	public String getPointIDs() {
		StringBuffer sb = new StringBuffer();
		for (Integer pointID : pointIDList) {
			sb.append(pointID).append(",");
		}
		if (sb.length() > 0) {
			return sb.substring(0, sb.length() - 1);
		} else {
			return "";
		}
	}

}