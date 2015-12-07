package com.cdel.util;

import java.security.MessageDigest;

public class MD5 {

	public static String Md5(String plainText, int num) {
		StringBuffer buf = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(num ==16){
			return buf.toString().substring(8, 24);
		}else{
			return buf.toString();
		}
		
	}
}
