package com.qzgf.application.appsystem.main.domain;

import java.util.HashMap;
import java.util.List;

import com.qzgf.core.dao.BaseSqlMapDAO;

/**
 */
public interface MainFacade {

	//数据操作
	public abstract BaseSqlMapDAO getBaseSqlMapDAO();

	public abstract void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO);
	
	//用户信息
	public abstract HashMap findUserbyName(String username);
	//菜单树
	public abstract String GetTree(List ls, String id);
	
	//所有菜单信息
	public abstract List findMenu(String userid);
	//系统默认按钮
	public abstract List findSysButton(String userid);
	
	public HashMap findMenuMsg(HashMap map);
	
	//所有菜单信息
	public abstract List findFavorite(String userid);
	
	public abstract int insertFavorite(HashMap map);
	
	public abstract int deleteFavorite(HashMap map);
	
	public abstract int updateUserPwd(HashMap map);
}