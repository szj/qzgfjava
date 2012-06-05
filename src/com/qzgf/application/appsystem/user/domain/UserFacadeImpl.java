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
	public int insertUser(HashMap map,String roleids){
		String id=baseSqlMapDAO.sequences("tab_system_user");
		map.put("pid", id);
		//处理子表，用户角色信息
		String[] rolearr=roleids.split(",");
		for(String currole:rolearr){
			HashMap search=new HashMap();
			search.put("puserid", id);
			search.put("proleid", currole);
			baseSqlMapDAO.update("User.insertUserrole", search);
		}
		//主表
		int result=baseSqlMapDAO.update("User.insertUser", map);
		return result;
	}
	//=====================================删除===============================
	public int deleteUser(HashMap map) { 
		return baseSqlMapDAO.update("User.deleteUser", map);
	}
	//=====================================修改===============================
	public int updateUser(HashMap map,String roleids){
		String id=map.get("pid").toString();
		map.put("pid", id);
		//处理子表，用户角色信息
		//先删除
		int i=baseSqlMapDAO.update("User.deleteUserrole", id);
		String[] rolearr=roleids.split(",");
		for(String currole:rolearr){
			HashMap search=new HashMap();
			search.put("puserid", id);
			search.put("proleid", currole);
			i=i+baseSqlMapDAO.update("User.insertUserrole", search);
		}
		
		return baseSqlMapDAO.update("User.updateUser", map);
	}

	//====================扩展=============================
	/**
	 * Purpose      :验证用户名是否重复
	 * @param username
	 * @return 
	 */
	@Override
	public boolean findUserbyName(HashMap map) {
		boolean result=false;
		List userList = baseSqlMapDAO.queryForList("User.findUserbyName", map);
		HashMap hs=new HashMap();
		if(userList.size()>0)
			result=true;
		return result;
	}

	/**
	 * Purpose      :查询机构下拉框信息
	 * @param map
	 * @return 
	 */
	@Override
	public List findDepartment(HashMap map) {
		List ls = baseSqlMapDAO.queryForList("User.findDepartment", map);
		return ls;
	}


	/**
	 * Purpose      : 用户角色信息
	 * @param map
	 * @return 
	 */
	@Override
	public List findUserRole(HashMap map) {
		 return  baseSqlMapDAO.queryForList("User.findUserrole", map);
	}


	//=============================常量设置=================================
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}

 
	
}
