/*
 * @Title: MemberSmsFacade.java
 * @Package com.cdel.advc.sms.facade
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-7-17 下午5:24:15
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-7-17                          
 */
package com.cdel.advc.memberSms.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdel.advc.member.domain.Member;
import com.cdel.advc.member.facade.MemberFacade;
import com.cdel.advc.memberSms.domain.MemberSms;
import com.cdel.advc.sms.handler.SmsSender;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.StringUtil;

/**
 * <p>
 * 学员手机短信 facade层
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-17 下午5:24:15
 */
@SuppressWarnings("serial")
@Service
public class MemberSmsFacade extends BaseFacadeImpl<MemberSms, Integer> {

	@Autowired
	private MemberFacade memberFacade;

	/**
	 * 向班级成员群发手机短信
	 * 
	 * @param sms
	 * @throws Exception
	 */
	public boolean addClassMemberSmss(MemberSms sms) throws Exception {
		Integer classID = sms.getClassID();
		Integer courseID = sms.getCourseID();
		String content = sms.getContent();
		Integer sender = sms.getSender();

		Member member = new Member();
		member.setClassID(classID);
		member.setCourseID(courseID);
		member.setMajorID(sms.getMajorID());
		member.setSmsStatus((short) 1);

		// 发送短信有效学员
		List<Member> members = this.memberFacade.getMemberList(member);
		int size = 0;

		if (members == null || (size = members.size()) == 0) {
			return addWarnMessage("没有需要发短信的学员！");
		}

		List<MemberSms> smss = new ArrayList<MemberSms>();// 手机短信列表
		MemberSms tempSms = null;
		String[] mumbers = new String[size];// 学员号码
		for (int i = 0; i < size; i++) {

			if (members.get(i).getSmsStatus() == 0) {
				continue;
			}

			if (!StringUtil.validateTelphone(members.get(i).getTelPhone())) {
				continue;
			}

			tempSms = new MemberSms();
			tempSms.setUserID(members.get(i).getUserID());
			tempSms.setClassID(members.get(i).getClassID());
			tempSms.setContent(content);
			tempSms.setMobile(members.get(i).getTelPhone());
			tempSms.setSender(sender);
			tempSms.setStatus((short) 1);
			tempSms.setSendTime(new Date());
			smss.add(tempSms);

			mumbers[i] = tempSms.getMobile();
		}

		if (smss.size() == 0) {
			return addWarnMessage("没有学员需要短信！");
		}

		// 发送短信
		new SmsSender().sendSms(sms.getContent(), mumbers);
		// 保存数据
		this.addList(smss);
		return true;
	}

	/**
	 * 发送单条短信，并存入数据库存档
	 * 
	 * @param sms
	 * @throws Exception
	 */
	public void addMemberSms(MemberSms sms) throws Exception {
		// 发送短信
		new SmsSender().sendSms(sms.getContent(),
				new String[] { sms.getMobile() });
		// 保存数据
		this.add(sms);

	}

	public boolean checkSms(MemberSms memberSms, Member member) {
		if (StringUtils.isBlank(memberSms.getContent())) {
			return addWarnMessage("短信内容不能为空！");
		}
		if (member.getSmsStatus().intValue() != 1) {
			return addWarnMessage("该学员已退订短信！");
		}
		if (member.getTelPhone() == null) {
			return addWarnMessage("未找到该学员手机号！");
		}
		if (!StringUtil.validateTelphone(member.getTelPhone())) {
			return addWarnMessage("该学员手机号不正确！");
		}
		return true;
	}

}
