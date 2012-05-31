package com.qzgf.application.appsystem.department.domain;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.core.dao.BaseSqlMapDAO;

/**
 * 首页
 * 
 */
public class DepartmentFacadeImpl implements DepartmentFacade { 
	//常量定义
	private static final Log logger = LogFactory.getLog(DepartmentFacadeImpl.class);
	
	private BaseSqlMapDAO baseSqlMapDAO;
	
	//=====================================列表信息===============================
	public List findDepartment(HashMap map){
		List ls = baseSqlMapDAO.queryForList("Department.findDepartment", map);
		return ls;
	}
	
	public  int findDepartmentCount(HashMap map){
		int count=(Integer)baseSqlMapDAO.queryForObject("Department.findDepartmentCount", map);
		return count;
	}
	//=====================================增加===============================

	/**
	 * Purpose      : 说明
	 * @param map
	 * @return 
	 */
	@Override
	public int deleteDepartment(HashMap map) {
		return baseSqlMapDAO.update("Department.deleteDepartment", map);
	}

	/**
	 * Purpose      : 说明
	 * @param map
	 * @return 
	 */
	@Override
	public int insertDepartment(HashMap map) {
		int result=baseSqlMapDAO.update("Department.insertDepartment", map);
		return result;
	}

	/**
	 * Purpose      : 说明
	 * @param map
	 * @return 
	 */
	@Override
	public int updateDepartment(HashMap map) {
		return baseSqlMapDAO.update("Department.updateDepartment", map);
	}


	//=============================常量设置=================================
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}

 
	
}
