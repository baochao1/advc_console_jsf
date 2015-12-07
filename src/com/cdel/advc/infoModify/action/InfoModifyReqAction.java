package com.cdel.advc.infoModify.action;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.apache.commons.lang.StringUtils;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.RowEditEvent;
import com.cdel.advc.infoModify.domain.InfoModify;
import com.cdel.advc.infoModify.facade.InfoModifyFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.StringUtil;

@SuppressWarnings("serial")
@ManagedBean
public class InfoModifyReqAction extends BaseAction<InfoModify> implements
		Serializable {
	@ManagedProperty(value = "#{infoModifyFacade}")
	private InfoModifyFacade infoModifyFacade;

	private InfoModify updateInfoModify = new InfoModify();

	/**
	 * 提交修改
	 * 
	 */
	public void updateSubmit(RowEditEvent event) {
		DataTable d = (DataTable) event.getSource();
		updateInfoModify = (InfoModify) d.getRowData();
		try {
			String txt = updateInfoModify.getMsgContent4TextArea2();
			if (StringUtils.isBlank(txt)) {
				this.addWarnMessage("info", "请输入内容！");
				return;
			}
			String newTxt = StringUtil.changeContent(txt);
			updateInfoModify.setMsgContentNew(newTxt);
			infoModifyFacade.updateContent(updateInfoModify);
			updateInfoModify.setMsgContent(newTxt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public InfoModify getUpdateInfoModify() {
		return updateInfoModify;
	}

	public void setUpdateInfoModify(InfoModify updateInfoModify) {
		this.updateInfoModify = updateInfoModify;
	}

	public void setInfoModifyFacade(InfoModifyFacade infoModifyFacade) {
		this.infoModifyFacade = infoModifyFacade;
	}

}
