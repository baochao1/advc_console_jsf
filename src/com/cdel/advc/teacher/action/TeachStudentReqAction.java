package com.cdel.advc.teacher.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.primefaces.model.DualListModel;
import com.cdel.advc.gdb.member.domain.AdvanceMember;
import com.cdel.advc.gdb.member.facade.AdvanceMemberFacade;
import com.cdel.advc.major.action.MajorOther;
import com.cdel.advc.teacher.domain.Teacher;
import com.cdel.util.BaseAction;
import com.cdel.util.ExceptionUtil;

@ManagedBean
@SuppressWarnings("serial")
public class TeachStudentReqAction extends BaseAction<Teacher> implements
		Serializable {

	@ManagedProperty(value = "#{advanceMemberFacade}")
	private AdvanceMemberFacade advanceMemberFacade;

	private DualListModel<AdvanceMember> students = new DualListModel<AdvanceMember>();
	private Integer teacherID;
	private Integer majorID;

	/**
	 * 管理老师负责的学员
	 * 
	 * @param teacherID
	 */
	public void showTeachStudent(Integer teacherID) {
		this.teacherID = teacherID;
		MajorOther mo = (MajorOther) this.getViewAction("majorOther");
		TeacherAction ta = (TeacherAction) this.getViewAction("teacherAction");
		if (mo.getMajorList() == null) {
			mo.setMajorList(ta.getSiteID());
		}
	}

	/**
	 * 查询高端班学员
	 */
	public void search() {
		if (majorID == null) {
			this.addWarnMessage("info", "请选择辅导！");
			return;
		}
		AdvanceMember am = new AdvanceMember();
		am.setMajorID(majorID);
		am.setTeacherID(teacherID);
		List<AdvanceMember> mlist = advanceMemberFacade.findList(am);
		List<AdvanceMember> source = new ArrayList<AdvanceMember>();
		List<AdvanceMember> target = new ArrayList<AdvanceMember>();
		if (mlist != null && mlist.size() > 0) {
			for (int i = 0; i < mlist.size(); i++) {
				AdvanceMember e = mlist.get(i);
				if (e.getTeacherID() != null) {
					target.add(e);
				} else {
					source.add(e);
				}
			}
		}
		students.setSource(source);
		students.setTarget(target);
	}

	/**
	 * 提交
	 */
	public void submitTeachStudent() {
		byte submitSuccess = 0;// 添加是否成功
		if ((students.getSource() == null || students.getSource().size() == 0)
				&& (students.getTarget() == null || students.getTarget().size() == 0)) {
			this.addWarnMessage("info", "对不起，没有学员信息，请先进行查询！");
			return;
		}
		List<AdvanceMember> target = students.getTarget();
		if (target != null && target.size() > 0) {
			for (int i = 0; i < target.size(); i++) {
				AdvanceMember am = target.get(i);
				am.setTeacherID(teacherID);
			}
		}
		try {
			advanceMemberFacade.deleteTeacherMember(teacherID);
			advanceMemberFacade.update(target);
			students.getTarget();
			submitSuccess = 1;
		} catch (Exception e) {
			e.printStackTrace();
			submitSuccess = -1;
			logger.error("target=" + target);
			logger.error(ExceptionUtil.getEMsg(e));
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public DualListModel<AdvanceMember> getStudents() {
		return students;
	}

	public void setStudents(DualListModel<AdvanceMember> students) {
		this.students = students;
	}

	public Integer getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(Integer teacherID) {
		this.teacherID = teacherID;
	}

	public Integer getMajorID() {
		return majorID;
	}

	public void setMajorID(Integer majorID) {
		this.majorID = majorID;
	}

	public void setAdvanceMemberFacade(AdvanceMemberFacade advanceMemberFacade) {
		this.advanceMemberFacade = advanceMemberFacade;
	}

}
