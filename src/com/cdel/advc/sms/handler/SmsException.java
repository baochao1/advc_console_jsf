/*
 * @Title: SmsException.java
 * @Package com.cdel.sms.handler
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-7-18 上午9:33:20
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-7-18                          
 */
package com.cdel.advc.sms.handler;

/**
 * <p>
 * 短信发送自定义异常
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-18 上午9:33:20
 */
@SuppressWarnings("serial")
public class SmsException extends RuntimeException {

	public SmsException() {
		super();
	}

	public SmsException(String message, Throwable cause) {
		super(message, cause);
	}

	public SmsException(String message) {
		super(message);
	}

	public SmsException(Throwable cause) {
		super(cause);
	}

}
