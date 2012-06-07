package com.qzgf.core.interceptor;

import java.util.HashMap;
import java.util.List;
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
import com.qzgf.core.common.AjaxResult;
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

	private String json ="";
	private static Logger logger = Logger
			.getLogger(UserSessionInterceptor.class);

	/**
	 * Purpose      : 用户session检测，及权限检测
	 * @param arg0
	 * @return
	 * @throws Exception 
	 */
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		ActionContext ac = invocation.getInvocationContext();
		Object action = invocation.getAction();
		if (action instanceof BaseAction) {
			//取得请求的url 例： /appsystem/role!Listdetail.do
			HttpServletRequest newrequest = ServletActionContext.getRequest();
			String currentURL = newrequest.getRequestURI();

			UserInfo ui = (UserInfo) ac.getSession().get(
					Constant.USER_SESSION_KEY);
			Map map = ac.getParameters(); //获得参数值,这边就是URL后面带的那个:action=index
			if (ui == null) {
				ac.getValueStack().set("interceptError", "页面Session过期，是否转到登陆页?");
				return "intercepthmsg";
			} else if(ui.currentUser.get("id").equals("1")){
				//系统管理员什么都不检查
			}
			else {
				//权限拦截
				//增加，删除，改，查等
				String[] parm = currentURL.split("!");
				String purl = parm[0];
				if(!purl.contains(".do")){
					purl=purl.concat(".do");
				}
				if(!Constant.Allower_Url.contains(purl)){
					if (parm.length > 1) {
						String pmethod = parm[1];
						if(pmethod.contains(".do")){
							pmethod=pmethod.replace(".do", "");
						}
						if(Constant.Allower_Method.containsKey(pmethod)){
							//处理权限--菜单按钮
							List ls=ui.getMenuPermission();
							if(!CheckPermission(ls,purl,pmethod)){
								
								ac.getValueStack().set("interceptError", "您没有执行"+purl+":"+pmethod+"的权限");
								return "intercepthmsg";	
								
//								AjaxResult	ar=AjaxResult.Error("您没有执行"+purl+":"+pmethod+"的权限");
//								//((BaseAction) action).json = ar.toString();
//								json = ar.toString();
//								return "json";
							}
						}
					}else{ 
						//处理权限--菜单
						List ls=ui.getMenuPermission();
						if(!CheckPermission(ls,purl,"")){ 
							ac.getValueStack().set("interceptError", "您没有执行"+purl+"的权限");
							return "intercepthmsg";	
							
							
//							AjaxResult	ar=AjaxResult.Error("您没有执行"+purl+"的权限");
//							json = ar.toString();
//							return "json"; 
						}
					}
				}
			} 
			((BaseAction) action).setUserInfo(ui);

		}
		return invocation.invoke();
	}

	
	public boolean CheckPermission(List ls,String url,String method){
		boolean result=false;
		for(Object obj:ls){
			HashMap hs=(HashMap)obj;
			if(method.equals("") && hs.get("url").toString().equalsIgnoreCase(url)){
				return true;
			}else if(!method.equals("") && hs.get("url").toString().equalsIgnoreCase(url)){
				//hs.get("optval").toString().trim()
				Integer optval=Integer.valueOf(hs.get("optval").toString().trim());
				Integer methodval=Constant.Allower_Method.get(method);
				if((optval & methodval)==methodval){
					return true;
				}else{
					return false;
				}
			}
		}
		return result;
		
	}


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

}
