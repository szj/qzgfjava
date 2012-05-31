package com.qzgf.core.common;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;


public class Util {

	private static MessageDigest digest = null;
	
	/**
	 * MD5加密
	 */
	public synchronized static final String hash(String data) {
		if (digest == null) {
			try {
				digest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException nsae) {
				System.err
						.println("Failed to load the MD5 MessageDigest. " + "We will be unable to function normally.");
				nsae.printStackTrace();
			}
		}
		digest.update(data.getBytes());
		return encodeHex(digest.digest());
	}
	
	public static final String encodeHex(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		int i;

		for (i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString().toUpperCase();
	}
	
	
	/**
	 *  null转化为string
	 *  摘自厦门忠诚度系统的公共类
	 * @param Object
	 * @return
	 */
	public static String getNulltoStr(Object o) {
		String str = "";
		try{
			if (o != null)
				str = o.toString();
		}catch(Exception e){
			return "";
		}
		return str;
	}
	/**
	 *  判断是否不为空
	 * @param boolean
	 * @return
	 */
	public static boolean bIsEmpty(String str) {
		boolean bisNull = false;
		if (str == null || str == "" || str.trim().equals(""))
			bisNull = true;
		return bisNull;
	}
	/**
	 * 
	 * Purpose      :web路径,存放excel数据.
	 * @param userID
	 * @return
	 */
	public static String getUserWebFilePath(String userID) {
		StringBuffer sb = new StringBuffer();
		int num = Math.abs(userID.hashCode());
		sb.append(Constant.ROOTPATH);
		sb.append("excel/");
		sb.append(num % 100);
		sb.append("/");
		sb.append(userID);
		sb.append("/");
		sb=new StringBuffer(sb.toString());
		File ft = new File(sb.toString());
		if (!ft.exists()) {
			ft.mkdirs();
		}
		return sb.toString();
	}
	
	/**
	 * 返回:http://localhost:serverPort
	 * @param request
	 * @return
	 */
	public static String getWebRealPath(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		sb.append("http://");
		sb.append(request.getServerName());
		if (request.getServerPort() != 80) {
			sb.append(":");
			sb.append(request.getServerPort());
		}
		return sb.toString();
	}
	
	/**
	 * 转换URL"/selfconfig/album?action=add"-->"/webframe/selfconfig/album.do?action=add"
	 * @param action
	 * @param request
	 * @return
	 */
	public static String getActionMappingURL(String action, HttpServletRequest request) {

		StringBuffer value = new StringBuffer(request.getContextPath());

		String servletMapping = Constant.SERVLET_MAPPING;
		if (servletMapping != null) {
			String queryString = null;
			int question = action.indexOf("?");
			if (question >= 0) {
				queryString = action.substring(question);
			}
			String actionMapping = getActionMappingName(action);
			if (servletMapping.startsWith("*.")) {
				value.append(actionMapping);
				value.append(servletMapping.substring(1));
			} else if (servletMapping.endsWith("/*")) {
				value.append(servletMapping.substring(0, servletMapping.length() - 2));
				value.append(actionMapping);
			} else if (servletMapping.equals("/")) {
				value.append(actionMapping);
			}
			if (queryString != null) {
				value.append(queryString);
			}
		}else {
			if (!action.startsWith("/")) {
				value.append("/");
			}
			value.append(action);
		}

		return (value.toString());
	}

	public static String getActionMappingName(String action) {
		String value = action;
		int question = action.indexOf("?");
		if (question >= 0) {
			value = value.substring(0, question);
		}
		int slash = value.lastIndexOf("/");
		int period = value.lastIndexOf(".");
		if ((period >= 0) && (period > slash)) {
			value = value.substring(0, period);
		}
		if (value.startsWith("/")) {
			System.out.println("value:"+value);
			return (value);
		} else {
			System.out.println("/Value:"+"/"+value);
			return ("/" + value);
		}
	}
	
	/**
	 * return: *.do
	 * @param action
	 * @return
	 */
	public static String getActionMappingURLWithoutPrefix(String action) {

		StringBuffer value = new StringBuffer();

		String servletMapping = Constant.SERVLET_MAPPING;
		if (servletMapping != null) {

			String queryString = null;
			int question = action.indexOf("?");
			if (question >= 0) {
				queryString = action.substring(question);
			}
			String actionMapping = getActionMappingNameWithoutPrefix(action);
			if (servletMapping.startsWith("*.")) {
				value.append(actionMapping);
				value.append(servletMapping.substring(1));
			} else if (servletMapping.endsWith("/*")) {
				value.append(servletMapping.substring(0, servletMapping.length() - 2));
				value.append(actionMapping);
			} else if (servletMapping.equals("/")) {
				value.append(actionMapping);
			}
			if (queryString != null) {
				value.append(queryString);
			}
		}
		return (value.toString());
	}
	
	public static String getActionMappingNameWithoutPrefix(String action) {
		String value = action;
		int question = action.indexOf("?");
		if (question >= 0) {
			value = value.substring(0, question);
		}
		int slash = value.lastIndexOf("/");
		int period = value.lastIndexOf(".");
		if ((period >= 0) && (period > slash)) {
			value = value.substring(0, period);
		}
		return (value);
	}
	
	/**
	 * 将一个字符串转换成日期格式
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date toDate(String date, String pattern) {
		if((""+date).equals("")){
			return null;
		}
		if(pattern == null){
			pattern = "yyyy-MM-dd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date newDate = new Date();
		try {
			newDate = sdf.parse(date);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return newDate;
	}
	
	/**
	 * 把日期转换成字符串型
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String toString(Date date, String pattern){
		if(date == null){
			return "";
		}
		if(pattern == null){
			pattern = "yyyy-MM-dd";;
		}
		String dateString = "";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			dateString = sdf.format(date);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dateString;
	}
	
	
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		//request.getRemoteAddr();
		return ip;
	} 


	
}
