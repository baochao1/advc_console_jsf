package com.cdel.advc.sms.handler;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cdel.util.Contants;
import com.cdel.util.ContantsUrl;
import com.cdel.util.ExceptionUtil;
import com.cdel.util.RemoteUtil;
import com.cdel.util.XMLUtil;

public class Client {
	protected final Log logger = LogFactory.getLog(getClass());
	private boolean needReceive = true;

	public static void main(String args[]) throws Exception {
		Client test = new Client();
		test.setNeedReceive(false);
		@SuppressWarnings("unused")
		int j = test.send("短信测试!!!!!!!!!！！", new String[] { "13269606741",
				"15901320764" });
	}

	/**
	 * 发送短信，支持群发
	 * 
	 * @param phoneNumbers
	 *            群发时，手机号码以逗号相隔
	 * @param content
	 *            短信内容
	 * @return
	 * 
	 * @throws Exception
	 */
	public int send(String content, String[] moblies) throws Exception {
		if (moblies == null || moblies.length == 0) {
			throw new Exception("电话号码不能为空！");
		}
		if (StringUtils.isBlank(content)) {
			throw new Exception("内容不能为空！");
		}
		int result = 0;
		try {
			for (String phone : moblies) {
				if (StringUtils.isBlank(phone)) {
					continue;
				}
				Map<String, String> smsSendMap = new HashMap<String, String>();
				smsSendMap.put("phone", phone);
				smsSendMap.put("Business", "学员短信");
				smsSendMap.put("SendContent", content);
				String smsResult = RemoteUtil.getPostRemoteString(
						ContantsUrl.SMS_SERVER_URL, smsSendMap);
				if (StringUtils.isNotBlank(smsResult)) {
					String code = XMLUtil.getRootText(smsResult, "code");
					if (!"1".equals(code)) {
						logger.error("手机号=" + phone + "的手机返回错误信息："
								+ Contants.smsInfo.get(Integer.parseInt(code)));
					}
				} else {
					logger.error("手机号=" + phone + "的手机返回未知错误");
				}
			}
			result = 1;
		} catch (Exception e) {
			// 获取验证码返回数据解析错误
			logger.error("发送短信异常：" + ExceptionUtil.getEMsg(e));
			result = 0;
		}
		return result;
	}

	public boolean isNeedReceive() {
		return needReceive;
	}

	public void setNeedReceive(boolean needReceive) {
		this.needReceive = needReceive;
	}

}