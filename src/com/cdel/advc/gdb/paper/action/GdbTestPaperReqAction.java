package com.cdel.advc.gdb.paper.action;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.cdel.advc.gdb.paper.domain.GdbTestPaper;
import com.cdel.advc.gdb.paper.facade.GdbTestPaperFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;
import com.cdel.util.DateUtil;

@SuppressWarnings("serial")
@ManagedBean
public class GdbTestPaperReqAction extends BaseAction<GdbTestPaper> implements
		Serializable {

	@ManagedProperty(value = "#{gdbTestPaperFacade}")
	private GdbTestPaperFacade gdbTestPaperFacade;

	private GdbTestPaper addPaper = new GdbTestPaper();// 添加/ 修改对象
	private byte submitSuccess = 0;// 添加是否成功
	private byte flag = -1;// 添加/编辑状态,0添加,1编辑

	/**
	 * 打开添加页面
	 * 
	 * @param id
	 */
	public void add() {
		flag = 0;
	}

	/**
	 * 打开修改页面
	 * 
	 * @param id
	 */
	public void update(Integer id) {
		flag = 1;
		addPaper = gdbTestPaperFacade.findByID(id);
	}

	/**
	 * 添加/修改
	 * 
	 * @param flag
	 */
	public void updateSubmit() {
		GdbTestPaperAction ga = (GdbTestPaperAction) this
				.getViewAction("gdbTestPaperAction");
		Integer studyPlanID = ga.getStudyPlanID();
		boolean f = false;
		if (flag == 0) {
			addPaper.setStudyPlanID(studyPlanID);
		}
		f = gdbTestPaperFacade.check(addPaper, flag);
		if (f) {
			try {
				if (flag == 0) {
					this.executeScript("add("
							+ studyPlanID
							+ ",'"
							+ addPaper.getPaperName()
							+ "',"
							+ addPaper.getPaperType()
							+ ",'"
							+ DateUtil.getNowDateString(addPaper.getTestTime(),
									"yyyy-MM-dd HH:mm:ss") + "','"
							+ addPaper.getPaperDesc() + "'," + ga.getCourseID()
							+ ",'" + Contants.qzConsoleSavePaper + "',"
							+ this.getCurrentUserID() + ")");
				} else {
					gdbTestPaperFacade.update(addPaper);
					submitSuccess = 1;
					ga.search4U();
					this.executeScript("optResultjs(" + submitSuccess + ","
							+ flag + ")");
				}
			} catch (Exception e) {
				submitSuccess = -1;
				this.executeScript("optResultjs(" + flag + ")");
				e.printStackTrace();
			}
		}
	}

	/**
	 * 在题库保存完试卷后，回存高端班试卷
	 */
	public void savePaper() {
		GdbTestPaperAction ga = (GdbTestPaperAction) this
				.getViewAction("gdbTestPaperAction");
		try {
			Integer paperID = this.getIntegerRequestParameterByMap("paperID");
			Integer paperViewID = this
					.getIntegerRequestParameterByMap("paperViewID");
			Integer studyPlanID = this
					.getIntegerRequestParameterByMap("studyPlanID");
			String paperName = this.getRequestParameterByMap("paperName");
			Integer paperType = this
					.getIntegerRequestParameterByMap("paperType");
			String testTime = this.getRequestParameterByMap("testTime");
			String paperDesc = this.getRequestParameterByMap("paperDesc");
			addPaper = new GdbTestPaper();
			addPaper.setPaperID(paperID);
			addPaper.setPaperViewID(paperViewID);
			addPaper.setStudyPlanID(studyPlanID);
			addPaper.setPaperType(paperType.shortValue());
			addPaper.setTestTime(DateUtil.stringToDate(testTime,
					"yyyy-MM-dd HH:mm:ss"));
			addPaper.setPaperName(paperName);
			addPaper.setCreateUserId(this.getCurrentUserID());
			addPaper.setCreateTime(new Date());
			addPaper.setPaperDesc(paperDesc);
			gdbTestPaperFacade.add(addPaper);
			submitSuccess = 1;
			ga.search();
		} catch (Exception e) {
			e.printStackTrace();
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public Map<Short, String> getPaperTypes() {
		return Contants.gdbPaperType;
	}

	public GdbTestPaper getAddPaper() {
		return addPaper;
	}

	public void setAddPaper(GdbTestPaper addPaper) {
		this.addPaper = addPaper;
	}

	public void setGdbTestPaperFacade(GdbTestPaperFacade gdbTestPaperFacade) {
		this.gdbTestPaperFacade = gdbTestPaperFacade;
	}

	public byte getFlag() {
		return flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

}
