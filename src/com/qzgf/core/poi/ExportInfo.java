
package com.qzgf.core.poi;
/**
 *@author chenf
 *@date 2005年11月17日
 *@docRoot
 *    用于描述导出的相关信息，比如：文件名称、对应于ibatis SqlMap的 select id
 *    需要展示的列名及所对应的数据字据
 * */
public class ExportInfo {
	//标志id
	private String id = null;
	//对应于ibatis map配置的select id
	private String sqlId = null;
	//导出的文件名称
	private String fileName = null;
	//导出对应的列头
	private String[] columns = null;
	//数据库表的字表
	private String[] fields = null;
	//备注信息
	private String description = null;
	/**
	 * 获取导出对应的列头
	 * */
	public String[] getColumns() {
		return columns;
	}
	/**
	 * 设置导出对应的列头
	 * */
	public void setColumns(String[] columns) {
		this.columns = columns;
	}
	/**
	 * 获取备注信息
	 * */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置备注信息
	 * */
	public void setDescription(String description) {
		this.description = description;
	}
	public String[] getFields() {
		return fields;
	}
	public void setFields(String[] fields) {
		this.fields = fields;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSqlId() {
		return sqlId;
	}
	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}
}
