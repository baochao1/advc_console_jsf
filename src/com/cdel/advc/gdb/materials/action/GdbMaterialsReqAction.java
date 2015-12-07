package com.cdel.advc.gdb.materials.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.apache.commons.lang.StringUtils;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.RowEditEvent;
import com.cdel.advc.gdb.materials.domain.GdbMaterials;
import com.cdel.advc.gdb.materials.facade.GdbMaterialsFacade;
import com.cdel.qz.course.facade.QzCourseFacade;
import com.cdel.qz.siteCourse.action.QzSiteCourseOtherInit;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;
import com.cdel.util.ExceptionUtil;

@SuppressWarnings("serial")
@ManagedBean
public class GdbMaterialsReqAction extends BaseAction<GdbMaterials> implements
		Serializable {
	@ManagedProperty(value = "#{gdbMaterialsFacade}")
	private GdbMaterialsFacade gdbMaterialsFacade;
	@ManagedProperty(value = "#{qzCourseFacade}")
	private QzCourseFacade qzCourseFacade;

	private GdbMaterials updateGdbMaterials = new GdbMaterials();
	private byte submitSuccess = 0;// 修改是否成功

	/**
	 * 修改状态
	 */
	public void changeStatus(Integer materialsID, Integer status) {
		updateGdbMaterials.setMaterialsID(materialsID);
		Short newStatus;
		if (status == 1) {
			newStatus = 0;
		} else {
			newStatus = 1;
		}
		updateGdbMaterials.setStatus(newStatus);
		try {
			gdbMaterialsFacade.update(updateGdbMaterials);
			this.addInfoMessage("info", SUCESSINFO);
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
		}
	}

	/**
	 * 添加资料
	 */
	public void add() {
		GdbMaterialsAction gdbMaterialsAction = (GdbMaterialsAction) this
				.getViewAction("gdbMaterialsAction");
		gdbMaterialsAction.setFileNames(new StringBuffer(""));
		gdbMaterialsAction.setFilePath(new ArrayList<String>());
	}

	/**
	 * 修改提交
	 * 
	 * @param event
	 */
	public void update(RowEditEvent event) {
		DataTable d = (DataTable) event.getSource();
		updateGdbMaterials = (GdbMaterials) d.getRowData();
		try {
			if (gdbMaterialsFacade
					.checkData((byte) 1, null, updateGdbMaterials)) {
				gdbMaterialsFacade.update(updateGdbMaterials);
				QzSiteCourseOtherInit qza = (QzSiteCourseOtherInit) this
						.getViewAction("qzSiteCourseOtherInit");
				updateGdbMaterials.setSiteCourseName(qzCourseFacade
						.getSiteCourseName(qza.getSiteCourseList(),
								updateGdbMaterials.getSiteCourseID()));
				this.addInfoMessage("info", SUCESSINFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
			logger.error("updateGdbMaterials=" + updateGdbMaterials);
			logger.error(ExceptionUtil.getEMsg(e));
		}
	}

	/**
	 * 添加提交
	 */
	public void updateSubmit() {
		GdbMaterialsAction gdbMaterialsAction = (GdbMaterialsAction) this
				.getViewAction("gdbMaterialsAction");
		List<String> filePath = gdbMaterialsAction.getFilePath();
		String fileNames = gdbMaterialsAction.getFileNames();
		String[] arr = StringUtils.split(fileNames, ",");
		byte nameflag = 1;// 是否输入了文件名
		if (StringUtils.isBlank(updateGdbMaterials.getMaterialName())) {
			nameflag = 0;
		}
		if (gdbMaterialsFacade
				.checkData((byte) 0, filePath, updateGdbMaterials)) {
			try {
				for (int i = 0; i < filePath.size(); i++) {
					updateGdbMaterials.setUploadPath(filePath.get(i));
					if (i <= arr.length - 1) {
						if (nameflag == 0) {
							updateGdbMaterials.setMaterialName(arr[i]
									.substring(0, arr[i].lastIndexOf(".")));
						}
						updateGdbMaterials.setCreator(this.getCurrentUserID());
						updateGdbMaterials.setUploadTime(new Date());
						updateGdbMaterials.setStatus((short) 1);
						updateGdbMaterials.setRealName(this
								.getAttribute(Contants.ADMIN_REAL_NAME));
						gdbMaterialsFacade.add(updateGdbMaterials);
					}
				}
				gdbMaterialsAction.search();
				submitSuccess = 1;
			} catch (Exception e) {
				e.printStackTrace();
				submitSuccess = -1;
				logger.error("updateGdbMaterials=" + updateGdbMaterials);
				logger.error(ExceptionUtil.getEMsg(e));
			}
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public GdbMaterials getUpdateGdbMaterials() {
		return updateGdbMaterials;
	}

	public void setUpdateGdbMaterials(GdbMaterials updateGdbMaterials) {
		this.updateGdbMaterials = updateGdbMaterials;
	}

	public void setGdbMaterialsFacade(GdbMaterialsFacade gdbMaterialsFacade) {
		this.gdbMaterialsFacade = gdbMaterialsFacade;
	}

	public void setQzCourseFacade(QzCourseFacade qzCourseFacade) {
		this.qzCourseFacade = qzCourseFacade;
	}

}
