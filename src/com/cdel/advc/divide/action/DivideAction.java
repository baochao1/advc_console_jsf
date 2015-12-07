package com.cdel.advc.divide.action;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.cdel.advc.classes.domain.Classes;
import com.cdel.advc.combo.domain.Combo;
import com.cdel.advc.course.domain.Course;
import com.cdel.advc.divide.domain.Divide;
import com.cdel.advc.divide.facade.DivideFacade;
import com.cdel.advc.member.domain.Member;
import com.cdel.advc.member.facade.MemberFacade;
import com.cdel.advc.memberclass.domain.MemberClass;
import com.cdel.advc.memberclass.facade.MemberClassFacade;
import com.cdel.advc.studentCourse.domain.BuyCourse;
import com.cdel.advc.studentCourse.facade.StudentCourseFacade;
import com.cdel.advc.website.domain.Website;
import com.cdel.advc.website.facade.WebsiteFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.ContantsUrl;
import com.cdel.util.ExceptionUtil;
import com.cdel.util.JsonUtil;

/**
 * 学员分班
 * 
 * @author zhangsulei
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class DivideAction extends BaseAction<Divide> implements Serializable {
	@ManagedProperty(value = "#{websiteFacade}")
	private WebsiteFacade websiteFacade;
	@ManagedProperty(value = "#{memberClassFacade}")
	private MemberClassFacade memberClassFacade;
	@ManagedProperty(value = "#{memberFacade}")
	private MemberFacade memberFacade;
	@ManagedProperty(value = "#{divideFacade}")
	private DivideFacade divideFacade;
	@ManagedProperty(value = "#{studentCourseFacade}")
	private StudentCourseFacade studentCourseFacade;

	private Divide searchDivide = new Divide();
	private Integer siteID;// 网站ID
	private List<MemberClass> mcList; // 学员所在班级
	private List<BuyCourse> bcList;// 学员所报课程
	private List<Divide> autoList;// 符合的策略
	private boolean show = false;// 控制是否显示查询结果
	private boolean showCl = false;// 控制是否显示策略查询结果
	private String bcListMsg;// 学员是否报课提示信息
	private Integer userID;// 查询的学员ID
	private List<Classes> classList;// 班级列表(换班)
	protected Integer isInterface;// 是否接口，1：是；0：否
	private String infoAddUrl = ContantsUrl.infoAddUrl;// 信息添加地址

	@PostConstruct
	public void initCourseAction() {
		siteID = this.getCurrentSiteID();
		super.heighti2 = super.calHeight(15f / 20);
		isInterface = this.getIntegerParameter("isInterface");
		if (isInterface != null && isInterface == 1) {
			searchDivide.setUserName(this.getParameter("userName"));
			searchDivide.setUserID(this.getIntegerParameter("userID"));
			search();
		}
		HttpServletRequest request = this.getRequest();
		request.setAttribute("infoAddUrl", infoAddUrl);
	}

	/**
	 * 查询
	 */
	@SuppressWarnings("unchecked")
	public void search() {
		show = true;
		String userName = searchDivide.getUserName();
		if (StringUtils.isBlank(userName)) {
			this.addWarnMessage("info", "请输入学员代码！");
			return;
		}
		
		Website website = websiteFacade.findByID(siteID);
		if (StringUtils.isBlank(website.getSelCourseUrl())) {
			this.addWarnMessage("info", "网站对应的ASP选课数据服务器没有配置！");
			return;
		}
		Member member = null;
		List<Member>  memberList = memberFacade.getMemberByName(searchDivide.getUserID(),userName.trim(), siteID);
		if(memberList.size()==1){
			member = memberList.get(0);
		}else if(memberList.size() >1){// 说明至少2个学员
			for (int i = 0; i < memberList.size(); i++) {
				if (siteID == memberList.get(i).getSiteID()) {
					member = memberList.get(i);
				}
			}
//			this.updateComponent("memberForm:memberDialog");
//			this.executeScript("memberDialog.show();"); // 执行js方法
//			return;
		}
		
		if (member == null) {
			this.addWarnMessage("info", "学员不存在！");
			show = false;
			showCl = false;
			this.updateComponent("searchForm2:showTabView");
			return;
		}
		try {
			MemberClass memberClass = new MemberClass();
			DivideLogAction divideLogAction = (DivideLogAction) this
					.getViewAction("divideLogAction");
			userID = member.getUserID();
			logger.warn("NotAPI----"+"HandsOp----userID=" + userID);
			
			divideLogAction.getSearchDivideLog().setUserID(userID);
			memberClass.setUserID(userID);
			memberClass.setStatus((short) 1);
			// 获取所在班级
			mcList = memberClassFacade.getMemberClassesInfo(memberClass);
			String resultData = studentCourseFacade.getBuyCourseFromRemote(
					userName.trim(), website, userID);
			// 获取报课信息
			bcList = JsonUtil.parseBuyCourse(resultData);
			logger.warn("NotAPI----" + "HandsOp----BuyCourseList=" + bcList);
			
			if (bcList == null || bcList.size() == 0) {
				bcListMsg = "学员没有报课！";
			} else {
				List<Object> result = divideFacade.getSelCourseCodeList(siteID,
						bcList);
				bcList = (List<BuyCourse>) result.get(2);
				logger.warn("NotAPI----" + "HandsOp----BuyCourseList=" + bcList);
				
				if (bcList == null || bcList.size() == 0) {
					bcListMsg = "学员所报课程与本系统实验精品课程、套餐无一匹配！";
				} else {
					// 获取策略
					autoList = divideFacade.getAutoMap(
							(List<Course>) result.get(0),
							(List<Combo>) result.get(1), mcList, false);
					showCl = true;
				}
			}
			this.updateComponent("searchForm2:showTabView");
			searchDivide.setUserID(null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("userID:" + userID + "学员分班查询");
			logger.error(ExceptionUtil.getEMsg(e));
		}
	}

	public Divide getSearchDivide() {
		return searchDivide;
	}

	public void setSearchDivide(Divide searchDivide) {
		this.searchDivide = searchDivide;
	}

	public List<MemberClass> getMcList() {
		return mcList;
	}

	public void setMcList(List<MemberClass> mcList) {
		this.mcList = mcList;
	}

	public List<BuyCourse> getBcList() {
		return bcList;
	}

	public void setBcList(List<BuyCourse> bcList) {
		this.bcList = bcList;
	}

	public List<Divide> getAutoList() {
		return autoList;
	}

	public void setAutoList(List<Divide> autoList) {
		this.autoList = autoList;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public String getBcListMsg() {
		return bcListMsg;
	}

	public void setBcListMsg(String bcListMsg) {
		this.bcListMsg = bcListMsg;
	}

	public void setWebsiteFacade(WebsiteFacade websiteFacade) {
		this.websiteFacade = websiteFacade;
	}

	public void setMemberClassFacade(MemberClassFacade memberClassFacade) {
		this.memberClassFacade = memberClassFacade;
	}

	public void setMemberFacade(MemberFacade memberFacade) {
		this.memberFacade = memberFacade;
	}

	public void setDivideFacade(DivideFacade divideFacade) {
		this.divideFacade = divideFacade;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public boolean isShowCl() {
		return showCl;
	}

	public void setShowCl(boolean showCl) {
		this.showCl = showCl;
	}

	public List<Classes> getClassList() {
		return classList;
	}

	public void setClassList(List<Classes> classList) {
		this.classList = classList;
	}

	public Integer getIsInterface() {
		return isInterface;
	}

	public void setIsInterface(Integer isInterface) {
		this.isInterface = isInterface;
	}

	public void setStudentCourseFacade(StudentCourseFacade studentCourseFacade) {
		this.studentCourseFacade = studentCourseFacade;
	}

	public String getInfoAddUrl() {
		return infoAddUrl;
	}

}
