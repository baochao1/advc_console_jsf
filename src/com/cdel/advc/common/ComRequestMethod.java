package com.cdel.advc.common;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.cdel.advc.classes.facade.ClassesFacade;
import com.cdel.advc.combo.facade.ComboFacade;
import com.cdel.advc.course.domain.Course;
import com.cdel.advc.course.facade.CourseFacade;
import com.cdel.advc.major.facade.MajorFacade;
import com.cdel.advc.member.domain.Member;
import com.cdel.advc.member.facade.MemberFacade;
import com.cdel.advc.strategy.facade.StrategyFacade;
import com.cdel.advc.teacher.facade.TeacherFacade;
import com.cdel.advc.testterm.facade.TesttermFacade;

/**
 * 项目中的一些公用方法(request)
 * 
 * @author dell
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
public class ComRequestMethod implements Serializable {

	@ManagedProperty(value = "#{courseFacade}")
	private CourseFacade courseFacade;
	@ManagedProperty(value = "#{classesFacade}")
	private ClassesFacade classesFacade;
	@ManagedProperty(value = "#{majorFacade}")
	private MajorFacade majorFacade;
	@ManagedProperty(value = "#{teacherFacade}")
	private TeacherFacade teacherFacade;
	@ManagedProperty(value = "#{comboFacade}")
	private ComboFacade comboFacade;
	@ManagedProperty(value = "#{testtermFacade}")
	private TesttermFacade testtermFacade;
	@ManagedProperty(value = "#{strategyFacade}")
	private StrategyFacade strategyFacade;
	@ManagedProperty(value = "#{memberFacade}")
	private MemberFacade memberFacade;
	
	/**
	 * 模糊匹配courseName
	 * 
	 * @param query
	 * @return
	 */
	public List<Course> completeCourseName(String query) {
		query = query.replaceAll("\\[", "#\\[");
		query = query.replaceAll("\\]", "#\\]");
		return courseFacade.getCourseByCourseName(query);
	}

	/**
	 * 模糊匹配className
	 * 
	 * @param query
	 * @return
	 */
	public List<String> completeClassName(String query) {
		return classesFacade.findClassNames(query);
	}

	/**
	 * 模糊匹配majorName
	 * 
	 * @param query
	 * @return
	 */
	public List<String> completeMajorName(String query) {
		return majorFacade.getMajorNameByMajorName(query);
	}

	/**
	 * 模糊匹配teacherName(根据teacherName或teacherCode)
	 * 
	 * @param query
	 * @return
	 */
	public List<String> completeTeacherName(String query) {
		return teacherFacade.getTeacherByTeacherName(query, new Byte("0"));
	}

	/**
	 * 模糊匹配teacherName(根据teacherCode)
	 * 
	 * @param query
	 * @return
	 */
	public List<String> completeTeacherName2(String query) {
		return teacherFacade.getTeacherByTeacherName(query, new Byte("1"));
	}
	
	/**
	 * 模糊匹配teacherName(根据teacherName)
	 * 
	 * @param query
	 * @return
	 */
	public List<String> completeTeacherName3(String query) {
		return teacherFacade.getTeacherByTeacherName(query, new Byte("2"));
	}

	/**
	 * 模糊匹配套餐(根据comboName)
	 * 
	 * @param query
	 * @return
	 */
	public List<String> completeComboName(String query) {
		query = query.replaceAll("\\[", "#\\[");
		query = query.replaceAll("\\]", "#\\]");
		return comboFacade.getComboByComboName(query, new Byte("0"));
	}

	/**
	 * 模糊匹配套餐(根据comboCode)
	 * 
	 * @param query
	 * @return
	 */
	public List<String> completeComboCode(String query) {
		return comboFacade.getComboByComboName(query, new Byte("1"));
	}

	/**
	 * 模糊匹配考期
	 * 
	 * @param query
	 * @return
	 */
	public List<String> completeTermName(String query) {
		return testtermFacade.getTermByTermName(query);
	}

	/**
	 * 模糊匹配策略描述
	 * 
	 * @param query
	 * @return
	 */
	public List<String> completeStrategyDesc(String query) {
		query = query.replaceAll("\\[", "#\\[");
		query = query.replaceAll("\\]", "#\\]");
		return strategyFacade.getStrategyDesc(query);
	}

	/**
	 * 通过学员名称找到学员
	 * @param userName
	 * @return
	 */
	public List<Member> getMemberListByName(String query) {	
		List<Member>  memberList = null;
		if(null != query  && !query.trim().equals("")){
			memberList = memberFacade.getMemberListByName(query); // 从教务库查询
		}
		return memberList;
	}
	
	public void setCourseFacade(CourseFacade courseFacade) {
		this.courseFacade = courseFacade;
	}

	public void setClassesFacade(ClassesFacade classesFacade) {
		this.classesFacade = classesFacade;
	}

	public void setMajorFacade(MajorFacade majorFacade) {
		this.majorFacade = majorFacade;
	}

	public void setTeacherFacade(TeacherFacade teacherFacade) {
		this.teacherFacade = teacherFacade;
	}

	public void setComboFacade(ComboFacade comboFacade) {
		this.comboFacade = comboFacade;
	}

	public void setTesttermFacade(TesttermFacade testtermFacade) {
		this.testtermFacade = testtermFacade;
	}

	public void setStrategyFacade(StrategyFacade strategyFacade) {
		this.strategyFacade = strategyFacade;
	}

	public void setMemberFacade(MemberFacade memberFacade) {
		this.memberFacade = memberFacade;
	}

}
