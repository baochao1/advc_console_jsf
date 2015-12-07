package com.cdel.api.ext;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cdel.advc.combo.domain.Combo;
import com.cdel.advc.course.domain.Course;
import com.cdel.advc.divide.domain.Divide;
import com.cdel.advc.divide.facade.DivideFacade;
import com.cdel.advc.major.domain.Major;
import com.cdel.advc.member.domain.Member;
import com.cdel.advc.member.facade.MemberFacade;
import com.cdel.advc.memberclass.domain.MemberClass;
import com.cdel.advc.memberclass.facade.MemberClassFacade;
import com.cdel.advc.strategy.domain.Strategy;
import com.cdel.advc.strategy.facade.StrategyFacade;
import com.cdel.advc.studentCourse.domain.BuyCourse;
import com.cdel.advc.studentCourse.facade.StudentCourseFacade;
import com.cdel.advc.website.domain.Website;
import com.cdel.advc.website.facade.WebsiteFacade;
import com.cdel.plat.grant.facade.LoginFacade;
import com.cdel.util.Contants;
import com.cdel.util.ExceptionUtil;
import com.cdel.util.JsonUtil;
import com.chnedu.plat.rad.util.RequestHandler;

/**
 * 呼叫中心查询学员接口 servlet
 */
@SuppressWarnings("serial")
public class CheckDivideServlet extends HttpServlet {

	protected static Logger logger = Logger.getLogger(CheckDivideServlet.class);

	private LoginFacade loginFacade;
	private MemberFacade memberFacade;
	private WebsiteFacade websiteFacade;
	private DivideFacade divideFacade;
	private MemberClassFacade memberClassFacade;
	private StrategyFacade strategyFacade;
	private StudentCourseFacade studentCourseFacade;

	private List<BuyCourse> bcList;// 学员所报课程
	private Integer userID;// 查询的学员ID
	private List<Divide> autoList;// 符合的策略
	private List<MemberClass> mcList; // 学员所在班级
	private Map<Major, Set<Course>> buyCourseMap;

	/** 强制注入memberFacade */
	public void init(ServletConfig servletConfig) throws ServletException {
		WebApplicationContext wc = WebApplicationContextUtils
				.getWebApplicationContext(servletConfig.getServletContext());
		loginFacade = (LoginFacade) wc.getBean("loginFacade");
		memberFacade = (MemberFacade) wc.getBean("memberFacade");
		websiteFacade = (WebsiteFacade) wc.getBean("websiteFacade");
		divideFacade = (DivideFacade) wc.getBean("divideFacade");
		memberClassFacade = (MemberClassFacade) wc.getBean("memberClassFacade");
		strategyFacade = (StrategyFacade) wc.getBean("strategyFacade");
		studentCourseFacade = (StudentCourseFacade) wc
				.getBean("studentCourseFacade");
	}

	/** */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=" + Contants.CODE);
		String operator = RequestHandler.getString(request, "operator");
		Integer siteID = RequestHandler.getInteger(request, "websiteID");
		if (siteID == null) {
			siteID = RequestHandler.getInteger(request, "siteID");
		}
		String userName = RequestHandler.getString(request, "userName");
		userID = RequestHandler.getInteger(request, "userid");
		
		String op = RequestHandler.getString(request, "op");
		if (StringUtils.isNotBlank(op) && op.equals("view")) {
			logger.warn("API----View-Hand=["+op+"]----userID=" + userID+"--userName:"+ userName);
			// 查看分班信息接口
			if (userID != null) {
				response.sendRedirect("/faces/page/api/divideList.xhtml?userID="
						+ userID
						+ "&userName="
						+ userName
						+ "&siteID="
						+ siteID + "&operator=" + operator);
			} else {
				response.sendRedirect("/faces/page/api/divideList.xhtml?userName="
						+ userName
						+ "&siteID="
						+ siteID
						+ "&operator="
						+ operator);
			}
			return;
		}
		logger.info("分班检测开始！");
		logger.warn("API----Operate-Auto=["+op+"]---userID=" + userID+"--userName:"+ userName);
		String rtnStr = "";
		if (StringUtils.isBlank(operator) || StringUtils.isBlank(userName)
				|| siteID == null) {
			logger.error("参数不全！");
		}
		int re = loginFacade.loginSet2(operator, request.getSession());
		if (re == -1) {
			logger.error("对不起，该操作员用户不存在！");
		}
		try {
			Website website = websiteFacade.findByID(siteID);
			if (StringUtils.isBlank(website.getSelCourseUrl())) {
				logger.error("网站对应的ASP选课数据服务器没有配置！");
				return;
			}
			Member member = memberFacade.getMemberByName(userID,
					userName.trim(), website);
			MemberClass memberClass = new MemberClass();
			userID = member.getUserID();
			memberClass.setUserID(userID);
			memberClass.setStatus((short) 1);
			// 获取所在班级
			mcList = memberClassFacade.getMemberClassesInfo(memberClass);
			String resultData = studentCourseFacade.getBuyCourseFromRemote(
					userName.trim(), website, userID);
			// 获取报课信息
			bcList = JsonUtil.parseBuyCourse(resultData);
			logger.warn("API----Operate-Auto=["+op+"]----BuyCourseList=" + bcList);

			int num = 0;
			if (bcList == null || bcList.size() == 0) {
				logger.error("学员没有报课！");
			} else {
				List<Object> result = divideFacade.getSelCourseCodeList(siteID,
						bcList);
				bcList = (List<BuyCourse>) result.get(2);
				logger.warn("API----Operate-Auto=["+op+"]====BuyCourseList=" + bcList);

				if (bcList == null || bcList.size() == 0) {
					logger.error("学员所报课程与本系统实验精品课程、套餐无一匹配！");
				} else {
					// 获取策略
					autoList = divideFacade.getAutoMap(
							(List<Course>) result.get(0),
							(List<Combo>) result.get(1), mcList, true);
					if (autoList != null && autoList.size() > 0) {
						Divide divide = autoList.get(0);
						if (divide != null) {
							List<Strategy> stralist = divide.getStrategyList();
							
							if (stralist != null && stralist.size() == 1) { // 只有一个分班策略，自动分班
								Strategy sty = stralist.get(0);
								String studyCourse = sty.getStudyCourse();
								MemberClass oldMc = divideFacade.validate(userID, sty.getTermID(),
										sty.getStrategyID(), studyCourse,sty.getStrategyType(),
										sty.getMajorID(), sty.getLimitNum());
								if (oldMc != null) {
									MemberClass mc = new MemberClass();
									mc.setUserID(userID);
									mc.setTermID(sty.getTermID());
									mc.setStudyCourse(divideFacade.restStudyCourse(studyCourse));
									if (oldMc.getClassID() == null) {
										oldMc = null;
									}
									divideFacade.addMemberToClass(mc,oldMc,RequestHandler.getCurrentAdmin(request),
													(String) request.getSession().getAttribute(Contants.ADMIN_REAL_NAME),
													sty.getStrategyID(), sty.getLimitNum(), sty.getMajorID());
									num = 1; // 针对一个策略分班成功
								} else {
									rtnStr = "-1";
								}
							}
						}
					}
				}
			}
	 
			rtnStr += getBuyMajor() + "|";
			rtnStr += getMcCount(num)  + "|";
			rtnStr += getNotMatchMcCount();
		} catch (Exception e) {
			logger.error("分班检测接口错误！");
			logger.error("操作人:" + operator + ",学员:" + userName);
			logger.error(ExceptionUtil.getEMsg(e));
			e.printStackTrace();
			re = -1;
		}
		logger.info("分班检测结束！");
		response.getWriter().println(rtnStr);
	}

	public int getMcCount(int num) {
		int i = 0;
		if (mcList != null && mcList.size()>0) {
			i = mcList.size();
		}else{
			i = num;
		}
		return i;
	}

	public int getBuyMajor() {
		int i = 0;
		Map<Major, Set<Course>>[] buyCourseArr = divideFacade.getBuyCourseArr();
		if (buyCourseArr != null && buyCourseArr.length > 0) {
			buyCourseMap = buyCourseArr[0];
			if (buyCourseMap != null && buyCourseMap.keySet() != null) {
				i = buyCourseMap.keySet().size();
			}
		}
		return i;
	}

	public int getNotMatchMcCount() throws Exception {
		int i = 1;
		if (mcList != null && buyCourseMap == null) {
			i = 0;
		}
		if (mcList != null && buyCourseMap != null) {
			if (buyCourseMap.keySet() == null) {
				return i;
			}
			Map<Integer, String> bcMap = new HashMap<Integer, String>();
			Iterator<Major> it = buyCourseMap.keySet().iterator();
			while (it.hasNext()) {
				Major m = it.next();
				String buyCourse = "";
				Set<Course> courseSet = buyCourseMap.get(m);
				Iterator<Course> iterator = courseSet.iterator();
				Integer[] courseIDArr = new Integer[courseSet.size()];
				int j = 0;
				while (iterator.hasNext()) {
					Course c = iterator.next();
					courseIDArr[j] = c.getCourseID();
					j++;
				}
				Arrays.sort(courseIDArr);
				for (Integer id : courseIDArr) {
					buyCourse += "," + id.toString() + ",";
				}
				bcMap.put(m.getMajorID(), buyCourse);
			}
			for (MemberClass mc : mcList) {
				Strategy stra = strategyFacade.findByID(mc.getStrategyID());
				if (stra != null) {
					if (stra.getIsAuto() == 1) {
						if (stra.getCourseRegex() == null
								|| bcMap.get(stra.getMajorID()) == null
								|| !divideFacade.getStrategyByReg(
										bcMap.get(mc.getMajorID().intValue()),
										stra)) {
							i = 0;
							break;
						}
					}
				}
			}
		}
		return i;
	}

}
