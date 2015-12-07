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
 * 呼叫中心   根据 最后一次更新时间   查询班级接口 servlet
 */
@SuppressWarnings("serial")
public class FindClassesByUpdateTimeServlet extends HttpServlet {

	protected static Logger logger = Logger.getLogger(FindClassesByUpdateTimeServlet.class);

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
	        this.doPost(request, response);
	}

	/**
	 * 回值：-1：密钥不一致
	 *	-2：参数无效
     *   1：返回一个json格式文件，内容为新增或者修改的班级信息。
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=" + Contants.CODE);
        
		String siteID = RequestHandler.getString(request, "siteID");// 网站ID
		String keyTime = RequestHandler.getString(request, "keyTime"); // 当前时间，格式如下：”20140210145720”
		String key = RequestHandler.getString(request, "key"); // md5(siteID+ keyTime+ privateKey)(32位大写)
		Integer count = RequestHandler.getInteger(request, "count"); // 查询的记录数  
		String updateTime = RequestHandler.getString(request, "updateTime");// 最后一次更新时间
		
		String str = RequestCheckHelper.commonRequestValid(key,siteID,keyTime);
		if (str.equals("1")) {
			Classes classes = new Classes();
			classes.setCount(count);
			classes.setUpdateTime(updateTime);
			classes.setSiteID(Integer.valueOf(siteID));
			List<Classes> classess = null;
			try {
				classess = classesFacade.findClassesByUpdateTime(classes);
			} catch (IllegalArgumentException e) {
				response.getWriter().print(e.getMessage());
				logger.debug("参数异常，" + e.getMessage());
				return;
			}
			JSONArray json = JSONArray.fromObject(classess);
			response.getWriter().print(json);
			logger.debug("请求成功! 返回值：" + json);
		} else {
			response.getWriter().print(str);
		}
	}

}
