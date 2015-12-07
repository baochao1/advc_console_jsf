package com.cdel.advc.memberdefine.facade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.cdel.advc.memberdefine.domain.Examkill;
import com.cdel.advc.memberdefine.domain.Memberdefine;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.CheckUtil;

@SuppressWarnings("serial")
@Service
public class MemberdefineFacade extends BaseFacadeImpl<Memberdefine, Integer> {
	/**
	 * 获取学员的信息反馈
	 * 
	 * @param userID
	 * @return
	 */
	public Memberdefine getMemberdefineByUserID(Integer userID) {
		return baseDao.get(userID, "getMemberdefineByUserID");
	}

	/**
	 * 清除学员反馈信息
	 * 
	 * @param memberdefineLog
	 * @throws Exception
	 */
	public void updateMemberdefine2(Integer userID) {

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("userID", userID);

		baseDao.update(map, "updateMemberdefine2");
	}

	/**
	 * 验证已往考试经历等
	 * 
	 * @param msg
	 * @param context
	 * @param examkillList
	 */
	public boolean checkExamKill(List<Examkill> examkillList, Memberdefine md) {
		if (examkillList != null && examkillList.size() > 0) {
			for (int i = 0; i < examkillList.size(); i++) {
				Examkill ek = examkillList.get(i);
				String ename = ek.getExamName();
				String year = ek.getYear();
				String score = ek.getScore();
				if (StringUtils.isBlank(ename) || StringUtils.isBlank(year)
						|| StringUtils.isBlank(score)) {
					return addWarnMessage("请输入完整信息！");
				}
				if (StringUtils.indexOf(ename, ",") > -1
						|| StringUtils.indexOf(ename, "|") > -1) {
					return addWarnMessage("考试名称：" + ename + "不能包含,或|");
				}
				if (StringUtils.indexOf(year, ",") > -1
						|| StringUtils.indexOf(year, "|") > -1) {
					return addWarnMessage("考试年度：" + year + "不能包含,或|");
				}
				if (!StringUtils.isNumeric(year)) {
					return addWarnMessage("考试年度：" + year + "必须为数字");
				}
				if (StringUtils.indexOf(score, ",") > -1
						|| StringUtils.indexOf(score, "|") > -1) {
					return addWarnMessage("成绩：" + score + "不能包含,或|");
				}
			}
		}
		String msg = "学习时长为1-24的整数或带1位小数";
		if (StringUtils.isBlank(md.getWeek1())
				|| !CheckUtil.checkFloat2(md.getWeek1(), 2, 1)
				|| new Double(md.getWeek1()) < 0.0
				|| new Double(md.getWeek1()) > 24.0) {
			return addWarnMessage("星期一" + msg);
		}
		if (StringUtils.isBlank(md.getWeek2())
				|| !CheckUtil.checkFloat2(md.getWeek2(), 2, 1)
				|| new Double(md.getWeek2()) < 0.0
				|| new Double(md.getWeek2()) > 24.0) {
			return addWarnMessage("星期二" + msg);
		}
		if (StringUtils.isBlank(md.getWeek3())
				|| !CheckUtil.checkFloat2(md.getWeek3(), 2, 1)
				|| new Double(md.getWeek3()) < 0.0
				|| new Double(md.getWeek3()) > 24.0) {
			return addWarnMessage("星期三" + msg);
		}
		if (StringUtils.isBlank(md.getWeek4())
				|| !CheckUtil.checkFloat2(md.getWeek4(), 2, 1)
				|| new Double(md.getWeek4()) < 0.0
				|| new Double(md.getWeek4()) > 24.0) {
			return addWarnMessage("星期四" + msg);
		}
		if (StringUtils.isBlank(md.getWeek5())
				|| !CheckUtil.checkFloat2(md.getWeek5(), 2, 1)
				|| new Double(md.getWeek5()) < 0.0
				|| new Double(md.getWeek5()) > 24.0) {
			return addWarnMessage("星期五" + msg);
		}
		if (StringUtils.isBlank(md.getWeek6())
				|| !CheckUtil.checkFloat2(md.getWeek6(), 2, 1)
				|| new Double(md.getWeek6()) < 0.0
				|| new Double(md.getWeek6()) > 24.0) {
			return addWarnMessage("星期六" + msg);
		}
		if (StringUtils.isBlank(md.getWeek7())
				|| !CheckUtil.checkFloat2(md.getWeek7(), 2, 1)
				|| new Double(md.getWeek7()) < 0.0
				|| new Double(md.getWeek7()) > 24.0) {
			return addWarnMessage("星期日" + msg);
		}
		return true;
	}

}
