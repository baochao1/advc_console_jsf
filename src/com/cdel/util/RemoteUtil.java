package com.cdel.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 用于远程链接
 * 
 * @author zhangsulei
 * 
 */
public class RemoteUtil {

	/**
	 * 请求编码
	 */
	public final static String REQUEST_ENCODING = Contants.CODE;

	/**
	 * 连接超时(毫秒)
	 */
	public final static int CONNECT_TIMEOUT = 5000;

	/**
	 * 读取数据超时(毫秒)
	 */
	public final static int READ_TIMEOUT = 10000;

	/**
	 * 根据远程服务地址获取返回内容
	 * 
	 * @param aspServer
	 * @param sendData
	 * @return
	 * @throws Exception
	 */
	public static String getRemoteString(String aspServer, String sendData,
			String code) throws Exception {
		StringBuffer resultData = new StringBuffer("");
		URL url = new URL(aspServer + sendData);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.connect();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				conn.getInputStream(), code));
		String str;
		while ((str = br.readLine()) != null) {
			resultData.append(str);
		}
		br.close();
		return resultData.toString();
	}

	/**
	 * 根据远程服务地址获取返回内容(post)
	 * 
	 * @param aspServer
	 * @param sendData
	 * @return
	 * @throws Exception
	 */
	public static String getPostRemoteString(String aspServer,
			Map<String, String> parameters) throws Exception {
		StringBuffer params = new StringBuffer();
		for (Iterator<?> iter = parameters.entrySet().iterator(); iter
				.hasNext();) {
			Entry<?, ?> element = (Entry<?, ?>) iter.next();
			params.append(element.getKey().toString());
			params.append("=");
			params.append(URLEncoder.encode(element.getValue().toString(),
					REQUEST_ENCODING));
			params.append("&");
		}
		if (params.length() > 0) {
			params = params.deleteCharAt(params.length() - 1);
		}

		StringBuffer resultData = new StringBuffer("");
		URL url = new URL(aspServer);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		System.setProperty("sun.net.client.defaultConnectTimeout",
				String.valueOf(CONNECT_TIMEOUT));// （单位：毫秒）jdk1.4换成这个,连接超时
		System.setProperty("sun.net.client.defaultReadTimeout",
				String.valueOf(READ_TIMEOUT)); // （单位：毫秒）jdk1.4换成这个,读操作超时
		conn.setDoOutput(true);
		byte[] b = params.toString().getBytes();
		conn.getOutputStream().write(b, 0, b.length);
		conn.getOutputStream().flush();
		conn.getOutputStream().close();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				conn.getInputStream(), REQUEST_ENCODING));
		String str;
		while ((str = br.readLine()) != null) {
			resultData.append(str);
		}
		br.close();
		return resultData.toString();
	}

}
