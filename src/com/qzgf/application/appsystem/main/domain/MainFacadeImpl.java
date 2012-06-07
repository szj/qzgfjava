package com.qzgf.application.appsystem.main.domain;

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
public class MainFacadeImpl implements MainFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(MainFacadeImpl.class);
	
	private BaseSqlMapDAO baseSqlMapDAO;
	
	/**
	 * 
	 * Purpose      : 用户信息
	 * @param username
	 * @return
	 */
	public HashMap findUserbyName(String username){
		Map map=new HashMap();
		map.put("pusername", username);
		List userList = baseSqlMapDAO.queryForList("Main.findUserbyName", map);
		HashMap hs=new HashMap();
		if(userList.size()>0)
			hs=(HashMap)userList.get(0);
		return hs;
	}
	
	//==============================得到菜单信息=========================================
	//===================================================================================

	public List findMenu(String userid){
		Map map=new HashMap();
		map.put("pismenu", 1);
		List menuList = baseSqlMapDAO.queryForList("Main.findMenu", map);
		return menuList;
	}
	

	public List findSysButton(String optval,String menuno){
		Map map=new HashMap();
		map.put("pismenu", 3);
		map.put("poptval", optval);
		map.put("pfather", menuno);
		List menuList = baseSqlMapDAO.queryForList("Main.findButton", map);
		return menuList;
	}
	
	
	public HashMap findMenuMsg(HashMap map){ 
		List menuList = baseSqlMapDAO.queryForList("Main.findMenu", map);
		HashMap hm=new HashMap();
		if(menuList.size()>0){
			hm=(HashMap)menuList.get(0);
		}
		return hm;
	}
	/**
	 * 处理菜单叠代返回值
	 * Purpose      : 说明
	 * @return
	 */
	public  String GetTree(List ls, String id)
    {
        String result = ""; 
        List list=listChildren(ls,id);
        Iterator it = list.iterator(); 
        Map menuitem = new HashMap();
        while (it.hasNext()){
        	menuitem = (HashMap) it.next();
        	if(hasChildren(ls,menuitem.get("id").toString())){
        		result += "{\"icon\":\"" + menuitem.get("icon").toString() +
     		    "\", \"id\":\"" +menuitem.get("id").toString() +
                "\",\"MenuName\":\"" + menuitem.get("name").toString()+
                "\",\"MenuID\":\"" + menuitem.get("id").toString()+
                "\",\"text\":\"" + menuitem.get("name").toString()+
                "\",\"MenuUrl\":\"" + menuitem.get("url").toString()+
                "\",\"MenuIcon\":\"" + menuitem.get("icon").toString()+
                "\",\"MenuNo\":\"" + menuitem.get("id").toString()+
                "\",\"MenuParentNo\":\"" + menuitem.get("father").toString()+
                "\",\"children\":[" + GetTree(ls, menuitem.get("id").toString())+
                "]},";
        	}  else
             result += "{\"icon\":\"" + menuitem.get("icon").toString() +
             		    "\", \"id\":\"" +menuitem.get("id").toString() +
                        "\",\"MenuName\":\"" + menuitem.get("name").toString()+
                        "\",\"MenuID\":\"" + menuitem.get("id").toString()+
                        "\",\"text\":\"" + menuitem.get("name").toString()+
                        "\",\"MenuUrl\":\"" + menuitem.get("url").toString()+
                        "\",\"MenuIcon\":\"" + menuitem.get("icon").toString()+
                        "\",\"MenuNo\":\"" + menuitem.get("id").toString()+
                        "\",\"MenuParentNo\":\"" + menuitem.get("father").toString()+
                        "\"},";
         
        } 

        if (result.length() > 1)
             result = result.substring(0, result.length() - 1);

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
	 * Purpose      : 查询收藏夹
	 * @param userid
	 * @return 
	 */
	@Override
	public List findFavorite(String userid) {
		Map map=new HashMap();
		map.put("puserid", userid);
		List curList = baseSqlMapDAO.queryForList("Main.findFavoritebyName", map);
		return curList;
	}

	/**
	 * Purpose      : 增加收藏夹
	 * @param map
	 * @return 
	 */
	@Override
	public int insertFavorite(HashMap map) {
		int result=baseSqlMapDAO.update("Main.insertFavorite", map);
		return result;
	}

	/**
	 * Purpose      : 说明
	 * @param map
	 * @return 
	 */
	@Override
	public int deleteFavorite(HashMap map) {
		int result=baseSqlMapDAO.update("Main.deleteFavorite", map);
		return result;
	}

	/**
	 * Purpose      : 修改密码
	 * @param map
	 * @return 
	 */
	@Override
	public int updateUserPwd(HashMap map) {
		int result=baseSqlMapDAO.update("Main.updateUserPwd", map);
		return result;
	}
	/**
	 * Purpose      : 菜单权限
	 * @param map
	 * @return 
	 */
	@Override
	public List findMenuPermission(HashMap map) { 
		return baseSqlMapDAO.queryForList("Main.findMenuPermission", map);
	}

	/**
	 * Purpose      : 字段权限
	 * @param map
	 * @return 
	 */
	@Override
	public List findMenufieldPermission(HashMap map) { 
		return baseSqlMapDAO.queryForList("Main.findMenufieldPermission", map);
	}

 
	//=============================常量设置=================================
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}



}
