package com.cdel.advc.gdb.materials.action;

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
import com.cdel.advc.gdb.materials.domain.GdbMaterials;
import com.cdel.advc.gdb.materials.facade.GdbMaterialsFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.FileUpload;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class GdbMaterialsAction extends BaseAction<GdbMaterials> implements
		Serializable {
	@ManagedProperty(value = "#{gdbMaterialsFacade}")
	private GdbMaterialsFacade gdbMaterialsFacade;
	@ManagedProperty(value = "#{fileUpload}")
	private FileUpload fileUpload;

	private LazyDataModel<GdbMaterials> gdbMaterialsPage;
	@SuppressWarnings("unused")
	private Integer siteID;// 网站ID
	private GdbMaterials searchGdbMaterials = new GdbMaterials();
	private List<String> filePath = new ArrayList<String>();// 文件路径
	private StringBuffer fileNames = new StringBuffer("");// 上传成功的文件名

	@PostConstruct
	public void initTechnologyMsgAction() {
		siteID = this.getCurrentSiteID();
		this.gdbMaterialsPage = this.gdbMaterialsFacade
				.findPage(searchGdbMaterials);
		super.heighti2 = super.calHeight(11.5f / 20);
	}

	/**
	 * 查询
	 * 
	 * @throws Exception
	 */
	public void search() {
		pageTable.setFirst(0);
		this.gdbMaterialsPage = this.gdbMaterialsFacade
				.findPage(searchGdbMaterials);
		this.updateComponent("searchForm:gdbMaterialsList");
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
		try {
			result = fileUpload.upload(uploadedFile.getInputstream(), fileName,
					this.getRequest().getContentType(), "QZ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result.equals("error")) {
			this.addErrorMessage("info", fileName + "上传失败！");
		} else if (result.equals("size")) {
			this.addWarnMessage("info", fileName + "文件太大,不能超过10M！");
		} else {
			this.addMessage("info", fileName + "上传成功！");
			filePath.add(result);
			fileNames.append(fileName).append(",");
		}
	}

	public LazyDataModel<GdbMaterials> getGdbMaterialsPage() {
		return gdbMaterialsPage;
	}

	public void setGdbMaterialsPage(LazyDataModel<GdbMaterials> gdbMaterialsPage) {
		this.gdbMaterialsPage = gdbMaterialsPage;
	}

	public GdbMaterials getSearchGdbMaterials() {
		return searchGdbMaterials;
	}

	public void setSearchGdbMaterials(GdbMaterials searchGdbMaterials) {
		this.searchGdbMaterials = searchGdbMaterials;
	}

	public void setGdbMaterialsFacade(GdbMaterialsFacade gdbMaterialsFacade) {
		this.gdbMaterialsFacade = gdbMaterialsFacade;
	}

	public void setFileUpload(FileUpload fileUpload) {
		this.fileUpload = fileUpload;
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

}
