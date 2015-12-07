package com.cdel.advc.major.action;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.major.domain.Major;
import com.cdel.advc.major.facade.MajorFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;
import com.cdel.util.RemoteUtil;

/**
 * 辅导action
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
public class MajorReqAction extends BaseAction<Major> implements Serializable {
	@ManagedProperty(value = "#{majorFacade}")
	private MajorFacade majorFacade;

	private Integer siteID;
	private Integer majorID;
	private String majorName;
	private String roomTitle;// 聊天室标题
	private String roomDesc;// 聊天室描述
	private Short roomeType;// 聊天室类型
	private Date reportDate;// 学习报告日期
	private byte submitSuccess = 0;// 修改是否成功

	/**
	 * 修改状态
	 */
	public void changeStatus(Integer majorID, Integer status) {
		Major m = new Major();
		m.setMajorID(majorID);
		Short newStatus;
		if (status == 1) {
			newStatus = 0;
		} else {
			newStatus = 1;
		}
		m.setStatus(newStatus);
		try {
			majorFacade.update(m);
			this.addInfoMessage("info", SUCESSINFO);
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
		}
	}

	/**
	 * 修改聊天室状态
	 * 
	 * @param majorID
	 * @param status
	 */
	public void changeRoomStatus(Integer majorID, Integer status) {
		Major m = new Major();
		m.setMajorID(majorID);
		Short newStatus;
		if (status == 1) {
			newStatus = 0;
		} else {
			newStatus = 1;
		}
		m.setEnterRoom(newStatus);
		try {
			majorFacade.update(m);
			this.addInfoMessage("info", SUCESSINFO);
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
		}
	}

	/**
	 * 建立聊天室
	 * 
	 * @param majorID
	 * @param majorName
	 */
	public void showRoomDialog(Integer siteID, Integer majorID, String majorName) {
		this.siteID = siteID;
		this.majorID = majorID;
		this.majorName = majorName;
		this.roomTitle = majorName + "聊天室";
	}

	/**
	 * 建立聊天室
	 */
	public void addRoomSubmit() {
		if (roomTitle == null || roomTitle.equals("")) {
			this.addWarnMessage("聊天室标题不能为空！");
			return;
		}
		try {
			String server = "http://talk.chinaacc.com/server/createroom.php";
			String sendData;
			if (siteID == 1) {
				// 会计
				sendData = "?id=major" + majorID + "&title=" + roomTitle
						+ "&desc=" + roomDesc + "&type=" + roomeType;
			} else {
				// 其他
				sendData = "?id=major_" + siteID + "_" + majorID + "&title="
						+ roomTitle + "&desc=" + roomDesc + "&type="
						+ roomeType + "&siteID=" + siteID;
			}
			String result = RemoteUtil.getRemoteString(server, sendData, "GBK");
			if (result.startsWith("{\"ret\":1")) {
				Major major = new Major();
				major.setMajorID(majorID);
				major.setRoomStatus(Short.parseShort("1"));
				majorFacade.update(major);
				MajorAction majorAction = (MajorAction) this
						.getViewAction("majorAction");
				majorAction.search();
			}
			submitSuccess = 1;
		} catch (Exception e) {
			e.printStackTrace();
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 修改学习报告状态
	 * 
	 * @param majorID
	 * @param status
	 */
	public void changeReportStatus(Integer majorID, Integer status) {
		Major m = new Major();
		m.setMajorID(majorID);
		Short newStatus;
		if (status == 1) {
			newStatus = 0;
		} else {
			newStatus = 1;
		}
		m.setReportStatus(newStatus);
		try {
			majorFacade.update(m);
			this.addInfoMessage("info", SUCESSINFO);
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
		}
		this.addCallbackParam("result", newStatus);
	}

	/**
	 * 更新学习报告生成时间
	 */
	public void addRepDateSubmit() {
		MajorAction ma = (MajorAction) this.getViewAction("majorAction");
		Major major = ma.getMajorPage().getRowData();
		if (major.getReportDate() == null) {
			this.addWarnMessage("info", "生成时间不能为空！");
			return;
		}
		try {
			majorFacade.update(major);
			this.addMessage("info", SUCESSINFO);
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
		}
	}

	public Map<Short, String> getStatus() {
		return Contants.status;
	}

	public Map<Short, String> getIsNewService() {
		return Contants.yesorno;
	}

	public void setMajorFacade(MajorFacade majorFacade) {
		this.majorFacade = majorFacade;
	}

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public Short getRoomeType() {
		return roomeType;
	}

	public void setRoomeType(Short roomeType) {
		this.roomeType = roomeType;
	}

	public Map<Short, String> getRoomTypeMap() {
		return new LinkedHashMap<Short, String>() {
			{
				put(Short.parseShort("1"), "公开房间");
				put(Short.parseShort("2"), "私有房间");
			}
		};
	}

	public String getRoomTitle() {
		return roomTitle;
	}

	public void setRoomTitle(String roomTitle) {
		this.roomTitle = roomTitle;
	}

	public String getRoomDesc() {
		return roomDesc;
	}

	public void setRoomDesc(String roomDesc) {
		this.roomDesc = roomDesc;
	}

	public Integer getSiteID() {
		return siteID;
	}

	public void setSiteID(Integer siteID) {
		this.siteID = siteID;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

}
