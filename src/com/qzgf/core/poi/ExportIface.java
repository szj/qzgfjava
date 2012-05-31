package com.qzgf.core.poi;


import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletOutputStream;

public interface ExportIface {
	/**
	 * 导出方法接口
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
	public boolean export(PrintWriter out, ResultSet rs, String sheetName, String[] fields, String[] colName);
	
	
	
	/**
	 * 导出方法接口
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
	public boolean export(ServletOutputStream out, ResultSet rs, String sheetName, String[] fields, String[] colName);

}
