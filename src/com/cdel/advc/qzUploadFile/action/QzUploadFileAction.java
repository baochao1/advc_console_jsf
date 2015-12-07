package com.cdel.advc.qzUploadFile.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.UploadedFile;

import com.cdel.advc.qzUploadFile.domain.QzUploadFile;
import com.cdel.advc.qzUploadFile.facade.QzUploadFileFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.FileUpload;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class QzUploadFileAction extends BaseAction<QzUploadFile> implements Serializable{
	
	@ManagedProperty(value = "#{qzUploadFileFacade}")
	private QzUploadFileFacade qzUploadFileFacade;
	
	private LazyDataModel<QzUploadFile> qzUploadFilePage;
	private QzUploadFile qzUploadFile = new QzUploadFile();
	
	private List<String> filePath = new ArrayList<String>();// 文件路径
	private StringBuffer fileNames = new StringBuffer("");// 上传成功的文件名
	
	@ManagedProperty(value = "#{fileUpload}")
	private FileUpload fileUpload;

	/*
	 * 初始化
	 * */
	@PostConstruct
	public void initQzUploadFileAction() {
		this.qzUploadFilePage = this.qzUploadFileFacade.findPage(qzUploadFile);
		super.heighti2 = super.calHeight(12.5f / 20);
	}
	
	/**
	 * 查询
	 * 
	 * @throws Exception
	 */
	public void search() {
		pageTable.setFirst(0);
		this.qzUploadFilePage = this.qzUploadFileFacade.findPage(qzUploadFile);
		this.updateComponent("searchForm:qzUploadFileList");
	}
	
	/**
	 * 上传
	 * 
	 * @param event
	 */
	public void handleFileUpload(FileUploadEvent event) {
		UploadedFile uploadedFile = event.getFile();
		String fileName = uploadedFile.getFileName();
		String result = "";
		if ((fileName.endsWith(".gif"))
				|| (fileName.endsWith(".GIF"))
				|| (fileName.endsWith(".JPEG"))
				|| (fileName.endsWith(".jpeg"))
				|| (fileName.endsWith(".png"))
				|| (fileName.endsWith(".jpg"))
				|| (fileName.endsWith(".JPG"))
				|| (fileName.endsWith(".PNG"))
		){
			try {
				result = fileUpload.upload(uploadedFile.getInputstream(), fileName, this.getRequest().getContentType(), "QZ");
			} catch (Exception e) {
				result = "error";
				e.printStackTrace();
			}
			if (result.equals("error")) {
				this.addErrorMessage("info", fileName + "上传失败！");
			} else if (result.equals("size")) {
				this.addWarnMessage("info", fileName + "文件太大,不能超过10M！");
			} else {
				this.addMessage("info", fileName + "上传成功！");
				filePath.add(result);
				fileNames.append(fileName);
			}
		}else{
			this.addWarnMessage("info", " 文件格式只能是gif,jpg,jpeg,png类型的！");
		}
	}


	public QzUploadFileFacade getQzUploadFileFacade() {
		return qzUploadFileFacade;
	}

	public void setQzUploadFileFacade(QzUploadFileFacade qzUploadFileFacade) {
		this.qzUploadFileFacade = qzUploadFileFacade;
	}

	public LazyDataModel<QzUploadFile> getQzUploadFilePage() {
		return qzUploadFilePage;
	}

	public void setQzUploadFilePage(LazyDataModel<QzUploadFile> qzUploadFilePage) {
		this.qzUploadFilePage = qzUploadFilePage;
	}

	public QzUploadFile getQzUploadFile() {
		return qzUploadFile;
	}

	public void setQzUploadFile(QzUploadFile qzUploadFile) {
		this.qzUploadFile = qzUploadFile;
	}

	public String getFileNames() {
		return fileNames.toString();
	}

	public void setFileNames(StringBuffer fileNames) {
		this.fileNames = fileNames;
	}

	public List<String> getFilePath() {
		return filePath;
	}

	public void setFilePath(List<String> filePath) {
		this.filePath = filePath;
	}

	public void setFileUpload(FileUpload fileUpload) {
		this.fileUpload = fileUpload;
	}

}
