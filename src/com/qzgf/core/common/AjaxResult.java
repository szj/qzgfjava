/**
* Copyright (C) qzgf, 2012
*
* License        :Apache License 2.0
* Project        :qzgfjava
* Package        :com.qzgf.core.common
* File	         :AjaxResult.java
* Written by     :fjfdszj
* Created Date   :May 23, 2012
* Purpose        :功能描述

======================================

* Modifyer by    :fjfdszj
* Update Date    :May 23, 2012
* Purpose        :描述

*/
package com.qzgf.core.common;

import net.sf.json.JSONSerializer;

/**
 * Purpose      : ajax提示对象，返回json格式
 *
 * @author fjfdszj
 * @see     AjaxResult.java
 *
 */
public class AjaxResult {
    private boolean iserror = false;
 
    public String message  ="";
    
    public Object data;
    
    /**
     * 
     * Purpose      : 将数据转化为json格式
     * @return
     */
    @Override
	public String toString() { 
    	return JSONSerializer.toJSON(this).toString();
    }
	//================================成功提示============================================
	public static AjaxResult Success()
    {
		AjaxResult ar=new AjaxResult();
		ar.iserror=false;
        return ar;
    }
    public static AjaxResult Success(String message)
    {
    	AjaxResult ar=new AjaxResult();
		ar.iserror=false;
		ar.message=message;
        return ar;
    }
    public static AjaxResult Success(Object data)
    {
    	AjaxResult ar=new AjaxResult();
		ar.iserror=false;
		ar.data=data;
        return ar;
    }
    public static AjaxResult Success(Object data, String message)
    {
    	AjaxResult ar=new AjaxResult();
		ar.iserror=false;
		ar.data=data;
		ar.message=message;
        return ar;
    }
    //======================================出错提示=============================================
    
    public static AjaxResult Error()
    {
    	AjaxResult ar=new AjaxResult();
		ar.iserror=true; 
        return ar;
     
    }
    public static AjaxResult Error(String message)
    {
    	AjaxResult ar=new AjaxResult();
		ar.iserror=true; 
		ar.message=message;
        return ar;
        
    }
    //======================变量由eclipse自动生成============================
	/**
	 * Purpose      : 说明
	 * @return the iserror
	 */
	public boolean isIserror() {
		return iserror;
	}
	/**
	 * Purpose      : 说明
	 * @param iserror the iserror to set
	 */
	
	public void setIserror(boolean iserror) {
		this.iserror = iserror;
	}
	/**
	 * Purpose      : 说明
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * Purpose      : 说明
	 * @param message the message to set
	 */
	
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * Purpose      : 说明
	 * @return the data
	 */
	public Object getData() {
		return data;
	}
	/**
	 * Purpose      : 说明
	 * @param data the data to set
	 */
	
	public void setData(Object data) {
		this.data = data;
	}

}
