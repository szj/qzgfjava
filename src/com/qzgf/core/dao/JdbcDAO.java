/**
* Copyright (C) qzgf, 2012
*
* License        :Apache License 2.0
* Project        :qzgfjava
* Package        :com.qzgf.application.appsystem.main.action
* File	         :main.java
* Written by     :fjfdszj
* Created Date   :May 21, 2012
* Purpose        :sql操作jdbc.

======================================

* Modifyer by    :fjfdszj
* Update Date    :May 21, 2012
* Purpose        :描述

*/

package com.qzgf.core.dao;

import java.sql.ResultSet;

public interface JdbcDAO {
	/**
	 * @author lsr
	 * @dete 2008年11月2日
	 * @param String sql 所要运行的sql
	 * @param Object[] para sql所要的参数
	 * @exception Exception
	 * @return ResultSet
	 * */
	public ResultSet executeSql(String sql,Object[] para)throws Exception;

	
	/**
	 * @author lsr
	 * @dete 2008年11月2日
	 * @param String sql 所要运行的sql
	 * @param Object[] para sql所要的参数
	 * @exception Exception
	 * @return ResultSet
	 * */
	public boolean executeInsertSql(String sql,Object[] para)throws Exception;
	
	/**
	 * @author lsr
	 * @dete 2008年11月2日
	 * @param String sql 所要运行的sql
	 * @param Object[] para sql所要的参数
	 * @exception Exception
	 * @return ResultSet
	 * */
	public int executeUpdateSql(String sql,Object[] para)throws Exception;
	/**
	 * @author lsr
	 * @dete 2008年11月2日
	 * 用于释放资源
	 * */
	public void clear();
}
