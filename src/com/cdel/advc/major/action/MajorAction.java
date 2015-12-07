package com.cdel.advc.major.action;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.major.domain.Major;
import com.cdel.advc.major.facade.MajorFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.CheckUtil;
import com.cdel.util.ExceptionUtil;

/**
 * 辅导action
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MajorAction extends BaseAction<Major> implements Serializable {

	@ManagedProperty(value = "#{majorFacade}")
	private MajorFacade majorFacade;

	private LazyDataModel<Major> majorPage;
	private Major searchMajor = new Major();// 搜索的课程
	private String url = "";// 要加载的小页面
	private Integer siteID;// 网站ID
	private Major updateMajor;// 修改的辅导
	private byte submitSuccess = 0;// 修改是否成功

	@PostConstruct
	public void initMajorAction() {
		url = "../grant/null.xhtml";
		siteID = this.getCurrentSiteID();
		searchMajor.setBusinessID(siteID);
		majorPage = majorFacade.findPage(searchMajor);
	}

	/**
	 * 查询
	 */
	public void search() {
		pageTable.setFirst(0);
		majorPage = majorFacade.findPage(searchMajor);
		this.updateComponent("searchForm:majorList");
	}

	/**
	 * 编辑
	 */
	public void update(Integer majorID) {
		url = "load/majorUpdate.xhtml";
		updateMajor = majorFacade.findByID(majorID);
	}

	/**
	 * 修改辅导
	 */
	public void updateSubmit() {
		Short reportDateSpace = updateMajor.getReportDateSpace();
		if (reportDateSpace == null
				|| !CheckUtil.checkInt(reportDateSpace.toString(), 3)) {
			this.addWarnMessage("学习报告生成间隔时间不能为空，且必须为数字,且长度不能超过3个字符！");
			return;
		}
		try {
			majorFacade.update(updateMajor);
			submitSuccess = 1;
			majorPage = majorFacade.findPage(searchMajor);
			this.updateComponent("searchForm:majorList");
			updateMajor = null;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateMajor=" + updateMajor);
			logger.error(ExceptionUtil.getEMsg(e));
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public void setMajorFacade(MajorFacade majorFacade) {
		this.majorFacade = majorFacade;
	}

	public LazyDataModel<Major> getMajorPage() {
		return majorPage;
	}

	public void setMajorPage(LazyDataModel<Major> majorPage) {
		this.majorPage = majorPage;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Major getSearchMajor() {
		return searchMajor;
	}

	public void setSearchMajor(Major searchMajor) {
		this.searchMajor = searchMajor;
	}

	public Major getUpdateMajor() {
		return updateMajor;
	}

	public void setUpdateMajor(Major updateMajor) {
		this.updateMajor = updateMajor;
	}

	public Integer getSiteID() {
		return siteID;
	}

}
