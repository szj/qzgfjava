package com.qzgf.application.appsystem.log.domain;

import java.util.HashMap;
import java.util.List;

import com.qzgf.core.dao.BaseSqlMapDAO;

/**
 */
public interface LogFacade {

	//数据操作
	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO); 
	 
	//所有菜单信息
	public abstract List findLog(HashMap map);
	
	public abstract int findLogCount(HashMap map);
	
	public abstract int insertLog(HashMap map);
	
	public abstract int deleteLog(HashMap map);
	
	public abstract int updateLog(HashMap map);

}