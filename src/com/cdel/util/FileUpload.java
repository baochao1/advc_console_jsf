package com.cdel.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import javax.servlet.ServletException;
import org.springframework.stereotype.Service;

@Service
public class FileUpload {
	private String time;
	private String securecode;

	public void initFileUpload() {
		time = DateUtil.getNowDateString(new Date(), "yyyy-MM-dd HH:mm:ss");
		securecode = MD5.Md5("1813QZ" + time, 16);
	}

	public String upload(InputStream is, String fileName, String contentType,String origin) throws ServletException, IOException {
		initFileUpload();
		DataOutputStream connOs = null;
		BufferedReader connBr = null;
		String fileServerUrl = Contants.fileServerUrl;
		URL url = new URL(fileServerUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Connection", "Keep-Alive");
		conn.setRequestProperty("Content-Type", contentType);
		String BOUNDARY = contentType.substring(contentType.indexOf("=") + 1);
		connOs = new DataOutputStream(conn.getOutputStream());
		conn.connect();

		StringBuffer buffer = new StringBuffer("");
		buffer.append("\r\n--" + BOUNDARY + "\r\n");
		buffer.append("Content-Disposition: form-data; name=\"");
		buffer.append("origin");
		buffer.append("\"\r\n\r\n");
		buffer.append(origin);
		buffer.append("\r\n--");

		buffer.append(BOUNDARY + "\r\n");
		buffer.append("Content-Disposition: form-data; name=\"");
		buffer.append("time");
		buffer.append("\"\r\n\r\n");
		buffer.append(time);
		buffer.append("\r\n--");

		buffer.append(BOUNDARY + "\r\n");
		buffer.append("Content-Disposition: form-data; name=\"");
		buffer.append("securecode");
		buffer.append("\"\r\n\r\n");
		buffer.append(securecode);
		buffer.append("\r\n");

		connOs.write(buffer.toString().getBytes());
		connOs.writeBytes((new StringBuilder("--"))
				.append(BOUNDARY)
				.append("\r\n")
				.append("Content-Disposition: form-data; name=\"uploadText\"; filename=\""
						+ fileName
						+ "\"\r\nContent-Type: application/octet-stream\r\n\r\n")
				.toString());
		byte[] b = new byte[Contants.BUFFER_SIZE];
		int num = 0;
		int size = 0;
		while ((num = is.read(b)) != -1) {
			connOs.write(b, 0, num);
			size = size + num;
		}
		connOs.writeBytes("\r\n");
		connOs.writeBytes((new StringBuilder("--")).append(BOUNDARY)
				.append("--\r\n").toString());
		String result = "error";
		if (size > Contants.FILE_SIZE) {
			result = "size";
		} else {
			connBr = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String str;
			while ((str = connBr.readLine()) != null) {
				sb.append(str);
			}
			String[] strArray = sb.toString().split("\"url\":\"");
			if (strArray != null && strArray.length > 0) {
				for (int i = 1; i < strArray.length; i++) {
					result = strArray[i].substring(0, strArray[i].length() - 4);
				}
			}
		}
		connOs.flush();
		connOs.close();
		connBr.close();
		is.close();
		return result;
	}

}
