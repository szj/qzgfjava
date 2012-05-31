/**
* Copyright (C) qzgf, 2012
*
* License        :Apache License 2.0
* Project        :qzgfjava
* Package        :com.qzgf.core.filter
* File	         :FilterParam.java
* Written by     :fjfdszj
* Created Date   :May 28, 2012
* Purpose        :功能描述

======================================

* Modifyer by    :fjfdszj
* Update Date    :May 28, 2012
* Purpose        :描述

*/
package com.qzgf.core.filter;

/**
 * Purpose      : 说明
 *
 * @author fjfdszj
 * @see     FilterParam.java
 *
 */
public class FilterParam {
	
	 public FilterParam(String name, Object value)
     {
         this.name = name;
         this.value = value;
     }
     public String name;
     public Object value;
     
     
     //================常量定义=================================
	/**
	 * Purpose      : 说明
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Purpose      : 说明
	 * @param name the name to set
	 */
	
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Purpose      : 说明
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}
	/**
	 * Purpose      : 说明
	 * @param value the value to set
	 */
	
	public void setValue(Object value) {
		this.value = value;
	}
}
