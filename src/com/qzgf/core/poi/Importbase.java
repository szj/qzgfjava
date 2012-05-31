package com.qzgf.core.poi;

import java.util.HashMap;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.core.common.ContextHelper;
import com.qzgf.core.dao.Ibatis;
import com.qzgf.core.dao.JdbcDAOImpl;

public class Importbase {

	private Log log = LogFactory.getLog(Importbase.class);
	private HashMap list = null; 
	
	//对于手机号导入，因业务逻辑过于复杂无法通用暂不使用该方法1.sql语句处理麻烦，因即有增加，删除也有查询。
	public void service(String exFlag,String filename,String type) {
		//1.初始化数据
		try {
			init();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		//2.一些异常处理
		if (exFlag == null) {
			if (log.isErrorEnabled()) {
				log.error("无法获得导出Flag!");
			}
			return;
		}
		
		ExportInfo exportInfo = (ExportInfo) list.get(exFlag);
		if (exportInfo == null) {
			if (log.isErrorEnabled()) {
				log.error("无法从资源文件里获得导出信息!");
			}
			return;
		}
		//3.启用sql陈述
		JdbcDAOImpl statementManager = (JdbcDAOImpl) ContextHelper.getBean("statementManager");
		Ibatis iu = new Ibatis(statementManager.getSqlMapClient());
		ImportUtil eu = new ImportUtil(statementManager);
		
		try {
			//获得需要导出的记录集
			ImportIface exportExc = ImportFactory.getInstance(type, filename,exportInfo.getColumns(), exportInfo.getFields());
			//导入数据
			HashMap line = exportExc.readLine();
			while (line != null) {
				//获取对应的sql语句
				String sql = iu.getSql(exportInfo.getSqlId(), line);
				//求得生成sql所需要的参数
				Object[] objPara = iu.getSqlParam();
				//导入数据的sql处理
				eu.importdata(sql, objPara);
				//下一轮循环处理
				line = exportExc.readLine();
			}
			exportExc.close();
		} catch (Exception ex) {
			if (log.isErrorEnabled())
				log.error(ex);
		}
	}
	
    //主要目的是获取List数据信息，即导入的配置文件信息
	public void init() throws ServletException {
		String configfile = "/export-config.xml";//读取配置文件信息
		if (configfile == null || configfile.trim().length() == 0) {
			if (log.isDebugEnabled()) {
				log.debug("初值化export信息失败!");
			}
			return;
		}
		if (log.isDebugEnabled()) {
			log.debug("初值化export文件" + configfile);
		}
		ImportUtil importUtil = new ImportUtil(null);
		list = importUtil.importConfig(configfile);
	}
	
	
	//测试用例,这个需要程序在tomcat运行的状态下进行测试 
	public static void main(String[] args){
		Importbase ib=new Importbase();
		ib.service("uploadExcelImport", "d:/shen.xls", "xls");
	}
	/**
	 * @return the list
	 */
	public HashMap getList() {
		return list;
	}

}
