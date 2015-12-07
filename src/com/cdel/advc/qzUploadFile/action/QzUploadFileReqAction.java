package com.cdel.advc.qzUploadFile.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.gdb.materials.domain.GdbMaterials;
import com.cdel.advc.qzUploadFile.domain.QzUploadFile;
import com.cdel.advc.qzUploadFile.facade.QzUploadFileFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.ExceptionUtil;

@SuppressWarnings("serial")
@ManagedBean
public class QzUploadFileReqAction extends BaseAction<GdbMaterials> implements
		Serializable {
	@ManagedProperty(value = "#{qzUploadFileFacade}")
	private QzUploadFileFacade qzUploadFileFacade;

	private QzUploadFile qzUploadFile = new QzUploadFile();
	private QzUploadFile updateQzUploadFile = new QzUploadFile();

	private byte submitSuccess = 0;// 修改是否成功

	/**
	 * 添加资料
	 */
	public void add() {
		QzUploadFileAction qzUploadFileAction = (QzUploadFileAction) this
				.getViewAction("qzUploadFileAction");
		qzUploadFileAction.setFileNames(new StringBuffer(""));
		qzUploadFileAction.setFilePath(new ArrayList<String>());
	}

	/**
	 * 添加提交
	 */
	public void updateSubmit() {
		QzUploadFileAction qzUploadFileAction = (QzUploadFileAction) this
				.getViewAction("qzUploadFileAction");
		List<String> filePath = qzUploadFileAction.getFilePath();

		if (qzUploadFileFacade.checkData((byte) 0, filePath)) {
			try {
				for (int i = 0; i < filePath.size(); i++) {
					updateQzUploadFile.setFileUrl(filePath.get(i));
					updateQzUploadFile.setCreator(this.getCurrentUserID());
					qzUploadFileFacade.add(updateQzUploadFile);
				}
				qzUploadFileAction.search();
				submitSuccess = 1;
			} catch (Exception e) {
				e.printStackTrace();
				submitSuccess = -1;
				logger.error("updateQzUploadFile=" + updateQzUploadFile);
				logger.error(ExceptionUtil.getEMsg(e));
			}
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public QzUploadFileFacade getQzUploadFileFacade() {
		return qzUploadFileFacade;
	}

	public void setQzUploadFileFacade(QzUploadFileFacade qzUploadFileFacade) {
		this.qzUploadFileFacade = qzUploadFileFacade;
	}

	public QzUploadFile getQzUploadFile() {
		return qzUploadFile;
	}

	public void setQzUploadFile(QzUploadFile qzUploadFile) {
		this.qzUploadFile = qzUploadFile;
	}

	public QzUploadFile getUpdateQzUploadFile() {
		return updateQzUploadFile;
	}

	public void setUpdateQzUploadFile(QzUploadFile updateQzUploadFile) {
		this.updateQzUploadFile = updateQzUploadFile;
	}

	public byte getSubmitSuccess() {
		return submitSuccess;
	}

	public void setSubmitSuccess(byte submitSuccess) {
		this.submitSuccess = submitSuccess;
	}
}
