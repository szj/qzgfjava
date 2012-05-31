/**
* Copyright (C) qzgf, 2012
*
* License        :Apache License 2.0
* Project        :qzgfjava
* Package        :com.qzgf.core.filter
* File	         :FilterRule.java
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
 * @see     FilterRule.java
 *
 */
public class FilterRule {
	public FilterRule()
    {
    }
    public FilterRule(String field, Object value)
    {
      new FilterRule(field, value, "equal");
    }

    public FilterRule(String field, Object value, String op)
    {
        this.field = field;
        this.value = value;
        this.op = op;
    }

    public String field;
    public Object value;
    public String op;
    public String type;
    
    
    
    //----------------常量定义--------------------------------------
	/**
	 * Purpose      : 说明
	 * @return the field
	 */
	public String getField() {
		return field;
	}
	/**
	 * Purpose      : 说明
	 * @param field the field to set
	 */
	
	public void setField(String field) {
		this.field = field;
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
	/**
	 * Purpose      : 说明
	 * @return the op
	 */
	public String getOp() {
		return op;
	}
	/**
	 * Purpose      : 说明
	 * @param op the op to set
	 */
	
	public void setOp(String op) {
		this.op = op;
	}
	/**
	 * Purpose      : 说明
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * Purpose      : 说明
	 * @param type the type to set
	 */
	
	public void setType(String type) {
		this.type = type;
	}
}
