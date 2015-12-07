package com.cdel.api.exitClass;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cdel.advc.divide.facade.DivideFacade;
import com.cdel.advc.memberclass.domain.MemberClass;
import com.cdel.advc.memberclass.facade.MemberClassFacade;
import com.cdel.api.callCenter.classes.FindClassesByUpdateTimeServlet;
import com.cdel.util.Contants;
import com.cdel.util.ExceptionUtil;
import com.chnedu.plat.rad.util.RequestHandler;

/**
 *  退班接口
 *  http://qz.chinaacc.com/exitClassServlet/operate.do?userID=42226763&classID=5948
 * @author xxg
 * 
 */
@SuppressWarnings("serial")
public class ExitClassServlet  extends HttpServlet{

	protected static Logger logger = Logger
			.getLogger(FindClassesByUpdateTimeServlet.class);

	 
	private DivideFacade divideFacade;
	private MemberClassFacade memberClassFacade;

	/** 强制注入memberClassFacade */
	public void init(ServletConfig servletConfig) throws ServletException {
		WebApplicationContext wc = WebApplicationContextUtils
				.getWebApplicationContext(servletConfig.getServletContext());
		memberClassFacade = (MemberClassFacade) wc.getBean("memberClassFacade");
		divideFacade =  (DivideFacade) wc.getBean("divideFacade");
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=" + Contants.CODE);
		Integer creator = 8888; // 操作人
		String creatorName = "APIExitClass"; // 操作人姓名

		Integer userID = RequestHandler.getInteger(request, "userID");
		Integer classID = RequestHandler.getInteger(request, "classID");

		byte sign = 0; // 
		MemberClass mc = new MemberClass();
		if(userID==null || classID==null){
			sign = -1;// 参数不完整
		}else if(userID !=null  &&  classID !=null){
			mc.setUserID(userID);
			mc.setClassID(classID);
			mc = memberClassFacade.getMemberClass(mc);
			if (mc == null || mc.getStatus() == 0) {
				sign = 0; //  学员已不在该班，无法退班！ 
			}else {
				mc.setStatus((short) 0);
				try {
					divideFacade.removeMemberClass(mc, creator, creatorName);
					sign = 1; // 退班成功
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("userID:" + userID + "学员退班！");
					logger.error(ExceptionUtil.getEMsg(e));
					sign = 2; // 退班异常
				}
			}
		}
		response.getWriter().println(sign);
	}
}
