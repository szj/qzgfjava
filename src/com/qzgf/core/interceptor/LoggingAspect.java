package com.qzgf.core.interceptor;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.aspectj.lang.JoinPoint;

import com.opensymphony.xwork2.ActionContext;
import com.qzgf.application.appsystem.user.model.UserInfo;
import com.qzgf.core.common.Constant;
import com.qzgf.core.dao.BaseSqlMapDAO;

 
public class LoggingAspect {
	private static Logger log = Logger.getLogger(LoggingAspect.class);
	private BaseSqlMapDAO baseSqlMapDAO;

	public void before(JoinPoint joinpoint) {
		//joinpoint.getArgs();//此方法返回的是一个数组，数组中包括request以及ActionCofig等类对象
		log.info("被拦截方法调用之前调用此方法，输出此语句");
	}

	public void after(JoinPoint joinpoint,Object result) {
		try {
			//当前用户信息
			UserInfo ui = (UserInfo) ActionContext.getContext().getSession()
					.get(Constant.USER_SESSION_KEY);
			//action信息
			Object[] args = joinpoint.getArgs();
			HttpServletRequest request = null;
			ActionMapping mapping = null;
			//通过分析aop监听参数分析出request等信息   
			for (int i = 0; i < args.length; i++) {
				if (args[i] instanceof HttpServletRequest) {
					request = (HttpServletRequest) args[i];
				}
				if (args[i] instanceof ActionMapping) {
					mapping = (ActionMapping) args[i];
				}
			}

			HashMap hs = new HashMap();

			hs.put("pcontrollerscode", joinpoint.getTarget().getClass()
					.getName());
			hs.put("popercode", joinpoint.getSignature().getName());
			hs.put("poperresult",result==null?"":String.valueOf(result));
			hs.put("poperip", ui.getClientIP());//Util.getIpAddr(request)
			hs.put("puserid", ui.currentUser.get("id").toString());
			//导入数据
			baseSqlMapDAO.insert("Log.insertLog", hs);

			log.info("被拦截方法调用之后调用此方法，输出此语句");
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}
	//=================常量定义======================================
	/**
	 * Purpose      : 说明
	 * @return the baseSqlMapDAO
	 */
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	/**
	 * Purpose      : 说明
	 * @param baseSqlMapDAO the baseSqlMapDAO to set
	 */

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
