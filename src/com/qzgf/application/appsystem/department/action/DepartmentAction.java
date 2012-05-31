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
package com.qzgf.application.appsystem.department.action;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONSerializer;

import com.qzgf.application.BaseAction;
import com.qzgf.application.appsystem.department.domain.DepartmentFacade;
import com.qzgf.application.appsystem.main.domain.MainFacade; 
import com.qzgf.core.common.AjaxResult;
import com.qzgf.core.common.Util;

/**
 * Purpose      : 说明
 *
 * @author fjfdszj
 * @see     DepartmentAction.java
 *
 */
public class DepartmentAction extends BaseAction{
	//===================================变量定义======================================
	private DepartmentFacade departmentFacade;
	
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
		List ls=departmentFacade.findDepartment(search);
		String data= JSONSerializer.toJSON(ls).toString();
		int count=departmentFacade.findDepartmentCount(search);
		json="{\"Rows\":"+data+",\"Total\":"+count+"}";
		return viewjson;
	}
	//===============================LG.Ajax专用(为传递参数方便，必须加HashMap search)=================
	//单行记录
	public String GetDepartment(HashMap search) {
		List ls=departmentFacade.findDepartment(search);
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
		int i=departmentFacade.deleteDepartment(search);
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
		int i=departmentFacade.updateDepartment(search);
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
		int i=departmentFacade.insertDepartment(search);
		if(i>0){
			 ar=AjaxResult.Success("成功");
		}else{
			ar=AjaxResult.Error("失败");
		} 
		json = ar.toString();
		return viewjson;
	}
	/**
	 * Purpose      : 说明
	 * @return the departmentFacade
	 */
	public DepartmentFacade getDepartmentFacade() {
		return departmentFacade;
	}
	/**
	 * Purpose      : 说明
	 * @param departmentFacade the departmentFacade to set
	 */
	
	public void setDepartmentFacade(DepartmentFacade departmentFacade) {
		this.departmentFacade = departmentFacade;
	}
	

}
