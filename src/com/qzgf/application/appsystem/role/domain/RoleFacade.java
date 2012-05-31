package com.qzgf.application.appsystem.role.domain;

import java.util.HashMap;
import java.util.List;

import com.qzgf.core.dao.BaseSqlMapDAO;

/**
 */
public interface RoleFacade {

	//数据操作
	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO); 
	 
	//所有菜单信息
	public abstract List findRole(HashMap map);
	
	public abstract int findRoleCount(HashMap map);
	
	public abstract int insertRole(HashMap map);
	
	public abstract int deleteRole(HashMap map);
	
	public abstract int updateRole(HashMap map);

}