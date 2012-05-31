package com.qzgf.application.appsystem.menu.domain;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.core.dao.BaseSqlMapDAO;

/**
 * 首页
 * 
 */
public class MenuFacadeImpl implements MenuFacade { 
	//常量定义
	private static final Log logger = LogFactory.getLog(MenuFacadeImpl.class);
	
	private BaseSqlMapDAO baseSqlMapDAO;

	//=====================================列表信息===============================
	public List findMenu(HashMap map){
		List ls = baseSqlMapDAO.queryForList("Menu.findMenu", map);
		return ls;
	}
	
	public  int findMenuCount(HashMap map){
		int count=(Integer)baseSqlMapDAO.queryForObject("Menu.findMenuCount", map);
		return count;
	}

	//=====================================删除===============================
	/**
	 * Purpose      : 说明
	 * @param map
	 * @return 
	 */
	@Override
	public int deleteMenu(HashMap map) {
		return baseSqlMapDAO.update("Menu.deleteMenu", map);
	}
	//=====================================增加===============================
	/**
	 * Purpose      : 说明
	 * @param map
	 * @return 
	 */
	@Override
	public int insertMenu(HashMap map) {
		int result=baseSqlMapDAO.update("Menu.insertMenu", map);
		return result;
	}
	//=====================================修改===============================
	/**
	 * Purpose      : 说明
	 * @param map
	 * @return 
	 */
	@Override
	public int updateMenu(HashMap map) {
		return baseSqlMapDAO.update("Menu.updateMenu", map);
	}

 

	//=============================常量设置=================================
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}

	
}
