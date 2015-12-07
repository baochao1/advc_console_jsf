package com.cdel.plat.grant.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.menu.MenuModel;
import org.primefaces.model.TreeNode;

import com.cdel.advc.website.action.WebsiteOther;
import com.cdel.plat.grant.domain.Privilege;
import com.cdel.plat.grant.facade.PrivilegeFacade;
import com.cdel.util.BaseAction;
import com.cdel.util.Contants;
import com.cdel.util.CookieUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class LeftTreeAction extends BaseAction<Object> implements Serializable {

	// private TreeNode leftTree;
	private MenuModel model;
	@ManagedProperty(value = "#{privilegeFacade}")
	private PrivilegeFacade privilegeFacade;
	// 当前节点类型
	private String currentNodeType;
	// 当前节点名称
	private String currentNodeName;
	// 当前节点url
	private String currentNodeUrl;
	// 是否到达最大节点数
	private boolean tabMax;

	/**
	 * 生成左侧菜单树
	 */
	@PostConstruct
	@SuppressWarnings(value = { "rawtypes", "unchecked" })
	public void initLeftTreeAction() {
		HttpSession session = this.getSession();
		Integer type = Contants.defSystemType;
		Map con = new HashMap();
		con.put("adminID", session.getAttribute(Contants.ADMIN_USER_ID));
		con.put("systemType", type);
		List<Privilege> userPriList = privilegeFacade.getPriByUser(con);
		// 增加去各个网站的链接
		Privilege ptmp = new Privilege();
		ptmp.setParentID(-1);
		ptmp.setPrivilegeName("链接");
		ptmp.setPrivilegeID(-10);
		ptmp.setTreeType(1);
		userPriList.add(ptmp);
		ptmp = new Privilege();
		ptmp.setParentID(-10);
		ptmp.setPrivilegeName("去会计题库");
		ptmp.setPrivilegeID(-11);
		ptmp.setTreeType(2);
		userPriList.add(ptmp);
		ptmp = new Privilege();
		ptmp.setParentID(-10);
		ptmp.setPrivilegeName("去会计答疑板");
		ptmp.setPrivilegeID(-12);
		ptmp.setTreeType(2);
		userPriList.add(ptmp);
		ptmp = new Privilege();
		ptmp.setParentID(-10);
		ptmp.setPrivilegeName("去综合题库");
		ptmp.setPrivilegeID(-13);
		ptmp.setTreeType(2);
		userPriList.add(ptmp);
		ptmp = new Privilege();
		ptmp.setParentID(-10);
		ptmp.setPrivilegeName("去综合答疑板");
		ptmp.setPrivilegeID(-14);
		ptmp.setTreeType(2);
		userPriList.add(ptmp);
		ptmp = new Privilege();
		ptmp.setParentID(-10);
		ptmp.setPrivilegeName("去系统管理");
		ptmp.setPrivilegeID(-15);
		ptmp.setTreeType(2);
		userPriList.add(ptmp);
		// 找出所有的叶子节点
		Map<Integer, List<Privilege>> childMap = new HashMap<Integer, List<Privilege>>();
		for (int i = 0; i < userPriList.size(); i++) {
			Privilege p = userPriList.get(i);
			Integer parentID = p.getParentID();
			if (p.getTreeType() == 2) {
				if (childMap.get(parentID) == null) {
					List<Privilege> l = new ArrayList<Privilege>();
					childMap.put(parentID, l);
				}
				childMap.get(parentID).add(p);
			}
		}
		Map<Integer, List<Privilege>> parentMap = getMapParent(userPriList,
				childMap);
		// 重新排序
		Set<Map.Entry<Integer, List<Privilege>>> key = parentMap.entrySet();
		for (Iterator<Map.Entry<Integer, List<Privilege>>> it = key.iterator(); it
				.hasNext();) {
			Map.Entry<Integer, List<Privilege>> entry = (Map.Entry<Integer, List<Privilege>>) it
					.next();
			List<Privilege> pl = entry.getValue();
			Collections.sort(pl);
		}
		model = new DefaultMenuModel();
		getMenu(parentMap, -1, model);
		// leftTree = new DefaultTreeNode("Root", null);
		// getTree(parentMap, -1, leftTree);
	}

	public void getMenu(Map<Integer, List<Privilege>> parentMap,
			Integer parentID, MenuModel parentNode) {
		HttpSession session = this.getSession();
		List<Privilege> userPriList = parentMap.get(parentID);
		try {
			if (userPriList != null) {
				for (int i = 0; i < userPriList.size(); i++) {
					DefaultSubMenu submenu = new DefaultSubMenu();
					Privilege p = userPriList.get(i);
					Integer privilegeID = p.getPrivilegeID();
					if (parentMap.get(privilegeID) != null) {
						submenu.setLabel(p.getPrivilegeName());
						getMenu(parentMap, privilegeID, submenu);
					} else {
						DefaultMenuItem item = new DefaultMenuItem();
						submenu.setLabel(p.getPrivilegeName());
						item.setValue(p.getPrivilegeName());
						item.setCommand("#{leftTreeAction.selected('leaf','"
								+ p.getActionURL() + "','"
								+ p.getPrivilegeName() + "')}");
						if (p.getPrivilegeID() < 0) {
							item.setTarget("_blank");
							String name = session
									.getAttribute(Contants.ADMIN_USER_NAME)
									+ "";
							String secret = session
									.getAttribute(Contants.ADMIN_PASSWORD) + "";
							gotoTree(p, item, name, secret);
						}
						submenu.addElement(item);
					}
					parentNode.addElement(submenu);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getMenu(Map<Integer, List<Privilege>> parentMap,
			Integer parentID, DefaultSubMenu submenu) {
		HttpSession session = this.getSession();
		List<Privilege> userPriList = parentMap.get(parentID);
		try {
			if (userPriList != null) {
				for (int i = 0; i < userPriList.size(); i++) {
					DefaultSubMenu submenuinner = new DefaultSubMenu();
					Privilege p = userPriList.get(i);
					Integer privilegeID = p.getPrivilegeID();
					if (parentMap.get(privilegeID) != null) {
						submenuinner.setLabel(p.getPrivilegeName());
						getMenu(parentMap, privilegeID, submenuinner);
						submenu.addElement(submenuinner);
					} else {
						DefaultMenuItem item = new DefaultMenuItem();
						item.setValue(p.getPrivilegeName());
						item.setCommand("#{leftTreeAction.selected('leaf','"
								+ p.getActionURL() + "','"
								+ p.getPrivilegeName() + "')}");
						if (p.getPrivilegeID() < 0) {
							item.setTarget("_blank");
							String name = session
									.getAttribute(Contants.ADMIN_USER_NAME)
									+ "";
							String secret = session
									.getAttribute(Contants.ADMIN_PASSWORD) + "";
							gotoTree(p, item, name, secret);
						}
						submenu.addElement(item);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 递归调用，生成tree的字符串(暂时不用)
	 * 
	 * @param strTree
	 * @param parentMap
	 * @param parentID
	 */
	public void getTree(Map<Integer, List<Privilege>> parentMap,
			Integer parentID, TreeNode parentNode) {
		List<Privilege> userPriList = parentMap.get(parentID);
		try {
			if (userPriList != null) {
				for (int i = 0; i < userPriList.size(); i++) {
					Privilege p = userPriList.get(i);
					Integer privilegeID = p.getPrivilegeID();
					TreeNode node = null;
					if (parentMap.get(privilegeID) != null) {
						node = new DefaultTreeNode(p, parentNode);
						getTree(parentMap, privilegeID, node);
					} else {
						node = new DefaultTreeNode("leaf", p, parentNode);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 把权限列表转换为按parentID为key的map，用于根据叶子反查非叶子节点
	 * 
	 * @param userPriList
	 * @return
	 */
	public Map<Integer, List<Privilege>> getMapParent(
			List<Privilege> userPriList, Map<Integer, List<Privilege>> childMap) {
		for (int i = 0; i < userPriList.size(); i++) {
			Privilege p = userPriList.get(i);
			Integer privilegeID = p.getPrivilegeID();
			Integer parentID = p.getParentID();
			if (childMap.get(privilegeID) != null) {
				if (childMap.get(parentID) == null) {
					List<Privilege> l = new ArrayList<Privilege>();
					childMap.put(parentID, l);
				}
				childMap.get(parentID).add(p);
			}
		}
		return childMap;
	}

	/**
	 * 单击树节点的响应函数
	 * 
	 * @param e
	 */
	public void selected(String type, String actionURL, String privilegeName) {
		// 生成页签
		if (type != null && type.equals("leaf")) {
			String[] arr = StringUtils.split(actionURL, "/");
			currentNodeUrl = arr[0];
		} else {
			currentNodeUrl = "";
		}
		currentNodeType = type;
		currentNodeName = privilegeName;
		this.executeScript("loadTabPage2('" + currentNodeUrl + "','"
				+ currentNodeName + "')");

	}

	/**
	 * 打开个人中心等指定链接
	 */
	public void openPage(String name, String displayName) {
		currentNodeUrl = name;
		currentNodeType = "leaf";
		currentNodeName = displayName;
	}

	public void gotoTree(Privilege p, DefaultMenuItem item, String name,
			String secret) {
		if (p.getPrivilegeID() == -11) {
			item.setHref(Contants.qzConsoleUrl
					+ "/login/login.action?userName=" + name + "&secret="
					+ secret);
		}
		if (p.getPrivilegeID() == -12) {
			item.setHref(Contants.faqConsoleUrl
					+ "/login/view.do?op=login&userName=" + name + "&secret="
					+ secret);
		}
		if (p.getPrivilegeID() == -13) {
			item.setHref(Contants.zhqzConsoleUrl
					+ "/login/view.do?op=login&userName=" + name + "&secret="
					+ secret);
		}
		if (p.getPrivilegeID() == -14) {
			item.setHref(Contants.zhfaqConsoleUrl
					+ "/login/view.do?op=login&userName=" + name + "&secret="
					+ secret);
		}
		if (p.getPrivilegeID() == -15) {
			item.setHref(Contants.authConsoleUrl + "?userName=" + name
					+ "&secret=" + secret);
		}
	}

	/**
	 * 打开修改网站页面
	 */
	public void changeSite() {
		WebsiteOther wo = (WebsiteOther) this.getViewAction("websiteOther");
		wo.setSelectSiteID(CookieUtil.getCookieInt(this.getRequest(),
				Contants.COOKIENAME_WEBSITE));
	}

	/**
	 * 提交修改网站页面
	 */
	public void changeSiteSubmit() {
		WebsiteOther wo = (WebsiteOther) this.getViewAction("websiteOther");
		if (wo.getSelectSiteID() == null) {
			this.addErrorMessage("info", "请选择网站！");
			return;
		}
		CookieUtil.addCookie(this.getResponse(), Contants.COOKIENAME_WEBSITE,
				wo.getSelectSiteID().toString());
		BaseDomain.baseSiteID = wo.getSelectSiteID();
	}

	public MenuModel getModel() {
		return model;
	}

	public void setPrivilegeFacade(PrivilegeFacade privilegeFacade) {
		this.privilegeFacade = privilegeFacade;
	}

	public String getCurrentNodeType() {
		return currentNodeType;
	}

	public void setCurrentNodeType(String currentNodeType) {
		this.currentNodeType = currentNodeType;
	}

	public String getCurrentNodeName() {
		return currentNodeName;
	}

	public void setCurrentNodeName(String currentNodeName) {
		this.currentNodeName = currentNodeName;
	}

	public String getCurrentNodeUrl() {
		return currentNodeUrl;
	}

	public void setCurrentNodeUrl(String currentNodeUrl) {
		this.currentNodeUrl = currentNodeUrl;
	}

	public boolean isTabMax() {
		return tabMax;
	}

	public void setTabMax(boolean tabMax) {
		this.tabMax = tabMax;
	}

}
