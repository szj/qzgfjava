/**
* Copyright (C) qzgf, 2012
*
* License        :Apache License 2.0
* Project        :qzgfjava
* Package        :com.qzgf.application.appsystem.main.action
* File	         :main.java
* Written by     :fjfdszj
* Created Date   :May 21, 2012
* Purpose        :功能描述

======================================

* Modifyer by    :fjfdszj
* Update Date    :May 21, 2012
* Purpose        :描述

*/
package com.qzgf.application.appsystem.user.action;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONSerializer;

import com.qzgf.application.BaseAction;
import com.qzgf.application.appsystem.user.domain.UserFacade;
import com.qzgf.core.common.AjaxResult;
import com.qzgf.core.common.Util;

/**
 * Purpose      : 说明
 *
 * @author fjfdszj
 * @see     DepartmentAction.java
 *
 */
public class UserAction extends BaseAction{
	//===================================变量定义======================================
	private UserFacade userFacade;
	
	//======================================页面层==================================================
	public String Index(){
		return SUCCESS;
	}
	//页面列表
	public String List(){
		return "list";
	}
	//单页查看
	public String Listdetail(){
		search.put("action", "View");
		return "listdetail";
	}
	//编辑页面
	public String Listedit(){
		search.put("action", "Edit");
		return "listdetail";
	}
	//增加页面
	public String Listadd(){
		search.put("action", "Add");
		return "listdetail";
	}
	//表格页面数据
	public String Grid(){
		super.Grid();
		//HashMap hs=new HashMap();
		List ls=userFacade.findUser(search);
		String data= JSONSerializer.toJSON(ls).toString();
		int count=userFacade.findUserCount(search);
		json="{\"Rows\":"+data+",\"Total\":"+count+"}";
		return viewjson;
	}
	//===============================LG.Ajax专用(为传递参数方便，必须加HashMap search)=================
	//单行记录
	public String GetUser(HashMap search) {
		List ls=userFacade.findUser(search);
		AjaxResult ar=AjaxResult.Success(ls.get(0));
		json = ar.toString();
		return json;
	}
	
	//验证用户名是否重复
	public boolean Exist(HashMap search) {
		return true;
	}
	//删除数据
	public String Delete(HashMap search){
		AjaxResult ar=null;
		int i=userFacade.deleteUser(search);
		if(i>0){
			 ar=AjaxResult.Success("成功");
		}else{
			ar=AjaxResult.Error("失败");
		} 
		json = ar.toString(); 
		return json;
	}
	//===========================================================
	
	
	//======================================事件提交层==================================================
	//编辑数据更新
	public String Edit(){
		AjaxResult ar=null;
		String value=Util.hash(search.get("ppassword").toString());
		search.put("ppassword", value);
		int i=userFacade.updateUser(search);
		if(i>0){
			 ar=AjaxResult.Success("成功");
		}else{
			ar=AjaxResult.Error("失败");
		} 
		json = ar.toString(); 
		return viewjson;
	}
	//新增数据
	public String Add(){
		AjaxResult ar=null;
		String value=Util.hash(search.get("ppassword").toString());
		search.put("ppassword", value);
		int i=userFacade.insertUser(search);
		if(i>0){
			 ar=AjaxResult.Success("成功");
		}else{
			ar=AjaxResult.Error("失败");
		} 
		json = ar.toString();
		return viewjson;
	}
	
	
	//==============================================变量设置（由eclipse自动生成）======================
	//================================================================================================= 
	public UserFacade getUserFacade() {
		return userFacade;
	} 
	
	public void setUserFacade(UserFacade userFacade) {
		this.userFacade = userFacade;
	}

}
