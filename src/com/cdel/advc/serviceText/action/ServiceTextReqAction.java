package com.cdel.advc.serviceText.action;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.course.domain.Course;
import com.cdel.advc.serviceText.domain.ServiceText;
import com.cdel.advc.serviceText.facade.ServiceTextFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;
import com.cdel.util.ExceptionUtil;

/**
 * 学习建议
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
public class ServiceTextReqAction extends BaseAction<Course> implements
		Serializable {

	@ManagedProperty(value = "#{serviceTextFacade}")
	private ServiceTextFacade serviceTextFacade;

	private ServiceText updateServiceText = new ServiceText();
	private byte submitSuccess = 0;// 添加是否成功
	private byte flag = 0;

	/**
	 * 更新状态
	 * 
	 * @throws Exception
	 */
	public void changeStatus(Integer textID, Short status, Integer serviceID)
			throws Exception {
		ServiceTextAction sa = (ServiceTextAction) this
				.getViewAction("serviceTextAction");
		if (textID != null && status != null) {
			updateServiceText.setTextID(textID);
			if (status == 1) {
				updateServiceText.setStatus((short) 0);
			} else {
				updateServiceText.setStatus((short) 1);
			}
			updateServiceText.setMajorID(sa.getMajorID());
			updateServiceText.setCourseID(sa.getCourseID());
			updateServiceText.setServiceID(serviceID);
			if (status == 0
					&& serviceTextFacade.hasSameServiceText(updateServiceText) > 0) {
				this.addWarnMessage("info", "已经添加过该课程、阶段、服务项的学习建议！");
			} else {
				serviceTextFacade.update(updateServiceText);
				sa.search4U();
				this.addMessage("info", "设置成功！");
			}
		}
	}

	/**
	 * 添加
	 */
	public void add(byte flag) {
		this.flag = flag;
	}

	/**
	 * 查看
	 */
	public void show(Integer textID) {
		updateServiceText = serviceTextFacade.findByID(textID);
	}

	/**
	 * 修改
	 */
	public void update(byte flag, Integer textID) {
		updateServiceText = serviceTextFacade.findByID(textID);
		this.flag = flag;
	}

	/**
	 * 提交修改
	 */
	public void updateSubmit() {
		ServiceTextAction sa = (ServiceTextAction) this
				.getViewAction("serviceTextAction");
		updateServiceText.setCourseID(sa.getCourseID());
		updateServiceText.setMajorID(sa.getMajorID());
		if (serviceTextFacade.checkServiceText(flag, updateServiceText)) {
			try {
				if (flag == 0) {
					updateServiceText.setCreateTime(new Date());
					updateServiceText.setStatus((short) 1);
					serviceTextFacade.add(updateServiceText);
					sa.search();
				} else {
					serviceTextFacade.update(updateServiceText);
					sa.search4U();
				}
				submitSuccess = 1;
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(ExceptionUtil.getEMsg(e));
				submitSuccess = -1;
			}
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public Map<Short, String> getStatus() {
		return Contants.status;
	}

	public void setServiceTextFacade(ServiceTextFacade serviceTextFacade) {
		this.serviceTextFacade = serviceTextFacade;
	}

	public ServiceText getUpdateServiceText() {
		return updateServiceText;
	}

	public void setUpdateServiceText(ServiceText updateServiceText) {
		this.updateServiceText = updateServiceText;
	}

	public byte getFlag() {
		return flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

}
