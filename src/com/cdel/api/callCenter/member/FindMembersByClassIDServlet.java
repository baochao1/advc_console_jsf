package com.cdel.api.callCenter.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cdel.api.callCenter.RequestCheckHelper;
import com.cdel.util.Contants;
import com.chnedu.plat.rad.util.RequestHandler;

/**
 * 呼叫中心查询学员接口 servlet
 */
@SuppressWarnings("serial")
public class FindMembersByClassIDServlet extends HttpServlet {

	protected static Logger logger = Logger
			.getLogger(FindMembersByClassIDServlet.class);

	private MemberFacade memberFacade = null;

	/** 强制注入memberFacade */
	public void init(ServletConfig servletConfig) throws ServletException {
		WebApplicationContext wc = WebApplicationContextUtils
				.getWebApplicationContext(servletConfig.getServletContext());
		memberFacade = (MemberFacade) wc.getBean("callCenterMemberFacade");
	}

	/** */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=" + Contants.CODE);

		Integer classID = RequestHandler.getInteger(request, "classID");
		String sign = RequestHandler.getString(request, "sign");// 数字签名

		if (!RequestCheckHelper.isRequestValid(classID, sign)) {
			response.getWriter().print("无效请求!");
			return;
		}

		List<Member> classess = null;
		try {
			classess = this.memberFacade.findMembersByClassID(classID);
		} catch (IllegalArgumentException e) {
			response.getWriter().print(e.getMessage());
			return;
		}

		JSONArray json = JSONArray.fromObject(classess);
		response.getWriter().print(json);
	}

}
