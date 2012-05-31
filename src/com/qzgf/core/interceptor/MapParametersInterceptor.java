package com.qzgf.core.interceptor;

import java.util.Iterator;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;

import com.opensymphony.xwork2.interceptor.NoParameters;
import com.opensymphony.xwork2.util.InstantiatingNullHandler;
import com.opensymphony.xwork2.util.OgnlValueStack;
import com.opensymphony.xwork2.util.XWorkConverter;
import com.opensymphony.xwork2.util.XWorkMethodAccessor;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * webwork拦截器
 * 用途：解决页面表单里的值通过HashMap的方式来传递时出错
 **/
@SuppressWarnings("serial")
public class MapParametersInterceptor extends AbstractInterceptor {
	@SuppressWarnings("unchecked")
	public String intercept(ActionInvocation invocation) throws Exception {
		//拦截器处理方法，将HashMap进一步封装处理(search.pid)
        if (!(invocation.getAction() instanceof NoParameters)) {
            final Map parameters = ActionContext.getContext().getParameters();
            ActionContext invocationContext = invocation.getInvocationContext();
            try {
                invocationContext.put(InstantiatingNullHandler.CREATE_NULL_OBJECTS, Boolean.TRUE);
                invocationContext.put(XWorkMethodAccessor.DENY_METHOD_EXECUTION, Boolean.TRUE);
                invocationContext.put(XWorkConverter.REPORT_CONVERSION_ERRORS, Boolean.TRUE);

                if (parameters != null) {
                    final OgnlValueStack stack = (OgnlValueStack) ActionContext.getContext().getValueStack();
                    for (Iterator iterator = parameters.entrySet().iterator();
                         iterator.hasNext();) {
                        Map.Entry entry = (Map.Entry) iterator.next();
                        String name = entry.getKey().toString();
                        if (acceptableName(name)) {
                            Object value = entry.getValue();
                            if(value!=null){
                            	Object[] tmp = (Object[])value;
                            	if(tmp.length==1){
                            		value=tmp[0];
                            	}
                            	stack.setValue(name, value);
                            }
                        }
                    }
                }
            } finally {
                invocationContext.put(InstantiatingNullHandler.CREATE_NULL_OBJECTS, Boolean.FALSE);
                invocationContext.put(XWorkMethodAccessor.DENY_METHOD_EXECUTION, Boolean.FALSE);
                invocationContext.put(XWorkConverter.REPORT_CONVERSION_ERRORS, Boolean.FALSE);
            }
        }
		return invocation.invoke();
	}
	
    protected boolean acceptableName(String name) {
        if (name.indexOf('=') != -1 || name.indexOf(',') != -1 || name.indexOf('#') != -1) {
            return false;
        } else {
            return true;
        }
    }
}
