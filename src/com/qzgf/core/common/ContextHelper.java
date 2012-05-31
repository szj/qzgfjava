package com.qzgf.core.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware; 
/** 
 */
public final class ContextHelper  implements ApplicationContextAware{
	 private static ApplicationContext context = null;
	 private static ContextHelper stools = null;
	 public synchronized static ContextHelper init(){
	  if(stools == null){
	   stools = new ContextHelper();
	  }
	  return stools;
	 }
	 
	 public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		 context = applicationContext;
	 }

	 public synchronized static Object getBean(String beanName) {
	  return context.getBean(beanName);
	 }
}
