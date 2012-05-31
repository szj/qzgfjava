package com.qzgf.application.appsystem.department.domain;

import java.util.HashMap;
import java.util.List;

import com.qzgf.core.dao.BaseSqlMapDAO;

/**
 */
public interface DepartmentFacade {

	//数据操作
	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO); 
	 
	//所有菜单信息
	public abstract List findDepartment(HashMap map);
	
	public abstract int findDepartmentCount(HashMap map);
	
	public abstract int insertDepartment(HashMap map);
	
	public abstract int deleteDepartment(HashMap map);
	
	public abstract int updateDepartment(HashMap map);

}