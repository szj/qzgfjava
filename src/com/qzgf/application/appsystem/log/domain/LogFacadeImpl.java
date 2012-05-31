package com.qzgf.application.appsystem.log.domain;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.core.dao.BaseSqlMapDAO;

/**
 * 首页
 * 
 */
public class LogFacadeImpl implements LogFacade { 
	//常量定义
	private static final Log logger = LogFactory.getLog(LogFacadeImpl.class);
	
	private BaseSqlMapDAO baseSqlMapDAO;
	 
	//=====================================列表信息===============================
	public List findLog(HashMap map){
		List ls = baseSqlMapDAO.queryForList("Log.findLog", map);
		return ls;
	}
	
	public  int findLogCount(HashMap map){
		int count=(Integer)baseSqlMapDAO.queryForObject("Log.findLogCount", map);
		return count;
	}

	//=====================================删除===============================
	/**
	 * Purpose      : 说明
	 * @param map
	 * @return 
	 */
	@Override
	public int deleteLog(HashMap map) {
		return baseSqlMapDAO.update("Log.deleteLog", map);
	}
	//=====================================增加===============================
	/**
	 * Purpose      : 说明
	 * @param map
	 * @return 
	 */
	@Override
	public int insertLog(HashMap map) {
		int result=baseSqlMapDAO.update("Log.insertLog", map);
		return result;
	}
	//=====================================修改===============================
	/**
	 * Purpose      : 说明
	 * @param map
	 * @return 
	 */
	@Override
	public int updateLog(HashMap map) {
		return baseSqlMapDAO.update("Log.updateLog", map);
	}


	//=============================常量设置=================================
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}

 
	
}
