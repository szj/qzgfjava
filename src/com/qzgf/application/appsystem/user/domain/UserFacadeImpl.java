package com.qzgf.application.appsystem.user.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.core.dao.BaseSqlMapDAO;

/**
 * 首页
 * 
 */
public class UserFacadeImpl implements UserFacade { 
	//常量定义
	private static final Log logger = LogFactory.getLog(UserFacadeImpl.class);
	
	private BaseSqlMapDAO baseSqlMapDAO;
	

 
	//=====================================列表信息===============================
	public List findUser(HashMap map){
		List ls = baseSqlMapDAO.queryForList("User.findUser", map);
		return ls;
	}
	
	public  int findUserCount(HashMap map){
		int count=(Integer)baseSqlMapDAO.queryForObject("User.findUserCount", map);
		return count;
	}
	//=====================================增加===============================
	public int insertUser(HashMap map){
		//String id=baseSqlMapDAO.sequences("t_dailywork");
		//map.put("pid", id);
		int result=baseSqlMapDAO.update("User.insertUser", map);
		return result;
	}
	//=====================================删除===============================
	public int deleteUser(HashMap map) { 
		return baseSqlMapDAO.update("User.deleteUser", map);
	}
	//=====================================修改===============================
	public int updateUser(HashMap map){
		return baseSqlMapDAO.update("User.updateUser", map);
	}

	//=============================常量设置=================================
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}

 
	
}
