/**
 * 这是一个根据iBatis的特性写的一个工具类
 * 根据ID可以取得对于的SQL和需要的参数条件
 * **/
package com.qzgf.core.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.impl.ExtendedSqlMapClient;
import com.ibatis.sqlmap.engine.impl.SqlMapExecutorDelegate;
import com.ibatis.sqlmap.engine.mapping.parameter.ParameterMap;
import com.ibatis.sqlmap.engine.mapping.sql.Sql;
import com.ibatis.sqlmap.engine.mapping.statement.MappedStatement;
import com.ibatis.sqlmap.engine.scope.RequestScope;

public class Ibatis {
	private Object[] sqlParam = null;

	private SqlMapClient sqlMapClient = null;
	private Log log = LogFactory.getLog(Ibatis.class);
	public Ibatis(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient c) {
		sqlMapClient = c;
	}

	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	/**
	 * 取得一条可以完成的sql,在方法里有给sqlParam数组赋值,这个数组里存在了sql所需要的参数
	 * @author chenf
	 * @param String sqlMapId 对应于ibatis配置文件里的sql语句
	 * @param Object parameter 前台传入的参数
	 * @throws none
	 * */
	public String getSql(String sqlMapId, Object parameter) {
		String sqlValue = "";
		ExtendedSqlMapClient extendedSqlMapClient = (ExtendedSqlMapClient) sqlMapClient;
		SqlMapExecutorDelegate sqlMapExecutorDelegate = extendedSqlMapClient
				.getDelegate();
		MappedStatement mappedStatement = sqlMapExecutorDelegate
				.getMappedStatement(sqlMapId);
		RequestScope requestScope = new RequestScope();
		mappedStatement.initRequest(requestScope);
		Sql sql = mappedStatement.getSql();

		sqlValue = sql.getSql(requestScope, parameter);
		
		ParameterMap pm = sql.getParameterMap(requestScope, parameter);
		sqlParam = pm.getParameterObjectValues(requestScope, parameter);
		if(log.isDebugEnabled())
			log.debug(sqlValue);
		return sqlValue;
	}

	public void setSqlParam(Object[] para) {
		sqlParam = para;
	}

	public Object[] getSqlParam() {
		return sqlParam;
	}
}
