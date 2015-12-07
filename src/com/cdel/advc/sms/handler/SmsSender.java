/*
 * @Title: SmsSender.java
 * @Package com.cdel.sms.handler
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-7-18 上午9:24:33
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-7-18                          
 */
package com.cdel.advc.sms.handler;

import com.cdel.util.Contants;

/**
 * <p>
 * 发送短信
 * </p>
 * 
 * @author Du Haiying Create at:2013-7-18 上午9:24:33
 */
public class SmsSender {

	private Client client;

	public SmsSender() {
		this.client = new Client();
		this.client.setNeedReceive(false);
	}

	public SmsSender(boolean received) {
		this.client = new Client();
		this.client.setNeedReceive(received);
	}

	/**
	 * 发送短信（支持群发）（单条短信内容不超过70个字）......？？？
	 * 
	 * @param content
	 *            短信内容
	 * @param moblies
	 *            手机号字符串
	 * 
	 * @throws Exception
	 */
	public void sendSms(String content, String[] moblies) throws Exception {
		int length = 0;
		int sendStatus = 0;
		while ((length = content.length()) > 0) {
			String sendContent = null;
			if (length > 69) {
				sendContent = content.substring(0, 65);
				content = content.substring(65);
			} else {
				sendContent = content;
				content = "";
			}

			sendStatus = client.send(sendContent, moblies);

			if (sendStatus != 1) {// 发送不成功，直接退出
				throw new Exception(Contants.smsInfo.get(sendStatus));
			}
		}
	}

}
