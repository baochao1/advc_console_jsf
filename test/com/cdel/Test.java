package com.cdel;

import java.util.Date;
import java.util.List;

import com.cdel.api.callCenter.memberCall.MemberCall;
import com.cdel.util.DateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Test {

	/**
	 * json测试方法
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String jsonData = "[{'callStatus':0,'callTime':'2014-05-20 17:28:54.12','classID':4900,'outboundID':88,'studyStatus':'1','updateTime':'2014-05-19 17:28:54.12','userID':40343653},"
				+ "{'callStatus':0,'callTime':'2015-05-20 17:28:54.66','classID':4900,'outboundID':88,'studyStatus':'1','updateTime':'2016-07-19 17:28:54.12','userID':40343653}]";
		Gson gson = new Gson();
		// GsonBuilder builder = new GsonBuilder();
		// builder.registerTypeAdapter(Date.class, new DateAdapter());
		// builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		// gson = builder.create();
		List<MemberCall> memberCallList = gson.fromJson(jsonData,
				new TypeToken<List<MemberCall>>() {
				}.getType());
		for (MemberCall mcall : memberCallList) {
			System.out.println("callTime: " + mcall.getCallTime());
			System.out.println("UpdateTime:" + mcall.getUpdateTime());

			// ///FFFFFFFFFFFFFFFFFFFF
		}
	}

	/**
     * 测试方法 
     * 测试测试测试测试测试测试测试测试测试测试测试测试测试
     */
	public static void test(String[] args) {

	}
}
