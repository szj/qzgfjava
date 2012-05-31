/**
* Copyright (C) qzgf, 2012
*
* License        :Apache License 2.0
* Project        :qzgfjava
* Package        :com.qzgf.core.filter
* File	         :FilterGroup.java
* Written by     :fjfdszj
* Created Date   :May 28, 2012
* Purpose        :功能描述

======================================

* Modifyer by    :fjfdszj
* Update Date    :May 28, 2012
* Purpose        :描述

*/
package com.qzgf.core.filter;

import java.util.List;

/**
 * Purpose      : 说明
 *
 * @author fjfdszj
 * @see     FilterGroup.java
 *
 */
public class FilterGroup {
    public List<FilterRule> rules;
    public String op;
    public List<FilterGroup> groups;
    
    
    
    //=================================常量定义=======================================
	/**
	 * Purpose      : 说明
	 * @return the rules
	 */
	public List<FilterRule> getRules() {
		return rules;
	}
	/**
	 * Purpose      : 说明
	 * @param rules the rules to set
	 */
	
	public void setRules(List<FilterRule> rules) {
		this.rules = rules;
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
	 * @return the groups
	 */
	public List<FilterGroup> getGroups() {
		return groups;
	}
	/**
	 * Purpose      : 说明
	 * @param groups the groups to set
	 */
	
	public void setGroups(List<FilterGroup> groups) {
		this.groups = groups;
	}
}
