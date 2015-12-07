package com.cdel.util;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import com.cdel.advc.member.domain.Member;
import com.cdel.advc.studentCourse.domain.BuyCourse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

	private static Gson getGson() {
		return new GsonBuilder().create();
	}

	/**
	 * 将json字符串转为相应对象
	 * 
	 * @param jsonStr
	 * @param clazz
	 */
	@SuppressWarnings("unchecked")
	public static <T> T jsonStr2Object(String jsonStr, Type type) {
		return (T) (StringUtils.isNotBlank(jsonStr) ? getGson().fromJson(
				jsonStr, type) : null);
	}

	public static Member parseMember(String jsonData) throws Exception {
		JSONObject json = JSONObject.fromObject(jsonData);
		Integer result = (Integer) json.get("code");
		if (result == 1) {
			if (!"null".equals(String.valueOf(json.get("userInfo")))) {
				JSONObject map = (JSONObject) json.get("userInfo");
				Member rmember = new Member();
				rmember.setUserID(StringUtil.object2Integer(map.get("Uid")));
				rmember.setRealName(StringUtil.nullToString(map.get("FullName")));
				rmember.setTelPhone(StringUtil.nullToString(map.get("Phone")));
				rmember.setGener("男".equals(StringUtil.nullToString(map
						.get("Sex"))) ? true : false);
				rmember.setAddress(StringUtil.nullToString(map.get("Province")));
				return rmember;
			} else {
				throw new RuntimeException("未获取学员信息");
			}
		} else if (result == -1) {
			throw new RuntimeException("用户名不存在");
		} else if (result == -2) {
			throw new RuntimeException("密钥不正确");
		} else if (result == -3) {
			throw new RuntimeException("时间过期");
		}
		return null;
	}

	public static List<BuyCourse> parseBuyCourse(String jsonData)
			throws Exception {
		JSONObject json = JSONObject.fromObject(jsonData);
		Integer result = (Integer) json.get("code");
		if (result == 1) {
			if (!"null".equals(String.valueOf(json.get("msg")))) {
				JSONArray jarr = (JSONArray) json.get("msg");
				BuyCourse bc = null;
				JSONObject obj = null;
				List<BuyCourse> list = new ArrayList<BuyCourse>();
				for (int i = 0, m = jarr.size(); i < m; i++) {
					bc = new BuyCourse();
					obj = jarr.getJSONObject(i);
					bc.setCourseCode(StringUtil.nullToString(obj
							.get("courseCode")));
					bc.setCourseName(StringUtil.nullToString(obj
							.get("courseName")));
					if (!StringUtil.nullToString(obj.get("AreaID")).equals("")) {
						bc.setAreaID(StringUtil.object2Integer(obj
								.get("AreaID")));
					}
					bc.setMajorID(StringUtil.nullToString(obj.get("majorID")));
					bc.setMajorName(StringUtil.nullToString(obj
							.get("majorName")));
					if (!StringUtil.nullToString(obj.get("datePay")).equals("")) {
						bc.setOpenTime(StringUtil.nullToString(
								obj.get("datePay")).split(" ")[0]);
					}
					bc.setCourseType(StringUtil.object2Integer(obj
							.get("courseType")));

					list.add(bc);
				}

				return list;
			} else {
				throw new RuntimeException("未获取报课信息");
			}
		} else if (result == -1) {
			throw new RuntimeException("用户名不存在");
		} else if (result == -2) {
			throw new RuntimeException("密钥不正确");
		} else if (result == -3) {
			throw new RuntimeException("时间过期");
		}
		return null;
	}

}
