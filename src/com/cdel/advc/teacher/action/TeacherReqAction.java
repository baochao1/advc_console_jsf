package com.cdel.advc.teacher.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UISelectOne;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.RowEditEvent;
import com.cdel.advc.classes.domain.Classes;
import com.cdel.advc.classes.facade.ClassesFacade;
import com.cdel.advc.classteacher.facade.ClassteacherFacade;
import com.cdel.advc.major.action.MajorOther;
import com.cdel.advc.teacher.domain.Teacher;
import com.cdel.advc.teacher.facade.TeacherFacade;
import com.cdel.advc.testterm.facade.TesttermFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;

@ManagedBean
@SuppressWarnings("serial")
public class TeacherReqAction extends BaseAction<Teacher> implements
		Serializable {

	@ManagedProperty(value = "#{teacherFacade}")
	private TeacherFacade teacherFacade;
	@ManagedProperty(value = "#{classesFacade}")
	private ClassesFacade classesFacade;
	@ManagedProperty(value = "#{testtermFacade}")
	private TesttermFacade testtermFacade;
	@ManagedProperty(value = "#{classteacherFacade}")
	private ClassteacherFacade classteacherFacade;

	private Teacher teacher = new Teacher();
	private Map<String, List<Classes>> classesMap = new LinkedHashMap<String, List<Classes>>();// 教师负责的班级
	private Classes filterClasses = new Classes();
	private Integer teacherID;// 操作的老师ID
	private byte submitSuccess = 0;// 修改是否成功
	private Integer[] classIDs;// 教师管理的班级ID

	/**
	 * 更新教师信息
	 * 
	 * @throws Exception
	 */
	public void updateTeacher(RowEditEvent event) {
		DataTable d = (DataTable) event.getSource();
		teacher = (Teacher) d.getRowData();
		if (teacherFacade.checkTeacher(teacher)) {
			try {
				teacherFacade.update(teacher);
				this.addInfoMessage("info", SUCESSINFO);
			} catch (Exception e) {
				e.printStackTrace();
				this.addErrorMessage("info", FAILINFO);
			}
		}
	}

	/**
	 * 查看老师负责的班级
	 * 
	 * @param teacherID
	 */
	public void checkAllClass(Integer teacherID) {
		List<Classes> classesList = classesFacade.getTeacherClasses(teacherID);
		int befMajorID = -1;
		String befMajorName = "";
		List<Classes> clist = new ArrayList<Classes>();
		if (classesList != null && classesList.size() > 0) {
			for (int i = 0; i < classesList.size(); i++) {
				Classes cl = classesList.get(i);
				if (befMajorID != -1
						&& cl.getMajorID().intValue() != befMajorID) {
					classesMap.put(befMajorName, clist);
					clist = new ArrayList<Classes>();
				}
				clist.add(cl);
				befMajorName = cl.getMajorName();
				befMajorID = cl.getMajorID();
			}
			classesMap.put(befMajorName, clist);
		}
	}

	/**
	 * 管理老师负责的班级
	 * 
	 * @param teacherID
	 */
	public void showTeachClass(Integer teacherID) {
		TeacherAction teacherAction = (TeacherAction) this
				.getViewAction("teacherAction");
		teacherAction.setClassesList(null);
		this.teacherID = teacherID;
		MajorOther mo = (MajorOther) this.getViewAction("majorOther");
		TeacherAction ta = (TeacherAction) this.getViewAction("teacherAction");
		if (mo.getMajorList() == null) {
			mo.setMajorList(ta.getSiteID());
		}
	}

	/**
	 * 根据辅导获取考期信息
	 * 
	 * @param e
	 */
	public void changeMajorID(AjaxBehaviorEvent e) {
		Integer majorID = (Integer) ((UISelectOne) e.getSource()).getValue();
		TeacherAction teacherAction = (TeacherAction) this
				.getViewAction("teacherAction");
		teacherAction.setTermList(testtermFacade.getTermListByMajorID(majorID));
	}

	/**
	 * 获取班级信息和教师管理的班级
	 */
	public void search() {
		if (classesFacade.checkTeaClass(filterClasses)) {
			TeacherAction teacherAction = (TeacherAction) this
					.getViewAction("teacherAction");
			filterClasses.setTeacherID(teacherID);
			List<Classes> classesList = classesFacade
					.getClassesByTerm(filterClasses);
			teacherAction.setClassesList(classesList);
			this.updateComponent("teachClassForm:classList");
			if (classesList != null && classesList.size() > 0) {
				classIDs = new Integer[classesList.size()];
				for (int i = 0; i < classesList.size(); i++) {
					if (classesList.get(i).getTeacherID() != null) {
						classIDs[i] = classesList.get(i).getClassID();
					}
				}
			}
		}
	}

	/**
	 * 提交教师管理的班级
	 */
	public void submitTeachClass() {
		TeacherAction teacherAction = (TeacherAction) this
				.getViewAction("teacherAction");
		if (teacherAction.getClassesList() == null) {
			this.addWarnMessage("info", "对不起，班级列表没有数据，请先进行查询！");
			return;
		}
		if (classIDs != null && classIDs.length > 0) {
			try {
				classteacherFacade.addClassTeachers(
						teacherAction.getClassesList(), teacherID, classIDs);
				submitSuccess = 1;
			} catch (Exception e) {
				e.printStackTrace();
				submitSuccess = -1;
			}
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public Map<Short, String> getIsHeaders() {
		return Contants.teacherHeader;
	}

	public TeacherFacade getTeacherFacade() {
		return teacherFacade;
	}

	public void setTeacherFacade(TeacherFacade teacherFacade) {
		this.teacherFacade = teacherFacade;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Map<String, List<Classes>> getClassesMap() {
		return classesMap;
	}

	public void setClassesMap(Map<String, List<Classes>> classesMap) {
		this.classesMap = classesMap;
	}

	public void setClassesFacade(ClassesFacade classesFacade) {
		this.classesFacade = classesFacade;
	}

	public void setTesttermFacade(TesttermFacade testtermFacade) {
		this.testtermFacade = testtermFacade;
	}

	public Classes getFilterClasses() {
		return filterClasses;
	}

	public void setFilterClasses(Classes filterClasses) {
		this.filterClasses = filterClasses;
	}

	public Integer[] getClassIDs() {
		return classIDs;
	}

	public void setClassIDs(Integer[] classIDs) {
		this.classIDs = classIDs;
	}

	public void setClassteacherFacade(ClassteacherFacade classteacherFacade) {
		this.classteacherFacade = classteacherFacade;
	}

	public Integer getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(Integer teacherID) {
		this.teacherID = teacherID;
	}

}
