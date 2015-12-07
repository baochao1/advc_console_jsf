package com.cdel.api.callCenter.memberCall;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cdel.api.callCenter.RequestCheckHelper;
import com.cdel.util.Contants;
import com.cdel.util.DateAdapter;
import com.chnedu.plat.rad.util.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * 呼叫中心   根据 最后一次更新时间   查询班级接口 servlet
 * http://192.168.190.109/advc/api/callCenter/member/saveMemberCall?type= &siteID= &keyTime= &key=  &keyTime=
 */
@SuppressWarnings("serial")
public class SaveMemberCallServlet extends HttpServlet {

	protected static Logger logger = Logger.getLogger(SaveMemberCallServlet.class);

	// @Autowired
	// @Qualifier("callCenterClassesFacade")
	private MemberCallFacade memberCallFacade = null;

	/** 强制注入classesFacade */
	public void init(ServletConfig servletConfig) throws ServletException {
		WebApplicationContext wc = WebApplicationContextUtils
				.getWebApplicationContext(servletConfig.getServletContext());
		memberCallFacade = (MemberCallFacade) wc.getBean("callCenterMemberCallFacade");
	}

	/** */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	        this.doPost(request, response);
	}

	/**
	 * 回值：-1：密钥不一致
	 *	-2：参数无效
     *   1：返回一个json格式文件，内容为新增或者修改的  学员 信息。
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=" + Contants.CODE);
		//回访系统标志type : 0: 原先第一个回访系统的的标志，1：第二个回访系统个标志
		Integer type = RequestHandler.getInteger(request, "type");
        if(type==null){
        	type = 0;
        }
        
		String siteID = RequestHandler.getString(request, "siteID");// 网站ID
		String keyTime = RequestHandler.getString(request, "keyTime"); // 当前时间，格式如下：”20140210145720”
		String key = RequestHandler.getString(request, "key"); // md5(siteID+ keyTime+ privateKey)(32位大写)
		String jsonData = RequestHandler.getString(request, "jsonData"); // 需要同步的回访记录
		// jsonData：json list 字符串 。
		// 回访信息包含字段信息如下：回访ID(outboundID),班级ID(classID)，
		// 学员编号(userID)，学习状况(studyStatus)，回访状态(callStatus),会访人(caller), 回访时间(callTime) 
		String str = RequestCheckHelper.commonRequestValid(key,siteID,keyTime);
		if (str.equals("1")) {
			if (StringUtils.isNotBlank(jsonData)) {
				try {
					Gson gson = null;
					GsonBuilder builder = new GsonBuilder();
					builder.registerTypeAdapter(Date.class, new DateAdapter());
					builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
					gson = builder.create();
					List<MemberCall> memberCallList = gson.fromJson(jsonData,
							new TypeToken<List<MemberCall>>() {
							}.getType());
					memberCallFacade.insertMemberCall(memberCallList,type);
					response.getWriter().print(str.trim());
				} catch (Exception e) {
					e.printStackTrace();
					logger.debug("参数异常，" + e.getMessage());
					return;
				}
			} else {
				response.getWriter().print("-2");
			}
		} else {
			response.getWriter().print(str);  
		}
	}
}
