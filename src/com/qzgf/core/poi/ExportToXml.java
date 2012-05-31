package com.qzgf.core.poi;
/**
 * 导出xml格式数据
 * @author chenf
 * */
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ExportToXml implements ExportIface {
	private Log log = LogFactory.getLog(ExportToXml.class);
	
	public boolean export(ServletOutputStream out, ResultSet rs, String sheetName,
			String[] fields, String[] colName){
		return false;
	}	
	/**
	 * 导出xml数据
	 * 为了方便将采用直接组成xml格式输入，也考虑到如果在大数据时，应用第三方开源包来组成
	 * xml格式的文件后再输出是否存对内存上的压力。
	 * @author chenf
	 * 
	 * @param out
	 *            输出流
	 * @param rs
	 *            需要导出的数据
	 * @param sheetName
	 *            文件名称
	 * @param fields
	 *            要导出的字段名
	 * @param colname
	 *            展示的列名
	 * @return boolean true为导出成功，false导出失败
	 */
	public boolean export(PrintWriter out, ResultSet rs, String sheetName,
			String[] fields, String[] colName) {
		if (out == null)
			return false;
		if (rs == null) {
			return false;
		}
		try {
			StringBuffer row = null;
			out.println("<?xml version=\"1.0\" encoding=\"GBK\" ?><table>");
		    //---可以在此添加一些对字段的注释
			
			// 导出主体数据
			while (rs.next()) {
				row = new StringBuffer();
				row.append("<row>");
				for (int i = 0; i < fields.length; i++) {
					row.append("<"+fields[i]+" name=\""+colName[i]+"\">").append(rs.getString(fields[i])).append("</"+fields[i]+">");
				}
				row.append("</row>");
				out.println(row.toString());
			}
			out.println("</table>");
		} catch (Exception ex) {
			if (log.isErrorEnabled()) {
				log.error(ex);
			}
		}
		return true;
	}

}
