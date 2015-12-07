package com.cdel.advc.strategy.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.cdel.advc.strategy.domain.Strategy;
import com.cdel.advc.strategy.domain.StrategyOrder;
import com.cdel.advc.strategy.facade.StrategyOrderFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.StringUtil;

@SuppressWarnings("serial")
@ManagedBean
public class StrategyOrderReqAction extends BaseAction<Strategy> implements
		Serializable {
	@ManagedProperty(value = "#{strategyOrderFacade}")
	private StrategyOrderFacade strategyOrderFacade;

	private List<StrategyOrder> soList = new ArrayList<StrategyOrder>();

	/**
	 * 修改排序
	 * 
	 * @param strategyID
	 */
	public void changeSeq(Integer strategyID, String courseIDs) {
		// 去掉前后的逗号 
		courseIDs = StringUtil.filterStringDouhao(courseIDs);
		StrategyOrder strategyOrder = new StrategyOrder();
		strategyOrder.setStrategyID(strategyID);
		strategyOrder.setCourseIDs(courseIDs);
		soList = strategyOrderFacade.findList(strategyOrder);
	}

	/**
	 * 提交修改排序
	 */
	public void updateSubmit() {
		byte submitSuccess = 0;// 修改是否成功
		try {
			// 设置顺序
			if (soList != null && soList.size() > 0) {
				for (int i = 0; i < soList.size(); i++) {
					StrategyOrder so = soList.get(i);
					so.setStatus((short) 1);
					so.setSequence(i + 1);
				}
			}
			strategyOrderFacade.update(soList);
			submitSuccess = 1;
		} catch (Exception e) {
			e.printStackTrace();
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public void setStrategyOrderFacade(StrategyOrderFacade strategyOrderFacade) {
		this.strategyOrderFacade = strategyOrderFacade;
	}

	public List<StrategyOrder> getSoList() {
		return soList;
	}

	public void setSoList(List<StrategyOrder> soList) {
		this.soList = soList;
	}

}