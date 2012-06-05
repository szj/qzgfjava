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
	 
	//基本操作
	public abstract List findRole(HashMap map);
	
	public abstract int findRoleCount(HashMap map);
	
	public abstract int insertRole(HashMap map);
	
	public abstract int deleteRole(HashMap map);
	
	public abstract int updateRole(HashMap map);
	//扩展操作
	//角色下拉框
	public abstract List findRoleSelect(HashMap map);
	//权限树
	public abstract String RightTree(HashMap map);
	//角色菜单
	public abstract int insertRoleMenu(HashMap map);
	//角色菜单信息
	public abstract List findRoleMenu(HashMap map);	
	//清 除角色菜单信息
	public abstract int deleteRoleMenu(HashMap map);
	
}