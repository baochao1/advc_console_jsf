package com.cdel.advc.msconf.action;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.msconf.domain.Msconf;
import com.cdel.advc.msconf.facade.MsconfFacade;
import com.cdel.util.BaseAction;

/**
 * 面授班管理
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
public class MsconfReqAction extends BaseAction<Msconf> implements Serializable {

	@ManagedProperty(value = "#{msconfFacade}")
	private MsconfFacade msconfFacade;

	private Msconf msconf = new Msconf();
	private byte submitSuccess = 0;// 添加是否成功

	/**
	 * 显示面授班修改页面
	 */
	public void update(Integer classID) {
		msconf = msconfFacade.findByID(classID);
	}

	/**
	 * 提交修改
	 */
	public void updateSubmit() {
		try {
			msconfFacade.update(msconf);
			submitSuccess = 1;
		} catch (Exception e) {
			submitSuccess = -1;
			e.printStackTrace();
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public void setMsconfFacade(MsconfFacade msconfFacade) {
		this.msconfFacade = msconfFacade;
	}

	public Msconf getMsconf() {
		return msconf;
	}

	public void setMsconf(Msconf msconf) {
		this.msconf = msconf;
	}

}
