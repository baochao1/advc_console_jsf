package com.cdel.advc.major.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.cdel.advc.major.domain.ClassNo;
import com.cdel.advc.major.domain.Major;
import com.cdel.advc.major.facade.ClassNoFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.CheckUtil;

/**
 * 辅导的当前班级数
 * 
 * @author 张苏磊
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClassNoAction extends BaseAction<Major> implements Serializable {
	@ManagedProperty(value = "#{classNoFacade}")
	private ClassNoFacade classNoFacade;

	private List<ClassNo> classNoList;// 当前班级数
	private Integer majorID;// 当前辅导ID

	/**
	 * 打开设置班级页面
	 */
	public void showClassNo(Integer majorID) {
		this.majorID = majorID;
		Map<Short, ClassNo> tmpMap = classNoFacade.getClassNoByMajor(majorID);
		classNoList = new ArrayList<ClassNo>();
		ClassNo classNo = null;
		for (int i = 1; i <= 3; i++) {
			classNo = new ClassNo();
			Short key = new Short(i + "");
			classNo.setClassType(key);
			if (i == 1) {
				classNo.setClassTypeName("实验班");
			} else if (i == 2) {
				classNo.setClassTypeName("精品班");
			} else {
				classNo.setClassTypeName("混合班");
			}
			if (tmpMap != null && tmpMap.get(key) != null) {
				classNo.setCurrClassNo(tmpMap.get(key).getCurrClassNo());
			} else {
				classNo.setCurrClassNo(new Short("0"));
			}
			classNoList.add(classNo);
		}
	}

	/**
	 * 修改updateClassNo
	 */
	public void updateClassNo(int index, int majorID) {
		ClassNo classNo = classNoList.get(index);
		Short currClassNo = classNo.getCurrClassNo();
		if (currClassNo == null
				|| !CheckUtil.checkInt(currClassNo.toString(), 3)) {
			this.addWarnMessage("info", "数字不能为空，且长度不能超过3个字符！");
			return;
		}
		try {
			classNo.setMajorID(majorID);
			classNoFacade.updateClassNo(classNo);
			this.addInfoMessage("info", SUCESSINFO);
		} catch (Exception e) {
			e.printStackTrace();
			this.addErrorMessage("info", FAILINFO);
		}
	}

	public void setClassNoFacade(ClassNoFacade classNoFacade) {
		this.classNoFacade = classNoFacade;
	}

	public List<ClassNo> getClassNoList() {
		return classNoList;
	}

	public void setClassNoList(List<ClassNo> classNoList) {
		this.classNoList = classNoList;
	}

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}

}
