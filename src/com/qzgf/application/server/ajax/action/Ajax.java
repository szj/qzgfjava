/**
* Copyright (C) qzgf, 2012
*
* License        :Apache License 2.0
* Project        :qzgfjava
* Package        :com.qzgf.application.server.ajax.action
* File	         :ajax.java
* Written by     :fjfdszj
* Created Date   :May 17, 2012
* Purpose        :功能描述

======================================

* Modifyer by    :fjfdszj
* Update Date    :May 17, 2012
* Purpose        :描述

*/
package com.qzgf.application.server.ajax.action;


import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Purpose      : 说明
 *
 * @author fjfdszj
 * @see     Ajax.java
 *
 */
public class Ajax {
	/**
	 * Purpose      : 处理ajax请求
	 */
	
	protected ApplicationContext appContext;
	
	public Object resultajax(String actionname,String method){
		//WebApplicationContextUtils.getWebApplicationContext(sc);
		//1
		Object result=null;
		Object beanajax=appContext.getBean(actionname);
		//2
		//ServletContext servletContext = WebContextFactory.get().getSession().getServletContext();
//        ApplicationContext ctx=WebApplicationContextUtils.getWebApplicationContext(servletContext); 
//        Object beanajax1=ctx.getBean("aduserInfoService");
        //3
        //SpringContextUtil.getBean("aduserInfoService");
        
        
		return result;
		
	}
	
}
