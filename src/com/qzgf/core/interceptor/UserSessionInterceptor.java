package com.qzgf.core.interceptor;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.views.util.UrlHelper;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.qzgf.application.BaseAction;
import com.qzgf.application.appsystem.user.model.UserInfo;
import com.qzgf.core.common.Constant;
import com.qzgf.core.common.Util;

/**
 * 
 * Purpose      : 用户session拦截器
 *
 * @author fjfdszj
 * @see     UserSessionInterceptor.java
 *
 */
@SuppressWarnings("serial")
public class UserSessionInterceptor extends MethodFilterInterceptor {

	private static Logger logger = Logger.getLogger(UserSessionInterceptor.class);
 
	/**
	 * Purpose      : 说明
	 * @param arg0
	 * @return
	 * @throws Exception 
	 */
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		ActionContext ac = invocation.getInvocationContext();
		Object action = invocation.getAction();
		if (action instanceof BaseAction) {

			ServletContext servletContext = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
			WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(servletContext);

			if (wc == null) {
				logger.error("用户session拦截器，WebApplicationContext为空！");
			} else {
				UserInfo ui = (UserInfo) ac.getSession().get(Constant.USER_SESSION_KEY);
				Map map = ac.getParameters(); //获得参数值,这边就是URL后面带的那个:action=index
				if(ui==null){
					//已过期,跳转到前台
					HttpServletRequest request = (HttpServletRequest) ac.get(ServletActionContext.HTTP_REQUEST);
					StringBuffer sb = new StringBuffer();
					sb.append(Util.getWebRealPath(request));
					sb.append(request.getContextPath());
					sb.append("/");
					sb.append(Util.getActionMappingURLWithoutPrefix(ac.getName()));
					UrlHelper.buildParametersString(map, sb, "&");
					ac.getValueStack().set("interceptError", "对不起,Session已过期,请重新登录");
					return "intercepthmsg";
				}
				
				((BaseAction) action).setUserInfo(ui);
			}
		}
		return invocation.invoke();
	}

}
