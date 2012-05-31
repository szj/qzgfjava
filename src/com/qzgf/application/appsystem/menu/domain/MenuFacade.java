package com.qzgf.application.appsystem.menu.domain;

import java.util.HashMap;
import java.util.List;

import com.qzgf.core.dao.BaseSqlMapDAO;

/**
 */
public interface MenuFacade {

	//数据操作
	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO); 
	 
	//所有菜单信息
	public abstract List findMenu(HashMap map);
	
	public abstract int findMenuCount(HashMap map);
	
	public abstract int insertMenu(HashMap map);
	
	public abstract int deleteMenu(HashMap map);
	
	public abstract int updateMenu(HashMap map);

}