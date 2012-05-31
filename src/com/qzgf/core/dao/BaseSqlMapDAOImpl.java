package com.qzgf.core.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * ibatis数据库基础操作实现工具包
 *  
 */
public class BaseSqlMapDAOImpl extends SqlMapClientDaoSupport implements
		BaseSqlMapDAO {
	protected SqlMapClientTemplate smcTemplate = this.getSqlMapClientTemplate();

	@SuppressWarnings("unchecked")
	public List queryForList(String statementName, Object parameterObject) {
		return this.smcTemplate.queryForList(statementName, parameterObject);
	}
	/**
	 * author:fjfdszj
	 * date:2009-08-08
	 * 扩展实现oracle的分页查询数据的功能
	 * 参数说明：enablelimit是否启用分页数据，skipResults开始页，maxResults结束页
	 * 因为String statementName是id无法直接转化为sql语句
	 */
	@SuppressWarnings("unchecked")
	public List queryForListExOracl(String statementName, Object parameterObject,boolean enablelimit,int minResults, int maxResults) {
		Ibatis iu = new Ibatis(smcTemplate.getSqlMapClient());
		String sqlsrc=iu.getSql(statementName, parameterObject);//得到带?号的sql。
		@SuppressWarnings("unused")
		Object[] parm=iu.getSqlParam();							//得到对应?号值的数组。
		if(enablelimit)//启用分页时就要调用sql语句
		{
			StringBuffer pageStr = new StringBuffer();   
	        pageStr.append("select * from ( select row_limit.*, rownum rownum_ from (");   
	        pageStr.append(sqlsrc);   
	        pageStr.append(" ) row_limit where rownum <= ");   
	        pageStr.append(maxResults);   
	        pageStr.append(" ) where rownum_ >");   
	        pageStr.append(minResults); 
	        return this.smcTemplate.queryForList(pageStr.toString(), parameterObject);
			//return this.smcTemplate.queryForList(pageStr.toString(), parameterObject);
		}
		else
		{
			return this.smcTemplate.queryForList(statementName, parameterObject);
		}
	}

	@SuppressWarnings("unchecked")
	public List queryForList(String statementName, Object parameterObject,
			int skipResults, int maxResults) {
		return this.smcTemplate.queryForList(statementName, parameterObject,
				skipResults, maxResults);
	}

	@SuppressWarnings("unchecked")
	public Map queryForMap(String statementName, Object parameterObject,
			String keyProperty) {
		return this.smcTemplate.queryForMap(statementName, parameterObject,
				keyProperty);
	}

	@SuppressWarnings("unchecked")
	public Map queryForMap(String statementName, Object parameterObject,
			String keyProperty, String valueProperty) {
		return this.smcTemplate.queryForMap(statementName, parameterObject,
				keyProperty, valueProperty);
	}

	public Object queryForObject(String statementName, Object parameterObject) {
		return this.smcTemplate.queryForObject(statementName, parameterObject);
	}

	public Object queryForObject(String statementName, Object parameterObject,
			Object resultObject) {
		return this.smcTemplate.queryForObject(statementName, parameterObject,
				resultObject);
	}

	public int update(String statementName, Object parameterObject) {
		return this.smcTemplate.update(statementName, parameterObject);
	}

	public void update(String statementName, Object parameterObject,
			int requiredRowsAffected) {
		this.smcTemplate.update(statementName, parameterObject,
				requiredRowsAffected);
	}

	public Object insert(String statementName, Object parameterObject) {
		return this.smcTemplate.insert(statementName, parameterObject);
	}
	
	public void input(String statementName, Object parameterObject) {
		this.smcTemplate.insert(statementName, parameterObject);
	}

	public void delete(String statementName, Object parameterObject) {
		this.smcTemplate.delete(statementName, parameterObject);
	}

	public String sequences(String seqName) {
		return (String) this.smcTemplate.queryForObject("getSequencesValue",
				seqName);
	}
	
	/**
	 * 返回参数集值
	 * 
	 * @param seqName
	 * @return
	 */
	public List parameterValue(HashMap map){
		return this.smcTemplate.queryForList("parameterValue",map);
	}
}
