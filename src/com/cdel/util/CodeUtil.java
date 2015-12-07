package com.cdel.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.commons.lang.StringUtils;

public class CodeUtil {
	
	private static DecimalFormat df = new DecimalFormat("0.00");
	
	public static String genStudentNo(Integer classID, Integer strategyID, String termYear,String termMonth, Short currCount){
		String studentNo =  termYear.substring(2)+termMonth;
		String styID = String.valueOf(strategyID);
		if(styID.length() > 1){
			studentNo = studentNo + styID.substring(0, 2);
		}else{
			studentNo = studentNo + "0" + styID;
		}
		String strClassID = String.valueOf(classID);
		if(strClassID.length()>2){
			studentNo = studentNo + strClassID.substring(0,3);
		}else if (strClassID.length() == 2) {
			studentNo = studentNo + "0" + strClassID.substring(0,2);
		}else{
			studentNo = studentNo + "00" + strClassID;
		}
		int curr = currCount+1;
		if(curr > 10){
			studentNo = studentNo + (curr);
		}else{
			studentNo = studentNo + "0" + (curr);
		}
		
		return studentNo;
	}
	
	public static String genStudentNewNo(String classCode, Short currCount){
		String studentNo =  classCode;
		int curr = currCount+1;
		if(curr > 10){
			studentNo = studentNo + (curr);
		}else{
			studentNo = studentNo + "0" + (curr);
		}
		
		return studentNo;
	}
	
	public static String genClassCode(String termYear, String termMonth, Integer strategyID, Short currSeq){
		String classCode =  termYear.substring(2)+termMonth;
		String styID = String.valueOf(strategyID);
		if(styID.length() > 3){
			classCode = classCode + styID.substring(0, 4);
		}else if (styID.length() == 3) {
			classCode = classCode + "0" + styID.substring(0, 3);
		}else if (styID.length() == 2) {
			classCode = classCode + "00" + styID.substring(0, 2);
		}else{
			classCode = classCode + "000" + styID;
		}
		if(currSeq > 100){
			classCode = classCode + currSeq;
		}else if(currSeq > 10){
			classCode = classCode + "0" +currSeq;
		}else{
			classCode = classCode + "00" +currSeq;
		}
		return classCode;
	}
	
	/**
	 * 
	 * 分钟数转为小时数
	 * 
	 * @param minutes
	 * @return
	 */
	public static String minute2Hour(Short minutes){
		
		if (minutes == null || minutes == 0) {
			return "";
		}else {
			
			return df.format(minutes.floatValue() / 60f);
		}
		
	}
	
	/**
	 * 
	 * 分钟数转为小时数
	 * 
	 * @param minutes
	 * @return
	 */
	public static double minute2HourNumber(Short minutes){
		
		if (minutes == null || minutes == 0) {
			return 0;
		}else {
			return new BigDecimal(minutes).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()/60;
		}
		
	}
	
	
	/**
	 * 对学员反馈 周次学习时间解析
	 * 
	 * @param studyTimeLong
	 * @return
	 */
	public static String[] splitStudyTimes(String studyTimeLong){
		
		String[] r = new  String[8];

		if (StringUtils.isNotBlank(studyTimeLong)) {
		   String[] temp = studyTimeLong.split("\\u007C");
		   float[] t = new float[8];
		   if (temp != null && temp.length >0) {
			   t[0] = Float.parseFloat(temp[6]);  
			   t[1] = Float.parseFloat(temp[0]);  
			   t[2] = Float.parseFloat(temp[1]);  
			   t[3] = Float.parseFloat(temp[2]);  
			   t[4] = Float.parseFloat(temp[3]);  
			   t[5] = Float.parseFloat(temp[4]);  
			   t[6] = Float.parseFloat(temp[5]);  
			   t[7] = (t[0] + t[1]+ t[2]+ t[3]+ t[4]+ t[4]+ t[5]+ t[6]);
		   }
		   
		   for (int i = 0, l = t.length; i < l; i++) {
			   if (t[i] == 0) {
				   r[i] = "";
			   }else {
				   r[i] = t[i] + "";
			   }
		   }
		}
	   
	   return r;
	}
	
}
