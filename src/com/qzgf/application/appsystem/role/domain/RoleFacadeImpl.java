package com.qzgf.application.appsystem.role.domain;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.qzgf.core.dao.BaseSqlMapDAO;

/**
 * 首页
 * 
 */
public class RoleFacadeImpl implements RoleFacade { 
	//常量定义
	private static final Log logger = LogFactory.getLog(RoleFacadeImpl.class);
	
	private BaseSqlMapDAO baseSqlMapDAO;
	 
	//=====================================列表信息===============================
	public List findRole(HashMap map){
		List ls = baseSqlMapDAO.queryForList("Role.findRole", map);
		return ls;
	}
	
	public  int findRoleCount(HashMap map){
		int count=(Integer)baseSqlMapDAO.queryForObject("Role.findRoleCount", map);
		return count;
	}

	//=====================================删除===============================
	/**
	 * Purpose      : 说明
	 * @param map
	 * @return 
	 */
	@Override
	public int deleteRole(HashMap map) {
		return baseSqlMapDAO.update("Role.deleteRole", map);
	}
	//=====================================增加===============================
	/**
	 * Purpose      : 说明
	 * @param map
	 * @return 
	 */
	@Override
	public int insertRole(HashMap map) {
		int result=baseSqlMapDAO.update("Role.insertRole", map);
		return result;
	}
	//=====================================修改===============================
	/**
	 * Purpose      : 说明
	 * @param map
	 * @return 
	 */
	@Override
	public int updateRole(HashMap map) {
		return baseSqlMapDAO.update("Role.updateRole", map);
	}

 
	
	//=============================常量设置=================================
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}

}
