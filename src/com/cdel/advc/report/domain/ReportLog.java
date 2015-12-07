/*
 *                                                                     
 */                                                                   
package com.cdel.advc.report.domain;

import java.io.Serializable;
import java.util.Date;

import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 *                                              
 * <p>学习报告评语日志？</p>
 * 
 * @author Du Haiying
 * Create at:2013-8-26 上午10:22:07
 */
@SuppressWarnings("serial")
public class ReportLog extends BaseDomain implements Serializable {

    private Integer remarkLogID;
    
    private Integer reportID;
    
    private Integer remarker;
        
    private String remarkContent;
    
    private Date remarkTime;
    
    //----------- vo dto -------------//
    private String teacherName;
    
    
    /**
     * 评论时间显示值
     */
    public  String getRemarkTimeStr(){
    	
    	if (this.remarkTime == null) {
			return "";
		}else {
			return DateUtil.getNowDateString(this.remarkTime, "yyyy-MM-dd HH:mm:ss");
		}
    	
    	
    }


    public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Integer getRemarkLogID() {
        return remarkLogID;
    }

    public void setRemarkLogID(Integer remarkLogID) {
        this.remarkLogID = remarkLogID;
    }

    public Integer getRemarker() {
        return remarker;
    }

    public void setRemarker(Integer remarker) {
        this.remarker = remarker;
    }

    public Integer getReportID() {
        return reportID;
    }

    public void setReportID(Integer reportID) {
        this.reportID = reportID;
    }

    public String getRemarkContent() {
        return remarkContent;
    }

    public void setRemarkContent(String remarkContent) {
        this.remarkContent = remarkContent;
    }

	public Date getRemarkTime() {
		return remarkTime;
	}

	public void setRemarkTime(Date remarkTime) {
		this.remarkTime = remarkTime;
	}

}