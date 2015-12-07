package com.cdel.advc.coursetimeLong.action;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.cdel.advc.coursetimeLong.domain.CoursetimeLong;
import com.cdel.advc.coursetimeLong.facade.CoursetimeLongFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.DateUtil;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CoursetimeLongAction extends BaseAction<CoursetimeLong> implements
		Serializable {
	@ManagedProperty(value = "#{coursetimeLongFacade}")
	private CoursetimeLongFacade coursetimeLongFacade;

	private LazyDataModel<CoursetimeLong> coursetimeLongPage;
	private CoursetimeLong searchCoursetimeLong = new CoursetimeLong();

	@PostConstruct
	public void initCoursetimeLong() {
		coursetimeLongPage = coursetimeLongFacade
				.findPage(searchCoursetimeLong);
	}

	/**
	 * 查询
	 */
	public void search() {
		pageTable.setFirst(0);
		search4U();
	}

	public void search4U() {
		searchCoursetimeLong.setCountDate(DateUtil.getNowDateString(
				searchCoursetimeLong.getdCountDate(), "yyyy-MM-dd"));
		coursetimeLongPage = coursetimeLongFacade
				.findPage(searchCoursetimeLong);
		this.updateComponent("searchForm:coursetimeLongList");
	}

	public LazyDataModel<CoursetimeLong> getCoursetimeLongPage() {
		return coursetimeLongPage;
	}

	public void setCoursetimeLongPage(
			LazyDataModel<CoursetimeLong> coursetimeLongPage) {
		this.coursetimeLongPage = coursetimeLongPage;
	}

	public CoursetimeLong getSearchCoursetimeLong() {
		return searchCoursetimeLong;
	}

	public void setSearchCoursetimeLong(CoursetimeLong searchCoursetimeLong) {
		this.searchCoursetimeLong = searchCoursetimeLong;
	}

	public void setCoursetimeLongFacade(
			CoursetimeLongFacade coursetimeLongFacade) {
		this.coursetimeLongFacade = coursetimeLongFacade;
	}

}
