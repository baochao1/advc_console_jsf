/*
 * @Title: CommonUtils.java
 * @Package com.cdeledu.plat.api.callCenter
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-10-14 下午5:17:04
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-10-14                          
 */
package com.cdel.api.callCenter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.cdel.util.Contants;

/**
 * <p>
 * 请求验证 帮助工具类
 * </p>
 *  
 * @author Du Haiying Create at:2013-10-14 下午5:17:04
 */
public class RequestCheckHelper {

	protected static Logger logger = Logger.getLogger(RequestCheckHelper.class);

	// private static final long MAX_TIME = 600000;

	/** 呼叫中心请求验证 key值 */
	private static final String KEY = "hujiao!@#$%^";

	/**
	 * 验证请求是否有效，无效请求不允许访问
	 */
	public static boolean isRequestValid(String className, String sign)
			throws UnsupportedEncodingException {
		if (StringUtils.isBlank(className) || StringUtils.isBlank(sign)) {
			logger.debug("无效请求，className：" + className + ", sign: " + sign);
			return false;
		}
		String mySign = new MD5().getMD5ofStr(URLEncoder.encode(className,
				Contants.CODE) + KEY);// MD5中文加密会有编码问题！！！
		logger.debug("请求，className：" + className + ", KEY：" + KEY + ", mySign："
				+ mySign + ", sign: " + sign);

		return mySign.equals(sign);
	}

	/**
	 * 通用的参数验证
	 *  -1：密钥不一致
	 *	-2：参数无效
     *   1：请求成功
	 * @param params
	 * @param sign
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String commonRequestValid(String sign, String...params) {
		if (StringUtils.isBlank(sign)) {
			logger.debug("无效参数，sign：" + sign );
			return "-2";
		}
		String param = "";
		if(params.length>0){
			for(String str :params){
				if (StringUtils.isBlank(str)) {
					logger.debug("无效参数，param：" + str );
					return "-2";
				}
				param += str;
			}
		}else {
				logger.debug("无效请求，params：" + params );
				return "-2";
		}
		 
		if (StringUtils.isBlank(param) || StringUtils.isBlank(sign)) {
			logger.debug("无效请求，param：" + param + ", sign: " + sign);
			return "-2";
		}
		
		String mySign = new MD5().getMD5ofStr(param + KEY);// MD5中文加密会有编码问题！！！
		logger.debug("请求，param：" + param + ", KEY：" + KEY + ", mySign："
				+ mySign + ", sign: " + sign);
		
		return mySign.equals(sign)? "1":"-1";
	}

	/**
	 * 验证请求是否有效，无效请求不允许访问
	 */
	public static boolean isRequestValid(Integer classID, String sign) {
		if (classID == null || StringUtils.isBlank(sign)) {
			logger.debug("无效请求，classID：" + classID + ", sign: " + sign);
			return false;
		}

		String mySign = new MD5().getMD5ofStr(classID + KEY);
		logger.debug("请求，classID：" + classID + ", KEY：" + KEY + ", mySign："
				+ mySign + ", sign: " + sign);

		return mySign.equals(sign);
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
//		siteID:1
//		keyTime:20140319155046
//		privateKey:hujiao!@#$%^
//		key:8E4B62A17BCB2CE536E1205E5548B8AB

		 System.out.println(RequestCheckHelper.commonRequestValid("8E4B62A17BCB2CE536E1205E5548B8AB","1","20140319155046"));
	}

}
