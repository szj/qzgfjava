package com.qzgf.application.appsystem.user.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Purpose      : 说明
 *
 * @author fjfdszj
 * @see     UserInfo.java
 *
 */
@SuppressWarnings("serial")
public class UserInfo implements Serializable {

	 //用户基本信息
	 public HashMap currentUser=new HashMap();
	 //用户的ip信息在action页面获取
	 public String clientIP="";
	 //管理菜单及按钮
	 public List menuPermission=new ArrayList();
	 //管理字段的是否显示
	 public List menufieldPermission=new ArrayList();
	 
	 
	 //==========================================常量定义==================================================

	/**
	 * Purpose      : 说明
	 * @return the menuPermission
	 */
	public List getMenuPermission() {
		return menuPermission;
	}

	/**
	 * Purpose      : 说明
	 * @param menuPermission the menuPermission to set
	 */
	
	public void setMenuPermission(List menuPermission) {
		this.menuPermission = menuPermission;
	}

	/**
	 * Purpose      : 说明
	 * @return the menufieldPermission
	 */
	public List getMenufieldPermission() {
		return menufieldPermission;
	}

	/**
	 * Purpose      : 说明
	 * @param menufieldPermission the menufieldPermission to set
	 */
	
	public void setMenufieldPermission(List menufieldPermission) {
		this.menufieldPermission = menufieldPermission;
	}

	/**
	 * Purpose      : 说明
	 * @return the currentUser
	 */
	public HashMap getCurrentUser() {
		return currentUser;
	}

	/**
	 * Purpose      : 说明
	 * @param currentUser the currentUser to set
	 */
	
	public void setCurrentUser(HashMap currentUser) {
		this.currentUser = currentUser;
	}

	/**
	 * Purpose      : 说明
	 * @return the clientIP
	 */
	public String getClientIP() {
		return clientIP;
	}

	/**
	 * Purpose      : 说明
	 * @param clientIP the clientIP to set
	 */
	
	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}
	
	
}
