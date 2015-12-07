package com.cdel.advc.membercall.facade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdel.advc.combo.facade.ComboFacade;
import com.cdel.advc.membercall.domain.MemberCallRecord;
import com.cdel.advc.memberclass.domain.MemberClass;
import com.cdel.advc.memberclass.facade.MemberClassFacade;
import com.cdel.advc.studentCourse.domain.BuyCourse;
import com.cdel.advc.studentCourse.facade.StudentCourseFacade;
import com.cdel.advc.website.domain.Website;
import com.cdel.advc.website.facade.WebsiteFacade;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.JsonUtil;

@SuppressWarnings("serial")
@Service
public class MemberCallRecordFacade extends
		BaseFacadeImpl<MemberCallRecord, Integer> implements Serializable {
	@Autowired
	private MemberClassFacade memberClassFacade;
	@Autowired
	private StudentCourseFacade studentCourseFacade;
	@Autowired
	private WebsiteFacade websiteFacade;
	@Autowired
	private ComboFacade comboFacade;

	/**
	 * 检测学员的报课情况
	 * 
	 * @param userID
	 * @param classID
	 * @return:0学员退班,-1没配asp地址,1课程已关闭,2正常
	 */
	public byte checkMemberInit(Integer siteID, Integer userID,
			Integer classID, String userName) throws Exception {
		MemberClass mc = new MemberClass();
		mc.setUserID(userID);
		mc.setClassID(classID);
		mc.setStatus((short) 1);
		mc.setClassStatus((short) 1);
		List<String> courseList = memberClassFacade.getMemberClassCodes(mc);
		if (courseList != null && courseList.size() > 0) {
			Website website = websiteFacade.findByID(siteID);
			if (StringUtils.isBlank(website.getSelCourseUrl())) {
				return (byte) -1;
			}
			String resultData = studentCourseFacade.getBuyCourseFromRemote(userName,
					website, userID);
			List<BuyCourse> bcList = JsonUtil.parseBuyCourse(resultData);
			StringBuffer sb = new StringBuffer("");
			if (bcList != null && bcList.size() > 0) {
				for (BuyCourse c : bcList) {
					sb.append("'").append(c.getCourseCode()).append("',");
				}
			}
			if (StringUtils.isNotBlank(sb.toString())) {
				// 通过套餐码查课程
				String comboCodes = sb.substring(0, sb.length() - 1);
				List<String> codesByCombo = comboFacade
						.getCourseCodesByCombo(comboCodes);
				for (String c : codesByCombo) {
					sb.append("'").append(c).append("',");
				}
				int status = 0;
				for (String coursecode : courseList) {
					if (sb.indexOf("'" + coursecode + "'") != -1) {
						// 班级中信息还在主库选课表中
						status = 1;
						break;
					}
				}
				// 虽然选课信息有，但是不包括此班级中信息了
				if (status == 0) {
					return (byte) 1;
				} else {
					return (byte) 2;
				}
			} else {
				return (byte) 1;
			}
		} else {
			return (byte) 0;
		}
	}

	/**
	 * 更新回访统计
	 * 
	 * @param userID
	 * @param classID
	 * @param callTime
	 * @param callStatus
	 * @throws Exception
	 */
	public void updateRecord(Integer userID, Integer classID, Date callTime,
			Short callStatus) throws Exception {
		MemberCallRecord mcr = new MemberCallRecord();
		mcr.setUserID(userID);
		mcr.setClassID(classID);
		mcr.setLastCallTime(callTime);
		if (callStatus == 1) {
			mcr.setSuccessCallTime(callTime);
		}
		if (getMemberCallRecord(mcr) == null) {
			add(mcr);
		} else {
			update(mcr);
		}
	}

	/**
	 * 获取记录
	 * 
	 * @param mcr
	 * @return
	 */
	public MemberCallRecord getMemberCallRecord(MemberCallRecord mcr) {
		return this.baseDao.getByEntity(mcr, "getMemberCallRecord");
	}

}
