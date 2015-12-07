package com.cdel.advc.gdb.materials.domain;

import java.util.Date;

import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

@SuppressWarnings("serial")
public class GdbMaterials extends BaseDomain implements java.io.Serializable {
	private String materialName;// 资料名称
	private Integer creator;// 创建人
	private String creatorName;// 创建人名称
	private String uploadPath;// 上传路径
	private Date uploadTime;// 上传时间
	private Integer siteCourseID;// 课程id
	private Integer materialsID;// 主键
	private String siteCourseName;// 课程名称
	private String realName;// 上传人
	private Short status;// 状态

	public String getUploadTimeStr() {
		return DateUtil.getNowDateString(getUploadTime(), "yyyy-MM-dd HH:mm");
	}

	public String getStatusStr() {
		if (status == null) {
			return "";
		} else {
			return Contants.status.get(status);
		}
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Integer getSiteCourseID() {
		return siteCourseID;
	}

	public void setSiteCourseID(Integer siteCourseID) {
		this.siteCourseID = siteCourseID;
	}

	public Integer getMaterialsID() {
		return materialsID;
	}

	public void setMaterialsID(Integer materialsID) {
		this.materialsID = materialsID;
	}

	public String getSiteCourseName() {
		return siteCourseName;
	}

	public void setSiteCourseName(String siteCourseName) {
		this.siteCourseName = siteCourseName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

}
