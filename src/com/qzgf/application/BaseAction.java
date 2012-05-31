/**
* Copyright (C) qzgf, 2012
*
* License        :Apache License 2.0
* Project        :qzgfjava
* Package        :com.qzgf.application.BaseAction;
* File	         :BaseAction
* Written by     :fjfdszj
* Created Date   :May 21, 2012
* Purpose        :用户session基类

======================================

* Modifyer by    :fjfdszj
* Update Date    :May 21, 2012
* Purpose        :描述

*/


package com.qzgf.application;

import java.lang.reflect.Method;
import java.util.HashMap;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.qzgf.application.appsystem.user.model.UserInfo;
import com.qzgf.core.common.Constant;
import com.qzgf.core.filter.FilterTranslator;

@SuppressWarnings("serial")
public class BaseAction extends ActionSupport {

	private String action = "Index";//默认action值
	
	public String viewjson = "json";//针对action的返回值为json时的处理
	public String json = "";

	//用户信息
	public UserInfo userInfo;
	
	//grid页面参数传递
	public int page=1;
	public int pagesize=10;
	public String sortname="";
	public String sortorder="";
	//grid查询页面
	public String where="";
	
	@SuppressWarnings("unchecked")
	public HashMap search = new HashMap();
	
	/**
	 * 默认构造函数
	 */
	public BaseAction(){
		
	}
	
	public String Grid(){
		//页面设置
		int startrow=pagesize*(page-1);
		search.put("START", startrow);
		search.put("END", pagesize);
		//排序
		String orderby=sortname+" "+sortorder;
		search.put("ORDERBY", orderby);
		
		if(!where.equals("")){
			//高级查询
			FilterTranslator ft= new FilterTranslator();
			ft.Translate(where); 
			String searchwhere=ft.CommandText;//sortname+" "+sortorder
			searchwhere=searchwhere.replaceAll("search.p", "");
			search.put("WHERE", searchwhere);
		}
		
		return "";
	}
    /**
     * 
     * Purpose      :反射执行相关action方法
     * @param method
     * @return
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	protected String execute(String method) throws Exception {
		Class[] c = null;
		Method m = this.getClass().getMethod(method, c);
		Object[] o = null;
		String result = (String) m.invoke(this, o);
		return result;
	}
	
	//================================================================================	
	//================================================================================
	//==============================基本参数设置======================================
	//================================================================================
	//================================================================================
	/**
	 * Purpose      : 说明
	 * @return the json
	 */
	public String getJson() {
		return json;
	}


	/**
	 * Purpose      : 说明
	 * @param json the json to set
	 */
	
	public void setJson(String json) {
		this.json = json;
	}



	@SuppressWarnings("unchecked")
	public HashMap getSearch() {
		return search;
	}

	@SuppressWarnings("unchecked")
	public void setSearch(HashMap search) {
		this.search = search;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}



	/**
	 * Purpose      : 说明
	 * @return the sortname
	 */
	public String getSortname() {
		return sortname;
	}

	/**
	 * Purpose      : 说明
	 * @param sortname the sortname to set
	 */
	
	public void setSortname(String sortname) {
		this.sortname = sortname;
	}

	/**
	 * Purpose      : 说明
	 * @return the sortorder
	 */
	public String getSortorder() {
		return sortorder;
	}

	/**
	 * Purpose      : 说明
	 * @param sortorder the sortorder to set
	 */
	
	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}

	/**
	 * Purpose      : 说明
	 * @return the where
	 */
	public String getWhere() {
		return where;
	}

	/**
	 * Purpose      : 说明
	 * @param where the where to set
	 */
	
	public void setWhere(String where) {
		this.where = where;
	}

	/**
	 * Purpose      : 说明
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * Purpose      : 说明
	 * @param page the page to set
	 */
	
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * Purpose      : 说明
	 * @return the pagesize
	 */
	public int getPagesize() {
		return pagesize;
	}

	/**
	 * Purpose      : 说明
	 * @param pagesize the pagesize to set
	 */
	
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	/**
	 * Purpose      : 说明
	 * @return the userInfo
	 */
	public UserInfo getUserInfo() {
		UserInfo curuserInfo=this.userInfo;
		//session中存在改用户信息则读取
		if(ActionContext.getContext().getSession().containsKey(Constant.USER_SESSION_KEY)){
			 curuserInfo=(UserInfo)ActionContext.getContext().getSession().get(Constant.USER_SESSION_KEY);
		}
		return curuserInfo;
	}

	/**
	 * Purpose      : 说明
	 * @param userInfo the userInfo to set
	 */
	
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
}
