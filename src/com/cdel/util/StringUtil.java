package com.cdel.util;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

@SuppressWarnings(value = { "rawtypes", "unchecked" })
public class StringUtil {
	/**
	 * 过滤fck特殊字符
	 * 
	 * @param content
	 * @param replaceTo
	 * @return
	 */
	public static String changeContent(String content, String replaceTo) {
		if (content != null && content != "") {
			if (content.startsWith("<p>")) {
				content = content.substring(3);
			}
			if (content.endsWith("</p>")) {
				content = content.substring(0, content.length() - 4);
			}
			content = content.replaceAll("(\r|\n)*$", "");
			content = content.replaceAll("\r\n", replaceTo);
			content = content.replaceAll("\n", replaceTo);
		}
		return content;
	}

	/**
	 * 转换textarea
	 * 
	 * @param content
	 * @return
	 */
	public static String changeContent(String content) {
		if (content != null && content != "") {
			content = content.replaceAll("\r\n", "<br/>");
			content = content.replaceAll("\n", "<br/>");
		}
		return content;
	}

	/**
	 * 转换textarea
	 * 
	 * @param content
	 * @return
	 */
	public static String changeContent2(String content) {
		if (content != null && content != "") {
			content = content.replaceAll("<br/>", "\r\n");
		}
		return content;
	}

	public boolean hasRedundant(String[] strs) {
		if (null == strs || strs.length < 2)
			return false;
		boolean isRedundant = false;
		for (int i = strs.length - 1; i > -1; i--)
			for (int j = i - 1; j > -1; j--)
				if (null != strs[i] && strs[i].equals(strs[j])) {
					isRedundant = true;
					break;
				}
		return isRedundant;
	}

	public boolean isExisted(String string, String[] strs) {
		if (null == string || null == strs || strs.length < 1)
			return false;
		boolean isOverlapped = false;
		for (int i = 0; i < strs.length; i++)
			if (null != strs[i] && strs[i].equals(string)) {
				isOverlapped = true;
				break;
			}
		return isOverlapped;
	}

	public boolean isExisted(String string, String strSource, String regex) {
		if (null == string || null == strSource || null == regex
				|| strSource.length() < 1)
			return false;
		return isExisted(string, strSource.split(regex));
	}

	public String makeUnique(String string, String regex) {
		if (null == string)
			return null;
		if (null == regex)
			return string;
		if ("".equals(string.trim()) || "".equals(regex))
			return string;
		String result = "";
		String[] strs = string.split(regex);
		strs = removeNull(strs);
		int len = strs.length;
		boolean isRedundant[] = new boolean[len];
		for (int i = 0; i < len; i++)
			isRedundant[i] = false;
		for (int i = 0; i < len; i++) {
			if (isRedundant[i])
				continue;
			for (int j = i + 1; j < len; j++)
				if (!isRedundant[j] && null != strs[j]
						&& strs[j].equals(strs[i]))
					isRedundant[j] = true;
			if (!isRedundant[i])
				result += strs[i] + regex;
		}
		return result;
	}

	public String appendUnique(String str1, String str2, String regex) {
		if (null == str1 && null == str2)
			return null;
		if (null == str1 && null != str2)
			return makeUnique(str2, regex);
		if (null == str2 && null != str1)
			return makeUnique(str1, regex);
		if (null == regex)
			return str1 + str2;
		return makeUnique(str1 + str2, regex);
	}

	public String[] removeStrings(String[] strs1, String[] strs2) {
		if (null == strs1 || null == strs2 || strs1.length < 1
				|| strs2.length < 1)
			return strs1;
		int len1 = strs1.length;
		int len2 = strs2.length;
		boolean willBeRemoved;
		List remainList = new ArrayList();
		for (int i = 0; i < len1; i++) {
			willBeRemoved = false;
			for (int j = 0; j < len2; j++)
				if (null != strs2[j] && strs2[j].equals(strs1[i])) {
					willBeRemoved = true;
				}
			if (!willBeRemoved)
				remainList.add((String) strs1[i]);
		}
		return (String[]) remainList.toArray(new String[0]);
	}

	public String removeString(String str1, String str2, String regex) {
		if (null == str1 || null == str2 || "".equals(str2) || "".equals(str1)
				|| null == regex)
			return str1;
		String strs[] = removeStrings(str1.split(regex), str2.split(regex));
		String result = "";
		for (int i = 0; i < strs.length; i++)
			if (null != strs[i])
				result += strs[i] + regex;
		return result;
	}

	public String[] removeNull(String[] strs) {
		if (null == strs || strs.length < 1)
			return strs;
		List strList = new ArrayList();
		for (int i = 0; i < strs.length; i++) {
			if (null != strs[i] && !"".equals(strs[i].trim())) {
				strList.add((String) strs[i].trim());
			}
		}
		return (String[]) strList.toArray(new String[0]);
	}

	public static String getSetRedString(String str, List strKeyList) {
		if (str == null)
			return null;
		if (strKeyList == null || strKeyList.size() < 0)
			return str;
		String flag = "";
		for (int i = 0; i < strKeyList.size(); i++) {
			flag = (String) strKeyList.get(i);
			if (flag != null && !flag.equals("")) {
				if (str.indexOf(flag) >= 0) {
					str = str.replaceAll(flag, "<font color='red'>" + flag
							+ "</font>");
				}
			}
		}
		return str;
	}

	public static String getStr(String src, int length) {
		if (src == null)
			return null;
		int len = length - 3;
		if (src.getBytes().length <= len)
			return src;
		byte[] s = src.getBytes();
		int flag = 0;
		for (int i = 0; i < len; ++i) {
			if (s[i] < 0)
				flag++;
		}
		if (flag % 2 != 0)
			len--;
		byte[] d = new byte[len];
		System.arraycopy(s, 0, d, 0, len);
		String ss = new String(d) + "...";
		return ss;
	}

	public static String replaceStr(String str, String findSrc,
			String replaceSrc) {
		if (str == null) {
			return null;
		}
		int i = 0;
		if ((i = str.indexOf(findSrc, i)) >= 0) {
			char[] cSrc = str.toCharArray();
			char[] cTo = replaceSrc.toCharArray();
			int len = findSrc.length();
			StringBuffer buf = new StringBuffer(cSrc.length);
			buf.append(cSrc, 0, i).append(cTo);
			i += len;
			int j = i;
			while ((i = str.indexOf(findSrc, i)) > 0) {
				buf.append(cSrc, j, i - j).append(cTo);
				i += len;
				j = i;
			}
			buf.append(cSrc, j, cSrc.length - j);
			return buf.toString();
		}
		return str;
	}

	public static String stripHtml(String content) {
		content = content.replaceAll("&amp;", "&");
		content = content.replaceAll("&lt;", "<");
		content = content.replaceAll("&gt;", ">");
		content = content.replaceAll("&quot;", "");
		content = content.replaceAll("<p.*?>", " ");
		content = content.replaceAll("<brs*/?>", " ");
		content = content.replaceAll("&nbsp;", "");
		return content.trim();
	}

	/**
	 * @see 判断字符串是否为null 是的话转为 ""
	 * @param obj
	 * @return
	 */
	public static String nullToString(Object obj) {
		if (obj == null || StringUtils.isBlank(obj.toString())
				|| "null".equals(obj.toString())) {
			obj = "";
		}
		return String.valueOf(obj).trim();
	}

	/**
	 * @see 判断字符串是否为"" 是的话转为null
	 * @param obj
	 * @return
	 */
	public static String stringToNull(String str) {
		if (str != null && "".equals(str)) {
			str = null;
		}
		return str;
	}

	/**
	 * @see 判断字符串是否是数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if ("".equals(nullToString(str))) {
			return false;
		}
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * @see 字符串转换为数字 不是数字的置为0
	 * @param str
	 * @return
	 */
	public static int string2Integer(String str) {
		if (isNumeric(str)) {
			return Integer.parseInt(str);
		} else {
			return 0;
		}
	}

	public static int object2Integer(Object o) {
		String str = StringUtil.nullToString(o);
		try {
			if (str.equals("")) {
				return 0;
			}
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			System.out.println("异常：\"" + str + "\"不是数字/整数...");
			return 0;
		}
	}

	/**
	 * 字符串转换为布尔类型
	 * 
	 * @param paramString
	 * @return
	 */
	public static Boolean strToBool(String paramString) {
		if (paramString == null)
			return null;
		Boolean localBoolean = null;
		try {
			localBoolean = new Boolean(paramString);
		} catch (Exception localException) {
			localBoolean = null;
		}
		return localBoolean;
	}

	// 此函数过滤用，防止SQL注入
	public static String stringFilter(String str) {
		if (StringUtil.nullToString(str).equals("")) {
			return str;
		}
		String paraValue = StringUtil.nullToString(str).replaceAll("'", "’");
		paraValue = paraValue.replaceAll("--", "－－");
		return null;
	}
	

	public static String sqlDealStr(String str) {
		if (str == null) {
			return str;
		}
		str = str.replaceAll("'", "");
		str = str.replaceAll(" ", "");
		str = str.replaceAll("@", "");
		str = str.replaceAll("%", "");
		str = str.replaceAll("\\+", "");
		str = str.replaceAll(";", "");
		return str;
	}

	/**
	 * 把文本处理成html
	 * 
	 * @param s
	 * @return
	 */
	public static String htmLEncode(String s) {
		char[] htmlChars = s.toCharArray();
		StringBuffer encodedHtml = new StringBuffer();
		for (int i = 0; i < htmlChars.length; i++) {
			switch (htmlChars[i]) {
			case '<':
				encodedHtml.append("&lt;");
				break;
			case '>':
				encodedHtml.append("&gt;");
				break;
			case '&':
				encodedHtml.append("&amp;");
				break;
			case '\'':
				encodedHtml.append("&#39;");
				break;
			case '"':
				encodedHtml.append("&quot;");
				break;
			case '\\':
				encodedHtml.append("&#92;");
				break;
			// case '\r':
			// encodedHtml.append("");
			// break;
			// case '\n':
			// encodedHtml.append("<br>");
			// break;
			case (char) 133:
				encodedHtml.append("&#133;");
				break;
			default:
				encodedHtml.append(htmlChars[i]);
				break;
			}
		}
		return encodedHtml.toString();
	}

	/**
	 * 专门针对将多个ID以逗号相隔的字段的处理，直接转为ID数组
	 * 
	 * @param idsString
	 *            以逗号相隔的ID字段
	 * @return
	 */
	public static Integer[] splitIDs(String idsString) {

		Integer[] ids = null;
		if (StringUtils.isNotBlank(idsString)) {
			String[] tempIds = StringUtils.split(idsString, ",");
			int size = 0;
			if (tempIds != null && (size = tempIds.length) > 0) {
				ids = new Integer[size];
				for (int i = 0; i < size; i++) {
					ids[i] = Integer.parseInt(tempIds[i]);
				}
			}
		}
		return ids;
	}
	
	// 如果字符串以逗号开头  和  以逗号结尾都去掉 ,546,545,547,548,
	public static String filterStringDouhao(String str) {
		 StringBuilder sb = new StringBuilder();
		 if (StringUtils.isNotBlank(str)) {
			 String[] strArray = str.trim().split(",");
				for(String tmpStr : strArray) {
					if(tmpStr.length()>0)
						sb.append(tmpStr.trim()).append(",");
				}
				if(sb.toString().endsWith(",")){
					sb.deleteCharAt(sb.length()-1);
				}
				if(sb.toString().startsWith(",")){
					sb.deleteCharAt(0);
				}
		}
		 return sb.toString();
	}
	public static void main(String[] args) {
		// System.out.println(StringUtil.getPercentumString((0.07f)));
		String str = ",,546,,545,547,548,,,,  ";
		System.out.println(filterStringDouhao(str));
	}

	/**
	 * 得到数字的百分比显示值
	 * 
	 * @param number
	 * @return
	 */
	public static String getPercentumString(float number) {

		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMaximumFractionDigits(1);

		return nf.format(number);
	}

	/**
	 * 验证手机号码是否有效
	 * 
	 * @param telPhone
	 * @return
	 */
	public static boolean validateTelphone(String telPhone) {

		if (telPhone == null) {
			return false;
		}
		if (telPhone.length() != 11) {
			return false;
		}
		if (!telPhone.startsWith("13") && !telPhone.startsWith("15")
				&& !telPhone.startsWith("18")) {
			return false;
		}
		return true;
	}

	/**
	 * 判断数组是否包含，s1包含s2
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean arrContain(String[] s1, String[] s2) {
		boolean f = false;
		int i = 0;
		for (String s2s : s2) {
			if ((i == 0 && !f) || (i > 0 && f)) {
				for (String s1s : s1) {
					if (s2s.equals(s1s)) {
						f = true;
						break;
					}
				}
			} else {
				break;
			}
			i++;
		}
		return f;
	}

}
