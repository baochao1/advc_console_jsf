package com.cdel.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import com.cdel.advc.studentCourse.domain.BuyCourse;

public class XMLUtil {

	@SuppressWarnings("rawtypes")
	public static List<BuyCourse> parseBuyCourse(String XMLData)
			throws Exception {
		List<BuyCourse> bcList = new LinkedList<BuyCourse>();
		XMLData = XMLData.replaceAll("&nbsp;", "");
		Document document = DocumentHelper.parseText(XMLData);
		Element root = document.getRootElement();
		Element selectCourse = root.element("selectcourse");
		List list = selectCourse.elements("course");
		for (Iterator it = list.iterator(); it.hasNext();) {
			Element e = (Element) it.next();
			BuyCourse bc = new BuyCourse();
			for (Iterator itr = e.elementIterator(); itr.hasNext();) {
				Element element = (Element) itr.next();
				String name = element.getName();
				String value = element.getStringValue();
				if ("courseID".equalsIgnoreCase(name)) {
					bc.setCourseCode(value);
				} else if ("courseName".equalsIgnoreCase(name)) {
					bc.setCourseName(value);
				} else if ("catalog".equalsIgnoreCase(name)) {
					bc.setMajorName(value);
				} else if ("eduType".equalsIgnoreCase(name)) {
					bc.setMajorID(value);
				} else if ("datePay".equalsIgnoreCase(name)) {
					bc.setOpenTime(value.split(" ")[0]);
				} else if ("courseType".equalsIgnoreCase(name)) {
					bc.setCourseType(Integer.parseInt(value));
				} else if ("areaID".equalsIgnoreCase(name)
						|| "AreaID".equalsIgnoreCase(name)) {
					if (value != null && !"".equals(value)) {
						bc.setAreaID(Integer.parseInt(value));
					}
				}
			}
			bcList.add(bc);
		}
		return bcList;
	}

	public static String parseMemberPhone(String XMLData) throws Exception {
		Document document = DocumentHelper.parseText(XMLData);
		Element root = document.getRootElement();
		String returnValue = root.element("httpStatus").getStringValue();
		int value = Integer.parseInt(returnValue);
		String[] arrMsg = { "学员代码不存在", "密钥不正确", "密钥时间过期", "信息不全" };
		if (value != 1) {
			throw new RuntimeException(arrMsg[value + 4]);
		}
		String phone = root.element("Mobile").getStringValue();
		return phone;
	}

	public static String getRootText(String XMLData, String rootName)
			throws Exception {
		Document document = DocumentHelper.parseText(XMLData);
		Element root = document.getRootElement();
		return root.elementTextTrim(rootName);
	}

}
