package com.cdel.advc.coursetimeLong.action;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.primefaces.event.CellEditEvent;
import com.cdel.advc.coursetimeLong.domain.CoursetimeLong;
import com.cdel.advc.coursetimeLong.facade.CoursetimeLongFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.CheckUtil;

@SuppressWarnings("serial")
@ManagedBean
public class CoursetimeLongReqAction extends BaseAction<CoursetimeLong>
		implements Serializable {
	@ManagedProperty(value = "#{coursetimeLongFacade}")
	private CoursetimeLongFacade coursetimeLongFacade;

	private CoursetimeLong updateCoursetimeLong = new CoursetimeLong();

	/**
	 * 修改
	 * 
	 */
	public void update(CellEditEvent event) {
		byte submitSuccess = 0;// 修改是否成功
		try {
			Integer timeLong = (Integer) event.getNewValue();
			if (timeLong == null || !CheckUtil.checkInt(timeLong.toString(), 5)) {
				this.addWarnMessage("info", "只能输入数字，不得大于5位，数据未提交，请重新输入！");
				return;
			}
			CoursetimeLongAction coursetimeLongAction = (CoursetimeLongAction) this
					.getViewAction("coursetimeLongAction");
			updateCoursetimeLong = coursetimeLongAction.getCoursetimeLongPage()
					.getRowData();
			updateCoursetimeLong.setOp(this.getCurrentUserID());
			updateCoursetimeLong.setTimeLong(timeLong);
			coursetimeLongFacade.update(updateCoursetimeLong);
			submitSuccess = 1;
		} catch (Exception e) {
			e.printStackTrace();
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 删除
	 * 
	 */
	public void delete(Integer timeLongID) {
		try {
			CoursetimeLongAction coursetimeLongAction = (CoursetimeLongAction) this
					.getViewAction("coursetimeLongAction");
			coursetimeLongFacade.delete(timeLongID);
			coursetimeLongAction.search();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 重新统计
	 * 
	 * @param timeLongID
	 * @param courseID
	 * @param countDate
	 * @throws Exception
	 */
	public void statAgain(Integer timeLongID, Integer courseID, String countDate)
			throws Exception {
		Integer avgTime = coursetimeLongFacade.getAvgTimeLong(courseID,
				countDate);
		updateCoursetimeLong.setTimeLongID(timeLongID);
		updateCoursetimeLong.setTimeLong(avgTime);
		updateCoursetimeLong.setOp(this.getCurrentUserID());
		coursetimeLongFacade.update(updateCoursetimeLong);
		CoursetimeLongAction coursetimeLongAction = (CoursetimeLongAction) this
				.getViewAction("coursetimeLongAction");
		coursetimeLongAction.search4U();
	}

	public CoursetimeLong getUpdateCoursetimeLong() {
		return updateCoursetimeLong;
	}

	public void setUpdateCoursetimeLong(CoursetimeLong updateCoursetimeLong) {
		this.updateCoursetimeLong = updateCoursetimeLong;
	}

	public void setCoursetimeLongFacade(
			CoursetimeLongFacade coursetimeLongFacade) {
		this.coursetimeLongFacade = coursetimeLongFacade;
	}

}
