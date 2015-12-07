package com.cdel.api.callCenter.classes;

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
 * 呼叫中心查询班级接口 servlet
 */
@SuppressWarnings("serial")
public class FindClassesServlet extends HttpServlet {

	protected static Logger logger = Logger.getLogger(FindClassesServlet.class);

	// @Autowired
	// @Qualifier("callCenterClassesFacade")
	private ClassesFacade classesFacade = null;

	/** 强制注入classesFacade */
	public void init(ServletConfig servletConfig) throws ServletException {
		WebApplicationContext wc = WebApplicationContextUtils
				.getWebApplicationContext(servletConfig.getServletContext());
		classesFacade = (ClassesFacade) wc.getBean("callCenterClassesFacade");
	}

	/** */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * 
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=" + Contants.CODE);

		String sign = RequestHandler.getString(request, "sign");// 数字签名
		String className = RequestHandler.getString(request, "className");
		String teacherName = RequestHandler.getString(request, "teacherName");

		if (!RequestCheckHelper.isRequestValid(className, sign)) {

			response.getWriter().print("无效请求!");
			logger.debug("无效请求：验证未通过！！！");
			return;
		}

		Classes classes = new Classes();
		classes.setClassName(className);
		classes.setTeacherName(teacherName);

		List<Classes> classess = null;
		try {
			classess = classesFacade.findClasses(classes);
		} catch (IllegalArgumentException e) {
			response.getWriter().print(e.getMessage());
			logger.debug("参数异常，" + e.getMessage());
			return;
		}

		JSONArray json = JSONArray.fromObject(classess);
		response.getWriter().print(json);

		logger.debug("请求成功! 返回值：" + json);
	}

}
