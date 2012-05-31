package com.qzgf.core.interceptor;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.views.util.UrlHelper;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.qzgf.application.appsystem.user.model.UserInfo;
import com.qzgf.core.common.Constant;
import com.qzgf.core.common.Util; 

/**
 * 判断是否有该权限
 * @author lsr
 *
 */
@SuppressWarnings("serial")
public class PermissionInterceptor extends AbstractInterceptor {

	private static final Log logger = LogFactory.getLog(PermissionInterceptor.class);

	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		/**
		 * ActionInvocation 对象可以用来访问运行时环境，以及Action本身；上下文（包括了Web应用的请求参数，session参数，用户Local等等）；
		 * Action的执行结果；还有那些调用Action的方法并判断Action是否已被调用
		 */
		ActionContext ac = invocation.getInvocationContext();

		String actionName = "/" + ac.getName(); //ac.getName:获得action名,即main.do的"main"

		String saction = "";

		
		Map map = ac.getParameters(); //获得参数值,这边就是URL后面带的那个:action=index

		String[] _saction = (String[]) map.get("action");//获得action参数值
		if (_saction != null) {
			saction = _saction[0];
		}

		boolean havePermission = false;

		ServletContext servletContext = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT); //获得配置环境
		WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(servletContext);//ApplicationContent.xml

		if (wc == null) {
			logger.error("ApplicationContext could not be found.");
			return "intercepthtml";
		} else {
			
			//获得用户Session信息,这里面包括了当前用户权限列表
			@SuppressWarnings("unused")
			UserInfo us = (UserInfo) ac.getSession().get(Constant.USER_SESSION_KEY);
            //session是否过期判断
			if(us==null){
				//已过期,跳转到前台
				HttpServletRequest request = (HttpServletRequest) ac.get(ServletActionContext.HTTP_REQUEST);
				StringBuffer sb = new StringBuffer();
				sb.append(Util.getWebRealPath(request));
				sb.append(request.getContextPath());
				sb.append("/");
				sb.append(Util.getActionMappingURLWithoutPrefix(ac.getName()));
				UrlHelper.buildParametersString(map, sb, "&");
				ac.getValueStack().set("interceptError", "对不起,Session已过期,请重新登录");
				return "intercepthtml";
			}
			//Map permission = (Map) us.getUserPermission().get(actionName + "?action=*");
			Map permission = null;
			if (permission != null) {
				havePermission = true;
			} else {
				//permission = (Map) us.getUserPermission().get(actionName + "?action=" + saction);
				//permission = null;
				if (permission != null) {
					havePermission = true;
				} else {
					havePermission = false;
				}
			}
			if (havePermission) {
				return invocation.invoke();
			} else {
				//没有权限,在页面上要有一个让用户重新登录的窗口
				HttpServletRequest request = (HttpServletRequest) ac.get(ServletActionContext.HTTP_REQUEST);
				StringBuffer sb = new StringBuffer();
				sb.append(Util.getWebRealPath(request));
				sb.append(request.getContextPath());
				sb.append("/");
				sb.append(Util.getActionMappingURLWithoutPrefix(ac.getName()));
				UrlHelper.buildParametersString(map, sb, "&");

				String curl = sb.toString();

				ac.getValueStack().set("interceptError", "对不起,你没有这个权限");
				ac.getValueStack().set("tourl", curl);
				return "intercepthtml";
			}
		}
	}

}
