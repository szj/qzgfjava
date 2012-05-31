/**
 * Copyright (C) qzgf, 2012
 *
 * License        :Apache License 2.0
 * Project        :qzgfjava
 * Package        :com.qzgf.application.appsystem.main.action
 * File	         :main.java
 * Written by     :fjfdszj
 * Created Date   :May 21, 2012
 * Purpose        :功能描述

======================================

 * Modifyer by    :fjfdszj
 * Update Date    :May 21, 2012
 * Purpose        :描述

 */
package com.qzgf.application.appsystem.main.action;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.qzgf.application.BaseAction;
import com.qzgf.application.appsystem.main.domain.MainFacade;
import com.qzgf.application.appsystem.user.model.UserInfo;
import com.qzgf.core.common.AjaxResult;
import com.qzgf.core.common.Constant;
import com.qzgf.core.common.ContextHelper;
import com.qzgf.core.common.Util;

/**
 * Purpose : 说明
 * 
 * @author fjfdszj
 * @see DepartmentAction.java
 * 
 */
public class MainAction extends BaseAction {
	/**
	 * Purpose : 说明
	 */

	private MainFacade mainFacade;

	private static Logger logger = Logger.getLogger(MainAction.class);

	/**
	 * 
	 * Purpose : 框架左菜单
	 * 
	 * @return
	 */
	public String GetTreeJson() {
		logger.info("框架树型菜单显示！");
		List menuList = mainFacade.findMenu("0");
		StringBuffer menustr = new StringBuffer();
		menustr.append("[");
		menustr.append(mainFacade.GetTree(menuList, "0"));
		menustr.append("]");
		json = menustr.toString();
		return viewjson;
	}

	/**
	 * 
	 * Purpose : 系统按钮
	 * 
	 * @return
	 */
	public String GetButtonJson() {
		List buttonList = mainFacade.findSysButton("0");
		AjaxResult ar = AjaxResult.Success(buttonList);
		json = ar.toString();
		return viewjson;
	}

	/**
	 * 
	 * Purpose : 用户信息验证
	 * 
	 * @return
	 */
	public String Login() {
		boolean result = false;
		// 1.验证码
		String authcode = ActionContext.getContext().getSession().get(
				Constant.VERIFY_SESSION_NAME).toString();
		if (authcode.equals(search.get("authcode").toString())) {
			// 2.取得用户信息
			HashMap hs = mainFacade.findUserbyName(search.get("username")
					.toString());
			UserInfo ui = new UserInfo();
			// 3.密码
			if (hs != null) {
				String md5pwd = Util.hash(search.get("password").toString());
				if ((md5pwd).equalsIgnoreCase(hs.get("password").toString())) {
					// 4.存储用户到session
					ui.setCurrentUser(hs);
					//取得用户的ip信息
					HttpServletRequest request = ServletActionContext.getRequest();
					String clientip=Util.getIpAddr(request);
					ui.setClientIP(clientip);
					result = true;
				}
			}
			ActionContext.getContext().getSession().put(
					Constant.USER_SESSION_KEY, ui);
		}

		java.lang.StringBuffer sb = new StringBuffer();
		sb.append(result);
		json = sb.toString();
		return viewjson;
	}

	/**
	 * 
	 * Purpose : 切换用户则不需要验证码
	 * 
	 * @return
	 */
	public String ReLogin() {
		boolean result = false;
		// 2.取得用户信息
		HashMap hs = mainFacade.findUserbyName(search.get("username").toString());
		UserInfo ui = new UserInfo();
		// 3.密码
		if (hs.size()>0) {
			String md5pwd = Util.hash(search.get("password").toString());
			if ((md5pwd).equalsIgnoreCase(hs.get("password").toString())) {
				// 4.存储用户到session
				ui.setCurrentUser(hs);

				result = true;
			}
		}
		ActionContext.getContext().getSession().put(Constant.USER_SESSION_KEY,ui);
		java.lang.StringBuffer sb = new StringBuffer();
		sb.append(result);
		json = sb.toString();
		return viewjson;
	}

	// ========LG.Ajax专用(为传递参数方便，必须加HashMap(search)=============
	/**
	 * 
	 * Purpose :修改密码
	 * 
	 * @param search
	 * @return
	 */
	public String ChangePassword(HashMap search) {
		String oldpwd = Util.hash(search.get("oldpwd").toString());
		String newpwd = Util.hash(search.get("newpwd").toString());
		UserInfo ui = super.getUserInfo();
		HashMap userhs = ui.currentUser;
		AjaxResult ar = AjaxResult.Success("1");
		if (oldpwd.equalsIgnoreCase(userhs.get("password").toString())) {
			search.clear();
			search.put("ppassword", newpwd);
			search.put("pid", userhs.get("id").toString());
			int faviorite = mainFacade.updateUserPwd(search);
			ar = AjaxResult.Success(faviorite);
		} else {
			ar = AjaxResult.Error("原密码不匹配！");
		}
		json = ar.toString();
		return json;
	}

	/**
	 * 
	 * Purpose :从session中取得用户信息
	 * 
	 * @param search
	 * @return
	 */
	public String GetCurrentUser(HashMap search) {
		UserInfo ui = super.getUserInfo();
		AjaxResult ar = AjaxResult.Success(ui.getCurrentUser());
		json = ar.toString();
		return json;
	}

	/**
	 * 
	 * Purpose : 取得用户收藏夹
	 * 
	 * @param search
	 * @return
	 */
	public String GetMyFavorite(HashMap search) {
		UserInfo ui = super.getUserInfo();
		HashMap hs = ui.getCurrentUser();
		List faviorite = mainFacade.findFavorite(hs.get("id").toString());
		AjaxResult ar = AjaxResult.Success(faviorite);
		json = ar.toString();
		return json;
	}

	/**
	 * 
	 * Purpose :删除用户收藏夹
	 * 
	 * @param search
	 * @return
	 */
	public String RemoveMyFavorite(HashMap search) {
		int faviorite = mainFacade.deleteFavorite(search);
		AjaxResult ar = AjaxResult.Success(faviorite);
		json = ar.toString();
		return json;
	}

	/**
	 * 
	 * Purpose : 增加收藏夹
	 * 
	 * @param search
	 * @return
	 */
	public String AddMyFavorite(HashMap search) {
		// 用户信息
		UserInfo ui = (UserInfo) ActionContext.getContext().getSession().get(
				Constant.USER_SESSION_KEY);
		HashMap userhs = ui.getCurrentUser();

		HashMap se = new HashMap();
		se.put("pid", search.get("pmenuid").toString());
		HashMap hs = mainFacade.findMenuMsg(se);
		se.clear();
		// 组装收藏夹信息
		se.put("puserid", userhs.get("id").toString());
		se.put("pmenuid", hs.get("id").toString());
		se.put("pmenuname", hs.get("name").toString());
		se.put("premark", search.get("premark").toString());
		se.put("purl", hs.get("url").toString());
		se.put("picon", hs.get("icon").toString());
		int faviorite = mainFacade.insertFavorite(se);
		AjaxResult ar = AjaxResult.Success(faviorite);
		json = ar.toString();
		return json;
	}

	// ============================页面层信息======================================================
	/**
	 * 
	 * Purpose : 框架页面
	 * 
	 * @return
	 */
	public String Index() {
		return "viewmain";
	}

	/**
	 * 
	 * Purpose : 登录页面
	 * 
	 * @return
	 */
	public String Viewlogin() {
		return "viewlogin";
	}

	// ====================================核心Ajax处理====================================================
	public String Ajax() {
		String type = search.get("type").toString();
		String method = search.get("method").toString();
		// 回调的参数数据类型
		try {
			// Class[] parmclass = null;
			Object clazz = ContextHelper.getBean(type);
			// 回调的方法
			Method promethod = clazz.getClass()
					.getMethod(method, HashMap.class);
			// 数据参数
			// Object[] parmval = null;
			// 返回结果集
			json = (String) promethod.invoke(clazz, search);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return viewjson;
	}

	// =======================================================常量设置=================================
	// =================================================================================================
	/**
	 * Purpose : 说明
	 * 
	 * @return the mainFacade
	 */
	public MainFacade getMainFacade() {
		return mainFacade;
	}

	/**
	 * Purpose : 说明
	 * 
	 * @param mainFacade
	 *            the mainFacade to set
	 */

	public void setMainFacade(MainFacade mainFacade) {
		this.mainFacade = mainFacade;
	}
}
