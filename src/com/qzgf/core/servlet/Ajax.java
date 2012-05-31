/**
 * Copyright (C) qzgf, 2012
 *
 * License        :Apache License 2.0
 * Project        :qzgfjava
 * Package        :com.qzgf.core.servlet
 * File	         :Ajax.java
 * Written by     :fjfdszj
 * Created Date   :May 25, 2012
 * Purpose        :功能描述

======================================

 * Modifyer by    :fjfdszj
 * Update Date    :May 25, 2012
 * Purpose        :描述

 */
package com.qzgf.core.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.OgnlValueStack;

/**
 * Purpose : 说明
 * 
 * @author fjfdszj
 * @see Ajax.java
 * 
 */
public class Ajax extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		String type=req.getParameter("type").toString();
		String method=req.getParameter("method").toString();
		String result="";//返回结果集
		try {
			//回调的参数数据类型
			Class[] parmclass =null;
			//parmclass[0]=OgnlValueStack.class;//HashMap.class
			//从spring池中取出的bean对象
			Object clazz=ctx.getBean(type); 
			//回调的方法
			Method promethod = clazz.getClass().getMethod(method, parmclass);
			//数据参数
			Object[] parmval = null;
			//final OgnlValueStack stack = (OgnlValueStack) ActionContext.getContext().getValueStack();
			//返回结果集
			result = (String) promethod.invoke(clazz, parmval);
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter out = res.getWriter();
		out.println(result);
	}

	// Process the HTTP Post request
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	// Clean up resources
	public void destroy() {
	}

	
}
