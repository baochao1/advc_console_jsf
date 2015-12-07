/*
 * @Title: MemberFacade.java
 * @Package com.cdel.advc.member.facade
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-7-18 上午8:33:47
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-7-18                          
 */
package com.cdel.advc.member.facade;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.cdel.advc.member.domain.Member;
import com.cdel.advc.website.domain.Website;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.cdel.util.JsonUtil;
import com.cdel.util.MD5;
import com.cdel.util.RemoteUtil;

/**
 * <p>
 * 学员 facade层
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-18 上午8:33:47
 */
@SuppressWarnings("serial")
@Service
public class MemberFacade extends BaseFacadeImpl<Member, Integer> {

	/**
	 * 一般化的查询 学员列表（用于群发短信时查询班级或课程相关的学员）
	 */
	public List<Member> getMemberList(Member member) {
		return this.baseDao.findList(member, "getMemberList");
	}

	/**
	 * 通过学员名称找到学员
	 * 
	 * @param userName
	 * @return
	 */
	public Member getMemberByName(String userName) {
		if (StringUtils.isBlank(userName)) {
			return null;
		}
		return this.baseDao.get(userName.trim(), "getMemberByName");
	}

	/**
	 * 通过学员名称找到学员
	 * 
	 * @param userName
	 * @return
	 */
	public Member getMemberByID(Integer userID) {
		if (userID == null) {
			return null;
		}
		return this.findByID(userID);
	}

	/**
	 * 通过学员名称找到学员
	 * 
	 * @param userName
	 * @return
	 */
	public List<Member> getMemberByName(Integer userID, String userName,
			Integer siteID) {
		List<Member>  memberList = new ArrayList<Member>();
		// 根据userID从教务库中查询学员对象
		Member member = this.getMemberByID(userID);
		if (member != null) {
			memberList.add(member);
			return memberList;
		}
		// 根据userName从教务库查询学员对象
		memberList = this.baseDao.findList(userName, "getMemberByName");
		if(memberList.size() == 0){ // 说明教务库中不存在															 
			// 如果教务库不存在，从主库main库中查询
			if (member == null) {
				String domain = Contants.siteIDs.get(siteID);
				if (domain == null) {
					domain = "";
				}
				member = this.baseDao.get(userName + domain, "getMemberByName");
				if (member == null) {
					member = this.getMember(userName);// 从主库main库中查询
					if (member != null) {
						member.setGener(true);
						member.setSiteID(siteID);
						member.setUserName(userName);
						member.setCreateTime(new Date());
						member.setStatus((short) 1);
						member.setSmsStatus((short) 1);
						member.setMemberSatuts("1111100000000000");
						// 将Member同步到教务库
						this.add(member);
						memberList.add(member);
					}
				}else{ 
					memberList.add(member);
				}
			}
		}
		return memberList;
	}

	/**
	 * 从main库中查询 Member 学员 对象
	 * 
	 * @param userName
	 * @return
	 */
	private Member getMember(String userName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(Contants.DATA_KEY, Contants.ACC_MAIN);
		params.put("userName", userName);
		Member member = this.baseDao.get(params, "getMemberByNameFromMain");// 从主库main库中查询
		return member;
	}

	/**
	 * 通过学员名称找到学员
	 * 
	 * @param userName
	 * @return
	 */
	public Member getMemberByName(Integer userID, String userName,
			Website website) {
		Member member = this.getMemberByID(userID);
		if (member != null) {
			return member;
		}
		// 先从教务库查询
		member = this.baseDao.get(userName, "getMemberByName"); // 从
																// 教务库查询
		// 如果教务库不存在，从获得远程学员信息
		if (member == null) {
			String domain = Contants.siteIDs.get(website.getSiteID());
			if (domain == null) {
				domain = "";
			}
			member = this.baseDao.get(userName + domain, "getMemberByName");
			if (member == null) {
				try {
					String resultData = this.getMemberFromRemote(userName,
							website, userID);
					member = this.getRemoteMember(website.getSiteID(),
							userName, resultData);
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return member;
	}

	/**
	 * 从接口获取学员报课信息
	 * 
	 * @param userName
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String getMemberFromRemote(String userName, Website website,
			Integer userID) throws Exception {
		String keyTime = DateUtil.getNowToString("yyyy-MM-dd HH:mm:ss");
		String aspServer = website.getPhoneUrl();

		String sendData = null;
		if (userID != null && userID > 0) {
			String key = MD5.Md5(userID + keyTime + website.getSiteAttr(), 16);
			sendData = "?userID=" + userID + "&keyTime="
					+ URLEncoder.encode(keyTime, Contants.code) + "&key=" + key;
		} else {
			String key = MD5
					.Md5(userName + keyTime + website.getSiteAttr(), 16);
			sendData = "?userName=" + userName + "&keyTime="
					+ URLEncoder.encode(keyTime, Contants.code) + "&key=" + key;
		}

		return RemoteUtil.getRemoteString(aspServer, sendData, Contants.code)
				.replaceAll("&nbsp;", "");
	}

	// 获得远程学员信息
	public Member getRemoteMember(Integer siteID, String userName,
			String resultData) throws Exception {
		Member m = JsonUtil.parseMember(resultData);
		m.setSiteID(siteID);
		m.setUserName(userName);
		m.setStatus((short) 1);
		m.setCreateTime(new Date());
		this.add(m);
		return m;
	}
	
	/**
	 * 通过学员名称找到学员
	 * @param userName
	 * @return
	 */
	public List<Member> getMemberListByName(String userName) {
		List<Member>  memberList = this.baseDao.findList(userName, "getMemberByName"); // 从教务库查询
		return memberList;
	}

}
