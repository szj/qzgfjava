package com.qzgf.core.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Ibatis数据库基础操作工具包
 * 
 */
public interface BaseSqlMapDAO {
	/**
	 * @param statementName
	 * @param parameterObject
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List queryForList(String statementName, Object parameterObject);

	
	/**
	 * author:fjfdszj
	 * date:2009-08-08
	 * 扩展实现oracle的分页查询数据的功能
	 */
	@SuppressWarnings("unchecked")
	public List queryForListExOracl(String statementName, Object parameterObject,boolean enablelimit,int skipResults, int maxResults);
	/**
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @param skipResults
	 * @param maxResults
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List queryForList(String statementName, Object parameterObject,
			int skipResults, int maxResults);

	/**
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @param keyProperty
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map queryForMap(String statementName, Object parameterObject,
			String keyProperty);

	/**
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @param keyProperty
	 * @param valueProperty
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map queryForMap(String statementName, Object parameterObject,
			String keyProperty, String valueProperty);

	/**
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @return
	 */
	public Object queryForObject(String statementName, Object parameterObject);

	/**
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @param resultObject
	 * @return
	 */
	public Object queryForObject(String statementName, Object parameterObject,
			Object resultObject);

	/**
	 * @param statementName
	 * @param parameterObject
	 * @return
	 */
	public int update(String statementName, Object parameterObject);

	/**
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @param requiredRowsAffected
	 */
	public void update(String statementName, Object parameterObject,
			int requiredRowsAffected);

	/**
	 * 
	 * @param statementName
	 * @param parameterObject
	 */
	public Object insert(String statementName, Object parameterObject);

	/**
	 * 
	 * @param statementName
	 * @param parameterObject
	 */
	public void delete(String statementName, Object parameterObject);

	/**
	 * 返回sequences值
	 * 
	 * @param seqName
	 * @return
	 */
	public String sequences(String seqName);
	
	public void input(String statementName, Object parameterObject);
	
	/**
	 * 返回参数集值
	 * 时间:2009-11-30
	 * 功能:相关参数
	 * @param map,含psort类别的参数
	 * @return
	 */
	public List parameterValue(HashMap map);
}
