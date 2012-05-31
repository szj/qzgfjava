package com.qzgf.application.appsystem.user.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("serial")
public class UserInfo implements Serializable {

	 public HashMap currentUser=new HashMap();
	 
	 public String clientIP="";
	 
	 
	 
	 
	 
	 
	 //==========================================常量定义==================================================

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
