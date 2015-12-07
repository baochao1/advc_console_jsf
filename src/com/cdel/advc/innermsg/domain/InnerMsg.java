package com.cdel.advc.innermsg.domain;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.cdel.util.Contants;
import com.cdel.util.DateUtil;

/**
 * 
 * <p>班级内部沟通消息 实体</p>
 * 
 * @author Du Haiying
 * Create at:2013-7-26 下午4:11:22
 */
@SuppressWarnings("serial")
public class InnerMsg extends InnerMsgReceive implements java.io.Serializable {

	/** ID */
	private Integer innerMsgID;
	
	/** 班级ID */
	private Integer referClass;
	
	/** 学员ID(接收人) */
	private String referUser;// 
	
	/** 发送人 */
	private Integer sender;
	
	/** 发送时间 */
	private Date sendTime;
	
	/** 发送内容   */
	private String innerMsgContent;
	
	/** 状态  */
	private Short status;
	
	// ------------------------------------------------
	
	/** 内部消息接受人 教师ID 字符串（多个以逗号相隔） */
	private  String receiveTeacherIDStr;
	
	private String className;// 相关班级
	
	private Short mailType;// 邮件类型
	
	private String replyContent;// 回复内容
	
	public String getReceiveTeacherIDStr() {
		return receiveTeacherIDStr;
	}

	public void setReceiveTeacherIDStr(String receiveTeacherIDStr) {
		this.receiveTeacherIDStr = receiveTeacherIDStr;
	}

	public Integer getInnerMsgID() {
		return innerMsgID;
	}

	public void setInnerMsgID(Integer innerMsgID) {
		this.innerMsgID = innerMsgID;
	}

	public Integer getReferClass() {
		return referClass;
	}

	public void setReferClass(Integer referClass) {
		this.referClass = referClass;
	}

	public String getReferUser() {
		return referUser;
	}

	public void setReferUser(String referUser) {
		this.referUser = referUser;
	}

	public Integer getSender() {
		return sender;
	}

	public void setSender(Integer sender) {
		this.sender = sender;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getSendTimeStr() {
		return DateUtil.getNowDateString(sendTime, "yyyy-MM-dd HH:mm");
	}

	public String getInnerMsgContent() {
		return innerMsgContent;
	}

	public void setInnerMsgContent(String innerMsgContent) {
		this.innerMsgContent = innerMsgContent;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getStatusStr() {
		if (status == null) {
			return "";
		} else {
			return Contants.status.get(status);
		}
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Short getMailType() {
		return mailType;
	}

	public void setMailType(Short mailType) {
		this.mailType = mailType;
	}

	public String getAskContent() {
		if (innerMsgContent == null) {
			return "";
		} else if (StringUtils.indexOf(innerMsgContent, "<br>") > -1) {
			return StringUtils.substring(innerMsgContent, 0,
					StringUtils.indexOf(innerMsgContent, "<br>"));
		} else {
			return innerMsgContent;
		}
	}

	public String getAskContentPart() {
		String askContent = getAskContent();
		if (askContent != null) {
			if (StringUtils.substring(askContent, 0, 20).equals(askContent)) {
				return askContent;
			} else {
				return StringUtils.substring(askContent, 0, 20) + "...";
			}
		} else {
			return "";
		}
	}

	public String getInnerMsgContentStr() {
		if (innerMsgContent == null) {
			return "";
		}
		if (StringUtils.indexOf(innerMsgContent, "<br>") == -1) {
			return "无";
		}
		String str = StringUtils.substring(innerMsgContent,
				StringUtils.indexOf(innerMsgContent, "<br>"));
		// 重新排序
		String[] arr = StringUtils.splitByWholeSeparator(str, "<br>");
		String[] arrnew = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			arrnew[i] = arr[arr.length - 1 - i];
		}
		return StringUtils.join(arrnew, "<br>");
	}

	public String getInnerMsgContentStrPart() {
		String str = getInnerMsgContentStr();
		if (StringUtils.indexOf(str, "</font>)<br>") > -1) {
			return StringUtils.substring(str, 0,
					StringUtils.indexOf(str, "</font>)<br>") + 8)
					+ "...";
		} else {
			return str;
		}
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

}
