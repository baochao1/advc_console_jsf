package com.cdel.advc.guideText.domain;

import java.util.Date;
import org.apache.commons.lang.StringUtils;
import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * 阶段引导语
 * 
 * @author 张苏磊
 */

@SuppressWarnings("serial")
public class GuideText extends BaseDomain implements java.io.Serializable {

	private Integer guideID;
	private String content;
	private Integer majorID;
	private Integer stageID;
	private Integer leftDays;// 剩余天数
	private Short type;// 1, "简略版",2, "详细版"
	private Short status;
	private Date createTime;

	// ------------------------------------------------------------

	private String stageName;
	private String majorName;

	public Integer getGuideID() {
		return guideID;
	}

	public void setGuideID(Integer guideID) {
		this.guideID = guideID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}

	public Integer getStageID() {
		return stageID;
	}

	public void setStageID(Integer stageID) {
		this.stageID = stageID;
	}

	public Integer getLeftDays() {
		return leftDays;
	}

	public void setLeftDays(Integer leftDays) {
		this.leftDays = leftDays;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getContentStr() {
		if (StringUtils.isNotBlank(content)) {
			String newStr = StringUtils.substring(content, 0, 50);
			if (StringUtils.length(content) == StringUtils.length(newStr)) {
				return content;
			} else {
				return newStr + "......";
			}
		} else {
			return "";
		}
	}

	public String getCreateTimeStr() {
		return DateUtil.getNowDateString(getCreateTime(), "yyyy-MM-dd");
	}

	public String getStatusStr() {
		if (this.status == null) {
			return "";
		}
		return Contants.status.get(status);
	}

	public String getTypeStr() {
		if (this.type == null) {
			return "";
		}
		return Contants.guideTextType.get(type);
	}

}