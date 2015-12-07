package com.cdel.advc.memberclass.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.primefaces.event.CellEditEvent;

import com.cdel.advc.classes.domain.Classes;
import com.cdel.advc.member.domain.Member;
import com.cdel.advc.member.facade.MemberFacade;
import com.cdel.advc.memberclass.domain.MemberClass;
import com.cdel.advc.memberclass.domain.MemberProfile;
import com.cdel.advc.memberclass.domain.PhoneNumberLog;
import com.cdel.advc.memberclass.facade.MemberClassFacade;
import com.cdel.advc.memberclass.facade.MemberProfileFacade;
import com.cdel.advc.memberclass.facade.PhoneNumberLogFacade;
import com.cdel.advc.website.domain.Website;
import com.cdel.advc.website.facade.WebsiteFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.ExceptionUtil;
import com.cdel.util.JsonUtil;

@SuppressWarnings("serial")
@ManagedBean
public class MemberClassReqAction extends BaseAction<MemberClass> implements
		Serializable { 

	@ManagedProperty("#{memberClassFacade}")
	private MemberClassFacade memberClassFacade;
	@ManagedProperty("#{memberFacade}")
	private MemberFacade memberFacade;
	@ManagedProperty("#{memberProfileFacade}")
	private MemberProfileFacade memberProfileFacade;
	@ManagedProperty("#{websiteFacade}")
	private WebsiteFacade websiteFacade;
	
	@ManagedProperty("#{phoneNumberLogFacade}")
	private PhoneNumberLogFacade phoneNumberLogFacade;
 
	private MemberClass memberClass = new MemberClass();
	private Classes classesDetail = new Classes();
	private MemberProfile profile = new MemberProfile();// 个性化信息
	byte submitSuccess = 0;// 修改是否成功

	/**
	 * 更新入班时间
	 * 
	 * @param event
	 */
	public void changeEnterTime(CellEditEvent event) {
		MemberClassAction mca = (MemberClassAction) this.getViewAction("memberClassAction");
		String id = event.getColumn().getClientId();
		memberClass = (MemberClass) this.getEditRow(event);
		try {
			String remarks = memberClass.getRemarks();
			if(remarks !=null && !remarks.equals("")){
				int size = remarks.length();
				if(size>100){
					this.addMessage("info", "对不起,您输出的字数过多!!!");
					return;
				}
			}
			
			memberClassFacade.update(memberClass);
			// 更新手机号
			if(id.indexOf("phone")>-1){
				updateCellPhoneNumber(mca.getSiteID(),memberClass.getClassID(),
						 memberClass.getUserID(),memberClass.getNewphonenumber());
			 }
			this.addMessage("info", SUCESSINFO);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtil.getEMsg(e));
			this.addErrorMessage("info", FAILINFO);
		}
	}

	/**
	 * 教务老师更新手机号
	 */
	public void updateCellPhoneNumber(Integer siteID,Integer classID,Integer userID,String newphonenumber){
		try { 
			PhoneNumberLog vo = new PhoneNumberLog();
			vo.setClassID(classID);
			vo.setMemberID(userID);
			vo.setNewphonenumber(newphonenumber);
			Integer teacherID = this.getCurrentUserID();
			vo.setUpdaterID(teacherID);
			vo.setSiteID(siteID);
			if(vo.getNewphonenumber() != null && !vo.getNewphonenumber().equals("") ){
				phoneNumberLogFacade.updateCellPhoneNumber(vo);
			}
			
//			this.addMessage("info", SUCESSINFO);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtil.getEMsg(e));
			this.addErrorMessage("info", FAILINFO);
		}
	}
	/**
	 * 查看个性化
	 * 
	 * @param classID
	 */
	public void showMemberProfile(Integer userID) {
		profile = memberProfileFacade.findByID(userID);
		if (profile != null) {
			submitSuccess = 1;
			this.updateComponent("proForm:proDialog");
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 更新个性化
	 */
	public void updateMemberProfile() {
		try {
			memberProfileFacade.update(profile);
			submitSuccess = 1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtil.getEMsg(e));
			submitSuccess = -1;
		}
		this.addCallbackParam("result", submitSuccess);
	}

	/**
	 * 更新本班学员手机号
	 * @param classID
	 * @param siteID
	 */
	public void updateMemberInClassPhone(Integer classID, Integer siteID) {
		try {
			Website website = websiteFacade.findByID(siteID);
			if (website == null) {
				this.addWarnMessage("info", "当前网站不能为空！");
				return;
			}
			List<Member> memberList = memberFacade.findList(classID);
			List<MemberClass> mcList = new ArrayList<MemberClass>();
			for (Member m : memberList) {
				Member m2 = JsonUtil.parseMember(memberFacade
						.getMemberFromRemote(m.getUserName().trim(), website,
								m.getUserID()));
				if (m2 != null) {
					m.setTelPhone(m2.getTelPhone());
					MemberClass mc = new MemberClass();
					mc.setClassID(classID);
					mc.setUserID(m.getUserID());
					mc.setNewClassID(classID);
					mcList.add(mc);
				}
			}
			memberFacade.update(memberList);
			
			// 更新学员班级表的时间，用于高忠慧接口
			memberClassFacade.update(mcList);
			
			MemberClassAction mca = (MemberClassAction) this.getViewAction("memberClassAction");
			mca.search4U();
			submitSuccess = 1;
		} catch (Exception e) {
			submitSuccess = -1;
			e.printStackTrace();
		}
		this.addCallbackParam("result", submitSuccess);
	}

	public Classes getClassesDetail() {
		return classesDetail;
	}

	public void setClassesDetail(Classes classesDetail) {
		this.classesDetail = classesDetail;
	}

	public MemberClass getMemberClass() {
		return memberClass;
	}

	public void setMemberClass(MemberClass memberClass) {
		this.memberClass = memberClass;
	}

	public void setMemberClassFacade(MemberClassFacade memberClassFacade) {
		this.memberClassFacade = memberClassFacade;
	}

	public void setMemberProfileFacade(MemberProfileFacade memberProfileFacade) {
		this.memberProfileFacade = memberProfileFacade;
	}

	public MemberProfile getProfile() {
		return profile;
	}

	public void setProfile(MemberProfile profile) {
		this.profile = profile;
	}

	public void setWebsiteFacade(WebsiteFacade websiteFacade) {
		this.websiteFacade = websiteFacade;
	}

	public void setMemberFacade(MemberFacade memberFacade) {
		this.memberFacade = memberFacade;
	}

	public void setPhoneNumberLogFacade(PhoneNumberLogFacade phoneNumberLogFacade) {
		this.phoneNumberLogFacade = phoneNumberLogFacade;
	}

}
