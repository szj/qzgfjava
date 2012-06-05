package com.qzgf.application.appsystem.role.domain;

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

	//========================业务扩展操作================================
	/**
	 * Purpose      : 角色下拉框
	 * @param map
	 * @return 
	 */
	@Override
	public List findRoleSelect(HashMap map) {
		List ls = baseSqlMapDAO.queryForList("Role.findRoleSelect", map);
		return ls;
	}
 
	public String RightTree(HashMap map){
		List ls = baseSqlMapDAO.queryForList("Role.findRoleMenu", map);
		return findRoleMenu(ls,"0");
	}

	public String findRoleMenu(List ls,String fatherid){
        String result = ""; 
        List list=listChildren(ls,fatherid);
        Iterator it = list.iterator(); 
        Map menuitem = new HashMap();
        while (it.hasNext()){
        	menuitem = (HashMap) it.next();
        	if(hasChildren(ls,menuitem.get("id").toString())){
        		result += "{\"id\":\"" + menuitem.get("id").toString() + 
                "\",\"name\":\"" + menuitem.get("name").toString()+
                "\",\"optval\":\"" + menuitem.get("optval").toString()+
                "\",\"father\":\"" + menuitem.get("father").toString()+
                "\",\"children\":" + findRoleMenu(ls, menuitem.get("id").toString())+
                "},";
        	}  else
             result += "{\"id\":\"" + menuitem.get("id").toString() + 
             "\",\"name\":\"" + menuitem.get("name").toString()+
             "\",\"optval\":\"" + menuitem.get("optval").toString()+
             "\",\"father\":\"" + menuitem.get("father").toString()+
                        "\"},";
         
        } 

        if (result.length() > 1)
             result = "["+result.substring(0, result.length() - 1)+"]";

        return result;
		
	}
	//遍历数组查询是否有子结点
	private boolean hasChildren(List ls, String id) {
        Iterator it = ls.iterator(); 
        Map menuitem = new HashMap();
        while (it.hasNext()){
        	menuitem = (HashMap) it.next();
        	if(menuitem.get("father").equals(id)){
        		return true;
        	}
        }
        return false;
	}
	
	//遍历数组查询是否有子结点
	private List listChildren(List ls, String id) {
         List result = new ArrayList();
         java.util.Iterator it = ls.iterator(); 
         Map menuitem = new HashMap();
         while (it.hasNext()){
         	menuitem = (HashMap) it.next();
         	if(menuitem.get("father").equals(id)){
                result.add(menuitem);
         	}
         }
         return result;
	}
	
	/**
	 * Purpose      : 角色--菜单
	 * @param map
	 * @return 
	 */
	@Override
	public int insertRoleMenu(HashMap map) { 
		return baseSqlMapDAO.update("Role.insertRolemenu", map);
	}


	/**
	 * Purpose      : 说明
	 * @param map
	 * @return 
	 */
	@Override
	public List findRoleMenu(HashMap map) {
		 return baseSqlMapDAO.queryForList("Role.findRoleMenu", map);
	}

	/**
	 * Purpose      : 说明
	 * @param map
	 * @return 
	 */
	@Override
	public int deleteRoleMenu(HashMap map) {
		return baseSqlMapDAO.update("Role.deleteRolemenu", map);
	}


	
	//=============================常量设置=================================
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}


}
