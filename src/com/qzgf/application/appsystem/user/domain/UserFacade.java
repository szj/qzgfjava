package com.qzgf.application.appsystem.user.domain;

import java.util.HashMap;
import java.util.List;

import com.qzgf.core.dao.BaseSqlMapDAO;

/**
 */
public interface UserFacade {

	//数据操作
	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);
	
	
	//===================基本操作======================================
	public abstract List findUser(HashMap map);
	
	public abstract int findUserCount(HashMap map);
	
	
	public abstract int insertUser(HashMap map);
	
	public abstract int deleteUser(HashMap map);
	
	public abstract int updateUser(HashMap map);
	//==============================================================

}