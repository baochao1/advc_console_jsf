package com.cdel.api.callCenter.member;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdel.advc.website.domain.Website;
import com.cdel.advc.website.facade.WebsiteFacade;
import com.cdel.api.callCenter.Constant;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.JsonUtil;

/**
 * 
 * <p>
 * 为呼叫中心提供的特定接口 学员facade层
 * </p>
 * 
 * @author Du Haiying Create at:2013-10-14 下午2:11:59
 */
@SuppressWarnings("serial")
@Service("callCenterMemberFacade")
public class MemberFacade extends
		BaseFacadeImpl<com.cdel.advc.member.domain.Member, Integer> {

	@Autowired
	private com.cdel.advc.member.facade.MemberFacade memberFacade;
	@Autowired
	private WebsiteFacade websiteFacade;

	/**
	 * 查找班级下的学员
	 * 
	 * @param classID
	 * 
	 * @return 返回学员代码，手机，电话号码，班级名称列表，注：如果学员的电话号码与手机号码都为空的，此学员信息不用返回。
	 */
	public List<Member> findMembersByClassID(Integer classID) {

		if (classID == null) {
			throw new IllegalArgumentException("非法参数:classID不能为null!");
		}

		Website website = websiteFacade.getWebsiteByClassID(classID);
		List<Member> memlist = this.baseDao.findSomeList(classID,
				Constant.FINDMEMBERSBYCLASSID);
		try {
			for (Member m : memlist) {
				com.cdel.advc.member.domain.Member m2 = JsonUtil
						.parseMember(memberFacade.getMemberFromRemote(m
								.getUserName().trim(), website, Integer
								.parseInt(m.getUserID())));
				if (m2 != null && StringUtils.isNotBlank(m2.getTelPhone())) {
					m.setTelPhone(m2.getTelPhone());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memlist;
	}

	/**
	 * 查找 学员的基本信息
	 * 
	 * @param classID
	 * 
	 * @return 返回学员代码，手机，电话号码，班级名称列表，注：如果学员的电话号码与手机号码都为空的，此学员信息不用返回。
	 */
	public List<Member> findMembersByUpdateTime(Member member) {
		if (member == null) {
			throw new IllegalArgumentException("非法参数:参数不能为null!");
		}
		if (StringUtils.isBlank(member.getUpdateTime())) {
			throw new IllegalArgumentException("非法参数:班级 最后一次更新时间不能为null!");
		}
		if (null == member.getCount()) {
			throw new IllegalArgumentException("非法参数:查询的记录数不能为null!");
		}
		if (null == member.getSiteID()) {
			throw new IllegalArgumentException("非法参数:查询的记录数不能为null!");
		}

		List<Member> memlist = this.baseDao.findSomeList(member,
				Constant.FINDMEMBERSBYUPDATETIME);
		Website website = websiteFacade.findByID(member.getSiteID());
		try {
			for (Member m : memlist) {
				com.cdel.advc.member.domain.Member m2 = JsonUtil
						.parseMember(memberFacade.getMemberFromRemote(m
								.getUserName().trim(), website, Integer
								.parseInt(m.getUserID())));
				if (m2 != null && StringUtils.isNotBlank(m2.getTelPhone())) {
					m.setTelPhone(m2.getTelPhone());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memlist;
	}
}
