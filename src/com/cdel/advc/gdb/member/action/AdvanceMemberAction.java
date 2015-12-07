package com.cdel.advc.gdb.member.action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang.StringUtils;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.gdb.member.domain.AdvanceMember;
import com.cdel.advc.gdb.member.facade.AdvanceMemberFacade;
import com.cdel.advc.course.facade.CourseFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class AdvanceMemberAction extends BaseAction<AdvanceMember> implements
		Serializable {
	@ManagedProperty(value = "#{advanceMemberFacade}")
	private AdvanceMemberFacade advanceMemberFacade;
	@ManagedProperty(value = "#{courseFacade}")
	private CourseFacade courseFacade;

	private LazyDataModel<AdvanceMember> advanceMemberPage;
	private AdvanceMember searchAdvanceMember = new AdvanceMember();
	private String courseIDs;// 高端班课程
	private String courseIDs2;// 高端班课程

	@PostConstruct
	public void initAdvanceMemberAction() {
		courseIDs = advanceMemberFacade.advcAdvanceCourse(courseFacade
				.getAdvcAdvanceCourse());
		courseIDs2 = StringUtils.replace(courseIDs, "'", "''");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Contants.DATA_KEY, Contants.ACC_MAIN);
		map.put("courseIDs", courseIDs);
		map.put("courseIDs2", courseIDs2);
		this.advanceMemberPage = this.advanceMemberFacade.findPage(map);
		super.heighti2 = super.calHeight(11.5f / 20);
	}

	/**
	 * 查询
	 * 
	 * @throws Exception
	 */
	public void search() {
		pageTable.setFirst(0);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Contants.DATA_KEY, Contants.ACC_MAIN);
		map.put("courseIDs", courseIDs);
		map.put("courseIDs2", courseIDs2);
		if (!StringUtils.isBlank(searchAdvanceMember.getUserName())) {
			map.put("userName", searchAdvanceMember.getUserName());
		}
		this.advanceMemberPage = this.advanceMemberFacade.findPage(map);
		this.updateComponent("searchForm:advanceMemberList");
	}

	public LazyDataModel<AdvanceMember> getAdvanceMemberPage() {
		return advanceMemberPage;
	}

	public void setAdvanceMemberPage(
			LazyDataModel<AdvanceMember> advanceMemberPage) {
		this.advanceMemberPage = advanceMemberPage;
	}

	public AdvanceMember getSearchAdvanceMember() {
		return searchAdvanceMember;
	}

	public void setSearchAdvanceMember(AdvanceMember searchAdvanceMember) {
		this.searchAdvanceMember = searchAdvanceMember;
	}

	public void setAdvanceMemberFacade(AdvanceMemberFacade advanceMemberFacade) {
		this.advanceMemberFacade = advanceMemberFacade;
	}

	public void setCourseFacade(CourseFacade courseFacade) {
		this.courseFacade = courseFacade;
	}

}
