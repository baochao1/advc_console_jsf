package com.cdel.advc.adminTeacher.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.cdel.advc.adminTeacher.domain.AdminStatis;
import com.cdel.advc.adminTeacher.faceade.AdminStatisFacade;
import com.cdel.util.BaseAction;
import com.chnedu.plat.rad.domain.BaseDomain;

/**
 * <p>
 * 班主任 和 管理员信息统计 
 * </p>
 * 
 * @author xuxiaoguang
 */
@SuppressWarnings("serial")
@ManagedBean
public class AdminStatisReqAction extends BaseAction<BaseDomain> implements
		Serializable {
	
	@ManagedProperty("#{adminStatisFacade}")
	private AdminStatisFacade adminStatisFacade;
	private List<AdminStatis> adminStatisList;// 知识点记录
	private AdminStatis adminStatis = new AdminStatis();
	private String orgName;
	
	/**
	 * 条件查询，强制设置 分页 从索引0位置 开始查询
	 */
	public void getList() {
		if(adminStatis.getOrgID() == null ){
			this.addMessage("info", "请选择部门！");
			adminStatisList = new ArrayList<AdminStatis>();
			return;
		}else if(adminStatis.getGroupID()==null){
			this.addMessage("info", "请选择小组！");
			adminStatisList = new ArrayList<AdminStatis>();
			return;
		}else{
			AdminStatis admin = new AdminStatis();
			adminStatisList = this.adminStatisFacade.showList(adminStatis);
			if(adminStatisList.size()>0){
				orgName = adminStatisList.get(0).getOrgName();
				admin.setOrgName(orgName);
			}
			int sum1 = 0,sum2 = 0,sum3 = 0,sum4 = 0;
			for(AdminStatis tmp:adminStatisList){
				sum1+=tmp.getAdminClassNum();
				sum2+=tmp.getAdminMemberTotal();
				sum3+=tmp.getAdviserClassNum();
				sum4+=tmp.getAdviserMemberTotal();
				admin.setAdminClassNum(sum1);
				admin.setAdminMemberTotal(sum2);
				admin.setAdviserClassNum(sum3);
				admin.setAdviserMemberTotal(sum4);
			}
			adminStatisList.add(admin);
		}
		
		this.updateComponent("teachMemberStatisForm:adminStatisList");
	}

	public AdminStatisFacade getAdminStatisFacade() {
		return adminStatisFacade;
	}

	public void setAdminStatisFacade(AdminStatisFacade adminStatisFacade) {
		this.adminStatisFacade = adminStatisFacade;
	}

	public List<AdminStatis> getAdminStatisList() {
		return adminStatisList;
	}

	public void setAdminStatisList(List<AdminStatis> adminStatisList) {
		this.adminStatisList = adminStatisList;
	}

	public AdminStatis getAdminStatis() {
		return adminStatis;
	}

	public void setAdminStatis(AdminStatis adminStatis) {
		this.adminStatis = adminStatis;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
