package com.qzgf.core.poi;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.qzgf.core.dao.Ibatis;
import com.qzgf.core.dao.JdbcDAOImpl;
import com.qzgf.core.common.Util;

public class Importdata extends HttpServlet {

	private Log log = LogFactory.getLog(Importdata.class);
	private HashMap list = null;
	private static ApplicationContext ctx = null;

	public Importdata() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		list = null;
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("GBK");

		JdbcDAOImpl statementManager = (JdbcDAOImpl) ctx
				.getBean("statementManager");
		Ibatis iu = new Ibatis(statementManager.getSqlMapClient());
		//获取ExportFlag
		String exFlag = Util.getNulltoStr(request.getParameter("ExportFlag"));
		if (exFlag == null) {
			if (log.isErrorEnabled()) {
				log.error("无法获得导出Flag!");
			}
			return;
		}

		ExportInfo exportInfo = (ExportInfo) list.get(exFlag);
		//String id=exportInfo.getId();

		if (exportInfo == null) {
			if (log.isErrorEnabled()) {
				log.error("无法从资源文件里获得导出信息!");
			}
			return;
		}

		String type = request.getParameter("type");

		ImportUtil eu = new ImportUtil(statementManager);

		try {
			//获得需要导出的记录集
			String filename = "";
			ImportIface exportExc = ImportFactory.getInstance(type, filename,exportInfo.getColumns(), exportInfo.getFields());
			//导入数据
			HashMap line = exportExc.readLine();
			while (line != null) {
                //进一步封装line;
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

	public void init(ServletConfig config) throws ServletException {
		// 
		String configfile = config.getInitParameter("config");
		if (configfile == null || configfile.trim().length() == 0) {
			if (log.isDebugEnabled()) {
				log.debug("初值化export信息失败!");
			}
			return;
		}
		if (log.isDebugEnabled()) {
			log.debug("初值化export文件" + configfile);
		}

		ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
		ImportUtil importUtil = new ImportUtil(null);
		list = importUtil.importConfig(configfile);
	}

}
