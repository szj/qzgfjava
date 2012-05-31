package com.qzgf.core.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;


/**
 * 数据采集定时监听器,通过监听器来调用(web.xml)
 *
 */
public class DataCollectionTimerListener implements ServletContextListener {
	private Logger log = Logger.getLogger(DataCollectionTimerListener.class);
	private java.util.Timer timer = null;
	
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();
		
		System.out.print("sys数据采集定时器");
		log.debug("数据采集定时器");
		
		if (log.isDebugEnabled())
			log.debug("数据采集定时器已启动");
		
		/*
		//激活GPS接口数据采集周期为1小时
		timer = new java.util.Timer(true);
		DataCollectionTimerTask dataCollectionTimerTask = new DataCollectionTimerTask(servletContext);
		timer.schedule(dataCollectionTimerTask, 0, 60 * 60 * 1000);
		
		
				
		//预警生成器周期为1小时
		ExecCreateWarningLogProcTimerTask execCreateWarningLogProcTimerTask = new ExecCreateWarningLogProcTimerTask(servletContext);
		timer.schedule(execCreateWarningLogProcTimerTask, 0, 60 * 60 * 1000);
		
		//IC卡异常报警每隔30分钟  30 * 60 * 1000
		CarICCollectionTimerTask carICCollectionTimerTask = new CarICCollectionTimerTask(servletContext);
		timer.schedule(carICCollectionTimerTask, 0, 30 * 60 * 1000);

		//回站报警每隔10分钟  10 * 60 * 1000
		BackSiteTimerTask backSiteTimerTask = new BackSiteTimerTask(servletContext);
		timer.schedule(backSiteTimerTask, 0, 10 * 60 * 1000);
		*/
		if (log.isDebugEnabled())
			log.debug("数据采集已经添加任务调度表");
	}
	
	public void contextDestroyed(ServletContextEvent arg0) {

	}
}
