package com.cdel.advc.studentCourse.facade;

import java.io.Serializable;
import java.net.URLEncoder;
import java.util.List;
import org.springframework.stereotype.Service;
import com.cdel.advc.studentCourse.domain.BuyCourse;
import com.cdel.advc.studentCourse.domain.StudentCourse;
import com.cdel.advc.website.domain.Website;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.cdel.util.MD5;
import com.cdel.util.RemoteUtil;
import com.cdel.util.StringUtil;
import com.cdel.util.XMLUtil;

@SuppressWarnings("serial")
@Service
public class StudentCourseFacade extends BaseFacadeImpl<StudentCourse, Integer>
		implements Serializable {

	public List<BuyCourse> studentBuyCourse(StudentCourse searchStudentCourse)
			throws Exception {
		List<BuyCourse> result = null;
		try {
			Integer siteType = searchStudentCourse.getSiteType();
			String username = StringUtil.nullToString(searchStudentCourse
					.getUserName());
			if (siteType != null) {
				String resultData = getBuyCourseFromRemote(username, siteType);
				result = XMLUtil.parseBuyCourse(resultData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 从asp端获取学员报课信息
	 * 
	 * @param userName
	 * @param website
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	public String getBuyCourseFromRemote(String userName, Website website)
			throws Exception {
		String keyTime = DateUtil.getNowToString("yyyy-MM-dd HH:mm:ss");
		String aspServer = website.getSelCourseUrl();

		String key = MD5.Md5(
				URLEncoder.encode(userName, "gb2312").replaceAll("_", "%5F")
						+ keyTime + website.getDecodeSiteAttr(), 16);
		String sendData = "?UserName=" + userName + "&KeyTime="
				+ URLEncoder.encode(keyTime, Contants.code) + "&Key=" + key;
		return RemoteUtil.getRemoteString(aspServer, sendData, "GBK")
				.replaceAll("&nbsp;", "");
	}

	/**
	 * 从接口获取学员报课信息
	 * 
	 * @param userName
	 * @param website
	 * @return
	 * @throws Exception
	 */
	public String getBuyCourseFromRemote(String userName, Website website,
			Integer userID) throws Exception {
		String keyTime = DateUtil.getNowToString("yyyy-MM-dd HH:mm:ss");
		String aspServer = website.getSelCourseUrl();

		String sendData = null;
		if (userID != null && userID > 0) {
			String key = MD5.Md5(userID + keyTime + website.getSiteAttr(), 16);
			sendData = "?userID=" + userID + "&keyTime="
					+ URLEncoder.encode(keyTime, Contants.code) + "&key=" + key;
		} else {
			String key = MD5
					.Md5(userName + keyTime + website.getSiteAttr(), 16);
			sendData = "?userName=" + userName + "&keyTime="
					+ URLEncoder.encode(keyTime, Contants.code) + "&key=" + key;
		}

		return RemoteUtil.getRemoteString(aspServer, sendData, Contants.code)
				.replaceAll("&nbsp;", "");
	}

	/**
	 * 获得学员的选课记录
	 * 
	 * @param username
	 *            用户登录名
	 * @param site
	 *            网站顺序(会计网-医学网-人事网-外语-中小学-建设-法律)
	 * @return
	 * @throws Exception
	 */
	public String getBuyCourseFromRemote(String userName, int site)
			throws Exception {
		String keyTime = DateUtil.getNowToString("yyyy-MM-dd HH:mm:ss");
		String key = MD5.Md5(
				URLEncoder.encode(userName, "gb2312").replaceAll("_", "%5F")
						+ keyTime + Contants.keys[site], 16);
		String sendData = "?UserName=" + userName + "&KeyTime="
				+ URLEncoder.encode(keyTime, Contants.code) + "&Key=" + key;
		return RemoteUtil.getRemoteString(Contants.aspServers[site], sendData,
				"GBK");
	}

}
